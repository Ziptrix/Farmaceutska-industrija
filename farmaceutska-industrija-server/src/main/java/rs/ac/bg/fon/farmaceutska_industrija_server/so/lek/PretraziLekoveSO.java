/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 * Sistemska operacija za pretragu lekova u sistemu prema zadatom kriterijumu.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije pretrage lekova.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Lek}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Pretragu lekova u bazi koji odgovaraju kriterijumima iz prosleđenog objekta i ključa.</li>
 *   <li>Učitavanje sastava svakog pronađenog leka (supstance povezane sa lekom iz {@link SupstancaLek}).</li>
 *   <li>Podešavanje količine zaliha supstanci koje čine sastav leka.</li>
 * </ul>
 * 
 * @author milos
 */
@Getter
public class PretraziLekoveSO extends ApstraktnaSO {

    /**
     * Lista lekova koji odgovaraju kriterijumu pretrage
     */
    List<Lek> lekovi;

    /**
     * Proverava preduslove pre izvršenja pretrage lekova.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Lek}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Lek} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Lek)) {
            throw new Exception("Prosledjen objekat mora biti tipa Lek!");
        }
    }

    /**
     * Izvršava operaciju pretrage lekova u sistemu.
     * <p>
     * Operacija prvo pronalazi sve lekove koji odgovaraju prosleđenom objektu i kriterijumu
     * a to je naziv leka, nakon toga
     * učitava sve supstance povezanih lekova i postavlja količinu upotrebljenu u sastavu.
     * </p>
     *
     * @param objekat objekat tipa {@link Lek} koji sadrži kriterijume pretrage.
     * @param kljuc   ključ koji se koristi za filtriranje pretrage.
     * 
     * @throws java.lang.Exception ako pretraga ne pronađe nijedan lek ili dođe do greške pri učitavanju supstanci.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.pretrazi((Lek) objekat, kljuc);

        lekovi = rezultat.stream()
                .map(op -> (Lek) op)
                .toList();

        List<OpstaDomenskaKlasa> slZaLek = broker.ucitajSve(new SupstancaLek());

        List<SupstancaLek> supstanceULeku = slZaLek.stream()
                .map(op -> (SupstancaLek) op)
                .toList();

        for (Lek lek : lekovi) {
            List<Supstanca> sastav = supstanceULeku.stream()
                    .filter(sl -> lek.getSerijskiBroj().equals(sl.getLek().getSerijskiBroj()))
                    .map(sl -> {
                        Supstanca s = sl.getSupstanca();
                        try {
                            s.setKolicinaZaliha(sl.getUpotrebljenaKolicina());
                        } catch (Exception e) {
                            System.out.println("Greska " + e.getMessage());
                        }
                        return s;
                    })
                    .toList();

            lek.setSastav(sastav);
        }

        if (lekovi.isEmpty()) {
            throw new Exception("Sistem ne moze da pronadje lekove koji odgovaraju!");
        }
    }

}

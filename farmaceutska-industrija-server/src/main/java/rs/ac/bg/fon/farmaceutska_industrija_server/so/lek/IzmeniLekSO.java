/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 * Sistemska operacija za izmenu postojećeg leka u sistemu.
 * Kod leka je moguće promeniti doziranje i upotrebljenu količinu svih supstanci koje lek sadrži u tabeli.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije izmene leka.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Lek}.
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Izmenu doziranja leka u bazi putem {@link #broker}.</li>
 *   <li>Izmenu svih supstanci koje lek sadrži u tabeli {@link SupstancaLek} sa ažuriranim količinama.</li>
 * </ul>
 * 
 * @author milos
 */
public class IzmeniLekSO extends ApstraktnaSO {

    /**
     * Proverava preduslove pre izvršenja izmene leka.
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
     * Izvršava operaciju izmene leka u sistemu.
     * <p>
     * Operacija ažurira doziranje leka u bazi i sve njegove supstance
     * sa novim količinama zaliha.
     *
     * @param objekat objekat tipa {@link Lek} koji se menja.
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako dođe do greške pri izmeni leka ili supstanci.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Lek lek = (Lek) objekat;
        broker.izmeni((OpstaDomenskaKlasa) lek);
        SupstancaLek supstanceLeka = new SupstancaLek();
        supstanceLeka.setLek(lek);

        for (Supstanca supstanca : lek.getSastav()) {
            supstanceLeka.setSupstanca(supstanca);
            supstanceLeka.setUpotrebljenaKolicina(supstanca.getKolicinaZaliha());
            broker.izmeni(supstanceLeka);
        }
    }

}

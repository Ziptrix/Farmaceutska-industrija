/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 * Sistemska operacija za dodavanje leka u sistem.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije dodavanja leka.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Lek}.
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Dodavanje leka u bazu putem {@link #broker}.</li>
 *   <li>Dodavanje svih supstanci koje lek sadrži u tabelu {@link SupstancaLek}.</li>
 *   <li>Ažuriranje količine zaliha supstanci u bazi.</li>
 * </ul>
 * 
 * @author milos
 */
public class DodajLekSO extends ApstraktnaSO {

    /**
     * Proverava preduslove pre izvršenja dodavanja leka.
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
            throw new Exception("Prosledjeni objekat mora biti tipa Lek!");
        }
    }

    /**
     * Izvršava operaciju dodavanja leka u sistem.
     * <p>
     * Operacija dodaje lek u bazu, povezuje ga sa supstancama koje sadrži
     * i ažurira količine zaliha supstanci u bazi.
     *
     * @param objekat objekat tipa {@link Lek} koji se dodaje.
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako dođe do greške pri dodavanju leka ili ažuriranju supstanci.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Lek lek = (Lek) objekat;
        broker.dodaj(lek);

        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve(new Supstanca());

        List<Supstanca> supstanceIzBaze = rezultat.stream()
                .map(op -> (Supstanca) op)
                .toList();

        for (Supstanca supstanca : lek.getSastav()) {
            SupstancaLek sl = new SupstancaLek();
            sl.setLek(lek);
            sl.setSupstanca(supstanca);
            sl.setUpotrebljenaKolicina(supstanca.getKolicinaZaliha());
            broker.dodaj(sl);

            Supstanca izBaze = supstanceIzBaze.stream()
                    .filter(s -> s.getSifra().equals(supstanca.getSifra()))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Supstanca nije pronadjena u bazi"));

            long novaKolicina = izBaze.getKolicinaZaliha() - supstanca.getKolicinaZaliha();
            izBaze.setKolicinaZaliha(novaKolicina);
            broker.izmeni(izBaze);
        }
    }

}

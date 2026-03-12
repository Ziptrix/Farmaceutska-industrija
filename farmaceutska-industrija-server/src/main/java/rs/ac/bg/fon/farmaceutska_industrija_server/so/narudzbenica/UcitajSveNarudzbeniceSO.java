/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 * Sistemska operacija za učitavanje svih narudžbenica iz sistema.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije učitavanja.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Narudzbenica}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Učitavanje svih narudžbenica iz baze podataka preko {@link #broker}.</li>
 *   <li>Skladištenje rezultata u listu {@link #narudzbenice}.</li>
 * </ul>
 * @author milos
 */
@Getter
public class UcitajSveNarudzbeniceSO extends ApstraktnaSO {

    /**
     * Lista svih narudžbenica učitanih iz baze
     */
    List<Narudzbenica> narudzbenice;

    /**
     * Proverava preduslove pre izvršenja učitavanja narudžbenica.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Narudzbenica}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Narudzbenica} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Narudzbenica)) {
            throw new Exception("Prosledjen objekat mora biti tipa Narudzbenica");
        }
    }

    /**
     * Izvršava operaciju učitavanja svih narudžbenica iz baze.
     * <p>
     * Rezultat učitavanja se skladišti u listu {@link #narudzbenice}.
     *
     * @param objekat objekat tipa {@link Narudzbenica} (koristi se za tip podataka).
     * @param kljuc   ključ koji se koristi za eventualno filtriranje (nije korišćen u ovoj SO).
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom učitavanja narudžbenica.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve((Narudzbenica) objekat);

        narudzbenice = rezultat.stream()
                .map(odk -> (Narudzbenica) odk)
                .toList();
    }

}

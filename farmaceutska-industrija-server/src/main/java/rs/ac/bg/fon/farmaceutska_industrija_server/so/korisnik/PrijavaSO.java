/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik;

import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 * Sistemska operacija za prijavu korisnika u sistem.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje prijave korisnika.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Korisnik}.
 * <p>
 * Rezultat operacije se čuva u {@link #korisnik}.
 * 
 * @author milos
 */
@Getter
public class PrijavaSO extends ApstraktnaSO {

    /**
     * Prijavljeni korisnik nakon uspešne operacije.
     */
    private Korisnik korisnik;

    /**
     * Proverava preduslove pre izvršenja prijave korisnika.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Korisnik}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Korisnik} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Korisnik)) {
            throw new Exception("Prosledjen objekat mora biti tipa Korisnik!");
        }
    }

    /**
     * Izvršava prijavu korisnika.
     * <p>
     * Pretvara prosleđeni objekat u {@link Korisnik} i koristi {@link #broker}
     * za vraćanje odgovarajućeg korisnika iz baze podataka. Rezultat se čuva
     * u {@link #korisnik}.
     *
     * @param objekat objekat tipa {@link Korisnik} koji se prijavljuje.
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako dođe do greške pri dohvatanju korisnika.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        korisnik = broker.vratiKorisnika((Korisnik) objekat);
    }

}

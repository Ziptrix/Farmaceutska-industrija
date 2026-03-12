/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 * Sistemska operacija koja dodaje novog dobavljača u bazu podataka.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije dodavanja.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Dobavljac}.
 * 
 * @author milos
 */
public class DodajDobavljacaSO extends ApstraktnaSO {

    /**
     * Proverava preduslove pre dodavanja dobavljača.
     * <p>
     * Metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Dobavljac}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Dobavljac} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti tipa Dobavljac!");
        }
    }

    /**
     * Izvršava operaciju dodavanja dobavljača u bazu podataka.
     * <p>
     * Pretvara prosleđeni objekat u {@link OpstaDomenskaKlasa} i koristi
     * {@link #broker} za upis u bazu.
     *
     * @param objekat objekat tipa {@link Dobavljac} koji se dodaje.
     * @param kljuc   parametar koji se ne koristi u ovoj operaciji.
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom dodavanja u bazu.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.dodaj((OpstaDomenskaKlasa) objekat);
    }

}

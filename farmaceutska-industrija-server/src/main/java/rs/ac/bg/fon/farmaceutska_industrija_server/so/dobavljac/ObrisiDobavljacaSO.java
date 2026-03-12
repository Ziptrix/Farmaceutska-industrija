/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import java.sql.SQLIntegrityConstraintViolationException;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 * Sistemska operacija koja briše objekat dobavljača iz baze podataka.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije brisanja.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Dobavljac}.
 * <p>
 * Ova operacija može baciti izuzetak ako dobavljač postoji u narudžbenici
 * ili ako dođe do druge greške prilikom brisanja.
 * 
 * @author milos
 */
public class ObrisiDobavljacaSO extends ApstraktnaSO {

    /**
     * Proverava preduslove pre brisanja dobavljača.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Dobavljac}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Dobavljac} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti instance Dobavljac!");
        }
    }

    /**
     * Izvršava operaciju brisanja dobavljača iz baze podataka.
     * <p>
     * Pretvara prosleđeni objekat u {@link OpstaDomenskaKlasa} i koristi
     * {@link #broker} za brisanje iz baze.
     * <p>
     * Ako dobavljač postoji u nekoj narudžbenici, baca izuzetak
     * sa porukom da se ne može obrisati.
     *
     * @param objekat objekat tipa {@link Dobavljac} koji se briše.
     * @param kljuc   parametar koji se ne koristi u ovoj operaciji.
     * 
     * @throws java.lang.Exception ako dobavljač postoji u narudžbenici ili dođe do greške prilikom brisanja.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.obrisi((OpstaDomenskaKlasa) objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Dobavljac se ne može obrisati jer postoji u narudžbenici.");
        } catch (Exception e) {
        }
    }

}

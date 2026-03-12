/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so;

import java.io.IOException;
import java.sql.SQLException;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerOpstaDomenskaKlasa;

/**
 * Apstraktna klasa koja predstavlja osnovu za sve sistemske operacije (SO).
 * <p>
 * Ova klasa definiše template metod {@link #izvrsi(Object, String)}, koji obezbeđuje
 * izvršavanje operacije sa transakcijom u bazi podataka.
 * Sadrži mehanizme za pokretanje, potvrđivanje i poništavanje transakcija, kao i podršku za test mod.
 * <p>
 * Sve konkretne sistemske operacije treba da naslede ovu klasu i implementiraju
 * metode {@link #preduslovi(Object)} i {@link #izvrsiOperaciju(Object, String)}.
 * 
 * @author milos
 */
public abstract class ApstraktnaSO {

    /**
     * Broker koji omogućava pristup opštim domenškim klasama u bazi.
     */
    protected static DBBrokerOpstaDomenskaKlasa broker = new DBBrokerOpstaDomenskaKlasa();

    /**
     * Flag koji označava da li je aktivan test mod.
     */
    private boolean testMode = false;

    /**
     * Podesava test mod.
     * Ako je testMode true, transakcije se neće potvrđivati niti zatvarati.
     *
     * @param testMode boolean vrednost koja aktivira test mode.
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    /**
     * Izvršava operaciju nad prosleđenim objektom sa zadatim ključem.
     * <p>
     * Prvo proverava preduslove, zatim započinje transakciju, izvršava operaciju,
     * i na kraju potvrđuje ili poništava transakciju u zavisnosti od uspeha.
     *
     * @param objekat objekat nad kojim se operacija izvršava.
     * @param kljuc   opcioni ključ koji može biti potreban operaciji.
     * 
     * @throws java.lang.Exception ako preduslovi nisu zadovoljeni ili dođe do greške u bazi.
     */
    public void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakcijuZaTest();
            System.out.println("Operacija je uspesno izvrsena!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Operacija nije uspesno izvrsena!");
            ponistiTransakciju();
            throw e;
        } finally {
            zatvoriTransakcijuZaTest();
        }
    }

    /**
     * Proverava preduslove za izvršenje konkretne sistemske operacije.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako preduslovi nisu zadovoljeni.
     */
    protected abstract void preduslovi(Object objekat) throws Exception;

    /**
     * Izvršava samu operaciju nad prosleđenim objektom.
     *
     * @param objekat objekat nad kojim se operacija izvršava.
     * @param kljuc   dodatni parametar koji može biti potreban operaciji.
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom izvršenja.
     */
    protected abstract void izvrsiOperaciju(Object objekat, String kljuc) throws Exception;

    private void zapocniTransakciju() throws SQLException, IOException {
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
    }

    private void potvrdiTransakciju() throws SQLException {
        DBBrokerKonekcija.vratiInstancu().potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws SQLException {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
    }

    private void zatvoriTransakciju() throws SQLException {
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    private void zatvoriTransakcijuZaTest() throws SQLException {
        if (!testMode) {
            DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
        }
    }

    private void potvrdiTransakcijuZaTest() throws SQLException {
        if (!testMode) {
            DBBrokerKonekcija.vratiInstancu().potvrdiTransakciju();
        }
    }
}

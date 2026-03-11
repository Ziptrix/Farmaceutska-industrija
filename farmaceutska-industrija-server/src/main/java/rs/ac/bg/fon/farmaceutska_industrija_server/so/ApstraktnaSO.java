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
 *
 * @author milos
 */
public abstract class ApstraktnaSO {

    protected static DBBrokerOpstaDomenskaKlasa broker = new DBBrokerOpstaDomenskaKlasa();

    private boolean testMode = false;

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public void izvrsi(Object objekat, String kljuc) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat, kljuc);
            potvrdiTransakciju();
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

    protected abstract void preduslovi(Object objekat) throws Exception;

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
}

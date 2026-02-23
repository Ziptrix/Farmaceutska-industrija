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
public abstract class ApstaktnaSO {
    
    protected static DBBrokerOpstaDomenskaKlasa broker = new DBBrokerOpstaDomenskaKlasa();

    public void izvrsi(Object objekat) throws Exception {
        try {
            preduslovi(objekat);
            zapocniTransakciju();
            izvrsiOperaciju(objekat);
            potvrdiTransakciju();
            System.out.println("Operacija je uspesno izvrsena!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Operacija nije uspesno izvrsena!");
            ponistiTransakciju();
        } finally {
            zatvoriTransakciju();
        }
    }

    protected abstract void preduslovi(Object objekat) throws Exception;

    protected abstract void izvrsiOperaciju(Object objekat) throws Exception;

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
}

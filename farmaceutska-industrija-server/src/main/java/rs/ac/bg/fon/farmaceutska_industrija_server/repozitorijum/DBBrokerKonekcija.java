/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author milos
 */
public class DBBrokerKonekcija {

    private Connection konekcija;
    private static DBBrokerKonekcija instanca;

    private DBBrokerKonekcija() {
    }

    public static DBBrokerKonekcija vratiInstancu() {
        if (instanca == null) {
            instanca = new DBBrokerKonekcija();
        }
        return instanca;
    }
    
    public Connection uspostaviKonekcijuZaTest() throws SQLException, IOException {
        if (konekcija == null || konekcija.isClosed()) {
            try {
                Properties podesavanja = new Properties();
                podesavanja.load(new FileInputStream("konfiguracija/broker-konfiguracija-test.properties"));

                String url = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("url"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                String user = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("username"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                String pass = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("password"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                konekcija = DriverManager.getConnection(url, user, pass);
                konekcija.setAutoCommit(false);

                System.out.println("Konekcija sa bazom uspesno uspostavljena!");
            } catch (SQLException e) {
                System.out.println("Konekcija sa bazom nije uspesno uspostavljena!");
                e.printStackTrace();
                throw e;
            }
        }

        return konekcija;
    }

    public Connection uspostaviKonekciju() throws SQLException, IOException {
        if (konekcija == null || konekcija.isClosed()) {
            try {
                Properties podesavanja = new Properties();
                podesavanja.load(new FileInputStream("konfiguracija/broker-konfiguracija.properties"));

                String url = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("url"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                String user = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("username"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                String pass = podesavanja.stringPropertyNames()
                        .stream()
                        .filter(k -> k.equals("password"))
                        .map(k -> podesavanja.getProperty(k))
                        .findFirst()
                        .orElse(null);

                konekcija = DriverManager.getConnection(url, user, pass);
                konekcija.setAutoCommit(false);

                System.out.println("Konekcija sa bazom uspesno uspostavljena!");
            } catch (SQLException e) {
                System.out.println("Konekcija sa bazom nije uspesno uspostavljena!");
                e.printStackTrace();
                throw e;
            }
        }

        return konekcija;
    }

    public void zatvoriKonekciju() throws SQLException {
        try {
            if (konekcija != null && !konekcija.isClosed()) {
                konekcija.close();
                System.out.println("Konekcija sa bazom uspesno zatvorena!");
            }
        } catch (SQLException e) {
            System.out.println("Konekcija sa bazom nije uspesno zatvorena!");
            e.printStackTrace();
            throw e;
        }
    }

    public void potvrdiTransakciju() throws SQLException {
        try {
            konekcija.commit();
            System.out.println("Transakcija je uspesno potvrdjena!");
        } catch (SQLException e) {
            System.out.println("Transakcija nije uspesno potvrdjena!");
            e.printStackTrace();
            throw e;
        }
    }

    public void ponistiTransakciju() throws SQLException {
        try {
            konekcija.rollback();
            System.out.println("Transakcija je uspesno ponistena!");
        } catch (SQLException e) {
            System.out.println("Transakcija nije uspesno ponistena!");
            e.printStackTrace();
            throw e;
        }
    }
}

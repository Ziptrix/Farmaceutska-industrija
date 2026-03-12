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
 * Predstavlja klasu koja sadrži metode za osnovne akcije rada sa bazom podataka.
 * Te metode su uspostavljanje konekcije sa bazom, zatvaranje konekcije sa bazom,
 * potvrda transakcije i ponštavanje transakcije.
 *
 * @author milos
 */
public class DBBrokerKonekcija {

    /**
     * Konekcija za povezivanje sa bazom kao Connection.
     */
    private Connection konekcija;

    /**
     * Instanca klase koja služi za omogućavanje Singleton paterna kao DBBrokerKonekcija.
     */
    private static DBBrokerKonekcija instanca;

    /**
     * Privatni bezparametarski konstruktor koji služi za omogućavanje Singleton paterna.
     */
    private DBBrokerKonekcija() {
    }

    /**
     * Vraća aktivnu instancu klase kao DBBrokerKonekcija.
     *
     * @return
     */
    public static DBBrokerKonekcija vratiInstancu() {
        if (instanca == null) {
            instanca = new DBBrokerKonekcija();
        }
        return instanca;
    }

    /**
     * Uspostavlja konekciju sa test bazom podataka koristeći konfiguracioni fajl
     * <code>konfiguracija/broker-konfiguracija-test.properties</code>.
     * <p>
     * Ako konekcija već postoji i nije zatvorena, metoda vraća postojeću konekciju.
     * Inače, učitava se konfiguracija i kreira se nova konekcija.
     * </p>
     *
     * @return {@link java.sql.Connection} objekat koji predstavlja konekciju ka test bazi podataka.
     * @throws SQLException ako dođe do greške prilikom uspostavljanja konekcije sa bazom.
     * @throws IOException ako dođe do greške prilikom učitavanja konfiguracionog fajla.
     */
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

    /**
     * Uspostavlja konekciju sa produkcionom bazom podataka koristeći konfiguracioni fajl
     * <code>konfiguracija/broker-konfiguracija.properties</code>.
     * <p>
     * Ako konekcija već postoji i nije zatvorena, metoda vraća postojeću konekciju.
     * Inače, učitava se konfiguracija i kreira se nova konekcija.
     * </p>
     *
     * @return {@link java.sql.Connection} objekat koji predstavlja konekciju ka produkcionoj bazi podataka.
     * @throws SQLException ako dođe do greške prilikom uspostavljanja konekcije sa bazom.
     * @throws IOException ako dođe do greške prilikom učitavanja konfiguracionog fajla.
     */
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

    /**
     * Zatvara aktivnu konekciju sa bazom podataka.
     * <p>
     * Ako konekcija postoji i nije već zatvorena,
     * metoda poziva {@link java.sql.Connection#close()} kako bi zatvorila konekciju.
     * U slučaju greške prilikom zatvaranja konekcije, izuzetak se prosleđuje dalje.
     * </p>
     *
     * @throws SQLException ako dođe do greške prilikom zatvaranja konekcije.
     */
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
    
    /**
     * Potvrđuje trenutnu transakciju nad aktivnom konekcijom sa bazom podataka.
     * <p>
     * Metoda poziva {@link java.sql.Connection#commit()} nad postojećom konekcijom
     * kako bi trajno sačuvala sve izmene napravljene u okviru trenutne transakcije.
     * Ukoliko dođe do greške tokom potvrđivanja transakcije, izuzetak se prosleđuje dalje.
     * </p>
     *
     * @throws SQLException ako dođe do greške prilikom potvrđivanja transakcije.
     */
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

    /**
     * Poništava trenutnu transakciju nad aktivnom konekcijom sa bazom podataka.
     * <p>
     * Metoda poziva {@link java.sql.Connection#rollback()} kako bi vratila bazu
     * podataka u stanje pre započete transakcije, čime se poništavaju sve izmene
     * napravljene u okviru te transakcije.
     * Ukoliko dođe do greške prilikom poništavanja transakcije, izuzetak se prosleđuje dalje.
     * </p>
     *
     * @throws SQLException ako dođe do greške prilikom poništavanja transakcije.
     */
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

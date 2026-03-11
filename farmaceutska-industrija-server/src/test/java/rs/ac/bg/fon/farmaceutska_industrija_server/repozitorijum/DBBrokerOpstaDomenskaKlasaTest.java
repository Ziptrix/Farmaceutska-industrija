/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
public class DBBrokerOpstaDomenskaKlasaTest {

    private DBBrokerOpstaDomenskaKlasa broker;
    private Dobavljac testDobavljac;

    @BeforeEach
    public void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        testDobavljac = new Dobavljac();
        testDobavljac.setIme("TestIme");
        testDobavljac.setPrezime("TestPrezime");
        testDobavljac.setGrad(new Grad(11000L, "Beograd"));

        broker.dodaj(testDobavljac);
        System.out.println("TestDobavljac ID posle dodaj: " + testDobavljac.getId());
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testVratiKorisnika_Uspesno() throws Exception {
        Korisnik k = new Korisnik();
        k.setKorisnickoIme("admin");
        k.setSifra("admin");

        Korisnik rezultat = broker.vratiKorisnika(k);

        assertNotNull(rezultat, "Korisnik ne sme biti null");
        assertEquals("admin", rezultat.getKorisnickoIme());
        assertNotNull(rezultat.getId(), "ID korisnika mora biti postavljen iz baze");
        assertNotNull(rezultat.getIme(), "Ime korisnika mora biti ucitano");
        assertNotNull(rezultat.getPrezime(), "Prezime korisnika mora biti ucitano");
    }

    @Test
    void testVratiKorisnika_NepostojeciKorisnik() throws Exception {
        Korisnik k = new Korisnik();
        k.setKorisnickoIme("mare");
        k.setSifra("mare");

        Exception exception = assertThrows(Exception.class, () -> {
            broker.vratiKorisnika(k);
        });

        assertTrue(exception.getMessage().contains("nije pronadjen"));
    }

    @Test
    void testUcitajSve_Dobavljaci() throws Exception {
        OpstaDomenskaKlasa objekat = new Dobavljac();

        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve(objekat);

        assertNotNull(rezultat, "Lista ne sme biti null");
        assertFalse(rezultat.isEmpty(), "Lista dobavljaca ne sme biti prazna");

        for (OpstaDomenskaKlasa o : rezultat) {
            assertTrue(o instanceof Dobavljac, "Element mora biti tipa Dobavljac");

            Dobavljac d = (Dobavljac) o;
            assertNotNull(d.getId(), "ID dobavljaca mora biti postavljen");
            assertNotNull(d.getIme(), "Ime dobavljaca ne sme biti null");
            assertNotNull(d.getPrezime(), "Prezime dobavljaca ne sme biti null");

            Grad grad = d.getGrad();
            assertNotNull(grad, "Grad dobavljaca ne sme biti null");
            assertNotNull(grad.getPostanskiBroj(), "ID grada ne sme biti null");
            assertNotNull(grad.getNaziv(), "Naziv grada ne sme biti null");
        }

        Dobavljac prvi = (Dobavljac) rezultat.get(0);
        assertNotNull(prvi.getGrad(), "Grad prvog dobavljaca mora biti postavljen");
    }

    @Test
    void testUcitajSve_PraznaTabela() throws Exception {
        OpstaDomenskaKlasa objekat = new Dobavljac();

        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve(objekat);

        assertNotNull(rezultat);
    }

    @Test
    void testDodajDobavljaca() throws Exception {
        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        broker.dodaj(d);

        assertNotNull(d.getId(), "ID dobavljaca mora biti postavljen nakon dodavanja");

        List<OpstaDomenskaKlasa> lista = broker.ucitajSve(new Dobavljac());
        boolean pronadjen = lista.stream()
                .map(o -> (Dobavljac) o)
                .anyMatch(x -> x.getId().equals(d.getId()));

        assertTrue(pronadjen, "Dodati dobavljac mora postojati u bazi");
    }

    @Test
    void testObrisiDobavljaca() throws Exception {
        testDobavljac.setIme("BrisanjeIme");
        testDobavljac.setPrezime("BrisanjePrezime");
        testDobavljac.setGrad(new Grad(11000L, "Beograd"));

        List<OpstaDomenskaKlasa> preBrisanja = broker.ucitajSve(new Dobavljac());
        boolean postojiPre = preBrisanja.stream()
                .map(o -> (Dobavljac) o)
                .anyMatch(d -> d.getId().equals(testDobavljac.getId()));
        assertTrue(postojiPre, "Dobavljac mora postojati pre brisanja");

        broker.obrisi(testDobavljac);

        List<OpstaDomenskaKlasa> posleBrisanja = broker.ucitajSve(new Dobavljac());
        boolean postojiPosle = posleBrisanja.stream()
                .map(o -> (Dobavljac) o)
                .anyMatch(d -> d.getId().equals(testDobavljac.getId()));
        assertFalse(postojiPosle, "Dobavljac ne sme postojati nakon brisanja");
    }

    @Test
    void testIzmeniDobavljaca() throws Exception {
        Grad noviGrad = new Grad(12000L, "Pozarevac");
        testDobavljac.setGrad(noviGrad);

        broker.izmeni(testDobavljac);

        List<OpstaDomenskaKlasa> lista = broker.ucitajSve(new Dobavljac());
        Dobavljac pronadjen = lista.stream()
                .map(o -> (Dobavljac) o)
                .filter(d -> d.getId().equals(testDobavljac.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(pronadjen, "Dobavljac mora postojati u bazi");
        assertEquals(noviGrad.getPostanskiBroj(), pronadjen.getGrad().getPostanskiBroj(), "Grad mora biti izmenjen");
        assertEquals(noviGrad.getNaziv(), pronadjen.getGrad().getNaziv(), "Naziv grada mora biti izmenjen");
    }

    @Test
    void testPretraziDobavljacaPoImenu() throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.pretrazi(new Dobavljac(), "Test");

        Dobavljac pronadjen = rezultat.stream()
                .map(o -> (Dobavljac) o)
                .filter(d -> d.getId().equals(testDobavljac.getId()))
                .findFirst()
                .orElse(null);

        assertNotNull(pronadjen, "Dobavljac mora biti pronađen u rezultatu pretrage");

        assertEquals("TestIme", pronadjen.getIme());
        assertEquals("TestPrezime", pronadjen.getPrezime());
    }

}

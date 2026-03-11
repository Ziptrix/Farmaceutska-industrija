/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerOpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class UcitajSveNarudzbeniceSOTest {

    private UcitajSveNarudzbeniceSO ucitajSve;
    private DBBrokerOpstaDomenskaKlasa broker;
    private Korisnik testKorisnik;
    private Dobavljac testDobavljac;
    private Supstanca testSupstanca;
    private Narudzbenica testNarudzbenica;
    private StavkaNarudzbenice testStavka;

    @BeforeEach
    void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();
        ucitajSve = new UcitajSveNarudzbeniceSO();
        ucitajSve.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        testKorisnik = new Korisnik();
        testKorisnik.setId(1L);
        testKorisnik.setKorisnickoIme("admin");
        testKorisnik.setSifra("admin");

        testDobavljac = new Dobavljac();
        testDobavljac.setId(1L);
        testDobavljac.setIme("Pera");
        testDobavljac.setPrezime("Peric");
        testDobavljac.setGrad(new Grad(21000L, "Novi Sad"));

        testSupstanca = new Supstanca();
        testSupstanca.setSifra(100L);
        testSupstanca.setNaziv("Kalcijum");
        testSupstanca.setKolicinaZaliha(120L);

        testNarudzbenica = new Narudzbenica();
        testNarudzbenica.setSifra(1212L);
        testNarudzbenica.setDatum(LocalDate.now());
        testNarudzbenica.setListaStavki(new ArrayList<>());
        testNarudzbenica.setUkupanIznos(1000L);
        testNarudzbenica.setKorisnik(testKorisnik);
        testNarudzbenica.setDobavljac(testDobavljac);

        testStavka = new StavkaNarudzbenice();
        testStavka.setRedniBroj(1L);
        testStavka.setNarudzbenica(testNarudzbenica);
        testStavka.setSupstanca(testSupstanca);
        testStavka.setKolicinaSupstance(10L);
        testStavka.setIznosStavke(100L);

        testNarudzbenica.setListaStavki(List.of(testStavka));

        broker.dodaj(testNarudzbenica);

        for (StavkaNarudzbenice sn : testNarudzbenica.getListaStavki()) {
            sn.setNarudzbenica(testNarudzbenica);
            broker.dodaj(sn);
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testUcitajSveNarudzbenice() throws Exception {
        ucitajSve.izvrsi(new Narudzbenica(), null);

        List<Narudzbenica> narudzbenice = ucitajSve.narudzbenice;

        assertNotNull(narudzbenice, "Lista narudzbenica ne sme biti null");
        assertFalse(narudzbenice.isEmpty(), "Lista narudzbenica ne sme biti prazna");

        Narudzbenica pronadjena = narudzbenice.stream()
                .filter(n -> n.getSifra().equals(testNarudzbenica.getSifra()))
                .findFirst()
                .orElse(null);

        assertNotNull(pronadjena, "Testna narudzbenica mora biti učitana");
        assertEquals(testNarudzbenica.getDobavljac().getIme(),
                pronadjena.getDobavljac().getIme());
        assertEquals(testNarudzbenica.getKorisnik().getKorisnickoIme(),
                pronadjena.getKorisnik().getKorisnickoIme());

        assertNotNull(pronadjena.getListaStavki());
        assertEquals(1, pronadjena.getListaStavki().size());
        assertEquals(testSupstanca.getNaziv(),
                pronadjena.getListaStavki().get(0).getSupstanca().getNaziv());
    }
}

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
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class ObrisiNarudzbenicuSOTest {

    private DBBrokerOpstaDomenskaKlasa broker;
    private ObrisiNarudzbenicuSO obrisiNarudzbenicu;
    private Narudzbenica narudzbenica;
    private Supstanca testSupstanca;
    private Dobavljac testDobavljac;
    private Korisnik testKorisnik;

    @BeforeEach
    void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();

        obrisiNarudzbenicu = new ObrisiNarudzbenicuSO();
        obrisiNarudzbenicu.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        testDobavljac = new Dobavljac();
        testDobavljac.setId(1L);
        testSupstanca = new Supstanca();
        testSupstanca.setSifra(100L);
        testKorisnik = new Korisnik();
        testKorisnik.setId(1L);
        testDobavljac.setGrad(new Grad(21000L, "Novi Sad"));

        narudzbenica = new Narudzbenica();
        narudzbenica.setSifra(11111L);
        narudzbenica.setDobavljac(testDobavljac);
        narudzbenica.setKorisnik(testKorisnik);
        narudzbenica.setDatum(LocalDate.now());
        narudzbenica.setListaStavki(new ArrayList<>());
        narudzbenica.setUkupanIznos(1000L);
        

        StavkaNarudzbenice stavka = new StavkaNarudzbenice();
        stavka.setRedniBroj(1L);
        stavka.setSupstanca(testSupstanca);
        stavka.setKolicinaSupstance(10L);
        stavka.setIznosStavke(100L);

        narudzbenica.getListaStavki().add(stavka);

        broker.dodaj(narudzbenica);

        for (StavkaNarudzbenice sn : narudzbenica.getListaStavki()) {
            sn.setNarudzbenica(narudzbenica);
            broker.dodaj(sn);
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testObrisiNarudzbenicu() throws Exception {
        List<OpstaDomenskaKlasa> pre = broker.ucitajSve(new Narudzbenica());

        Narudzbenica pronadjenaPre = pre.stream()
                .map(o -> (Narudzbenica) o)
                .filter(n -> n.getSifra().equals(narudzbenica.getSifra()))
                .findFirst()
                .orElse(null);
        assertNotNull(pronadjenaPre, "Narudzbenica mora postojati pre brisanja");

        obrisiNarudzbenicu.izvrsi(narudzbenica, null);

        List<OpstaDomenskaKlasa> posle = broker.ucitajSve(new Narudzbenica());
        assertFalse(posle.stream().anyMatch(n -> ((Narudzbenica) n).getSifra().equals("TEST123")));
    }
}

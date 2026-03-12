/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.dobavljac;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.ObrisiDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica.DodajNarudzbenicuSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;

/**
 *
 * @author milos
 */
public class ObrisiDobavljacaSOTest {

    private ObrisiDobavljacaSO obrisiDobavljaca;

    @BeforeEach
    void setUp() throws Exception {
        obrisiDobavljaca = new ObrisiDobavljacaSO();
        obrisiDobavljaca.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testObrisiDobavljaca() throws Exception {

        DodajDobavljacaSO dodajSO = new DodajDobavljacaSO();
        dodajSO.setTestMode(true);

        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajSO.izvrsi(d, null);

        obrisiDobavljaca.izvrsi(d, null);

        assertNotNull(d);
    }

    @Test
    void testObrisiDobavljacaNull() {
        assertThrows(Exception.class, () -> {
            obrisiDobavljaca.izvrsi(null, null);
        });
    }

    @Test
    void testObrisiDobavljacaPogresanTip() {
        assertThrows(Exception.class, () -> {
            obrisiDobavljaca.izvrsi(new Grad(), null);
        });
    }

    @Test
    void testObrisiDobavljacaKojiJeUNarudzbenici() throws Exception {

        DodajDobavljacaSO dodajDobavljacaSO = new DodajDobavljacaSO();
        dodajDobavljacaSO.setTestMode(true);

        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajDobavljacaSO.izvrsi(d, null);

        // Kreiranje narudzbenice koja koristi tog dobavljaca
        Narudzbenica n = new Narudzbenica();
        n.setSifra(456L);
        n.setDatum(LocalDate.now());
        n.setUkupanIznos(1000L);
        n.setKorisnik(new Korisnik());
        n.setListaStavki(new ArrayList<>());
        n.setDobavljac(d);

        DodajNarudzbenicuSO dodajNarudzbenicuSO = new DodajNarudzbenicuSO();
        dodajNarudzbenicuSO.setTestMode(true);
        dodajNarudzbenicuSO.izvrsi(n, null);

        assertThrows(Exception.class, () -> {
            obrisiDobavljaca.izvrsi(d, null);
        });
    }
}

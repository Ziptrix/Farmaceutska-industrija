/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.UcitajSveDobavljaceSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class UcitajSveDobavljaceSOTest {

    private UcitajSveDobavljaceSO ucitajSve;

    @BeforeEach
    void setUp() throws Exception {
        ucitajSve = new UcitajSveDobavljaceSO();
        ucitajSve.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testUcitajSveDobavljace() throws Exception {
        DodajDobavljacaSO dodajSO = new DodajDobavljacaSO();
        dodajSO.setTestMode(true);

        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajSO.izvrsi(d, null);

        ucitajSve.izvrsi(new Dobavljac(), null);

        List<Dobavljac> lista = ucitajSve.getDobavljaci();

        assertTrue(lista.stream()
                .anyMatch(d1 -> d1.getIme().equals("TestIme")));
        assertNotNull(lista);
        assertFalse(lista.isEmpty());
    }

    @Test
    void testUcitajSveDobavljaceNull() {
        Exception e = assertThrows(Exception.class, () -> {
            ucitajSve.izvrsi(null, null);
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }

    @Test
    void testUcitajSveDobavljacePogresanTip() {
        Exception e = assertThrows(Exception.class, () -> {
            ucitajSve.izvrsi(new Grad(), null);
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }
}

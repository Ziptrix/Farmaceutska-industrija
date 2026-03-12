/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class DodajDobavljacaSOTest {

    private DodajDobavljacaSO dodajDobavljaca;

    @BeforeEach
    void setUp() throws Exception {
        dodajDobavljaca = new DodajDobavljacaSO();
        dodajDobavljaca.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testDodajDobavljaca() throws Exception {

        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajDobavljaca.izvrsi(d, null);

        assertNotNull(d);
        assertEquals("TestIme", d.getIme());
    }

    @Test
    void testDodajDobavljacaNull() {
        assertThrows(Exception.class, () -> {
            dodajDobavljaca.izvrsi(null, null);
        });
    }

    @Test
    void testDodajDobavljacaPogresanTip() {
        assertThrows(Exception.class, () -> {
            dodajDobavljaca.izvrsi(new Grad(), null);
        });
    }
}

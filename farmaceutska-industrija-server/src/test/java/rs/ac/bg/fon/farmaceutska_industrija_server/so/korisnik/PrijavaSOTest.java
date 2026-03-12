/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class PrijavaSOTest {

    private PrijavaSO prijavaSO;

    @BeforeEach
    void setUp() throws Exception {
        prijavaSO = new PrijavaSO();
        prijavaSO.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class, () -> {
            prijavaSO.izvrsi(null, null);
        });
        assertTrue(ex.getMessage().contains("mora biti tipa Korisnik"));
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class, () -> {
            prijavaSO.izvrsi("neki string", null);
        });
        assertTrue(ex.getMessage().contains("mora biti tipa Korisnik"));
    }

    @Test
    void testIzvrsiOperaciju() throws Exception {
        Korisnik k = new Korisnik();
        k.setKorisnickoIme("admin");
        k.setSifra("admin");

        assertDoesNotThrow(() -> prijavaSO.izvrsi(k, null));
        assertEquals("admin", k.getKorisnickoIme());
    }

    @Test
    void testIzvrsiOperacijuKorisnikNePostoji() throws Exception {
        Korisnik k = new Korisnik();
        k.setKorisnickoIme("mare");
        k.setSifra("mare");

        Exception ex = assertThrows(Exception.class, () -> prijavaSO.izvrsi(k, null));
        assertTrue(ex.getMessage().contains("nije pronadjen"));
    }
}

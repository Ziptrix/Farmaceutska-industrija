/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.DodajLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class DodajLekSOTest {

    private DodajLekSO dodajLek;

    @BeforeEach
    void setUp() throws Exception {
        dodajLek = new DodajLekSO();
        dodajLek.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testDodajLek() throws Exception {
        Supstanca supstanca = new Supstanca();
        supstanca.setSifra(100L);
        supstanca.setNaziv("Kalcijum");
        supstanca.setKolicinaZaliha(20L);
        supstanca.setCena(10L);

        Lek lek = new Lek();
        lek.setSerijskiBroj(111L);
        lek.setNaziv("TEST_LEK");
        lek.setDoziranje("TEST_DOZIRANJE");
        lek.setSastav(List.of(supstanca));

        dodajLek.izvrsi(lek, null);
        assertNotNull(lek);
    }

    @Test
    void testDodajLekNull() {

        Exception e = assertThrows(Exception.class, () -> {
            dodajLek.izvrsi(null, null);
        });

        assertTrue(e.getMessage().contains("Lek"));
    }

    @Test
    void testDodajLekPogresanTip() {

        Exception e = assertThrows(Exception.class, () -> {
            dodajLek.izvrsi(new Supstanca(), null);
        });

        assertTrue(e.getMessage().contains("Lek"));
    }
}

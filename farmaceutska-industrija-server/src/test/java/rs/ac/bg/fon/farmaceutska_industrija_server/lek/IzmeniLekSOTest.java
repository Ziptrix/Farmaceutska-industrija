/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.lek;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerOpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.DodajLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.IzmeniLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class IzmeniLekSOTest {

    private DBBrokerOpstaDomenskaKlasa broker;
    private DodajLekSO dodajLek;
    private IzmeniLekSO izmeniLek;
    private Lek testLek;

    @BeforeEach
    void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();
        dodajLek = new DodajLekSO();
        dodajLek.setTestMode(true);
        izmeniLek = new IzmeniLekSO();
        izmeniLek.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        Lek lek = new Lek();
        lek.setSerijskiBroj(111L);
        lek.setNaziv("TestLek");
        lek.setDoziranje("TestDoziranje");

        List<Supstanca> sastavLeka = new ArrayList<>();
        sastavLeka.add(new Supstanca(100L, "Kalijum", 20L, 10L));

        lek.setSastav(sastavLeka);

        dodajLek.izvrsi(lek, null);

        testLek = broker.ucitajSve(new Lek()).stream()
                .map(o -> (Lek) o)
                .filter(l -> l.getNaziv().equals("TestLek"))
                .findFirst()
                .orElseThrow(() -> new Exception("Lek nije dodat u bazi u setUp()"));
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testIzmeniDoziranjeLeka() throws Exception {
        testLek.setDoziranje("750mg");
        izmeniLek.izvrsi(testLek, null);

        Lek izBaze = broker.ucitajSve(new Lek()).stream()
                .map(o -> (Lek) o)
                .filter(l -> l.getSerijskiBroj() == testLek.getSerijskiBroj())
                .findFirst()
                .orElse(null);

        assertNotNull(izBaze, "Lek mora postojati u bazi nakon izmene");
        assertEquals("750mg", izBaze.getDoziranje(), "Doziranje leka mora biti izmenjeno");
        assertEquals(testLek.getNaziv(), izBaze.getNaziv(), "Naziv leka se ne menja");
        assertEquals(testLek.getSerijskiBroj(), izBaze.getSerijskiBroj(), "Serijski broj leka se ne menja");
    }

}

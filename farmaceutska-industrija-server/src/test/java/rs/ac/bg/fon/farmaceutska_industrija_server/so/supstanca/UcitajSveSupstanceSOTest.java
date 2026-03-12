/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.supstanca;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class UcitajSveSupstanceSOTest {

    private UcitajSveSupstanceSO ucitajSve;

    @BeforeEach
    void setUp() throws Exception {
        ucitajSve = new UcitajSveSupstanceSO();
        ucitajSve.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testUcitajSveSupstance() throws Exception {
        Supstanca s = new Supstanca();

        ucitajSve.izvrsi(s, null);

        List<Supstanca> supstance = ucitajSve.supstance;

        assertNotNull(supstance, "Lista supstanci ne sme biti null");
        assertFalse(supstance.isEmpty(), "Lista supstanci ne sme biti prazna");

        boolean imaParacetamol = supstance.stream()
                .anyMatch(sub -> "Kalcijum".equals(sub.getNaziv()));
        assertTrue(imaParacetamol, "Lista supstanci mora sadržati Kalcijum");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.grad;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class UcitajSveGradoveSOTest {

    private UcitajSveGradoveSO ucitajSve;

    @BeforeEach
    void setUp() throws Exception {
        ucitajSve = new UcitajSveGradoveSO();
        ucitajSve.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testUcitajSveGradove() throws Exception {
        Grad grad = new Grad();

        ucitajSve.izvrsi(grad, null);

        List<Grad> gradovi = ucitajSve.gradovi;

        assertNotNull(gradovi, "Lista gradova ne sme biti null");
        assertFalse(gradovi.isEmpty(), "Lista gradova ne sme biti prazna");

        boolean imaBeograd = gradovi.stream()
                .anyMatch(g -> "Beograd".equals(g.getNaziv()));
        assertTrue(imaBeograd, "Lista gradova mora sadržati Beograd");
    }
}

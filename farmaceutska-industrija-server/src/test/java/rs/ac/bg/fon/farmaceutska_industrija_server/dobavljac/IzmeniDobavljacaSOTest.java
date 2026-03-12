/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.dobavljac;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.IzmeniDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class IzmeniDobavljacaSOTest {

    private IzmeniDobavljacaSO izmeniDobavljaca;
//    private Connection konekcija;

    @BeforeEach
    void setUp() throws Exception {
        izmeniDobavljaca = new IzmeniDobavljacaSO();
        izmeniDobavljaca.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
//        if (konekcija != null) {
//            konekcija.rollback();
//            konekcija.close();
//        }
    }

    @Test
    void testIzmeniDobavljacaGrad() throws Exception {

        DodajDobavljacaSO dodajSO = new DodajDobavljacaSO();
        dodajSO.setTestMode(true);

        Dobavljac d = new Dobavljac();
        d.setIme("TestIme");
        d.setPrezime("TestPrezime");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajSO.izvrsi(d, null);

        // promena grada
        Grad noviGrad = new Grad(21000L, "Novi Sad");
        d.setGrad(noviGrad);

        izmeniDobavljaca.izvrsi(d, null);

        assertEquals(21000, d.getGrad().getPostanskiBroj());
    }

    @Test
    void testIzmeniDobavljacaNull() {

        Exception e = assertThrows(Exception.class, () -> {
            izmeniDobavljaca.izvrsi(null, null);
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }

    @Test
    void testIzmeniDobavljacaPogresanTip() {

        Exception e = assertThrows(Exception.class, () -> {
            izmeniDobavljaca.izvrsi(new Grad(), null);
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }
}

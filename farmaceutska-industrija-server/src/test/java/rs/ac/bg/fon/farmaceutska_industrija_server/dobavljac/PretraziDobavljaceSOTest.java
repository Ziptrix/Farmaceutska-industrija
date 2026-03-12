/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.dobavljac;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.PretraziDobavljaceSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class PretraziDobavljaceSOTest {

    private PretraziDobavljaceSO pretrazi;

    @BeforeEach
    void setUp() throws Exception {
        pretrazi = new PretraziDobavljaceSO();
        pretrazi.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testPretraziDobavljace() throws Exception {

        DodajDobavljacaSO dodajSO = new DodajDobavljacaSO();
        dodajSO.setTestMode(true);

        Dobavljac d = new Dobavljac();
        d.setIme("Marko");
        d.setPrezime("Markovic");
        d.setGrad(new Grad(11000L, "Beograd"));

        dodajSO.izvrsi(d, null);

        pretrazi.izvrsi(new Dobavljac(), "Marko");

        List<Dobavljac> lista = pretrazi.getDobavljaci();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        assertTrue(lista.stream().anyMatch(x -> x.getIme().equals("Marko")));
    }

    @Test
    void testPretraziDobavljaceNull() {

        Exception e = assertThrows(Exception.class, () -> {
            pretrazi.izvrsi(null, "Marko");
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }

    @Test
    void testPretraziDobavljacePogresanTip() {

        Exception e = assertThrows(Exception.class, () -> {
            pretrazi.izvrsi(new Grad(), "Marko");
        });

        assertTrue(e.getMessage().contains("Dobavljac"));
    }

    @Test
    void testPretraziDobavljaceNemaRezultata() {

        Exception e = assertThrows(Exception.class, () -> {
            pretrazi.izvrsi(new Dobavljac(), "NEPOSTOJECEIME123");
        });

        assertTrue(e.getMessage().contains("ne moze da pronadje"));
    }
}

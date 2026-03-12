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
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.PretraziLekoveSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
public class PretraziLekoveSOTest {

    private DBBrokerOpstaDomenskaKlasa broker;
    private PretraziLekoveSO pretrazi;
    private Lek testLek;

    @BeforeEach
    void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();
        pretrazi = new PretraziLekoveSO();
        pretrazi.setTestMode(true);

        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        // Dodavanje leka
        testLek = new Lek();
        testLek.setSerijskiBroj(222L);
        testLek.setNaziv("Paracetamol");
        testLek.setDoziranje("500mg");
        List<Supstanca> sastav = new ArrayList<>();
        sastav.add(new Supstanca(100L, "Kalcijum", 20L, 10L));
        testLek.setSastav(sastav);

        broker.dodaj(testLek);

        // Dodavanje veze u SupstancaLek
        for (Supstanca s : sastav) {
            SupstancaLek sl = new SupstancaLek();
            sl.setLek(testLek);
            sl.setSupstanca(s);
            sl.setUpotrebljenaKolicina(s.getKolicinaZaliha());
            broker.dodaj(sl);
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testPretraziLekPoNazivu() throws Exception {
        // Pretrazi lek po nazivu
        pretrazi.izvrsi(new Lek(), "Paracetamol");

        assertNotNull(pretrazi.getLekovi(), "Rezultat pretrage ne sme biti null");
        assertFalse(pretrazi.getLekovi().isEmpty(), "Treba da postoji bar jedan lek u rezultatu");

        Lek lek = pretrazi.getLekovi().get(0);
        assertEquals("Paracetamol", lek.getNaziv(), "Naziv leka mora se poklapati");

        // Proveri da li je sastav popunjen
        assertNotNull(lek.getSastav(), "Sastav leka ne sme biti null");
        assertFalse(lek.getSastav().isEmpty(), "Sastav leka ne sme biti prazan");
        assertEquals("Kalcijum", lek.getSastav().get(0).getNaziv(), "Naziv supstance mora se poklapati");
    }
}

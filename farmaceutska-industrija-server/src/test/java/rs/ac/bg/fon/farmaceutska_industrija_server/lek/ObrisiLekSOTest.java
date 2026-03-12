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
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.ObrisiLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
public class ObrisiLekSOTest {

    private DBBrokerOpstaDomenskaKlasa broker;
    private ObrisiLekSO obrisiLek;
    private Lek testLek;

    @BeforeEach
    void setUp() throws Exception {
        broker = new DBBrokerOpstaDomenskaKlasa();
        obrisiLek = new ObrisiLekSO();
        obrisiLek.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();

        // Napravi lek za test
        testLek = new Lek();
        testLek.setSerijskiBroj(100100L);
        testLek.setNaziv("TestLek");
        testLek.setDoziranje("TestDoziranje");
        List<Supstanca> sastavLeka = new ArrayList<>();
        sastavLeka.add(new Supstanca(100L, "Kalijum", 20L, 10L));

        broker.dodaj(testLek);

        // Poveži lek sa supstancama (postojeće u bazi)
        List<OpstaDomenskaKlasa> supstance = broker.ucitajSve(new Supstanca());
        for (OpstaDomenskaKlasa op : supstance) {
            Supstanca sup = (Supstanca) op;
            SupstancaLek sl = new SupstancaLek();
            sl.setLek(testLek);
            sl.setSupstanca(sup);
            sl.setUpotrebljenaKolicina(20L);
            broker.dodaj(sl);
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testObrisiLek() throws Exception {
        // Pozovi SO
        obrisiLek.izvrsi(testLek, null);

        // Proveri da lek više ne postoji
        List<Lek> lekovi = broker.ucitajSve(new Lek()).stream()
                .map(o -> (Lek) o)
                .filter(l -> l.getSerijskiBroj()== testLek.getSerijskiBroj())
                .toList();

        assertTrue(lekovi.isEmpty(), "Lek mora biti obrisan iz baze");

        // Proveri da su svi povezani SupstancaLek redovi obrisani
        List<SupstancaLek> slovi = broker.ucitajSve(new SupstancaLek()).stream()
                .map(o -> (SupstancaLek) o)
                .filter(sl -> sl.getLek().getSerijskiBroj()== testLek.getSerijskiBroj())
                .toList();

        assertTrue(slovi.isEmpty(), "Svi zapisi u SupstancaLek moraju biti obrisani");
    }

}

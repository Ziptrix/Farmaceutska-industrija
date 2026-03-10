/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class LekTest {

    private Lek lek;

    @BeforeEach
    public void setUp() {
        lek = new Lek();
    }

    @AfterEach
    public void tearDown() {
        lek = null;
    }

    @Test
    void testLek() {
        assertNotNull(lek);
    }

    @Test
    void testSetSifra() throws Exception {
        lek.setSerijskiBroj(123L);

        assertEquals(123L, lek.getSerijskiBroj());
    }

    @Test
    void testSetSifraNull() throws Exception {
        assertThrows(Exception.class, () -> lek.setSerijskiBroj(null));
    }

    @Test
    void testSetSifraNula() throws Exception {
        assertThrows(Exception.class, () -> lek.setSerijskiBroj(0L));
    }

    @Test
    void testSetSifraNegativna() throws Exception {
        assertThrows(Exception.class, () -> lek.setSerijskiBroj(-1L));
    }

    @Test
    void testSetNaziv() throws Exception {
        lek.setNaziv("Febricet");

        assertEquals("Febricet", lek.getNaziv());
    }

    @Test
    void testSetNazivNull() throws Exception {
        assertThrows(Exception.class, () -> lek.setNaziv(null));
    }

    @Test
    void testSetNazivPrazan() throws Exception {
        assertThrows(Exception.class, () -> lek.setNaziv(""));
    }

    @Test
    void testSetDoziranje() throws Exception {
        lek.setDoziranje("Tri puta dnevno po jedna tableta");

        assertEquals("Tri puta dnevno po jedna tableta", lek.getDoziranje());
    }

    @Test
    void testSetDoziranjeNull() throws Exception {
        assertThrows(Exception.class, () -> lek.setDoziranje(null));
    }

    @Test
    void testSetDoziranjePrazno() throws Exception {
        assertThrows(Exception.class, () -> lek.setDoziranje(""));
    }

    @Test
    void testSetSastav() throws Exception {
        Supstanca supstanca = new Supstanca();
        List<Supstanca> sastavLeka = new ArrayList<>();
        sastavLeka.add(supstanca);

        lek.setSastav(sastavLeka);
        assertEquals(sastavLeka, lek.getSastav());
    }

    @Test
    void testToString() throws Exception {
        lek.setNaziv("Febricet");
        lek.setDoziranje("Pri visokim temperaturama popiti");

        assertTrue(lek.toString().contains("Febricet"));
        assertTrue(lek.toString().contains("Pri visokim temperaturama popiti"));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("medicine", lek.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPrimarnogKljuca() {
        assertEquals("serial_number", lek.vratiNazivPrimarnogKljuca());
    }

    @Test
    void testVratiNaziveKolonaZaInsertUpit() {
        assertEquals("serial_number, name, dosage", lek.vratiNaziveKolonaZaInsertUpit());
    }

    @Test
    void testVratiVrednostiInsertUpita() throws Exception {
        assertEquals(lek.getSerijskiBroj() + ", '" + lek.getNaziv() + "', '" + lek.getDoziranje() + "'",
                lek.vratiVrednostiInsertUpita());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("l.serial_number, l.name AS naziv_leka, l.dosage, "
                + "s.code, s.name, "
                + "s.quantity, s.price, "
                + "sl.id_medicine, sl.id_substance, sl.quantity_used",
                lek.vratiVrednostiSelectUpita());
    }

    @Test
    void testVratiVrednostiUpdateUpita() throws Exception {
        assertEquals("dosage = '" + lek.getDoziranje() + "'", lek.vratiVrednostiUpdateUpita());
    }

    @Test
    void testVratiJoin() {
        assertEquals(" l JOIN substance_medicine sl ON l.serial_number = sl.id_medicine "
                + " JOIN substance s ON s.code = sl.id_substance",
                lek.vratiJoin());
    }
    
    @Test
    void testVratiUslovZaDelete() {
        assertEquals("WHERE serial_number = ?", lek.vratiUslovZaDelete());
    }
    
    @Test
    void testVratiNazivKoloneZaPretragu() {
        assertEquals("l.name", lek.vratiNazivKoloneZaPretragu());
    }
    
    @Test
    void testVratiNazivKoloneZaGroupBy() {
        assertEquals("serial_number", lek.vratiNazivKoloneZaGroupBy());
    }
}

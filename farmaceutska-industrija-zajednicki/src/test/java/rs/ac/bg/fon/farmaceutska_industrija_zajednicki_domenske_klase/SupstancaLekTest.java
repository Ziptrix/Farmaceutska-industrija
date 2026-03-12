/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
public class SupstancaLekTest {

    private SupstancaLek supstancaLek;

    @BeforeEach
    public void setUp() {
        supstancaLek = new SupstancaLek();
    }

    @AfterEach
    public void tearDown() {
        supstancaLek = null;
    }

    @Test
    void testSupstancaLek() {
        assertNotNull(supstancaLek);
    }

    @Test
    void testSetLek() throws Exception {
        Lek lek = new Lek();
        supstancaLek.setLek(lek);

        assertEquals(lek, supstancaLek.getLek());
    }

    @Test
    void testSetLekNull() {
        assertThrows(Exception.class, () -> supstancaLek.setLek(null));
    }

    @Test
    void testSetSupstanca() throws Exception {
        Supstanca supstanca = new Supstanca();
        supstancaLek.setSupstanca(supstanca);

        assertEquals(supstanca, supstancaLek.getSupstanca());
    }

    @Test
    void testSetSupstancaNull() {
        assertThrows(Exception.class, () -> supstancaLek.setSupstanca(null));
    }

    @Test
    void testSetUpotrebljenaKolicina() throws Exception {
        supstancaLek.setUpotrebljenaKolicina(50L);

        assertEquals(50, supstancaLek.getUpotrebljenaKolicina());
    }

    @Test
    void testSetUpotrebljenaKolicinaNull() throws Exception {
        assertThrows(Exception.class, () -> supstancaLek.setUpotrebljenaKolicina(null));
    }

    @Test
    void testSetUpotrebljenaKolicinaNula() throws Exception {
        assertThrows(Exception.class, () -> supstancaLek.setUpotrebljenaKolicina(0L));
    }

    @Test
    void testSetUpotrebljenaKolicinaNegativan() throws Exception {
        assertThrows(Exception.class, () -> supstancaLek.setUpotrebljenaKolicina(-1L));
    }

    @Test
    void testToString() {
        Lek lek = new Lek();
        Supstanca supstanca = new Supstanca();

        supstancaLek = new SupstancaLek(lek, supstanca, 50L);

        assertTrue(supstancaLek.toString().contains(lek.toString()));
        assertTrue(supstancaLek.toString().contains(supstanca.toString()));
        assertTrue(supstancaLek.toString().contains(50 + ""));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("substance_medicine", supstancaLek.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPrimarnogKljuca() {
        assertEquals("id_substance", supstancaLek.vratiNazivPrimarnogKljuca());
    }

    @Test
    void testVratiNaziveKolonaZaInsertUpit() {
        assertEquals("id_medicine, id_substance, quantity_used", supstancaLek.vratiNaziveKolonaZaInsertUpit());
    }

    @Test
    void testVratiNazivKoloneZaPretragu() {
        assertEquals("sl.id_medicine", supstancaLek.vratiNazivKoloneZaPretragu());
    }

    @Test
    void testVratiVrednostiInsertUpita() throws Exception {
        Lek lek = new Lek();
        Supstanca supstanca = new Supstanca();
        supstancaLek.setLek(lek);
        supstancaLek.setSupstanca(supstanca);

        assertEquals(supstancaLek.getLek().getSerijskiBroj() + ", " + supstancaLek.getSupstanca().getSifra() + ", " + supstancaLek.getUpotrebljenaKolicina(),
                supstancaLek.vratiVrednostiInsertUpita());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("sl.id_medicine, sl.id_substance, sl.quantity_used, s.code, s.name, s.quantity, s.price"
                + ", l.serial_number, l.name, l.dosage",
                supstancaLek.vratiVrednostiSelectUpita());
    }

    @Test
    void testVratiVrednostiUpdateUpita() throws Exception {
        assertEquals("quantity_used = " + supstancaLek.getUpotrebljenaKolicina(), supstancaLek.vratiVrednostiUpdateUpita());
    }

    @Test
    void testVratiJoin() {
        assertEquals(" sl JOIN substance s ON sl.id_substance = s.code"
                + " JOIN medicine l ON l.serial_number = sl.id_medicine",
                supstancaLek.vratiJoin());
    }

    @Test
    void testVratiUslovZaDelete() {
        assertEquals("WHERE id_medicine = ?", supstancaLek.vratiUslovZaDelete());
    }
}

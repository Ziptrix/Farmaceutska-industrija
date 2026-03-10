/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class SupstancaTest {

    private Supstanca supstanca;

    @BeforeEach
    public void setUp() {
        supstanca = new Supstanca();
    }

    @AfterEach
    public void tearDown() {
        supstanca = null;
    }

    @Test
    void testSupstanca() {
        assertNotNull(supstanca);
    }

    @Test
    void testSetSifra() throws Exception {
        supstanca.setSifra(1L);

        assertEquals(1L, supstanca.getSifra());
    }

    @Test
    void testSetSifraNull() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setSifra(null));
    }

    @Test
    void testSetSifraNula() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setSifra(0L));
    }

    @Test
    void testSetSifraNegativna() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setSifra(-1L));
    }

    @Test
    void testSetNaziv() throws Exception {
        supstanca.setNaziv("paracetamol");

        assertEquals("paracetamol", supstanca.getNaziv());
    }

    @Test
    void testSetNazivNull() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setNaziv(null));
    }

    @Test
    void testSetNazivPrazan() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setNaziv(""));
    }

    @Test
    void testSetKolicinaZaliha() throws Exception {
        supstanca.setKolicinaZaliha(100L);

        assertEquals(100L, supstanca.getKolicinaZaliha());
    }

    @Test
    void testSetKolicinaZalihaNull() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setKolicinaZaliha(null));
    }

    @Test
    void testSetKolicinaZalihaNula() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setKolicinaZaliha(0L));
    }

    @Test
    void testSetKolicinaZalihaNegativna() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setKolicinaZaliha(-1L));
    }

    @Test
    void testSetCena() throws Exception {
        supstanca.setCena(50L);

        assertEquals(50L, supstanca.getCena());
    }

    @Test
    void testSetCenaNull() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setCena(null));
    }

    @Test
    void testSetCenaNula() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setCena(0L));
    }

    @Test
    void testSetCenaNegativna() throws Exception {
        assertThrows(Exception.class, () -> supstanca.setCena(-1L));
    }

    @Test
    void testToString() throws Exception {
        supstanca.setNaziv("paracetamol");
        supstanca.setKolicinaZaliha(100L);

        assertTrue(supstanca.toString().contains("paracetamol"));
        assertTrue(supstanca.toString().contains(100L + ""));
    }

    @Test
    void testToStringZaliheCena() throws Exception {
        supstanca.setKolicinaZaliha(100L);
        supstanca.setCena(10L);

        assertTrue(supstanca.toStringZaliheCena().contains(100L + ""));
        assertTrue(supstanca.toStringZaliheCena().contains(10L + ""));
    }

    @ParameterizedTest
    @CsvSource({
        "123, 123, true",
        "111, 222, false"
    })
    void testEquals(Long sifra1, Long sifra2, boolean rezultat) throws Exception {
        supstanca.setSifra(sifra1);

        Supstanca s = new Supstanca();
        s.setSifra(sifra2);

        assertEquals(rezultat, supstanca.equals(s));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("substance s", supstanca.vratiNazivTabele());
    }

    @Test
    void testVratiNazivPrimarnogKljuca() {
        assertEquals("s.code", supstanca.vratiNazivPrimarnogKljuca());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("s.code, s.name, s.quantity, s.price", supstanca.vratiVrednostiSelectUpita());
    }

    @Test
    void testVratiVrednostiUpdateUpita() throws Exception {
        assertEquals("quantity = " + supstanca.getKolicinaZaliha(), supstanca.vratiVrednostiUpdateUpita());
    }

    @Test
    void testVratiJoin() {
        assertEquals("", supstanca.vratiJoin());
    }
}

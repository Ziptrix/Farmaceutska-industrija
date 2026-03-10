/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class DobavljacTest {

    private Dobavljac dobavljac;

    @BeforeEach
    public void setUp() {
        dobavljac = new Dobavljac();
    }

    @AfterEach
    public void tearDown() {
        dobavljac = null;
    }

    @Test
    void testDobavljac() {
        assertNotNull(dobavljac);
    }

    @Test
    void testSetID() throws Exception {
        dobavljac.setId(1L);

        assertEquals(1L, dobavljac.getId());
    }

    @Test
    void testSetIDNull() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setId(null));
    }

    @Test
    void testSetIDNula() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setId(0L));
    }

    @Test
    void testSetIDNegativan() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setId(-1L));
    }

    @Test
    void testSetIme() throws Exception {
        dobavljac.setIme("Mika");

        assertEquals("Mika", dobavljac.getIme());
    }

    @Test
    void testSetImeNull() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setIme(null));
    }

    @Test
    void testSetImePrazno() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setIme(""));
    }

    @Test
    void testSetPrezime() throws Exception {
        dobavljac.setPrezime("Mikic");

        assertEquals("Mikic", dobavljac.getPrezime());
    }

    @Test
    void testSetPrezimeNull() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setPrezime(null));
    }

    @Test
    void testSetPrezimePrazno() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setPrezime(""));
    }

    @Test
    void testSetGrad() throws Exception {
        Grad g = new Grad();
        dobavljac.setGrad(g);

        assertEquals(g, dobavljac.getGrad());
    }

    @Test
    void testSetGradNull() throws Exception {
        assertThrows(Exception.class, () -> dobavljac.setGrad(null));
    }

    @Test
    void testToString() throws Exception {
        dobavljac.setIme("Mika");
        dobavljac.setPrezime("Mikic");
        dobavljac.setGrad(new Grad(12000L, "Pozarevac"));

        assertTrue(dobavljac.toString().contains("Mika"));
        assertTrue(dobavljac.toString().contains("Mikic"));
        assertTrue(dobavljac.toString().contains("Pozarevac"));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("supplier", dobavljac.vratiNazivTabele());
    }

    @Test
    void testVratiNaziveKolonaZaInsertUpit() {
        assertEquals("first_name, last_name, city", dobavljac.vratiNaziveKolonaZaInsertUpit());
    }

    @Test
    void testVratiVrednostiInsertUpita() throws Exception {
        dobavljac.setGrad(new Grad(12000L, "Pozarevac"));

        assertEquals("'" + dobavljac.getIme() + "', '" + dobavljac.getPrezime() + "', " + dobavljac.getGrad().getPostanskiBroj(),
                dobavljac.vratiVrednostiInsertUpita());
    }

    @Test
    void testVratiJoin() {
        assertEquals(" s JOIN city c ON s.city = c.zip_code", dobavljac.vratiJoin());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("s.id, s.first_name, s.last_name, c.zip_code, c.name", dobavljac.vratiVrednostiSelectUpita());
    }

    @Test
    void testVratiUslovZaDelete() {
        assertEquals("WHERE id = ?", dobavljac.vratiUslovZaDelete());
    }

    @Test
    void testVratiNazivPrimarnogKljuca() {
        assertEquals("id", dobavljac.vratiNazivPrimarnogKljuca());
    }

    @Test
    void testVratiVrednostiUpdateUpita() throws Exception {
        dobavljac.setGrad(new Grad(12000L, "Pozarevac"));

        assertEquals("city = " + dobavljac.getGrad().getPostanskiBroj(), dobavljac.vratiVrednostiUpdateUpita());
    }

    @Test
    void testVratiNazivKoloneZaPretragu() {
        assertEquals("first_name", dobavljac.vratiNazivKoloneZaPretragu());
    }

    @Test
    void testVratiNazivKoloneZaGroupBy() {
        assertEquals("id", dobavljac.vratiNazivKoloneZaGroupBy());
    }
}

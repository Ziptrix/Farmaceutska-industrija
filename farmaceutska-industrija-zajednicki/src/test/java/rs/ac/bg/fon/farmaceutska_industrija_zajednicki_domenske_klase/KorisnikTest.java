/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class KorisnikTest {

    private Korisnik korisnik;

    @BeforeEach
    public void setUp() {
        korisnik = new Korisnik();
    }

    @AfterEach
    public void tearDown() {
        korisnik = null;
    }

    @Test
    void testKorisnik() {
        assertNotNull(korisnik);
    }

    @Test
    void testKorisnikStringString() {
        korisnik = new Korisnik("admin", "admin");

        assertNotNull(korisnik);
        assertEquals("admin", korisnik.getKorisnickoIme());
        assertEquals("admin", korisnik.getSifra());
    }

    @Test
    void testSetID() throws Exception {
        korisnik.setId(1L);

        assertEquals(1L, korisnik.getId());
    }

    @Test
    void testSetKorisnickoIme() throws Exception {
        korisnik.setKorisnickoIme("admin");

        assertEquals("admin", korisnik.getKorisnickoIme());
    }

    @Test
    void testSetKorisnickoImeNull() throws Exception {
        assertThrows(Exception.class, () -> korisnik.setKorisnickoIme(null));
    }

    @Test
    void testSetKorisnickoImePrazno() {
        assertThrows(Exception.class, () -> korisnik.setKorisnickoIme(""));
    }

    @Test
    void testSetSifra() throws Exception {
        korisnik.setSifra("admin");

        assertEquals("admin", korisnik.getSifra());
    }

    @Test
    void testSetSifraNull() {
        assertThrows(Exception.class, () -> korisnik.setSifra(null));
    }

    @Test
    void testSetSifraPrazna() {
        assertThrows(Exception.class, () -> korisnik.setSifra(""));
    }

    @Test
    void testSetIme() throws Exception {
        korisnik.setIme("Milos");

        assertEquals("Milos", korisnik.getIme());
    }

    @Test
    void testSetImeNull() {
        assertThrows(Exception.class, () -> korisnik.setIme(null));
    }

    @Test
    void testSetImePrazno() {
        assertThrows(Exception.class, () -> korisnik.setIme(""));
    }

    @Test
    void testSetPrezime() throws Exception {
        korisnik.setPrezime("Korda");

        assertEquals("Korda", korisnik.getPrezime());
    }

    @Test
    void testSetPrezimeNull() {
        assertThrows(Exception.class, () -> korisnik.setPrezime(null));
    }

    @Test
    void testSetPrezimePrazno() {
        assertThrows(Exception.class, () -> korisnik.setPrezime(""));
    }

    @Test
    void testToString() throws Exception {
        korisnik.setIme("Milos");
        korisnik.setPrezime("Korda");
        korisnik.setKorisnickoIme("admin");

        assertTrue(korisnik.toString().contains("Milos"));
        assertTrue(korisnik.toString().contains("Korda"));
        assertTrue(korisnik.toString().contains("admin"));
    }

    @ParameterizedTest
    @CsvSource({
        "admin, admin, true",
        "admin, mare, false",
        "mare, andja, false"
    })
    void testEquals(String korisnickoIme1, String korisnickoIme2, boolean rezultat) throws Exception {
        korisnik.setKorisnickoIme(korisnickoIme1);

        Korisnik k = new Korisnik();
        k.setKorisnickoIme(korisnickoIme2);

        assertEquals(rezultat, korisnik.equals(k));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("user", korisnik.vratiNazivTabele());
    }
}

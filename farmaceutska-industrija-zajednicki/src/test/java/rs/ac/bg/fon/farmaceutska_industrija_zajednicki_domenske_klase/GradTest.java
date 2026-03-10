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
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class GradTest {

    private Grad grad;

    @BeforeEach
    public void setUp() {
        grad = new Grad();
    }

    @AfterEach
    public void tearDown() {
        grad = null;
    }

    @Test
    void testGrad() {
        assertNotNull(grad);
    }

    @Test
    void testSetPostanskiBroj() throws Exception {
        grad.setPostanskiBroj(12000L);

        assertEquals(12000L, grad.getPostanskiBroj());
    }

    @Test
    void testSetPostanskiBrojNull() throws Exception {
        assertThrows(Exception.class, () -> grad.setPostanskiBroj(null));
    }

    @Test
    void testSetPostanskiBrojNula() throws Exception {
        assertThrows(Exception.class, () -> grad.setPostanskiBroj(0L));
    }

    @Test
    void testSetPostanskiBrojNegativan() throws Exception {
        assertThrows(Exception.class, () -> grad.setPostanskiBroj(-1L));
    }

    @Test
    void testSetNaziv() throws Exception {
        grad.setNaziv("Pozarevac");

        assertEquals("Pozarevac", grad.getNaziv());
    }

    @Test
    void testSetNazivNull() throws Exception {
        assertThrows(Exception.class, () -> grad.setNaziv(null));
    }

    @Test
    void testSetNazivPrazan() throws Exception {
        assertThrows(Exception.class, () -> grad.setNaziv(""));
    }

    @Test
    void testToString() throws Exception {
        grad.setPostanskiBroj(12000L);
        grad.setNaziv("Pozarevac");

        assertTrue(grad.toString().contains(12000 + ""));
        assertTrue(grad.toString().contains("Pozarevac"));
    }

    @ParameterizedTest
    @CsvSource({
        "12000, 12000, true",
        "11000, 12000, false"
    })
    void testEquals(Long postanskiBroj1, Long postanskiBroj2, boolean rezultat) throws Exception {
        grad.setPostanskiBroj(postanskiBroj1);

        Grad g = new Grad();
        g.setPostanskiBroj(postanskiBroj2);

        assertEquals(rezultat, grad.equals(g));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("city", grad.vratiNazivTabele());
    }

    @Test
    void testVratiJoin() {
        assertEquals("", grad.vratiJoin());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("zip_code, name", grad.vratiVrednostiSelectUpita());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class StavkaNarudzbeniceTest {

    private StavkaNarudzbenice stavkaNarudzbenice;

    @BeforeEach
    public void setUp() {
        stavkaNarudzbenice = new StavkaNarudzbenice();
    }

    @AfterEach
    public void tearDown() {
        stavkaNarudzbenice = null;
    }

    @Test
    void testStavkaNarudzbenice() {
        assertNotNull(stavkaNarudzbenice);
    }

    @Test
    void testSetRedniBroj() throws Exception {
        stavkaNarudzbenice.setRedniBroj(1L);

        assertEquals(1, stavkaNarudzbenice.getRedniBroj());
    }

    @Test
    void testSetRedniBrojNull() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setRedniBroj(null));
    }

    @Test
    void testSetRedniBrojNula() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setRedniBroj(0L));
    }

    @Test
    void testSetRedniBrojNegativan() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setRedniBroj(-1L));
    }

    @Test
    void testSetNarudzbenica() throws Exception {
        Narudzbenica narudzbenica = new Narudzbenica();
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);

        assertEquals(narudzbenica, stavkaNarudzbenice.getNarudzbenica());
    }

    @Test
    void testSetNarudzbenicaNull() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setNarudzbenica(null));
    }

    @Test
    void testSetKolicinaSupstance() throws Exception {
        stavkaNarudzbenice.setKolicinaSupstance(20L);

        assertEquals(20, stavkaNarudzbenice.getKolicinaSupstance());
    }

    @Test
    void testSetKolicinaSupstanceNull() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setKolicinaSupstance(null));
    }

    @Test
    void testSetKolicinaSupstanceNula() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setKolicinaSupstance(0L));
    }

    @Test
    void testSetKolicinaSupstanceNegativna() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setKolicinaSupstance(-1L));
    }

    @Test
    void testSetIznosStavke() throws Exception {
        stavkaNarudzbenice.setIznosStavke(200L);

        assertEquals(200, stavkaNarudzbenice.getIznosStavke());
    }

    @Test
    void testSetIznosStavkeNull() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setIznosStavke(null));
    }

    @Test
    void testSetIznosStavkeNula() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setIznosStavke(0L));
    }

    @Test
    void testSetIznosStavkeNegativan() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setIznosStavke(-1L));
    }

    @Test
    void testSetSupstanca() throws Exception {
        Supstanca supstanca = new Supstanca();
        stavkaNarudzbenice.setSupstanca(supstanca);

        assertEquals(supstanca, stavkaNarudzbenice.getSupstanca());
    }

    @Test
    void testSetSupstancaNull() throws Exception {
        assertThrows(Exception.class, () -> stavkaNarudzbenice.setSupstanca(null));
    }

    @Test
    void testToString() throws Exception {
        Supstanca supstanca = new Supstanca();
        stavkaNarudzbenice.setKolicinaSupstance(30L);
        stavkaNarudzbenice.setSupstanca(supstanca);

        assertTrue(stavkaNarudzbenice.toString().contains(30 + ""));
        assertTrue(stavkaNarudzbenice.toString().contains(supstanca.toString()));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("item", stavkaNarudzbenice.vratiNazivTabele());
    }

    @Test
    void testVratiNaziveKolonaZaInsertUpit() {
        assertEquals("order_number, id_po, quantity, amount, substance", stavkaNarudzbenice.vratiNaziveKolonaZaInsertUpit());
    }

    @Test
    void testVratiVrednostiInsertUpita() throws Exception {
        Narudzbenica narudzbenica = new Narudzbenica();
        Supstanca supstanca = new Supstanca();
        stavkaNarudzbenice.setNarudzbenica(narudzbenica);
        stavkaNarudzbenice.setSupstanca(supstanca);

        assertEquals(stavkaNarudzbenice.getRedniBroj() + ", " + stavkaNarudzbenice.getNarudzbenica().getSifra() + ", "
                + stavkaNarudzbenice.getKolicinaSupstance() + ", " + stavkaNarudzbenice.getIznosStavke() + ", " + stavkaNarudzbenice.getSupstanca().getSifra(),
                stavkaNarudzbenice.vratiVrednostiInsertUpita());
    }

    @Test
    void testVratiUslovZaDelete() {
        assertEquals("WHERE id_po = ?", stavkaNarudzbenice.vratiUslovZaDelete());
    }
}

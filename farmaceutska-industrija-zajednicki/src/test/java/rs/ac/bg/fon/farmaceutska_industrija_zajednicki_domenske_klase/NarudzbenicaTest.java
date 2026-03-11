/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki_domenske_klase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;

/**
 *
 * @author milos
 */
public class NarudzbenicaTest {

    private Narudzbenica narudzbenica;

    @BeforeEach
    public void setUp() {
        narudzbenica = new Narudzbenica();
    }

    @AfterEach
    public void tearDown() {
        narudzbenica = null;
    }

    @Test
    void testNarudzbenica() {
        assertNotNull(narudzbenica);
    }

    @Test
    void testSetSifra() throws Exception {
        narudzbenica.setSifra(111L);

        assertEquals(111, narudzbenica.getSifra());
    }

    @Test
    void testSetSifraNull() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setSifra(null));
    }

    @Test
    void testSetSifraNula() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setSifra(0L));
    }

    @Test
    void testSetSifraNegativna() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setSifra(-1L));
    }

    @Test
    void testSetDatum() throws Exception {
        narudzbenica.setDatum(LocalDate.now());

        assertEquals(LocalDate.now(), narudzbenica.getDatum());
    }

    @Test
    void testSetDatumNull() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setDatum(null));
    }

    @Test
    void testSetDatumAfter() {
        assertThrows(Exception.class, () -> narudzbenica.setDatum(LocalDate.now().plusDays(10)));
    }

    @Test
    void testSetUkupanIznos() throws Exception {
        narudzbenica.setUkupanIznos(1000L);

        assertEquals(1000, narudzbenica.getUkupanIznos());
    }

    @Test
    void testSetUkupanIznosNull() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setUkupanIznos(null));
    }

    @Test
    void testSetUkupanIznosNula() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setUkupanIznos(0L));
    }

    @Test
    void testSetUkupanIznosNegativan() throws Exception {
        assertThrows(Exception.class, () -> narudzbenica.setUkupanIznos(-1L));
    }

    @Test
    void testSetKorisnik() throws Exception {
        Korisnik korisnik = new Korisnik();
        narudzbenica.setKorisnik(korisnik);

        assertEquals(korisnik, narudzbenica.getKorisnik());
    }

    @Test
    void testSetDobavljac() throws Exception {
        Dobavljac dobavljac = new Dobavljac();
        narudzbenica.setDobavljac(dobavljac);

        assertEquals(dobavljac, narudzbenica.getDobavljac());
    }

    @Test
    void testSetListaStavki() throws Exception {
        StavkaNarudzbenice stavkaNarudzbenice = new StavkaNarudzbenice();
        List<StavkaNarudzbenice> stavkeNarudzbenice = new ArrayList<>();
        stavkeNarudzbenice.add(stavkaNarudzbenice);

        narudzbenica.setListaStavki(stavkeNarudzbenice);
        assertEquals(1, narudzbenica.getListaStavki().size());
        assertEquals(stavkeNarudzbenice, narudzbenica.getListaStavki());
        assertTrue(narudzbenica.getListaStavki().contains(stavkaNarudzbenice));
    }

    @Test
    void testToString() throws Exception {
        narudzbenica.setDatum(LocalDate.now());
        narudzbenica.setUkupanIznos(1000L);

        assertTrue(narudzbenica.toString().contains(LocalDate.now().toString()));
        assertTrue(narudzbenica.toString().contains(1000 + ""));
    }

    @Test
    void testVratiNazivTabele() {
        assertEquals("purchase_order", narudzbenica.vratiNazivTabele());
    }

    @Test
    void testVratiNaziveKolonaZaInsertUpit() {
        assertEquals("code, date, total_amount, user, supplier", narudzbenica.vratiNaziveKolonaZaInsertUpit());
    }

    @Test
    void testVratiVrednostiInsertUpita() throws Exception {
        Korisnik korisnik = new Korisnik();
        Dobavljac dobavljac = new Dobavljac();
        narudzbenica.setKorisnik(korisnik);
        narudzbenica.setDobavljac(dobavljac);

        assertEquals(narudzbenica.getSifra() + ", '" + narudzbenica.getDatum() + "', "
                + narudzbenica.getUkupanIznos() + ", " + narudzbenica.getKorisnik().getId() + ", " + narudzbenica.getDobavljac().getId(),
                narudzbenica.vratiVrednostiInsertUpita());
    }

    @Test
    void testVratiVrednostiSelectUpita() {
        assertEquals("n.code, n.date, n.total_amount, n.user, n.supplier, "
                + "sn.order_number AS stavka_id, sn.quantity, sn.amount, "
                + "s.code AS supstanca_id, s.name, s.quantity, s.price, "
                + "k.id, k.first_name, k.last_name, k.username, k.password, "
                + "d.id, d.first_name, d.last_name, d.city, "
                + "g.zip_code, g.name",
                narudzbenica.vratiVrednostiSelectUpita());
    }

    @Test
    void testVratiJoin() {
        assertEquals(" n JOIN item sn ON n.code = sn.id_po "
                + " JOIN substance s ON sn.substance = s.code "
                + " JOIN user k ON n.user = k.id "
                + " JOIN supplier d ON n.supplier = d.id "
                + " JOIN city g ON d.city = g.zip_code ",
                narudzbenica.vratiJoin());
    }

    @Test
    void testVratiUslovZaDelete() {
        assertEquals("WHERE code = ?", narudzbenica.vratiUslovZaDelete());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerKonekcija;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class DodajNarudzbenicuSOTest {

    private DodajNarudzbenicuSO dodajNarudzbenicu;

    @BeforeEach
    void setUp() throws Exception {
        dodajNarudzbenicu = new DodajNarudzbenicuSO();
        dodajNarudzbenicu.setTestMode(true);
        DBBrokerKonekcija.vratiInstancu().uspostaviKonekcijuZaTest();
    }

    @AfterEach
    void tearDown() throws Exception {
        DBBrokerKonekcija.vratiInstancu().ponistiTransakciju();
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testDodajNarudzbenicu() throws Exception {
        List<OpstaDomenskaKlasa> supstanceIzBaze = dodajNarudzbenicu.getBroker().ucitajSve(new Supstanca());
        assertFalse(supstanceIzBaze.isEmpty(), "Test baza mora imati barem jednu supstancu");

        Supstanca s = (Supstanca) supstanceIzBaze.get(0);

        StavkaNarudzbenice stavka = new StavkaNarudzbenice();
        stavka.setRedniBroj(1L);
        stavka.setSupstanca(s);
        stavka.setKolicinaSupstance(10L);

        Narudzbenica narudzbenica = new Narudzbenica();
        narudzbenica.setSifra(100L);
        narudzbenica.setDatum(LocalDate.now());
        narudzbenica.setKorisnik(new Korisnik());
        narudzbenica.setDobavljac(new Dobavljac());
        narudzbenica.setListaStavki(new ArrayList<>());
        narudzbenica.getListaStavki().add(stavka);

        stavka.setNarudzbenica(narudzbenica);

        dodajNarudzbenicu.izvrsi(narudzbenica, null);

        // Provera da li je narudzbenica dobila ID
        assertNotNull(narudzbenica.getSifra(), "Narudzbenica mora imati ID nakon dodavanja");

        // Provera da li je stavka dobila ID
        assertNotNull(stavka.getRedniBroj(), "Stavka narudzbenice mora imati ID nakon dodavanja");

        // Provera da li je količina supstance u bazi promenjena
        List<OpstaDomenskaKlasa> supstancePosle = dodajNarudzbenicu.getBroker().ucitajSve(new Supstanca());
        Supstanca sPosle = supstancePosle.stream()
                .map(op -> (Supstanca) op)
                .filter(sub -> sub.getSifra().equals(s.getSifra()))
                .findFirst()
                .orElse(null);

        assertNotNull(sPosle, "Supstanca mora postojati u bazi");
        assertEquals(s.getKolicinaZaliha() + 10, sPosle.getKolicinaZaliha(), "Količina supstance mora biti povećana za količinu stavke");
    }
}

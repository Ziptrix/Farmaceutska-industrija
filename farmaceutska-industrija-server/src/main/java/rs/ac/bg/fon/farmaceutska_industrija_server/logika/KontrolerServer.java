/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.logika;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.DodajDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.IzmeniDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.ObrisiDobavljacaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.PretraziDobavljaceSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.UcitajSveDobavljaceSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.grad.UcitajSveGradoveSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik.PrijavaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.DodajLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.IzmeniLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.ObrisiLekSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.lek.PretraziLekoveSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica.DodajNarudzbenicuSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica.ObrisiNarudzbenicuSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica.UcitajSveNarudzbeniceSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.supstanca.UcitajSveSupstanceSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class KontrolerServer {

    private static KontrolerServer instanca;
    private Grad grad;
    private Dobavljac dobavljac;
    private Supstanca supstanca;
    private Lek lek;
    private Narudzbenica narudzbenica;

    private KontrolerServer() {
        grad = new Grad();
        dobavljac = new Dobavljac();
        supstanca = new Supstanca();
        lek = new Lek();
        narudzbenica = new Narudzbenica();
    }

    public static KontrolerServer vratiInstancu() {
        if (instanca == null) {
            instanca = new KontrolerServer();
        }
        return instanca;
    }

    public Korisnik prijava(Korisnik korisnik) throws Exception {
        ApstraktnaSO prijava = new PrijavaSO();
        prijava.izvrsi(korisnik, null);
        return ((PrijavaSO) prijava).getKorisnik();
    }

    public List<Grad> ucitajSveGradove() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveGradoveSO();
        ucitajSve.izvrsi(grad, null);
        return ((UcitajSveGradoveSO) ucitajSve).getGradovi();
    }

    public void dodajDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO dodaj = new DodajDobavljacaSO();
        dodaj.izvrsi(dobavljac, null);
    }

    public List<Dobavljac> ucitajSveDobavljace() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveDobavljaceSO();
        ucitajSve.izvrsi(dobavljac, null);
        return ((UcitajSveDobavljaceSO) ucitajSve).getDobavljaci();
    }

    public void obrisiDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO obrisi = new ObrisiDobavljacaSO();
        obrisi.izvrsi(dobavljac, null);
    }

    public void izmeniDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO izmeni = new IzmeniDobavljacaSO();
        izmeni.izvrsi(dobavljac, null);
    }

    public List<Dobavljac> pretraziDobavljace(String kriterijum) throws Exception {
        ApstraktnaSO pretrazi = new PretraziDobavljaceSO();
        pretrazi.izvrsi(dobavljac, kriterijum);
        return ((PretraziDobavljaceSO) pretrazi).getDobavljaci();
    }

    public List<Supstanca> ucitajSveSupstance() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveSupstanceSO();
        ucitajSve.izvrsi(supstanca, null);
        return ((UcitajSveSupstanceSO) ucitajSve).getSupstance();
    }

    public void dodajLek(Lek lek) throws Exception {
        ApstraktnaSO dodaj = new DodajLekSO();
        dodaj.izvrsi(lek, null);
    }

    public List<Lek> pretraziLekove(String kriterijum) throws Exception {
        ApstraktnaSO pretrazi = new PretraziLekoveSO();
        pretrazi.izvrsi(lek, kriterijum);
        return ((PretraziLekoveSO) pretrazi).getLekovi();
    }

    public void izmeniLek(Lek lek) throws Exception {
        ApstraktnaSO izmeni = new IzmeniLekSO();
        izmeni.izvrsi(lek, null);
    }

    public void obrisiLek(Lek lek) throws Exception {
        ApstraktnaSO obrisi = new ObrisiLekSO();
        obrisi.izvrsi(lek, null);
    }

    public void dodajNarudzbenicu(Narudzbenica narudzbenica) throws Exception {
        ApstraktnaSO dodaj = new DodajNarudzbenicuSO();
        dodaj.izvrsi(narudzbenica, null);
    }

    public List<Narudzbenica> ucitajSveNarudzbenice() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveNarudzbeniceSO();
        ucitajSve.izvrsi(narudzbenica, null);
        return ((UcitajSveNarudzbeniceSO) ucitajSve).getNarudzbenice();
    }

    public void obrisiNarudzbenicu(Narudzbenica narudzbenica) throws Exception {
        ApstraktnaSO obrisi = new ObrisiNarudzbenicuSO();
        obrisi.izvrsi(narudzbenica, null);
    }
}

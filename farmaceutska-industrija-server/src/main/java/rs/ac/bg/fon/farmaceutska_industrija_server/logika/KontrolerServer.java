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
import rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac.UcitajSveDobavljaceSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.grad.UcitajSveGradoveSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik.PrijavaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class KontrolerServer {

    private static KontrolerServer instanca;
    private Grad grad;
    private Dobavljac dobavljac;

    private KontrolerServer() {
        grad = new Grad();
        dobavljac = new Dobavljac();
    }

    public static KontrolerServer vratiInstancu() {
        if (instanca == null) {
            instanca = new KontrolerServer();
        }
        return instanca;
    }

    public Korisnik prijava(Korisnik korisnik) throws Exception {
        ApstraktnaSO prijava = new PrijavaSO();
        prijava.izvrsi(korisnik);
        return ((PrijavaSO) prijava).getKorisnik();
    }

    public List<Grad> ucitajSveGradove() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveGradoveSO();
        ucitajSve.izvrsi(grad);
        return ((UcitajSveGradoveSO) ucitajSve).getGradovi();
    }

    public void dodajDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO dodaj = new DodajDobavljacaSO();
        dodaj.izvrsi(dobavljac);
    }

    public List<Dobavljac> ucitajSveDobavljace() throws Exception {
        ApstraktnaSO ucitajSve = new UcitajSveDobavljaceSO();
        ucitajSve.izvrsi(dobavljac);
        return ((UcitajSveDobavljaceSO) ucitajSve).getDobavljaci();
    }

    public void obrisiDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO obrisi = new ObrisiDobavljacaSO();
        obrisi.izvrsi(dobavljac);
    }

    public void izmeniDobavljaca(Dobavljac dobavljac) throws Exception {
        ApstraktnaSO izmeni = new IzmeniDobavljacaSO();
        izmeni.izvrsi(dobavljac);
    }
}

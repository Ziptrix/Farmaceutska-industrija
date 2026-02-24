/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.kontroler;

import java.net.Socket;
import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.OdgovorServera;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.Operacija;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.Posiljalac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.Primalac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.ZahtevKlijenta;

/**
 *
 * @author milos
 */
public class KontrolerKlijent {

    Socket soket;
    Posiljalac posiljalac;
    Primalac primalac;
    private static KontrolerKlijent instanca;

    private KontrolerKlijent() throws Exception {
        soket = new Socket("127.0.0.1", 9090);
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
    }

    public static KontrolerKlijent vratiInstancu() throws Exception {
        if (instanca == null) {
            instanca = new KontrolerKlijent();
        }
        return instanca;
    }

    public Korisnik prijava(Korisnik korisnik) throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.LOGIN, korisnik);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() == null) {
            return (Korisnik) odgovor.getRezultat();
        } else {
            throw odgovor.getIzuzetak();
        }
    }

    public List<Grad> prikaziSveGradove() throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.PRIKAZI_SVE_GRADOVE, null);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() == null) {
            return (List<Grad>) odgovor.getRezultat();
        } else {
            throw odgovor.getIzuzetak();
        }
    }

    public void dodajDobavljaca(Dobavljac dobavljac) throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.DODAJ_DOBAVLJACA, dobavljac);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() == null) {
            dobavljac.setId(((Dobavljac) odgovor.getRezultat()).getId());
        } else {
            throw odgovor.getIzuzetak();
        }
    }

    public List<Dobavljac> prikaziSveDobavljace() throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.PRIKAZI_SVE_DOBAVLJACE, null);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() == null) {
            return (List<Dobavljac>) odgovor.getRezultat();
        } else {
            throw odgovor.getIzuzetak();
        }
    }

    public void obrisiDobavljaca(Dobavljac dobavljac) throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.OBRISI_DOBAVLJACA, dobavljac);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }

    public void izmeniDobavljaca(Dobavljac dobavljac) throws Exception {
        ZahtevKlijenta zahtev = new ZahtevKlijenta(Operacija.IZMENI_DOBAVLJACA, dobavljac);
        posiljalac.posaljiObjekat(zahtev);
        OdgovorServera odgovor = (OdgovorServera) primalac.primiObjekat();

        if (odgovor.getIzuzetak() != null) {
            throw odgovor.getIzuzetak();
        }
    }
}

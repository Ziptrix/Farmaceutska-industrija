/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.niti;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import rs.ac.bg.fon.farmaceutska_industrija_server.logika.KontrolerServer;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.OdgovorServera;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.Posiljalac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.Primalac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija.ZahtevKlijenta;

/**
 *
 * @author milos
 */
public class KlijentskiZahteviNit extends Thread {

    private Socket soket;
    private Korisnik korisnik;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private ServerskaNit server;

    public KlijentskiZahteviNit(Socket soket, ServerskaNit server) throws IOException {
        this.soket = soket;
        this.korisnik = new Korisnik();
        posiljalac = new Posiljalac(soket);
        primalac = new Primalac(soket);
        this.server = server;
    }

    public Socket getSoket() {
        return soket;
    }

    public Korisnik getKorisnika() {
        return korisnik;
    }

    public void setKorisnika(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public void run() {
        while (!soket.isClosed()) {
            try {
                ZahtevKlijenta zahtev = (ZahtevKlijenta) primalac.primiObjekat();
                OdgovorServera odgovor = obradiZahtevKlijenta(zahtev);
                posiljalac.posaljiObjekat(odgovor);
            } catch (SocketException se) {
                System.out.println("Klijent je prekinuo vezu: " + soket);
                break;
            } catch (IOException ioe) {
                System.out.println("Greska u komunikaciji sa klijentom: " + ioe.getMessage());
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        server.odjaviKorisnika(korisnik, this);
    }

    private OdgovorServera obradiZahtevKlijenta(ZahtevKlijenta zahtev) {
        OdgovorServera odgovor = new OdgovorServera();
        try {
            switch (zahtev.getOperacija()) {
                case LOGIN: {
                    Korisnik korisnik = (Korisnik) zahtev.getArgument();
                    try {
                        Korisnik pronadjen = KontrolerServer.vratiInstancu().prijava(korisnik);
                        server.prijavaKorisnika(pronadjen, this);
                        this.korisnik = pronadjen;
                        odgovor.setRezultat(pronadjen);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRIKAZI_SVE_GRADOVE: {
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().ucitajSveGradove());
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case DODAJ_DOBAVLJACA: {
                    Dobavljac dobavljac = (Dobavljac) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().dodajDobavljaca(dobavljac);
                        odgovor.setRezultat(dobavljac);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRIKAZI_SVE_DOBAVLJACE: {
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().ucitajSveDobavljace());
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case OBRISI_DOBAVLJACA: {
                    Dobavljac dobavljac = (Dobavljac) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().obrisiDobavljaca(dobavljac);
                        odgovor.setRezultat(null);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case IZMENI_DOBAVLJACA: {
                    Dobavljac dobavljac = (Dobavljac) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().izmeniDobavljaca(dobavljac);
                        odgovor.setRezultat(dobavljac);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRETRAGA_DOBAVLJACA: {
                    String kriterijum = (String) zahtev.getArgument();
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().pretraziDobavljace(kriterijum));
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRIKAZI_SVE_SUPSTANCE: {
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().ucitajSveSupstance());
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case DODAJ_LEK: {
                    Lek lek = (Lek) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().dodajLek(lek);
                        odgovor.setRezultat(lek);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRETRAGA_LEKOVA: {
                    String kriterijum = (String) zahtev.getArgument();
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().pretraziLekove(kriterijum));
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case IZMENI_LEK: {
                    Lek lek = (Lek) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().izmeniLek(lek);
                        odgovor.setRezultat(lek);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case OBRISI_LEK: {
                    Lek lek = (Lek) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().obrisiLek(lek);
                        odgovor.setRezultat(null);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case DODAJ_NARUDZBENICU: {
                    Narudzbenica narudzbenica = (Narudzbenica) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().dodajNarudzbenicu(narudzbenica);
                        odgovor.setRezultat(narudzbenica);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case PRIKAZI_SVE_NARUDZBENICE: {
                    try {
                        odgovor.setRezultat(KontrolerServer.vratiInstancu().ucitajSveNarudzbenice());
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                case OBRISI_NARUDZBENICU: {
                    Narudzbenica narudzbenica = (Narudzbenica) zahtev.getArgument();
                    try {
                        KontrolerServer.vratiInstancu().obrisiNarudzbenicu(narudzbenica);
                        odgovor.setRezultat(null);
                    } catch (Exception e) {
                        odgovor.setIzuzetak(e);
                    }
                    break;
                }
                default:
                    throw new AssertionError();
            }
        } catch (Exception e) {
            odgovor.setIzuzetak(e);
        }
        return odgovor;
    }

}

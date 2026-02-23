/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.logika;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstaktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik.PrijavaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class KontrolerServer {

    private static KontrolerServer instanca;

    private KontrolerServer() {
    }

    public static KontrolerServer vratiInstancu() {
        if (instanca == null) {
            instanca = new KontrolerServer();
        }
        return instanca;
    }

    public Korisnik prijava(Korisnik korisnik) throws Exception {
        ApstaktnaSO prijava = new PrijavaSO();
        prijava.izvrsi(korisnik);
        return ((PrijavaSO) prijava).getKorisnik();
    }
}

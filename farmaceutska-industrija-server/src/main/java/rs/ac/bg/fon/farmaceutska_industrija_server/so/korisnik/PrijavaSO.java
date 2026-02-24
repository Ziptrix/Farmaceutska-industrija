/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.korisnik;

import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
@Getter
public class PrijavaSO extends ApstraktnaSO {

    private Korisnik korisnik;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Korisnik)) {
            throw new Exception("Prosledjen objekat mora biti tipa Korisnik!");
        }

        if (objekat instanceof Korisnik) {
            if (((Korisnik) objekat).getKorisnickoIme().isEmpty() || ((Korisnik) objekat).getKorisnickoIme()== "") {
                throw new Exception("Korisnicko ime Korisnika ne sme biti prazno!");
            }
            if (((Korisnik) objekat).getSifra().isEmpty() || ((Korisnik) objekat).getSifra()== "") {
                throw new Exception("Sifra Korisnika ne sme biti prazna!");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        korisnik = broker.vratiKorisnika((Korisnik) objekat);
    }

}

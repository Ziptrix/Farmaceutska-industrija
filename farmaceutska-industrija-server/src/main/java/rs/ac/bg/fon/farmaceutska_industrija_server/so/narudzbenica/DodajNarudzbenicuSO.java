/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
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
public class DodajNarudzbenicuSO extends ApstraktnaSO {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Narudzbenica)) {
            throw new Exception("Prosledjen objekat mora biti tipa Narudzbenica!");
        }

        if (((Narudzbenica) objekat).getSifra() == null) {
            throw new Exception("Sifra narudzbenice nije uneta!");
        }

        if (((Narudzbenica) objekat).getSifra() <= 0) {
            throw new Exception("Uneta sifra narudzbenica mora imati pozitivnu vrednost!");
        }

        if (((Narudzbenica) objekat).getDatum() == null) {
            throw new Exception("Datum kreiranja narudzbenice nije unet!");
        }

        if (((Narudzbenica) objekat).getUkupanIznos() == null) {
            throw new Exception("Ukupan inznos narudzbenice nije unet!");
        }

        if (((Narudzbenica) objekat).getUkupanIznos() <= 0) {
            throw new Exception("Ukupan iznos narudzbenice mora biti pozitivan!");
        }

        if (((Narudzbenica) objekat).getKorisnik() == null || !(((Narudzbenica) objekat).getKorisnik() instanceof Korisnik)) {
            throw new Exception("Korisnik koji je uneo narudzbenicu nije prosledjen!");
        }

        if (((Narudzbenica) objekat).getDobavljac() == null || !(((Narudzbenica) objekat).getDobavljac() instanceof Dobavljac)) {
            throw new Exception("Dobavljac od kojeg se narucuju supstance nije prosledjen!");
        }

        if (((Narudzbenica) objekat).getListaStavki() == null) {
            throw new Exception("Lista supstanci koje su nabavljene nije uneta");
        }

        if (((Narudzbenica) objekat).getListaStavki().isEmpty()) {
            throw new Exception("Lista supstanci koje su nabavljene ne sme biti prazna!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Narudzbenica narudzbenica = (Narudzbenica) objekat;
        broker.dodaj(narudzbenica);

        List<OpstaDomenskaKlasa> supstanceIzBaze = broker.ucitajSve(new Supstanca());

        List<Supstanca> supstance = supstanceIzBaze.stream()
                .map(odk -> (Supstanca) odk)
                .toList();

        for (StavkaNarudzbenice stavkaNarudzbenice : narudzbenica.getListaStavki()) {
            broker.dodaj(stavkaNarudzbenice);

            for (Supstanca supstanca : supstance) {
                if (stavkaNarudzbenice.getSupstanca().equals(supstanca)) {
                    supstanca.setKolicinaZaliha(supstanca.getKolicinaZaliha() + stavkaNarudzbenice.getKolicinaSupstance());
                    broker.izmeni(supstanca);
                    break;
                }
            }
        }
    }

}

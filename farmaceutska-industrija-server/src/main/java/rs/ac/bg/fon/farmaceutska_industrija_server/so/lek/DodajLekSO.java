/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
public class DodajLekSO extends ApstraktnaSO {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Lek)) {
            throw new Exception("Prosledjeni objekat mora biti tipa Lek!");
        }

        if (((Lek) objekat).getSerijskiBroj() == null) {
            throw new Exception("Serijski broj leka nije unet!");
        }

        if (((Lek) objekat).getSerijskiBroj() <= 0) {
            throw new Exception("Unet serijski broj leka mora imatu pozitivnu vrednost!");
        }

        if (((Lek) objekat).getNaziv() == null) {
            throw new Exception("Naziv leka nije unet!");
        }

        if (((Lek) objekat).getNaziv().isEmpty() || ((Lek) objekat).getNaziv().equals("")) {
            throw new Exception("Naziv leka ne sme biti prazan!");
        }

        if (((Lek) objekat).getDoziranje() == null) {
            throw new Exception("Doziranje za lek nije uneto!");
        }

        if (((Lek) objekat).getDoziranje().isEmpty() || ((Lek) objekat).getDoziranje().equals("")) {
            throw new Exception("Doziranje leka ne sme biti prazno!");
        }

        if (((Lek) objekat).getSastav() == null) {
            throw new Exception("Lista supstanci od kojih je lek napravljen nije uneta!");
        }

        if (((Lek) objekat).getSastav().isEmpty()) {
            throw new Exception("Lista supstanci od kojih je lek napravljen ne sme biti prazna!");
        }

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Lek lek = (Lek) objekat;
        broker.dodaj(lek);

        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve(new Supstanca());

        List<Supstanca> supstanceIzBaze = rezultat.stream()
                .map(op -> (Supstanca) op)
                .toList();

        for (Supstanca supstanca : lek.getSastav()) {
            SupstancaLek sl = new SupstancaLek();
            sl.setLek(lek);
            sl.setSupstanca(supstanca);
            sl.setUpotrebljenaKolicina(supstanca.getKolicinaZaliha());
            broker.dodaj(sl);

            Supstanca izBaze = supstanceIzBaze.stream()
                    .filter(s -> s.getSifra().equals(supstanca.getSifra()))
                    .findFirst()
                    .orElseThrow(() -> new Exception("Supstanca nije pronadjena u bazi"));

            long novaKolicina = izBaze.getKolicinaZaliha() - supstanca.getKolicinaZaliha();
            izBaze.setKolicinaZaliha(novaKolicina);
            broker.izmeni(izBaze);
        }
    }

}

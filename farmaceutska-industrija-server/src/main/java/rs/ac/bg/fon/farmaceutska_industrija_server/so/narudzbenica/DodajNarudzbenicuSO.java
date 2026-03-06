/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
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

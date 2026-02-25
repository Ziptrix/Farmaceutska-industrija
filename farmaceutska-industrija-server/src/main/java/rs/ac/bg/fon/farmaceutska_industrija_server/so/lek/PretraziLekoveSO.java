/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
@Getter
public class PretraziLekoveSO extends ApstraktnaSO {

    List<Lek> lekovi;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Lek)) {
            throw new Exception("Prosledjen objekat mora biti tipa Lek!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.pretrazi((Lek) objekat, kljuc);
        lekovi = new ArrayList<>();

        for (OpstaDomenskaKlasa opstaDomenskaKlasa : rezultat) {
            lekovi.add((Lek) opstaDomenskaKlasa);
        }

        List<OpstaDomenskaKlasa> slZaLek = broker.ucitajSve(new SupstancaLek());
        List<SupstancaLek> supstanceULeku = new ArrayList<>();

        for (OpstaDomenskaKlasa opstaDomenskaKlasa : slZaLek) {
            supstanceULeku.add((SupstancaLek) opstaDomenskaKlasa);
        }

        for (Lek lek : lekovi) {
            List<Supstanca> sastav = new ArrayList<>();

            for (SupstancaLek supstancaLek : supstanceULeku) {
                if (lek.getSerijskiBroj().equals(supstancaLek.getLek().getSerijskiBroj())) {
                    Supstanca s = supstancaLek.getSupstanca();
                    s.setKolicinaZaliha(supstancaLek.getUpotrebljenaKolicina());
                    sastav.add(s);
                }
            }
            lek.setSastav(sastav);
        }
    }

}

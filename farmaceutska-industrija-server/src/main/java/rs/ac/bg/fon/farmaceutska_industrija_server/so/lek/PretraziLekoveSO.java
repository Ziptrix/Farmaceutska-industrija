/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

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

        lekovi = rezultat.stream()
                .map(op -> (Lek) op)
                .toList();

        List<OpstaDomenskaKlasa> slZaLek = broker.ucitajSve(new SupstancaLek());

        List<SupstancaLek> supstanceULeku = slZaLek.stream()
                .map(op -> (SupstancaLek) op)
                .toList();

        for (Lek lek : lekovi) {
            List<Supstanca> sastav = supstanceULeku.stream()
                    .filter(sl -> lek.getSerijskiBroj().equals(sl.getLek().getSerijskiBroj()))
                    .map(sl -> {
                        Supstanca s = sl.getSupstanca();
                        try {
                            s.setKolicinaZaliha(sl.getUpotrebljenaKolicina());
                        } catch (Exception e) {
                            System.out.println("Greska " + e.getMessage());
                        }
                        return s;
                    })
                    .toList();

            lek.setSastav(sastav);
        }

        if (lekovi.isEmpty()) {
            throw new Exception("Sistem ne moze da pronadje lekove koji odgovaraju!");
        }
    }

}

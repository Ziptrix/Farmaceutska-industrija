/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.grad;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstaktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
@Getter
public class UcitajSveGradoveSO extends ApstaktnaSO {

    List<Grad> gradovi;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Grad)) {
            throw new Exception("Prosledjen objekat mora biti tipa Grad!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve((Grad) objekat);

        gradovi = new ArrayList<>();

        for (OpstaDomenskaKlasa opstaDomenskaKlasa : rezultat) {
            gradovi.add((Grad) opstaDomenskaKlasa);
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
@Getter
public class PretraziDobavljaceSO extends ApstraktnaSO {

    List<Dobavljac> dobavljaci;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti tipa Dobavljac!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.pretrazi((Dobavljac) objekat, kljuc);
        dobavljaci = new ArrayList<>();

        for (OpstaDomenskaKlasa opstaDomenskaKlasa : rezultat) {
            dobavljaci.add((Dobavljac) opstaDomenskaKlasa);
        }
    }

}

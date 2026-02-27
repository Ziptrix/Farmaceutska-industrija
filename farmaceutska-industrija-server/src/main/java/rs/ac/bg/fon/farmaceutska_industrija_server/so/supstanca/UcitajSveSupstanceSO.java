/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.supstanca;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
@Getter
public class UcitajSveSupstanceSO extends ApstraktnaSO {

    List<Supstanca> supstance;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Supstanca)) {
            throw new Exception("Prosledjen objekat mora biti tipa Supstanca!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        supstance = broker.ucitajSve((Supstanca) objekat).stream()
                .map(opsta -> (Supstanca) opsta)
                .collect(Collectors.toList());

        if (supstance.isEmpty()) {
            throw new Exception("Lista ucitanih supstanci je prazna pa se forma za dodavanje leka ne otvara!");
        }
    }

}

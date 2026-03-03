/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 *
 * @author milos
 */
public class IzmeniLekSO extends ApstraktnaSO {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Lek)) {
            throw new Exception("Prosledjen objekat mora biti tipa Lek!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Lek lek = (Lek) objekat;
        broker.izmeni((OpstaDomenskaKlasa) lek);
        SupstancaLek supstanceLeka = new SupstancaLek();
        supstanceLeka.setLek(lek);

        for (Supstanca supstanca : lek.getSastav()) {
            supstanceLeka.setSupstanca(supstanca);
            supstanceLeka.setUpotrebljenaKolicina(supstanca.getKolicinaZaliha());
            broker.izmeni(supstanceLeka);
        }
    }

}

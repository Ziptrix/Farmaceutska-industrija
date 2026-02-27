/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import java.sql.SQLIntegrityConstraintViolationException;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
public class ObrisiDobavljacaSO extends ApstraktnaSO {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti instance Dobavljac!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        try {
            broker.obrisi((OpstaDomenskaKlasa) objekat);
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new Exception("Dobavljac se ne može obrisati jer postoji u narudžbenici.");
        } catch (Exception e) {
        }
    }

}

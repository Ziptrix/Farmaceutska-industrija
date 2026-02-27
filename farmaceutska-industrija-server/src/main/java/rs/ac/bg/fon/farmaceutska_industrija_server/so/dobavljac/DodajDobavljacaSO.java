/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
public class DodajDobavljacaSO extends ApstraktnaSO {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti tipa Dobavljac!");
        }

        if (((Dobavljac) objekat).getIme() == null) {
            throw new Exception("Ime dobavljaca nije uneto!");
        }

        if (((Dobavljac) objekat).getIme().isEmpty() || ((Dobavljac) objekat).getIme().equals("")) {
            throw new Exception("Ime dobavljaca ne sme biti prazno!");
        }

        if (((Dobavljac) objekat).getPrezime() == null) {
            throw new Exception("Prezime dobavljaca nije uneto!");
        }

        if (((Dobavljac) objekat).getPrezime().isEmpty() || ((Dobavljac) objekat).getPrezime().equals("")) {
            throw new Exception("Prezime dobavljaca ne sme biti prazno!");
        }

        if (((Dobavljac) objekat).getGrad() == null || !(((Dobavljac) objekat).getGrad() instanceof Grad)) {
            throw new Exception("Grad iz kojeg dobavljac dolazi nije prosledjen!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.dodaj((OpstaDomenskaKlasa) objekat);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.tabele.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class ModelKorisnici extends AbstractTableModel {

    List<Korisnik> korisnici;
    String[] naziviKolona = {"ID", "Ime", "Prezime", "Korisnicko ime", "Sifra"};
    Class[] klaseKolona = {Long.class, String.class, String.class, String.class, String.class,};

    public ModelKorisnici(List<Korisnik> korisnici) {
        this.korisnici = korisnici;
    }

    @Override
    public int getRowCount() {
        if (korisnici == null) {
            return 0;
        }
        return korisnici.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik = korisnici.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return korisnik.getId();
            case 1:
                return korisnik.getIme();
            case 2:
                return korisnik.getPrezime();
            case 3:
                return korisnik.getKorisnickoIme();
            case 4:
                return korisnik.getSifra();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return klaseKolona[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return naziviKolona[column];
    }

    public void dodajUListu(Korisnik korisnik) {
        korisnici.add(korisnik);
    }

}

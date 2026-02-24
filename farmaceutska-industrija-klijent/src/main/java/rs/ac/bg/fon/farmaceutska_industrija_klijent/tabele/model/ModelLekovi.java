/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.tabele.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class ModelLekovi extends AbstractTableModel {

    List<Lek> lekovi;
    String[] naziviKolona = {"Serijski broj", "Naziv", "Doziranje", "Sastav"};
    Class[] klaseKolona = {Long.class, String.class, String.class, Supstanca.class};

    public ModelLekovi(List<Lek> lekovi) {
        this.lekovi = lekovi;
    }

    @Override
    public int getRowCount() {
        if (lekovi == null) {
            return 0;
        }
        return lekovi.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lek lek = lekovi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lek.getSerijskiBroj();
            case 1:
                return lek.getNaziv();
            case 2:
                return lek.getDoziranje();
            case 3:
                return lek.getSastav();
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

    public Lek getLek(int red) {
        return lekovi.get(red);
    }

}

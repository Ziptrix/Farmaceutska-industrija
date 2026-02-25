/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.tabele.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class ModelStavkeNarudzbenice extends AbstractTableModel {

    List<StavkaNarudzbenice> stavke;
    String[] naziviKolona = {"Redni broj", "Kolicina", "Iznos", "Supstanca"};
    Class[] klaseKolona = {Long.class, Long.class, Long.class, Supstanca.class};

    public ModelStavkeNarudzbenice(List<StavkaNarudzbenice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        if (stavke == null) {
            return 0;
        }
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaNarudzbenice stavka = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRedniBroj();
            case 1:
                return stavka.getKolicinaSupstance();
            case 2:
                return stavka.getIznosStavke();
            case 3:
                return stavka.getSupstanca();
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

    public void setStavkaNarudzbenice(StavkaNarudzbenice stavka) {
        stavke.add(stavka);
    }

}

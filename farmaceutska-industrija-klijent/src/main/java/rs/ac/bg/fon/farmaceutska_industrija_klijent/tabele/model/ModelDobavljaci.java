/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.tabele.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;

/**
 *
 * @author milos
 */
public class ModelDobavljaci extends AbstractTableModel {

    private List<Dobavljac> dobavljaci;
    private String[] naziviKolona = {"ID", "Ime", "Prezime", "Grad"};
    private Class[] klaseKolona = {Long.class, String.class, String.class, Grad.class};

    public ModelDobavljaci(List<Dobavljac> dobavljaci) {
        this.dobavljaci = dobavljaci;
    }

    @Override
    public int getRowCount() {
        if (dobavljaci == null) {
            return 0;
        }
        return dobavljaci.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Dobavljac dobavljac = dobavljaci.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dobavljac.getId();
            case 1:
                return dobavljac.getIme();
            case 2:
                return dobavljac.getPrezime();
            case 3:
                return dobavljac.getGrad();
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

    public Dobavljac getDobavljac(int red) {
        return dobavljaci.get(red);
    }

}

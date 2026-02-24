/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.tabele.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 *
 * @author milos
 */
public class ModelSupstance extends AbstractTableModel {

    List<Supstanca> supstance;
    String[] naziviKolona = {"Sifra", "Naziv", "Kolicina", "Cena"};
    Class[] klaseKolona = {Long.class, String.class, Long.class, Long.class};

    public ModelSupstance(List<Supstanca> supstance) {
        this.supstance = supstance;
    }

    @Override
    public int getRowCount() {
        if (supstance == null) {
            return 0;
        }
        return supstance.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Supstanca supstanca = supstance.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return supstanca.getSifra();
            case 1:
                return supstanca.getNaziv();
            case 2:
                return supstanca.getKolicinaZaliha();
            case 3:
                return supstanca.getCena();

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

    public Supstanca getSupstanca(int red) {
        return supstance.get(red);
    }

    public void dodajSupstancu(Supstanca supstanca) {
        supstance.add(supstanca);
    }
    
    public List<Supstanca> getSupstance(){
        return supstance;
    }

}

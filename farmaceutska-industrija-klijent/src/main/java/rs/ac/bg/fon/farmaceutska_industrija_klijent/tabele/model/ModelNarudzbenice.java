/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.tabele.model;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;

/**
 *
 * @author milos
 */
public class ModelNarudzbenice extends AbstractTableModel {

    List<Narudzbenica> narudzbenice;
    String[] naziviKolona = {"Sifra", "Datum kreiranja", "Ukupan iznos"};
    Class[] klaseKolona = {Long.class, LocalDate.class, Long.class};

    public ModelNarudzbenice(List<Narudzbenica> narudzbenice) {
        this.narudzbenice = narudzbenice;
    }

    @Override
    public int getRowCount() {
        if (narudzbenice == null) {
            return 0;
        }
        return narudzbenice.size();
    }

    @Override
    public int getColumnCount() {
        return naziviKolona.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Narudzbenica narudzbenica = narudzbenice.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return narudzbenica.getSifra();
            case 1:
                return narudzbenica.getDatum();
            case 2:
                return narudzbenica.getUkupanIznos();
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

    public Narudzbenica getNarudzbenica(int red) {
        return narudzbenice.get(red);
    }

}

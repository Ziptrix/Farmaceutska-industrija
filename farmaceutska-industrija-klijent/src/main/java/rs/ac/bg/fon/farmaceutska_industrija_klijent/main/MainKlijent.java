/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_klijent.main;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import rs.ac.bg.fon.farmaceutska_industrija_klijent.forme.FrmPrijava;

/**
 *
 * @author milos
 */
public class MainKlijent {

    public static void main(String[] args) {
        JDialog dijalog = new JDialog((JFrame) null, "Prijava", true);
        JPanel panel = new FrmPrijava();
        dijalog.add(panel);
        dijalog.pack();
        dijalog.setLocationRelativeTo(null);
        dijalog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dijalog.setVisible(true);
    }
}

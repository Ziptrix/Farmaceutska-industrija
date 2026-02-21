/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farnaceutska_industrija_zajednicki.komunikacija;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author milos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OdgovorServera {

    private Object odgovor;
    private Exception izuzetak;

    @Override
    public String toString() {
        if (izuzetak == null) {
            return "Odgovor " + odgovor + "koji treba poslati klijentu nema gresaka!";
        } else {
            return "Odgovor " + odgovor + "se nece poslati klijentu jer se desila greska: " + izuzetak;
        }
    }

}

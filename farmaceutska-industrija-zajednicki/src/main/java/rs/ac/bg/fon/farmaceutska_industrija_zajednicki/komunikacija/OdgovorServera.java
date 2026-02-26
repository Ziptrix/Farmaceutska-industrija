/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija;

import java.io.Serializable;
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
public class OdgovorServera implements Serializable {

    private Object rezultat;
    private Exception izuzetak;

    @Override
    public String toString() {
        if (izuzetak == null) {
            return "Odgovor " + rezultat + " koji treba poslati klijentu nema gresaka!";
        } else {
            return "Odgovor " + rezultat + " se nece poslati klijentu jer se desila greska: " + izuzetak;
        }
    }

}

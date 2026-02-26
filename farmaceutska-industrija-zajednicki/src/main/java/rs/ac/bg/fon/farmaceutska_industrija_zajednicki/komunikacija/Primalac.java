/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import lombok.AllArgsConstructor;

/**
 *
 * @author milos
 */

@AllArgsConstructor
public class Primalac {
    private Socket soket;
    
    public Object primiObjekat() throws Exception{
        try {
            ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Desila se greska prilikom prijema odgovora!");
        }
    }
}

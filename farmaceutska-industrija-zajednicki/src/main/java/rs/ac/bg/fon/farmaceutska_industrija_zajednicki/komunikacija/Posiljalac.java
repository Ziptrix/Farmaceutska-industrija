/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.komunikacija;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import lombok.AllArgsConstructor;

/**
 *
 * @author milos
 */

@AllArgsConstructor
public class Posiljalac {
    private Socket soket;
    
    public void posaljiObjekat(Object objekat) throws Exception{
        try {
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(objekat);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Desila se greska prilikom slanja odgovora klijentu!");
        }
    }
}

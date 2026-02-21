/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farnaceutska_industrija_zajednicki.komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author milos
 */
public class Primalac {
    private Socket socket;
    
    public Object primiObjekat() throws Exception{
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Desila se greska prilikom prijema odgovora od servera!");
        }
    }
}

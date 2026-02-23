/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.niti;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class ServerskaNit extends Thread {

    private ServerSocket serverskiSoket;

    private List<KlijentskiZahteviNit> klijenti;

    public ServerskaNit() throws Exception {
        serverskiSoket = new ServerSocket(9090);
        klijenti = new ArrayList<>();
    }
    
    public ServerSocket getServerskiSoket() {
        return serverskiSoket;
    }

    @Override
    public void run() {
        while (!serverskiSoket.isClosed()) {            
            try {
                System.out.println("Cekanje klijenta...");
                Socket soket = serverskiSoket.accept();
                System.out.println("Uspesno povezivanje sa klijentom!");
                
                KlijentskiZahteviNit nit = new KlijentskiZahteviNit(soket);
                nit.start();
                klijenti.add(nit);
                System.out.println("Klijent se uspesno povezao!");
            } catch (Exception e) {
            }
        }
        prekiniSveNiti();
    }

    private void prekiniSveNiti() {
        for (KlijentskiZahteviNit klijent : klijenti) {
            try {
                klijent.getSoket().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private List<Korisnik> vratiSveKorisnike(){
        List<Korisnik> korisnici = new ArrayList<>();
        for (KlijentskiZahteviNit klijent : klijenti) {
            korisnici.add(klijent.getKorisnika());
        }
        return korisnici;
    }

}

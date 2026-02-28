/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.niti;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.forme.FrmServer;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;

/**
 *
 * @author milos
 */
public class ServerskaNit extends Thread {

    private ServerSocket serverskiSoket;
    private List<KlijentskiZahteviNit> klijenti;
    private List<Korisnik> prijavljeniKorisnici;
    private FrmServer forma;

    public ServerskaNit(FrmServer forma) throws Exception {
        serverskiSoket = new ServerSocket(9090);
        klijenti = new ArrayList<>();
        prijavljeniKorisnici = new ArrayList<>();
        this.forma = forma;
    }

    public ServerSocket getServerskiSoket() {
        return serverskiSoket;
    }

    public List<Korisnik> getPrijavljeniKorisnici() {
        return prijavljeniKorisnici;
    }

    @Override
    public void run() {
        while (!serverskiSoket.isClosed()) {
            try {
                System.out.println("Cekanje klijenta...");
                Socket soket = serverskiSoket.accept();
                System.out.println("Uspesno povezivanje sa klijentom!");

                KlijentskiZahteviNit nit = new KlijentskiZahteviNit(soket, this);
                nit.start();

                System.out.println("Klijent se uspesno povezao!");
            } catch (Exception e) {
            }
        }
        prekiniSveNiti();
    }

    public void prijavaKorisnika(Korisnik korisnik, KlijentskiZahteviNit nit) throws Exception {
        if (prijavljeniKorisnici.stream().anyMatch(k -> k.equals(korisnik))) {
            throw new Exception("Korisnik sa unetim kredencijalima je vec prijavljen na sistem!");
        }

        prijavljeniKorisnici.add(korisnik);

        forma.prikaziTabelu();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try ( FileWriter out = new FileWriter("prijavljeni.json")) {
            gson.toJson(prijavljeniKorisnici, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        klijenti.add(nit);
        System.out.println("Korisnici " + prijavljeniKorisnici);
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

    public void odjaviKorisnika(Korisnik korisnik, KlijentskiZahteviNit nit) {
        if (korisnik != null) {
            prijavljeniKorisnici.remove(korisnik);
            forma.prikaziTabelu();
        }
        klijenti.remove(nit);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try ( FileWriter out = new FileWriter("prijavljeni.json")) {
            gson.toJson(prijavljeniKorisnici, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Korisnik je odjavljen " + korisnik);
    }

    public void ocistiJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try ( FileWriter out = new FileWriter("prijavljeni.json")) {
            gson.toJson(new ArrayList<>(), out); // upis prazne liste
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

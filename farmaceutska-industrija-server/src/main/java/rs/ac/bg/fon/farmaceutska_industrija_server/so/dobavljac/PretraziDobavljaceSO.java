/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.dobavljac;

import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Dobavljac;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 * Sistemska operacija koja pretražuje dobavljače u bazi podataka prema zadatom kriterijumu.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje pretrage.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Dobavljac}.
 * <p>
 * Rezultat pretrage se čuva u listi {@link #dobavljaci}.
 * Ako ne postoji nijedan dobavljač koji odgovara kriterijumima, baca se izuzetak.
 * 
 * @author milos
 */
@Getter
public class PretraziDobavljaceSO extends ApstraktnaSO {

    /**
     * Lista dobavljača koji odgovaraju kriterijumu pretrage.
     */
    List<Dobavljac> dobavljaci;

     /**
     * Proverava preduslove pre pretrage dobavljača.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Dobavljac}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Dobavljac} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Dobavljac)) {
            throw new Exception("Prosledjen objekat mora biti tipa Dobavljac!");
        }
    }

    /**
     * Izvršava pretragu dobavljača u bazi podataka prema kriterijumu za pretragu,
     * a to je ime dobavljača.
     * <p>
     * Pretvara prosleđeni objekat u {@link Dobavljac} i koristi {@link #broker}
     * za pretragu. Rezultat se mapira u listu {@link Dobavljac} i čuva u {@link #dobavljaci}.
     * <p>
     * Ako lista dobavljača bude prazna, baca se izuzetak.
     *
     * @param objekat objekat tipa {@link Dobavljac} koji sadrži kriterijume pretrage.
     * @param kljuc   ključ koji se koristi za filtriranje u pretrazi.
     * 
     * @throws java.lang.Exception ako ne postoji nijedan dobavljač koji odgovara kriterijumima
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.pretrazi((Dobavljac) objekat, kljuc);

        dobavljaci = rezultat.stream()
                .map(op -> (Dobavljac) op)
                .toList();
        
        if(dobavljaci.isEmpty()){
            throw new Exception("Sistem ne moze da pronadje dobavljace koji odgovaraju!");
        }
    }

}

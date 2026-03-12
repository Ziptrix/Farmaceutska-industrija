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
 * Sistemska operacija koja učitava sve dobavljače iz baze podataka.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i učitavanje svih dobavljača.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Dobavljac}.
 * <p>
 * Rezultat učitavanja se čuva u listi {@link #dobavljaci}.
 * Ako lista bude prazna, baca se izuzetak.
 * 
 * @author milos
 */
@Getter
public class UcitajSveDobavljaceSO extends ApstraktnaSO {

    /**
     * Lista svih dobavljača učitanih iz baze podataka.
     */
    List<Dobavljac> dobavljaci;

    /**
     * Proverava preduslove pre učitavanja dobavljača.
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
     * Izvršava učitavanje svih dobavljača iz baze podataka.
     * <p>
     * Pretvara prosleđeni objekat u {@link Dobavljac} i koristi {@link #broker}
     * za učitavanje svih dobavljača. Rezultat se mapira u listu {@link Dobavljac}
     * i čuva u {@link #dobavljaci}.
     * <p>
     * Ako lista dobavljača bude prazna, baca se izuzetak.
     *
     * @param objekat objekat tipa {@link Dobavljac} koji se koristi za učitavanje (može biti prazan).
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako lista učitanih dobavljača bude prazna.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve((Dobavljac) objekat);

        dobavljaci = rezultat.stream()
                .map(op -> (Dobavljac) op)
                .toList();

        if (dobavljaci.isEmpty()) {
            throw new Exception("Lista ucitanih dobavljaca je prazna pa se forma za dodavanje narudzbenice ne otvara!");
        }
    }

}

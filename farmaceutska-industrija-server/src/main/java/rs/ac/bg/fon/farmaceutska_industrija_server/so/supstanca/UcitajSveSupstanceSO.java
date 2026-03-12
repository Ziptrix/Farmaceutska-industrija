/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.supstanca;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 * Sistemska operacija za učitavanje svih supstanci iz sistema.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije učitavanja.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Supstanca}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Učitavanje svih supstanci iz baze podataka preko {@link #broker}.</li>
 *   <li>Skladištenje rezultata u listu {@link #supstance}.</li>
 * </ul>
 * @author milos
 */
@Getter
public class UcitajSveSupstanceSO extends ApstraktnaSO {

    /**
     * Lista svih supstanci učitanih iz baze
     */
    List<Supstanca> supstance;

    /**
     * Proverava preduslove pre izvršenja učitavanja supstanci.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Supstanca}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Supstanca} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Supstanca)) {
            throw new Exception("Prosledjen objekat mora biti tipa Supstanca!");
        }
    }

    /**
     * Izvršava operaciju učitavanja svih supstanci iz baze.
     * <p>
     * Rezultat učitavanja se skladišti u listu {@link #supstance}.
     *
     * @param objekat objekat tipa {@link Supstanca} (koristi se za tip podataka).
     * @param kljuc   ključ koji se koristi za eventualno filtriranje (nije korišćen u ovoj SO).
     * 
     * @throws java.lang.Exception ako lista učitanih supstanci bude prazna.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        supstance = broker.ucitajSve((Supstanca) objekat).stream()
                .map(opsta -> (Supstanca) opsta)
                .collect(Collectors.toList());

        if (supstance.isEmpty()) {
            throw new Exception("Lista ucitanih supstanci je prazna pa se forma za dodavanje leka ne otvara!");
        }
    }

}

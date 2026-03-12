/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.grad;

import java.util.List;
import lombok.Getter;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Grad;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 * Sistemska operacija koja učitava sve gradove iz baze podataka.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i učitavanje svih gradova.
 * </p>
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Grad}.
 * </p>
 * <p>
 * Rezultat učitavanja se čuva u listi {@link #gradovi}.
 * Ako lista bude prazna, baca se izuzetak.
 * </p>
 * 
 * @author milos
 */
@Getter
public class UcitajSveGradoveSO extends ApstraktnaSO {

    /**
     * Lista svih gradova učitanih iz baze podataka.
     */
    List<Grad> gradovi;

    /**
     * Proverava preduslove pre učitavanja gradova.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Grad}
     * ili je {@code null}.
     * </p>
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Grad} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Grad)) {
            throw new Exception("Prosledjen objekat mora biti tipa Grad!");
        }
    }

    /**
     * Izvršava učitavanje svih gradova iz baze podataka.
     * <p>
     * Pretvara prosleđeni objekat u {@link Grad} i koristi {@link #broker}
     * za učitavanje svih gradova. Rezultat se mapira u listu {@link Grad}
     * i čuva u {@link #gradovi}.
     * </p>
     * <p>
     * Ako lista gradova bude prazna, baca se izuzetak.
     * </p>
     *
     * @param objekat objekat tipa {@link Grad} koji se koristi za učitavanje (može biti prazan).
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako lista učitanih gradova bude prazna.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = broker.ucitajSve((Grad) objekat);

        gradovi = rezultat.stream()
                .map(op -> (Grad) op)
                .toList();

        if (gradovi.isEmpty()) {
            throw new Exception("Lista ucitanih gradova je prazna pa se forma za dodavanje dobavljaca ne otvara!");
        }
    }

}

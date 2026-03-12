/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.lek;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Lek;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.SupstancaLek;

/**
 * Sistemska operacija za brisanje postojećeg leka iz sistema.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije brisanja leka.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Lek}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Brisanje svih supstanci povezanih sa lekom u tabeli {@link SupstancaLek}.</li>
 *   <li>Brisanje samog leka iz baze.</li>
 * </ul>
 * 
 * @author milos
 */
public class ObrisiLekSO extends ApstraktnaSO{

    /**
     * Proverava preduslove pre izvršenja brisanja leka.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Lek}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Lek} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Lek)){
            throw new Exception("Prosledjen objekat mora biti tipa Lek!");
        }
    }

    /**
     * Izvršava operaciju brisanja leka u sistemu.
     * <p>
     * Operacija prvo briše sve supstance povezane sa lekom u tabeli {@link SupstancaLek},
     * a zatim briše sam lek iz baze.
     *
     * @param objekat objekat tipa {@link Lek} koji se briše.
     * @param kljuc   ključ koji se koristi (nije obavezan za ovu operaciju).
     * 
     * @throws java.lang.Exception ako dođe do greške pri brisanju leka ili supstanci.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Lek lek = (Lek) objekat;
        SupstancaLek supstancaLek = new SupstancaLek();
        supstancaLek.setLek(lek);
        
        broker.obrisi(supstancaLek);
        broker.obrisi(lek);
    }
    
}

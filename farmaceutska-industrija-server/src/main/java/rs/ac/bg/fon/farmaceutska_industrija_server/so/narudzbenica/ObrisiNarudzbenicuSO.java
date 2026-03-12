/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;

/**
 * Sistemska operacija za brisanje postojeće narudžbenice iz sistema.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije brisanja narudžbenice.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Narudzbenica}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Brisanje svih stavki narudžbenice iz baze podataka.</li>
 *   <li>Brisanje same narudžbenice iz baze.</li>
 * </ul>
 * 
 * @author milos
 */
public class ObrisiNarudzbenicuSO extends ApstraktnaSO {

    /**
     * Proverava preduslove pre izvršenja brisanja narudžbenice.
     * <p>
     * Ova metoda baca izuzetak ako prosleđeni objekat nije instanca {@link Narudzbenica}
     * ili je {@code null}.
     *
     * @param objekat objekat koji se proverava.
     * 
     * @throws java.lang.Exception ako objekat nije tipa {@link Narudzbenica} ili je {@code null}.
     */
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Narudzbenica)) {
            throw new Exception("Prosledjen objekat mora biti tipa Narudzbenica!");
        }
    }

    /**
     * Izvršava operaciju brisanja narudžbenice u sistemu.
     * <p>
     * Operacija prvo briše sve stavke narudžbenice, a potom samu narudžbenicu iz baze podataka.
     *
     * @param objekat objekat tipa {@link Narudzbenica} koji se briše.
     * @param kljuc   ključ koji se koristi za eventualno filtriranje (nije korišćen u ovoj SO).
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom brisanja stavki ili narudžbenice.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Narudzbenica narudzbenica = (Narudzbenica) objekat;
        
        for (StavkaNarudzbenice sn : narudzbenica.getListaStavki()) {
            broker.obrisi(sn);
        }
        
        broker.obrisi(narudzbenica);
    }

}

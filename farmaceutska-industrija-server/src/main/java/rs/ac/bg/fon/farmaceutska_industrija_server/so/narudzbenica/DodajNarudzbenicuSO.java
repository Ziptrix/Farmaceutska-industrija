/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.so.narudzbenica;

import java.util.List;
import rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum.DBBrokerOpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_server.so.ApstraktnaSO;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Narudzbenica;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.StavkaNarudzbenice;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Supstanca;

/**
 * Sistemska operacija za dodavanje nove narudžbenice u sistem.
 * <p>
 * Nasleđuje {@link ApstraktnaSO} i implementira konkretne metode
 * za proveru preduslova i izvršenje operacije dodavanja narudžbenice.
 * <p>
 * Preduslovi: prosleđeni objekat mora biti instanca klase {@link Narudzbenica}.
 * <p>
 * Izvršenje operacije uključuje:
 * <ul>
 *   <li>Dodavanje narudžbenice u bazu podataka.</li>
 *   <li>Dodavanje svih stavki narudžbenice u bazu.</li>
 *   <li>Ažuriranje količina supstanci u bazi prema količinama korišćenim u stavkama narudžbenice.</li>
 * </ul>
 * 
 * @author milos
 */
public class DodajNarudzbenicuSO extends ApstraktnaSO {

    /**
     * Vraća instancu brokera baze podataka koji se koristi u ovoj SO.
     *
     * @return instanca klase {@link DBBrokerOpstaDomenskaKlasa}
     */
    public DBBrokerOpstaDomenskaKlasa getBroker() {
        return broker;
    }

    /**
     * Proverava preduslove pre izvršenja dodavanja narudžbenice.
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
     * Izvršava operaciju dodavanja narudžbenice u sistem.
     * <p>
     * Operacija dodaje narudžbenicu i sve njene stavke u bazu podataka.
     * Takođe ažurira količine supstanci u bazi prema količinama korišćenim u stavkama narudžbenice.
     *
     * @param objekat objekat tipa {@link Narudzbenica} koji sadrži podatke za dodavanje.
     * @param kljuc   ključ koji se koristi za eventualno filtriranje (nije korišćen u ovoj SO).
     * 
     * @throws java.lang.Exception ako dođe do greške pri dodavanju narudžbenice, stavki ili ažuriranju supstanci.
     */
    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Narudzbenica narudzbenica = (Narudzbenica) objekat;
        broker.dodaj(narudzbenica);

        List<OpstaDomenskaKlasa> supstanceIzBaze = broker.ucitajSve(new Supstanca());

        List<Supstanca> supstance = supstanceIzBaze.stream()
                .map(odk -> (Supstanca) odk)
                .toList();

        for (StavkaNarudzbenice stavkaNarudzbenice : narudzbenica.getListaStavki()) {
            broker.dodaj(stavkaNarudzbenice);

            for (Supstanca supstanca : supstance) {
                if (stavkaNarudzbenice.getSupstanca().equals(supstanca)) {
                    supstanca.setKolicinaZaliha(supstanca.getKolicinaZaliha() + stavkaNarudzbenice.getKolicinaSupstance());
                    broker.izmeni(supstanca);
                    break;
                }
            }
        }
    }

}

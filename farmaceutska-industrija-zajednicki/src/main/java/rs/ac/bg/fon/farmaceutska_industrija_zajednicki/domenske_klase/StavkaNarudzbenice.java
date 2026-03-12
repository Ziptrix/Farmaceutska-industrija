/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Predstavlja jednu stavku narudžbenice u kojoj se nalazi nova količina jedne supstance (objekat klase {@link Supstanca}).
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaNarudzbenice implements OpstaDomenskaKlasa {

    /**
     * Redni broj stavke narudžbenice kao Long.
     */
    private Long redniBroj;
    
    /**
     * Narudžbenica u kojoj se stavka narudžbenice nalazi kao {@link Narudzbenica}.
     */
    private Narudzbenica narudzbenica;
    
    /**
     * Količina poručene supstance stavke narudžbenice kao Long.
     */
    private Long kolicinaSupstance;
    
    /**
     * Iznos jedne stavke narudžbenice kao Long.
     */
    private Long iznosStavke;
    
    /**
     * Supstanca čija se nova količina poručuje u stavci narudžbenice kao {@link Supstanca}.
     */
    private Supstanca supstanca;

    /**
     * Postavlja novi redni broj stavke narudžbenice.
     * Uneti redni broj ne sme biti null, manji od ili jednak nuli.
     *
     * @param redniBroj Redni broj stavke narudžbenice kao Long.
     *
     * @throws java.lang.Exception Ako je uneti redni broj null, manji od ili jednak nuli.
     */
    public void setRedniBroj(Long redniBroj) throws Exception {
        if (redniBroj == null) {
            throw new Exception("Redni broj stavke narudzbenice mora da postoji!");
        }
        if (redniBroj <= 0) {
            throw new Exception("Redni broj stavke narudzbenice mora biti pozitivan!");
        }
        this.redniBroj = redniBroj;
    }

    /**
     * Postavlja novu narudžbenicu u kojoj se stavka narudžbenice nalazi.
     * Uneta narudžbenica ne sme biti null.
     *
     * @param narudzbenica Narudžbenica u kojoj se stavka narudžbenice nalazi kao {@link Narudzbenica}.
     *
     * @throws java.lang.Exception Ako je uneta narudzbenica null.
     */
    public void setNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        if (narudzbenica == null || !(narudzbenica instanceof Narudzbenica)) {
            throw new Exception("Narudzbenica u kojoj se nalazi njena stavka mora da postoji!");
        }
        this.narudzbenica = narudzbenica;
    }

    /**
     * Postavlja novu količinu poručene supstance stavke narudžbenice.
     * Uneta količina supstance ne sme biti null, manja od ili jednaka nuli.
     *
     * @param kolicinaSupstance Količina poručene supstance stavke narudžbenice kao Long.
     *
     * @throws java.lang.Exception Ako je uneta količina supstance null, manja od ili jednaka nuli.
     */
    public void setKolicinaSupstance(Long kolicinaSupstance) throws Exception {
        if (kolicinaSupstance == null) {
            throw new Exception("Kolicina supstance stavke narudzbenice mora da postoji!");
        }
        if (kolicinaSupstance <= 0) {
            throw new Exception("Kolicina supstance stavke narudzbenice mora biti pozitivna!");
        }
        this.kolicinaSupstance = kolicinaSupstance;
    }

    /**
     * Postavlja novi iznos jedne stavke narudžbenice.
     * Uneti iznos stavke ne sme biti null, manji od ili jednak nuli.
     *
     * @param iznosStavke Iznos jedne stavke narudžbenice kao Long.
     *
     * @throws java.lang.Exception Ako je uneti iznos stavke null, manji od ili jednak nuli.
     */
    public void setIznosStavke(Long iznosStavke) throws Exception {
        if (iznosStavke == null) {
            throw new Exception("Iznos stavke narudzbenice mora da postoji!");
        }
        if (iznosStavke <= 0) {
            throw new Exception("Iznos stavke narudzbenice mora biti pozitivna!");
        }
        this.iznosStavke = iznosStavke;
    }

    /**
     * Postavlja novu supstancu čija se nova količina poručuje u  stavci narudžbenice.
     * Uneta supstanca ne sme biti null.
     *
     * @param supstanca Supstanca čija se nova količina poručuje u stavci narudžbenice kao {@link Supstanca}.
     *
     * @throws java.lang.Exception Ako je uneta supstanca null.
     */
    public void setSupstanca(Supstanca supstanca) throws Exception, Exception {
        if (supstanca == null || !(supstanca instanceof Supstanca)) {
            throw new Exception("Supstanca koja se nalazi u stavci mora da postoji!");
        }
        this.supstanca = supstanca;
    }

    /**
     * Vraća String sa količinom poručene supstance i supstancom stavke narudžbenice.
     * 
     * @return String sa količinom poručene supstance i supstancom u formatu
     * ##### jedinica supstance ##### odnosno, na primer
     * 10 jedinica supstance Paracetamol
     */
    @Override
    public String toString() {
        return kolicinaSupstance + " jedinica supstance " + supstanca;
    }

    @Override
    public void postaviId(long id) {
        this.redniBroj = id;
    }

    @Override
    public Long vratiId() {
        return redniBroj;
    }

    @Override
    public String vratiNazivTabele() {
        return "item";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "order_number, id_po, quantity, amount, substance";
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return redniBroj + ", " + narudzbenica.getSifra() + ", " + kolicinaSupstance + ", " + iznosStavke + ", " + supstanca.getSifra();
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception {
        ps.setLong(1, narudzbenica.getSifra());
    }

    @Override
    public String vratiJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiUslovZaDelete() {
        return "WHERE id_po = ?";
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

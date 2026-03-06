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
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaNarudzbenice implements OpstaDomenskaKlasa {

    private Long redniBroj;
    private Narudzbenica narudzbenica;
    private Long kolicinaSupstance;
    private Long iznosStavke;
    private Supstanca supstanca;

    public void setRedniBroj(Long redniBroj) throws Exception {
        if (redniBroj == null) {
            throw new Exception("Redni broj stavke narudzbenice mora da postoji!");
        }
        if (redniBroj <= 0) {
            throw new Exception("Redni broj stavke narudzbenice mora biti pozitivan!");
        }
        this.redniBroj = redniBroj;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) throws Exception {
        if (narudzbenica == null || !(narudzbenica instanceof Narudzbenica)) {
            throw new Exception("Narudzbenica u kojoj se nalazi njena stavka mora da postoji!");
        }
        this.narudzbenica = narudzbenica;
    }

    public void setKolicinaSupstance(Long kolicinaSupstance) throws Exception {
        if (kolicinaSupstance == null) {
            throw new Exception("Kolicina supstance stavke narudzbenice mora da postoji!");
        }
        if (kolicinaSupstance <= 0) {
            throw new Exception("Kolicina supstance stavke narudzbenice mora biti pozitivna!");
        }
        this.kolicinaSupstance = kolicinaSupstance;
    }

    public void setIznosStavke(Long iznosStavke) throws Exception {
        if (iznosStavke == null) {
            throw new Exception("Iznos stavke narudzbenice mora da postoji!");
        }
        if (iznosStavke <= 0) {
            throw new Exception("Iznos stavke narudzbenice mora biti pozitivna!");
        }
        this.iznosStavke = iznosStavke;
    }

    public void setSupstanca(Supstanca supstanca) throws Exception, Exception {
        if (supstanca == null || !(supstanca instanceof Supstanca)) {
            throw new Exception("Supstanca koja se nalazi u stavci mora da postoji!");
        }
        this.supstanca = supstanca;
    }

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

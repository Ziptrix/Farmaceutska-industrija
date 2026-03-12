/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Predstavlja grad iz kojeg {@link Dobavljac} dolazi.
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 * 
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Grad implements OpstaDomenskaKlasa {

    /**
     * Poštanski broj grada kao Long.
     */
    private Long postanskiBroj;
    
    /**
     * Naziv grada kao String.
     */
    private String naziv;

    /**
     * Postavlja novi poštanski broj grada.
     * Uneti poštanski broj ne sme biti null, manji od ili jednak nuli.
     * 
     * @param postanskiBroj Poštanski broj grada kao Long.
     * 
     * @throws java.lang.Exception Ako je uneti poštanski broj null, manji od ili jednak nuli.
     */
    public void setPostanskiBroj(Long postanskiBroj) throws Exception {
        if (postanskiBroj == null) {
            throw new Exception("Postanski broj grada mora da postoji!");
        }
        if (postanskiBroj <= 0) {
            throw new Exception("Postanski broj grada mora biti pozitivan!");
        }
        this.postanskiBroj = postanskiBroj;
    }

    /**
     * Postavlja novi naziv grada.
     * Uneti naziv ne sme biti null ili prazan.
     * 
     * @param naziv Naziv grada kao String.
     * 
     * @throws java.lang.Exception Ako je uneti naziv null ili prazan.
     */
    public void setNaziv(String naziv) throws Exception {
        if (naziv == null) {
            throw new Exception("Naziv grada mora da postoji!");
        }
        if (naziv.isEmpty() || naziv.equals("")) {
            throw new Exception("Naziv grada ne sme biti prazan!");
        }
        this.naziv = naziv;
    }

    /**
     * Vraća String sa svim podacima o gradu.
     * 
     * @return String sa svim podacima u formatu
     * #####, ##### odnosno, na primer:
     * 11000, Beograd
     */
    @Override
    public String toString() {
        return postanskiBroj + ", " + naziv;
    }

    /**
     * Vraća hash code koji nije zasnovan na atributima grada
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dva grada prema poštanskom broju.
     * 
     * @param obj Drugi grad sa kojim se poredi.
     * 
     * @return
     * <ul>
     * <li>true - ako je uneti objekat različit od null, ako je klase {@link Grad}
     * i ako je poštanski broj isti kao i kod prvog grada.</li>
     * <li>false - u svim ostalim slučajevima.
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grad other = (Grad) obj;
        return Objects.equals(this.postanskiBroj, other.postanskiBroj);
    }

    @Override
    public String vratiNazivTabele() {
        return "city";
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lista = new ArrayList<>();

        while (rs.next()) {
            Grad g = new Grad();
            g.setPostanskiBroj(rs.getLong("zip_code"));
            g.setNaziv(rs.getString("name"));
            lista.add(g);
        }

        return lista;
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        return "zip_code, name";
    }

    @Override
    public String vratiUslovZaDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Long vratiId() {
        return postanskiBroj;
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

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
 * Predstavlja supstancu koja je potrebna za pravljenje nekog novog leka (objekat klase {@link Lek}).
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supstanca implements OpstaDomenskaKlasa {

    /**
     * Šifra supstance kao Long.
     */
    private Long sifra;

    /**
     * Naziv supstance kao String.
     */
    private String naziv;

    /**
     * Količina zaliha supstance kao Long.
     */
    private Long kolicinaZaliha;

    /**
     * Cena supstance kao Long.
     */
    private Long cena;

    /**
     * Postavlja novu šifru supstance.
     * Uneta šifra ne sme biti null, manja od ili jednaka nuli.
     *
     * @param sifra Šifra supstance kao Long.
     *
     * @throws java.lang.Exception Ako je uneta šifra null, manja od ili jednaka nuli.
     */
    public void setSifra(Long sifra) throws Exception {
        if (sifra == null) {
            throw new Exception("Sifra supstance mora da postoji!");
        }
        if (sifra <= 0) {
            throw new Exception("Sifra supstance mora biti pozitivna!");
        }
        this.sifra = sifra;
    }

    /**
     * Postavlja novi naziv supstance.
     * Uneti naziv ne sme biti null ili prazan.
     *
     * @param naziv Naziv supstance kao String.
     *
     * @throws java.lang.Exception Ako je uneti naziv null ili prazan.
     */
    public void setNaziv(String naziv) throws Exception {
        if (naziv == null) {
            throw new Exception("Naziv supstance mora da postoji!");
        }
        if (naziv.isEmpty() || naziv.equals("")) {
            throw new Exception("Sifra supstance ne sme biti prazna!");
        }
        this.naziv = naziv;
    }

    /**
     * Postavlja novu količinu zaliha supstance.
     * Uneta količina zaliha ne sme biti null, manja od ili jednaka nuli.
     *
     * @param kolicinaZaliha Količina zaliha supstance kao Long.
     *
     * @throws java.lang.Exception Ako je uneta količina zaliha null, manja od ili jednaka nuli.
     */
    public void setKolicinaZaliha(Long kolicinaZaliha) throws Exception {
        if (kolicinaZaliha == null) {
            throw new Exception("Kolicina supstance mora da postoji!");
        }
        if (kolicinaZaliha <= 0) {
            throw new Exception("Kolicina supstance mora biti pozitivna!");
        }
        this.kolicinaZaliha = kolicinaZaliha;
    }

    /**
     * Postavlja novu cenu supstance.
     * Uneta cena ne sme biti null, manja od ili jednaka nuli.
     *
     * @param cena CEna supstance kao Long.
     *
     * @throws java.lang.Exception Ako je uneta cena null, manja od ili jednaka nuli.
     */
    public void setCena(Long cena) throws Exception {
        if (cena == null) {
            throw new Exception("Cena supstance mora da postoji!");
        }
        if (cena <= 0) {
            throw new Exception("Cena supstance mora biti pozitivna!");
        }
        this.cena = cena;
    }

    /**
     * Vraća String sa nazivom i količinom supstance.
     *
     * @return String sa nazivom i količinom u formatu
     * ##### , kolicina: ##### odnosno, na primer
     * Paracetamol, kolicina: 100
     */
    @Override
    public String toString() {
        return naziv + ", kolicina: " + kolicinaZaliha;
    }

    /**
     * Vraća String sa količinom i cenom supstance.
     *
     * @return String sa količinom i cenom u formatu
     * Na stanju: #####, po ceni od: ##### odnosno, na primer
     * Na stanju: 100, po ceni od: 10
     */
    public String toStringZaliheCena() {
        return "Na stanju: " + kolicinaZaliha + ", po ceni od: " + cena;
    }

    /**
     * Vraća hash code koji nije zasnovan na atributima supstance
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Poredi dve supstance prema šifri.
     * 
     * @param obj Druga supstanca sa kojim se poredi.
     * 
     * @return
     * <ul>
     * <li>true - ako je uneti objekat različit od null, ako je klase {@link Supstanca}
     * i ako je šifra ista kao i kod prve supstance.</li>
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
        final Supstanca other = (Supstanca) obj;
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public void postaviId(long id) {
        this.sifra = id;
    }

    @Override
    public Long vratiId() {
        return sifra;
    }

    @Override
    public String vratiNazivTabele() {
        return "substance s";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "s.code";
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
    public String vratiVrednostiSelectUpita() {
        return "s.code, s.name, s.quantity, s.price";
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        return "quantity = " + kolicinaZaliha;
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lista = new ArrayList<>();

        while (rs.next()) {
            Supstanca s = new Supstanca();
            s.setSifra(rs.getLong("s.code"));
            s.setNaziv(rs.getString("s.name"));
            s.setKolicinaZaliha(rs.getLong("s.quantity"));
            s.setCena(rs.getLong("s.price"));
            lista.add(s);
        }

        return lista;
    }

    @Override
    public String vratiUslovZaDelete() {
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

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
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Supstanca implements OpstaDomenskaKlasa {

    private Long sifra;
    private String naziv;
    private Long kolicinaZaliha;
    private Long cena;

    public void setSifra(Long sifra) throws Exception {
        if(sifra == null){
            throw new Exception("Sifra supstance mora da postoji!");
        }
        if(sifra <= 0){
            throw new Exception("Sifra supstance mora biti pozitivna!");
        }
        this.sifra = sifra;
    }

    public void setNaziv(String naziv) throws Exception {
        if(naziv == null){
            throw new Exception("Naziv supstance mora da postoji!");
        }
        if(naziv.isEmpty() || naziv.equals("")){
            throw new Exception("Sifra supstance ne sme biti prazna!");
        }
        this.naziv = naziv;
    }

    public void setKolicinaZaliha(Long kolicinaZaliha) throws Exception {
        if(kolicinaZaliha == null){
            throw new Exception("Kolicina supstance mora da postoji!");
        }
        if(kolicinaZaliha <= 0){
            throw new Exception("Kolicina supstance mora biti pozitivna!");
        }
        this.kolicinaZaliha = kolicinaZaliha;
    }

    public void setCena(Long cena) throws Exception {
        if(cena == null){
            throw new Exception("Cena supstance mora da postoji!");
        }
        if(cena <= 0){
            throw new Exception("Cena supstance mora biti pozitivna!");
        }
        this.cena = cena;
    }

    @Override
    public String toString() {
        return naziv + ", kolicina: " + kolicinaZaliha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

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

    public String toStringZaliheCena() {
        return "Na stanju: " + kolicinaZaliha + ", po ceni od: " + cena;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class Korisnik implements OpstaDomenskaKlasa {

    private Long id;
    private String korisnickoIme;
    private String sifra;
    private String ime;
    private String prezime;

    public Korisnik(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public void setId(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID korisnika mora da postoji!");
        }
        if (id <= 0) {
            throw new Exception("ID korisnika mora biti pozitivan!");
        }
        this.id = id;
    }

    public void setKorisnickoIme(String korisnickoIme) throws Exception {
        if (korisnickoIme == null) {
            throw new Exception("Korisnicko ime korisnika mora da postoji!");
        }
        if (korisnickoIme.isEmpty() || korisnickoIme.equals("")) {
            throw new Exception("Korisnicko ime korisnika ne sme biti prazno!");
        }
        this.korisnickoIme = korisnickoIme;
    }

    public void setSifra(String sifra) throws Exception {
        if (sifra == null) {
            throw new Exception("Sifra korisnika mora da postoji!");
        }
        if (sifra.isEmpty() || sifra.equals("")) {
            throw new Exception("Sifra korisnika ne sme biti prazno!");
        }
        this.sifra = sifra;
    }

    public void setIme(String ime) throws Exception {
        if (ime == null) {
            throw new Exception("Ime korisnika mora da postoji!");
        }
        if (ime.isEmpty() || ime.equals("")) {
            throw new Exception("Ime korisnika ne sme biti prazno!");
        }
        this.ime = ime;
    }

    public void setPrezime(String prezime) throws Exception {
        if (prezime == null) {
            throw new Exception("Prezime korisnika mora da postoji!");
        }
        if (prezime.isEmpty() || prezime.equals("")) {
            throw new Exception("Prezime korisnika ne sme biti prazno!");
        }
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + ", korisnicko ime: " + korisnickoIme;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Korisnik other = (Korisnik) obj;
        return Objects.equals(this.korisnickoIme, other.korisnickoIme);
    }

    @Override
    public String vratiNazivTabele() {
        return "user";
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
        this.id = id;
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiJoin() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        return id;
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

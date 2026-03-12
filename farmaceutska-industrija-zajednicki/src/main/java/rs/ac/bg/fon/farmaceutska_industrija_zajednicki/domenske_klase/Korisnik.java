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
 * Predstavlja korisnika, odnosno, <b>farmaceuta</b> koji koristi aplikaciju.
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 * 
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Korisnik implements OpstaDomenskaKlasa {

    /**
     * Id korisnika kao Long.
     */
    private Long id;
    
    /**
     * Korisničko ime korisnika kao String.
     */
    private String korisnickoIme;
    
    /**
     * Šifra korisnika kao String.
     */
    private String sifra;
    
    /**
     * Ime korisnika kao String.
     */
    private String ime;
    
    /**
     * Prezime korisnika kao String.
     */
    private String prezime;

    /**
     * Inicijalizuje instancu korisnika sa unetim korisničkim imenom i šifrom.
     * 
     * @param korisnickoIme Korisničko ime korisnika koje se unosi u objekat. Korisničko ime ne sme biti null niti prazno.
     * @param sifra Šifra korisnika koja se unosi u objekat. Šifra ne sme biti null niti prazna.
     */
    public Korisnik(String korisnickoIme, String sifra) {
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    /**
     * Postavlja novi id korisnika.
     * Uneti id ne sme biti null, manji od ili jednak nuli.
     * 
     * @param id Id korisnika kao Long.
     * 
     * @throws java.lang.Exception Ako je uneti id null, manji od ili jednak nuli. 
     */
    public void setId(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID korisnika mora da postoji!");
        }
        if (id <= 0) {
            throw new Exception("ID korisnika mora biti pozitivan!");
        }
        this.id = id;
    }

    /**
     * Postavlja novo korisničko ime korisnika.
     * Uneto korisničko ime ne sme biti null ili prazno.
     * 
     * @param korisnickoIme Korisničko ime korisnika kao String.
     * 
     * @throws java.lang.Exception Ako je uneto korisničko ime null ili prazno. 
     */
    public void setKorisnickoIme(String korisnickoIme) throws Exception {
        if (korisnickoIme == null) {
            throw new Exception("Korisnicko ime korisnika mora da postoji!");
        }
        if (korisnickoIme.isEmpty() || korisnickoIme.equals("")) {
            throw new Exception("Korisnicko ime korisnika ne sme biti prazno!");
        }
        this.korisnickoIme = korisnickoIme;
    }

    /**
     * Postavlja novu šifru korisnika.
     * Uneta šifra ne sme biti null ili prazna.
     * 
     * @param sifra Šifra korisnika kao String.
     * 
     * @throws java.lang.Exception Ako je uneta šifra null ili prazna. 
     */
    public void setSifra(String sifra) throws Exception {
        if (sifra == null) {
            throw new Exception("Sifra korisnika mora da postoji!");
        }
        if (sifra.isEmpty() || sifra.equals("")) {
            throw new Exception("Sifra korisnika ne sme biti prazno!");
        }
        this.sifra = sifra;
    }

    /**
     * Postavlja novo ime korisnika.
     * Uneto ime ne sme biti null ili prazno.
     * 
     * @param ime Ime korisnika kao String.
     * 
     * @throws java.lang.Exception Ako je uneto ime null ili prazno. 
     */
    public void setIme(String ime) throws Exception {
        if (ime == null) {
            throw new Exception("Ime korisnika mora da postoji!");
        }
        if (ime.isEmpty() || ime.equals("")) {
            throw new Exception("Ime korisnika ne sme biti prazno!");
        }
        this.ime = ime;
    }

    /**
     * Postavlja novo prezime korisnika.
     * Uneto prezime ne sme biti null ili prazno.
     * 
     * @param prezime Prezime korisnika kao String.
     * 
     * @throws java.lang.Exception Ako je uneto prezime null ili prazno. 
     */
    public void setPrezime(String prezime) throws Exception {
        if (prezime == null) {
            throw new Exception("Prezime korisnika mora da postoji!");
        }
        if (prezime.isEmpty() || prezime.equals("")) {
            throw new Exception("Prezime korisnika ne sme biti prazno!");
        }
        this.prezime = prezime;
    }

    /**
     * Vraća String sa osnovnim podacima o korisniku.
     * 
     * @return String sa osnovnim podacima u formatu
     * ##### #####, korisnicko ime: ##### odnosno, na primer
     * Miloš Korda, korisnicko ime: admin
     */
    @Override
    public String toString() {
        return ime + " " + prezime + ", korisnicko ime: " + korisnickoIme;
    }

    /**
     * Vraća hash code koji nije zasnovan na atributima korisnika
     * 
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * Poredi dva korisnika prema korisničkom imenu.
     * 
     * @param obj Drugi korisnik sa kojim se poredi.
     * 
     * @return
     * <ul>
     * <li>true - ako je uneti objekat različit od null, ako je klase {@link Korisnik}
     * i ako je korisničko ime isto kao i kod prvog korisnika.</li>
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

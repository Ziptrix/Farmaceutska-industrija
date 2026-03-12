/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Predstavlja lek koji se pravi u aplikaciji.
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lek implements OpstaDomenskaKlasa {

    /**
     * Serijski broj leka kao Long.
     */
    private Long serijskiBroj;
    
    /**
     * Naziv leka kao String.
     */
    private String naziv;
    
    /**
     * Doziranje leka kao String.
     */
    private String doziranje;
    
    /**
     * Lista supstanci od kojih je lek napravljen.
     */
    private List<Supstanca> sastav;

    /**
     * Postavlja novi serijski broj leka.
     * Uneti serijski broj ne sme biti null, manji od ili jednak nuli.
     *
     * @param serijskiBroj Serijski broj leka kao Long.
     *
     * @throws java.lang.Exception Ako je uneti serijski broj null, manji od ili jednak nuli.
     */
    public void setSerijskiBroj(Long serijskiBroj) throws Exception {
        if (serijskiBroj == null) {
            throw new Exception("Serijski broj leka mora da postoji!");
        }
        if (serijskiBroj <= 0) {
            throw new Exception("Serijski broj leka mora biti pozitivan!");
        }
        this.serijskiBroj = serijskiBroj;
    }

    /**
     * Postavlja novi naziv leka.
     * Uneti naziv ne sme biti null ili prazan.
     *
     * @param naziv Naziv leka kao Long.
     *
     * @throws java.lang.Exception Ako je uneti naziv null ili prazan.
     */
    public void setNaziv(String naziv) throws Exception {
        if (naziv == null) {
            throw new Exception("Naziv leka mora da postoji!");
        }
        if (naziv.isEmpty() || naziv.equals("")) {
            throw new Exception("Naziv leka ne sme biti prazan!");
        }
        this.naziv = naziv;
    }

    /**
     * Postavlja novo doziranje leka.
     * Uneto doziranje ne sme biti null ili prazno.
     *
     * @param doziranje Doziranje leka kao Long.
     *
     * @throws java.lang.Exception Ako je uneto doziranje null ili prazno.
     */
    public void setDoziranje(String doziranje) throws Exception {
        if (doziranje == null) {
            throw new Exception("Doziranje leka mora da postoji!");
        }
        if (doziranje.isEmpty() || doziranje.equals("")) {
            throw new Exception("Doziranje leka ne sme biti prazno!");
        }
        this.doziranje = doziranje;
    }

    /**
     * Postavlja novu listu supstanci od kojih je lek napravljen.
     * 
     * @param sastav Lista supstanci od kojih je lek napravljen.
     */
    public void setSastav(List<Supstanca> sastav) {
        this.sastav = sastav;
    }

    /**
     * Vraća String sa nazivom i doziranjem leka.
     *
     * @return String sa nazivom i doziranjem u formatu
     * ##### ##### odnosno, na primer
     * Febricet tri puta dnevno
     */
    @Override
    public String toString() {
        return naziv + " " + doziranje;
    }

    @Override
    public void postaviId(long id) {
        this.serijskiBroj = id;
    }

    @Override
    public Long vratiId() {
        return serijskiBroj;
    }

    @Override
    public String vratiNazivTabele() {
        return "medicine";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "serial_number";
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "serial_number, name, dosage";
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return serijskiBroj + ", '" + naziv + "', '" + doziranje + "'";
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        return "l.serial_number, l.name AS naziv_leka, l.dosage, "
                + "s.code, s.name, "
                + "s.quantity, s.price, "
                + "sl.id_medicine, sl.id_substance, sl.quantity_used";
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        return "dosage = '" + doziranje + "'";
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception {
        ps.setLong(1, serijskiBroj);
    }

    @Override
    public String vratiJoin() {
        return " l JOIN substance_medicine sl ON l.serial_number = sl.id_medicine "
                + " JOIN substance s ON s.code = sl.id_substance";
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lekovi = new ArrayList<>();
        Lek trenutniLek = null;
        long poslednjiSerijskiBroj = -1;

        while (rs.next()) {
            long serijskiBroj = rs.getLong("serial_number");

            if (trenutniLek == null || serijskiBroj != poslednjiSerijskiBroj) {
                trenutniLek = new Lek();
                trenutniLek.setSerijskiBroj(serijskiBroj);
                trenutniLek.setNaziv(rs.getString("naziv_leka"));
                trenutniLek.setDoziranje(rs.getString("l.dosage"));
                trenutniLek.setSastav(new ArrayList<>());
                lekovi.add(trenutniLek);

                poslednjiSerijskiBroj = serijskiBroj;
            }

            Supstanca s = new Supstanca();
            s.setSifra(rs.getLong("sl.id_substance"));
            s.setNaziv(rs.getString("s.name"));
            s.setCena(rs.getLong("s.price"));
            s.setKolicinaZaliha(rs.getLong("sl.quantity_used"));

            trenutniLek.getSastav().add(s);
        }

        return lekovi;
    }

    @Override
    public String vratiUslovZaDelete() {
        return "WHERE serial_number = ?";
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        return "l.name";
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        return "serial_number";
    }

}

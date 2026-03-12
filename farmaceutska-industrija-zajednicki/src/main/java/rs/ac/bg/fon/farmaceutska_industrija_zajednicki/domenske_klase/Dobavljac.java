/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Predstavlja dobavljača od kojeg se poručuje {@link Supstanca}.
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 * 
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dobavljac implements OpstaDomenskaKlasa {

    /**
     * Id dobavljača kao Long.
     */
    private Long id;
    
    /**
     * Ime dobavljača kao String.
     */
    private String ime;
    
    /**
     * Prezime dobavljača kao String.
     */
    private String prezime;
    
    /**
     * Grad iz kojeg dobavljač dolazi kao {@link Grad}
     */
    private Grad grad;

    /**
     * Postavlja novi id dobavljača.
     * Uneti id ne sme biti null, manji od ili jednak nuli.
     * 
     * @param id Id dobavljača kao Long.
     * 
     * @throws java.lang.Exception Ako je uneti id null, manji od ili jednak nuli. 
     */
    public void setId(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID dobavljaca mora da postoji!");
        }
        if (id <= 0) {
            throw new Exception("ID dobavljaca mora biti pozitivan!");
        }
        this.id = id;
    }

    /**
     * Postavlja novo ime dobavljača.
     * Uneto ime ne sme biti null ili prazno.
     * 
     * @param ime Ime dobavljača kao String.
     * 
     * @throws java.lang.Exception Ako je uneto ime null ili prazno. 
     */
    public void setIme(String ime) throws Exception {
        if (ime == null) {
            throw new Exception("Ime dobavljaca mora da postoji!");
        }
        if (ime.isEmpty() || ime.equals("")) {
            throw new Exception("Ime dobavljaca ne sme biti prazno!");
        }
        this.ime = ime;
    }

    /**
     * Predstavlja novo prezime dobavljača.
     * Uneto prezime ne sme biti null ili prazno.
     * 
     * @param prezime Prezime dobavljača kao String.
     * 
     * @throws java.lang.Exception Ako je uneto prezime null ili prazno. 
     */
    public void setPrezime(String prezime) throws Exception {
        if (prezime == null) {
            throw new Exception("Prezime dobavljaca mora da postoji!");
        }
        if (prezime.isEmpty() || prezime.equals("")) {
            throw new Exception("Prezime dobavljaca ne sme biti prazno!");
        }
        this.prezime = prezime;
    }

    /**
     * Predstavlja novi grad iz kojeg dobavljač dolazi.
     * Uneti grad ne sme biti null.
     * 
     * @param grad Grad iz kojeg dobavljač dolazi kao {@link Grad}
     * 
     * @throws java.lang.Exception Ako je uneti grad null. 
     */
    public void setGrad(Grad grad) throws Exception {
        if (grad == null || !(grad instanceof Grad)) {
            throw new Exception("Grad dobavljaca mora da postoji!");
        }
        this.grad = grad;
    }

    /**
     * Vraća String sa osnovnim podacima o dobavljaču.
     * 
     * @return String sa osnovnim podacima u formatu
     * ##### #####, iz grada: ##### odnosno, na primer
     * Mika Mikic iz grada: Pozarevac
     */
    @Override
    public String toString() {
        return ime + " " + prezime + ", iz grada: " + grad.getNaziv();
    }

    @Override
    public String vratiNazivTabele() {
        return "supplier";
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "first_name, last_name, city";
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return "'" + ime + "', '" + prezime + "', " + grad.getPostanskiBroj();
    }

    @Override
    public void postaviId(long id) {
        this.id = id;
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lista = new ArrayList<>();

        while (rs.next()) {
            Dobavljac d = new Dobavljac();
            d.setId(rs.getLong("s.id"));
            d.setIme(rs.getString("s.first_name"));
            d.setPrezime(rs.getString("s.last_name"));
            Grad g = new Grad();
            g.setPostanskiBroj(rs.getLong("c.zip_code"));
            g.setNaziv(rs.getString("c.name"));
            d.setGrad(g);
            lista.add(d);
        }

        return lista;

    }

    @Override
    public String vratiJoin() {
        return " s JOIN city c ON s.city = c.zip_code";
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        return "s.id, s.first_name, s.last_name, c.zip_code, c.name";
    }

    @Override
    public String vratiUslovZaDelete() {
        return "WHERE id = ?";
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws SQLException {
        ps.setLong(1, id);
    }

    @Override
    public Long vratiId() {
        return id;
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "id";
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        return "city = " + grad.getPostanskiBroj();
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        return "first_name";
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        return "id";
    }

}

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
import lombok.Setter;

/**
 *
 * @author milos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dobavljac implements OpstaDomenskaKlasa {

    private Long id;
    private String ime;
    private String prezime;
    private Grad grad;

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
    public String vratiUslovZaUpdateDelete() {
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

}

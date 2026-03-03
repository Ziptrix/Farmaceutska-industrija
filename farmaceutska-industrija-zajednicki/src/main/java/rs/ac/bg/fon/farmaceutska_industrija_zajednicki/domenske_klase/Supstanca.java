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
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class Supstanca implements OpstaDomenskaKlasa {

    private Long sifra;
    private String naziv;
    private Long kolicinaZaliha;
    private Long cena;

    @Override
    public String toString() {
        return naziv + ", kolicina: " + kolicinaZaliha;
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

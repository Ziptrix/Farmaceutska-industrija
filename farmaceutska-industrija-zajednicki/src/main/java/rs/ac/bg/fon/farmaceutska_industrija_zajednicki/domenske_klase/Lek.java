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
import lombok.Setter;

/**
 *
 * @author milos
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lek implements OpstaDomenskaKlasa {

    private Long serijskiBroj;
    private String naziv;
    private String doziranje;
    private List<Supstanca> sastav;

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

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
public class SupstancaLek implements OpstaDomenskaKlasa {

    private Lek lek;
    private Supstanca supstanca;
    private Long upotrebljenaKolicina;

    @Override
    public String toString() {
        return lek + ", " + supstanca + ", " + upotrebljenaKolicina;
    }

    @Override
    public void postaviId(long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Long vratiId() {
        return supstanca.getSifra();
    }

    @Override
    public String vratiNazivTabele() {
        return "substance_medicine";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        return "id_substance";
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "id_medicine, id_substance, quantity_used";
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        return "sl.id_medicine";
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return lek.getSerijskiBroj() + ", " + supstanca.getSifra() + ", " + upotrebljenaKolicina;
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        return "sl.id_medicine, sl.id_substance, sl.quantity_used, s.code, s.name, s.quantity, s.price"
                + ", l.serial_number, l.name, l.dosage";
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        return "quantity_used = " + upotrebljenaKolicina;
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception {
        ps.setLong(1, lek.getSerijskiBroj());
    }

    @Override
    public String vratiJoin() {
        return " sl JOIN substance s ON sl.id_substance = s.code"
                + " JOIN medicine l ON l.serial_number = sl.id_medicine";
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lista = new ArrayList<>();

        while (rs.next()) {
            SupstancaLek sl = new SupstancaLek();

            Lek lek = new Lek();
            lek.setSerijskiBroj(rs.getLong("l.serial_number"));
            lek.setNaziv(rs.getString("l.name"));
            lek.setDoziranje(rs.getString("l.dosage"));

            Supstanca supstanca = new Supstanca();
            supstanca.setSifra(rs.getLong("s.code"));
            supstanca.setNaziv(rs.getString("s.name"));
            supstanca.setCena(rs.getLong("s.price"));
            supstanca.setKolicinaZaliha(rs.getLong("quantity_used"));

            sl.setLek(lek);
            sl.setSupstanca(supstanca);
            sl.setUpotrebljenaKolicina(rs.getLong("quantity_used"));

            lista.add(sl);
        }

        return lista;
    }

    @Override
    public String vratiUslovZaDelete() {
        return "WHERE id_medicine = ?";
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

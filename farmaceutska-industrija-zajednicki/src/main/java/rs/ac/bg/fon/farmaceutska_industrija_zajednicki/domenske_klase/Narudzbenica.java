/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
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
public class Narudzbenica implements OpstaDomenskaKlasa {

    private Long sifra;
    private LocalDate datum;
    private Long ukupanIznos;
    private Korisnik korisnik;
    private Dobavljac dobavljac;
    private List<StavkaNarudzbenice> listaStavki;

    @Override
    public String toString() {
        return "Datum kreiranja: " + datum + ", ukupan iznos: " + ukupanIznos;
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
        return "purchase_order";
    }

    @Override
    public String vratiNazivPrimarnogKljuca() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "code, date, total_amount, user, supplier";
    }

    @Override
    public String vratiNazivKoloneZaPretragu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return sifra + ", '" + datum + "', " + ukupanIznos + ", " + korisnik.getId() + ", " + dobavljac.getId();
    }

    @Override
    public String vratiVrednostiSelectUpita() {
        return "n.code, n.date, n.total_amount, n.user, n.supplier, "
                + "sn.order_number AS stavka_id, sn.quantity, sn.amount, "
                + "s.code AS supstanca_id, s.name, s.quantity, s.price";
    }

    @Override
    public String vratiVrednostiUpdateUpita() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception {
        ps.setLong(1, sifra);
    }

    @Override
    public String vratiJoin() {
        return " n JOIN item sn ON n.code = sn.id_po "
                + " JOIN substance s ON sn.substance = s.code ";
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception {
        List<OpstaDomenskaKlasa> lista = new ArrayList<>();

        while (rs.next()) {
            Long idNarudzbenice = rs.getLong("n.code");
            Narudzbenica postojeca = null;

            postojeca = lista.stream()
                    .map(odk -> (Narudzbenica) odk)
                    .filter(n -> n.getSifra().equals(idNarudzbenice))
                    .findFirst()
                    .orElse(null);

//            for (OpstaDomenskaKlasa odk : lista) {
//                Narudzbenica n = (Narudzbenica) odk;
//                if (n.getSifra().equals(idNarudzbenice)) {
//                    postojeca = n;
//                    break;
//                }
//            }
            if (postojeca == null) {
                postojeca = new Narudzbenica();
                postojeca.setSifra(idNarudzbenice);
                postojeca.setDatum(rs.getDate("n.date").toLocalDate());
                postojeca.setUkupanIznos(rs.getLong("n.total_amount"));
                postojeca.setKorisnik(null);
                postojeca.setDobavljac(null);
                postojeca.setListaStavki(new ArrayList<>());
                lista.add(postojeca);
            }

            Supstanca s = new Supstanca();
            s.setSifra(rs.getLong("supstanca_id"));
            s.setNaziv(rs.getString("s.name"));
            s.setKolicinaZaliha(rs.getLong("s.quantity"));
            s.setCena(rs.getLong("s.price"));

            StavkaNarudzbenice sn = new StavkaNarudzbenice();
            sn.setRedniBroj(rs.getLong("stavka_id"));
            sn.setKolicinaSupstance(rs.getLong("sn.quantity"));
            sn.setIznosStavke(rs.getLong("sn.amount"));
            sn.setSupstanca(s);
            sn.setNarudzbenica(postojeca);

            postojeca.getListaStavki().add(sn);
        }

        return lista;
    }

    @Override
    public String vratiUslovZaDelete() {
        return "WHERE code = ?";
    }

    @Override
    public String vratiNazivKoloneZaGroupBy() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

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

/**
 * Predstavlja narudžbenicu kojom naručujemo novu količinu supstanci (objekat klase {@link Supstanca}).
 * Ova domenska klasa implementira interfejs {@link OpstaDomenskaKlasa}.
 *
 * @author milos
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Narudzbenica implements OpstaDomenskaKlasa {

    /**
     * Šifra narudžbenice kao Long.
     */
    private Long sifra;
    
    /**
     * Datum kreiranja narudžbenice kao LocalDate.
     */
    private LocalDate datum;
    
    /**
     * Ukupan iznos narudžbenice kao Long.
     */
    private Long ukupanIznos;
    
    /**
     * Korisnik koji je kreirao narudžbenicu kao {@link Korisnik}.
     */
    private Korisnik korisnik;
    
    /**
     * Dobavljač od kojeg se poručuje {@link Supstanca} kao {@link Dobavljac}.
     */
    private Dobavljac dobavljac;
    
    /**
     * Lista stavki narudžbenice kao {@link StavkaNarudzbenice}.
     */
    private List<StavkaNarudzbenice> listaStavki;

    /**
     * Postavlja novu šifru narudžbenice.
     * Uneta šifra ne sme biti null, manja od ili jednaka nuli.
     *
     * @param sifra Šifra narudžbenice kao Long.
     *
     * @throws java.lang.Exception Ako je uneta šifra null, manja od ili jednaka nuli.
     */
    public void setSifra(Long sifra) throws Exception {
        if (sifra == null) {
            throw new Exception("Sifra narudzbenice mora da postoji!");
        }
        if (sifra <= 0) {
            throw new Exception("Sifra narudzbenice mora biti pozitivna!");
        }
        this.sifra = sifra;
    }

    /**
     * Postavlja novi datum kreiranja narudžbenice.
     * Uneti datum ne sme biti null i ne sme biti nakon današnjeg datuma.
     *
     * @param datum Datum narudžbenice kao LocalDate.
     *
     * @throws java.lang.Exception Ako je uneti datum null ili ako je nakon današnjeg datuma.
     */
    public void setDatum(LocalDate datum) throws Exception {
        if (datum == null) {
            throw new Exception("Datum narudzbenice mora da postoji!");
        }
        if (datum.isAfter(LocalDate.now())) {
            throw new Exception("Datum narudzbenice ne sme biti nakon danasnjeg datuma!");
        }
        this.datum = datum;
    }

    /**
     * Postavlja novi ukupan iznos narudžbenice.
     * Uneti ukupan iznos ne sme biti null, manji od ili jednak nuli.
     *
     * @param ukupanIznos Ukupan iznos narudžbenice kao Long.
     *
     * @throws java.lang.Exception Ako je uneti ukupan iznos null, manji od ili jednak nuli.
     */
    public void setUkupanIznos(Long ukupanIznos) throws Exception {
        if (ukupanIznos == null) {
            throw new Exception("Ukupan iznos narudzbenice mora da postoji!");
        }
        if (ukupanIznos <= 0) {
            throw new Exception("Ukupan iznos narudzbenice mora biti pozitivan!");
        }
        this.ukupanIznos = ukupanIznos;
    }

    /**
     * Postavlja novog korisnika koji je kreirao narudžbenicu.
     * 
     * @param korisnik Korisnik koji je kreirao narudžbenicu kao {@link Korisnik}.
     */
    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    /**
     * Postavlja novog dobavljača od kojeg se poručuje supstanca.
     * 
     * @param dobavljac Dobavljač od kojeg se poručuje {@link Supstanca} kao {@link Dobavljac}.
     */
    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    /**
     * Postavlja novu listu stavki narudžbenica.
     * 
     * @param listaStavki Lista stavki narudžbenice kao {@link StavkaNarudzbenice}.
     */
    public void setListaStavki(List<StavkaNarudzbenice> listaStavki) {
        this.listaStavki = listaStavki;
    }

    /**
     * Vraća String sa datumom kreiranja i ukupnim iznosom narudžbenice.
     * 
     * @return String sa datumom kreiranja i ukupnim iznosom u formatu
     * Datum kreiranja: #####, ukupan iznos: ##### odnosno, na primer
     * Datum kreiranja: 12.03.2026., ukupan iznos: 1000
     */
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
                + "s.code AS supstanca_id, s.name, s.quantity, s.price, "
                + "k.id, k.first_name, k.last_name, k.username, k.password, "
                + "d.id, d.first_name, d.last_name, d.city, "
                + "g.zip_code, g.name";
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
                + " JOIN substance s ON sn.substance = s.code "
                + " JOIN user k ON n.user = k.id "
                + " JOIN supplier d ON n.supplier = d.id "
                + " JOIN city g ON d.city = g.zip_code ";
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

            if (postojeca == null) {
                postojeca = new Narudzbenica();
                postojeca.setSifra(idNarudzbenice);
                postojeca.setDatum(rs.getDate("n.date").toLocalDate());
                postojeca.setUkupanIznos(rs.getLong("n.total_amount"));
                postojeca.setKorisnik(null);
                postojeca.setDobavljac(null);
                postojeca.setListaStavki(new ArrayList<>());

                Korisnik k = new Korisnik();
                k.setId(rs.getLong("k.id"));
                k.setIme(rs.getString("k.first_name"));
                k.setPrezime(rs.getString("k.last_name"));
                k.setKorisnickoIme(rs.getString("k.username"));
                k.setSifra(rs.getString("k.password"));
                postojeca.setKorisnik(k);

                Dobavljac d = new Dobavljac();
                d.setId(rs.getLong("d.id"));
                d.setIme(rs.getString("d.first_name"));
                d.setPrezime(rs.getString("d.last_name"));
                Grad g = new Grad();
                g.setPostanskiBroj(rs.getLong("g.zip_code"));
                g.setNaziv(rs.getString("g.name"));
                d.setGrad(g);
                postojeca.setDobavljac(d);

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

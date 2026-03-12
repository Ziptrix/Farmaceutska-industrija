/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.Korisnik;
import rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase.OpstaDomenskaKlasa;

/**
 *
 * @author milos
 */
@NoArgsConstructor
@AllArgsConstructor
public class DBBrokerOpstaDomenskaKlasa {

    private Connection konekcija;

    public Korisnik vratiKorisnika(Korisnik korisnik) throws Exception {
        String upit = "SELECT * FROM " + korisnik.vratiNazivTabele() + " WHERE username = ? AND password = ?";
        System.out.println("UPIT " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setString(1, korisnik.getKorisnickoIme());
            ps.setString(2, korisnik.getSifra());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                korisnik.setId(rs.getLong("id"));
                korisnik.setIme(rs.getString("first_name"));
                korisnik.setPrezime(rs.getString("last_name"));
            } else {
                throw new Exception("Korisnik sa unetim kredencijalima nije pronadjen!");
            }

            ps.close();
            rs.close();

            return korisnik;
        } catch (SQLException e) {
            System.out.println("Objekat User nije uspesno ucitan iz baze!");
            throw e;
        }
    }

    public List<OpstaDomenskaKlasa> ucitajSve(OpstaDomenskaKlasa objekat) throws Exception {
        List<OpstaDomenskaKlasa> objekti = new ArrayList<>();
        String upit = "SELECT " + objekat.vratiVrednostiSelectUpita()
                + " FROM " + objekat.vratiNazivTabele()
                + objekat.vratiJoin();
        System.out.println("UPIT " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);

            objekti = objekat.vratiListuZaSelectUpit(rs);

            st.close();
            rs.close();

            return objekti;
        } catch (SQLException e) {
            System.out.println("Objekti nisu uspesno ucitani iz baze!");
            throw e;
        }
    }

    public void dodaj(OpstaDomenskaKlasa objekat) throws Exception {
        String upit = "INSERT INTO "
                + objekat.vratiNazivTabele() + " ("
                + objekat.vratiNaziveKolonaZaInsertUpit() + ") VALUES ("
                + objekat.vratiVrednostiInsertUpita() + ")";
        System.out.println("UPIT " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();
            if (rs != null && rs.next()) {
                objekat.postaviId(rs.getLong(1));
            }

            st.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Objekat nije uspesno dodat u bazu!");
            throw e;
        }
    }

    public void obrisi(OpstaDomenskaKlasa objekat) throws Exception {
        String upit = "DELETE FROM " + objekat.vratiNazivTabele()
                + " " + objekat.vratiUslovZaDelete();
        System.out.println("UPIT: " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            PreparedStatement ps = konekcija.prepareStatement(upit);
            objekat.postaviVrednostiZaDeleteUpit(ps);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Objekat nije uspesno izbrisan iz baze!");
            throw e;
        }
    }

    public void izmeni(OpstaDomenskaKlasa objekat) throws Exception {
        String upit = "UPDATE " + objekat.vratiNazivTabele()
                + " SET " + objekat.vratiVrednostiUpdateUpita()
                + " WHERE " + objekat.vratiNazivPrimarnogKljuca()
                + " = " + objekat.vratiId();
        System.out.println("UPIT: " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            Statement st = konekcija.createStatement();
            st.executeUpdate(upit);

            st.close();
        } catch (Exception e) {
            System.out.println("Objekat nije uspesno izmenjen u bazi!");
            throw e;
        }
    }

    public List<OpstaDomenskaKlasa> pretrazi(OpstaDomenskaKlasa objekat, String kriterijum) throws Exception {
        List<OpstaDomenskaKlasa> rezultat = new ArrayList<>();
        String upit = "SELECT " + objekat.vratiVrednostiSelectUpita()
                + " FROM " + objekat.vratiNazivTabele()
                + objekat.vratiJoin()
                + " WHERE " + objekat.vratiNazivKoloneZaPretragu()
                + " LIKE '%" + kriterijum + "%'"
                + " GROUP BY " + objekat.vratiNazivKoloneZaGroupBy();
        System.out.println("UPIT: " + upit);

        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);

            rezultat = objekat.vratiListuZaSelectUpit(rs);

            st.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Objekti nisu uspesno ucitani iz baze!");
            throw e;
        }

        return rezultat;
    }

}

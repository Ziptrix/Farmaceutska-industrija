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
        } catch (Exception e) {
            System.out.println("Objekat User nije uspesno ucitan iz baze!");
            e.printStackTrace();
            throw e;
        }
    }

    public List<OpstaDomenskaKlasa> ucitajSve(OpstaDomenskaKlasa opstaKlasa) throws Exception{
        List<OpstaDomenskaKlasa> objekti = new ArrayList<>();
        String upit = "SELECT * FROM " + opstaKlasa.vratiNazivTabele();
        System.out.println("UPIT " + upit);
        
        try {
            konekcija = DBBrokerKonekcija.vratiInstancu().uspostaviKonekciju();
            Statement st = konekcija.createStatement();
            ResultSet rs = st.executeQuery(upit);
            
            objekti = opstaKlasa.vratiListu(rs);
            
            st.close();
            rs.close();
            
            return objekti;
        } catch (SQLException e) {
            System.out.println("Objekti nisu uspesno ucitani iz baze!");
        }
        return null;
    }

}

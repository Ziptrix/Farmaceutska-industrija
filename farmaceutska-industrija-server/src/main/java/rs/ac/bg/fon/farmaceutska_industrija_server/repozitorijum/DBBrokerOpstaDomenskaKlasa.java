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
 * Predstavlja klasu koja sadrži metode za generičke crud operacije.
 * Na ovaj način se obezbešuje da jednu istu metodu možemo koristiti za
 * izvršavanje operacije nad svim domenskim objektima.
 * <p>
 * Izuzetak je metoda vratiKorisnika koja nije generička već radi sa domenskim objektom.
 * </p>
 *
 * @author milos
 */
@NoArgsConstructor
@AllArgsConstructor
public class DBBrokerOpstaDomenskaKlasa {

    /**
     * Konekcija za povezivanje sa bazom kao Connection.
     */
    private Connection konekcija;

    /**
     * Pretražuje bazu podataka i vraća korisnika na osnovu prosleđenih kredencijala (korisničko ime i šifra).
     * <p>
     * Metoda izvršava SQL upit nad tabelom čiji naziv se dobija pozivom
     * {@link Korisnik#vratiNazivTabele()}, koristeći {@link java.sql.PreparedStatement}
     * kako bi se bezbedno prosledili parametri za korisničko ime i šifru.
     * Ako korisnik postoji u bazi, popunjavaju se njegova osnovna polja
     * (id, ime i prezime) i vraća se ažurirani objekat {@link Korisnik}.
     * <p>
     * Ukoliko korisnik sa zadatim kredencijalima ne postoji u bazi, metoda baca izuzetak.
     *
     * @param korisnik objekat {@link Korisnik} koji sadrži korisničko ime i šifru
     * na osnovu kojih se vrši pretraga u bazi.
     * 
     * @return {@link Korisnik} objekat popunjen podacima iz baze podataka.
     * 
     * @throws java.sql.SQLException ako dođe do greške prilikom izvršavanja SQL upita.
     * @throws java.lang.Exception ako korisnik sa prosleđenim kredencijalima nije pronađen.
     */
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

    /**
     * Učitava sve zapise iz baze podataka za prosleđeni tip domenskog objekta.
     * <p>
     * SQL upit se dinamički formira korišćenjem metoda definisanih u interfejsu
     * {@link OpstaDomenskaKlasa}, kao što su:
     * {@link OpstaDomenskaKlasa#vratiNazivTabele()},
     * {@link OpstaDomenskaKlasa#vratiVrednostiSelectUpita()} i
     * {@link OpstaDomenskaKlasa#vratiJoin()}.
     * <p>
     * Nakon izvršavanja upita, rezultat iz {@link java.sql.ResultSet} objekta
     * se mapira u listu domenskih objekata pozivom metode
     * {@link OpstaDomenskaKlasa#vratiListuZaSelectUpit(java.sql.ResultSet)}.
     *
     * @param objekat instanca koja implementira {@link OpstaDomenskaKlasa} i
     * sadrži informacije potrebne za formiranje SQL upita i mapiranje rezultata.
     * 
     * @return lista objekata tipa {@link OpstaDomenskaKlasa} učitanih iz baze.
     * 
     * @throws java.sql.SQLException ako dođe do greške prilikom izvršavanja SQL upita.
     * @throws java.lang.Exception ako dođe do greške tokom obrade rezultata.
     */
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

    /**
     * Dodaje novi zapis u bazu podataka za prosleđeni domenski objekat.
     * <p>
     * SQL {@code INSERT} upit se dinamički formira korišćenjem metoda definisanih
     * u interfejsu {@link OpstaDomenskaKlasa}, kao što su:
     * {@link OpstaDomenskaKlasa#vratiNazivTabele()},
     * {@link OpstaDomenskaKlasa#vratiNaziveKolonaZaInsertUpit()} i
     * {@link OpstaDomenskaKlasa#vratiVrednostiInsertUpita()}.
     * <p>
     * Nakon uspešnog izvršavanja upita, metoda preuzima generisani primarni ključ
     * iz baze podataka i postavlja ga u objekat pozivom metode
     * {@link OpstaDomenskaKlasa#postaviId(long)}.
     *
     * @param objekat instanca koja implementira {@link OpstaDomenskaKlasa} i 
     * sadrži podatke koji se upisuju u bazu.
     * 
     * @throws java.sql.SQLException ako dođe do greške prilikom izvršavanja SQL upita.
     * @throws java.lang.Exception ako dođe do greške tokom obrade operacije.
     */
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

    /**
     * Briše zapis iz baze podataka koji odgovara prosleđenom domenskom objektu.
     * <p>
     * SQL {@code DELETE} upit se dinamički formira korišćenjem metoda definisanih
     * u interfejsu {@link OpstaDomenskaKlasa}.
     * Naziv tabele se dobija pozivom {@link OpstaDomenskaKlasa#vratiNazivTabele()},
     * dok se uslov za brisanje dobija pozivom {@link OpstaDomenskaKlasa#vratiUslovZaDelete()}.
     * <p>
     * Parametri za {@link java.sql.PreparedStatement} postavljaju se pozivom
     * metode {@link OpstaDomenskaKlasa#postaviVrednostiZaDeleteUpit(java.sql.PreparedStatement)}.
     *
     * @param objekat instanca koja implementira {@link OpstaDomenskaKlasa} i
     * sadrži podatke potrebne za formiranje uslova brisanja.
     * 
     * @throws java.sql.SQLException ako dođe do greške prilikom izvršavanja SQL upita.
     * @throws java.lang.Exception ako dođe do greške tokom izvršavanja operacije.
     */
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

    /**
     * Menja postojeći zapis u bazi podataka koji odgovara prosleđenom domenskom objektu.
     * <p>
     * SQL {@code UPDATE} upit se dinamički formira korišćenjem metoda definisanih
     * u interfejsu {@link OpstaDomenskaKlasa}.
     * Naziv tabele se dobija pozivom {@link OpstaDomenskaKlasa#vratiNazivTabele()},
     * dok se vrednosti koje se ažuriraju dobijaju pozivom {@link OpstaDomenskaKlasa#vratiVrednostiUpdateUpita()}.
     * <p>
     * Uslov za izmenu zapisa formira se na osnovu primarnog ključa koji se dobija
     * pozivom {@link OpstaDomenskaKlasa#vratiNazivPrimarnogKljuca()} i identifikatora
     * objekta {@link OpstaDomenskaKlasa#vratiId()}.
     *
     * @param objekat instanca koja implementira {@link OpstaDomenskaKlasa} i
     * sadrži podatke koji se ažuriraju u bazi.
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom izvršavanja SQL upita.
     */
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

    /**
     * Pretražuje zapise u bazi podataka na osnovu prosleđenog kriterijuma
     * za dati tip domenskog objekta.
     * <p>
     * SQL {@code SELECT} upit se dinamički formira korišćenjem metoda definisanih
     * u interfejsu {@link OpstaDomenskaKlasa}.
     * Naziv tabele, potrebni {@code JOIN} izrazi i kolone za {@code SELECT} dobijaju se pozivima metoda
     * {@link OpstaDomenskaKlasa#vratiNazivTabele()},
     * {@link OpstaDomenskaKlasa#vratiJoin()} i
     * {@link OpstaDomenskaKlasa#vratiVrednostiSelectUpita()}.
     * <p>
     * Pretraga se vrši korišćenjem {@code LIKE} operatora nad kolonom koja se dobija
     * pozivom metode {@link OpstaDomenskaKlasa#vratiNazivKoloneZaPretragu()},
     * dok se grupisanje rezultata vrši po koloni definisanoj metodom
     * {@link OpstaDomenskaKlasa#vratiNazivKoloneZaGroupBy()}.
     * <p>
     * Dobijeni rezultat iz {@link java.sql.ResultSet} objekta mapira se u listu
     * domenskih objekata pozivom metode
     * {@link OpstaDomenskaKlasa#vratiListuZaSelectUpit(java.sql.ResultSet)}.
     *
     * @param objekat instanca koja implementira {@link OpstaDomenskaKlasa} i
     * sadrži informacije potrebne za formiranje SQL upita i mapiranje rezultata.
     * 
     * @param kriterijum tekstualni kriterijum koji se koristi u {@code LIKE} uslovu za pretragu zapisa.
     * 
     * @return lista objekata tipa {@link OpstaDomenskaKlasa} koji zadovoljavaju zadati kriterijum pretrage.
     * 
     * @throws java.lang.Exception ako dođe do greške prilikom izvršavanja SQL upita.
     */
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interfejs koji predstavlja generički objekat aplikacije.
 * Sadrži metode za interakciju sa bazom podataka i rukovanje CRUD operacijama
 * specifičnim za svaku domensku klasu.
 * Takođe obezbeđuje serijalizaciju objekata, kako bi se oni mogli
 * prosleđivati preko mreže (soketa).
 * <p>
 * Sve domenske klase treba da implementiraju ovaj interfejs kako bi se obezbedila
 * doslednost u pristupu i manipulaciji podacima.
 * </p>
 * @author milos
 */
public interface OpstaDomenskaKlasa extends Serializable {

    /**
     * Postavlja id ovog domenskog objekta.
     * 
     * @param id Atribut identifikator kao Long.
     */
    void postaviId(long id);

    /**
     * Vraća atribut identifikator ovog domenskog objekta.
     * 
     * @return Atribut identifikator kao Long.
     */
    Long vratiId();

    /**
     * Vraća naziv tabele iz baze podataka koja je povezana sa ovim domenskim objektom.
     * 
     * @return Naziv tabele.
     */
    String vratiNazivTabele();

    /**
     * Vraća naziv atributa identifikatora ovog domenskog objekta.
     * On se koristi za identifikaciju prilikom izvršavanja upita nad bazom podataka.
     * 
     * @return Atribut identifikator kao String.
     */
    String vratiNazivPrimarnogKljuca();

    /**
     * Vraća nazive kolona iz tabele u bazi podataka ovog domenskog objekta.
     * Vraćaju se sve kolone koje su neophodne prilikom izvršenja INSERT upita.
     * 
     * @return Nazivi kolona kao String.
     */
    String vratiNaziveKolonaZaInsertUpit();

    /**
     * Vraća naziv kolone iz tabele u bazi podataka ovog domenskog objekta.
     * Vraća se ona kolona, ili više njih, po kojima se vrši kriterijum pretrage.
     * 
     * @return Naziv kolone kao String.
     */
    String vratiNazivKoloneZaPretragu();

    /**
     * Vraća nazive vrednosti za kolone koje se ubacuju u tabelu ovog domenskog objekta prilikom izvršenja INSERT upita.
     * 
     * @return Vrednosti za kolone kao String.
     */
    String vratiVrednostiInsertUpita();

    /**
     * Vraća nazive kolona iz tabele u bazi podataka ovog domenskog objekta.
     * Vraćaju se sve kolone za SELECT upit.
     * 
     * @return Nazivi kolona kao String.
     */
    String vratiVrednostiSelectUpita();

    /**
     * Vraća nazive vrednosti za kolonu, ili više njih, koja se menja
     * u tabeli ovog domenskog objekta prilikom izvršenja UPDATE upita.
     * 
     * @return Vrednosti za kolone kao String.
     */
    String vratiVrednostiUpdateUpita();

    /**
     * Postavlja vrednost atributa identifikatora u objektu klase PreparedStatement
     * prilikom izvršenja parametrizovanog DELETE upita.
     * 
     * @param ps Objekat klase PreparedStatement
     * 
     * @throws java.lang.Exception Ako se desi problem prilikom izvršenja upita nad bazom podataka.
     */
    void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception;

    /**
     * Vraća JOIN klauzulu neophodnu za izvršenje SELECT upita.
     * 
     * @return JOIN klauzula kao String.
     */
    String vratiJoin();

    /**
     * Vraća listu objekata ove domenske klase kao rezultat izvršenja SELECT upita.
     * 
     * @param rs Objekat klase ResultSet koji služi za postavljanje vrednosti atributa
     * konkretnog objekta ove domenske klase.
     * 
     * @return Lista objekata ove domenske klase.
     * 
     * @throws java.lang.Exception Ako se desi problem prilikom izvršenja upita nad bazom podataka. 
     */
    List<OpstaDomenskaKlasa> vratiListuZaSelectUpit(ResultSet rs) throws Exception;

    /**
     * Vraća WHERE klauzulu neophodnu za izvršenje parametrizovanog DELETE upita.
     * Brisanje ovog domenskog objekta u tabeli baze podataka se vrši prema atributu identifikatoru.
     * 
     * @return WHERE klauzla kao String.
     */
    String vratiUslovZaDelete();

    /**
     * Vraća naziv kolone, ili više njih, po kojima se radi GROUP BY klauzula.
     * 
     * @return Naziv kolona kao String.
     */
    String vratiNazivKoloneZaGroupBy();

}

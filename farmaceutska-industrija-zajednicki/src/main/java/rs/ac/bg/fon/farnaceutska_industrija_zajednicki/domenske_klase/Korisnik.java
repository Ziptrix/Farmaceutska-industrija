/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farnaceutska_industrija_zajednicki.domenske_klase;

import java.io.Serializable;
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
public class Korisnik implements Serializable {

    private Long id;
    private String korisnicko_ime;
    private String sifra;
    private String ime;
    private String prezime;

    public Korisnik(String korisnicko_ime, String sifra) {
        this.korisnicko_ime = korisnicko_ime;
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + ", korisnicko ime: " + korisnicko_ime;
    }

}

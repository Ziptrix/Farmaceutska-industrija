/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_zajednicki.domenske_klase;

import java.sql.ResultSet;
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
public class Dobavljac implements OpstaDomenskaKlasa{

    private Long id;
    private String ime;
    private String prezime;
    private Grad grad;

    @Override
    public String toString() {
        return ime + " " + prezime + ", iz grada: " + grad.getNaziv();
    }

    @Override
    public String vratiNazivTabele() {
        return "supplier";
    }

    @Override
    public String vratiNaziveKolonaZaInsertUpit() {
        return "first_name, last_name, city";
    }

    @Override
    public String vratiVrednostiInsertUpita() {
        return "'" + ime + "', '" + prezime + "', " + grad.getPostanskiBroj();
    }

    @Override
    public void postaviId(long id) {
        this.id = id;
    }

    @Override
    public List<OpstaDomenskaKlasa> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

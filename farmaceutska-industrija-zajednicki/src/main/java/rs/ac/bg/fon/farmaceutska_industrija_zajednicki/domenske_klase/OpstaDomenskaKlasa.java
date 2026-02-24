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
 *
 * @author milos
 */
public interface OpstaDomenskaKlasa extends Serializable {

    String vratiNazivTabele();

    String vratiNaziveKolonaZaInsertUpit();

    String vratiVrednostiInsertUpita();

    void postaviId(long id);
    
    List<OpstaDomenskaKlasa> vratiListuZaSelectUpit (ResultSet rs) throws Exception;
    
    String vratiJoin();
    
    String vratiVrednostiSelectUpita();
    
    String vratiUslovZaUpdateDelete();
    
    void postaviVrednostiZaDeleteUpit(PreparedStatement ps) throws Exception;

}

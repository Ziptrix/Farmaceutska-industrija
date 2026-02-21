/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.farnaceutska_industrija_zajednicki.domenske_klase;

import java.io.Serializable;

/**
 *
 * @author milos
 */
public interface OpstaDomenskaKlasa extends Serializable{
    String vratiNazivTabele();
    
    String vratiNaziveKolonaZaInsertUpit();
    
    String vratiVrednostiInsertUpita();
    
    void postaviId(long id);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package rs.ac.bg.fon.farmaceutska_industrija_server.repozitorijum;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author milos
 */
public class DBBrokerKonekcijaTest {

    public DBBrokerKonekcijaTest() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBBrokerKonekcija.vratiInstancu().zatvoriKonekciju();
    }

    @Test
    void testUspostaviKonekciju() throws Exception {
        Connection conn = DBBrokerKonekcija
                .vratiInstancu()
                .uspostaviKonekcijuZaTest();

        assertNotNull(conn);
        assertFalse(conn.isClosed());
    }

    @Test
    void testPotvrdiTransakciju() throws Exception {
        DBBrokerKonekcija broker = DBBrokerKonekcija.vratiInstancu();
        broker.uspostaviKonekciju();

        assertDoesNotThrow(() -> broker.potvrdiTransakciju());
    }

    @Test
    void testPonistiTransakciju() throws Exception {
        DBBrokerKonekcija broker = DBBrokerKonekcija.vratiInstancu();
        broker.uspostaviKonekciju();

        assertDoesNotThrow(() -> broker.ponistiTransakciju());
    }

    @Test
    void testZatvoriKonekciju() throws Exception {
        DBBrokerKonekcija broker = DBBrokerKonekcija.vratiInstancu();
        broker.uspostaviKonekciju();

        broker.zatvoriKonekciju();

        Connection conn = broker.uspostaviKonekciju();
        assertFalse(conn.isClosed());
    }
}

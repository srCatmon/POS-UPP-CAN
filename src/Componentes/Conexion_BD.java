/*
 * 
 */
package Componentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author CeAnCof Software
 */

public class Conexion_BD {
    
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:file:C:\\POS\\BD\\punto-venta", "root", "CeAnCof7614");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }
}


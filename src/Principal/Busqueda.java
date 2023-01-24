/*
 * 
 */
package Principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author CeAnCof software
 */



public class Busqueda {
    static Connection con = Componentes.Conexion_BD.getConnection();
    public static String Cate;
    public static long Codi;
    public static Object []Datos = new Object[3];
    public static String Categoria;
    private static final String []CATEGORIA = {"papeleria","regalos","farmacia","electronicos","merceria","catalogo"};
    public static int enc;

    public static int getEnc() {
        return enc;
    }

    public static void setEnc(int enc) {
        Busqueda.enc = enc;
    }

    public static Object[] getDatos() {
        return Datos;
    }

    public static void setDatos(Object[] Datos) {
        Busqueda.Datos = Datos;
    }
    
    
    
    public static void Buscar(){
        try{
            int conti = 0, cont = 0;
            do {
                Codi = Principal.Ventana_Principal.getCodigo();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT Codigo_Producto FROM " + CATEGORIA[cont] + " where Codigo_Producto='" + Codi + "';");
                if (rs.next()) {
                    Cate = CATEGORIA[cont];
                    conti = 1;
                    enc = 1;
                } else {
                    cont += 1;
                    enc = 0;
                    Cate = null;
                }
            } while (conti == 0 && cont < 6);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static Object[] Caracteristicas() {
        Buscar();
        String Producto;
        if(enc == 1){
            try {
                switch (Cate) {
                    case "papeleria":
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery("SELECT * FROM papeleria where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Tamaño") + "  " + rs.getString("Color"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio");
                        Categoria = "papeleria";
                        break;

                    case "regalos":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM regalos where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Tamaño") + "  " + rs.getString("Tipo"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio");
                        Categoria = "regalos";
                        break;

                    case "farmacia":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM farmacia where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Cantidad") + "  " + rs.getString("Caracteristicas"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio_Venta");
                        Categoria = "farmacia";
                        break;

                    case "electronicos":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM electronicos where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Caracteristicas") + "  " + rs.getString("Registro"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio");
                        Categoria = "electronicos";
                        break;

                    case "merceria":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM merceria where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Tamaño") + "  " + rs.getString("Color"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio_Venta");
                        Categoria = "merceria";
                        break;

                    case "catalogo":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM catalogo where Codigo_Producto='" + Codi + "';");
                        rs.next();
                        Producto = (rs.getString("Nombre") + "      " + rs.getString("Marca") + "   " + rs.getString("Caracteristicas") + "  " + rs.getString("Tamaño") + " " + rs.getString("Color"));
                        Datos[0] = rs.getObject("Codigo_Producto");
                        Datos[1] = Producto;
                        Datos[2] = rs.getObject("Precio_Venta");
                        Categoria = "catalogo";
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error al obtener los datos del producto");
                        break;
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return Datos;
    }
}

/*
 * 
 */
package Administracion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author CeAnCof Software
 */

public class Administrar_Productos extends javax.swing.JFrame {
    
    private int np;
    private float sto;
    private long cod, CodigoProd;
    private String CaracteristicasProd, NombreProd, MarcaProd;
    private Date FechaProd;
    private Double CompraProd, VentaProd;
    private PreparedStatement ps;
    private final Connection con = Componentes.Conexion_BD.getConnection();
    Statement st;
    ResultSet rs;

    public Administrar_Productos() {
        initComponents();
    }
    
        @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Buscar() {
        try {
            String busq = txtBusqueda.getText();
            String sql = "select * from electronicos where Codigo_Producto='" + busq + "';";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                long codigoP = rs.getLong("Codigo_Producto");
                double stock = rs.getDouble("Stock");
                double cv = rs.getDouble("Precio");
                double cc = rs.getDouble("Precio_Compra");
                txtCodigo.setText(String.valueOf(codigoP));
                txtNombre.setText(rs.getString("Nombre"));
                txtMarca.setText(rs.getString("Marca"));
                txtCaracteristicas.setText(rs.getString("Caracteristicas"));
                txtRegistro.setText(rs.getString("Registro"));
                txtStock.setText(String.valueOf(stock));
                txtCostoVenta.setText(String.valueOf(cv));
                jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                txtCostoCompra.setText(String.valueOf(cc));
            } else {
                sql = "select * from regalos where Codigo_Producto=" + busq + ";";
                st = con.createStatement();
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    long codigoP = rs.getLong("Codigo_Producto");
                    double stock = rs.getDouble("Stock");
                    int np = rs.getInt("Cantidad_Pzs");
                    double cc = rs.getDouble("Precio_Compra");
                    double cv = rs.getDouble("Precio");
                    txtCodigo.setText(String.valueOf(codigoP));
                    txtNombre.setText(rs.getString("Nombre"));
                    txtTamaño.setText(rs.getString("Tamaño"));
                    txtMarca.setText(rs.getString("Marca"));
                    txtSubCategoria.setText(rs.getString("Categoria"));
                    txtTipo.setText(rs.getString("Tipo"));
                    txtNumeroPiezas.setText(String.valueOf(np));
                    txtStock.setText(String.valueOf(stock));
                    txtCostoVenta.setText(String.valueOf(cv));
                    jdcFechaCaducidad.setDate(rs.getDate("Fecha_Caducidad"));
                    jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                    txtCostoCompra.setText(String.valueOf(cc));
                } else {
                    sql = "select * from papeleria where Codigo_Producto=" + busq + ";";
                    st = con.createStatement();
                    rs = st.executeQuery(sql);
                    if (rs.next()) {
                        long codigoP = rs.getLong("Codigo_Producto");
                        double stock = rs.getDouble("Stock");
                        int nump = rs.getInt("Num_Piezas");
                        double cv = rs.getDouble("Precio");
                        double cc = rs.getDouble("Costo");
                        txtCodigo.setText(String.valueOf(codigoP));
                        txtNombre.setText(rs.getString("Nombre"));
                        txtMarca.setText(rs.getString("Marca"));
                        txtTamaño.setText(rs.getString("Tamaño"));
                        txtColor.setText(rs.getString("Color"));
                        txtNumeroPiezas.setText(String.valueOf(nump));
                        txtStock.setText(String.valueOf(stock));
                        txtCostoVenta.setText(String.valueOf(cv));
                        txtCostoCompra.setText(String.valueOf(cc));
                        jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                    } else {
                        sql = "select * from farmacia where Codigo_Producto=" + busq + ";";
                        st = con.createStatement();
                        rs = st.executeQuery(sql);
                        if (rs.next()) {
                            long codigoP = rs.getLong("Codigo_Producto");
                            double cc = rs.getDouble("Precio_Compra");
                            double cv = rs.getDouble("Precio_Venta");
                            double stock = rs.getDouble("Stock");
                            txtCodigo.setText(String.valueOf(codigoP));
                            txtNombre.setText(rs.getString("Nombre"));
                            txtMarca.setText(rs.getString("Marca"));
                            txtCantidad.setText(rs.getString("Cantidad"));
                            txtCaracteristicas.setText(rs.getString("Caracteristicas"));
                            jdcFechaCaducidad.setDate(rs.getDate("Fecha_Caducidad"));
                            jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                            txtCostoCompra.setText(String.valueOf(cc));
                            txtCostoVenta.setText(String.valueOf(cv));
                            txtStock.setText(String.valueOf(stock));
                        } else {
                            sql = "select * from merceria where Codigo_Producto=" + busq + ";";
                            st = con.createStatement();
                            rs = st.executeQuery(sql);
                            if (rs.next()) {
                                long codigoP = rs.getLong("Codigo_Producto");
                                double cc = rs.getDouble("Precio_Compra");
                                double cv = rs.getDouble("Precio_Venta");
                                double stock = rs.getDouble("Stock");
                                txtCodigo.setText(String.valueOf(codigoP));
                                txtNombre.setText(rs.getString("Nombre"));
                                txtMarca.setText(rs.getString("Marca"));
                                txtColor.setText(rs.getString("Color"));
                                txtTamaño.setText(rs.getString("Tamaño"));
                                jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                                txtCostoCompra.setText(String.valueOf(cc));
                                txtCostoVenta.setText(String.valueOf(cv));
                                txtCantidad.setText(rs.getString("Cantidad"));
                                txtStock.setText(String.valueOf(stock));
                            } else {
                                sql = "select * from catalogo where Codigo_Producto=" + busq + ";";
                                st = con.createStatement();
                                rs = st.executeQuery(sql);
                                if (rs.next()) {
                                    long codigoP = rs.getLong("Codigo_Producto");
                                    int codigoC = rs.getInt("Codigo");
                                    double stock = rs.getDouble("Stock");
                                    double cc = rs.getDouble("Precio_Compra");
                                    double cv = rs.getDouble("Precio_Venta");
                                    txtCodigo.setText(String.valueOf(codigoP));
                                    txtNombre.setText(rs.getString("Nombre"));
                                    txtCodigoCatalogo.setText(String.valueOf(codigoC));
                                    txtTamaño.setText(rs.getString("Tamaño"));
                                    txtMarca.setText(rs.getString("Marca"));
                                    txtColor.setText(rs.getString("Color"));
                                    txtCaracteristicas.setText(rs.getString("Caracteristicas"));
                                    jdcFechaCompra.setDate(rs.getDate("Fecha_Compra"));
                                    txtCostoCompra.setText(String.valueOf(cc));
                                    txtCostoVenta.setText(String.valueOf(cv));
                                    txtStock.setText(String.valueOf(stock));
                                } else {
                                    JOptionPane.showMessageDialog(null, "Producto no encontrado");
                                }
                            }
                        }
                    }
                }
            }

        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void limpiar() {
        txtCodigo.setText(null);
        txtNombre.setText(null);
        txtMarca.setText(null);
        txtCostoCompra.setText(null);
        txtCostoVenta.setText(null);
        jdcFechaCompra.setDate(null);
        jdcFechaCaducidad.setDate(null);
        txtTamaño.setText(null);
        txtCantidad.setText(null);
        txtColor.setText(null);
        txtNumeroPiezas.setText(null);
        txtSubCategoria.setText(null);
        txtCodigoCatalogo.setText(null);
        txtRegistro.setText(null);
        txtCaracteristicas.setText(null);
        txtTipo.setText(null);
        txtStock.setText(null);
    }

    public void AñadirPape() {
        try {
            ps = con.prepareStatement("insert into papeleria(idTipo_Venta,Codigo_Producto,"
                    + "Nombre,Marca,Tamaño,Color,Num_Piezas,Stock,Costo,Precio,Fecha_Compra)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?)");
            np = Integer.parseInt(txtNumeroPiezas.getText());
            sto = Float.parseFloat(txtStock.getText());
            long cod = Long.valueOf(txtCodigo.getText());
            Date Fecha = jdcFechaCompra.getDate();
            java.sql.Date dat = new java.sql.Date(Fecha.getTime());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            ps.setInt(1, 11);
            ps.setLong(2, cod);
            ps.setString(3, txtNombre.getText());
            ps.setString(4, txtMarca.getText());
            ps.setString(5, txtTamaño.getText());
            ps.setString(6, txtColor.getText());
            ps.setInt(7, np);
            ps.setFloat(8, sto);
            ps.setDouble(9, cc);
            ps.setDouble(10, cv);
            ps.setDate(11, dat);
            int r = ps.executeUpdate();
            if (r > 0) {
                CodigoProd = Long.valueOf(txtCodigo.getText());
                NombreProd = txtNombre.getText();
                MarcaProd = txtMarca.getText();
                CaracteristicasProd = (txtColor.getText() + ", " + txtTamaño.getText() + ", " + np);
                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                FechaProd = jdcFechaCompra.getDate();
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void AñadirElec() {
        try {
            ps = con.prepareStatement("insert into electronicos(idTipo_Venta,Codigo_Producto,"
                    + "Nombre,Marca,Caracteristicas,Registro,Stock,Precio,Fecha_Compra,Precio_Compra)"
                    + "values(?,?,?,?,?,?,?,?,?,?)");
            sto = Float.parseFloat(txtStock.getText());
            long cod = Long.valueOf(txtCodigo.getText());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            Date Fecha = jdcFechaCompra.getDate();
            java.sql.Date dat = new java.sql.Date(Fecha.getTime());
            ps.setInt(1, 15);
            ps.setLong(2, cod);
            ps.setString(3, txtNombre.getText());
            ps.setString(4, txtMarca.getText());
            ps.setString(5, txtCaracteristicas.getText());
            ps.setString(6, txtRegistro.getText());
            ps.setFloat(7, sto);
            ps.setDouble(8, cv);
            ps.setDate(9, dat);
            ps.setDouble(10, cc);
            int r = ps.executeUpdate();
            if (r > 0) {
                CodigoProd = Long.valueOf(txtCodigo.getText());
                NombreProd = txtNombre.getText();
                MarcaProd = txtMarca.getText();
                CaracteristicasProd = (txtCaracteristicas.getText() + ", " + txtRegistro.getText());
                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                FechaProd = jdcFechaCompra.getDate();
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void AñadirReg() {
        try {
            ps = con.prepareStatement("insert into regalos(idTipo_Venta,Codigo_Producto,"
                    + "Nombre,Tamaño,Marca,Categoria,Tipo,Cantidad_Pzs,Stock,Precio,Fecha_Caducidad,Fecha_Compra,Precio_Compra)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            sto = Float.parseFloat(txtStock.getText());
            long cod = Long.valueOf(txtCodigo.getText());
            np = Integer.parseInt(txtNumeroPiezas.getText());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            Date Fecha1 = jdcFechaCompra.getDate();
            java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
            Date Fecha2 = jdcFechaCaducidad.getDate();
            java.sql.Date dat2 = new java.sql.Date(Fecha2.getTime());
            ps.setInt(1, 13);
            ps.setLong(2, cod);
            ps.setString(3, txtNombre.getText());
            ps.setString(4, txtTamaño.getText());
            ps.setString(5, txtMarca.getText());
            ps.setString(6, txtSubCategoria.getText());
            ps.setString(7, txtTipo.getText());
            ps.setInt(8, np);
            ps.setFloat(9, sto);
            ps.setDouble(10, cv);
            ps.setDate(11, dat2);
            ps.setDate(12, dat1);
            ps.setDouble(13, cc);
            int r = ps.executeUpdate();
            if (r > 0) {
                CodigoProd = Long.valueOf(txtCodigo.getText());
                NombreProd = txtNombre.getText();
                MarcaProd = txtMarca.getText();
                CaracteristicasProd = (txtSubCategoria.getText() + ", " + txtTipo.getText() + ", " + np);
                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                FechaProd = jdcFechaCompra.getDate();
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void AñadirFar() {
        try {
            ps = con.prepareStatement("insert into farmacia(Codigo_Producto,"
                    + "Nombre,Marca,Cantidad,Caracteristicas,Fecha_Caducidad,Fecha_Compra,Precio_Compra,Precio_Venta,Stock)"
                    + "values(?,?,?,?,?,?,?,?,?,?)");
            long cod = Long.valueOf(txtCodigo.getText());
            sto = Float.parseFloat(txtStock.getText());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            Date Fecha1 = jdcFechaCompra.getDate();
            java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
            Date Fecha2 = jdcFechaCaducidad.getDate();
            java.sql.Date dat2 = new java.sql.Date(Fecha2.getTime());
            ps.setLong(1, cod);
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtMarca.getText());
            ps.setString(4, txtCantidad.getText());
            ps.setString(5, txtCaracteristicas.getText());
            ps.setDate(6, dat2);
            ps.setDate(7, dat1);
            ps.setDouble(8, cc);
            ps.setDouble(9, cv);
            ps.setFloat(10, sto);
            int r = ps.executeUpdate();
            if (r > 0) {
                NombreProd = txtNombre.getText();
                CodigoProd = Long.valueOf(txtCodigo.getText());
                MarcaProd = txtMarca.getText();
                CaracteristicasProd = (txtCantidad.getText() + ", " + txtCaracteristicas.getText());
                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                FechaProd = jdcFechaCompra.getDate();
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void AñadirMer() {
        try {
            ps = con.prepareStatement("insert into merceria(Codigo_Producto,Nombre,"
                    + "Marca,Color,Tamaño,Fecha_Compra,Precio_Compra,Precio_Venta,Cantidad,Stock)"
                    + "values(?,?,?,?,?,?,?,?,?,?)");
            long cod = Long.valueOf(txtCodigo.getText());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            sto = Float.parseFloat(txtStock.getText());
            Date Fecha = jdcFechaCompra.getDate();
            java.sql.Date dat = new java.sql.Date(Fecha.getTime());
            ps.setLong(1, cod);
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtMarca.getText());
            ps.setString(4, txtColor.getText());
            ps.setString(5, txtTamaño.getText());
            ps.setDate(6, dat);
            ps.setDouble(7, cc);
            ps.setDouble(8, cv);
            ps.setString(9, txtCantidad.getText());
            ps.setFloat(10, sto);
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void AñadirCat() {
        try {
            ps = con.prepareStatement("insert into catalogo(Codigo_Producto,Nombre,"
                    + "Codigo,Tamaño,Marca,Color,Caracteristicas,Fecha_Compra,Precio_Compra,Precio_Venta,Stock)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?)");
            long cod = Long.valueOf(txtCodigo.getText());
            int codC = Integer.parseInt(txtCodigoCatalogo.getText());
            sto = Float.parseFloat(txtStock.getText());
            Double cv = Double.parseDouble(txtCostoVenta.getText());
            Double cc = Double.parseDouble(txtCostoCompra.getText());
            Date Fecha1 = jdcFechaCompra.getDate();
            java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
            ps.setLong(1, cod);
            ps.setString(2, txtNombre.getText());
            ps.setInt(3, codC);
            ps.setString(4, txtTamaño.getText());
            ps.setString(5, txtMarca.getText());
            ps.setString(6, txtColor.getText());
            ps.setString(7, txtCaracteristicas.getText());
            ps.setDate(8, dat1);
            ps.setDouble(9, cc);
            ps.setDouble(10, cv);
            ps.setFloat(11, sto);
            int r = ps.executeUpdate();
            if (r > 0) {
                CodigoProd = Long.valueOf(txtCodigo.getText());
                NombreProd = txtNombre.getText();
                MarcaProd = txtMarca.getText();
                CaracteristicasProd = (txtTamaño.getText() + ", " + txtColor.getText() + ", " + txtCaracteristicas.getText());
                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                FechaProd = jdcFechaCompra.getDate();
                JOptionPane.showMessageDialog(null, "Registro añadido exitosamente");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error confirme los datos ingresados");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        }
    }

    public void Actualizar() {
        try {
            if (rbtnPapeleria.isSelected()) {
                Double cv = Double.parseDouble(txtCostoVenta.getText());
                Double cc = Double.parseDouble(txtCostoCompra.getText());
                Date Fecha1 = jdcFechaCompra.getDate();
                java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
                int np = Integer.parseInt(txtNumeroPiezas.getText());
                cod = Long.valueOf(txtCodigo.getText());
                double sto = Double.parseDouble(txtStock.getText());
                ps = con.prepareStatement("UPDATE papeleria SET "
                        + "Nombre=?,"
                        + "Marca=?,"
                        + "Color=?,"
                        + "Tamaño=?,"
                        + "Num_Piezas=?,"
                        + "Stock=?,"
                        + "Fecha_Compra=?,"
                        + "Precio_Compra=?,"
                        + "Precio=?,"
                        + "Codigo_Producto=?"
                        + "where Codigo_Producto=?");
                ps.setString(1, txtNombre.getText());
                ps.setString(2, txtMarca.getText());
                ps.setString(3, txtColor.getText());
                ps.setString(4, txtTamaño.getText());
                ps.setInt(5, np);
                ps.setDouble(6, sto);
                ps.setDate(7, dat1);
                ps.setDouble(8, cc);
                ps.setDouble(9, cv);
                ps.setLong(10, cod);
                ps.setString(11, txtBusqueda.getText());
                int r = ps.executeUpdate();
                if (r > 0) {
                    CodigoProd = Long.valueOf(txtCodigo.getText());
                    NombreProd = txtNombre.getText();
                    MarcaProd = txtMarca.getText();
                    CaracteristicasProd = (txtColor.getText() + ", " + txtTamaño.getText() + ", " + np);
                    CompraProd = Double.parseDouble(txtCostoCompra.getText());
                    VentaProd = Double.parseDouble(txtCostoVenta.getText());
                    FechaProd = jdcFechaCompra.getDate();
                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                }
            } else {
                if (rbtnFarmacia.isSelected()) {
                    cod = Long.valueOf(txtCodigo.getText());
                    Double cv = Double.parseDouble(txtCostoVenta.getText());
                    Double cc = Double.parseDouble(txtCostoCompra.getText());
                    Date Fecha1 = jdcFechaCompra.getDate();
                    java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
                    double sto = Double.parseDouble(txtStock.getText());
                    Date Fecha2 = jdcFechaCaducidad.getDate();
                    java.sql.Date dat2 = new java.sql.Date(Fecha2.getTime());
                    ps = con.prepareStatement("UPDATE farmacia SET "
                            + "Nombre=?,"
                            + "Marca=?,"
                            + "Cantidad=?,"
                            + "Caracteristicas=?,"
                            + "Fecha_Caducidad=?,"
                            + "Fecha_Compra=?,"
                            + "Precio_Compra=?,"
                            + "Precio_Venta=?,"
                            + "Stock=?,"
                            + "Codigo_Producto=?"
                            + "where Codigo_Producto=?");
                    ps.setString(1, txtNombre.getText());
                    ps.setString(2, txtMarca.getText());
                    ps.setString(3, txtCantidad.getText());
                    ps.setString(4, txtCaracteristicas.getText());
                    ps.setDate(5, dat2);
                    ps.setDate(6, dat1);
                    ps.setDouble(7, cc);
                    ps.setDouble(8, cv);
                    ps.setDouble(9, sto);
                    ps.setLong(10, cod);
                    ps.setString(11, txtBusqueda.getText());
                    int r = ps.executeUpdate();
                    if (r > 0) {
                        NombreProd = txtNombre.getText();
                        CodigoProd = Long.valueOf(txtCodigo.getText());
                        MarcaProd = txtMarca.getText();
                        CaracteristicasProd = (txtCantidad.getText() + ", " + txtCaracteristicas.getText());
                        CompraProd = Double.parseDouble(txtCostoCompra.getText());
                        VentaProd = Double.parseDouble(txtCostoVenta.getText());
                        FechaProd = jdcFechaCompra.getDate();
                        JOptionPane.showMessageDialog(null, "Producto actualizado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en los datos");
                    }
                } else {
                    if (rbtnRegalos.isSelected()) {
                        cod = Long.valueOf(txtCodigo.getText());
                        double sto = Double.parseDouble(txtStock.getText());
                        int np = Integer.parseInt(txtNumeroPiezas.getText());
                        Double cv = Double.parseDouble(txtCostoVenta.getText());
                        Double cc = Double.parseDouble(txtCostoCompra.getText());
                        Date Fecha1 = jdcFechaCompra.getDate();
                        java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
                        Date Fecha2 = jdcFechaCaducidad.getDate();
                        java.sql.Date dat2 = new java.sql.Date(Fecha2.getTime());
                        ps = con.prepareStatement("UPDATE regalos SET "
                                + "Codigo_Producto=?,"
                                + "Nombre=?,"
                                + "Tamaño=?,"
                                + "Marca=?,"
                                + "Categoria=?,"
                                + "Tipo=?,"
                                + "Cantidad_Pzs=?,"
                                + "Stock=?,"
                                + "Precio=?,"
                                + "Fecha_Caducidad=?,"
                                + "Fecha_Compra=?,"
                                + "Precio_Compra=?"
                                + "where Codigo_Producto=?");
                        ps.setLong(1, cod);
                        ps.setString(2, txtNombre.getText());
                        ps.setString(3, txtTamaño.getText());
                        ps.setString(4, txtMarca.getText());
                        ps.setString(5, txtSubCategoria.getText());
                        ps.setString(6, txtTipo.getText());
                        ps.setInt(7, np);
                        ps.setDouble(8, sto);
                        ps.setDouble(9, cv);
                        ps.setDate(10, dat2);
                        ps.setDate(11, dat1);
                        ps.setDouble(12, cc);
                        ps.setString(13, txtBusqueda.getText());

                        int r = ps.executeUpdate();
                        if (r > 0) {
                            CodigoProd = Long.valueOf(txtCodigo.getText());
                            NombreProd = txtNombre.getText();
                            MarcaProd = txtMarca.getText();
                            CaracteristicasProd = (txtSubCategoria.getText() + ", " + txtTipo.getText() + ", " + np);
                            CompraProd = Double.parseDouble(txtCostoCompra.getText());
                            VentaProd = Double.parseDouble(txtCostoVenta.getText());
                            FechaProd = jdcFechaCompra.getDate();
                            JOptionPane.showMessageDialog(null, "Producto actualizado");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error en los datos");
                        }
                    } else {
                        if (rbtnCatalogo.isSelected()) {
                            cod = Long.valueOf(txtCodigo.getText());
                            int codC = Integer.parseInt(txtCodigoCatalogo.getText());
                            double sto = Double.parseDouble(txtStock.getText());
                            Double cv = Double.parseDouble(txtCostoVenta.getText());
                            Double cc = Double.parseDouble(txtCostoCompra.getText());
                            Date Fecha1 = jdcFechaCompra.getDate();
                            java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
                            ps = con.prepareStatement("UPDATE catalogo SET "
                                    + "Nombre=?,"
                                    + "Codigo=?,"
                                    + "Tamaño=?,"
                                    + "Marca=?,"
                                    + "Color=?,"
                                    + "Caracteristicas=?,"
                                    + "Fecha_Compra=?,"
                                    + "Precio_Compra=?,"
                                    + "Precio_Venta=?,"
                                    + "Stock=?,"
                                    + "Codigo_Producto =?"
                                    + "where Codigo_Producto=?");
                            ps.setString(1, txtNombre.getText());
                            ps.setInt(2, codC);
                            ps.setString(3, txtTamaño.getText());
                            ps.setString(4, txtMarca.getText());
                            ps.setString(5, txtColor.getText());
                            ps.setString(6, txtCaracteristicas.getText());
                            ps.setDate(7, dat1);
                            ps.setDouble(8, cc);
                            ps.setDouble(9, cv);
                            ps.setDouble(10, sto);
                            ps.setLong(11, cod);
                            ps.setString(12, txtBusqueda.getText());
                            int r = ps.executeUpdate();
                            if (r > 0) {
                                CodigoProd = Long.valueOf(txtCodigo.getText());
                                NombreProd = txtNombre.getText();
                                MarcaProd = txtMarca.getText();
                                CaracteristicasProd = (txtTamaño.getText() + ", " + txtColor.getText() + ", " + txtCaracteristicas.getText());
                                CompraProd = Double.parseDouble(txtCostoCompra.getText());
                                VentaProd = Double.parseDouble(txtCostoVenta.getText());
                                FechaProd = jdcFechaCompra.getDate();
                                JOptionPane.showMessageDialog(null, "Producto actualizado");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error en los datos");
                            }
                        } else {
                            if (rbtnMerceria.isSelected()) {
                                Double cv = Double.parseDouble(txtCostoVenta.getText());
                                Double cc = Double.parseDouble(txtCostoCompra.getText());
                                double sto = Double.parseDouble(txtStock.getText());
                                cod = Long.valueOf(txtCodigo.getText());
                                Date Fecha1 = jdcFechaCompra.getDate();
                                java.sql.Date dat1 = new java.sql.Date(Fecha1.getTime());
                                ps = con.prepareStatement("UPDATE merceria SET "
                                        + "Nombre=?,"
                                        + "Marca=?,"
                                        + "Color=?,"
                                        + "Tamaño=?,"
                                        + "Fecha_Compra=?,"
                                        + "Precio_Compra=?,"
                                        + "Precio_Venta=?,"
                                        + "Cantidad=?,"
                                        + "Stock=?,"
                                        + "Codigo_Producto=?"
                                        + "where Codigo_Producto=?");
                                ps.setString(1, txtNombre.getText());
                                ps.setString(2, txtMarca.getText());
                                ps.setString(3, txtColor.getText());
                                ps.setString(4, txtTamaño.getText());
                                ps.setDate(5, dat1);
                                ps.setDouble(6, cc);
                                ps.setDouble(7, cv);
                                ps.setString(8, txtCantidad.getText());
                                ps.setDouble(9, sto);
                                ps.setLong(10, cod);
                                ps.setString(11, txtBusqueda.getText());
                                int r = ps.executeUpdate();
                                if (r > 0) {
                                    CodigoProd = Long.valueOf(txtCodigo.getText());
                                    NombreProd = txtNombre.getText();
                                    MarcaProd = txtMarca.getText();
                                    CaracteristicasProd = (txtColor.getText() + ", " + txtTamaño.getText() + ", " + txtCantidad.getText());
                                    CompraProd = Double.parseDouble(txtCostoCompra.getText());
                                    VentaProd = Double.parseDouble(txtCostoVenta.getText());
                                    FechaProd = jdcFechaCompra.getDate();
                                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error en los datos");
                                }
                            } else {
                                cod = Long.valueOf(txtCodigo.getText());
                                CodigoProd = Long.valueOf(txtCodigo.getText());
                                double sto = Double.parseDouble(txtStock.getText());
                                Date Fecha1 = jdcFechaCompra.getDate();
                                java.sql.Date dat = new java.sql.Date(Fecha1.getTime());
                                Double cv = Double.parseDouble(txtCostoVenta.getText());
                                Double cc = Double.parseDouble(txtCostoCompra.getText());
                                ps = con.prepareStatement("UPDATE electronicos SET "
                                        + "Nombre=?,"
                                        + "Marca=?,"
                                        + "Caracteristicas=?,"
                                        + "Registro=?,"
                                        + "Stock=?,"
                                        + "Precio=?,"
                                        + "Fecha_Compra=?,"
                                        + "Precio_Compra=?,"
                                        + "Codigo_Producto=?"
                                        + "where Codigo_Producto=?;");
                                ps.setString(1, txtNombre.getText());
                                ps.setString(2, txtMarca.getText());
                                ps.setString(3, txtCaracteristicas.getText());
                                ps.setString(4, txtRegistro.getText());
                                ps.setDouble(5, sto);
                                ps.setDouble(6, cv);
                                ps.setDate(7, dat);
                                ps.setDouble(8, cc);
                                ps.setLong(9, cod);
                                ps.setString(10, txtBusqueda.getText());
                                int r = ps.executeUpdate();
                                if (r > 0) {
                                    CodigoProd = Long.valueOf(txtCodigo.getText());
                                    NombreProd = txtNombre.getText();
                                    MarcaProd = txtMarca.getText();
                                    CaracteristicasProd = (txtCaracteristicas.getText() + ", " + txtRegistro.getText());
                                    CompraProd = Double.parseDouble(txtCostoCompra.getText());
                                    VentaProd = Double.parseDouble(txtCostoVenta.getText());
                                    FechaProd = jdcFechaCompra.getDate();
                                    JOptionPane.showMessageDialog(null, "Producto actualizado");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error en los datos");
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
        } catch (SQLException e3) {
            JOptionPane.showMessageDialog(null, e3);
        }
    }

    public void Cambios() {
        try {
            java.sql.Date fechaProd = new java.sql.Date(FechaProd.getTime());
            ps = con.prepareStatement("insert into cambios(Fecha,Codigo_Producto,Nombre,Marca,Compra,Venta,Caracteristicas)"
                    + "values(?,?,?,?,?,?,?)");
            ps.setDate(1, fechaProd);
            ps.setLong(2, CodigoProd);
            ps.setString(3, NombreProd);
            ps.setString(4, MarcaProd);
            ps.setDouble(5, CompraProd);
            ps.setDouble(6, VentaProd);
            ps.setString(7, CaracteristicasProd);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnG = new javax.swing.ButtonGroup();
        PanelAdministrar = new FondoPanel();
        rbtnMerceria = new javax.swing.JRadioButton();
        lblStock = new javax.swing.JLabel();
        lblCaracteristicas = new javax.swing.JLabel();
        lblRegistro = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblFechaCompra = new javax.swing.JLabel();
        txtNombre = new java.awt.TextField();
        txtMarca = new java.awt.TextField();
        txtCostoVenta = new java.awt.TextField();
        txtCostoCompra = new java.awt.TextField();
        txtTamaño = new java.awt.TextField();
        txtColor = new java.awt.TextField();
        txtSubCategoria = new java.awt.TextField();
        txtRegistro = new java.awt.TextField();
        txtTipo = new java.awt.TextField();
        txtCantidad = new java.awt.TextField();
        txtNumeroPiezas = new java.awt.TextField();
        txtCodigoCatalogo = new java.awt.TextField();
        lblBusqueda = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        txtCaracteristicas = new java.awt.TextField();
        txtStock = new java.awt.TextField();
        btnSalir = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtCodigo = new java.awt.TextField();
        lblCodigoCatalogo = new javax.swing.JLabel();
        cbCambio = new javax.swing.JCheckBox();
        lblSubCategoria = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblCostoVenta = new javax.swing.JLabel();
        lblTamaño = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblCostoCompra = new javax.swing.JLabel();
        lblFechaCaducidad = new javax.swing.JLabel();
        lblNumeroPiezas = new javax.swing.JLabel();
        rbtnPapeleria = new javax.swing.JRadioButton();
        rbtnRegalos = new javax.swing.JRadioButton();
        rbtnFarmacia = new javax.swing.JRadioButton();
        rbtnCatalogo = new javax.swing.JRadioButton();
        rbtnElectronica = new javax.swing.JRadioButton();
        jdcFechaCompra = new com.toedter.calendar.JDateChooser();
        jdcFechaCaducidad = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Productos");
        setBounds(new java.awt.Rectangle(300, 20, 610, 490));
        setIconImage(getIconImage());
        setResizable(false);

        btnG.add(rbtnMerceria);
        rbtnMerceria.setForeground(new java.awt.Color(255, 255, 255));
        rbtnMerceria.setText("Merceria");
        rbtnMerceria.setOpaque(false);
        rbtnMerceria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnMerceriaActionPerformed(evt);
            }
        });

        lblStock.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblStock.setForeground(new java.awt.Color(255, 255, 255));
        lblStock.setText("Stock");

        lblCaracteristicas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCaracteristicas.setForeground(new java.awt.Color(255, 255, 255));
        lblCaracteristicas.setText("Caracteristicas");

        lblRegistro.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblRegistro.setForeground(new java.awt.Color(255, 255, 255));
        lblRegistro.setText("Registro");

        lblColor.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblColor.setForeground(new java.awt.Color(255, 255, 255));
        lblColor.setText("Color");

        lblTipo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(255, 255, 255));
        lblTipo.setText("Tipo");

        lblFechaCompra.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblFechaCompra.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaCompra.setText("Fecha de compra");

        txtNombre.setBackground(new java.awt.Color(51, 51, 51));
        txtNombre.setEditable(false);

        txtMarca.setBackground(new java.awt.Color(51, 51, 51));
        txtMarca.setEditable(false);

        txtCostoVenta.setBackground(new java.awt.Color(51, 51, 51));
        txtCostoVenta.setEditable(false);

        txtCostoCompra.setBackground(new java.awt.Color(51, 51, 51));
        txtCostoCompra.setEditable(false);

        txtTamaño.setBackground(new java.awt.Color(51, 51, 51));
        txtTamaño.setEditable(false);

        txtColor.setBackground(new java.awt.Color(51, 51, 51));
        txtColor.setEditable(false);

        txtSubCategoria.setBackground(new java.awt.Color(51, 51, 51));
        txtSubCategoria.setEditable(false);

        txtRegistro.setBackground(new java.awt.Color(51, 51, 51));
        txtRegistro.setEditable(false);

        txtTipo.setBackground(new java.awt.Color(51, 51, 51));
        txtTipo.setEditable(false);

        txtCantidad.setBackground(new java.awt.Color(51, 51, 51));
        txtCantidad.setEditable(false);

        txtNumeroPiezas.setBackground(new java.awt.Color(51, 51, 51));
        txtNumeroPiezas.setEditable(false);

        txtCodigoCatalogo.setBackground(new java.awt.Color(51, 51, 51));
        txtCodigoCatalogo.setEditable(false);

        lblBusqueda.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        lblBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        lblBusqueda.setText("Codigo del producto");

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        txtCaracteristicas.setBackground(new java.awt.Color(51, 51, 51));
        txtCaracteristicas.setEditable(false);

        txtStock.setBackground(new java.awt.Color(51, 51, 51));
        txtStock.setEditable(false);

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblCodigo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigo.setText("Codigo");

        lblNombre.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Nombre");

        txtCodigo.setBackground(new java.awt.Color(51, 51, 51));
        txtCodigo.setEditable(false);
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        lblCodigoCatalogo.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCodigoCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigoCatalogo.setText("Codigo");

        cbCambio.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cbCambio.setForeground(new java.awt.Color(255, 255, 255));
        cbCambio.setText("Cambio de precios");
        cbCambio.setOpaque(false);

        lblSubCategoria.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblSubCategoria.setForeground(new java.awt.Color(255, 255, 255));
        lblSubCategoria.setText("Subcategoria");

        lblMarca.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("Marca");

        lblCostoVenta.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCostoVenta.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoVenta.setText("Costo de venta");

        lblTamaño.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblTamaño.setForeground(new java.awt.Color(255, 255, 255));
        lblTamaño.setText("Tamaño");

        lblCantidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidad.setText("Cantidad");

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblCostoCompra.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblCostoCompra.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoCompra.setText("Costo de compra");

        lblFechaCaducidad.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblFechaCaducidad.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaCaducidad.setText("Fecha de caducidad");

        lblNumeroPiezas.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblNumeroPiezas.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroPiezas.setText("Numero de piezas");

        btnG.add(rbtnPapeleria);
        rbtnPapeleria.setForeground(new java.awt.Color(255, 255, 255));
        rbtnPapeleria.setText("Papeleria");
        rbtnPapeleria.setOpaque(false);
        rbtnPapeleria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnPapeleriaActionPerformed(evt);
            }
        });

        btnG.add(rbtnRegalos);
        rbtnRegalos.setForeground(new java.awt.Color(255, 255, 255));
        rbtnRegalos.setText("Regalos");
        rbtnRegalos.setOpaque(false);
        rbtnRegalos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnRegalosActionPerformed(evt);
            }
        });

        btnG.add(rbtnFarmacia);
        rbtnFarmacia.setForeground(new java.awt.Color(255, 255, 255));
        rbtnFarmacia.setText("Farmacia");
        rbtnFarmacia.setOpaque(false);
        rbtnFarmacia.setRequestFocusEnabled(false);
        rbtnFarmacia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFarmaciaActionPerformed(evt);
            }
        });

        btnG.add(rbtnCatalogo);
        rbtnCatalogo.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCatalogo.setText("Catalogo");
        rbtnCatalogo.setOpaque(false);
        rbtnCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCatalogoActionPerformed(evt);
            }
        });

        btnG.add(rbtnElectronica);
        rbtnElectronica.setForeground(new java.awt.Color(255, 255, 255));
        rbtnElectronica.setText("Electronica");
        rbtnElectronica.setOpaque(false);
        rbtnElectronica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnElectronicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAdministrarLayout = new javax.swing.GroupLayout(PanelAdministrar);
        PanelAdministrar.setLayout(PanelAdministrarLayout);
        PanelAdministrarLayout.setHorizontalGroup(
            PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAdministrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                .addComponent(lblBusqueda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                .addComponent(rbtnPapeleria)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnFarmacia)
                                .addGap(28, 28, 28)
                                .addComponent(rbtnRegalos)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnCatalogo)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnElectronica)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnMerceria)))
                        .addGap(81, 81, 81))
                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAdministrarLayout.createSequentialGroup()
                                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblColor)
                                            .addComponent(lblTamaño)
                                            .addComponent(lblSubCategoria)
                                            .addComponent(lblRegistro)
                                            .addComponent(lblTipo)))
                                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblCostoCompra)
                                            .addComponent(lblNombre))
                                        .addGap(20, 20, 20)
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCostoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                        .addComponent(lblFechaCompra)
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTamaño, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtColor, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtSubCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(jdcFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                        .addComponent(lblCodigo)
                                        .addGap(78, 78, 78)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAdministrarLayout.createSequentialGroup()
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMarca)
                                            .addComponent(lblCostoVenta))
                                        .addGap(46, 46, 46)
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCostoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lblFechaCaducidad)
                                                .addComponent(lblCantidad)
                                                .addComponent(lblNumeroPiezas)
                                                .addComponent(lblCodigoCatalogo)
                                                .addComponent(lblCaracteristicas)
                                                .addComponent(lblStock)))
                                        .addGap(18, 18, 18)
                                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtNumeroPiezas, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtCodigoCatalogo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtCaracteristicas, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(txtStock, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jdcFechaCaducidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(cbCambio))))
                        .addContainerGap())))
        );
        PanelAdministrarLayout.setVerticalGroup(
            PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAdministrarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusqueda)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPapeleria)
                    .addComponent(rbtnElectronica)
                    .addComponent(rbtnMerceria)
                    .addComponent(rbtnFarmacia)
                    .addComponent(rbtnRegalos)
                    .addComponent(rbtnCatalogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCostoCompra)
                            .addComponent(txtCostoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaCompra)
                            .addComponent(jdcFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTamaño)
                            .addComponent(txtTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColor)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSubCategoria)
                            .addComponent(txtSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegistro)
                            .addComponent(txtRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo)
                            .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PanelAdministrarLayout.createSequentialGroup()
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMarca)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCostoVenta)
                            .addComponent(txtCostoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaCaducidad)
                            .addComponent(jdcFechaCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidad)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumeroPiezas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNumeroPiezas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigoCatalogo)
                            .addComponent(txtCodigoCatalogo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCaracteristicas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCaracteristicas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblStock)
                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(PanelAdministrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAdministrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelAdministrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnMerceriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnMerceriaActionPerformed
        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(true);

        txtCodigo.setEditable(true);
        txtCodigo.setBackground(Color.white);
        txtNombre.setEditable(true);
        txtNombre.setBackground(Color.white);
        txtMarca.setEditable(true);
        txtMarca.setBackground(Color.white);
        txtColor.setEditable(true);
        txtColor.setBackground(Color.white);
        txtTamaño.setEditable(true);
        txtTamaño.setBackground(Color.white);
        jdcFechaCompra.setEnabled(true);
        jdcFechaCompra.setBackground(Color.white);
        txtCostoCompra.setEditable(true);
        txtCostoCompra.setBackground(Color.white);
        txtCostoVenta.setEditable(true);
        txtCostoVenta.setBackground(Color.white);
        txtStock.setEditable(true);
        txtStock.setBackground(Color.white);
        txtCantidad.setEditable(true);
        txtCantidad.setBackground(Color.white);

        txtCaracteristicas.setEditable(false);
        txtCaracteristicas.setBackground(Color.black);
        txtRegistro.setEditable(false);
        txtRegistro.setBackground(Color.black);
        jdcFechaCaducidad.setEnabled(false);
        jdcFechaCaducidad.setBackground(Color.black);
        txtNumeroPiezas.setEditable(false);
        txtNumeroPiezas.setBackground(Color.black);
        txtSubCategoria.setEditable(false);
        txtSubCategoria.setBackground(Color.black);
        txtCodigoCatalogo.setEditable(false);
        txtCodigoCatalogo.setBackground(Color.black);
        txtTipo.setEditable(false);
        txtTipo.setBackground(Color.black);
    }//GEN-LAST:event_rbtnMerceriaActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Buscar();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (evt.getSource() == btnSalir) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (evt.getSource() == btnBuscar) {
            limpiar();
            Buscar();
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (evt.getSource() == btnAgregar) {
            if (rbtnPapeleria.isSelected()) {
                AñadirPape();
                Cambios();
            } else {
                if (rbtnElectronica.isSelected()) {
                    AñadirElec();
                    Cambios();
                } else {
                    if (rbtnRegalos.isSelected()) {
                        AñadirReg();
                        Cambios();
                    } else {
                        if (rbtnFarmacia.isSelected()) {
                            AñadirFar();
                            Cambios();
                        } else {
                            if (rbtnCatalogo.isSelected()) {
                                AñadirCat();
                                Cambios();
                            } else {
                                if (rbtnMerceria.isSelected()) {
                                    AñadirMer();
                                    Cambios();
                                }
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (cbCambio.isSelected()) {
            Actualizar();
            Cambios();
        } else {
            Actualizar();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (evt.getSource() == btnEliminar) {
            String codig = txtCodigo.getText();
            try {
                if (codig != null) {
                    long busq = Long.parseLong(codig);
                    ps = con.prepareStatement("delete from papeleria where Codigo_Producto='" + busq + "';");
                    int r = ps.executeUpdate();
                    if (r > 0) {
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                    } else {
                        ps = con.prepareStatement("delete from regalos where Codigo_Producto='" + busq + "';");
                        r = ps.executeUpdate();
                        if (r > 0) {
                            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                        } else {
                            ps = con.prepareStatement("delete from electronicos where Codigo_Producto='" + busq + "';");
                            r = ps.executeUpdate();
                            if (r > 0) {
                                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                            } else {
                                ps = con.prepareStatement("delete from farmacia where Codigo_Producto='" + busq + "';");
                                r = ps.executeUpdate();
                                if (r > 0) {
                                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                                } else {
                                    ps = con.prepareStatement("delete from catalogo where Codigo_Producto='" + busq + "';");
                                    r = ps.executeUpdate();
                                    if (r > 0) {
                                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                                    } else {
                                        ps = con.prepareStatement("delete from merceria where Codigo_Producto='" + busq + "';");
                                        r = ps.executeUpdate();
                                        if (r > 0) {
                                            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                                        } else {
                                            JOptionPane.showMessageDialog(null, "No se encontro el producto o ya se ha eliminado");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (HeadlessException | SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Formato de datos introducidos incorrcto, por favor verifiquelos");
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void rbtnPapeleriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnPapeleriaActionPerformed
        if (evt.getSource() == rbtnPapeleria) {
            btnAgregar.setEnabled(true);
            btnActualizar.setEnabled(true);
            txtCodigo.setEditable(true);
            txtCodigo.setBackground(Color.white);
            txtNombre.setEditable(true);
            txtNombre.setBackground(Color.white);
            txtMarca.setEditable(true);
            txtMarca.setBackground(Color.white);
            txtTamaño.setEditable(true);
            txtTamaño.setBackground(Color.white);
            txtColor.setEditable(true);
            txtColor.setBackground(Color.white);
            txtNumeroPiezas.setEditable(true);
            txtNumeroPiezas.setBackground(Color.white);
            txtStock.setEditable(true);
            txtStock.setBackground(Color.white);
            txtCostoCompra.setEditable(true);
            txtCostoCompra.setBackground(Color.white);
            txtCostoVenta.setEditable(true);
            txtCostoVenta.setBackground(Color.white);
            jdcFechaCompra.setEnabled(true);
            jdcFechaCompra.setBackground(Color.white);

            jdcFechaCaducidad.setEnabled(false);
            jdcFechaCaducidad.setBackground(Color.black);
            txtCantidad.setEditable(false);
            txtCantidad.setBackground(Color.black);
            txtSubCategoria.setEditable(false);
            txtSubCategoria.setBackground(Color.black);
            txtCodigoCatalogo.setEditable(false);
            txtCodigoCatalogo.setBackground(Color.black);
            txtRegistro.setEditable(false);
            txtRegistro.setBackground(Color.black);
            txtCaracteristicas.setEditable(false);
            txtCaracteristicas.setBackground(Color.black);
            txtTipo.setEditable(false);
            txtTipo.setBackground(Color.black);
        }
    }//GEN-LAST:event_rbtnPapeleriaActionPerformed

    private void rbtnRegalosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnRegalosActionPerformed
        if (evt.getSource() == rbtnRegalos) {
            btnAgregar.setEnabled(true);
            btnAgregar.setBackground(Color.white);
            btnActualizar.setEnabled(true);
            btnActualizar.setBackground(Color.white);
            txtCodigo.setEditable(true);
            txtCodigo.setBackground(Color.white);
            txtNombre.setEditable(true);
            txtNombre.setBackground(Color.white);
            txtMarca.setEditable(true);
            txtMarca.setBackground(Color.white);
            txtSubCategoria.setEditable(true);
            txtSubCategoria.setBackground(Color.white);
            txtTipo.setEditable(true);
            txtTipo.setBackground(Color.white);
            txtNumeroPiezas.setEditable(true);
            txtNumeroPiezas.setBackground(Color.white);
            txtStock.setEditable(true);
            txtStock.setBackground(Color.white);
            txtCostoVenta.setEditable(true);
            txtCostoVenta.setBackground(Color.white);
            jdcFechaCaducidad.setEnabled(true);
            jdcFechaCaducidad.setBackground(Color.white);
            jdcFechaCompra.setEnabled(true);
            jdcFechaCompra.setBackground(Color.white);
            txtCostoCompra.setEditable(true);
            txtCostoCompra.setBackground(Color.white);
            txtTamaño.setEditable(true);
            txtTamaño.setBackground(Color.white);

            txtRegistro.setEditable(false);
            txtRegistro.setBackground(Color.black);
            txtCantidad.setEditable(false);
            txtCantidad.setBackground(Color.black);
            txtColor.setEditable(false);
            txtColor.setBackground(Color.black);
            txtCodigoCatalogo.setEditable(false);
            txtCodigoCatalogo.setBackground(Color.black);
            txtCaracteristicas.setEditable(false);
            txtCaracteristicas.setBackground(Color.black);
        }
    }//GEN-LAST:event_rbtnRegalosActionPerformed

    private void rbtnFarmaciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFarmaciaActionPerformed

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(true);

        txtCodigo.setEditable(true);
        txtCodigo.setBackground(Color.white);
        txtNombre.setEditable(true);
        txtNombre.setBackground(Color.white);
        txtMarca.setEditable(true);
        txtMarca.setBackground(Color.white);
        txtCantidad.setEditable(true);
        txtCantidad.setBackground(Color.white);
        txtCaracteristicas.setEditable(true);
        txtCaracteristicas.setBackground(Color.white);
        jdcFechaCaducidad.setEnabled(true);
        jdcFechaCaducidad.setBackground(Color.white);
        jdcFechaCompra.setEnabled(true);
        jdcFechaCompra.setBackground(Color.white);
        txtCostoCompra.setEditable(true);
        txtCostoCompra.setBackground(Color.white);
        txtCostoVenta.setEditable(true);
        txtCostoVenta.setBackground(Color.white);
        txtStock.setEditable(true);
        txtStock.setBackground(Color.white);

        txtTamaño.setEditable(false);
        txtTamaño.setBackground(Color.black);
        txtTamaño.setEditable(false);
        txtTamaño.setBackground(Color.black);
        txtRegistro.setEditable(false);
        txtRegistro.setBackground(Color.black);
        txtColor.setEditable(false);
        txtColor.setBackground(Color.black);
        txtNumeroPiezas.setEditable(false);
        txtNumeroPiezas.setBackground(Color.black);
        txtSubCategoria.setEditable(false);
        txtSubCategoria.setBackground(Color.black);
        txtCodigoCatalogo.setEditable(false);
        txtCodigoCatalogo.setBackground(Color.black);
        txtTipo.setEditable(false);
        txtTipo.setBackground(Color.black);
    }//GEN-LAST:event_rbtnFarmaciaActionPerformed

    private void rbtnCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCatalogoActionPerformed

        btnAgregar.setEnabled(true);
        btnActualizar.setEnabled(true);

        txtCodigo.setEditable(true);
        txtCodigo.setBackground(Color.white);
        txtNombre.setEditable(true);
        txtNombre.setBackground(Color.white);
        txtTamaño.setEditable(true);
        txtTamaño.setBackground(Color.white);
        txtMarca.setEditable(true);
        txtMarca.setBackground(Color.white);
        txtColor.setEditable(true);
        txtColor.setBackground(Color.white);
        txtCaracteristicas.setEditable(true);
        txtCaracteristicas.setBackground(Color.white);
        jdcFechaCompra.setEnabled(true);
        jdcFechaCompra.setBackground(Color.white);
        txtCostoCompra.setEditable(true);
        txtCostoCompra.setBackground(Color.white);
        txtCostoVenta.setEditable(true);
        txtCostoVenta.setBackground(Color.white);
        txtCodigoCatalogo.setEditable(true);
        txtCodigoCatalogo.setBackground(Color.white);
        txtStock.setEditable(true);
        txtStock.setBackground(Color.white);

        txtRegistro.setEditable(false);
        txtRegistro.setBackground(Color.black);
        jdcFechaCaducidad.setEnabled(false);
        jdcFechaCaducidad.setBackground(Color.black);
        txtCantidad.setEditable(false);
        txtCantidad.setBackground(Color.black);
        txtNumeroPiezas.setEditable(false);
        txtNumeroPiezas.setBackground(Color.black);
        txtSubCategoria.setEditable(false);
        txtSubCategoria.setBackground(Color.black);
        txtTipo.setEditable(false);
        txtTipo.setBackground(Color.black);
    }//GEN-LAST:event_rbtnCatalogoActionPerformed

    private void rbtnElectronicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnElectronicaActionPerformed
        if (evt.getSource() == rbtnElectronica) {
            btnAgregar.setEnabled(true);
            btnActualizar.setEnabled(true);

            txtCodigo.setEditable(true);
            txtCodigo.setBackground(Color.white);
            txtNombre.setEditable(true);
            txtNombre.setBackground(Color.white);
            txtMarca.setEditable(true);
            txtMarca.setBackground(Color.white);
            txtCaracteristicas.setEditable(true);
            txtCaracteristicas.setBackground(Color.white);
            txtRegistro.setEditable(true);
            txtRegistro.setBackground(Color.white);
            txtStock.setEditable(true);
            txtStock.setBackground(Color.white);
            txtCostoVenta.setEditable(true);
            txtCostoVenta.setBackground(Color.white);
            jdcFechaCompra.setEnabled(true);
            jdcFechaCompra.setBackground(Color.white);
            txtCostoCompra.setEditable(true);
            txtCostoCompra.setBackground(Color.white);

            txtTamaño.setEditable(false);
            txtTamaño.setBackground(Color.black);
            jdcFechaCaducidad.setEnabled(false);
            jdcFechaCaducidad.setBackground(Color.black);
            txtCantidad.setEditable(false);
            txtCantidad.setBackground(Color.black);
            txtColor.setEditable(false);
            txtColor.setBackground(Color.black);
            txtNumeroPiezas.setBackground(Color.black);
            txtNumeroPiezas.setEditable(false);
            txtSubCategoria.setBackground(Color.black);
            txtSubCategoria.setEditable(false);
            txtCodigoCatalogo.setEditable(false);
            txtCodigoCatalogo.setBackground(Color.black);
            txtTipo.setEditable(false);
            txtTipo.setBackground(Color.black);
        }
    }//GEN-LAST:event_rbtnElectronicaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Administrar_Productos().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAdministrar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.ButtonGroup btnG;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox cbCambio;
    private com.toedter.calendar.JDateChooser jdcFechaCaducidad;
    private com.toedter.calendar.JDateChooser jdcFechaCompra;
    private javax.swing.JLabel lblBusqueda;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCaracteristicas;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblCodigoCatalogo;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblCostoCompra;
    private javax.swing.JLabel lblCostoVenta;
    private javax.swing.JLabel lblFechaCaducidad;
    private javax.swing.JLabel lblFechaCompra;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumeroPiezas;
    private javax.swing.JLabel lblRegistro;
    private javax.swing.JLabel lblStock;
    private javax.swing.JLabel lblSubCategoria;
    private javax.swing.JLabel lblTamaño;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JRadioButton rbtnCatalogo;
    private javax.swing.JRadioButton rbtnElectronica;
    private javax.swing.JRadioButton rbtnFarmacia;
    private javax.swing.JRadioButton rbtnMerceria;
    private javax.swing.JRadioButton rbtnPapeleria;
    private javax.swing.JRadioButton rbtnRegalos;
    private javax.swing.JTextField txtBusqueda;
    private java.awt.TextField txtCantidad;
    private java.awt.TextField txtCaracteristicas;
    private java.awt.TextField txtCodigo;
    private java.awt.TextField txtCodigoCatalogo;
    private java.awt.TextField txtColor;
    private java.awt.TextField txtCostoCompra;
    private java.awt.TextField txtCostoVenta;
    private java.awt.TextField txtMarca;
    private java.awt.TextField txtNombre;
    private java.awt.TextField txtNumeroPiezas;
    private java.awt.TextField txtRegistro;
    private java.awt.TextField txtStock;
    private java.awt.TextField txtSubCategoria;
    private java.awt.TextField txtTamaño;
    private java.awt.TextField txtTipo;
    // End of variables declaration//GEN-END:variables
    class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Componentes/fondo login.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}

/*
 * 
 */
package Servicios;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
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
public class AgregarApartados extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    public static String vendedor;
    private int Ind = 0;
    private final String Info[][] = new String[6][3];
    private final String Categoria[] = new String[6];
    private String Cate, code1, code2, code3, code4, code5, code6, Error = "";
    private String Apartado, Nombre, Telefono, Codigos = "";
    private double Total = 0, Dejado = 0;
    private Date Fecha;

    public AgregarApartados() {
        initComponents();
        vendedor = Principal.Ventana_Principal.getNombreVend();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Limpiar() {
        txtNombre.setText(null);
        txtTelefono.setText(null);
        txtApartado.setText(null);
        jdcFecha.setDate(null);
        txtCodigo.setText(null);
        txtTotal.setText(null);
        txtDinero.setText(null);
        txtCodigo.setText(null);
        txtCodigo1.setText(null);
        txtCodigo2.setText(null);
        txtCodigo3.setText(null);
        txtCodigo4.setText(null);
        txtCodigo5.setText(null);
        Apartado = null;
        Nombre = null;
        Telefono = null;
        Codigos = null;
        Total = 0;
        Ind = 0;
        Cate = null;
        code1 = null;
        code2 = null;
        code3 = null;
        code4 = null;
        code5 = null;
        code6 = null;
        for (int i = 0; i < Ind; i++) {
            for (int j = 0; i < 3; i++) {
                Info[i][j] = "";
            }
            Categoria[i] = null;
        }
    }

    public void Validar() {
        code1 = txtCodigo.getText();
        code2 = txtCodigo1.getText();
        code3 = txtCodigo2.getText();
        code4 = txtCodigo3.getText();
        code5 = txtCodigo4.getText();
        code6 = txtCodigo5.getText();
        if (!"".equals(code1)) {
            Ind += 1;
            Info[0][0] = code1;
            System.out.println(Info[0][0]);
        } else {
            Error += ("Casilla 1 ,");
        }
        if (!"".equals(code2)) {
            Ind += 1;
            Info[1][0] = code2;
            System.out.println(Info[1][0]);
        } else {
            Error += ("Casilla 2 ,");
        }
        if (!"".equals(code3)) {
            Ind += 1;
            Info[2][0] = code3;
            System.out.println(Info[2][0]);
        } else {
            Error += ("Casilla 3 ,");
        }
        if (!"".equals(code4)) {
            Ind += 1;
            Info[3][0] = code4;
            System.out.println(Info[3][0]);
        } else {
            Error += ("Casilla 4 ,");
        }
        if (!"".equals(code5)) {
            Ind += 1;
            Info[4][0] = code5;
            System.out.println(Info[4][0]);
        } else {
            Error += ("Casilla 5 ,");
        }
        if (!"".equals(code6)) {
            Ind += 1;
            Info[5][0] = code6;
            System.out.println(Info[5][0]);
        } else {
            Error += ("Casilla 6.");
        }
        System.out.println(Ind);
        if (Ind == 0) {
            JOptionPane.showMessageDialog(null, "Casillas vacias: " + Error);
        } else {
            PreGuardado();
        }
    }

    public void PreGuardado() {
        try {
            int mes = 0;
            for (int i = 0; i < Ind; i++) {
                System.out.println(Info[i][0]);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select * from papeleria where Codigo_Producto='" + Info[i][0] + "';");
                if (rs.next()) {
                    Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                    Info[i][2] = String.valueOf(rs.getDouble("Precio"));
                    Categoria[i] = "papeleria";
                    mes = 1;
                } else {
                    rs = st.executeQuery("select * from regalos where Codigo_Producto='" + Info[i][0] + "';");
                    if (rs.next()) {
                        Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                        Info[i][2] = String.valueOf(rs.getDouble("Precio"));
                        Categoria[i] = "regalos";
                        mes = 1;
                    } else {
                        rs = st.executeQuery("select * from catalogo where Codigo_Producto='" + Info[i][0] + "';");
                        if (rs.next()) {
                            Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                            Info[i][2] = String.valueOf(rs.getDouble("Precio_Venta"));
                            Categoria[i] = "catalogo";
                            mes = 1;
                        } else {
                            rs = st.executeQuery("select * from electronicos where Codigo_Producto='" + Info[i][0] + "';");
                            if (rs.next()) {
                                Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                                Info[i][2] = String.valueOf(rs.getDouble("Precio"));
                                Categoria[i] = "electronicos";
                                mes = 1;
                            } else {
                                rs = st.executeQuery("select * from farmacia where Codigo_Producto='" + Info[i][0] + "';");
                                if (rs.next()) {
                                    Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                                    Info[i][2] = String.valueOf(rs.getDouble("Precio_Venta"));
                                    Categoria[i] = "farmacia";
                                    mes = 1;
                                } else {
                                    rs = st.executeQuery("select * from merceria where Codigo_Producto='" + Info[i][0] + "';");
                                    if (rs.next()) {
                                        Info[i][1] = String.valueOf(rs.getDouble("Stock"));
                                        Info[i][2] = String.valueOf(rs.getDouble("Precio_Venta"));
                                        Categoria[i] = "merceria";
                                        mes = 1;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Producto no encontrado: " + Info[i][0]);
                                        mes = 0;
                                    }
                                }
                            }
                        }
                    }
                }
                Total += Double.parseDouble(Info[i][2]);
            }
            if (mes > 0) {
                btnGuardar.setEnabled(true);
            }
            txtTotal.setText(String.valueOf(Total));
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void AgregarApartado() {
        try {
            Nombre = txtNombre.getText();
            Telefono = txtTelefono.getText();
            Apartado = txtApartado.getText();
            Fecha = jdcFecha.getDate();
            Dejado = Double.parseDouble(txtDinero.getText());
            java.sql.Date dat = new java.sql.Date(Fecha.getTime());
            for (int i = 0; i < Ind; i++) {
                Codigos += (Info[i][0] + " ,");
            }
            PreparedStatement ps = con.prepareStatement("insert into apartados(Nombre, Telefono, Apartado,Fecha,Codigo,Total,Abono,Dejado)values(?,?,?,?,?,?,?,?);");
            ps.setString(1, Nombre);
            ps.setString(2, Telefono);
            ps.setString(3, Apartado);
            ps.setDate(4, dat);
            ps.setString(5, Codigos);
            ps.setDouble(6, Total);
            ps.setDouble(7, 0.0);
            ps.setDouble(8, Dejado);
            int r = ps.executeUpdate();
            if (r > 0) {
                ActualizarStock();
                AgregarVenta();
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ActualizarStock() {
        try {
            for (int i = 0; i < Ind; i++) {
                Double Stock = Double.parseDouble(Info[i][1]);
                Double Sto = Stock - 1;
                Statement st = con.createStatement();
                st.executeQuery("update " + Categoria[i] + " set Stock='" + Sto + "' where Codigo_Producto='" + Info[i][0] + "';");
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void AgregarVenta() {
        try {
            Date fecha5 = new Date();
            java.sql.Date dat5 = new java.sql.Date(fecha5.getTime());
            PreparedStatement ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Compra, Precio_Venta,Categoria,Vendidos,Stock,Vendedor,Caracteristica)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?)");
            ps.setDate(1, dat5);
            ps.setInt(2, 18010001);
            ps.setString(3, Nombre);
            ps.setString(4, "APARTADOS ARRENDADORA");
            ps.setDouble(5, 0.0);
            ps.setDouble(6, Dejado);
            ps.setString(7, "APARTADOS");
            ps.setInt(8, Ind);
            ps.setDouble(9, 0.0);
            ps.setString(10, vendedor);
            ps.setString(11, Apartado);
            int r = ps.executeUpdate();
            if (r > 0) {
                Mensaje();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Mensaje() {
        try {
            String busq = txtNombre.getText();
            String sql = "select * From apartados where idApartado=(Select MAX(idApartado) from apartados);";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("idApartado");
                JOptionPane.showMessageDialog(null, "Guardado exitoso, el numero de referencia es: " + id);
                Limpiar();
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        txtNombre = new java.awt.TextField();
        txtDinero = new java.awt.TextField();
        txtTotal = new java.awt.TextField();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        btnCancelar = new java.awt.Button();
        txtCodigo5 = new java.awt.TextField();
        btnRevisar = new java.awt.Button();
        txtTelefono = new java.awt.TextField();
        txtCodigo4 = new java.awt.TextField();
        txtCodigo3 = new java.awt.TextField();
        txtCodigo = new java.awt.TextField();
        txtCodigo2 = new java.awt.TextField();
        txtCodigo1 = new java.awt.TextField();
        txtApartado = new java.awt.TextField();
        btnGuardar = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar un apartado");
        setIconImage(getIconImage());

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        jdcFecha.setDateFormatString("dd/MM/yyyy");

        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setLabel("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtCodigo5.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo5ActionPerformed(evt);
            }
        });

        btnRevisar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRevisar.setLabel("Revisar");
        btnRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarActionPerformed(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        txtCodigo4.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo4ActionPerformed(evt);
            }
        });

        txtCodigo3.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo3ActionPerformed(evt);
            }
        });

        txtCodigo.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtCodigo2.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo2ActionPerformed(evt);
            }
        });

        txtCodigo1.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        txtCodigo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigo1ActionPerformed(evt);
            }
        });

        txtApartado.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N

        btnGuardar.setEnabled(false);
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setLabel("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setText("Telefono");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel3.setText("Fecha");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setText("Apartado");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setText("Codigo o codigos");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setText("Total");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setText("Dinero dejado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)))
                                .addComponent(txtApartado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnRevisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtCodigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCodigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel5))
                                    .addGap(95, 95, 95)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))))
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigo5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRevisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtCodigo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo5ActionPerformed

    private void btnRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarActionPerformed
        code1 = "";
        code2 = "";
        code3 = "";
        code4 = "";
        code5 = "";
        code6 = "";
        Ind = 0;
        Total = 0;
        Validar();
    }//GEN-LAST:event_btnRevisarActionPerformed

    private void txtCodigo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo4ActionPerformed

    private void txtCodigo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo3ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo2ActionPerformed

    private void txtCodigo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigo1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        AgregarApartado();
    }//GEN-LAST:event_btnGuardarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new AgregarApartados().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnCancelar;
    private java.awt.Button btnGuardar;
    private java.awt.Button btnRevisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private java.awt.TextField txtApartado;
    private java.awt.TextField txtCodigo;
    private java.awt.TextField txtCodigo1;
    private java.awt.TextField txtCodigo2;
    private java.awt.TextField txtCodigo3;
    private java.awt.TextField txtCodigo4;
    private java.awt.TextField txtCodigo5;
    private java.awt.TextField txtDinero;
    private java.awt.TextField txtNombre;
    private java.awt.TextField txtTelefono;
    private java.awt.TextField txtTotal;
    // End of variables declaration//GEN-END:variables
class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Componentes/fondoventanas.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }

}

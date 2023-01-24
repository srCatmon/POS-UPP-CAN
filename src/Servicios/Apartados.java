/*
 * 
 */
package Servicios;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @author CeAnCof software
 */
public class Apartados extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    Double Total = 0.0, Abono = 0.0, Dejado = 0.0, semi = 0.0, tot = 0.0, Fin = 0.0;
    public static String vendedor;
    private String CoD;

    public Apartados() {
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
        txtApartado.setText(null);
        txtTelefono.setText(null);
        txtValor.setText(null);
        txtAbonos.setText(null);
        btnAbonar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        txaTodos = new java.awt.TextArea();
        jPanel1 = new FondoPanel();
        txtApartado = new java.awt.TextField();
        btnMostrarTodos = new java.awt.Button();
        btnCancelar = new java.awt.Button();
        txtNombre = new java.awt.TextField();
        btnSalir = new java.awt.Button();
        btnEliminar = new java.awt.Button();
        btnBuscar = new java.awt.Button();
        btnAbonar = new java.awt.Button();
        txtAbonos = new java.awt.TextField();
        txtReferencia = new java.awt.TextField();
        btnAgregarNuevo = new java.awt.Button();
        txtValor = new java.awt.TextField();
        lblTitulo = new javax.swing.JLabel();
        txtTelefono = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jFrame1.setTitle("Mostrar todos");
        jFrame1.setBounds(new java.awt.Rectangle(100, 250, 550, 350));
        jFrame1.setIconImage(getIconImage());

        txaTodos.setEditable(false);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txaTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txaTodos, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar apartados");
        setIconImage(getIconImage());
        setResizable(false);

        txtApartado.setEditable(false);
        txtApartado.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnMostrarTodos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnMostrarTodos.setLabel("Mostrar todos");
        btnMostrarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTodosActionPerformed(evt);
            }
        });

        btnCancelar.setEnabled(false);
        btnCancelar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnCancelar.setLabel("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnSalir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSalir.setLabel("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnEliminar.setEnabled(false);
        btnEliminar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminar.setLabel("Marcar como pagado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnBuscar.setLabel("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAbonar.setEnabled(false);
        btnAbonar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAbonar.setLabel("Abonar");
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        txtAbonos.setEditable(false);
        txtAbonos.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        txtReferencia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        btnAgregarNuevo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAgregarNuevo.setLabel("Agregar nuevo");
        btnAgregarNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarNuevoActionPerformed(evt);
            }
        });

        txtValor.setEditable(false);

        lblTitulo.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Apartados");

        txtTelefono.setEditable(false);
        txtTelefono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Restan:");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Telefono:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apartado:");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre cliente:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Numero de referencia");

        lblTitulo1.setFont(new java.awt.Font("Tw Cen MT", 1, 16)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo1.setText("Abonos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTitulo1)))
                                .addComponent(txtApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtApartado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lblTitulo1))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMostrarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAgregarNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    private void btnMostrarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTodosActionPerformed
        jFrame1.setVisible(true);
        txaTodos.setText(null);
        try {
            String sql = "SELECT * FROM apartados";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                txaTodos.append("Id        Nombre        Telefono      Apartado       Fecha"
                        + "      Codigo    Total     Abono     Dejado");
                txaTodos.append(System.getProperty("line.separator"));
                txaTodos.append(System.getProperty("line.separator"));
                txaTodos.append(System.getProperty("line.separator"));
                do {
                    int id = rs.getInt("IdApartado");
                    String Nombre = rs.getString("Nombre");
                    String Telefono = rs.getString("Telefono");
                    String Apartado = rs.getString("Apartado");
                    Date Fecha = rs.getDate("Fecha");
                    String Codigo = rs.getString("Codigo");
                    Double Total = rs.getDouble("Total");
                    Double Abono = rs.getDouble("Abono");
                    Double Dejado = rs.getDouble("Dejado");
                    txaTodos.append(id + "      " + Nombre + "      " + Telefono + "      " + Apartado + "      " + Fecha
                            + "      " + Codigo + "      " + Total + "      " + Abono + "      " + Dejado);
                    txaTodos.append(System.getProperty("line.separator"));
                    txaTodos.append(System.getProperty("line.separator"));
                } while (rs.next());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnMostrarTodosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            int busq = Integer.parseInt(txtReferencia.getText());
            PreparedStatement ps = con.prepareStatement("update apartados set Nombre='Cancelado' where idApartado='" + busq + "';");
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Apartado cancelado");
                Limpiar();

            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if (evt.getSource() == btnSalir) {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (evt.getSource() == btnEliminar) {
            try {
                int busq = Integer.parseInt(txtReferencia.getText());
                PreparedStatement ps = con.prepareStatement("update apartados "
                    + "set Apartado='Pagado y Entregado',Total=?,Abono=?,Dejado=? where idApartado=" + busq + ";");
                ps.setDouble(1, 0.0);
                ps.setDouble(2, 0.0);
                ps.setDouble(3, 0.0);
                int r = ps.executeUpdate();
                if (r > 0) {
                    Date fecha = new Date();
                    java.sql.Date dat = new java.sql.Date(fecha.getTime());
                    String Nombre = "Abono a apartado " + busq;
                    String Marca = "Apartados";
                    double Final = Total - (Abono + Dejado);
                    if (Final == 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizo el estatus a 'Entregado'");
                        Limpiar();
                    } else {
                        ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Venta,Categoria,Vendidos,Vendedor)"
                            + "values(?,?,?,?,?,?,?,?)");
                        ps.setDate(1, dat);
                        ps.setInt(2, 18010001);
                        ps.setString(3, Nombre);
                        ps.setString(4, Marca);
                        ps.setDouble(5, Final);
                        ps.setString(6, "Apartados");
                        ps.setInt(7, 1);
                        ps.setString(8, vendedor);
                        r = ps.executeUpdate();
                        if (r > 0) {
                            JOptionPane.showMessageDialog(null, "Se actualizo el estatus a 'Entregado'");
                            Limpiar();
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al  añadir a ventas");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar a entregado");
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (evt.getSource() == btnBuscar) {
            try {
                int busq = Integer.parseInt(txtReferencia.getText());
                String sql = "Select * from apartados where idApartado=" + busq + ";";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    String Nombre = rs.getString("Nombre");
                    if (!Nombre.equals("Cancelado")) {
                        String Telefono = rs.getString("Telefono");
                        String Apartado = rs.getString("Apartado");
                        Total = rs.getDouble("Total");
                        Abono = rs.getDouble("Abono");
                        Dejado = rs.getDouble("Dejado");
                        CoD = rs.getString("Codigo");
                        Double semi = Abono + Dejado;
                        Double tot = Total - semi;
                        txtNombre.setText(Nombre);
                        txtTelefono.setText(Telefono);
                        txtApartado.setText(Apartado);
                        txtValor.setText(String.valueOf(tot));
                        txtAbonos.setEditable(true);
                        btnAbonar.setEnabled(true);
                        btnCancelar.setEnabled(true);
                        btnEliminar.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este apartado ya se ha cancelado");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro un apartado con el numero de referencia: " + busq);
                }
            } catch (NumberFormatException | SQLException e1) {
                JOptionPane.showMessageDialog(null, e1);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        if (evt.getSource() == btnAbonar) {
            try {
                int busq = Integer.parseInt(txtReferencia.getText());
                String sql = "select Total,Abono,Dejado from apartados where idApartado=" + busq + ";";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    double Abonado = rs.getDouble("Abono");
                    Dejado = rs.getDouble("Dejado");
                    Total = rs.getDouble("Total");
                    Abono = Double.parseDouble(txtAbonos.getText());
                    double Tot = (Total - (Dejado + Abonado + Abono));
                    Double cambio = (Abonado + Abono);
                    PreparedStatement ps = con.prepareStatement("Update apartados set "
                        + "Abono=? where idApartado=" + busq + ";");
                    ps.setDouble(1, cambio);
                    int r = ps.executeUpdate();
                    if (r > 0) {
                        Date fecha = new Date();
                        java.sql.Date dat = new java.sql.Date(fecha.getTime());
                        String Nombre = "Abono a apartado " + busq;
                        String Marca = "Apartados";
                        double abo = Double.valueOf(txtAbonos.getText());
                        ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Venta,Categoria,Vendidos,Vendedor)"
                            + "values(?,?,?,?,?,?,?,?)");
                        ps.setDate(1, dat);
                        ps.setInt(2, 18010001);
                        ps.setString(3, Nombre);
                        ps.setString(4, Marca);
                        ps.setDouble(5, abo);
                        ps.setString(6, "Apartados");
                        ps.setInt(7, 1);
                        ps.setString(8, vendedor);
                        r = ps.executeUpdate();
                        if (r > 0) {
                            JOptionPane.showMessageDialog(null, "Abono Exitoso");
                            txtValor.setText(String.valueOf(Tot));
                            txtAbonos.setText(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "error al guardar en ventas");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar el abono");
                    }
                }
            } catch (HeadlessException | NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnAbonarActionPerformed

    private void btnAgregarNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarNuevoActionPerformed
        if (evt.getSource() == btnAgregarNuevo) {
            Servicios.AgregarApartados form = new Servicios.AgregarApartados();
            form.setVisible(true);
        }
    }//GEN-LAST:event_btnAgregarNuevoActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Apartados().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAbonar;
    private java.awt.Button btnAgregarNuevo;
    private java.awt.Button btnBuscar;
    private java.awt.Button btnCancelar;
    private java.awt.Button btnEliminar;
    private java.awt.Button btnMostrarTodos;
    private java.awt.Button btnSalir;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private java.awt.TextArea txaTodos;
    private java.awt.TextField txtAbonos;
    private java.awt.TextField txtApartado;
    private java.awt.TextField txtNombre;
    private java.awt.TextField txtReferencia;
    private java.awt.TextField txtTelefono;
    private java.awt.TextField txtValor;
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

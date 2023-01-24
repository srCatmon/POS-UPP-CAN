/*
 * 
 */
package Servicios;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author CeAnCof Software
 */
public class ConsultarRentas extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    private String Nombre = null, Telefono = null, Direccion = null, Dinero = null, Referencias = null, Renta = null, Fecha;
    private final Double Apartado = 0.0;
    private Double Total = 0.0, din = 0.0, Abono;
    private Date time;
    public static String vendedor;

    public ConsultarRentas() {
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
        txtDireccion.setText(null);
        txtReferencias.setText(null);
        txtDinero.setText(null);
        jdcFecha.setDate(null);
    }

    public void Buscar() {
        try {
            int busq = Integer.parseInt(txtBusqueda.getText());
            String sql = "select * from rentas where idRenta=" + busq + ";";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                Nombre = rs.getString("Nombre");
                if (!Nombre.equals("Cancelado")) {
                    din = rs.getDouble("Apartado");
                    txtNombre.setText(rs.getString("Nombre"));
                    txtTelefono.setText(rs.getString("Telefono"));
                    Telefono = rs.getString("Telefono");
                    txtDireccion.setText(rs.getString("Direccion"));
                    Direccion = rs.getString("Direccion");
                    txtReferencias.setText(rs.getString("Referencias"));
                    Referencias = rs.getString("Referencias");
                    jdcFecha.setDate(rs.getDate("Fecha"));
                    java.util.Date fecha = rs.getDate("Fecha");
                    System.out.println(fecha);
                    Fecha = String.valueOf(fecha);
                    time = rs.getTime("Fecha");
                    txtDinero.setText(String.valueOf(din));
                    Dinero = String.valueOf(din);
                    Renta = rs.getString("Renta");
                    Total = rs.getDouble("Total");
                    btnEliminar.setEnabled(true);
                    btnDetalles.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    btnAbono.setEnabled(true);
                    btnImprimir.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Renta Entregada o cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro una renta con esa referencia");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Formato de los campos incorrecto, por favor reviselo");
        } catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "Fallo al conectar con la base de datos");
        }
    }

    public void Detalles() {
        txaInfo.append("Nombre del cliente: " + Nombre);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Direccion: " + Direccion);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Refencias del lugar: " + Referencias);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Fecha y hora estimada de entrega: " + Fecha + "   " + time);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Telefono: " + Telefono);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Dinero dejado a cuenta: $" + din);
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("El total de la renta es: $" + String.valueOf(Total));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append("Resta por pagar: " + (Total - din));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(System.getProperty("line.separator"));
        txaInfo.append(Renta);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jAbono = new javax.swing.JDialog();
        lblTitulo = new java.awt.Label();
        txtAbono = new java.awt.TextField();
        btnAbonar = new java.awt.Button();
        fDetalles = new javax.swing.JFrame();
        txaInfo = new java.awt.TextArea();
        btnAceptar = new java.awt.Button();
        jPanel1 = new FondoPanel();
        btnBuscar = new java.awt.Button();
        btnSalir = new java.awt.Button();
        btnAgregar = new java.awt.Button();
        btnImprimir = new java.awt.Button();
        btnDetalles = new java.awt.Button();
        btnAbono = new java.awt.Button();
        btnCancelar = new java.awt.Button();
        txtDireccion = new java.awt.TextField();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        txtTelefono = new java.awt.TextField();
        txtDinero = new java.awt.TextField();
        txtNombre = new java.awt.TextField();
        txtReferencias = new java.awt.TextField();
        txtBusqueda = new java.awt.TextField();
        btnEliminar = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jAbono.setBounds(new java.awt.Rectangle(200, 150, 330, 130));

        lblTitulo.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblTitulo.setText("Dinero a abonar");

        txtAbono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnAbonar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAbonar.setLabel("Abonar");
        btnAbonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jAbonoLayout = new javax.swing.GroupLayout(jAbono.getContentPane());
        jAbono.getContentPane().setLayout(jAbonoLayout);
        jAbonoLayout.setHorizontalGroup(
            jAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jAbonoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jAbonoLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jAbonoLayout.createSequentialGroup()
                        .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jAbonoLayout.setVerticalGroup(
            jAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jAbonoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jAbonoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAbonar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        fDetalles.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        fDetalles.setTitle("Detalles");
        fDetalles.setBounds(new java.awt.Rectangle(150, 50, 600, 300));
        fDetalles.setIconImage(getIconImage());

        txaInfo.setEditable(false);
        txaInfo.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        btnAceptar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAceptar.setLabel("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fDetallesLayout = new javax.swing.GroupLayout(fDetalles.getContentPane());
        fDetalles.getContentPane().setLayout(fDetallesLayout);
        fDetallesLayout.setHorizontalGroup(
            fDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fDetallesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        fDetallesLayout.setVerticalGroup(
            fDetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fDetallesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txaInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar rentas");
        setIconImage(getIconImage());
        setResizable(false);

        btnBuscar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnBuscar.setLabel("Buscar");
        btnBuscar.setName(""); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnSalir.setLabel("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAgregar.setLabel("Agregar nuevo");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnImprimir.setEnabled(false);
        btnImprimir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnImprimir.setLabel("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnDetalles.setEnabled(false);
        btnDetalles.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnDetalles.setLabel("Mostrar detalles");
        btnDetalles.setName(""); // NOI18N
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        btnAbono.setEnabled(false);
        btnAbono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnAbono.setLabel("Abono");
        btnAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonoActionPerformed(evt);
            }
        });

        btnCancelar.setEnabled(false);
        btnCancelar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnCancelar.setLabel("Cancelar renta");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtDireccion.setEditable(false);

        jdcFecha.setDateFormatString("EEEE dd MMMM YYYY");
        jdcFecha.setEnabled(false);

        txtTelefono.setEditable(false);

        txtDinero.setEditable(false);

        txtNombre.setEditable(false);

        txtReferencias.setEditable(false);

        txtBusqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBusquedaActionPerformed(evt);
            }
        });

        btnEliminar.setEnabled(false);
        btnEliminar.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnEliminar.setLabel("Marcar como entregada");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Dinero dejado");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Telefono");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Fecha de entrega");

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Referencias");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Numero de referencia");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtDinero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(txtReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtReferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetalles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        Servicios.AgregarRentas frame = new Servicios.AgregarRentas();
        frame.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            int id = Integer.parseInt(txtBusqueda.getText());
            Map parametro = new HashMap();
            parametro.put("idRenta", id);
            InputStream ris = JRLoader.getFileInputStream("C:\\POS\\Reportes/Orden_Servicio.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(ris, parametro, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (NumberFormatException | JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        txaInfo.setText(null);
        Detalles();
        fDetalles.setVisible(true);
    }//GEN-LAST:event_btnDetallesActionPerformed

    private void btnAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonoActionPerformed
        jAbono.setVisible(true);
    }//GEN-LAST:event_btnAbonoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        try {
            String busq = txtBusqueda.getText();
            PreparedStatement ps = con.prepareStatement("update rentas set Nombre='Cancelado' where idRenta=" + busq + ";");
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Renta cancelada");
                Limpiar();
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (SQLException ex2) {
            JOptionPane.showMessageDialog(null, "Fallo al conectar con la base de datos");
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        try {
            String busq = txtBusqueda.getText();
            PreparedStatement ps = con.prepareStatement("delete from rentas where idRenta=" + busq + ";");
            int r = ps.executeUpdate();
            if (r > 0) {
                Date fecha = new Date();
                java.sql.Date dat = new java.sql.Date(fecha.getTime());
                String Nombre = "Pago final a renta: " + busq;
                String Marca = "Renta pagos";
                double Final = Total - Abono;
                ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Venta,Categoria,Vendidos,Vendedor)"
                        + "values(?,?,?,?,?,?,?,?)");
                ps.setDate(1, dat);
                ps.setInt(2, 19010001);
                ps.setString(3, Nombre);
                ps.setString(4, Marca);
                ps.setDouble(5, Final);
                ps.setString(6, "Rentas");
                ps.setInt(7, 1);
                ps.setString(8, vendedor);
                r = ps.executeUpdate();
                if (r > 0) {
                    JOptionPane.showMessageDialog(null, "Renta entregada");
                    Limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al añadir a ventas");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Error al marcar como entregada");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, "Fallo al conectar con la base de datos");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAbonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActionPerformed
        try {
            int busq = Integer.parseInt(txtBusqueda.getText());
            din = Double.parseDouble(txtAbono.getText());
            PreparedStatement ps = con.prepareStatement("update rentas set apartado=? where idRenta=" + busq + ";");
            double abo = Double.valueOf(txtDinero.getText());
            Abono = din + abo;
            ps.setDouble(1, Abono);
            int r = ps.executeUpdate();
            if (r > 0) {
                Date fecha = new Date();
                java.sql.Date dat = new java.sql.Date(fecha.getTime());
                String Nombre = "Abono a renta";
                String Marca = "Renta";
                ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Venta,Categoria,Vendedor)"
                        + "values(?,?,?,?,?,?,?)");
                ps.setObject(1, dat);
                ps.setObject(2, 19010001);
                ps.setObject(3, Nombre);
                ps.setObject(4, Marca);
                ps.setObject(5, din);
                ps.setObject(6, "Rentas");
                ps.setObject(7, vendedor);
                r = ps.executeUpdate();
                if (r > 0) {
                    txtDinero.setText(String.valueOf(Abono));
                    txtAbono.setText(null);
                    jAbono.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Abono realizado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al añadir a ventas");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar abono");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Formato de los datos es incorrecto, por favor reviselos");
        } catch (SQLException ex2) {
            JOptionPane.showMessageDialog(null, ex2);
        }
    }//GEN-LAST:event_btnAbonarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        fDetalles.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ConsultarRentas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAbonar;
    private java.awt.Button btnAbono;
    private java.awt.Button btnAceptar;
    private java.awt.Button btnAgregar;
    private java.awt.Button btnBuscar;
    private java.awt.Button btnCancelar;
    private java.awt.Button btnDetalles;
    private java.awt.Button btnEliminar;
    private java.awt.Button btnImprimir;
    private java.awt.Button btnSalir;
    private javax.swing.JFrame fDetalles;
    private javax.swing.JDialog jAbono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private java.awt.Label lblTitulo;
    private java.awt.TextArea txaInfo;
    private java.awt.TextField txtAbono;
    private java.awt.TextField txtBusqueda;
    private java.awt.TextField txtDinero;
    private java.awt.TextField txtDireccion;
    private java.awt.TextField txtNombre;
    private java.awt.TextField txtReferencias;
    private java.awt.TextField txtTelefono;
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

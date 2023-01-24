/*
 * 
 */
package Reportes;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author CeAnCof Software
 */
public class ReporteEmpleado extends javax.swing.JFrame {
    private double TotalConsul;
    private Date FechaIni, FechaFin;
    public static String vendedor;
    private java.sql.Date dat1, dat2;
    static Connection con = Componentes.Conexion_BD.getConnection();

    public ReporteEmpleado() {
        initComponents();
        vendedor = Principal.Ventana_Principal.getNombreVend();
    }
    
    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }
    
        public void Impresion() {
        try {
            FechaIni = jdcFechaInicial.getDate();
            dat1 = new java.sql.Date(FechaIni.getTime());
            FechaFin = jdcFechaFinal.getDate();
            dat2 = new java.sql.Date(FechaFin.getTime());            
            double TotalConsul2 = 0;
            DefaultTableModel modelo = new DefaultTableModel();
            jtTabla.setModel(modelo);
            PreparedStatement ps = con.prepareStatement("select * from empleado where Fecha_Venta >='" + dat1 + "' and Fecha_Venta<'" + dat2 + "'");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumn = rsMd.getColumnCount();
            modelo.addColumn("Numero de venta");
            modelo.addColumn("Fecha venta");
            modelo.addColumn("Codigo del producto");
            modelo.addColumn("Nombre");
            modelo.addColumn("Marca");
            modelo.addColumn("Precio de venta");
            modelo.addColumn("Vendidos");
            modelo.addColumn("Stock");
            modelo.addColumn("Categoria");
            modelo.addColumn("Vendedor");
            modelo.addColumn("Caracteristica");
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumn];
                for (int i = 0; i < cantidadColumn; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
                Double tota = (Double) rs.getObject(6);
                String marca = (String) rs.getObject(5);
                if (!marca.equals("APARTADOS ARRENDADORA") || !marca.equals("Apartados")) {
                    if (tota > 0) {
                        TotalConsul += tota;
                    } else {
                        TotalConsul2 += (tota * -1);
                    }
                } else {
                    if (tota > 0) {
                        TotalConsul += tota;
                    } else {
                        TotalConsul += (tota * -1);
                    }
                }
            }
            txtTotalConsul.setText(String.valueOf(TotalConsul));
            txtTotalConsul1.setText(String.valueOf(TotalConsul2));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameGasto = new javax.swing.JFrame();
        PanelGasto = new FondoPanel2();
        btnAceptar = new javax.swing.JButton();
        lblConcepto = new javax.swing.JLabel();
        txtConcepto = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btngAgrupar = new javax.swing.ButtonGroup();
        PanelReporteEmpleado = new FondoPanel();
        txtTotalConsul1 = new java.awt.TextField();
        txtTotalConsul = new java.awt.TextField();
        lblFechaFinal = new javax.swing.JLabel();
        lblFechaInicial = new javax.swing.JLabel();
        jdcFechaFinal = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        btnAñadir = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        jdcFechaInicial = new com.toedter.calendar.JDateChooser();
        lblTitulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        FrameGasto.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FrameGasto.setTitle("Añadir Gasto");
        FrameGasto.setBounds(new java.awt.Rectangle(0, 0, 375, 235));
        FrameGasto.setIconImage(getIconImage());
        FrameGasto.setResizable(false);

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblConcepto.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblConcepto.setForeground(new java.awt.Color(255, 255, 255));
        lblConcepto.setText("Concepto");

        txtConcepto.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total");

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Registrar salida de dinero");

        javax.swing.GroupLayout PanelGastoLayout = new javax.swing.GroupLayout(PanelGasto);
        PanelGasto.setLayout(PanelGastoLayout);
        PanelGastoLayout.setHorizontalGroup(
            PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGastoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGastoLayout.createSequentialGroup()
                        .addGroup(PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConcepto)
                            .addComponent(lblTotal))
                        .addGap(18, 18, 18)
                        .addGroup(PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelGastoLayout.createSequentialGroup()
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelGastoLayout.setVerticalGroup(
            PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGastoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(49, 49, 49)
                .addGroup(PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConcepto)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FrameGastoLayout = new javax.swing.GroupLayout(FrameGasto.getContentPane());
        FrameGasto.getContentPane().setLayout(FrameGastoLayout);
        FrameGastoLayout.setHorizontalGroup(
            FrameGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameGastoLayout.setVerticalGroup(
            FrameGastoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte del empleado");
        setBounds(new java.awt.Rectangle(300, 20, 800, 500));
        setIconImage(getIconImage());

        txtTotalConsul1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        txtTotalConsul.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N

        lblFechaFinal.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblFechaFinal.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaFinal.setText("Fecha final");

        lblFechaInicial.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        lblFechaInicial.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaInicial.setText("Fecha inicial");

        jdcFechaFinal.setDateFormatString("dd/MM/yyyy");
        jdcFechaFinal.setOpaque(false);

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAñadir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnAñadir.setText("Añadir gasto");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnImprimir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setEnabled(false);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jtTabla.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jtTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero de venta", "Fecha de venta", "Codigo del producto", "Nombre", "Marca", "Precio", "Vendidos", "Stock", "Categoria", "Vendedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabla.setOpaque(false);
        jScrollPane1.setViewportView(jtTabla);

        jdcFechaInicial.setDateFormatString("dd/MM/yyyy");
        jdcFechaInicial.setOpaque(false);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        lblTitulo.setText("Reporte del  Empleado");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total de las ventas");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sacado de caja");

        javax.swing.GroupLayout PanelReporteEmpleadoLayout = new javax.swing.GroupLayout(PanelReporteEmpleado);
        PanelReporteEmpleado.setLayout(PanelReporteEmpleadoLayout);
        PanelReporteEmpleadoLayout.setHorizontalGroup(
            PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReporteEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReporteEmpleadoLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReporteEmpleadoLayout.createSequentialGroup()
                        .addGroup(PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelReporteEmpleadoLayout.createSequentialGroup()
                                .addComponent(lblFechaInicial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdcFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblFechaFinal)
                                .addGap(10, 10, 10)
                                .addComponent(jdcFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 47, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addGap(18, 18, 18)
                        .addGroup(PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAñadir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(txtTotalConsul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalConsul1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PanelReporteEmpleadoLayout.setVerticalGroup(
            PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReporteEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInicial)
                    .addComponent(jdcFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaFinal)
                    .addComponent(jdcFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelReporteEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReporteEmpleadoLayout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalConsul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalConsul1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelReporteEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelReporteEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            TotalConsul = 0.0;
            PreparedStatement ps;
            ps = con.prepareStatement("delete from empleado");
            int r = ps.executeUpdate();
            if (r > 0) {
                ps = con.prepareStatement("insert into empleado(idVenta,Fecha_Venta,Codigo_Producto"
                    + ",Nombre,Marca,Precio_Venta,Vendidos,Stock,Categoria,Vendedor,Caracteristica) select idVenta,Fecha_Venta,Codigo_Producto"
                    + ",Nombre,Marca,Precio_Venta,Vendidos,Stock,Categoria,Vendedor,Caracteristica from ventas;");
                r = ps.executeUpdate();
                if (r > 0) {
                    Impresion();
                    btnImprimir.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error 2");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        FrameGasto.setVisible(true);
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            Map parametro = new HashMap();
            parametro.put("fechaInicial", dat1);
            parametro.put("fechaFinal", dat2);
            InputStream ris = JRLoader.getFileInputStream("C:\\POS\\Reportes/Reporte.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(ris, parametro, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            Long Codigo = Long.valueOf(20010001);
            String Concepto = txtConcepto.getText();
            String Marca = "dinero sacado de caja";
            double Total = Double.parseDouble(txtTotal.getText());
            double precio = 0.0;
            double tot = (Total * -1);
            int vendi = 1;
            int Stock = 1;
            String Categoria = "General";
            Date Fecha5 = new Date();
            java.sql.Date dat = new java.sql.Date(Fecha5.getTime());
            PreparedStatement ps;
            ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Compra,Precio_Venta,Vendedor,Stock,vendidos,Categoria)"
                + "values(?,?,?,?,?,?,?,?,?,?)");
            ps.setDate(1, dat);
            ps.setLong(2, Codigo);
            ps.setString(3, Concepto);
            ps.setString(4, Marca);
            ps.setDouble(5, precio);
            ps.setDouble(6, tot);
            ps.setString(7, vendedor);
            ps.setInt(8, Stock);
            ps.setInt(9, vendi);
            ps.setString(10, Categoria);
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Se añadio exitosamente");
                FrameGasto.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Formato de los datos incorrecto, por favor reviselos");
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ReporteEmpleado().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrameGasto;
    private javax.swing.JPanel PanelGasto;
    private javax.swing.JPanel PanelReporteEmpleado;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup btngAgrupar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcFechaFinal;
    private com.toedter.calendar.JDateChooser jdcFechaInicial;
    private javax.swing.JTable jtTabla;
    private javax.swing.JLabel lblConcepto;
    private javax.swing.JLabel lblFechaFinal;
    private javax.swing.JLabel lblFechaInicial;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtTotal;
    private java.awt.TextField txtTotalConsul;
    private java.awt.TextField txtTotalConsul1;
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
    
    class FondoPanel2 extends JPanel {
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
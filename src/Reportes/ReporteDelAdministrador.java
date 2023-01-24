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
public class ReporteDelAdministrador extends javax.swing.JFrame {
    private String sql, Nombre, Marca, Nombre2, Marca2, NumId, NumId2;
    private Long Codigo_Producto, Codigo_Producto2;
    private double Precio_Compra, Precio_Venta, Precio_Compra2, Precio_Venta2,Stock,Stock2;
    private Date Fecha;
    public static String vendedor;    
    static Connection con = Componentes.Conexion_BD.getConnection();

    public ReporteDelAdministrador() {
        initComponents();
        vendedor = Principal.Ventana_Principal.getNombreVend();
    }
    
    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }
    
        public void Escarbar() {
        try {
            PreparedStatement ps = con.prepareStatement("select * from regalos where Codigo_Producto=?");
            ResultSet rs;
            ps.setString(1, NumId2);
            rs = ps.executeQuery();
            if (rs.next()) {
                Codigo_Producto2 = rs.getLong("Codigo_Producto");
                Nombre2 = rs.getString("Nombre");
                Marca2 = rs.getString("Marca");
                Precio_Compra2 = rs.getDouble("Precio_Compra");
                Precio_Venta2 = rs.getDouble("Precio");
                Stock2 = rs.getDouble("Stock");
            } else {
                ps = con.prepareStatement("select * from electronicos where Codigo_Producto=?");
                ps.setString(1, NumId2);
                rs = ps.executeQuery();
                if (rs.next()) {
                    Codigo_Producto2 = rs.getLong("Codigo_Producto");
                    Nombre2 = rs.getString("Nombre");
                    Marca2 = rs.getString("Marca");
                    Precio_Compra2 = rs.getDouble("Precio_Compra");
                    Precio_Venta2 = rs.getDouble("Precio");
                    Stock2 = rs.getDouble("Stock");
                } else {
                    ps = con.prepareStatement("select * from farmacia where Codigo_Producto=?");
                    ps.setString(1, NumId2);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        Codigo_Producto2 = rs.getLong("Codigo_Producto");
                        Nombre2 = rs.getString("Nombre");
                        Marca2 = rs.getString("Marca");
                        Precio_Compra2 = rs.getDouble("Precio_Compra");
                        Precio_Venta2 = rs.getDouble("Precio_Venta");
                        Stock2 = rs.getDouble("Stock");
                    } else {
                        ps = con.prepareStatement("select * from papeleria where Codigo_Producto=?");
                        ps.setString(1, NumId2);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            Codigo_Producto2 = rs.getLong("Codigo_Producto");
                            Nombre2 = rs.getString("Nombre");
                            Marca2 = rs.getString("Marca");
                            Precio_Compra2 = rs.getDouble("Precio_Compra");
                            Precio_Venta2 = rs.getDouble("Precio");
                            Stock2 = rs.getDouble("Stock");
                        } else {
                            ps = con.prepareStatement("select * from catalogo where Codigo_Producto=?");
                            ps.setString(1, NumId2);
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                Codigo_Producto2 = rs.getLong("Codigo_Producto");
                                Nombre2 = rs.getString("Nombre");
                                Marca2 = rs.getString("Marca");
                                Precio_Compra2 = rs.getDouble("Precio_Compra");
                                Precio_Venta2 = rs.getDouble("Precio_Venta");
                                Stock2 = 1;
                            } else {
                                ps = con.prepareStatement("select * from merceria where Codigo_Producto=?");
                                ps.setString(1, NumId2);
                                rs = ps.executeQuery();
                                if (rs.next()) {
                                    Codigo_Producto2 = rs.getLong("Codigo_Producto");
                                    Nombre2 = rs.getString("Nombre");
                                    Marca2 = rs.getString("Marca");
                                    Precio_Compra2 = rs.getDouble("Precio_Compra");
                                    Precio_Venta2 = rs.getDouble("Precio_Venta");
                                    Stock2 = rs.getDouble("Stock");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Producto no encontrado");
                                }
                            }
                        }
                    }
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Busqueda() {
        try {
            Double tot = 0.0;
            double tot2 = 0.0;
            DefaultTableModel modelo = new DefaultTableModel();
            jtTabla.setModel(modelo);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumn = rsMd.getColumnCount();
            modelo.addColumn("Numero de venta");
            modelo.addColumn("Fecha venta");
            modelo.addColumn("Codigo del producto");
            modelo.addColumn("Nombre");
            modelo.addColumn("Marca");
            modelo.addColumn("Precio de compra");
            modelo.addColumn("Precio de venta");
            modelo.addColumn("Vendedor");
            modelo.addColumn("Stock");
            modelo.addColumn("Vendidos");
            modelo.addColumn("Categoria");
            modelo.addColumn("Caracteristicas");
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumn];
                for (int i = 0; i < cantidadColumn; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
                Double tota = (Double) rs.getObject(7);
                String marca = (String) rs.getObject(5);
                if (!marca.equals("APARTADOS ARRENDADORA") || !marca.equals("Apartados")) {
                    if (tota > 0) {
                        tot += tota;
                    } else {
                        tot2 += (tota * -1);
                    }
                } else {
                    if (tota > 0) {
                        tot += tota;
                    } else {
                        tot += (tota * -1);
                    }
                }
            }
            txtTotal.setText(String.valueOf(tot));
            txtTotal1.setText(String.valueOf(tot2));
            btnImprimir.setEnabled(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameCambio = new javax.swing.JFrame();
        PanelCambio = new FondoPanel2();
        txtCodigoCam = new java.awt.TextField();
        btnRealizarCam = new java.awt.Button();
        jLabel3 = new javax.swing.JLabel();
        FrameSalida = new javax.swing.JFrame();
        PanelSalida = new FondoPanel2();
        lblConcepto1 = new javax.swing.JLabel();
        txtConcepto1 = new javax.swing.JTextField();
        lblTotal1 = new javax.swing.JLabel();
        txtTotal2 = new javax.swing.JTextField();
        btnAceptar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btngFiltro = new javax.swing.ButtonGroup();
        JPanelReporteAdmin = new FondoPanel()
        ;
        cbCategoria2 = new javax.swing.JComboBox<>();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        lblCategoria2 = new javax.swing.JLabel();
        rbtnFecha = new javax.swing.JRadioButton();
        rbtnProducto = new javax.swing.JRadioButton();
        lblFechaIni = new javax.swing.JLabel();
        btnGasto = new java.awt.Button();
        lblTitulo = new javax.swing.JLabel();
        txtTotal1 = new java.awt.TextField();
        spTabla = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        rbtnCategoria2 = new javax.swing.JRadioButton();
        lblNomCod = new javax.swing.JLabel();
        btnCambio = new java.awt.Button();
        txtNomCod = new java.awt.TextField();
        btnImprimir = new java.awt.Button();
        btnBuscar = new java.awt.Button();
        txtTotal = new java.awt.TextField();
        jdcFechaIni = new com.toedter.calendar.JDateChooser();
        lblFechaFin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        FrameCambio.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FrameCambio.setTitle("Cambio de mercancia");
        FrameCambio.setBounds(new java.awt.Rectangle(0, 0, 350, 160));
        FrameCambio.setIconImage(getIconImage());
        FrameCambio.setResizable(false);

        txtCodigoCam.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnRealizarCam.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnRealizarCam.setLabel("Realizar el cambio");
        btnRealizarCam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarCamActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Realizar cambio de mercancia");

        javax.swing.GroupLayout PanelCambioLayout = new javax.swing.GroupLayout(PanelCambio);
        PanelCambio.setLayout(PanelCambioLayout);
        PanelCambioLayout.setHorizontalGroup(
            PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelCambioLayout.createSequentialGroup()
                        .addComponent(txtCodigoCam, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRealizarCam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        PanelCambioLayout.setVerticalGroup(
            PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(PanelCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRealizarCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoCam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout FrameCambioLayout = new javax.swing.GroupLayout(FrameCambio.getContentPane());
        FrameCambio.getContentPane().setLayout(FrameCambioLayout);
        FrameCambioLayout.setHorizontalGroup(
            FrameCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameCambioLayout.setVerticalGroup(
            FrameCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        FrameSalida.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FrameSalida.setTitle("Salida de dinero");
        FrameSalida.setBounds(new java.awt.Rectangle(0, 0, 380, 210));
        FrameSalida.setIconImage(getIconImage());
        FrameSalida.setResizable(false);

        lblConcepto1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblConcepto1.setForeground(new java.awt.Color(255, 255, 255));
        lblConcepto1.setText("Concepto");

        txtConcepto1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        lblTotal1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTotal1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal1.setText("Total");

        txtTotal2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        btnAceptar1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAceptar1.setText("Aceptar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Registrar salida de dinero");

        javax.swing.GroupLayout PanelSalidaLayout = new javax.swing.GroupLayout(PanelSalida);
        PanelSalida.setLayout(PanelSalidaLayout);
        PanelSalidaLayout.setHorizontalGroup(
            PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSalidaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelSalidaLayout.createSequentialGroup()
                        .addGroup(PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblConcepto1)
                            .addComponent(lblTotal1))
                        .addGap(18, 18, 18)
                        .addGroup(PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelSalidaLayout.createSequentialGroup()
                                .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelSalidaLayout.setVerticalGroup(
            PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSalidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(49, 49, 49)
                .addGroup(PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConcepto1)
                    .addComponent(txtConcepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal1)
                    .addComponent(txtTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FrameSalidaLayout = new javax.swing.GroupLayout(FrameSalida.getContentPane());
        FrameSalida.getContentPane().setLayout(FrameSalidaLayout);
        FrameSalidaLayout.setHorizontalGroup(
            FrameSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameSalidaLayout.setVerticalGroup(
            FrameSalidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte del administrador");
        setBounds(new java.awt.Rectangle(300, 20, 800, 500));
        setIconImage(getIconImage());

        cbCategoria2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "papeleria", "regalos", "farmacia", "electronicos", "merceria", "catalogo" }));
        cbCategoria2.setEnabled(false);

        jdcFechaFin.setDateFormatString("yyyy-MM-dd");
        jdcFechaFin.setEnabled(false);

        lblCategoria2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCategoria2.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria2.setText("Categoria");

        btngFiltro.add(rbtnFecha);
        rbtnFecha.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnFecha.setForeground(new java.awt.Color(255, 255, 255));
        rbtnFecha.setText("Filtro por Fecha");
        rbtnFecha.setOpaque(false);
        rbtnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFechaActionPerformed(evt);
            }
        });

        btngFiltro.add(rbtnProducto);
        rbtnProducto.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnProducto.setForeground(new java.awt.Color(255, 255, 255));
        rbtnProducto.setText("Filtro por producto");
        rbtnProducto.setOpaque(false);
        rbtnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnProductoActionPerformed(evt);
            }
        });

        lblFechaIni.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblFechaIni.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaIni.setText("Fecha Inicial");

        btnGasto.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        btnGasto.setLabel("Salida de dinero");
        btnGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGastoActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        lblTitulo.setText("Reportes del administrador");

        txtTotal1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTotal1.setForeground(new java.awt.Color(0, 102, 0));

        spTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spTablaMouseClicked(evt);
            }
        });

        jtTabla.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
                "Numero de venta", "Fecha de venta", "Codigo de producto", "Nombre", "Marca", "Precio de compra", "Precio de venta", "Vendedor", "Vendidos", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.NE_RESIZE_CURSOR));
        jtTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTablaMouseClicked(evt);
            }
        });
        spTabla.setViewportView(jtTabla);

        rbtnCategoria2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnCategoria2.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCategoria2.setText("Filtro por categoria");
        rbtnCategoria2.setOpaque(false);
        rbtnCategoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCategoria2ActionPerformed(evt);
            }
        });

        lblNomCod.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblNomCod.setForeground(new java.awt.Color(255, 255, 255));
        lblNomCod.setText("Nombre");

        btnCambio.setEnabled(false);
        btnCambio.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        btnCambio.setLabel("Cambio mercancia");
        btnCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambioActionPerformed(evt);
            }
        });

        txtNomCod.setEnabled(false);

        btnImprimir.setEnabled(false);
        btnImprimir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        btnImprimir.setLabel("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnBuscar.setEnabled(false);
        btnBuscar.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N
        btnBuscar.setLabel("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtTotal.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 102, 0));

        jdcFechaIni.setDateFormatString("yyyy-MM-dd");
        jdcFechaIni.setEnabled(false);

        lblFechaFin.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblFechaFin.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaFin.setText("Fecha Final");

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Total:");

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sacado de caja:");

        javax.swing.GroupLayout JPanelReporteAdminLayout = new javax.swing.GroupLayout(JPanelReporteAdmin);
        JPanelReporteAdmin.setLayout(JPanelReporteAdminLayout);
        JPanelReporteAdminLayout.setHorizontalGroup(
            JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTabla)
                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                        .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnFecha)
                                    .addComponent(rbtnProducto))
                                .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lblFechaIni))
                                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNomCod)))
                                .addGap(18, 18, 18)
                                .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                        .addComponent(jdcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblFechaFin)
                                        .addGap(18, 18, 18)
                                        .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtNomCod, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                .addComponent(rbtnCategoria2)
                                .addGap(18, 18, 18)
                                .addComponent(lblCategoria2)
                                .addGap(18, 18, 18)
                                .addComponent(cbCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblTitulo)
                            .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(36, 36, 36)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnGasto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        JPanelReporteAdminLayout.setVerticalGroup(
            JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(25, 25, 25)
                .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                        .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtnFecha)
                                .addComponent(lblFechaIni))
                            .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                                    .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFechaFin)
                                        .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jdcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNomCod, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPanelReporteAdminLayout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblNomCod)
                                        .addComponent(rbtnProducto)))))
                        .addGap(18, 18, 18)
                        .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rbtnCategoria2)
                                .addComponent(lblCategoria2))
                            .addComponent(cbCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPanelReporteAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(JPanelReporteAdminLayout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGasto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(spTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelReporteAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanelReporteAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFechaActionPerformed
        if (rbtnFecha.isSelected()) {
            sql = null;
            txtNomCod.setEnabled(false);
            txtNomCod.setText(null);
            jdcFechaIni.setEnabled(true);
            jdcFechaFin.setEnabled(true);
            btnBuscar.setEnabled(true);
            btnImprimir.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnFechaActionPerformed

    private void rbtnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnProductoActionPerformed
        if (rbtnProducto.isSelected()) {
            sql = null;
            txtNomCod.setEnabled(true);
            jdcFechaIni.setEnabled(false);
            jdcFechaFin.setEnabled(false);
            jdcFechaIni.setDate(null);
            jdcFechaFin.setDate(null);
            btnBuscar.setEnabled(true);
            btnImprimir.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnProductoActionPerformed

    private void btnGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGastoActionPerformed
        FrameSalida.setVisible(true);
    }//GEN-LAST:event_btnGastoActionPerformed

    private void jtTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTablaMouseClicked
        try {
            PreparedStatement ps;
            ResultSet rs;
            int Fila = jtTabla.getSelectedRow();
            NumId = jtTabla.getValueAt(Fila, 0).toString();
            ps = con.prepareStatement("select * from ventas where IdVenta=?");
            ps.setString(1, NumId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Codigo_Producto = rs.getLong("Codigo_Producto");
                Nombre = rs.getString("Nombre");
                Marca = rs.getString("Marca");
                Precio_Compra = rs.getDouble("Precio_Compra");
                Precio_Venta = rs.getDouble("Precio_Venta");
                Stock = rs.getInt("Stock");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jtTablaMouseClicked

    private void spTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spTablaMouseClicked

    }//GEN-LAST:event_spTablaMouseClicked

    private void rbtnCategoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCategoria2ActionPerformed
        if (rbtnCategoria2.isSelected()) {
            cbCategoria2.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnCategoria2ActionPerformed

    private void btnCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambioActionPerformed
        FrameCambio.setVisible(true);
    }//GEN-LAST:event_btnCambioActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            Date fIni = jdcFechaIni.getDate();
            java.sql.Date da1 = new java.sql.Date(fIni.getTime());
            Date fFin = jdcFechaFin.getDate();
            java.sql.Date da2 = new java.sql.Date(fFin.getTime());
            Map parametro = new HashMap();
            parametro.put("fechaInicial", da1);
            parametro.put("fechaFinal", da2);
            InputStream ris = JRLoader.getFileInputStream("C:\\POS\\Reportes/ReporteAdministrador.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(ris, parametro, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            if (rbtnFecha.isSelected()) {
                if (rbtnCategoria2.isSelected()) {
                    Date Ini = jdcFechaIni.getDate();
                    java.sql.Date dat1 = new java.sql.Date(Ini.getTime());
                    Date Fin = jdcFechaFin.getDate();
                    java.sql.Date dat2 = new java.sql.Date(Fin.getTime());
                    String Cate = (String) cbCategoria2.getSelectedItem();
                    if (!Cate.equals("Selecciona")) {
                        sql = "select * from ventas where Categoria='" + Cate + "' and (Fecha_Venta >='" + dat1 + "' and Fecha_Venta<'" + dat2 + "');";
                    } else {
                        sql = "select * from ventas where Fecha_Venta >='" + dat1 + "' and Fecha_Venta<'" + dat2 + "';";
                    }
                    btnCambio.setEnabled(true);
                } else {
                    Date Ini = jdcFechaIni.getDate();
                    java.sql.Date dat1 = new java.sql.Date(Ini.getTime());
                    Date Fin = jdcFechaFin.getDate();
                    java.sql.Date dat2 = new java.sql.Date(Fin.getTime());
                    sql = "select * from ventas where Fecha_Venta >='" + dat1 + "' and Fecha_Venta<'" + dat2 + "'";
                    btnCambio.setEnabled(true);
                }
            }
            if (rbtnProducto.isSelected()) {
                String busq = txtNomCod.getText();
                sql = "select * from ventas where Nombre like '%" + busq + "%';";
                btnCambio.setEnabled(true);
            }
            Busqueda();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        try {
            Long Codigo = Long.valueOf(11090001);
            String Concepto = txtConcepto1.getText();
            String Marca = "dinero sacado de caja";
            double Total = Double.parseDouble(txtTotal2.getText());
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
                FrameSalida.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Ocurrio un error");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Formato de los datos incorrecto, por favor reviselos");
        }
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnRealizarCamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarCamActionPerformed
        try {
            NumId2 = txtCodigoCam.getText();
            Escarbar();
            if (Precio_Venta2 >= Precio_Venta) {
                PreparedStatement ps;
                ps = con.prepareStatement("update ventas set Codigo_Producto=?,"
                        + "Nombre=?,Marca=?,Precio_Compra=?,Stock=? where idVenta=?");
                ps.setLong(1, Codigo_Producto2);
                ps.setString(2, Nombre2);
                ps.setString(3, Marca2);
                ps.setDouble(4, Precio_Compra2);
                ps.setDouble(5, Stock2 - 1);
                ps.setString(6, NumId);
                int r = ps.executeUpdate();
                if (r > 0) {
                    if (Precio_Venta2 > Precio_Venta) {
                        Double Dif = Precio_Venta2 - Precio_Venta;
                        Fecha = new Date();
                        Stock -= 1;
                        java.sql.Date dat = new java.sql.Date(Fecha.getTime());
                        ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,"
                                + "Precio_Compra,Precio_Venta,Vendedor,Stock)values(?,?,?,?,?,?,?,?)");
                        ps.setDate(1, dat);
                        ps.setLong(2, Codigo_Producto2);
                        ps.setString(3, Nombre2);
                        ps.setString(4, Marca2);
                        ps.setDouble(5, Precio_Compra2);
                        ps.setDouble(6, Dif);
                        ps.setString(7, "Administrador");
                        ps.setDouble(8, Stock);
                        r = ps.executeUpdate();
                        if (r > 0) {
                            JOptionPane.showMessageDialog(null, "Cambio exitoso");
                            FrameCambio.dispose();
                            txtCodigoCam.setText(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Ourrio un problema al hacer el cambio 1");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ourrio un problema al hacer el cambio 2");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se puede cambiar este producto ya que su precio es menor");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnRealizarCamActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new ReporteDelAdministrador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrameCambio;
    private javax.swing.JFrame FrameSalida;
    private javax.swing.JPanel JPanelReporteAdmin;
    private javax.swing.JPanel PanelCambio;
    private javax.swing.JPanel PanelSalida;
    private javax.swing.JButton btnAceptar1;
    private java.awt.Button btnBuscar;
    private java.awt.Button btnCambio;
    private java.awt.Button btnGasto;
    private java.awt.Button btnImprimir;
    private java.awt.Button btnRealizarCam;
    private javax.swing.ButtonGroup btngFiltro;
    private javax.swing.JComboBox<String> cbCategoria2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaIni;
    private javax.swing.JTable jtTabla;
    private javax.swing.JLabel lblCategoria2;
    private javax.swing.JLabel lblConcepto1;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaIni;
    private javax.swing.JLabel lblNomCod;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JRadioButton rbtnCategoria2;
    private javax.swing.JRadioButton rbtnFecha;
    private javax.swing.JRadioButton rbtnProducto;
    private javax.swing.JScrollPane spTabla;
    private java.awt.TextField txtCodigoCam;
    private javax.swing.JTextField txtConcepto1;
    private java.awt.TextField txtNomCod;
    private java.awt.TextField txtTotal;
    private java.awt.TextField txtTotal1;
    private javax.swing.JTextField txtTotal2;
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

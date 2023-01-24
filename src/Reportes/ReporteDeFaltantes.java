/*
 *
 */
package Reportes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author CeAnCoft software
 */
public class ReporteDeFaltantes extends javax.swing.JFrame {

    private PreparedStatement ps;
    static Connection con = Componentes.Conexion_BD.getConnection();

    public ReporteDeFaltantes() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void ImprimirFalt() {
        String categoria = (String) cbCategoria.getSelectedItem();
        int faltantes = (Integer) spnFaltantes.getValue();
        String ubicacion;
        if (categoria.equals("Papeleria")) {
            ubicacion = "C:\\POS\\Reportes/Papeleria.jasper";
        } else {
            if (categoria.equals("Farmacia")) {
                ubicacion = "C:\\POS\\Reportes/Farmacia.jasper";
            } else {
                if (categoria.equals("Regalos")) {
                    ubicacion = "C:\\POS\\Reportes/Regalos.jasper";
                } else {
                    if (categoria.equals("Catalogo")) {
                        ubicacion = "C:\\POS\\Reportes/Catalogo.jasper";
                    } else {
                        if (categoria.equals("Merceria")) {
                            ubicacion = "C:\\POS\\Reportes/Merceria.jasper";
                        } else {
                            if (categoria.equals("Electronica")) {
                                ubicacion = "C:\\POS\\Reportes/Electronicos.jasper";
                            } else {
                                JOptionPane.showMessageDialog(null, "Por favor seleccione una categoria");
                                ubicacion = "";
                            }
                        }
                    }
                }
            }
        }
        try {
            Map parametro = new HashMap();
            parametro.put("Stocke", faltantes);
            InputStream ris = JRLoader.getFileInputStream(ubicacion);
            JasperPrint jprint = JasperFillManager.fillReport(ris, parametro, con);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void Faltantes() {
        try {
            int falt = (Integer) spnFaltantes.getValue();
            String cate = (String) cbCategoria.getSelectedItem();
            if (cate.equals("Papeleria")) {
                DefaultTableModel modelo = new DefaultTableModel();
                jtTabla.setModel(modelo);
                ps = con.prepareStatement("select * from papeleria where Stock<='" + falt + "'");
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsMd = rs.getMetaData();
                int cantidadColumn = rsMd.getColumnCount();
                modelo.addColumn("Tipo de venta");
                modelo.addColumn("Codigo de Producto");
                modelo.addColumn("Nombre");
                modelo.addColumn("Marca");
                modelo.addColumn("Tamaño");
                modelo.addColumn("Color");
                modelo.addColumn("Numero de piezas");
                modelo.addColumn("Stock");
                modelo.addColumn("Costo");
                modelo.addColumn("Precio");
                modelo.addColumn("Fecha de compra");
                modelo.addColumn("Precio de compra");
                while (rs.next()) {
                    Object[] filas = new Object[cantidadColumn];
                    for (int i = 0; i < cantidadColumn; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }
                btnImprimirFalt.setEnabled(true);
            } else {
                if (cate.equals("Farmacia")) {
                    DefaultTableModel modelo = new DefaultTableModel();
                    jtTabla.setModel(modelo);
                    ps = con.prepareStatement("select * from farmacia where Stock<='" + falt + "'");
                    ResultSet rs = ps.executeQuery();
                    ResultSetMetaData rsMd = rs.getMetaData();
                    int cantidadColumn = 10;
                    modelo.addColumn("Codigo de producto");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Marca");
                    modelo.addColumn("Cantidad");
                    modelo.addColumn("Caracteristicas");
                    modelo.addColumn("Fecha de caducidad");
                    modelo.addColumn("Fecha de compra");
                    modelo.addColumn("Precio de compra");
                    modelo.addColumn("Precio de venta");
                    modelo.addColumn("Stock");
                    while (rs.next()) {
                        Object[] filas = new Object[cantidadColumn];
                        for (int i = 0; i < cantidadColumn; i++) {
                            filas[i] = rs.getObject(i + 1);
                        }
                        modelo.addRow(filas);
                    }
                    btnImprimirFalt.setEnabled(true);
                } else {
                    if (cate.equals("Regalos")) {
                        DefaultTableModel modelo = new DefaultTableModel();
                        jtTabla.setModel(modelo);
                        ps = con.prepareStatement("select * from regalos where Stock<='" + falt + "'");
                        ResultSet rs = ps.executeQuery();
                        ResultSetMetaData rsMd = rs.getMetaData();
                        int cantidadColumn = 13;
                        modelo.addColumn("Tipo de venta");
                        modelo.addColumn("Codigo del producto");
                        modelo.addColumn("Nombre");
                        modelo.addColumn("Tamaño");
                        modelo.addColumn("Marca");
                        modelo.addColumn("Categoria");
                        modelo.addColumn("Tipo");
                        modelo.addColumn("Cantidad de piezas");
                        modelo.addColumn("Stock");
                        modelo.addColumn("Precio de venta");
                        modelo.addColumn("Fecha de caducidad");
                        modelo.addColumn("Fecha de compra");
                        modelo.addColumn("Precio de compra");
                        while (rs.next()) {
                            Object[] filas = new Object[cantidadColumn];
                            for (int i = 0; i < cantidadColumn; i++) {
                                filas[i] = rs.getObject(i + 1);
                            }
                            modelo.addRow(filas);
                        }
                        btnImprimirFalt.setEnabled(true);
                    } else {
                        if (cate.equals("Catalogo")) {
                            DefaultTableModel modelo = new DefaultTableModel();
                            jtTabla.setModel(modelo);
                            ps = con.prepareStatement("select * from catalogo where Stock<='" + falt + "'");
                            ResultSet rs = ps.executeQuery();
                            ResultSetMetaData rsMd = rs.getMetaData();
                            int cantidadColumn = 11;
                            modelo.addColumn("Codigo de Producto");
                            modelo.addColumn("Nombre");
                            modelo.addColumn("Codigo");
                            modelo.addColumn("Tamaño");
                            modelo.addColumn("Marca");
                            modelo.addColumn("Color");
                            modelo.addColumn("Caracteristicas");
                            modelo.addColumn("Fecha de compra");
                            modelo.addColumn("Precio de compra");
                            modelo.addColumn("Precio de venta");
                            modelo.addColumn("Stock");
                            while (rs.next()) {
                                Object[] filas = new Object[cantidadColumn];
                                for (int i = 0; i < cantidadColumn; i++) {
                                    filas[i] = rs.getObject(i + 1);
                                }
                                modelo.addRow(filas);
                            }
                            btnImprimirFalt.setEnabled(true);
                        } else {
                            if (cate.equals("Electronica")) {
                                DefaultTableModel modelo = new DefaultTableModel();
                                jtTabla.setModel(modelo);
                                ps = con.prepareStatement("select * from electronicos where Stock<='" + falt + "'");
                                ResultSet rs = ps.executeQuery();
                                ResultSetMetaData rsMd = rs.getMetaData();
                                int cantidadColumn = 10;
                                modelo.addColumn("Tipo de venta");
                                modelo.addColumn("Codigo de producto");
                                modelo.addColumn("Nombre");
                                modelo.addColumn("Marca");
                                modelo.addColumn("Caracteristicas");
                                modelo.addColumn("Registro");
                                modelo.addColumn("Stock");
                                modelo.addColumn("Precio de venta");
                                modelo.addColumn("Fecha de compra");
                                modelo.addColumn("Precio de compra");
                                while (rs.next()) {
                                    Object[] filas = new Object[cantidadColumn];
                                    for (int i = 0; i < cantidadColumn; i++) {
                                        filas[i] = rs.getObject(i + 1);
                                    }
                                    modelo.addRow(filas);
                                }
                                btnImprimirFalt.setEnabled(true);
                            } else {
                                if (cate.equals("Merceria")) {
                                    DefaultTableModel modelo = new DefaultTableModel();
                                    jtTabla.setModel(modelo);
                                    ps = con.prepareStatement("select * from merceria where Stock<='" + falt + "'");
                                    ResultSet rs = ps.executeQuery();
                                    ResultSetMetaData rsMd = rs.getMetaData();
                                    int cantidadColumn = 9;
                                    modelo.addColumn("Codigo de producto");
                                    modelo.addColumn("Nombre");
                                    modelo.addColumn("Marca");
                                    modelo.addColumn("Color");
                                    modelo.addColumn("Tamaño");
                                    modelo.addColumn("Fecha de compra");
                                    modelo.addColumn("Precio de compra");
                                    modelo.addColumn("Precio de venta");
                                    modelo.addColumn("Stock");
                                    while (rs.next()) {
                                        Object[] filas = new Object[cantidadColumn];
                                        for (int i = 0; i < cantidadColumn; i++) {
                                            filas[i] = rs.getObject(i + 1);
                                        }
                                        modelo.addRow(filas);
                                    }
                                    btnImprimirFalt.setEnabled(true);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Por favor seleccione una categoria");
                                    btnImprimirFalt.setEnabled(false);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelFaltantes = new FondoPanel();
        btnAceptar = new java.awt.Button();
        btnImprimirFalt = new java.awt.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        rbtnFaltantes = new javax.swing.JRadioButton();
        lblCategoria1 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        spnFaltantes = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte de Faltantes");
        setBounds(new java.awt.Rectangle(300, 20, 900, 550));
        setIconImage(getIconImage());

        btnAceptar.setEnabled(false);
        btnAceptar.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnAceptar.setLabel("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnImprimirFalt.setEnabled(false);
        btnImprimirFalt.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnImprimirFalt.setLabel("Imprimir");
        btnImprimirFalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirFaltActionPerformed(evt);
            }
        });

        jtTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo del Producto", "Nombre", "Marca", "Costo", "Stock", "Categoria", "Caracteristicas"
            }
        ));
        jScrollPane1.setViewportView(jtTabla);

        rbtnFaltantes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnFaltantes.setForeground(new java.awt.Color(255, 255, 255));
        rbtnFaltantes.setText("Reporte de faltantes");
        rbtnFaltantes.setOpaque(false);
        rbtnFaltantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFaltantesActionPerformed(evt);
            }
        });

        lblCategoria1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCategoria1.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoria1.setText("Categoria");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Papeleria", "Regalos", "Farmacia", "Electronica", "Merceria", "Catalogo" }));
        cbCategoria.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Productos minimos");

        spnFaltantes.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        spnFaltantes.setModel(new javax.swing.SpinnerNumberModel(1, 0, null, 1));
        spnFaltantes.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Reporte de faltantes");

        javax.swing.GroupLayout PanelFaltantesLayout = new javax.swing.GroupLayout(PanelFaltantes);
        PanelFaltantes.setLayout(PanelFaltantesLayout);
        PanelFaltantesLayout.setHorizontalGroup(
            PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFaltantesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(PanelFaltantesLayout.createSequentialGroup()
                        .addGroup(PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelFaltantesLayout.createSequentialGroup()
                                .addGroup(PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbtnFaltantes)
                                    .addGroup(PanelFaltantesLayout.createSequentialGroup()
                                        .addGap(230, 230, 230)
                                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelFaltantesLayout.createSequentialGroup()
                                        .addGap(149, 149, 149)
                                        .addComponent(lblCategoria1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImprimirFalt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelFaltantesLayout.setVerticalGroup(
            PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFaltantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFaltantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnFaltantes)
                        .addComponent(lblCategoria1)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(spnFaltantes, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimirFalt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFaltantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelFaltantes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Faltantes();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnImprimirFaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirFaltActionPerformed
        ImprimirFalt();
    }//GEN-LAST:event_btnImprimirFaltActionPerformed

    private void rbtnFaltantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFaltantesActionPerformed
        cbCategoria.setEnabled(true);
        spnFaltantes.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnImprimirFalt.setEnabled(true);
    }//GEN-LAST:event_rbtnFaltantesActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ReporteDeFaltantes().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelFaltantes;
    private java.awt.Button btnAceptar;
    private java.awt.Button btnImprimirFalt;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtTabla;
    private javax.swing.JLabel lblCategoria1;
    private javax.swing.JRadioButton rbtnFaltantes;
    private javax.swing.JSpinner spnFaltantes;
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

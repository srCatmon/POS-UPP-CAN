/*
 * 
 */
package Administracion;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author CeAnCoft Sofware
 */
public class ConsultarTablas extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    public static String Cod;
    private String Filtro, Consulta;

    public static String getCod() {
        return Cod;
    }

    public static void setCod(String Cod) {
        ConsultarTablas.Cod = Cod;
    }

    public ConsultarTablas() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Busqueda() {
        try {
            String cate = (String) cbCategoria.getSelectedItem();
            if (cate.equals("Papeleria")) {
                DefaultTableModel modelo = new DefaultTableModel();
                jtTabla.setModel(modelo);
                PreparedStatement ps = con.prepareStatement(Consulta);
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
                modelo.addColumn("Precio de compra");
                modelo.addColumn("Precio de venta");
                modelo.addColumn("Fecha de compra");
                
                while (rs.next()) {
                    Object[] filas = new Object[cantidadColumn];
                    for (int i = 0; i < cantidadColumn; i++) {
                        filas[i] = rs.getObject(i + 1);
                    }
                    modelo.addRow(filas);
                }
            } else {
                if (cate.equals("Farmacia")) {
                    DefaultTableModel modelo = new DefaultTableModel();
                    jtTabla.setModel(modelo);
                    PreparedStatement ps;
                    ps = con.prepareStatement(Consulta);
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
                } else {
                    if (cate.equals("Regalos")) {
                        DefaultTableModel modelo = new DefaultTableModel();
                        jtTabla.setModel(modelo);
                        PreparedStatement ps;
                        ps = con.prepareStatement(Consulta);
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
                    } else {
                        if (cate.equals("Catalogo")) {
                            DefaultTableModel modelo = new DefaultTableModel();
                            jtTabla.setModel(modelo);
                            PreparedStatement ps;
                            ps = con.prepareStatement(Consulta);
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
                        } else {
                            if (cate.equals("Electronica")) {
                                DefaultTableModel modelo = new DefaultTableModel();
                                jtTabla.setModel(modelo);
                                PreparedStatement ps;
                                ps = con.prepareStatement(Consulta);
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
                            } else {
                                if (cate.equals("Merceria")) {
                                    DefaultTableModel modelo = new DefaultTableModel();
                                    jtTabla.setModel(modelo);
                                    PreparedStatement ps;
                                    ps = con.prepareStatement(Consulta);
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
                                } else {
                                    if (cate.equals("Rentas")) {
                                        DefaultTableModel modelo = new DefaultTableModel();
                                        jtTabla.setModel(modelo);
                                        PreparedStatement ps;
                                        ps = con.prepareStatement("select * from rentas");
                                        ResultSet rs = ps.executeQuery();
                                        ResultSetMetaData rsMd = rs.getMetaData();
                                        int cantidadColumn = 9;
                                        modelo.addColumn("id Renta");
                                        modelo.addColumn("Nombre");
                                        modelo.addColumn("Telefono");
                                        modelo.addColumn("Direccion");
                                        modelo.addColumn("Referencia");
                                        modelo.addColumn("Fecha");
                                        modelo.addColumn("Apartado");
                                        modelo.addColumn("Renta");
                                        modelo.addColumn("Total");
                                        while (rs.next()) {
                                            Object[] filas = new Object[cantidadColumn];
                                            for (int i = 0; i < cantidadColumn; i++) {
                                                filas[i] = rs.getObject(i + 1);
                                            }
                                            modelo.addRow(filas);
                                        }
                                    } else {
                                        if (cate.equals("Apartados")) {
                                            DefaultTableModel modelo = new DefaultTableModel();
                                            jtTabla.setModel(modelo);
                                            PreparedStatement ps;
                                            ps = con.prepareStatement("select * from apartados");
                                            ResultSet rs = ps.executeQuery();
                                            ResultSetMetaData rsMd = rs.getMetaData();
                                            int cantidadColumn = 9;
                                            modelo.addColumn("Id Apartado");
                                            modelo.addColumn("Nombre");
                                            modelo.addColumn("Telefono");
                                            modelo.addColumn("Apartado");
                                            modelo.addColumn("Fecha");
                                            modelo.addColumn("Codigos");
                                            modelo.addColumn("Total");
                                            modelo.addColumn("Abonos");
                                            modelo.addColumn("Dejado");
                                            while (rs.next()) {
                                                Object[] filas = new Object[cantidadColumn];
                                                for (int i = 0; i < cantidadColumn; i++) {
                                                    filas[i] = rs.getObject(i + 1);
                                                }
                                                modelo.addRow(filas);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Por favor seleccione una categoria");
                                        }
                                    }
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

        PanelTablas = new FondoPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTabla = new javax.swing.JTable();
        rbtnFiltro = new javax.swing.JRadioButton();
        txtCodigo = new java.awt.TextField();
        lblCategoria1 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        btnAceptar = new java.awt.Button();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar tablas");
        setBounds(new java.awt.Rectangle(300, 20, 800, 500));
        setIconImage(getIconImage());

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtTablaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jtTablaMousePressed(evt);
            }
        });
        jtTabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtTablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtTabla);

        rbtnFiltro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtnFiltro.setText("Filtrar por nombre");
        rbtnFiltro.setOpaque(false);

        txtCodigo.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N

        lblCategoria1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCategoria1.setText("Tabla de productos");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Papeleria", "Regalos", "Farmacia", "Electronica", "Merceria", "Catalogo", "Rentas", "Apartados" }));
        cbCategoria.setInheritsPopupMenu(true);

        btnAceptar.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        btnAceptar.setLabel("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Nombre del producto: ");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabel2.setText("Consultar tablas");

        javax.swing.GroupLayout PanelTablasLayout = new javax.swing.GroupLayout(PanelTablas);
        PanelTablas.setLayout(PanelTablasLayout);
        PanelTablasLayout.setHorizontalGroup(
            PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 781, Short.MAX_VALUE)
                    .addGroup(PanelTablasLayout.createSequentialGroup()
                        .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelTablasLayout.createSequentialGroup()
                                .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelTablasLayout.createSequentialGroup()
                                        .addComponent(rbtnFiltro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1))
                                    .addGroup(PanelTablasLayout.createSequentialGroup()
                                        .addComponent(lblCategoria1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelTablasLayout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelTablasLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelTablasLayout.setVerticalGroup(
            PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCategoria1)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTablasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnFiltro)
                        .addComponent(jLabel1))
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTablas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTablaMouseClicked

    }//GEN-LAST:event_jtTablaMouseClicked

    private void jtTablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtTablaMousePressed

    }//GEN-LAST:event_jtTablaMousePressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String categ = (String) cbCategoria.getSelectedItem();
        if (categ.equals("Papeleria")) {
            Consulta = "select * from papeleria";
        } else {
            if (categ.equals("Farmacia")) {
                Consulta = "select * from farmacia";
            } else {
                if (categ.equals("Regalos")) {
                    Consulta = "select * from regalos";
                } else {
                    if (categ.equals("Catalogo")) {
                        Consulta = "select * from catalogo";
                    } else {
                        if (categ.equals("Electronica")) {
                            Consulta = "select * from electronicos";
                        } else {
                            if (categ.equals("Merceria")) {
                                Consulta = "select * from merceria";
                            } else {
                                if (categ.equals("Rentas")) {
                                    Consulta = "select * from rentas";
                                } else {
                                    if (categ.equals("Apartados")) {
                                        Consulta = "select * from apartados";
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Por favor seleccione una categoria.");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (rbtnFiltro.isSelected()) {
            Cod = txtCodigo.getText();
            Consulta += (" where Nombre like '%" + Cod + "%'");

        }
        Busqueda();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jtTablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtTablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            String categ = (String) cbCategoria.getSelectedItem();
            CambioCostos form = new CambioCostos();
            if (categ.equals("Papeleria")) {
                int Fila = jtTabla.getSelectedRow();
                Cod = jtTabla.getValueAt(Fila, 1).toString();
            } else {
                if (categ.equals("Farmacia")) {
                    int Fila = jtTabla.getSelectedRow();
                    Cod = jtTabla.getValueAt(Fila, 0).toString();
                } else {
                    if (categ.equals("Regalos")) {
                        int Fila = jtTabla.getSelectedRow();
                        Cod = jtTabla.getValueAt(Fila, 1).toString();
                    } else {
                        if (categ.equals("Catalogo")) {
                            int Fila = jtTabla.getSelectedRow();
                            Cod = jtTabla.getValueAt(Fila, 0).toString();
                        } else {
                            if (categ.equals("Electronica")) {
                                int Fila = jtTabla.getSelectedRow();
                                Cod = jtTabla.getValueAt(Fila, 1).toString();
                            } else {
                                if (categ.equals("Merceria")) {
                                    int Fila = jtTabla.getSelectedRow();
                                    Cod = jtTabla.getValueAt(Fila, 0).toString();
                                }
                            }
                        }
                    }
                }
            }
            form.BuscarExt();
            form.setVisible(true);
        }
    }//GEN-LAST:event_jtTablaKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new ConsultarTablas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTablas;
    private java.awt.Button btnAceptar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtTabla;
    private javax.swing.JLabel lblCategoria1;
    private javax.swing.JRadioButton rbtnFiltro;
    private java.awt.TextField txtCodigo;
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

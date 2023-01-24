/*
 * 
 */
package Servicios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author CeAnCof Software
 */
public class CodigosDeBarras extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    Statement st;
    ResultSet rs;
    Object[] Datos = new Object[2];

    public CodigosDeBarras() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Limpiar() {
        DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
    }

    public String Buscar(String CodigoBusq) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
            st = con.createStatement();
            rs = st.executeQuery("SELECT Nombre FROM papeleria WHERE Codigo_Producto='" + Datos[0] + "';");
            if (rs.next()) {
                Datos[1] = rs.getObject("Nombre");
                model.addRow(Datos);
            } else {
                rs = st.executeQuery("SELECT Nombre FROM regalos WHERE Codigo_Producto='" + CodigoBusq + "';");
                if (rs.next()) {
                    Datos[1] = rs.getObject("Nombre");
                    model.addRow(Datos);
                } else {
                    rs = st.executeQuery("SELECT Nombre FROM farmacia WHERE Codigo_Producto='" + CodigoBusq + "';");
                    if (rs.next()) {
                        Datos[1] = rs.getObject("Nombre");
                        model.addRow(Datos);
                    } else {
                        rs = st.executeQuery("SELECT Nombre FROM electronicos WHERE Codigo_Producto='" + CodigoBusq + "';");
                        if (rs.next()) {
                            Datos[1] = rs.getObject("Nombre");
                            model.addRow(Datos);
                        } else {
                            rs = st.executeQuery("SELECT Nombre FROM catalogo WHERE Codigo_Producto='" + CodigoBusq + "';");
                            if (rs.next()) {
                                Datos[1] = rs.getObject("Nombre");
                                model.addRow(Datos);
                            } else {
                                rs = st.executeQuery("SELECT Nombre FROM merceria WHERE Codigo_Producto='" + CodigoBusq + "';");
                                if (rs.next()) {
                                    Datos[1] = rs.getObject("Nombre");
                                    model.addRow(Datos);
                                } else {
                                    JOptionPane.showMessageDialog(this, "El codigo no fue encontrado");
                                }
                            }
                        }
                    }
                }
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return String.valueOf(Datos[1]);
    }

    public void Uno() {
        Datos[0] = txtCodigoOne.getText();
        Buscar(String.valueOf(Datos[0]));
    }

    public void Generar_Uno() throws IOException, PrinterException {
        try {
            String codigo = String.valueOf(jTabla.getValueAt(0, 0));
            Document doc = new Document();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:\\POS\\Codigos_de_barras/" + codigo + ".pdf"));
            doc.open();
            Barcode128 cod = new Barcode128();
            cod.setCode(codigo);
            com.itextpdf.text.Image img = cod.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
            img.scalePercent(120);
            doc.add(img);
            doc.add(new Paragraph(String.valueOf(Datos[1])));
            doc.close();
            File pdfs = new File("C:\\POS\\Codigos_de_barras/" + codigo + ".pdf");
            Desktop.getDesktop().open(pdfs);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Sigui() {
        Datos[0] = txtCodigoMul.getText();
        Buscar(String.valueOf(Datos[0]));
        txtCodigoMul.setText(null);
    }

    public void Generar_Multi() {
        try {
            Date fecha = new Date();
            String strDateFormat = "EEEE_dd_MMMM_YYYY_hh_mm";
            SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
            Document doc = new Document();
            String Nombre = obj.format(fecha);
            Barcode128 cod = new Barcode128();
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:\\POS\\Codigos_de_barras/codigos de barras " + Nombre + ".pdf"));
            doc.open();
            for (int i = 0; i < jTabla.getRowCount(); i++) {
                cod.setCode(String.valueOf(jTabla.getValueAt(i, 0)));
                com.itextpdf.text.Image img = cod.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                img.scalePercent(120);
                doc.add(img);
                doc.add(new Paragraph(String.valueOf(jTabla.getValueAt(i, 1))));
                doc.add(new Paragraph(" "));
            }
            doc.close();
            File pdfs = new File("C:\\POS\\Codigos_de_barras/codigos de barras " + Nombre + ".pdf");
            Desktop.getDesktop().open(pdfs);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void Categoria() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
            String busq = (String) cbCategoriaAll.getSelectedItem();
            st = con.createStatement();
            rs = st.executeQuery("SELECT Codigo_Producto, Nombre FROM " + busq + ";");
            while (rs.next()) {
                Datos[1] = rs.getObject("Codigo_Producto");
                Datos[0] = rs.getObject("Nombre");
                model.addRow(Datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Generar_All() {
        try {
            com.itextpdf.text.Image img;
            String busq = (String) cbCategoriaAll.getSelectedItem();
            Date fecha = new Date();
            String strDateFormat = "EEEE_dd_MMMM_YYYY_hh_mm";
            SimpleDateFormat obj = new SimpleDateFormat(strDateFormat);
            Document doc = new Document();
            String Nombre1 = "Categoria_" + busq + "_" + obj.format(fecha) + "";
            PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream("C:\\POS\\Codigos_de_barras/codigos de barras " + Nombre1 + ".pdf"));
            doc.open();
            for (int i = 0; i < jTabla.getRowCount(); i++) {
                String cod = String.valueOf(jTabla.getValueAt(i, 1));
                Barcode128 codigo = new Barcode128();
                codigo.setCode(cod);
                img = codigo.createImageWithBarcode(pdf.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
                img.scalePercent(120);
                doc.add(img);
                doc.add(new Paragraph(String.valueOf(jTabla.getValueAt(i, 1))));
                doc.add(new Paragraph(" "));
            }
            doc.close();
            File pdfs = new File("C:\\POS\\Codigos_de_barras/codigos de barras " + Nombre1 + ".pdf");
            Desktop.getDesktop().open(pdfs);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngCode = new javax.swing.ButtonGroup();
        PanelGenerador = new FondoPanel();
        lblCodigoMul = new javax.swing.JLabel();
        txtCodigoMul = new java.awt.TextField();
        btnSiguienteMul = new java.awt.Button();
        lblCategoriaAll = new javax.swing.JLabel();
        btnSalir = new java.awt.Button();
        btnGenerar = new java.awt.Button();
        rbtnOne = new javax.swing.JRadioButton();
        cbCategoriaAll = new javax.swing.JComboBox<>();
        rbtnMulti = new javax.swing.JRadioButton();
        rbtnAll = new javax.swing.JRadioButton();
        lblCodigoOne = new javax.swing.JLabel();
        txtCodigoOne = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();
        btnAceptar = new java.awt.Button();
        btnAceptar2 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generador de codigos de barras");
        setBounds(new java.awt.Rectangle(300, 20, 600, 520));
        setIconImage(getIconImage());
        setResizable(false);

        lblCodigoMul.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCodigoMul.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigoMul.setText("Codigo del producto");

        txtCodigoMul.setEnabled(false);
        txtCodigoMul.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtCodigoMul.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoMulKeyPressed(evt);
            }
        });

        btnSiguienteMul.setEnabled(false);
        btnSiguienteMul.setLabel("Siguiente");
        btnSiguienteMul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteMulActionPerformed(evt);
            }
        });

        lblCategoriaAll.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCategoriaAll.setForeground(new java.awt.Color(255, 255, 255));
        lblCategoriaAll.setText("Categoria");

        btnSalir.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        btnSalir.setLabel("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGenerar.setEnabled(false);
        btnGenerar.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        btnGenerar.setLabel("Generar codigos");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btngCode.add(rbtnOne);
        rbtnOne.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnOne.setForeground(new java.awt.Color(255, 255, 255));
        rbtnOne.setText("Uno solo");
        rbtnOne.setOpaque(false);
        rbtnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnOneActionPerformed(evt);
            }
        });

        cbCategoriaAll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbCategoriaAll.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "papeleria", "farmacia", "regalos", "catalogo", "merceria", "electronicos" }));
        cbCategoriaAll.setEnabled(false);
        cbCategoriaAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaAllActionPerformed(evt);
            }
        });

        btngCode.add(rbtnMulti);
        rbtnMulti.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnMulti.setForeground(new java.awt.Color(255, 255, 255));
        rbtnMulti.setText("Multiples");
        rbtnMulti.setOpaque(false);
        rbtnMulti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnMultiActionPerformed(evt);
            }
        });

        btngCode.add(rbtnAll);
        rbtnAll.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbtnAll.setForeground(new java.awt.Color(255, 255, 255));
        rbtnAll.setText("Todos de un apartado");
        rbtnAll.setOpaque(false);
        rbtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnAllActionPerformed(evt);
            }
        });

        lblCodigoOne.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblCodigoOne.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigoOne.setText("Codigo del producto");

        txtCodigoOne.setEnabled(false);
        txtCodigoOne.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        txtCodigoOne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoOneKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        jLabel1.setText("Generador de codigos de barras");

        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre del producto", "Codigo del producto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTabla);
        if (jTabla.getColumnModel().getColumnCount() > 0) {
            jTabla.getColumnModel().getColumn(0).setResizable(false);
            jTabla.getColumnModel().getColumn(1).setResizable(false);
            jTabla.getColumnModel().getColumn(1).setPreferredWidth(50);
        }

        btnAceptar.setLabel("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnAceptar2.setLabel("Aceptar");
        btnAceptar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelGeneradorLayout = new javax.swing.GroupLayout(PanelGenerador);
        PanelGenerador.setLayout(PanelGeneradorLayout);
        PanelGeneradorLayout.setHorizontalGroup(
            PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGeneradorLayout.createSequentialGroup()
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelGeneradorLayout.createSequentialGroup()
                            .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(lblCodigoOne))
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(lblCodigoMul))
                                        .addComponent(rbtnMulti))))
                            .addGap(30, 30, 30)
                            .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addComponent(txtCodigoMul, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSiguienteMul, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addComponent(txtCodigoOne, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addComponent(cbCategoriaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAceptar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(PanelGeneradorLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbtnOne)
                                .addComponent(jLabel1)
                                .addComponent(rbtnAll)
                                .addGroup(PanelGeneradorLayout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(lblCategoriaAll))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelGeneradorLayout.setVerticalGroup(
            PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelGeneradorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(rbtnOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCodigoOne)
                    .addComponent(txtCodigoOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnMulti)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigoMul)
                    .addComponent(txtCodigoMul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguienteMul, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtnAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCategoriaAll)
                        .addComponent(cbCategoriaAll, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAceptar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PanelGeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelGeneradorLayout.createSequentialGroup()
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGenerador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelGenerador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoMulKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoMulKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Sigui();
        }
    }//GEN-LAST:event_txtCodigoMulKeyPressed

    private void btnSiguienteMulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteMulActionPerformed
        if (evt.getSource() == btnSiguienteMul) {
            Sigui();
        }
    }//GEN-LAST:event_btnSiguienteMulActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        if (rbtnOne.isSelected()) {
            try {
                Generar_Uno();
                txtCodigoOne.setText(null);
                Limpiar();
            } catch (IOException | PrinterException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            if (rbtnMulti.isSelected()) {
                try {
                    Generar_Multi();
                    btnGenerar.setEnabled(false);
                    Limpiar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    Limpiar();
                }
            } else {
                if (rbtnAll.isSelected()) {
                    try {
                        Generar_All();
                        Limpiar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void rbtnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnOneActionPerformed
        if (evt.getSource() == rbtnOne) {
            txtCodigoOne.setEnabled(true);
            btnGenerar.setEnabled(true);
            txtCodigoMul.setEnabled(false);
            cbCategoriaAll.setEnabled(false);
            btnAceptar.setEnabled(true);
            btnAceptar2.setEnabled(false);
            btnSiguienteMul.setEnabled(false);
        }
    }//GEN-LAST:event_rbtnOneActionPerformed

    private void cbCategoriaAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaAllActionPerformed

    }//GEN-LAST:event_cbCategoriaAllActionPerformed

    private void rbtnMultiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnMultiActionPerformed
        if (evt.getSource() == rbtnMulti) {
            txtCodigoMul.setEnabled(true);
            btnGenerar.setEnabled(true);
            btnSiguienteMul.setEnabled(true);
            txtCodigoOne.setEnabled(false);
            cbCategoriaAll.setEnabled(false);
            btnAceptar.setEnabled(false);
            btnAceptar2.setEnabled(false);
        }
    }//GEN-LAST:event_rbtnMultiActionPerformed

    private void rbtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnAllActionPerformed
        if (evt.getSource() == rbtnAll) {
            txtCodigoMul.setEnabled(false);
            btnGenerar.setEnabled(true);
            txtCodigoMul.setEnabled(true);
            cbCategoriaAll.setEnabled(true);
            btnSiguienteMul.setEnabled(false);
            btnAceptar.setEnabled(false);
            btnAceptar2.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnAllActionPerformed

    private void txtCodigoOneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoOneKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoOneKeyPressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Limpiar();
        Uno();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar2ActionPerformed
        Limpiar();
        Categoria();
    }//GEN-LAST:event_btnAceptar2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new CodigosDeBarras().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGenerador;
    private java.awt.Button btnAceptar;
    private java.awt.Button btnAceptar2;
    private java.awt.Button btnGenerar;
    private java.awt.Button btnSalir;
    private java.awt.Button btnSiguienteMul;
    private javax.swing.ButtonGroup btngCode;
    private javax.swing.JComboBox<String> cbCategoriaAll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabla;
    private javax.swing.JLabel lblCategoriaAll;
    private javax.swing.JLabel lblCodigoMul;
    private javax.swing.JLabel lblCodigoOne;
    private javax.swing.JRadioButton rbtnAll;
    private javax.swing.JRadioButton rbtnMulti;
    private javax.swing.JRadioButton rbtnOne;
    private java.awt.TextField txtCodigoMul;
    private java.awt.TextField txtCodigoOne;
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

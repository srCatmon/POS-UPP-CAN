/*
 * 
 */
package Administracion;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author CeAnCof Software
 */
public class MenuEmpleado extends javax.swing.JFrame {

    public MenuEmpleado() {
        initComponents();
    }
    
        @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelEmpleado = new FondoPanel();
        lblTitulo = new javax.swing.JLabel();
        btnGenCodBar = new javax.swing.JButton();
        btnCambCost = new javax.swing.JButton();
        btnConsulFalt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Empleado");
        setBounds(new java.awt.Rectangle(0, 0, 280, 280));
        setIconImage(getIconImage());
        setPreferredSize(new java.awt.Dimension(280, 280));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Menú de administracion");
        lblTitulo.setFocusable(false);
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnGenCodBar.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnGenCodBar.setText("Generador de codigos de barras");
        btnGenCodBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenCodBarActionPerformed(evt);
            }
        });

        btnCambCost.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnCambCost.setText("Cambios de costos");
        btnCambCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambCostActionPerformed(evt);
            }
        });

        btnConsulFalt.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnConsulFalt.setText("Consultar faltantes");
        btnConsulFalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulFaltActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEmpleadoLayout = new javax.swing.GroupLayout(PanelEmpleado);
        PanelEmpleado.setLayout(PanelEmpleadoLayout);
        PanelEmpleadoLayout.setHorizontalGroup(
            PanelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCambCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsulFalt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenCodBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelEmpleadoLayout.setVerticalGroup(
            PanelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenCodBar)
                .addGap(18, 18, 18)
                .addComponent(btnConsulFalt)
                .addGap(18, 18, 18)
                .addComponent(btnCambCost)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenCodBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenCodBarActionPerformed
        Servicios.CodigosDeBarras metod = new Servicios.CodigosDeBarras();
        metod.setVisible(true);
    }//GEN-LAST:event_btnGenCodBarActionPerformed

    private void btnCambCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambCostActionPerformed
        Administracion.CambioCostos metod = new Administracion.CambioCostos();
        metod.setVisible(true);
    }//GEN-LAST:event_btnCambCostActionPerformed

    private void btnConsulFaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulFaltActionPerformed
        Reportes.ReporteDeFaltantes metod = new Reportes.ReporteDeFaltantes();
        metod.setVisible(true);
    }//GEN-LAST:event_btnConsulFaltActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new MenuEmpleado().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelEmpleado;
    private javax.swing.JButton btnCambCost;
    private javax.swing.JButton btnConsulFalt;
    private javax.swing.JButton btnGenCodBar;
    private javax.swing.JLabel lblTitulo;
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

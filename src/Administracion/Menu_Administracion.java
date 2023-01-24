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
 * @author CeAnCof software
 */
public class Menu_Administracion extends javax.swing.JFrame {

    public Menu_Administracion() {
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

        PanelMenu = new FondoPanel();
        btnAdmiProduc = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnAdmiUsuar = new javax.swing.JButton();
        btnReporVent = new javax.swing.JButton();
        btnDinCaja = new javax.swing.JButton();
        btnGenCodBar = new javax.swing.JButton();
        btnConsulFalt = new javax.swing.JButton();
        btnConsulTab = new javax.swing.JButton();
        btnCambCost = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menú de administracion");
        setBounds(new java.awt.Rectangle(0, 0, 280, 525));
        setIconImage(getIconImage());
        setResizable(false);

        btnAdmiProduc.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnAdmiProduc.setText("Administracion de productos");
        btnAdmiProduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmiProducActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Menú de administracion");
        lblTitulo.setFocusable(false);
        lblTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnAdmiUsuar.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnAdmiUsuar.setText("Administracion de usuarios");
        btnAdmiUsuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmiUsuarActionPerformed(evt);
            }
        });

        btnReporVent.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnReporVent.setText("Reporte de ventas");
        btnReporVent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporVentActionPerformed(evt);
            }
        });

        btnDinCaja.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnDinCaja.setText("Consultar dinero en caja");
        btnDinCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDinCajaActionPerformed(evt);
            }
        });

        btnGenCodBar.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnGenCodBar.setText("Generador de codigos de barras");
        btnGenCodBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenCodBarActionPerformed(evt);
            }
        });

        btnConsulFalt.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnConsulFalt.setText("Consultar faltantes");
        btnConsulFalt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulFaltActionPerformed(evt);
            }
        });

        btnConsulTab.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnConsulTab.setText("Consultar tablas");
        btnConsulTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulTabActionPerformed(evt);
            }
        });

        btnCambCost.setFont(new java.awt.Font("Dubai", 1, 15)); // NOI18N
        btnCambCost.setText("Cambios de costos");
        btnCambCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambCostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmiProduc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmiUsuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReporVent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDinCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGenCodBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsulFalt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsulTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCambCost, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdmiProduc)
                .addGap(18, 18, 18)
                .addComponent(btnAdmiUsuar)
                .addGap(18, 18, 18)
                .addComponent(btnReporVent)
                .addGap(18, 18, 18)
                .addComponent(btnDinCaja)
                .addGap(18, 18, 18)
                .addComponent(btnGenCodBar)
                .addGap(18, 18, 18)
                .addComponent(btnConsulFalt)
                .addGap(18, 18, 18)
                .addComponent(btnConsulTab)
                .addGap(18, 18, 18)
                .addComponent(btnCambCost)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambCostActionPerformed
        Administracion.CambioCostos metod = new Administracion.CambioCostos();
        metod.setVisible(true);
    }//GEN-LAST:event_btnCambCostActionPerformed

    private void btnConsulTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulTabActionPerformed
        Administracion.ConsultarTablas metod = new Administracion.ConsultarTablas();
        metod.setVisible(true);
    }//GEN-LAST:event_btnConsulTabActionPerformed

    private void btnConsulFaltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulFaltActionPerformed
        Reportes.ReporteDeFaltantes metod = new Reportes.ReporteDeFaltantes();
        metod.setVisible(true);
    }//GEN-LAST:event_btnConsulFaltActionPerformed

    private void btnGenCodBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenCodBarActionPerformed
        Servicios.CodigosDeBarras metod = new Servicios.CodigosDeBarras();
        metod.setVisible(true);
    }//GEN-LAST:event_btnGenCodBarActionPerformed

    private void btnDinCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDinCajaActionPerformed
        Administracion.AdministracionDineroCaja metod = new Administracion.AdministracionDineroCaja();
        metod.setVisible(true);
    }//GEN-LAST:event_btnDinCajaActionPerformed

    private void btnReporVentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporVentActionPerformed
        Reportes.ReporteDelAdministrador metod = new Reportes.ReporteDelAdministrador();
        metod.setVisible(true);
    }//GEN-LAST:event_btnReporVentActionPerformed

    private void btnAdmiUsuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmiUsuarActionPerformed
        Administracion.AdministracionUsuarios metod = new Administracion.AdministracionUsuarios();
        metod.setVisible(true);
    }//GEN-LAST:event_btnAdmiUsuarActionPerformed

    private void btnAdmiProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdmiProducActionPerformed
        Administracion.Administrar_Productos metod = new Administracion.Administrar_Productos();
        metod.setVisible(true);
    }//GEN-LAST:event_btnAdmiProducActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Menu_Administracion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JButton btnAdmiProduc;
    private javax.swing.JButton btnAdmiUsuar;
    private javax.swing.JButton btnCambCost;
    private javax.swing.JButton btnConsulFalt;
    private javax.swing.JButton btnConsulTab;
    private javax.swing.JButton btnDinCaja;
    private javax.swing.JButton btnGenCodBar;
    private javax.swing.JButton btnReporVent;
    private javax.swing.JLabel lblTitulo;
    // End of variables declaration//GEN-END:variables
}
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
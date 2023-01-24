/*
 * 
 */
package Identificadores;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author CeAnCof Software
 */
public class IdentificacionReportes extends javax.swing.JFrame {
    static Connection con = Componentes.Conexion_BD.getConnection();

    public IdentificacionReportes() {
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

        PanelReportes = new FondoPanel();
        btnCambiar2 = new javax.swing.JButton();
        lblUser2 = new javax.swing.JLabel();
        pfContra2 = new javax.swing.JPasswordField();
        txtUser2 = new javax.swing.JTextField();
        lblcontra2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Identificador de usuario");
        setBounds(new java.awt.Rectangle(200, 200, 320, 190));
        setIconImage(getIconImage());
        setResizable(false);

        btnCambiar2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCambiar2.setText("Aceptar");
        btnCambiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiar2ActionPerformed(evt);
            }
        });

        lblUser2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblUser2.setForeground(new java.awt.Color(255, 255, 255));
        lblUser2.setText("Usuario: ");

        lblcontra2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblcontra2.setForeground(new java.awt.Color(255, 255, 255));
        lblcontra2.setText("Contraseña");

        javax.swing.GroupLayout PanelReportesLayout = new javax.swing.GroupLayout(PanelReportes);
        PanelReportes.setLayout(PanelReportesLayout);
        PanelReportesLayout.setHorizontalGroup(
            PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReportesLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnCambiar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(PanelReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReportesLayout.createSequentialGroup()
                        .addComponent(lblcontra2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pfContra2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelReportesLayout.createSequentialGroup()
                        .addComponent(lblUser2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelReportesLayout.setVerticalGroup(
            PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReportesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser2)
                    .addComponent(txtUser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PanelReportesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblcontra2)
                    .addComponent(pfContra2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnCambiar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelReportes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiar2ActionPerformed
        try {
            String usuario = txtUser2.getText();
            String contraseña = String.valueOf(pfContra2.getPassword());
            String sql = "select * from usuarios_bd where nombre_User='" + usuario + "'  and contraseña_User='" + contraseña + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String det = rs.getString("Rol_User");
                if (det.equals("Administrador")) {
                    this.dispose();
                    txtUser2.setText(null);
                    pfContra2.setText(null);
                    Reportes.ReporteDelAdministrador form = new Reportes.ReporteDelAdministrador();
                    form.setVisible(true);
                } else {
                    if (det.equals("Empleado")) {
                        this.dispose();
                        txtUser2.setText(null);
                        pfContra2.setText(null);
                        Reportes.ReporteEmpleado form = new Reportes.ReporteEmpleado();
                        form.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Este usuario no tiene privilegios");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor intentelo de nuevo");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnCambiar2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new IdentificacionReportes().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelReportes;
    private javax.swing.JButton btnCambiar2;
    private javax.swing.JLabel lblUser2;
    private javax.swing.JLabel lblcontra2;
    private javax.swing.JPasswordField pfContra2;
    private javax.swing.JTextField txtUser2;
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



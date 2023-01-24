/*
 *
 */
package Identificadores;

import Principal.Ventana_Principal;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author CeAnCof Software
 */

public class Login extends javax.swing.JFrame {

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public Login() {
        initComponents();
    }

    private String contraseña;
    public static String Usuario, usuario;

    public static String getUsuario() {
        Login.Usuario = usuario;
        return Usuario;
    }

    public static void setUsuario(String Usuario) {
        Login.Usuario = Usuario;
    }

    public void Entrar() {
        try {
            int resultado;
            String usua = tfUsuario.getText();
            contraseña = String.valueOf(jpfContraseña.getPassword());
            String sql = "select * from usuarios_bd where nombre_User='" + usua + "'  and contraseña_User='" + contraseña + "';";
            Connection con;
            con = Componentes.Conexion_BD.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                resultado = 1;
                usuario = rs.getString("Nombre");
                if (resultado == 1) {
                    this.setVisible(false);
                    getUsuario();
                    JOptionPane.showMessageDialog(null, "Bienvenido: "+ usuario);
                    Ventana_Principal form = new Ventana_Principal();
                    form.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor intentelo de nuevo");
            }
            con.close();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsuario = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jpfContraseña = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Entrar");
        setBounds(new java.awt.Rectangle(350, 200, 250, 200));
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario:");
        getContentPane().add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        lblContraseña.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblContraseña.setText("Contraseña:");
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        tfUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(tfUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 150, 30));

        jpfContraseña.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jpfContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpfContraseñaActionPerformed(evt);
            }
        });
        jpfContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jpfContraseñaKeyPressed(evt);
            }
        });
        getContentPane().add(jpfContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 150, 30));

        btnEntrar.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(51, 51, 51));
        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEntrarKeyPressed(evt);
            }
        });
        getContentPane().add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 86, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingreso al punto de venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Componentes/fondo login.jpg"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 250));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jpfContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpfContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpfContraseñaActionPerformed

    private void jpfContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jpfContraseñaKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Entrar();
        }
    }//GEN-LAST:event_jpfContraseñaKeyPressed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        if (evt.getSource() == btnEntrar) {
            Entrar();
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntrarKeyPressed

    }//GEN-LAST:event_btnEntrarKeyPressed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField jpfContraseña;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables
}

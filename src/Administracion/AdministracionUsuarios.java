/*
 * 
 */
package Administracion;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author CeAnCoft software
 */
public class AdministracionUsuarios extends javax.swing.JFrame {
    private int idUser;
    static Connection con = Componentes.Conexion_BD.getConnection();

    public AdministracionUsuarios() { 
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

        PanelUsuarios = new FondoPanel();
        txtPrivilegio = new java.awt.TextField();
        btnBuscarUser = new javax.swing.JButton();
        btnEliminarUser = new java.awt.Button();
        txtNombreVende = new java.awt.TextField();
        lblUsuario = new javax.swing.JLabel();
        txtContraseña = new java.awt.TextField();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new java.awt.TextField();
        lblContraseña = new javax.swing.JLabel();
        btnAgregarUser = new java.awt.Button();
        txtId = new javax.swing.JTextField();
        btnActualizarUser = new java.awt.Button();
        lblNombreVende = new javax.swing.JLabel();
        lblUsuarioBusq = new javax.swing.JLabel();
        lblPrivilegios = new javax.swing.JLabel();
        txtBusq = new java.awt.TextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administracion de usuarios");
        setBounds(new java.awt.Rectangle(300, 20, 450, 370));
        setIconImage(getIconImage());
        setResizable(false);

        txtPrivilegio.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnBuscarUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscarUser.setText("Buscar");
        btnBuscarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUserActionPerformed(evt);
            }
        });

        btnEliminarUser.setEnabled(false);
        btnEliminarUser.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnEliminarUser.setLabel("Eliminar");
        btnEliminarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUserActionPerformed(evt);
            }
        });

        txtNombreVende.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario");

        txtContraseña.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Id");

        txtUsuario.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        lblContraseña.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(255, 255, 255));
        lblContraseña.setText("Contraseña");

        btnAgregarUser.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnAgregarUser.setLabel("Agregar nuevo");
        btnAgregarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUserActionPerformed(evt);
            }
        });

        txtId.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnActualizarUser.setEnabled(false);
        btnActualizarUser.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnActualizarUser.setLabel("Actualizar");
        btnActualizarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUserActionPerformed(evt);
            }
        });

        lblNombreVende.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblNombreVende.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreVende.setText("Nombre del vendedor");

        lblUsuarioBusq.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblUsuarioBusq.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuarioBusq.setText("Usuario");

        lblPrivilegios.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblPrivilegios.setForeground(new java.awt.Color(255, 255, 255));
        lblPrivilegios.setText("Privilegios");

        txtBusq.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Administracion de usuarios");

        javax.swing.GroupLayout PanelUsuariosLayout = new javax.swing.GroupLayout(PanelUsuarios);
        PanelUsuarios.setLayout(PanelUsuariosLayout);
        PanelUsuariosLayout.setHorizontalGroup(
            PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreVende)
                            .addComponent(lblUsuario)
                            .addComponent(lblContraseña)
                            .addComponent(lblPrivilegios)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreVende, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrivilegio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                                .addComponent(lblUsuarioBusq)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelUsuariosLayout.setVerticalGroup(
            PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(23, 23, 23)
                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBusq, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuarioBusq))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUsuariosLayout.createSequentialGroup()
                                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreVende)
                                    .addComponent(txtNombreVende, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelUsuariosLayout.createSequentialGroup()
                                .addComponent(btnActualizarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsuario))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblContraseña))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPrivilegios)
                            .addComponent(txtPrivilegio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(btnAgregarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelUsuariosLayout.createSequentialGroup()
                        .addComponent(btnBuscarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUserActionPerformed
        try {
            txtNombreVende.setText(null);
            txtId.setText(null);
            txtUsuario.setText(null);
            txtContraseña.setText(null);
            txtPrivilegio.setText(null);
            String usuario = txtBusq.getText();
            String sql = "select * from usuarios_bd where nombre_User='" + usuario + "';";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                idUser = rs.getInt("id_Usuario");
                txtNombreVende.setText(rs.getString("Nombre"));
                txtUsuario.setText(rs.getString("nombre_User"));
                txtContraseña.setText(rs.getString("contraseña_User"));
                txtPrivilegio.setText(rs.getString("Rol_User"));
                txtId.setText(String.valueOf(idUser));
                btnActualizarUser.setEnabled(true);
                btnEliminarUser.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el usuario");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnBuscarUserActionPerformed

    private void btnEliminarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUserActionPerformed
        try {
            PreparedStatement ps;
            idUser = Integer.parseInt(txtId.getText());
            ps = con.prepareStatement("delete from usuarios_bd where id_Usuario='" + idUser + "';");
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            }
        } catch (HeadlessException | SQLException | NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }//GEN-LAST:event_btnEliminarUserActionPerformed

    private void btnAgregarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUserActionPerformed
        try {
            int id = Integer.parseInt(txtId.getText());
            PreparedStatement ps;
            ps = con.prepareStatement("insert into usuarios_bd(id_Usuario,nombre_User,contraseña_User,Rol_User,Nombre) values(?,?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, txtUsuario.getText());
            ps.setString(3, txtContraseña.getText());
            ps.setString(4, txtPrivilegio.getText());
            ps.setString(5, txtNombreVende.getText());
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Usuario agregado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en los datos");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }//GEN-LAST:event_btnAgregarUserActionPerformed

    private void btnActualizarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUserActionPerformed
        try {
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE usuarios_bd SET "
                + "nombre_User=?,"
                + "contraseña_User=?,"
                + "Rol_User=?,"
                + "Nombre=?"
                + "where id_Usuario=?");
            ps.setString(1, txtUsuario.getText());
            ps.setString(2, txtContraseña.getText());
            ps.setString(3, txtPrivilegio.getText());
            ps.setString(4, txtNombreVende.getText());
            ps.setInt(5, idUser);
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Usuario Actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "Error en los datos");
            }
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }//GEN-LAST:event_btnActualizarUserActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new AdministracionUsuarios().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelUsuarios;
    private java.awt.Button btnActualizarUser;
    private java.awt.Button btnAgregarUser;
    private javax.swing.JButton btnBuscarUser;
    private java.awt.Button btnEliminarUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblNombreVende;
    private javax.swing.JLabel lblPrivilegios;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuarioBusq;
    private java.awt.TextField txtBusq;
    private java.awt.TextField txtContraseña;
    private javax.swing.JTextField txtId;
    private java.awt.TextField txtNombreVende;
    private java.awt.TextField txtPrivilegio;
    private java.awt.TextField txtUsuario;
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
/*
 * 
 */
package Administracion;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

/**
 * @author CeAnCof Software
 */
public class AdministracionDineroCaja extends javax.swing.JFrame {
    static Connection con = Componentes.Conexion_BD.getConnection();
    private Calendar c;
    private Date Fecha,FechaBusq,Fecha1,Fecha2,Fecha3;
    private String format, format2, format3, format4;
    private Double Tot = 0.0, Sacado = 0.0, Caja = 0.0;

    public AdministracionDineroCaja() {
        initComponents();
        Fecha = new Date();
        jdcFecha.setDate(Fecha);
    }
    
        @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }
    
    public void Fechas() {
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        c.add(Calendar.WEEK_OF_YEAR, -1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        format = sdf.format(c.getTime());
        try{
            SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd");
            format3 = sdf5.format(c.getTime());
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = f.parse(format3);
            FechaBusq = new Date(d.getTime() + TimeUnit.DAYS.toMillis(1));
            SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy-MM-dd");
            format4 = sdf6.format(FechaBusq);
            System.out.println(FechaBusq);
        }catch(ParseException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void Fecha() {
        Fecha = new Date();
        Date Fecha2 = new Date(Fecha.getTime() + TimeUnit.DAYS.toMillis(1));
        jdcFecha.setDate(Fecha);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        format2 = sdf2.format(Fecha2.getTime());
    }
    
    public void Plazo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Fecha1 = jdcFechaIni.getDate();
        Fecha2 = jdcFechaFin.getDate();
        Fecha3 = new Date(Fecha1.getTime() + TimeUnit.DAYS.toMillis(1));
        format = sdf.format(Fecha1.getTime());
        format2 = sdf.format(Fecha2.getTime());
        format4 = sdf.format(Fecha3.getTime());
    }

    public void Consultar() {
        try {
            Tot = 0.0;
            Double Ventas = 0.0;
            Double Dinero;
            int Sig = 0;
            
            PreparedStatement ps = con.prepareStatement("select * from dinerocaja where Fecha >='" + format + "' and Fecha <'" + format2 + "';");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Dinero = rs.getDouble("Dinero");
                Tot += Dinero;
                System.out.println(Dinero);
                Sig = 1;
            }
            if (Sig > 0) {
                ps = con.prepareStatement("select * from ventas where Fecha_Venta >='" + format4 + "' and Fecha_Venta<'" + format2 + "'");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Double PreVentas = rs.getDouble("Precio_Venta");
                    String Marca = rs.getString("Marca");
                    if (PreVentas > 0) {
                        Ventas += PreVentas;
                    } else {
                        if (Marca.equals("dinero sacado de caja")) {
                            Sacado += (PreVentas * -1);
                        } else {
                            Ventas += (PreVentas * -1);
                        }
                    }
                }
                txtDinero.setText(String.valueOf(Tot));
                txtSacado.setText(String.valueOf(Sacado));
                Double Caja = (Ventas + Tot - Sacado);
                txtCaja.setText(String.valueOf(Caja));
                txtVentas.setText(String.valueOf(Ventas));
                Caja = 0.0;
                Sacado = 0.0;
                System.out.println(Caja);
            }else{
                txtCaja.setText(String.valueOf(0.0));
                txtVentas.setText(String.valueOf(0.0));
                txtDinero.setText(String.valueOf(0.0));
                txtSacado.setText(String.valueOf(0.0));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Guardar() {
        try {
            Double Din = Double.parseDouble(txtDineroNuevo.getText());
            Date FechaG = new Date();
            java.sql.Date dat = new java.sql.Date(FechaG.getTime());
            String sql = "insert into dinerocaja (Fecha,Tipo,Dinero)values(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, dat);
            ps.setString(2, "Entrada de dinero");
            ps.setDouble(3, Din);
            int r = ps.executeUpdate();
            if (r > 0) {
                JOptionPane.showMessageDialog(null, "Operacion realizada");
                txtDineroNuevo.setText(null);
                Caja = 0.0;
                Sacado = 0.0;
            }
        } catch (HeadlessException | NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGDinero = new javax.swing.ButtonGroup();
        PanelDinero = new FondoPanel();
        txtCaja = new java.awt.TextField();
        btnGuardar = new java.awt.Button();
        btnConsultar = new java.awt.Button();
        txtSacado = new java.awt.TextField();
        rbtnSemana = new javax.swing.JRadioButton();
        txtDinero = new java.awt.TextField();
        rbtnFechas = new javax.swing.JRadioButton();
        txtDineroNuevo = new java.awt.TextField();
        jdcFechaIni = new com.toedter.calendar.JDateChooser();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        txtVentas = new java.awt.TextField();
        lblTitulo = new javax.swing.JLabel();
        lblDinDej = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dinero en caja");
        setBounds(new java.awt.Rectangle(300, 20, 630, 320));
        setIconImage(getIconImage());
        setResizable(false);

        txtCaja.setEditable(false);
        txtCaja.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        btnGuardar.setFont(new java.awt.Font("Dubai", 3, 16)); // NOI18N
        btnGuardar.setLabel("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnConsultar.setFont(new java.awt.Font("Dubai", 3, 16)); // NOI18N
        btnConsultar.setLabel("Consultar");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        txtSacado.setEditable(false);
        txtSacado.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        btnGDinero.add(rbtnSemana);
        rbtnSemana.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        rbtnSemana.setForeground(new java.awt.Color(255, 255, 255));
        rbtnSemana.setSelected(true);
        rbtnSemana.setText("Semana actual");
        rbtnSemana.setOpaque(false);
        rbtnSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSemanaActionPerformed(evt);
            }
        });

        txtDinero.setEditable(false);
        txtDinero.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        btnGDinero.add(rbtnFechas);
        rbtnFechas.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        rbtnFechas.setForeground(new java.awt.Color(255, 255, 255));
        rbtnFechas.setText("Fechas");
        rbtnFechas.setOpaque(false);
        rbtnFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnFechasActionPerformed(evt);
            }
        });

        txtDineroNuevo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jdcFechaIni.setDateFormatString("dd/MM/yyyy");
        jdcFechaIni.setEnabled(false);

        jdcFechaFin.setDateFormatString("dd/MM/yyyy");
        jdcFechaFin.setEnabled(false);

        jdcFecha.setDateFormatString("EEEE dd MMMM YYYY");
        jdcFecha.setEnabled(false);

        txtVentas.setEditable(false);
        txtVentas.setFont(new java.awt.Font("Dubai", 0, 14)); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Dubai", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Dinero sacado de caja");

        lblDinDej.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblDinDej.setForeground(new java.awt.Color(255, 255, 255));
        lblDinDej.setText("Dinero dejado");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ventas de la semana");

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dinero sacado");

        jLabel2.setFont(new java.awt.Font("Dubai", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("En caja");

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha actual");

        javax.swing.GroupLayout PanelDineroLayout = new javax.swing.GroupLayout(PanelDinero);
        PanelDinero.setLayout(PanelDineroLayout);
        PanelDineroLayout.setHorizontalGroup(
            PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDineroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelDineroLayout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDineroNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(rbtnSemana)
                    .addComponent(lblTitulo)
                    .addGroup(PanelDineroLayout.createSequentialGroup()
                        .addComponent(rbtnFechas)
                        .addGap(18, 18, 18)
                        .addComponent(jdcFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDineroLayout.createSequentialGroup()
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDinDej)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSacado, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtCaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        PanelDineroLayout.setVerticalGroup(
            PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDineroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(rbtnSemana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(rbtnFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcFechaFin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDinDej)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDineroLayout.createSequentialGroup()
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCaja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addGroup(PanelDineroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jdcFecha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDineroNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))))
                    .addGroup(PanelDineroLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDinero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelDinero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if(rbtnSemana.isSelected()){
            Fecha();
            Fechas();
            Consultar();
        }
        if(rbtnFechas.isSelected()){
            Plazo();
            Consultar();
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void rbtnSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSemanaActionPerformed
        if(evt.getSource() == rbtnSemana){
            jdcFechaIni.setEnabled(false);
            jdcFechaFin.setEnabled(false);
            jdcFechaIni.setDate(null);
            jdcFechaFin.setDate(null);
        }
    }//GEN-LAST:event_rbtnSemanaActionPerformed

    private void rbtnFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnFechasActionPerformed
        if(evt.getSource() == rbtnFechas){
            jdcFechaIni.setEnabled(true);
            jdcFechaFin.setEnabled(true);
        }
    }//GEN-LAST:event_rbtnFechasActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new AdministracionDineroCaja().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDinero;
    private java.awt.Button btnConsultar;
    private javax.swing.ButtonGroup btnGDinero;
    private java.awt.Button btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private com.toedter.calendar.JDateChooser jdcFechaIni;
    private javax.swing.JLabel lblDinDej;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JRadioButton rbtnFechas;
    private javax.swing.JRadioButton rbtnSemana;
    private java.awt.TextField txtCaja;
    private java.awt.TextField txtDinero;
    private java.awt.TextField txtDineroNuevo;
    private java.awt.TextField txtSacado;
    private java.awt.TextField txtVentas;
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

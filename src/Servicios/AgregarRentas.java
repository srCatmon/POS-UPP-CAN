/*
 * 
 */
package Servicios;

import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author CeAnCof Software
 */
public class AgregarRentas extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    Properties propiedades;
    InputStream entrada;
    private double Brincolin, InflableChicoNiño, InflableChicoNiña, InflableGrande, Audio,
            Sonorizacion1, Sonorizacion2, Karaoke, Carpa3x6, Carpa5x5, Carpa5x10,
            Carpa10x10, Toldo3x6, Toldo5x5, Toldo5x10, Toldo10x10, Salon,
            Cubremanteles, CubremantelesSalon, Moño, MantelCircular, MantelRectangular, PaqueteMesasCirulares1,
            PaqueteMesasCirulares2, PaqueteMesasRectangulares1, PaqueteMesasRectangulares2,
            SillasExtra, MesasExtra, PaqueteMesasCirularesSalon, PaqueteMesasRectangularesSalon,
            SillasExtrasSalon, MantelExtra;

    private double Costo = 0;
    private String Nombre, Telefono, Direccion, Referencias, Renta = "";
    private Date Fecha;
    private double Total, Apartado;
    private int contador1, contador2, contador3, contador4, contador5, contador6, contador7, contador8;
    public static String vendedor;

    public AgregarRentas() {
        initComponents();
        vendedor = Principal.Ventana_Principal.getNombreVend();
        try {
            propiedades = new Properties();
            entrada = null;
            entrada = new FileInputStream("C:\\POS/RentasCostos.properties");
            propiedades.load(entrada);
            Brincolin = Double.parseDouble(propiedades.getProperty("Brincolin"));
            InflableChicoNiña = Double.parseDouble(propiedades.getProperty("InflableChicoNiña"));
            InflableChicoNiño = Double.parseDouble(propiedades.getProperty("InflableChicoNiño"));
            InflableGrande = Double.parseDouble(propiedades.getProperty("InflableGrande"));
            Audio = Double.parseDouble(propiedades.getProperty("Audio"));
            Sonorizacion1 = Double.parseDouble(propiedades.getProperty("Sonorizacion1"));
            Sonorizacion2 = Double.parseDouble(propiedades.getProperty("Sonorizacion2"));
            Karaoke = Double.parseDouble(propiedades.getProperty("Karaoke"));
            Carpa3x6 = Double.parseDouble(propiedades.getProperty("Carpa3x6"));
            Carpa5x5 = Double.parseDouble(propiedades.getProperty("Carpa5x5"));
            Carpa5x10 = Double.parseDouble(propiedades.getProperty("Carpa5x10"));
            Carpa10x10 = Double.parseDouble(propiedades.getProperty("Carpa10x10"));
            Toldo3x6 = Double.parseDouble(propiedades.getProperty("Toldo3x6"));
            Toldo5x5 = Double.parseDouble(propiedades.getProperty("Toldo5x5"));
            Toldo5x10 = Double.parseDouble(propiedades.getProperty("Toldo5x10"));
            Toldo10x10 = Double.parseDouble(propiedades.getProperty("Toldo10x10"));
            Salon = Double.parseDouble(propiedades.getProperty("Salon"));
            Cubremanteles = Double.parseDouble(propiedades.getProperty("Cubremanteles"));
            CubremantelesSalon = Double.parseDouble(propiedades.getProperty("CubremantelesSalon"));
            Moño = Double.parseDouble(propiedades.getProperty("Moño"));
            MantelCircular = Double.parseDouble(propiedades.getProperty("MantelCircular"));
            MantelRectangular = Double.parseDouble(propiedades.getProperty("MantelRectangular"));
            PaqueteMesasCirulares1 = Double.parseDouble(propiedades.getProperty("PaqueteMesasCirulares1"));
            PaqueteMesasCirulares2 = Double.parseDouble(propiedades.getProperty("PaqueteMesasCirulares2"));
            PaqueteMesasRectangulares1 = Double.parseDouble(propiedades.getProperty("PaqueteMesasRectangulares1"));
            PaqueteMesasRectangulares2 = Double.parseDouble(propiedades.getProperty("PaqueteMesasRectangulares2"));
            SillasExtra = Double.parseDouble(propiedades.getProperty("SillasExtra"));
            MesasExtra = Double.parseDouble(propiedades.getProperty("MesasExtra"));
            PaqueteMesasCirularesSalon = Double.parseDouble(propiedades.getProperty("PaqueteMesasCirularesSalon"));
            PaqueteMesasRectangularesSalon = Double.parseDouble(propiedades.getProperty("PaqueteMesasRectangularesSalon"));
            SillasExtrasSalon = Double.parseDouble(propiedades.getProperty("SillasExtrasSalon"));
            MantelExtra = Double.parseDouble(propiedades.getProperty("MantelExtra"));

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Obtener() {
        try {
            Nombre = txtNombre.getText();
            Telefono = txtTelefono.getText();
            Direccion = txtDireccion.getText();
            Referencias = txtReferencias.getText();
            Fecha = jdcFecha.getDate();
            String Tot = txtTotal.getText();
            Total = Double.parseDouble(Tot);
            String Apar = txtDinero.getText();
            Apartado = Double.parseDouble(Apar);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de los datos incorrecto, por favor reviselos");
        }
    }

    public void Guardar() {
        Obtener();
        Renta();
        java.sql.Date dat = new java.sql.Date(Fecha.getTime());
        try {
            PreparedStatement ps = con.prepareStatement("insert into rentas("
                    + "Nombre,Telefono,Direccion,Referencias,Fecha,Apartado,"
                    + "Renta,Total)values(?,?,?,?,?,?,?,?)");
            ps.setString(1, Nombre);
            ps.setString(2, Telefono);
            ps.setString(3, Direccion);
            ps.setString(4, Referencias);
            ps.setDate(5, dat);
            ps.setDouble(6, Apartado);
            ps.setString(7, Renta);
            ps.setDouble(8, Total);
            int r = ps.executeUpdate();
            if (r > 0) {
                System.out.println("1");
                String busq = txtNombre.getText();
                String sql = "select idRenta from rentas where Nombre='" + busq + "';";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    System.out.println("2");
                    int id = rs.getInt("idRenta");
                    String nombre = "Renta";
                    String Marca = "Renta";
                    Date Fecha5 = new Date();
                    java.sql.Date dat5 = new java.sql.Date(Fecha5.getTime());
                    ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Venta,Categoria,Vendidos,Vendedor)"
                            + "values(?,?,?,?,?,?,?,?)");
                    ps.setDate(1, dat5);
                    ps.setInt(2, 19010001);
                    ps.setString(3, nombre);
                    ps.setString(4, Marca);
                    ps.setDouble(5, Apartado);
                    ps.setString(6, "Rentas");
                    ps.setInt(7, 1);
                    ps.setString(8, vendedor);
                    r = ps.executeUpdate();
                    if (r > 0) {
                        System.out.println("3");
                        JOptionPane.showMessageDialog(null, "Guardado exitoso, el numero de referencia es: " + id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error 3");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error 2");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error 1");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Renta() {
        Renta = "";
        if (rbtnInfChi1.isSelected()) {
            Renta += " Inflable chico niño,";
        }
        if (rbtnInfChi2.isSelected()) {
            Renta += " Inflable chico niña,";
        }
        if (rbtnInfGran.isSelected()) {
            Renta += " Inflable grande,";
        }
        if (rbtnBrinco.isSelected()) {
            Renta += " Brincolin,";
        }
        if (rbtnAudio.isSelected()) {
            Renta += " Audio,";
        }
        if (rbtnSono1.isSelected()) {
            Renta += " Sonorizacion 1,";
        }
        if (rbtnSono2.isSelected()) {
            Renta += " Sonorizacion 2,";
        }
        if (rbtnKaraoke.isSelected()) {
            Renta += " Karaoke,";
        }
        if (rbtnCar36.isSelected()) {
            Renta += " Carpa 3x6,";
        }
        if (rbtnCar55.isSelected()) {
            Renta += " Carpa 5x5,";
        }
        if (rbtnCar510.isSelected()) {
            Renta += " Carpa 5x10,";
        }
        if (rbtnCar1010.isSelected()) {
            Renta += " Carpa 10x10,";
        }
        if (rbtnTol36.isSelected()) {
            Renta += " Toldo 3x6,";
        }
        if (rbtnTol55.isSelected()) {
            Renta += " Toldo 5x5,";
        }
        if (rbtnTol510.isSelected()) {
            Renta += " Toldo 5x10,";
        }
        if (rbtnTol1010.isSelected()) {
            Renta += " Toldo 10x10,";
        }
        if (rbtnSalonL.isSelected()) {
            Renta += " Salon,";
        }
        if (rbtnCubreman.isSelected()) {
            contador1 = (Integer) spnPaqMesCir.getValue();
            contador1 += (Integer) spnPaqMesRec.getValue();
            Renta += (" " + contador1 + " Cubremanteles,");
        }
        if (rbtnCubreMan.isSelected()) {
            contador2 = 0;
            contador2 += (Integer) spnMesRecSal.getValue();
            contador2 += (Integer) spnMesCirSal.getValue();
            Renta += (" " + contador2 + " Cubremanteles salon,");
        }
        if (rbtnMoño.isSelected()) {

            Renta += " Moños para silla,";
        }
        if (rbtnMantelcir.isSelected()) {
            Renta += " Manteles circulares,";
        }
        if (rbtnMantelrec.isSelected()) {
            Renta += " Manteles rectangulares,";
        }
        contador1 = (Integer) spnPaqMesCir.getValue();
        contador2 = (Integer) spnPaqMesRec.getValue();
        contador3 = (Integer) spnMesRecSal.getValue();
        contador4 = (Integer) spnMesCirSal.getValue();
        contador5 = (Integer) spnSilExt.getValue();
        contador6 = (Integer) spnMesExt.getValue();
        contador7 = (Integer) spnSilExtSal.getValue();
        contador8 = (Integer) spnManExt.getValue();
        if (contador1 > 0) {
            Renta += (" " + contador1 + " Paquetes de mesas circulares,");
        }
        if (contador2 > 0) {
            Renta += (" " + contador2 + " Paquetes de mesas Rectangulares,");
        }
        if (contador3 > 0) {
            Renta += (" " + contador3 + " Paquetes de mesas circulares salon,");
        }
        if (contador4 > 0) {
            Renta += (" " + contador4 + " Paquetes de mesas rectangulares salon,");
        }
        if (contador5 > 0) {
            Renta += (" " + contador5 + " Sillas extras,");
        }
        if (contador6 > 0) {
            Renta += (" " + contador6 + " Mesas extras,");
        }
        if (contador7 > 0) {
            Renta += (" " + contador7 + " Sillas extras salon,");
        }
        if (contador8 > 0) {
            Renta += (" " + contador8 + " Manteles extras,");
        }
    }

    public void Calculo() {
        Double CostoPaq1, CostoPaq2, CostoPaqSal1 = 0.0, CostoPaqSal2 = 0.0, CostoMesCirSal = 0.0,
                CostoMesRecSal = 0.0, CostoMesExtSal = 0.0, CostoMesExt = 0.0, CostoSilExtSal = 0.0,
                CostoSilExt = 0.0, CostoManExt = 0.0;

        int contador1 = (Integer) spnPaqMesCir.getValue();
        if (contador1 > 0 && contador1 > 3) {
            CostoPaq1 = PaqueteMesasCirulares2 * contador1;
        } else {
            CostoPaq1 = PaqueteMesasCirulares1 * contador1;
        }
        int contador2 = (Integer) spnPaqMesRec.getValue();
        if (contador2 > 0 && contador2 > 3) {
            CostoPaq2 = PaqueteMesasRectangulares2 * contador2;
        } else {
            CostoPaq2 = PaqueteMesasRectangulares1 * contador2;
        }
        int contador3 = (Integer) spnMesCirSal.getValue();
        if (contador3 > 0) {
            CostoMesCirSal = PaqueteMesasCirularesSalon * contador3;
        }
        int contador4 = (Integer) spnMesRecSal.getValue();
        if (contador4 > 0) {
            CostoMesRecSal = PaqueteMesasRectangularesSalon * contador4;
        }
        int contador5 = (Integer) spnSilExt.getValue();
        if (contador5 > 0) {
            CostoSilExt = SillasExtra * contador5;
        }
        int contador6 = (Integer) spnMesExt.getValue();
        if (contador6 > 0) {
            CostoMesExt = MesasExtra * contador6;
        }
        int contador7 = (Integer) spnSilExtSal.getValue();
        if (contador7 > 0) {
            CostoSilExtSal = SillasExtrasSalon * contador7;
        }
        int contador8 = (Integer) spnManExt.getValue();
        if (contador8 > 0) {
            CostoManExt = MantelExtra * contador8;
        }

        Costo += CostoPaq1 + CostoPaq2 + CostoPaqSal1 + CostoPaqSal2 + CostoMesCirSal
                + CostoMesRecSal + CostoMesExtSal + CostoMesExt + CostoSilExtSal
                + CostoSilExt + CostoManExt;

        double flete = Double.parseDouble(txtFlete.getText());
        Costo += flete;
        if (rbtnInfChi1.isSelected()) {
            Costo += InflableChicoNiño;
        }
        if (rbtnInfChi2.isSelected()) {
            Costo += InflableChicoNiña;
        }
        if (rbtnInfGran.isSelected()) {
            Costo += InflableGrande;
        }
        if (rbtnBrinco.isSelected()) {
            Costo += Brincolin;
        }
        if (rbtnAudio.isSelected()) {
            Costo += Audio;
        }
        if (rbtnSono1.isSelected()) {
            Costo += Sonorizacion1;
        }
        if (rbtnSono2.isSelected()) {
            Costo += Sonorizacion2;
        }
        if (rbtnKaraoke.isSelected()) {
            Costo += Karaoke;
        }
        if (rbtnCar36.isSelected()) {
            Costo += Carpa3x6;
        }
        if (rbtnCar55.isSelected()) {
            Costo += Carpa5x5;
        }
        if (rbtnCar510.isSelected()) {
            Costo += Carpa5x10;
        }
        if (rbtnCar1010.isSelected()) {
            Costo += Carpa10x10;
        }
        if (rbtnTol36.isSelected()) {
            Costo += Toldo3x6;
        }
        if (rbtnTol55.isSelected()) {
            Costo += Toldo5x5;
        }
        if (rbtnTol510.isSelected()) {
            Costo += Toldo5x10;
        }
        if (rbtnTol1010.isSelected()) {
            Costo += Toldo10x10;
        }
        if (rbtnSalonL.isSelected()) {
            Costo += Salon;
        }
        if (rbtnCubreman.isSelected()) {
            contador1 = (Integer) spnPaqMesRec.getValue();
            contador1 += (Integer) spnPaqMesCir.getValue();
            Costo += (contador1 * Cubremanteles);
        }
        if (rbtnCubreMan.isSelected()) {
            contador2 = (Integer) spnMesRecSal.getValue();
            contador2 += (Integer) spnMesCirSal.getValue();
            Costo += (contador2 * CubremantelesSalon);
        }
        if (rbtnMoño.isSelected()) {
            contador3 = (Integer) spnPaqMesRec.getValue();
            contador3 += (Integer) spnPaqMesCir.getValue();
            contador3 = (Integer) spnMesRecSal.getValue();
            contador3 += (Integer) spnMesCirSal.getValue();
            int cont = (Integer) spnSilExt.getValue();
            cont += (Integer) spnSilExtSal.getValue();
            Costo += (Moño * cont);
            Costo += (Moño * (contador3 * 10));
        }
        if (rbtnMantelcir.isSelected()) {
            contador4 = (Integer) spnManExt.getValue();
            contador4 += (Integer) spnMesExt.getValue();
            Costo += (MantelCircular * contador4);
        }
        if (rbtnMantelrec.isSelected()) {
            contador5 = (Integer) spnManExt.getValue();
            contador5 += (Integer) spnMesExt.getValue();
            Costo += (MantelRectangular * contador5);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new FondoPanel();
        lblPaqMesRec1 = new javax.swing.JLabel();
        spnPaqMesCir = new javax.swing.JSpinner();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        txtReferencias = new java.awt.TextField();
        spnMesRecSal = new javax.swing.JSpinner();
        spnSilExt = new javax.swing.JSpinner();
        spnMesCirSal = new javax.swing.JSpinner();
        txtDireccion = new java.awt.TextField();
        lblSillasExtrasSalon = new javax.swing.JLabel();
        txtNombre = new java.awt.TextField();
        lblMesasExtras = new javax.swing.JLabel();
        lblPaqMesCir = new javax.swing.JLabel();
        lblFlete = new javax.swing.JLabel();
        txtFlete = new javax.swing.JTextField();
        lblManExt = new javax.swing.JLabel();
        btnAceptar = new java.awt.Button();
        spnManExt = new javax.swing.JSpinner();
        rbtnTol510 = new javax.swing.JRadioButton();
        btnCalcular = new javax.swing.JButton();
        txtTelefono = new java.awt.TextField();
        lblMesRecSal = new javax.swing.JLabel();
        lblMesCirSal = new javax.swing.JLabel();
        spnSilExtSal = new javax.swing.JSpinner();
        spnMesExt = new javax.swing.JSpinner();
        spnPaqMesRec = new javax.swing.JSpinner();
        rbtnMantelcir = new javax.swing.JRadioButton();
        txtTotal = new java.awt.TextField();
        txtDinero = new java.awt.TextField();
        rbtnCar36 = new javax.swing.JRadioButton();
        rbtnInfGran = new javax.swing.JRadioButton();
        rbtnInfChi1 = new javax.swing.JRadioButton();
        rbtnInfChi2 = new javax.swing.JRadioButton();
        rbtnBrinco = new javax.swing.JRadioButton();
        rbtnMoño = new javax.swing.JRadioButton();
        rbtnCubreman = new javax.swing.JRadioButton();
        rbtnCar1010 = new javax.swing.JRadioButton();
        rbtnCar510 = new javax.swing.JRadioButton();
        rbtnCar55 = new javax.swing.JRadioButton();
        lblSillasExtras = new javax.swing.JLabel();
        rbtnCubreMan = new javax.swing.JRadioButton();
        rbtnKaraoke = new javax.swing.JRadioButton();
        rbtnSono2 = new javax.swing.JRadioButton();
        rbtnSono1 = new javax.swing.JRadioButton();
        rbtnAudio = new javax.swing.JRadioButton();
        rbtnTol1010 = new javax.swing.JRadioButton();
        rbtnTol55 = new javax.swing.JRadioButton();
        rbtnTol36 = new javax.swing.JRadioButton();
        rbtnMantelrec = new javax.swing.JRadioButton();
        rbtnSalonL = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva orden de renta");
        setIconImage(getIconImage());
        setResizable(false);

        lblPaqMesRec1.setForeground(new java.awt.Color(255, 255, 255));
        lblPaqMesRec1.setText("Paquete mesas rectangulares");

        spnPaqMesCir.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));
        spnPaqMesCir.setMinimumSize(new java.awt.Dimension(25, 20));
        spnPaqMesCir.setPreferredSize(new java.awt.Dimension(25, 20));

        jdcFecha.setDateFormatString("yyyy-MM-dd hh:mm");

        txtReferencias.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtReferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReferenciasActionPerformed(evt);
            }
        });

        spnMesRecSal.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnMesRecSal.setMinimumSize(new java.awt.Dimension(25, 20));
        spnMesRecSal.setPreferredSize(new java.awt.Dimension(25, 20));

        spnSilExt.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnSilExt.setMinimumSize(new java.awt.Dimension(25, 20));
        spnSilExt.setPreferredSize(new java.awt.Dimension(25, 20));

        spnMesCirSal.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnMesCirSal.setMinimumSize(new java.awt.Dimension(25, 20));
        spnMesCirSal.setPreferredSize(new java.awt.Dimension(25, 20));

        txtDireccion.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });

        lblSillasExtrasSalon.setForeground(new java.awt.Color(255, 255, 255));
        lblSillasExtrasSalon.setText("Sillas extra salon");

        txtNombre.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblMesasExtras.setForeground(new java.awt.Color(255, 255, 255));
        lblMesasExtras.setText("Mesas extra");

        lblPaqMesCir.setForeground(new java.awt.Color(255, 255, 255));
        lblPaqMesCir.setText("Paquete mesas circulares");

        lblFlete.setForeground(new java.awt.Color(255, 255, 255));
        lblFlete.setText("Costo por flete");

        txtFlete.setText("0.0");

        lblManExt.setForeground(new java.awt.Color(255, 255, 255));
        lblManExt.setText("Mantel extra");

        btnAceptar.setFont(new java.awt.Font("Dialog", 0, 13)); // NOI18N
        btnAceptar.setLabel("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        spnManExt.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        rbtnTol510.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTol510.setText("Toldo 5x10");
        rbtnTol510.setOpaque(false);

        btnCalcular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        lblMesRecSal.setForeground(new java.awt.Color(255, 255, 255));
        lblMesRecSal.setText("Mesa rectangular salon");

        lblMesCirSal.setForeground(new java.awt.Color(255, 255, 255));
        lblMesCirSal.setText("Mesa circular salon");

        spnSilExtSal.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnSilExtSal.setMinimumSize(new java.awt.Dimension(25, 2));
        spnSilExtSal.setPreferredSize(new java.awt.Dimension(25, 20));

        spnMesExt.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnMesExt.setMinimumSize(new java.awt.Dimension(25, 20));
        spnMesExt.setPreferredSize(new java.awt.Dimension(25, 20));

        spnPaqMesRec.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnPaqMesRec.setMinimumSize(new java.awt.Dimension(25, 20));
        spnPaqMesRec.setPreferredSize(new java.awt.Dimension(25, 20));

        rbtnMantelcir.setForeground(new java.awt.Color(255, 255, 255));
        rbtnMantelcir.setText("Mantel circular");
        rbtnMantelcir.setOpaque(false);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtTotal.setText("0.00");
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        txtDinero.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N

        rbtnCar36.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCar36.setText("Carpa 3x6");
        rbtnCar36.setOpaque(false);
        rbtnCar36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCar36ActionPerformed(evt);
            }
        });

        rbtnInfGran.setForeground(new java.awt.Color(255, 255, 255));
        rbtnInfGran.setText("Inflable grande");
        rbtnInfGran.setOpaque(false);
        rbtnInfGran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInfGranActionPerformed(evt);
            }
        });

        rbtnInfChi1.setForeground(new java.awt.Color(255, 255, 255));
        rbtnInfChi1.setText("Inflable chico niño");
        rbtnInfChi1.setOpaque(false);
        rbtnInfChi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInfChi1ActionPerformed(evt);
            }
        });

        rbtnInfChi2.setForeground(new java.awt.Color(255, 255, 255));
        rbtnInfChi2.setText("Inflable chico niña");
        rbtnInfChi2.setOpaque(false);
        rbtnInfChi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnInfChi2ActionPerformed(evt);
            }
        });

        rbtnBrinco.setForeground(new java.awt.Color(255, 255, 255));
        rbtnBrinco.setText("Brincolin");
        rbtnBrinco.setOpaque(false);
        rbtnBrinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnBrincoActionPerformed(evt);
            }
        });

        rbtnMoño.setForeground(new java.awt.Color(255, 255, 255));
        rbtnMoño.setText("Moño");
        rbtnMoño.setOpaque(false);

        rbtnCubreman.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCubreman.setText("Cubremantel");
        rbtnCubreman.setOpaque(false);

        rbtnCar1010.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCar1010.setText("Carpa 10x10");
        rbtnCar1010.setOpaque(false);

        rbtnCar510.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCar510.setText("Carpa 5x10");
        rbtnCar510.setOpaque(false);

        rbtnCar55.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCar55.setText("Carpa 5x5");
        rbtnCar55.setOpaque(false);
        rbtnCar55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCar55ActionPerformed(evt);
            }
        });

        lblSillasExtras.setForeground(new java.awt.Color(255, 255, 255));
        lblSillasExtras.setText("Sillas Extras");

        rbtnCubreMan.setForeground(new java.awt.Color(255, 255, 255));
        rbtnCubreMan.setText("Cubre mantel salon");
        rbtnCubreMan.setOpaque(false);
        rbtnCubreMan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnCubreManActionPerformed(evt);
            }
        });

        rbtnKaraoke.setForeground(new java.awt.Color(255, 255, 255));
        rbtnKaraoke.setText("Karaoke");
        rbtnKaraoke.setOpaque(false);
        rbtnKaraoke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnKaraokeActionPerformed(evt);
            }
        });

        rbtnSono2.setForeground(new java.awt.Color(255, 255, 255));
        rbtnSono2.setText("Sonorizacion 2");
        rbtnSono2.setOpaque(false);
        rbtnSono2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSono2ActionPerformed(evt);
            }
        });

        rbtnSono1.setForeground(new java.awt.Color(255, 255, 255));
        rbtnSono1.setText("Sonorizacion 1");
        rbtnSono1.setOpaque(false);
        rbtnSono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSono1ActionPerformed(evt);
            }
        });

        rbtnAudio.setForeground(new java.awt.Color(255, 255, 255));
        rbtnAudio.setText("Audio");
        rbtnAudio.setOpaque(false);
        rbtnAudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnAudioActionPerformed(evt);
            }
        });

        rbtnTol1010.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTol1010.setText("Toldo 10x10");
        rbtnTol1010.setOpaque(false);

        rbtnTol55.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTol55.setText("Toldo 5x5");
        rbtnTol55.setOpaque(false);

        rbtnTol36.setForeground(new java.awt.Color(255, 255, 255));
        rbtnTol36.setText("Toldo 3x6");
        rbtnTol36.setOpaque(false);

        rbtnMantelrec.setForeground(new java.awt.Color(255, 255, 255));
        rbtnMantelrec.setText("Mantel rectangular");
        rbtnMantelrec.setOpaque(false);

        rbtnSalonL.setForeground(new java.awt.Color(255, 255, 255));
        rbtnSalonL.setText("Salon");
        rbtnSalonL.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar nueva orden de servicio");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha y hora estimada");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Telefono");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Direccion");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dinero dejado a cuenta");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Referencias de la ubicacion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(rbtnInfGran)
                                                            .addComponent(rbtnAudio)
                                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(rbtnSono1)
                                                                .addComponent(rbtnSono2)
                                                                .addComponent(rbtnKaraoke, javax.swing.GroupLayout.Alignment.LEADING)))
                                                        .addGap(30, 30, 30)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(rbtnTol1010)
                                                            .addComponent(rbtnCar1010)
                                                            .addComponent(rbtnTol36)
                                                            .addComponent(rbtnTol55)
                                                            .addComponent(rbtnTol510)))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rbtnInfChi2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rbtnCar510)))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbtnCubreMan)
                                                    .addComponent(rbtnMoño)
                                                    .addComponent(rbtnCubreman)
                                                    .addComponent(rbtnSalonL)
                                                    .addComponent(rbtnMantelcir)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtFlete, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(rbtnMantelrec, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addComponent(lblFlete)))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbtnInfChi1)
                                                    .addComponent(rbtnBrinco))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbtnCar36)
                                                    .addComponent(rbtnCar55))))
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(spnPaqMesCir, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnPaqMesRec, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnMesCirSal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnMesRecSal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnSilExt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnMesExt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnSilExtSal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(spnManExt, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPaqMesCir)
                                            .addComponent(lblPaqMesRec1)
                                            .addComponent(lblMesCirSal)
                                            .addComponent(lblSillasExtras)
                                            .addComponent(lblMesasExtras)
                                            .addComponent(lblSillasExtrasSalon)
                                            .addComponent(lblManExt)
                                            .addComponent(lblMesRecSal)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel4))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel3))))
                                                .addComponent(jLabel5))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel6)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtReferencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(50, 50, 50)
                                            .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDinero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(btnCalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtReferencias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnPaqMesCir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnBrinco)
                        .addComponent(rbtnCar36)
                        .addComponent(rbtnSalonL)
                        .addComponent(lblPaqMesCir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnInfChi1)
                    .addComponent(rbtnCar55)
                    .addComponent(rbtnCubreman)
                    .addComponent(spnPaqMesRec, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPaqMesRec1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnInfChi2)
                    .addComponent(rbtnCar510)
                    .addComponent(rbtnCubreMan)
                    .addComponent(spnMesCirSal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMesCirSal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnInfGran)
                    .addComponent(rbtnCar1010)
                    .addComponent(rbtnMoño)
                    .addComponent(spnMesRecSal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMesRecSal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnSilExt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnAudio)
                        .addComponent(rbtnTol1010)
                        .addComponent(rbtnMantelcir)
                        .addComponent(lblSillasExtras)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnMesExt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnMantelrec)
                    .addComponent(rbtnTol36)
                    .addComponent(rbtnSono1)
                    .addComponent(lblMesasExtras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnSilExtSal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnSono2)
                    .addComponent(rbtnTol55)
                    .addComponent(lblSillasExtrasSalon)
                    .addComponent(txtFlete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnManExt, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnKaraoke)
                    .addComponent(rbtnTol510)
                    .addComponent(lblManExt)
                    .addComponent(lblFlete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtReferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReferenciasActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (evt.getSource() == btnAceptar) {
            Guardar();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        if (evt.getSource() == btnCalcular) {
            Costo = 0;
            Calculo();
            txtTotal.setText(String.valueOf(Costo));
        }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void rbtnCar36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCar36ActionPerformed

    }//GEN-LAST:event_rbtnCar36ActionPerformed

    private void rbtnInfGranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInfGranActionPerformed

    }//GEN-LAST:event_rbtnInfGranActionPerformed

    private void rbtnInfChi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInfChi1ActionPerformed

    }//GEN-LAST:event_rbtnInfChi1ActionPerformed

    private void rbtnInfChi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnInfChi2ActionPerformed

    }//GEN-LAST:event_rbtnInfChi2ActionPerformed

    private void rbtnBrincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnBrincoActionPerformed

    }//GEN-LAST:event_rbtnBrincoActionPerformed

    private void rbtnCar55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCar55ActionPerformed

    }//GEN-LAST:event_rbtnCar55ActionPerformed

    private void rbtnCubreManActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnCubreManActionPerformed

    }//GEN-LAST:event_rbtnCubreManActionPerformed

    private void rbtnKaraokeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnKaraokeActionPerformed

    }//GEN-LAST:event_rbtnKaraokeActionPerformed

    private void rbtnSono2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSono2ActionPerformed

    }//GEN-LAST:event_rbtnSono2ActionPerformed

    private void rbtnSono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSono1ActionPerformed

    }//GEN-LAST:event_rbtnSono1ActionPerformed

    private void rbtnAudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnAudioActionPerformed

    }//GEN-LAST:event_rbtnAudioActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new AgregarRentas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAceptar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JLabel lblFlete;
    private javax.swing.JLabel lblManExt;
    private javax.swing.JLabel lblMesCirSal;
    private javax.swing.JLabel lblMesRecSal;
    private javax.swing.JLabel lblMesasExtras;
    private javax.swing.JLabel lblPaqMesCir;
    private javax.swing.JLabel lblPaqMesRec1;
    private javax.swing.JLabel lblSillasExtras;
    private javax.swing.JLabel lblSillasExtrasSalon;
    private javax.swing.JRadioButton rbtnAudio;
    private javax.swing.JRadioButton rbtnBrinco;
    private javax.swing.JRadioButton rbtnCar1010;
    private javax.swing.JRadioButton rbtnCar36;
    private javax.swing.JRadioButton rbtnCar510;
    private javax.swing.JRadioButton rbtnCar55;
    private javax.swing.JRadioButton rbtnCubreMan;
    private javax.swing.JRadioButton rbtnCubreman;
    private javax.swing.JRadioButton rbtnInfChi1;
    private javax.swing.JRadioButton rbtnInfChi2;
    private javax.swing.JRadioButton rbtnInfGran;
    private javax.swing.JRadioButton rbtnKaraoke;
    private javax.swing.JRadioButton rbtnMantelcir;
    private javax.swing.JRadioButton rbtnMantelrec;
    private javax.swing.JRadioButton rbtnMoño;
    private javax.swing.JRadioButton rbtnSalonL;
    private javax.swing.JRadioButton rbtnSono1;
    private javax.swing.JRadioButton rbtnSono2;
    private javax.swing.JRadioButton rbtnTol1010;
    private javax.swing.JRadioButton rbtnTol36;
    private javax.swing.JRadioButton rbtnTol510;
    private javax.swing.JRadioButton rbtnTol55;
    private javax.swing.JSpinner spnManExt;
    private javax.swing.JSpinner spnMesCirSal;
    private javax.swing.JSpinner spnMesExt;
    private javax.swing.JSpinner spnMesRecSal;
    private javax.swing.JSpinner spnPaqMesCir;
    private javax.swing.JSpinner spnPaqMesRec;
    private javax.swing.JSpinner spnSilExt;
    private javax.swing.JSpinner spnSilExtSal;
    private java.awt.TextField txtDinero;
    private java.awt.TextField txtDireccion;
    private javax.swing.JTextField txtFlete;
    private java.awt.TextField txtNombre;
    private java.awt.TextField txtReferencias;
    private java.awt.TextField txtTelefono;
    private java.awt.TextField txtTotal;
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

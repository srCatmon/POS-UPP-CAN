/*
 *
 */
package Principal;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * @author CeAnCof Software
 */
public class Consultar extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    public String Codi,Cant;
    public String Busq1;
    public long Busq2;

    public Consultar() {
        initComponents();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }

    public void Buscar() {
        Object Producto;
        Object Datos[] = new Object[5];
        Busq1 = txtBusqueda.getText();
        try {
            Busq2 = Long.valueOf(txtBusqueda.getText());
        } catch (NumberFormatException e) {
            Busq2 = 0;
        }
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM papeleria where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Tamaño") + "      " + rs.getString("Color"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio");
                    model.addRow(Datos);
                } while (rs.next());
            }
            rs = st.executeQuery("SELECT * FROM regalos where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Tamaño") + "      " + rs.getString("Tipo"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio");
                    model.addRow(Datos);
                } while (rs.next());
            }
            rs = st.executeQuery("SELECT * FROM farmacia where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Cantidad") + "    " + rs.getString("Caracteristicas"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio_Venta");
                    model.addRow(Datos);
                } while (rs.next());
            }
            rs = st.executeQuery("SELECT * FROM electronicos where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Caracteristicas") + "     " + rs.getString("Registro"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio");
                    model.addRow(Datos);
                } while (rs.next());
            }
            rs = st.executeQuery("SELECT * FROM merceria where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Tamaño") + "      " + rs.getString("Color"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio_Venta");
                    model.addRow(Datos);
                } while (rs.next());
            }
            rs = st.executeQuery("SELECT * FROM catalogo where (LOWER (Nombre) like LOWER('%" + Busq1 + "%')) or (Codigo_Producto='" + Busq2 + "');");
            if (rs.next()) {
                do {
                    DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
                    Producto = (rs.getString("Marca") + "       " + rs.getString("Caracteristicas") + "     " + rs.getString("Tamaño") + "      " + rs.getString("Color"));
                    Datos[0] = rs.getLong("Codigo_Producto");
                    Datos[1] = rs.getString("Nombre");
                    Datos[2] = Producto;
                    Datos[3] = rs.getDouble("Stock");
                    Datos[4] = rs.getDouble("Precio_Venta");
                    model.addRow(Datos);
                } while (rs.next());
            }
            txtBusqueda.setText(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }
    
    public void Agregar(){
        Codi = txtCodigo.getText();
        Cant = String.valueOf(spnCantidad.getValue());
        Principal.Ventana_Principal.setCodigo(Long.parseLong(Codi));
        Principal.Ventana_Principal.setCanti(Cant);
        Principal.Ventana_Principal.añadirBusq();
        FrameVenta.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FrameVenta = new javax.swing.JFrame();
        PanelVenta = new FondoPanel2();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        btnAceptar = new javax.swing.JButton();
        PanelConsultas = new FondoPanel();
        lblTitulo = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTabla = new javax.swing.JTable();

        FrameVenta.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        FrameVenta.setTitle("Agregar a la venta");
        FrameVenta.setBounds(new java.awt.Rectangle(0, 0, 370, 200));
        FrameVenta.setIconImage(getIconImage());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo del producto");

        txtCodigo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cantidad");

        spnCantidad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerListModel(new String[] {"0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "6.5", "7.0", "7.5", "8.0", "8.5", "9.0", "9.5", "10.0", "10.5", "11.0", "11.5", "12.0", "12.5", "13.0", "13.5", "14.0", "14.5", "15.0", "15.5", "16.0", "16.5", "17.0", "17.5", "18.0", "18.5", "19.0", "19.5", "20.0", "20.5", "21.0", "21.5", "22.0", "22.5", "23.0", "23.5", "24.0", "24.5", "25.0", "25.5", "26.0", "26.5", "27.0", "27.5", "28.0", "28.5", "29.0", "29.5", "30.0", "30.5", "31.0", "31.5", "32.0", "32.5", "33.0", "33.5", "34.0", "34.5", "35.0", "35.5", "36.0", "36.5", "37.0", "37.5", "38.0", "38.5", "39.0", "39.5", "40.0", "40.5", "41.0", "41.5", "42.0", "42.5", "43.0", "43.5", "44.0", "44.5", "45.0", "45.5", "46.0", "46.5", "47.0", "47.5", "48.0", "48.5", "49.0", "49.5", "50.0", "50.5", "51.0", "51.5", "52.0", "52.5", "53.0", "53.5", "54.0", "54.5", "55.0", "55.5", "56.0", "56.5", "57.0", "57.5", "58.0", "58.5", "59.0", "59.5", "60.0", "60.5", "61.0", "61.5", "62.0", "62.5", "63.0", "63.5", "64.0", "64.5", "65.0", "65.5", "66.0", "66.5", "67.0", "67.5", "68.0", "68.5", "69.0", "69.5", "70.0", "70.5", "71.0", "71.5", "72.0", "72.5", "73.0", "73.5", "74.0", "74.5", "75.0", "75.5", "76.0", "76.5", "77.0", "77.5", "78.0", "78.5", "79.0", "79.5", "80.0", "80.5", "81.0", "81.5", "82.0", "82.5", "83.0", "83.5", "84.0", "84.5", "85.0", "85.5", "86.0", "86.5", "87.0", "87.5", "88.0", "88.5", "89.0", "89.5", "90.0", "90.5", "91.0", "91.5", "92.0", "92.5", "93.0", "93.5", "94.0", "94.5", "95.0", "95.5", "96.0", "96.5", "97.0", "97.5", "98.0", "98.5", "99.0", "99.5", "100.0", "100.5", "101.0", "101.5", "102.0", "102.5", "103.0", "103.5", "104.0", "104.5", "105.0", "105.5", "106.0", "106.5", "107.0", "107.5", "108.0", "108.5", "109.0", "109.5", "110.0", "110.5", "111.0", "111.5", "112.0", "112.5", "113.0", "113.5", "114.0", "114.5", "115.0", "115.5", "116.0", "116.5", "117.0", "117.5", "118.0", "118.5", "119.0", "119.5", "120.0", "120.5", "121.0", "121.5", "122.0", "122.5", "123.0", "123.5", "124.0", "124.5", "125.0", "125.5", "126.0", "126.5", "127.0", "127.5", "128.0", "128.5", "129.0", "129.5", "130.0", "130.5", "131.0", "131.5", "132.0", "132.5", "133.0", "133.5", "134.0", "134.5", "135.0", "135.5", "136.0", "136.5", "137.0", "137.5", "138.0", "138.5", "139.0", "139.5", "140.0", "140.5", "141.0", "141.5", "142.0", "142.5", "143.0", "143.5", "144.0", "144.5", "145.0", "145.5", "146.0", "146.5", "147.0", "147.5", "148.0", "148.5", "149.0", "149.5", "150.0", "150.5", "151.0", "151.5", "152.0", "152.5", "153.0", "153.5", "154.0", "154.5", "155.0", "155.5", "156.0", "156.5", "157.0", "157.5", "158.0", "158.5", "159.0", "159.5", "160.0", "160.5", "161.0", "161.5", "162.0", "162.5", "163.0", "163.5", "164.0", "164.5", "165.0", "165.5", "166.0", "166.5", "167.0", "167.5", "168.0", "168.5", "169.0", "169.5", "170.0", "170.5", "171.0", "171.5", "172.0", "172.5", "173.0", "173.5", "174.0", "174.5", "175.0", "175.5", "176.0", "176.5", "177.0", "177.5", "178.0", "178.5", "179.0", "179.5", "180.0", "180.5", "181.0", "181.5", "182.0", "182.5", "183.0", "183.5", "184.0", "184.5", "185.0", "185.5", "186.0", "186.5", "187.0", "187.5", "188.0", "188.5", "189.0", "189.5", "190.0", "190.5", "191.0", "191.5", "192.0", "192.5", "193.0", "193.5", "194.0", "194.5", "195.0", "195.5", "196.0", "196.5", "197.0", "197.5", "198.0", "198.5", "199.0", "199.5", "200.0", "200.5", "201.0", "201.5", "202.0", "202.5", "203.0", "203.5", "204.0", "204.5", "205.0", "205.5", "206.0", "206.5", "207.0", "207.5", "208.0", "208.5", "209.0", "209.5", "210.0", "210.5", "211.0", "211.5", "212.0", "212.5", "213.0", "213.5", "214.0", "214.5", "215.0", "215.5", "216.0", "216.5", "217.0", "217.5", "218.0", "218.5", "219.0", "219.5", "220.0", "220.5", "221.0", "221.5", "222.0", "222.5", "223.0", "223.5", "224.0", "224.5", "225.0", "225.5", "226.0", "226.5", "227.0", "227.5", "228.0", "228.5", "229.0", "229.5", "230.0", "230.5", "231.0", "231.5", "232.0", "232.5", "233.0", "233.5", "234.0", "234.5", "235.0", "235.5", "236.0", "236.5", "237.0", "237.5", "238.0", "238.5", "239.0", "239.5", "240.0", "240.5", "241.0", "241.5", "242.0", "242.5", "243.0", "243.5", "244.0", "244.5", "245.0", "245.5", "246.0", "246.5", "247.0", "247.5", "248.0", "248.5", "249.0", "249.5", "250.0", "250.5", "251.0", "251.5", "252.0", "252.5", "253.0", "253.5", "254.0", "254.5", "255.0", "255.5", "256.0", "256.5", "257.0", "257.5", "258.0", "258.5", "259.0", "259.5", "260.0", "260.5", "261.0", "261.5", "262.0", "262.5", "263.0", "263.5", "264.0", "264.5", "265.0", "265.5", "266.0", "266.5", "267.0", "267.5", "268.0", "268.5", "269.0", "269.5", "270.0", "270.5", "271.0", "271.5", "272.0", "272.5", "273.0", "273.5", "274.0", "274.5", "275.0", "275.5", "276.0", "276.5", "277.0", "277.5", "278.0", "278.5", "279.0", "279.5", "280.0", "280.5", "281.0", "281.5", "282.0", "282.5", "283.0", "283.5", "284.0", "284.5", "285.0", "285.5", "286.0", "286.5", "287.0", "287.5", "288.0", "288.5", "289.0", "289.5", "290.0", "290.5", "291.0", "291.5", "292.0", "292.5", "293.0", "293.5", "294.0", "294.5", "295.0", "295.5", "296.0", "296.5", "297.0", "297.5", "298.0", "298.5", "299.0", "299.5", "300.0", "300.5", "301.0", "301.5", "302.0", "302.5", "303.0", "303.5", "304.0", "304.5", "305.0", "305.5", "306.0", "306.5", "307.0", "307.5", "308.0", "308.5", "309.0", "309.5", "310.0", "310.5", "311.0", "311.5", "312.0", "312.5", "313.0", "313.5", "314.0", "314.5", "315.0", "315.5", "316.0", "316.5", "317.0", "317.5", "318.0", "318.5", "319.0", "319.5", "320.0", "320.5", "321.0", "321.5", "322.0", "322.5", "323.0", "323.5", "324.0", "324.5", "325.0", "325.5", "326.0", "326.5", "327.0", "327.5", "328.0", "328.5", "329.0", "329.5", "330.0", "330.5", "331.0", "331.5", "332.0", "332.5", "333.0", "333.5", "334.0", "334.5", "335.0", "335.5", "336.0", "336.5", "337.0", "337.5", "338.0", "338.5", "339.0", "339.5", "340.0", "340.5", "341.0", "341.5", "342.0", "342.5", "343.0", "343.5", "344.0", "344.5", "345.0", "345.5", "346.0", "346.5", "347.0", "347.5", "348.0", "348.5", "349.0", "349.5", "350.0", "350.5", "351.0", "351.5", "352.0", "352.5", "353.0", "353.5", "354.0", "354.5", "355.0", "355.5", "356.0", "356.5", "357.0", "357.5", "358.0", "358.5", "359.0", "359.5", "360.0", "360.5", "361.0", "361.5", "362.0", "362.5", "363.0", "363.5", "364.0", "364.5", "365.0", "365.5", "366.0", "366.5", "367.0", "367.5", "368.0", "368.5", "369.0", "369.5", "370.0", "370.5", "371.0", "371.5", "372.0", "372.5", "373.0", "373.5", "374.0", "374.5", "375.0", "375.5", "376.0", "376.5", "377.0", "377.5", "378.0", "378.5", "379.0", "379.5", "380.0", "380.5", "381.0", "381.5", "382.0", "382.5", "383.0", "383.5", "384.0", "384.5", "385.0", "385.5", "386.0", "386.5", "387.0", "387.5", "388.0", "388.5", "389.0", "389.5", "390.0", "390.5", "391.0", "391.5", "392.0", "392.5", "393.0", "393.5", "394.0", "394.5", "395.0", "395.5", "396.0", "396.5", "397.0", "397.5", "398.0", "398.5", "399.0", "399.5", "400.0", "400.5", "401.0", "401.5", "402.0", "402.5", "403.0", "403.5", "404.0", "404.5", "405.0", "405.5", "406.0", "406.5", "407.0", "407.5", "408.0", "408.5", "409.0", "409.5", "410.0", "410.5", "411.0", "411.5", "412.0", "412.5", "413.0", "413.5", "414.0", "414.5", "415.0", "415.5", "416.0", "416.5", "417.0", "417.5", "418.0", "418.5", "419.0", "419.5", "420.0", "420.5", "421.0", "421.5", "422.0", "422.5", "423.0", "423.5", "424.0", "424.5", "425.0", "425.5", "426.0", "426.5", "427.0", "427.5", "428.0", "428.5", "429.0", "429.5", "430.0", "430.5", "431.0", "431.5", "432.0", "432.5", "433.0", "433.5", "434.0", "434.5", "435.0", "435.5", "436.0", "436.5", "437.0", "437.5", "438.0", "438.5", "439.0", "439.5", "440.0", "440.5", "441.0", "441.5", "442.0", "442.5", "443.0", "443.5", "444.0", "444.5", "445.0", "445.5", "446.0", "446.5", "447.0", "447.5", "448.0", "448.5", "449.0", "449.5", "450.0", "450.5", "451.0", "451.5", "452.0", "452.5", "453.0", "453.5", "454.0", "454.5", "455.0", "455.5", "456.0", "456.5", "457.0", "457.5", "458.0", "458.5", "459.0", "459.5", "460.0", "460.5", "461.0", "461.5", "462.0", "462.5", "463.0", "463.5", "464.0", "464.5", "465.0", "465.5", "466.0", "466.5", "467.0", "467.5", "468.0", "468.5", "469.0", "469.5", "470.0", "470.5", "471.0", "471.5", "472.0", "472.5", "473.0", "473.5", "474.0", "474.5", "475.0", "475.5", "476.0", "476.5", "477.0", "477.5", "478.0", "478.5", "479.0", "479.5", "480.0", "480.5", "481.0", "481.5", "482.0", "482.5", "483.0", "483.5", "484.0", "484.5", "485.0", "485.5", "486.0", "486.5", "487.0", "487.5", "488.0", "488.5", "489.0", "489.5", "490.0", "490.5", "491.0", "491.5", "492.0", "492.5", "493.0", "493.5", "494.0", "494.5", "495.0", "495.5", "496.0", "496.5", "497.0", "497.5", "498.0", "498.5", "499.0", "499.5", "500.0", "500.5", "501.0", "501.5", "502.0", "502.5", "503.0", "503.5", "504.0", "504.5", "505.0", "505.5", "506.0", "506.5", "507.0", "507.5", "508.0", "508.5", "509.0", "509.5", "510.0", "510.5", "511.0", "511.5", "512.0", "512.5", "513.0", "513.5", "514.0", "514.5", "515.0", "515.5", "516.0", "516.5", "517.0", "517.5", "518.0", "518.5", "519.0", "519.5", "520.0", "520.5", "521.0", "521.5", "522.0", "522.5", "523.0", "523.5", "524.0", "524.5", "525.0", "525.5", "526.0", "526.5", "527.0", "527.5", "528.0", "528.5", "529.0", "529.5", "530.0", "530.5", "531.0", "531.5", "532.0", "532.5", "533.0", "533.5", "534.0", "534.5", "535.0", "535.5", "536.0", "536.5", "537.0", "537.5", "538.0", "538.5", "539.0", "539.5", "540.0", "540.5", "541.0", "541.5", "542.0", "542.5", "543.0", "543.5", "544.0", "544.5", "545.0", "545.5", "546.0", "546.5", "547.0", "547.5", "548.0", "548.5", "549.0", "549.5", "550.0", "550.5", "551.0", "551.5", "552.0", "552.5", "553.0", "553.5", "554.0", "554.5", "555.0", "555.5", "556.0", "556.5", "557.0", "557.5", "558.0", "558.5", "559.0", "559.5", "560.0", "560.5", "561.0", "561.5", "562.0", "562.5", "563.0", "563.5", "564.0", "564.5", "565.0", "565.5", "566.0", "566.5", "567.0", "567.5", "568.0", "568.5", "569.0", "569.5", "570.0", "570.5", "571.0", "571.5", "572.0", "572.5", "573.0", "573.5", "574.0", "574.5", "575.0", "575.5", "576.0", "576.5", "577.0", "577.5", "578.0", "578.5", "579.0", "579.5", "580.0", "580.5", "581.0", "581.5", "582.0", "582.5", "583.0", "583.5", "584.0", "584.5", "585.0", "585.5", "586.0", "586.5", "587.0", "587.5", "588.0", "588.5", "589.0", "589.5", "590.0", "590.5", "591.0", "591.5", "592.0", "592.5", "593.0", "593.5", "594.0", "594.5", "595.0", "595.5", "596.0", "596.5", "597.0", "597.5", "598.0", "598.5", "599.0", "599.5", "600.0", "600.5", "601.0", "601.5", "602.0", "602.5", "603.0", "603.5", "604.0", "604.5", "605.0", "605.5", "606.0", "606.5", "607.0", "607.5", "608.0", "608.5", "609.0", "609.5", "610.0", "610.5", "611.0", "611.5", "612.0", "612.5", "613.0", "613.5", "614.0", "614.5", "615.0", "615.5", "616.0", "616.5", "617.0", "617.5", "618.0", "618.5", "619.0", "619.5", "620.0", "620.5", "621.0", "621.5", "622.0", "622.5", "623.0", "623.5", "624.0", "624.5", "625.0", "625.5", "626.0", "626.5", "627.0", "627.5", "628.0", "628.5", "629.0", "629.5", "630.0", "630.5", "631.0", "631.5", "632.0", "632.5", "633.0", "633.5", "634.0", "634.5", "635.0", "635.5", "636.0", "636.5", "637.0", "637.5", "638.0", "638.5", "639.0", "639.5", "640.0", "640.5", "641.0", "641.5", "642.0", "642.5", "643.0", "643.5", "644.0", "644.5", "645.0", "645.5", "646.0", "646.5", "647.0", "647.5", "648.0", "648.5", "649.0", "649.5", "650.0", "650.5", "651.0", "651.5", "652.0", "652.5", "653.0", "653.5", "654.0", "654.5", "655.0", "655.5", "656.0", "656.5", "657.0", "657.5", "658.0", "658.5", "659.0", "659.5", "660.0", "660.5", "661.0", "661.5", "662.0", "662.5", "663.0", "663.5", "664.0", "664.5", "665.0", "665.5", "666.0", "666.5", "667.0", "667.5", "668.0", "668.5", "669.0", "669.5", "670.0", "670.5", "671.0", "671.5", "672.0", "672.5", "673.0", "673.5", "674.0", "674.5", "675.0", "675.5", "676.0", "676.5", "677.0", "677.5", "678.0", "678.5", "679.0", "679.5", "680.0", "680.5", "681.0", "681.5", "682.0", "682.5", "683.0", "683.5", "684.0", "684.5", "685.0", "685.5", "686.0", "686.5", "687.0", "687.5", "688.0", "688.5", "689.0", "689.5", "690.0", "690.5", "691.0", "691.5", "692.0", "692.5", "693.0", "693.5", "694.0", "694.5", "695.0", "695.5", "696.0", "696.5", "697.0", "697.5", "698.0", "698.5", "699.0", "699.5", "700.0", "700.5", "701.0", "701.5", "702.0", "702.5", "703.0", "703.5", "704.0", "704.5", "705.0", "705.5", "706.0", "706.5", "707.0", "707.5", "708.0", "708.5", "709.0", "709.5", "710.0", "710.5", "711.0", "711.5", "712.0", "712.5", "713.0", "713.5", "714.0", "714.5", "715.0", "715.5", "716.0", "716.5", "717.0", "717.5", "718.0", "718.5", "719.0", "719.5", "720.0", "720.5", "721.0", "721.5", "722.0", "722.5", "723.0", "723.5", "724.0", "724.5", "725.0", "725.5", "726.0", "726.5", "727.0", "727.5", "728.0", "728.5", "729.0", "729.5", "730.0", "730.5", "731.0", "731.5", "732.0", "732.5", "733.0", "733.5", "734.0", "734.5", "735.0", "735.5", "736.0", "736.5", "737.0", "737.5", "738.0", "738.5", "739.0", "739.5", "740.0", "740.5", "741.0", "741.5", "742.0", "742.5", "743.0", "743.5", "744.0", "744.5", "745.0", "745.5", "746.0", "746.5", "747.0", "747.5", "748.0", "748.5", "749.0", "749.5", "750.0", "750.5", "751.0", "751.5", "752.0", "752.5", "753.0", "753.5", "754.0", "754.5", "755.0", "755.5", "756.0", "756.5", "757.0", "757.5", "758.0", "758.5", "759.0", "759.5", "760.0", "760.5", "761.0", "761.5", "762.0", "762.5", "763.0", "763.5", "764.0", "764.5", "765.0", "765.5", "766.0", "766.5", "767.0", "767.5", "768.0", "768.5", "769.0", "769.5", "770.0", "770.5", "771.0", "771.5", "772.0", "772.5", "773.0", "773.5", "774.0", "774.5", "775.0", "775.5", "776.0", "776.5", "777.0", "777.5", "778.0", "778.5", "779.0", "779.5", "780.0", "780.5", "781.0", "781.5", "782.0", "782.5", "783.0", "783.5", "784.0", "784.5", "785.0", "785.5", "786.0", "786.5", "787.0", "787.5", "788.0", "788.5", "789.0", "789.5", "790.0", "790.5", "791.0", "791.5", "792.0", "792.5", "793.0", "793.5", "794.0", "794.5", "795.0", "795.5", "796.0", "796.5", "797.0", "797.5", "798.0", "798.5", "799.0", "799.5", "800.0", "800.5", "801.0", "801.5", "802.0", "802.5", "803.0", "803.5", "804.0", "804.5", "805.0", "805.5", "806.0", "806.5", "807.0", "807.5", "808.0", "808.5", "809.0", "809.5", "810.0", "810.5", "811.0", "811.5", "812.0", "812.5", "813.0", "813.5", "814.0", "814.5", "815.0", "815.5", "816.0", "816.5", "817.0", "817.5", "818.0", "818.5", "819.0", "819.5", "820.0", "820.5", "821.0", "821.5", "822.0", "822.5", "823.0", "823.5", "824.0", "824.5", "825.0", "825.5", "826.0", "826.5", "827.0", "827.5", "828.0", "828.5", "829.0", "829.5", "830.0", "830.5", "831.0", "831.5", "832.0", "832.5", "833.0", "833.5", "834.0", "834.5", "835.0", "835.5", "836.0", "836.5", "837.0", "837.5", "838.0", "838.5", "839.0", "839.5", "840.0", "840.5", "841.0", "841.5", "842.0", "842.5", "843.0", "843.5", "844.0", "844.5", "845.0", "845.5", "846.0", "846.5", "847.0", "847.5", "848.0", "848.5", "849.0", "849.5", "850.0", "850.5", "851.0", "851.5", "852.0", "852.5", "853.0", "853.5", "854.0", "854.5", "855.0", "855.5", "856.0", "856.5", "857.0", "857.5", "858.0", "858.5", "859.0", "859.5", "860.0", "860.5", "861.0", "861.5", "862.0", "862.5", "863.0", "863.5", "864.0", "864.5", "865.0", "865.5", "866.0", "866.5", "867.0", "867.5", "868.0", "868.5", "869.0", "869.5", "870.0", "870.5", "871.0", "871.5", "872.0", "872.5", "873.0", "873.5", "874.0", "874.5", "875.0", "875.5", "876.0", "876.5", "877.0", "877.5", "878.0", "878.5", "879.0", "879.5", "880.0", "880.5", "881.0", "881.5", "882.0", "882.5", "883.0", "883.5", "884.0", "884.5", "885.0", "885.5", "886.0", "886.5", "887.0", "887.5", "888.0", "888.5", "889.0", "889.5", "890.0", "890.5", "891.0", "891.5", "892.0", "892.5", "893.0", "893.5", "894.0", "894.5", "895.0", "895.5", "896.0", "896.5", "897.0", "897.5", "898.0", "898.5", "899.0", "899.5", "900.0", "900.5", "901.0", "901.5", "902.0", "902.5", "903.0", "903.5", "904.0", "904.5", "905.0", "905.5", "906.0", "906.5", "907.0", "907.5", "908.0", "908.5", "909.0", "909.5", "910.0", "910.5", "911.0", "911.5", "912.0", "912.5", "913.0", "913.5", "914.0", "914.5", "915.0", "915.5", "916.0", "916.5", "917.0", "917.5", "918.0", "918.5", "919.0", "919.5", "920.0", "920.5", "921.0", "921.5", "922.0", "922.5", "923.0", "923.5", "924.0", "924.5", "925.0", "925.5", "926.0", "926.5", "927.0", "927.5", "928.0", "928.5", "929.0", "929.5", "930.0", "930.5", "931.0", "931.5", "932.0", "932.5", "933.0", "933.5", "934.0", "934.5", "935.0", "935.5", "936.0", "936.5", "937.0", "937.5", "938.0", "938.5", "939.0", "939.5", "940.0", "940.5", "941.0", "941.5", "942.0", "942.5", "943.0", "943.5", "944.0", "944.5", "945.0", "945.5", "946.0", "946.5", "947.0", "947.5", "948.0", "948.5", "949.0", "949.5", "950.0", "950.5", "951.0", "951.5", "952.0", "952.5", "953.0", "953.5", "954.0", "954.5", "955.0", "955.5", "956.0", "956.5", "957.0", "957.5", "958.0", "958.5", "959.0", "959.5", "960.0", "960.5", "961.0", "961.5", "962.0", "962.5", "963.0", "963.5", "964.0", "964.5", "965.0", "965.5", "966.0", "966.5", "967.0", "967.5", "968.0", "968.5", "969.0", "969.5", "970.0", "970.5", "971.0", "971.5", "972.0", "972.5", "973.0", "973.5", "974.0", "974.5", "975.0", "975.5", "976.0", "976.5", "977.0", "977.5", "978.0", "978.5", "979.0", "979.5", "980.0", "980.5", "981.0", "981.5", "982.0", "982.5", "983.0", "983.5", "984.0", "984.5", "985.0", "985.5", "986.0", "986.5", "987.0", "987.5", "988.0", "988.5", "989.0", "989.5", "990.0", "990.5", "991.0", "991.5", "992.0", "992.5", "993.0", "993.5", "994.0", "994.5", "995.0", "995.5", "996.0", "996.5", "997.0", "997.5", "998.0", "998.5", "999.0", "999.5", "1000.0", "1000.5"}));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelVentaLayout = new javax.swing.GroupLayout(PanelVenta);
        PanelVenta.setLayout(PanelVentaLayout);
        PanelVentaLayout.setHorizontalGroup(
            PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelVentaLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2))
                    .addGroup(PanelVentaLayout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelVentaLayout.setVerticalGroup(
            PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(PanelVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FrameVentaLayout = new javax.swing.GroupLayout(FrameVenta.getContentPane());
        FrameVenta.getContentPane().setLayout(FrameVentaLayout);
        FrameVentaLayout.setHorizontalGroup(
            FrameVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameVentaLayout.setVerticalGroup(
            FrameVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar Productos");
        setBounds(new java.awt.Rectangle(350, 20, 850, 550));
        setIconImage(getIconImage());

        lblTitulo.setFont(new java.awt.Font("Dubai", 1, 16)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo.setText("Nombre o Codigo del producto: ");

        txtBusqueda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyPressed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jTabla.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo del producto", "Nombre", "Caracteristicas", "Stock", "Precio de venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTabla.setOpaque(false);
        jTabla.setRowHeight(30);
        jTabla.setRowMargin(5);
        jTabla.getTableHeader().setReorderingAllowed(false);
        jTabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTablaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTabla);
        if (jTabla.getColumnModel().getColumnCount() > 0) {
            jTabla.getColumnModel().getColumn(0).setPreferredWidth(150);
            jTabla.getColumnModel().getColumn(0).setMaxWidth(150);
            jTabla.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTabla.getColumnModel().getColumn(1).setMaxWidth(400);
            jTabla.getColumnModel().getColumn(3).setPreferredWidth(80);
            jTabla.getColumnModel().getColumn(3).setMaxWidth(80);
            jTabla.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTabla.getColumnModel().getColumn(4).setMaxWidth(110);
        }

        javax.swing.GroupLayout PanelConsultasLayout = new javax.swing.GroupLayout(PanelConsultas);
        PanelConsultas.setLayout(PanelConsultasLayout);
        PanelConsultasLayout.setHorizontalGroup(
            PanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(PanelConsultasLayout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 242, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelConsultasLayout.setVerticalGroup(
            PanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitulo)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBusquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            DefaultTableModel model = (DefaultTableModel) jTabla.getModel();
            if (model.getRowCount() > 0) {
                for (int i = model.getRowCount() - 1; i > -1; i--) {
                    model.removeRow(i);
                }
            }
            Buscar();
        }
    }//GEN-LAST:event_txtBusquedaKeyPressed

    private void jTablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            int Row = jTabla.getSelectedRow();
            Object Codigo = jTabla.getValueAt(Row, 0);
            if(Codigo != null){
                txtCodigo.setText(Codigo.toString());
                spnCantidad.setValue("1.0");
                FrameVenta.setVisible(true);
            }    
        }
    }//GEN-LAST:event_jTablaKeyPressed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Agregar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            Agregar();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Consultar().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame FrameVenta;
    private javax.swing.JPanel PanelConsultas;
    private javax.swing.JPanel PanelVenta;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTabla;
    private javax.swing.JLabel lblTitulo;
    public static javax.swing.JSpinner spnCantidad;
    private javax.swing.JTextField txtBusqueda;
    public static javax.swing.JTextField txtCodigo;
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

    class FondoPanel2 extends JPanel {

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
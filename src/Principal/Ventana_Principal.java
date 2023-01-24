/*
 *
 */
package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 * @author CeAnCof Software
 */
public class Ventana_Principal extends javax.swing.JFrame {

    static Connection con = Componentes.Conexion_BD.getConnection();
    FondoPanel fondo = new FondoPanel();
    public static Long Codigo;
    public static Object[][] Producto, Caracteristicas;
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement st;
    private static int contador = 1, Conti;
    private static Double Precio, Tot = 0.0, Cantidad;
    private Double[] Precios;
    private static final String[] CATEGORIA = {"papeleria", "regalos", "farmacia", "electronicos", "merceria", "catalogo"};
    public static String NombreVend, Canti;
    Date fecha;

    public static void setCanti(String Canti) {
        Ventana_Principal.Canti = Canti;
    }

    public static Long getCodigo() {
        return Codigo;
    }

    public static void setCodigo(Long Codigo) {
        Ventana_Principal.Codigo = Codigo;
    }

    public static Object[][] getProducto() {
        return Producto;
    }

    public static String getNombreVend() {
        return NombreVend;
    }

    public static void setNombreVend(String NombreVend) {
        Ventana_Principal.NombreVend = NombreVend;
    }

    public static void setProducto(Object[][] Producto) {
        Ventana_Principal.Producto = Producto;
    }

    public Ventana_Principal() {
        NombreVend = Identificadores.Login.getUsuario();
        initComponents();
    }

    public static void añadirBusq() {
        txtCodigo.setText(String.valueOf(Codigo));
        spnCantidad.setValue(Canti);
        BuscarIni();
    }

    @Override
    public Image getIconImage() {
        Image redValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Componentes/icono.png"));
        return redValue;
    }
    
    public static void BuscarIni(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) jTableVentas.getModel();
            int totalrows = modelo.getRowCount();
            String codi = txtCodigo.getText();
            Codigo = Long.parseLong(codi);
            Cantidad = Double.parseDouble(String.valueOf(spnCantidad.getValue()));
            if(totalrows==0){
                Buscar();
            }else{
                int repetidos=0;
                for(int i = 0; i < totalrows; i++){
                    if(codi.equals(String.valueOf(jTableVentas.getValueAt(i, 1)))) {
                        repetidos++;
                        double ItemsActual = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 3)));
                        double Valor = Double.parseDouble(String.valueOf(modelo.getValueAt(i, 4)));
                        double numVen = ItemsActual + Cantidad;
                        double subtotal = numVen * Valor;
                        modelo.setValueAt(numVen, i, 3);
                        modelo.setValueAt(subtotal, i, 5);
                        Tot += Cantidad * Valor;
                        txtTotal.setText(String.valueOf(Tot));
                        txtCodigo.setText(null);
                        spnCantidad.setValue("1.0");
                    }
                }
                if(repetidos==0){
                        Buscar();
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void Buscar() {
        try {
            Object Filas[] = new Object[6];
            Object[] DatosG;
            Principal.Busqueda.Caracteristicas();
            int conti = Principal.Busqueda.getEnc();
            if (conti == 1) {
                DatosG = Principal.Busqueda.getDatos();
                Filas[0] = contador;
                Filas[1] = DatosG[0];
                Filas[2] = DatosG[1];
                Filas[3] = Cantidad;
                Precio = (Double) Double.parseDouble(DatosG[2].toString());
                Filas[4] = Precio;
                Filas[5] = (Precio * Cantidad);
                DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
                model.addRow(Filas);
                contador += 1;
                txtCodigo.setText(null);
                Tot += (Precio * Cantidad);
                txtTotal.setText(String.valueOf(Tot));
                spnCantidad.setValue("1.0");
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void detalles() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
            int continuar, cont = 0;
            contador = 0;
            String categoriaAc = null;
            Caracteristicas = new Object[model.getRowCount()][11];
            fecha = new Date();
            java.sql.Date dat = new java.sql.Date(fecha.getTime());
            double Stock, NuevoStock;

            for (int i = 0; i < model.getRowCount(); i++) {
                do {
                    st = con.createStatement();
                    rs = st.executeQuery("SELECT Codigo_Producto FROM " + CATEGORIA[cont] + " where Codigo_Producto='" + model.getValueAt(contador, 1) + "';");
                    if (rs.next()) {
                        categoriaAc = CATEGORIA[cont];
                        continuar = 1;
                        contador += 1;
                        cont = 0;
                    } else {
                        cont += 1;
                        categoriaAc = null;
                        continuar = 0;
                    }
                } while (continuar == 0 && cont < 6);
                switch (categoriaAc) {
                    case "papeleria":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM papeleria where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Stock = rs.getDouble("Stock");
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "papeleria";
                        Caracteristicas[i][10] = (rs.getString("Tamaño") + "  " + rs.getString("Color"));
                        break;

                    case "regalos":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM regalos where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        Stock = rs.getDouble("Stock");
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "regalos";
                        Caracteristicas[i][10] = (rs.getString("Tamaño") + "  " + rs.getString("Tipo"));
                        break;

                    case "farmacia":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM farmacia where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        Stock = rs.getDouble("Stock");
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "farmacia";
                        Caracteristicas[i][10] = (rs.getString("Cantidad") + "  " + rs.getString("Caracteristicas"));
                        break;

                    case "electronicos":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM electronicos where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        Stock = rs.getDouble("Stock");
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "electronicos";
                        Caracteristicas[i][10] = (rs.getString("Caracteristicas") + "  " + rs.getString("Registro"));
                        break;

                    case "merceria":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM merceria where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        Stock = rs.getDouble("Stock");
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "merceria";
                        Caracteristicas[i][10] = (rs.getString("Tamaño") + "  " + rs.getString("Color"));
                        break;

                    case "catalogo":
                        st = con.createStatement();
                        rs = st.executeQuery("SELECT * FROM catalogo where Codigo_Producto='" + model.getValueAt(i, 1) + "';");
                        rs.next();
                        Cantidad = Double.parseDouble(model.getValueAt(i, 3).toString());
                        Stock = rs.getDouble("Stock");
                        NuevoStock = (Stock - Cantidad);
                        Caracteristicas[i][0] = dat;
                        Caracteristicas[i][1] = rs.getLong("Codigo_Producto");
                        Caracteristicas[i][2] = rs.getString("Nombre");
                        Caracteristicas[i][3] = rs.getString("Marca");
                        Caracteristicas[i][4] = rs.getDouble("Precio_Compra");
                        Caracteristicas[i][5] = jTableVentas.getValueAt(i, 5);
                        Caracteristicas[i][6] = NombreVend;
                        Caracteristicas[i][7] = NuevoStock;
                        Caracteristicas[i][8] = Cantidad;
                        Caracteristicas[i][9] = "catalogo";
                        Caracteristicas[i][10] = (rs.getString("Caracteristicas") + "  " + rs.getString("Tamaño") + " " + rs.getString("Color"));
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error con el producto: " + (i + 1));
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Subir() {
        try {
            int max = Caracteristicas.length;
            for (int i = 0; i < max; i++) {
                ps = con.prepareStatement("insert into ventas(Fecha_Venta,Codigo_Producto,Nombre,Marca,Precio_Compra,Precio_Venta,Vendedor,Stock,vendidos,Categoria,Caracteristica)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?)");
                java.sql.Date dat = new java.sql.Date(fecha.getTime());
                ps.setObject(1, Caracteristicas[i][0]);
                ps.setObject(2, Caracteristicas[i][1]);
                ps.setObject(3, Caracteristicas[i][2]);
                ps.setObject(4, Caracteristicas[i][3]);
                ps.setObject(5, Caracteristicas[i][4]);
                ps.setObject(6, Caracteristicas[i][5]);
                ps.setObject(7, Caracteristicas[i][6]);
                ps.setObject(8, Caracteristicas[i][7]);
                ps.setObject(9, Caracteristicas[i][8]);
                ps.setObject(10, Caracteristicas[i][9]);
                ps.setObject(11, Caracteristicas[i][10]);
                int r = ps.executeUpdate();
                PreparedStatement ps2 = con.prepareStatement("update " + Caracteristicas[i][9] + " set Stock=? where Codigo_Producto ='" + Caracteristicas[i][1] + "';");
                ps2.setObject(1, Caracteristicas[i][7]);
                int r2 = ps2.executeUpdate();
                if (r2 != 1) {
                    JOptionPane.showMessageDialog(null, "Error en el producto " + (i + 1));
                }
                if (r == 0) {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al guardar en BD");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void Cobrar() {
        try {
            String Cambios = txtDinero.getText();
            Double Cambio = 0.0;
            Double venta = Double.parseDouble(txtTotal.getText());
            lblCambio3.setText(String.valueOf(Cambio));
            lblCambio3.setForeground(Color.black);
            lblCambio2.setForeground(Color.black);
            if (!"".equals(Cambios)) {
                double c = Double.parseDouble(Cambios);
                Cambio = c - venta;
                if (Cambio >= 0) {
                    lblCambio3.setForeground(Color.white);
                    lblCambio2.setForeground(Color.white);
                    lblCambio3.setText(String.valueOf(Cambio));
                    btnAceptarCobro.setEnabled(true);
                    Conti = 1;
                } else {
                    lblCambio3.setForeground(Color.red);
                    lblCambio2.setForeground(Color.red);
                    lblCambio3.setText(String.valueOf(Cambio));
                    btnAceptarCobro.setEnabled(false);
                    Conti = 0;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Introdujo un caracter no numerico");
            Conti = 2;
        }
    }

    public void Limpiar() {
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        Tot = 0.0;
        txtTotal.setText("0.0");
        txtDinero.setText(null);
        lblTotal3.setText("0.0");
        lblCambio3.setText("0.0");
        Caracteristicas = null;
        Producto = null;
        Precios = null;
        contador = 1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cobro = new javax.swing.JFrame();
        jPanel1 = new FondoPanel();
        lblTotal3 = new javax.swing.JLabel();
        lblTotal2 = new javax.swing.JLabel();
        lblCambio2 = new javax.swing.JLabel();
        lblCambio3 = new javax.swing.JLabel();
        txtDinero = new java.awt.TextField();
        btnAceptarCobro = new javax.swing.JButton();
        lblTotal1 = new javax.swing.JLabel();
        lblDinero = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        JCambiarUser = new javax.swing.JDialog();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblcontra = new javax.swing.JLabel();
        btnCambiar = new javax.swing.JButton();
        pfContra = new javax.swing.JPasswordField();
        PrincipalPanel = new FondoPanel();
        lblCodigo = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        jSpTabla = new javax.swing.JScrollPane();
        jTableVentas = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        btnDescuento = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnDuplicar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCobrar = new javax.swing.JButton();
        lblPropiedad = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        ServiciosMenu = new javax.swing.JMenu();
        ApartadosMenuItem = new javax.swing.JMenuItem();
        RentasMenuItem = new javax.swing.JMenuItem();
        PedidosMenuItem = new javax.swing.JMenuItem();
        ProductosMenu = new javax.swing.JMenu();
        ConsutarMenuItem = new javax.swing.JMenuItem();
        AdministracionMenuItem = new javax.swing.JMenuItem();
        ReportesMenuItem = new javax.swing.JMenuItem();
        OpcionesMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        AcercaMenuItem1 = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        Cobro.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Cobro.setTitle("Cobrar");
        Cobro.setBounds(new java.awt.Rectangle(200, 50, 350, 250));
        Cobro.setIconImage(getIconImage());

        lblTotal3.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        lblTotal3.setText("0.00");

        lblTotal2.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        lblTotal2.setText("$");

        lblCambio2.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        lblCambio2.setText("$");

        lblCambio3.setFont(new java.awt.Font("Dialog", 0, 22)); // NOI18N
        lblCambio3.setText("0.00");

        txtDinero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        txtDinero.setFont(new java.awt.Font("Dubai", 0, 16)); // NOI18N
        txtDinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDineroActionPerformed(evt);
            }
        });
        txtDinero.addTextListener(new java.awt.event.TextListener() {
            public void textValueChanged(java.awt.event.TextEvent evt) {
                txtDineroTextValueChanged(evt);
            }
        });
        txtDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDineroKeyPressed(evt);
            }
        });

        btnAceptarCobro.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        btnAceptarCobro.setText("Cobrar");
        btnAceptarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarCobroActionPerformed(evt);
            }
        });

        lblTotal1.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        lblTotal1.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal1.setText("Total:");

        lblDinero.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        lblDinero.setForeground(new java.awt.Color(255, 255, 255));
        lblDinero.setText("Dinero:");

        lblCambio.setFont(new java.awt.Font("Dubai", 0, 15)); // NOI18N
        lblCambio.setForeground(new java.awt.Color(255, 255, 255));
        lblCambio.setText("Cambio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotal1)
                            .addComponent(lblDinero)
                            .addComponent(lblCambio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTotal2)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCambio2)
                                .addGap(18, 18, 18)
                                .addComponent(lblCambio3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptarCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal3)
                    .addComponent(lblTotal2)
                    .addComponent(lblTotal1))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDinero))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCambio3)
                    .addComponent(lblCambio2)
                    .addComponent(lblCambio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnAceptarCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout CobroLayout = new javax.swing.GroupLayout(Cobro.getContentPane());
        Cobro.getContentPane().setLayout(CobroLayout);
        CobroLayout.setHorizontalGroup(
            CobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CobroLayout.setVerticalGroup(
            CobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        JCambiarUser.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        JCambiarUser.setTitle("Cambiar");
        JCambiarUser.setBounds(new java.awt.Rectangle(50, 50, 240, 250));
        JCambiarUser.setResizable(false);

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUser.setText("Usuario");

        lblcontra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblcontra.setText("Contraseña");

        btnCambiar.setText("Aceptar");
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JCambiarUserLayout = new javax.swing.GroupLayout(JCambiarUser.getContentPane());
        JCambiarUser.getContentPane().setLayout(JCambiarUserLayout);
        JCambiarUserLayout.setHorizontalGroup(
            JCambiarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JCambiarUserLayout.createSequentialGroup()
                .addGroup(JCambiarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JCambiarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(JCambiarUserLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblcontra))
                        .addGroup(JCambiarUserLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(lblUser))
                        .addGroup(JCambiarUserLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(txtUser, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGroup(JCambiarUserLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(pfContra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)))
                    .addGroup(JCambiarUserLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(btnCambiar)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        JCambiarUserLayout.setVerticalGroup(
            JCambiarUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JCambiarUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUser)
                .addGap(18, 18, 18)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblcontra)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pfContra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambiar)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas");
        setBounds(new java.awt.Rectangle(50, 50, 850, 630));
        setIconImage(getIconImage());

        lblCodigo.setFont(new java.awt.Font("DialogInput", 1, 21)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(255, 255, 255));
        lblCodigo.setText("Codigo del Producto:");

        btnAceptar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        jTableVentas.setFont(new java.awt.Font("Bell MT", 1, 17)); // NOI18N
        jTableVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Codigo", "Producto", "Cantidad", "Precio", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableVentas.setOpaque(false);
        jTableVentas.setRowHeight(30);
        jTableVentas.setSelectionBackground(new java.awt.Color(0, 153, 153));
        jTableVentas.getTableHeader().setReorderingAllowed(false);
        jSpTabla.setViewportView(jTableVentas);
        if (jTableVentas.getColumnModel().getColumnCount() > 0) {
            jTableVentas.getColumnModel().getColumn(0).setMinWidth(35);
            jTableVentas.getColumnModel().getColumn(0).setPreferredWidth(40);
            jTableVentas.getColumnModel().getColumn(0).setMaxWidth(45);
            jTableVentas.getColumnModel().getColumn(1).setMinWidth(130);
            jTableVentas.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTableVentas.getColumnModel().getColumn(1).setMaxWidth(170);
            jTableVentas.getColumnModel().getColumn(3).setMinWidth(70);
            jTableVentas.getColumnModel().getColumn(3).setPreferredWidth(75);
            jTableVentas.getColumnModel().getColumn(3).setMaxWidth(80);
            jTableVentas.getColumnModel().getColumn(4).setMinWidth(80);
            jTableVentas.getColumnModel().getColumn(4).setPreferredWidth(90);
            jTableVentas.getColumnModel().getColumn(4).setMaxWidth(110);
            jTableVentas.getColumnModel().getColumn(5).setMinWidth(80);
            jTableVentas.getColumnModel().getColumn(5).setPreferredWidth(90);
            jTableVentas.getColumnModel().getColumn(5).setMaxWidth(110);
        }

        txtTotal.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(0, 204, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0.0");

        lblTotal.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Total:");

        spnCantidad.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerListModel(new String[] {"0.5", "1.0", "1.5", "2.0", "2.5", "3.0", "3.5", "4.0", "4.5", "5.0", "5.5", "6.0", "6.5", "7.0", "7.5", "8.0", "8.5", "9.0", "9.5", "10.0", "10.5", "11.0", "11.5", "12.0", "12.5", "13.0", "13.5", "14.0", "14.5", "15.0", "15.5", "16.0", "16.5", "17.0", "17.5", "18.0", "18.5", "19.0", "19.5", "20.0", "20.5", "21.0", "21.5", "22.0", "22.5", "23.0", "23.5", "24.0", "24.5", "25.0", "25.5", "26.0", "26.5", "27.0", "27.5", "28.0", "28.5", "29.0", "29.5", "30.0", "30.5", "31.0", "31.5", "32.0", "32.5", "33.0", "33.5", "34.0", "34.5", "35.0", "35.5", "36.0", "36.5", "37.0", "37.5", "38.0", "38.5", "39.0", "39.5", "40.0", "40.5", "41.0", "41.5", "42.0", "42.5", "43.0", "43.5", "44.0", "44.5", "45.0", "45.5", "46.0", "46.5", "47.0", "47.5", "48.0", "48.5", "49.0", "49.5", "50.0", "50.5", "51.0", "51.5", "52.0", "52.5", "53.0", "53.5", "54.0", "54.5", "55.0", "55.5", "56.0", "56.5", "57.0", "57.5", "58.0", "58.5", "59.0", "59.5", "60.0", "60.5", "61.0", "61.5", "62.0", "62.5", "63.0", "63.5", "64.0", "64.5", "65.0", "65.5", "66.0", "66.5", "67.0", "67.5", "68.0", "68.5", "69.0", "69.5", "70.0", "70.5", "71.0", "71.5", "72.0", "72.5", "73.0", "73.5", "74.0", "74.5", "75.0", "75.5", "76.0", "76.5", "77.0", "77.5", "78.0", "78.5", "79.0", "79.5", "80.0", "80.5", "81.0", "81.5", "82.0", "82.5", "83.0", "83.5", "84.0", "84.5", "85.0", "85.5", "86.0", "86.5", "87.0", "87.5", "88.0", "88.5", "89.0", "89.5", "90.0", "90.5", "91.0", "91.5", "92.0", "92.5", "93.0", "93.5", "94.0", "94.5", "95.0", "95.5", "96.0", "96.5", "97.0", "97.5", "98.0", "98.5", "99.0", "99.5", "100.0", "100.5", "101.0", "101.5", "102.0", "102.5", "103.0", "103.5", "104.0", "104.5", "105.0", "105.5", "106.0", "106.5", "107.0", "107.5", "108.0", "108.5", "109.0", "109.5", "110.0", "110.5", "111.0", "111.5", "112.0", "112.5", "113.0", "113.5", "114.0", "114.5", "115.0", "115.5", "116.0", "116.5", "117.0", "117.5", "118.0", "118.5", "119.0", "119.5", "120.0", "120.5", "121.0", "121.5", "122.0", "122.5", "123.0", "123.5", "124.0", "124.5", "125.0", "125.5", "126.0", "126.5", "127.0", "127.5", "128.0", "128.5", "129.0", "129.5", "130.0", "130.5", "131.0", "131.5", "132.0", "132.5", "133.0", "133.5", "134.0", "134.5", "135.0", "135.5", "136.0", "136.5", "137.0", "137.5", "138.0", "138.5", "139.0", "139.5", "140.0", "140.5", "141.0", "141.5", "142.0", "142.5", "143.0", "143.5", "144.0", "144.5", "145.0", "145.5", "146.0", "146.5", "147.0", "147.5", "148.0", "148.5", "149.0", "149.5", "150.0", "150.5", "151.0", "151.5", "152.0", "152.5", "153.0", "153.5", "154.0", "154.5", "155.0", "155.5", "156.0", "156.5", "157.0", "157.5", "158.0", "158.5", "159.0", "159.5", "160.0", "160.5", "161.0", "161.5", "162.0", "162.5", "163.0", "163.5", "164.0", "164.5", "165.0", "165.5", "166.0", "166.5", "167.0", "167.5", "168.0", "168.5", "169.0", "169.5", "170.0", "170.5", "171.0", "171.5", "172.0", "172.5", "173.0", "173.5", "174.0", "174.5", "175.0", "175.5", "176.0", "176.5", "177.0", "177.5", "178.0", "178.5", "179.0", "179.5", "180.0", "180.5", "181.0", "181.5", "182.0", "182.5", "183.0", "183.5", "184.0", "184.5", "185.0", "185.5", "186.0", "186.5", "187.0", "187.5", "188.0", "188.5", "189.0", "189.5", "190.0", "190.5", "191.0", "191.5", "192.0", "192.5", "193.0", "193.5", "194.0", "194.5", "195.0", "195.5", "196.0", "196.5", "197.0", "197.5", "198.0", "198.5", "199.0", "199.5", "200.0", "200.5", "201.0", "201.5", "202.0", "202.5", "203.0", "203.5", "204.0", "204.5", "205.0", "205.5", "206.0", "206.5", "207.0", "207.5", "208.0", "208.5", "209.0", "209.5", "210.0", "210.5", "211.0", "211.5", "212.0", "212.5", "213.0", "213.5", "214.0", "214.5", "215.0", "215.5", "216.0", "216.5", "217.0", "217.5", "218.0", "218.5", "219.0", "219.5", "220.0", "220.5", "221.0", "221.5", "222.0", "222.5", "223.0", "223.5", "224.0", "224.5", "225.0", "225.5", "226.0", "226.5", "227.0", "227.5", "228.0", "228.5", "229.0", "229.5", "230.0", "230.5", "231.0", "231.5", "232.0", "232.5", "233.0", "233.5", "234.0", "234.5", "235.0", "235.5", "236.0", "236.5", "237.0", "237.5", "238.0", "238.5", "239.0", "239.5", "240.0", "240.5", "241.0", "241.5", "242.0", "242.5", "243.0", "243.5", "244.0", "244.5", "245.0", "245.5", "246.0", "246.5", "247.0", "247.5", "248.0", "248.5", "249.0", "249.5", "250.0", "250.5", "251.0", "251.5", "252.0", "252.5", "253.0", "253.5", "254.0", "254.5", "255.0", "255.5", "256.0", "256.5", "257.0", "257.5", "258.0", "258.5", "259.0", "259.5", "260.0", "260.5", "261.0", "261.5", "262.0", "262.5", "263.0", "263.5", "264.0", "264.5", "265.0", "265.5", "266.0", "266.5", "267.0", "267.5", "268.0", "268.5", "269.0", "269.5", "270.0", "270.5", "271.0", "271.5", "272.0", "272.5", "273.0", "273.5", "274.0", "274.5", "275.0", "275.5", "276.0", "276.5", "277.0", "277.5", "278.0", "278.5", "279.0", "279.5", "280.0", "280.5", "281.0", "281.5", "282.0", "282.5", "283.0", "283.5", "284.0", "284.5", "285.0", "285.5", "286.0", "286.5", "287.0", "287.5", "288.0", "288.5", "289.0", "289.5", "290.0", "290.5", "291.0", "291.5", "292.0", "292.5", "293.0", "293.5", "294.0", "294.5", "295.0", "295.5", "296.0", "296.5", "297.0", "297.5", "298.0", "298.5", "299.0", "299.5", "300.0", "300.5", "301.0", "301.5", "302.0", "302.5", "303.0", "303.5", "304.0", "304.5", "305.0", "305.5", "306.0", "306.5", "307.0", "307.5", "308.0", "308.5", "309.0", "309.5", "310.0", "310.5", "311.0", "311.5", "312.0", "312.5", "313.0", "313.5", "314.0", "314.5", "315.0", "315.5", "316.0", "316.5", "317.0", "317.5", "318.0", "318.5", "319.0", "319.5", "320.0", "320.5", "321.0", "321.5", "322.0", "322.5", "323.0", "323.5", "324.0", "324.5", "325.0", "325.5", "326.0", "326.5", "327.0", "327.5", "328.0", "328.5", "329.0", "329.5", "330.0", "330.5", "331.0", "331.5", "332.0", "332.5", "333.0", "333.5", "334.0", "334.5", "335.0", "335.5", "336.0", "336.5", "337.0", "337.5", "338.0", "338.5", "339.0", "339.5", "340.0", "340.5", "341.0", "341.5", "342.0", "342.5", "343.0", "343.5", "344.0", "344.5", "345.0", "345.5", "346.0", "346.5", "347.0", "347.5", "348.0", "348.5", "349.0", "349.5", "350.0", "350.5", "351.0", "351.5", "352.0", "352.5", "353.0", "353.5", "354.0", "354.5", "355.0", "355.5", "356.0", "356.5", "357.0", "357.5", "358.0", "358.5", "359.0", "359.5", "360.0", "360.5", "361.0", "361.5", "362.0", "362.5", "363.0", "363.5", "364.0", "364.5", "365.0", "365.5", "366.0", "366.5", "367.0", "367.5", "368.0", "368.5", "369.0", "369.5", "370.0", "370.5", "371.0", "371.5", "372.0", "372.5", "373.0", "373.5", "374.0", "374.5", "375.0", "375.5", "376.0", "376.5", "377.0", "377.5", "378.0", "378.5", "379.0", "379.5", "380.0", "380.5", "381.0", "381.5", "382.0", "382.5", "383.0", "383.5", "384.0", "384.5", "385.0", "385.5", "386.0", "386.5", "387.0", "387.5", "388.0", "388.5", "389.0", "389.5", "390.0", "390.5", "391.0", "391.5", "392.0", "392.5", "393.0", "393.5", "394.0", "394.5", "395.0", "395.5", "396.0", "396.5", "397.0", "397.5", "398.0", "398.5", "399.0", "399.5", "400.0", "400.5", "401.0", "401.5", "402.0", "402.5", "403.0", "403.5", "404.0", "404.5", "405.0", "405.5", "406.0", "406.5", "407.0", "407.5", "408.0", "408.5", "409.0", "409.5", "410.0", "410.5", "411.0", "411.5", "412.0", "412.5", "413.0", "413.5", "414.0", "414.5", "415.0", "415.5", "416.0", "416.5", "417.0", "417.5", "418.0", "418.5", "419.0", "419.5", "420.0", "420.5", "421.0", "421.5", "422.0", "422.5", "423.0", "423.5", "424.0", "424.5", "425.0", "425.5", "426.0", "426.5", "427.0", "427.5", "428.0", "428.5", "429.0", "429.5", "430.0", "430.5", "431.0", "431.5", "432.0", "432.5", "433.0", "433.5", "434.0", "434.5", "435.0", "435.5", "436.0", "436.5", "437.0", "437.5", "438.0", "438.5", "439.0", "439.5", "440.0", "440.5", "441.0", "441.5", "442.0", "442.5", "443.0", "443.5", "444.0", "444.5", "445.0", "445.5", "446.0", "446.5", "447.0", "447.5", "448.0", "448.5", "449.0", "449.5", "450.0", "450.5", "451.0", "451.5", "452.0", "452.5", "453.0", "453.5", "454.0", "454.5", "455.0", "455.5", "456.0", "456.5", "457.0", "457.5", "458.0", "458.5", "459.0", "459.5", "460.0", "460.5", "461.0", "461.5", "462.0", "462.5", "463.0", "463.5", "464.0", "464.5", "465.0", "465.5", "466.0", "466.5", "467.0", "467.5", "468.0", "468.5", "469.0", "469.5", "470.0", "470.5", "471.0", "471.5", "472.0", "472.5", "473.0", "473.5", "474.0", "474.5", "475.0", "475.5", "476.0", "476.5", "477.0", "477.5", "478.0", "478.5", "479.0", "479.5", "480.0", "480.5", "481.0", "481.5", "482.0", "482.5", "483.0", "483.5", "484.0", "484.5", "485.0", "485.5", "486.0", "486.5", "487.0", "487.5", "488.0", "488.5", "489.0", "489.5", "490.0", "490.5", "491.0", "491.5", "492.0", "492.5", "493.0", "493.5", "494.0", "494.5", "495.0", "495.5", "496.0", "496.5", "497.0", "497.5", "498.0", "498.5", "499.0", "499.5", "500.0", "500.5", "501.0", "501.5", "502.0", "502.5", "503.0", "503.5", "504.0", "504.5", "505.0", "505.5", "506.0", "506.5", "507.0", "507.5", "508.0", "508.5", "509.0", "509.5", "510.0", "510.5", "511.0", "511.5", "512.0", "512.5", "513.0", "513.5", "514.0", "514.5", "515.0", "515.5", "516.0", "516.5", "517.0", "517.5", "518.0", "518.5", "519.0", "519.5", "520.0", "520.5", "521.0", "521.5", "522.0", "522.5", "523.0", "523.5", "524.0", "524.5", "525.0", "525.5", "526.0", "526.5", "527.0", "527.5", "528.0", "528.5", "529.0", "529.5", "530.0", "530.5", "531.0", "531.5", "532.0", "532.5", "533.0", "533.5", "534.0", "534.5", "535.0", "535.5", "536.0", "536.5", "537.0", "537.5", "538.0", "538.5", "539.0", "539.5", "540.0", "540.5", "541.0", "541.5", "542.0", "542.5", "543.0", "543.5", "544.0", "544.5", "545.0", "545.5", "546.0", "546.5", "547.0", "547.5", "548.0", "548.5", "549.0", "549.5", "550.0", "550.5", "551.0", "551.5", "552.0", "552.5", "553.0", "553.5", "554.0", "554.5", "555.0", "555.5", "556.0", "556.5", "557.0", "557.5", "558.0", "558.5", "559.0", "559.5", "560.0", "560.5", "561.0", "561.5", "562.0", "562.5", "563.0", "563.5", "564.0", "564.5", "565.0", "565.5", "566.0", "566.5", "567.0", "567.5", "568.0", "568.5", "569.0", "569.5", "570.0", "570.5", "571.0", "571.5", "572.0", "572.5", "573.0", "573.5", "574.0", "574.5", "575.0", "575.5", "576.0", "576.5", "577.0", "577.5", "578.0", "578.5", "579.0", "579.5", "580.0", "580.5", "581.0", "581.5", "582.0", "582.5", "583.0", "583.5", "584.0", "584.5", "585.0", "585.5", "586.0", "586.5", "587.0", "587.5", "588.0", "588.5", "589.0", "589.5", "590.0", "590.5", "591.0", "591.5", "592.0", "592.5", "593.0", "593.5", "594.0", "594.5", "595.0", "595.5", "596.0", "596.5", "597.0", "597.5", "598.0", "598.5", "599.0", "599.5", "600.0", "600.5", "601.0", "601.5", "602.0", "602.5", "603.0", "603.5", "604.0", "604.5", "605.0", "605.5", "606.0", "606.5", "607.0", "607.5", "608.0", "608.5", "609.0", "609.5", "610.0", "610.5", "611.0", "611.5", "612.0", "612.5", "613.0", "613.5", "614.0", "614.5", "615.0", "615.5", "616.0", "616.5", "617.0", "617.5", "618.0", "618.5", "619.0", "619.5", "620.0", "620.5", "621.0", "621.5", "622.0", "622.5", "623.0", "623.5", "624.0", "624.5", "625.0", "625.5", "626.0", "626.5", "627.0", "627.5", "628.0", "628.5", "629.0", "629.5", "630.0", "630.5", "631.0", "631.5", "632.0", "632.5", "633.0", "633.5", "634.0", "634.5", "635.0", "635.5", "636.0", "636.5", "637.0", "637.5", "638.0", "638.5", "639.0", "639.5", "640.0", "640.5", "641.0", "641.5", "642.0", "642.5", "643.0", "643.5", "644.0", "644.5", "645.0", "645.5", "646.0", "646.5", "647.0", "647.5", "648.0", "648.5", "649.0", "649.5", "650.0", "650.5", "651.0", "651.5", "652.0", "652.5", "653.0", "653.5", "654.0", "654.5", "655.0", "655.5", "656.0", "656.5", "657.0", "657.5", "658.0", "658.5", "659.0", "659.5", "660.0", "660.5", "661.0", "661.5", "662.0", "662.5", "663.0", "663.5", "664.0", "664.5", "665.0", "665.5", "666.0", "666.5", "667.0", "667.5", "668.0", "668.5", "669.0", "669.5", "670.0", "670.5", "671.0", "671.5", "672.0", "672.5", "673.0", "673.5", "674.0", "674.5", "675.0", "675.5", "676.0", "676.5", "677.0", "677.5", "678.0", "678.5", "679.0", "679.5", "680.0", "680.5", "681.0", "681.5", "682.0", "682.5", "683.0", "683.5", "684.0", "684.5", "685.0", "685.5", "686.0", "686.5", "687.0", "687.5", "688.0", "688.5", "689.0", "689.5", "690.0", "690.5", "691.0", "691.5", "692.0", "692.5", "693.0", "693.5", "694.0", "694.5", "695.0", "695.5", "696.0", "696.5", "697.0", "697.5", "698.0", "698.5", "699.0", "699.5", "700.0", "700.5", "701.0", "701.5", "702.0", "702.5", "703.0", "703.5", "704.0", "704.5", "705.0", "705.5", "706.0", "706.5", "707.0", "707.5", "708.0", "708.5", "709.0", "709.5", "710.0", "710.5", "711.0", "711.5", "712.0", "712.5", "713.0", "713.5", "714.0", "714.5", "715.0", "715.5", "716.0", "716.5", "717.0", "717.5", "718.0", "718.5", "719.0", "719.5", "720.0", "720.5", "721.0", "721.5", "722.0", "722.5", "723.0", "723.5", "724.0", "724.5", "725.0", "725.5", "726.0", "726.5", "727.0", "727.5", "728.0", "728.5", "729.0", "729.5", "730.0", "730.5", "731.0", "731.5", "732.0", "732.5", "733.0", "733.5", "734.0", "734.5", "735.0", "735.5", "736.0", "736.5", "737.0", "737.5", "738.0", "738.5", "739.0", "739.5", "740.0", "740.5", "741.0", "741.5", "742.0", "742.5", "743.0", "743.5", "744.0", "744.5", "745.0", "745.5", "746.0", "746.5", "747.0", "747.5", "748.0", "748.5", "749.0", "749.5", "750.0", "750.5", "751.0", "751.5", "752.0", "752.5", "753.0", "753.5", "754.0", "754.5", "755.0", "755.5", "756.0", "756.5", "757.0", "757.5", "758.0", "758.5", "759.0", "759.5", "760.0", "760.5", "761.0", "761.5", "762.0", "762.5", "763.0", "763.5", "764.0", "764.5", "765.0", "765.5", "766.0", "766.5", "767.0", "767.5", "768.0", "768.5", "769.0", "769.5", "770.0", "770.5", "771.0", "771.5", "772.0", "772.5", "773.0", "773.5", "774.0", "774.5", "775.0", "775.5", "776.0", "776.5", "777.0", "777.5", "778.0", "778.5", "779.0", "779.5", "780.0", "780.5", "781.0", "781.5", "782.0", "782.5", "783.0", "783.5", "784.0", "784.5", "785.0", "785.5", "786.0", "786.5", "787.0", "787.5", "788.0", "788.5", "789.0", "789.5", "790.0", "790.5", "791.0", "791.5", "792.0", "792.5", "793.0", "793.5", "794.0", "794.5", "795.0", "795.5", "796.0", "796.5", "797.0", "797.5", "798.0", "798.5", "799.0", "799.5", "800.0", "800.5", "801.0", "801.5", "802.0", "802.5", "803.0", "803.5", "804.0", "804.5", "805.0", "805.5", "806.0", "806.5", "807.0", "807.5", "808.0", "808.5", "809.0", "809.5", "810.0", "810.5", "811.0", "811.5", "812.0", "812.5", "813.0", "813.5", "814.0", "814.5", "815.0", "815.5", "816.0", "816.5", "817.0", "817.5", "818.0", "818.5", "819.0", "819.5", "820.0", "820.5", "821.0", "821.5", "822.0", "822.5", "823.0", "823.5", "824.0", "824.5", "825.0", "825.5", "826.0", "826.5", "827.0", "827.5", "828.0", "828.5", "829.0", "829.5", "830.0", "830.5", "831.0", "831.5", "832.0", "832.5", "833.0", "833.5", "834.0", "834.5", "835.0", "835.5", "836.0", "836.5", "837.0", "837.5", "838.0", "838.5", "839.0", "839.5", "840.0", "840.5", "841.0", "841.5", "842.0", "842.5", "843.0", "843.5", "844.0", "844.5", "845.0", "845.5", "846.0", "846.5", "847.0", "847.5", "848.0", "848.5", "849.0", "849.5", "850.0", "850.5", "851.0", "851.5", "852.0", "852.5", "853.0", "853.5", "854.0", "854.5", "855.0", "855.5", "856.0", "856.5", "857.0", "857.5", "858.0", "858.5", "859.0", "859.5", "860.0", "860.5", "861.0", "861.5", "862.0", "862.5", "863.0", "863.5", "864.0", "864.5", "865.0", "865.5", "866.0", "866.5", "867.0", "867.5", "868.0", "868.5", "869.0", "869.5", "870.0", "870.5", "871.0", "871.5", "872.0", "872.5", "873.0", "873.5", "874.0", "874.5", "875.0", "875.5", "876.0", "876.5", "877.0", "877.5", "878.0", "878.5", "879.0", "879.5", "880.0", "880.5", "881.0", "881.5", "882.0", "882.5", "883.0", "883.5", "884.0", "884.5", "885.0", "885.5", "886.0", "886.5", "887.0", "887.5", "888.0", "888.5", "889.0", "889.5", "890.0", "890.5", "891.0", "891.5", "892.0", "892.5", "893.0", "893.5", "894.0", "894.5", "895.0", "895.5", "896.0", "896.5", "897.0", "897.5", "898.0", "898.5", "899.0", "899.5", "900.0", "900.5", "901.0", "901.5", "902.0", "902.5", "903.0", "903.5", "904.0", "904.5", "905.0", "905.5", "906.0", "906.5", "907.0", "907.5", "908.0", "908.5", "909.0", "909.5", "910.0", "910.5", "911.0", "911.5", "912.0", "912.5", "913.0", "913.5", "914.0", "914.5", "915.0", "915.5", "916.0", "916.5", "917.0", "917.5", "918.0", "918.5", "919.0", "919.5", "920.0", "920.5", "921.0", "921.5", "922.0", "922.5", "923.0", "923.5", "924.0", "924.5", "925.0", "925.5", "926.0", "926.5", "927.0", "927.5", "928.0", "928.5", "929.0", "929.5", "930.0", "930.5", "931.0", "931.5", "932.0", "932.5", "933.0", "933.5", "934.0", "934.5", "935.0", "935.5", "936.0", "936.5", "937.0", "937.5", "938.0", "938.5", "939.0", "939.5", "940.0", "940.5", "941.0", "941.5", "942.0", "942.5", "943.0", "943.5", "944.0", "944.5", "945.0", "945.5", "946.0", "946.5", "947.0", "947.5", "948.0", "948.5", "949.0", "949.5", "950.0", "950.5", "951.0", "951.5", "952.0", "952.5", "953.0", "953.5", "954.0", "954.5", "955.0", "955.5", "956.0", "956.5", "957.0", "957.5", "958.0", "958.5", "959.0", "959.5", "960.0", "960.5", "961.0", "961.5", "962.0", "962.5", "963.0", "963.5", "964.0", "964.5", "965.0", "965.5", "966.0", "966.5", "967.0", "967.5", "968.0", "968.5", "969.0", "969.5", "970.0", "970.5", "971.0", "971.5", "972.0", "972.5", "973.0", "973.5", "974.0", "974.5", "975.0", "975.5", "976.0", "976.5", "977.0", "977.5", "978.0", "978.5", "979.0", "979.5", "980.0", "980.5", "981.0", "981.5", "982.0", "982.5", "983.0", "983.5", "984.0", "984.5", "985.0", "985.5", "986.0", "986.5", "987.0", "987.5", "988.0", "988.5", "989.0", "989.5", "990.0", "990.5", "991.0", "991.5", "992.0", "992.5", "993.0", "993.5", "994.0", "994.5", "995.0", "995.5", "996.0", "996.5", "997.0", "997.5", "998.0", "998.5", "999.0", "999.5", "1000.0", "1000.5"}));

        btnDescuento.setText("Descuento");
        btnDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescuentoActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnDuplicar.setText("Duplicar producto");
        btnDuplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDuplicarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar venta");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCobrar.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        lblPropiedad.setFont(new java.awt.Font("Bell MT", 2, 14)); // NOI18N
        lblPropiedad.setText("CeAnCof Software \tv2.1.01");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PrincipalPanelLayout = new javax.swing.GroupLayout(PrincipalPanel);
        PrincipalPanel.setLayout(PrincipalPanelLayout);
        PrincipalPanelLayout.setHorizontalGroup(
            PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalPanelLayout.createSequentialGroup()
                        .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PrincipalPanelLayout.createSequentialGroup()
                                .addComponent(lblPropiedad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTotal)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSpTabla))
                        .addGap(14, 14, 14)
                        .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDuplicar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(PrincipalPanelLayout.createSequentialGroup()
                        .addComponent(lblCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        PrincipalPanelLayout.setVerticalGroup(
            PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCobrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(spnCantidad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(txtCodigo))
                .addGap(25, 25, 25)
                .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(PrincipalPanelLayout.createSequentialGroup()
                        .addComponent(btnDuplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPropiedad, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        ServiciosMenu.setMnemonic('f');
        ServiciosMenu.setText("Servicios");

        ApartadosMenuItem.setMnemonic('o');
        ApartadosMenuItem.setText("Apartados");
        ApartadosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApartadosMenuItemActionPerformed(evt);
            }
        });
        ServiciosMenu.add(ApartadosMenuItem);

        RentasMenuItem.setMnemonic('s');
        RentasMenuItem.setText("Rentas");
        RentasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RentasMenuItemActionPerformed(evt);
            }
        });
        ServiciosMenu.add(RentasMenuItem);

        PedidosMenuItem.setMnemonic('d');
        PedidosMenuItem.setText("Pedidos");
        PedidosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PedidosMenuItemActionPerformed(evt);
            }
        });
        ServiciosMenu.add(PedidosMenuItem);

        menuBar.add(ServiciosMenu);

        ProductosMenu.setMnemonic('e');
        ProductosMenu.setText("Productos");

        ConsutarMenuItem.setMnemonic('n');
        ConsutarMenuItem.setText("Consultar");
        ConsutarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsutarMenuItemActionPerformed(evt);
            }
        });
        ProductosMenu.add(ConsutarMenuItem);

        AdministracionMenuItem.setMnemonic('t');
        AdministracionMenuItem.setText("Administracion");
        AdministracionMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdministracionMenuItemActionPerformed(evt);
            }
        });
        ProductosMenu.add(AdministracionMenuItem);

        ReportesMenuItem.setMnemonic('p');
        ReportesMenuItem.setText("Reportes");
        ReportesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesMenuItemActionPerformed(evt);
            }
        });
        ProductosMenu.add(ReportesMenuItem);

        menuBar.add(ProductosMenu);

        OpcionesMenu.setMnemonic('h');
        OpcionesMenu.setText("Opciones");

        jMenuItem1.setMnemonic('c');
        jMenuItem1.setText("Cambiar de usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        OpcionesMenu.add(jMenuItem1);

        AcercaMenuItem1.setMnemonic('e');
        AcercaMenuItem1.setText("Acerca de");
        AcercaMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaMenuItem1ActionPerformed(evt);
            }
        });
        OpcionesMenu.add(AcercaMenuItem1);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("Salir");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirMenuItemActionPerformed(evt);
            }
        });
        OpcionesMenu.add(aboutMenuItem);

        menuBar.add(OpcionesMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrincipalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PrincipalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PedidosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PedidosMenuItemActionPerformed
        Servicios.Pedidos form = new Servicios.Pedidos();
        form.setVisible(true);
    }//GEN-LAST:event_PedidosMenuItemActionPerformed

    private void AcercaMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaMenuItem1ActionPerformed
        Principal.AcercaDe form = new Principal.AcercaDe();
        form.setVisible(true);
    }//GEN-LAST:event_AcercaMenuItem1ActionPerformed

    private void SalirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirMenuItemActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        BuscarIni();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        int[] NumRows = jTableVentas.getSelectedRows();
        contador = 1;
        for (int i = NumRows.length - 1; i >= 0; i--) {
            Double T = Double.parseDouble(model.getValueAt(NumRows[i], 5).toString());
            Tot -= T;
            model.removeRow(NumRows[i]);
        }
        for (int i = 0; i < jTableVentas.getRowCount(); i++) {
            model.setValueAt(contador, i, 0);
            contador += 1;
        }
        txtTotal.setText(String.valueOf(Tot));
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
        int cantidad = model.getRowCount();
        if (cantidad > 0) {
            lblTotal3.setText(String.valueOf(Tot));
            Cobro.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "No hay productos en la lista de venta");
        }
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void txtDineroTextValueChanged(java.awt.event.TextEvent evt) {//GEN-FIRST:event_txtDineroTextValueChanged
        Cobrar();
    }//GEN-LAST:event_txtDineroTextValueChanged

    private void txtDineroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            if (Conti == 1) {
                detalles();
                Subir();
                Limpiar();
                Cobro.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "La cantidad introducida no puede ser menor al total de la venta");
            }
        }
    }//GEN-LAST:event_txtDineroKeyPressed

    private void btnAceptarCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarCobroActionPerformed
        if (Conti == 1) {
            detalles();
            Subir();
            Limpiar();
            Cobro.dispose();
        } else {
            if (Conti == 0) {
                JOptionPane.showMessageDialog(this, "La cantidad introducida no puede ser menor al total de la venta");
            }
            JOptionPane.showMessageDialog(this, "Al caracteres invalidos en el campo de dinero.");
        }
    }//GEN-LAST:event_btnAceptarCobroActionPerformed

    private void txtDineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDineroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDineroActionPerformed

    private void btnDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescuentoActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
            int[] NumRows = jTableVentas.getSelectedRows();
            String desc = JOptionPane.showInputDialog(null, "Descuento a aplicar");
            double Descuento = Double.parseDouble(desc);
            double pretotal = 0;
            if (Descuento > 0 && Descuento < 100) {
                Precios = new Double[NumRows.length];
                for (int i = 0; i < NumRows.length; i++) {
                    double PrecioPreDesc = Double.parseDouble(model.getValueAt(NumRows[i], 5).toString());
                    Precios[i] = (PrecioPreDesc - (PrecioPreDesc * (Descuento / 100)));
                    jTableVentas.setValueAt(Precios[i], NumRows[i], 5);
                    pretotal += (PrecioPreDesc * (Descuento / 100));
                }
                double Total = Double.parseDouble(txtTotal.getText());
                Total = Total - pretotal;
                Tot = Total;
                txtTotal.setText(String.valueOf(Total));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDescuentoActionPerformed

    private void btnDuplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDuplicarActionPerformed
        try {
            DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
            int[] numrow = jTableVentas.getSelectedRows();
            for (int i = 0; i < numrow.length; i++) {
                Object[] Dupli = new Object[6];
                Dupli[0] = contador;
                Dupli[1] = jTableVentas.getValueAt(numrow[i], 1);
                Dupli[2] = jTableVentas.getValueAt(numrow[i], 2);
                Dupli[3] = jTableVentas.getValueAt(numrow[i], 3);
                Dupli[4] = jTableVentas.getValueAt(numrow[i], 4);
                Dupli[5] = jTableVentas.getValueAt(numrow[i], 5);
                model.addRow(Dupli);
                double precio = Double.parseDouble(jTableVentas.getValueAt(numrow[i], 5).toString());
                double canti = Double.parseDouble(jTableVentas.getValueAt(numrow[i], 3).toString());
                Tot += precio;
                txtTotal.setText(String.valueOf(Tot));
                spnCantidad.setValue("1.0");
                contador += 1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnDuplicarActionPerformed

    private void ReportesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesMenuItemActionPerformed
        Identificadores.IdentificacionReportes form = new Identificadores.IdentificacionReportes();
        form.setVisible(true);
    }//GEN-LAST:event_ReportesMenuItemActionPerformed

    private void ConsutarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsutarMenuItemActionPerformed
        Principal.Consultar form = new Principal.Consultar();
        form.setVisible(true);
    }//GEN-LAST:event_ConsutarMenuItemActionPerformed

    private void AdministracionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdministracionMenuItemActionPerformed
        Identificadores.IdentificacionAdministracion metod = new Identificadores.IdentificacionAdministracion();
        metod.setVisible(true);
    }//GEN-LAST:event_AdministracionMenuItemActionPerformed

    private void ApartadosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApartadosMenuItemActionPerformed
        Servicios.Apartados form = new Servicios.Apartados();
        form.setVisible(true);
    }//GEN-LAST:event_ApartadosMenuItemActionPerformed

    private void RentasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RentasMenuItemActionPerformed
        Servicios.ConsultarRentas form = new Servicios.ConsultarRentas();
        form.setVisible(true);
    }//GEN-LAST:event_RentasMenuItemActionPerformed

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
           BuscarIni();
        }

        if (evt.getKeyChar() == KeyEvent.VK_ESCAPE) {
            DefaultTableModel model = (DefaultTableModel) jTableVentas.getModel();
            int cantidad = model.getRowCount();
            if (cantidad > 0) {
                lblTotal3.setText(String.valueOf(Tot));
                Cobro.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No hay productos en la lista de venta");
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_F3) {
            Principal.Consultar form = new Principal.Consultar();
            form.setVisible(true);
        }
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        try {
            String usuario = txtUser.getText();
            String contraseña = String.valueOf(pfContra.getPassword());
            String sql = "select * from usuarios_bd where nombre_User='" + usuario + "'  and contraseña_User='" + contraseña + "';";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                NombreVend = rs.getString("Nombre");
                JCambiarUser.setVisible(false);
                txtUser.setText(null);
                pfContra.setText(null);
                JCambiarUser.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor intentelo de nuevo");
            }
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JCambiarUser.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Ventana_Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AcercaMenuItem1;
    private javax.swing.JMenuItem AdministracionMenuItem;
    private javax.swing.JMenuItem ApartadosMenuItem;
    private javax.swing.JFrame Cobro;
    private javax.swing.JMenuItem ConsutarMenuItem;
    private javax.swing.JDialog JCambiarUser;
    private javax.swing.JMenu OpcionesMenu;
    private javax.swing.JMenuItem PedidosMenuItem;
    private javax.swing.JPanel PrincipalPanel;
    private javax.swing.JMenu ProductosMenu;
    private javax.swing.JMenuItem RentasMenuItem;
    private javax.swing.JMenuItem ReportesMenuItem;
    private javax.swing.JMenu ServiciosMenu;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptarCobro;
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnDescuento;
    private javax.swing.JButton btnDuplicar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jSpTabla;
    private static javax.swing.JTable jTableVentas;
    private javax.swing.JLabel lblCambio;
    private javax.swing.JLabel lblCambio2;
    private javax.swing.JLabel lblCambio3;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDinero;
    private javax.swing.JLabel lblPropiedad;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotal1;
    private javax.swing.JLabel lblTotal2;
    private javax.swing.JLabel lblTotal3;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblcontra;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPasswordField pfContra;
    public static javax.swing.JSpinner spnCantidad;
    public static javax.swing.JTextField txtCodigo;
    private java.awt.TextField txtDinero;
    private static javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUser;
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

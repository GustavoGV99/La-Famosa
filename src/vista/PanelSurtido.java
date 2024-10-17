package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import static utileria.DateAndTime.*;
import static utileria.Validaciones.*;
import consultas.ConsultasSucursal;
import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import controlador.CtrlChecador;
import controlador.CtrlProducto;
import controlador.CtrlProveedor;
import modelo.Acomodo;
import modelo.DetalleProducto;
import modelo.Producto;
import modelo.Proveedor;
import modelo.DetalleSurtido;
import modelo.Sucursal;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class PanelSurtido extends JPanel {
	public JTextField txtFieldTotal;
	
	public JButton btnRegistrar;
	public JButton btnBuscar;
	public JButton btnModificar;
	
	public JTable table;
	public JTextField textFieldFecha;
	public JTextField textFieldCantidad;
	public JTextField textFieldCaducidad;
	public JTextField textFieldPrecio;
	public JTextField textFieldFechaAcomodo;
	public JTextField textFieldCantidadAcomodo;
	public JTextField textFieldLugar;
	public JButton btnProveedores;
	public JButton btnProductos;
	public JButton btnLimpiar;
	public JButton btnAcomodar;
	public JComboBox comboBoxProveedor;
	public JComboBox comboBoxSucursal;
	public JComboBox comboBoxProducto;
	public JComboBox comboBoxSucursalAcomodar;
	
	private Proveedor proveedor;
	private Producto producto;
	
	private PanelProveedor panelProveedor;
	private PanelProducto panelProducto;
	
	private ConsultasProveedor consultasProveedor;
	private ConsultasProducto consultasProducto;
	private ConsultasSucursal consultasSucursal;
	
	private CtrlProveedor ctrlProveedor;
	private CtrlProducto ctrlProducto;
	
	public ArrayList<DetalleSurtido> listaRenglones;
	public ArrayList<Acomodo> listaAcomodar;

	/**
	 * Create the panel.
	 */
	public PanelSurtido() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(962, 54, 140, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1100, 56, 2, 195);
		add(separator_1);
		
		// ETIQUETAS Y TEXTFIELD
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTotal.setBounds(300, 109, 210, 14);
		add(lblTotal);
		
		txtFieldTotal = new JTextField();
		txtFieldTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosDecimales(arg0);
			}
		});
		txtFieldTotal.setText("$");
		txtFieldTotal.setEnabled(true);
		txtFieldTotal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldTotal.setBorder(null);
		txtFieldTotal.setBackground(new Color(176, 196, 222));
		txtFieldTotal.setBounds(363, 106, 132, 20);
		add(txtFieldTotal);
		txtFieldTotal.setColumns(10);
		
		// TABLA
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Barras", "Nombre", "Modelo", "Marca", "Cantidad", "Caducidad", "Precio", "Lugar Acomodo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(255, 255, 204));
		table.setRowHeight(25);
		table.setBounds(35, 419, 1070, 222);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(35, 419, 1070, 222);					// AGREGADO
		add(js);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(903, 70, 172, 34);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(903, 113, 172, 34);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(903, 156, 172, 34);
		add(btnModificar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(903, 198, 172, 34);
		add(btnLimpiar);
		
		// ETIQUETAS DISEÑO
		JLabel lblDatos = new JLabel("Datos resurtido");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(26, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblIngresarDatos = new JLabel("Datos generales");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(50, 35, 139, 23);
		add(lblIngresarDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(876, 42, 99, 14);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(11, 385, 60, 14);
		add(lblDetalle);
		
		// SEPARADORES
		/*JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(528, 355, 459, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(782, 52, 2, 213);
		add(separator_1);*/
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(50, 313, 2, 44);
		add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(175, 51, 350, 2);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(50, 142, 475, 2);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(50, 56, 2, 86);
		add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(524, 53, 2, 89);
		add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(143, 23, 683, 2);
		add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(26, 32, 2, 342);
		add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(26, 372, 800, 2);
		add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(824, 23, 2, 349);
		add(separator_11);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(70, 397, 1061, 2);
		add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(11, 409, 2, 250);
		add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1129, 399, 2, 260);
		add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(10, 657, 1120, 2);
		add(separator_15);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setBackground(new Color(0, 128, 128));
		separator_17.setBounds(140, 298, 385, 2);
		add(separator_17);
		
		JLabel lblNewLabel_3 = new JLabel("Acceder a");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(50, 286, 193, 14);
		add(lblNewLabel_3);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setBackground(new Color(0, 128, 128));
		separator_18.setBounds(50, 355, 475, 2);
		add(separator_18);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(0, 128, 128));
		separator_2_1.setBounds(524, 298, 2, 59);
		add(separator_2_1);
		
		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel.setBounds(62, 77, 46, 14);
		add(lblNewLabel);
		
		textFieldFecha = new JTextField();
		textFieldFecha.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(textFieldFecha.getText().equals("dd/mm/aaaa")) {
					textFieldFecha.setText("");
					textFieldFecha.setForeground(Color.BLACK);
				}
			}
		});
		textFieldFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		textFieldFecha.setBorder(null);
		textFieldFecha.setBackground(new Color(176, 196, 222));
		textFieldFecha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldFecha.setForeground(Color.GRAY);
		textFieldFecha.setText("dd/mm/aaaa");
		textFieldFecha.setBounds(140, 72, 132, 20);
		add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Proveedor");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(62, 106, 210, 14);
		add(lblNewLabel_1);
		
		comboBoxProveedor = new JComboBox();
		comboBoxProveedor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxProveedor.setBounds(140, 101, 132, 22);
		add(comboBoxProveedor);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(300, 73, 214, 14);
		add(lblNewLabel_2);
		
		comboBoxSucursal = new JComboBox();
		comboBoxSucursal.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxSucursal.setBounds(363, 69, 132, 22);
		add(comboBoxSucursal);
		
		JLabel lblNewLabel_4 = new JLabel("Detalle");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(50, 159, 58, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Producto");
		lblNewLabel_5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(62, 197, 563, 14);
		add(lblNewLabel_5);
		
		comboBoxProducto = new JComboBox();
		comboBoxProducto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxProducto.setBounds(140, 193, 132, 22);
		add(comboBoxProducto);
		
		JLabel lblNewLabel_6 = new JLabel("Cantidad");
		lblNewLabel_6.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(300, 197, 325, 14);
		add(lblNewLabel_6);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
			}
		});
		textFieldCantidad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldCantidad.setBorder(null);
		textFieldCantidad.setBackground(new Color(176, 196, 222));
		textFieldCantidad.setBounds(378, 194, 132, 20);
		add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Caducidad");
		lblNewLabel_7.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(62, 230, 563, 14);
		add(lblNewLabel_7);
		
		textFieldCaducidad = new JTextField();
		textFieldCaducidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(textFieldCaducidad.getText().equals("dd/mm/aaaa")) {
					textFieldCaducidad.setText("");
					textFieldCaducidad.setForeground(Color.BLACK);
				}
			}
		});
		textFieldCaducidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		textFieldCaducidad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldCaducidad.setForeground(Color.GRAY);
		textFieldCaducidad.setText("dd/mm/aaaa");
		textFieldCaducidad.setBorder(null);
		textFieldCaducidad.setBackground(new Color(176, 196, 222));
		textFieldCaducidad.setBounds(140, 227, 132, 20);
		add(textFieldCaducidad);
		textFieldCaducidad.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Precio");
		lblNewLabel_8.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(300, 230, 325, 14);
		add(lblNewLabel_8);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setText("$");
		textFieldPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosDecimales(arg0);
			}
		});
		textFieldPrecio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldPrecio.setBorder(null);
		textFieldPrecio.setBackground(new Color(176, 196, 222));
		textFieldPrecio.setBounds(378, 227, 132, 20);
		add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		JSeparator separator_16_1 = new JSeparator();
		separator_16_1.setOrientation(SwingConstants.VERTICAL);
		separator_16_1.setBackground(new Color(0, 128, 128));
		separator_16_1.setBounds(50, 185, 2, 78);
		add(separator_16_1);
		
		JSeparator separator_16_1_1 = new JSeparator();
		separator_16_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_16_1_1.setBackground(new Color(0, 128, 128));
		separator_16_1_1.setBounds(524, 171, 2, 94);
		add(separator_16_1_1);
		
		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setBackground(new Color(0, 128, 128));
		separator_4_1.setBounds(140, 171, 385, 2);
		add(separator_4_1);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBackground(new Color(0, 128, 128));
		separator_3_1.setBounds(50, 263, 475, 2);
		add(separator_3_1);
		
		JLabel lblNewLabel_9 = new JLabel("Acomodar");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(549, 39, 72, 14);
		add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Fecha");
		lblNewLabel_10.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(568, 74, 57, 14);
		add(lblNewLabel_10);
		
		textFieldFechaAcomodo = new JTextField();
		textFieldFechaAcomodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(textFieldFechaAcomodo.getText().equals("dd/mm/aaaa")) {
					textFieldFechaAcomodo.setText("");
					textFieldFechaAcomodo.setForeground(Color.BLACK);
				}
			}
		});
		textFieldFechaAcomodo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		textFieldFechaAcomodo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldFechaAcomodo.setForeground(Color.GRAY);
		textFieldFechaAcomodo.setText("dd/mm/aaaa");
		textFieldFechaAcomodo.setBorder(null);
		textFieldFechaAcomodo.setBackground(new Color(176, 196, 222));
		textFieldFechaAcomodo.setBounds(646, 71, 132, 20);
		add(textFieldFechaAcomodo);
		textFieldFechaAcomodo.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Sucursal");
		lblNewLabel_11.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(568, 172, 57, 14);
		add(lblNewLabel_11);
		
		comboBoxSucursalAcomodar = new JComboBox();
		comboBoxSucursalAcomodar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxSucursalAcomodar.setBounds(646, 168, 132, 22);
		add(comboBoxSucursalAcomodar);
		
		JLabel lblNewLabel_12 = new JLabel("Cantidad");
		lblNewLabel_12.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(568, 105, 57, 14);
		add(lblNewLabel_12);
		
		textFieldCantidadAcomodo = new JTextField();
		textFieldCantidadAcomodo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
			}
		});
		textFieldCantidadAcomodo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldCantidadAcomodo.setBorder(null);
		textFieldCantidadAcomodo.setBackground(new Color(176, 196, 222));
		textFieldCantidadAcomodo.setBounds(646, 103, 132, 20);
		add(textFieldCantidadAcomodo);
		textFieldCantidadAcomodo.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Lugar");
		lblNewLabel_13.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(568, 138, 57, 14);
		add(lblNewLabel_13);
		
		textFieldLugar = new JTextField();
		textFieldLugar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		textFieldLugar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldLugar.setBorder(null);
		textFieldLugar.setBackground(new Color(176, 196, 222));
		textFieldLugar.setBounds(646, 135, 132, 20);
		add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		JSeparator separator_4_1_1 = new JSeparator();
		separator_4_1_1.setBackground(new Color(0, 128, 128));
		separator_4_1_1.setBounds(625, 54, 172, 2);
		add(separator_4_1_1);
		
		JSeparator separator_3_1_1 = new JSeparator();
		separator_3_1_1.setBackground(new Color(0, 128, 128));
		separator_3_1_1.setBounds(549, 216, 248, 2);
		add(separator_3_1_1);
		
		JSeparator separator_16_1_2 = new JSeparator();
		separator_16_1_2.setOrientation(SwingConstants.VERTICAL);
		separator_16_1_2.setBackground(new Color(0, 128, 128));
		separator_16_1_2.setBounds(549, 56, 2, 162);
		add(separator_16_1_2);
		
		JSeparator separator_16_1_1_1 = new JSeparator();
		separator_16_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_16_1_1_1.setBackground(new Color(0, 128, 128));
		separator_16_1_1_1.setBounds(795, 54, 2, 164);
		add(separator_16_1_1_1);
		
		JSeparator separator_16_2 = new JSeparator();
		separator_16_2.setOrientation(SwingConstants.VERTICAL);
		separator_16_2.setBackground(new Color(0, 128, 128));
		separator_16_2.setBounds(876, 56, 2, 195);
		add(separator_16_2);
		
		JSeparator separator_17_1 = new JSeparator();
		separator_17_1.setBackground(new Color(0, 128, 128));
		separator_17_1.setBounds(876, 250, 226, 2);
		add(separator_17_1);
		
		btnProveedores = new JButton("Proveedores");
		btnProveedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				proveedor = new Proveedor();
				consultasProveedor = new ConsultasProveedor();
				panelProveedor = new PanelProveedor();
				ctrlProveedor = new CtrlProveedor(proveedor, consultasProveedor, panelProveedor);
				consultasProveedor.llenarTabla(panelProveedor.table);
				panelProveedor.setSize(1200, 670);
				panelProveedor.setLocation(0, 0);
				PanelSurtido.this.removeAll();
				PanelSurtido.this.add(panelProveedor, BorderLayout.CENTER);
				PanelSurtido.this.revalidate();
				PanelSurtido.this.repaint();
			}
		});
		btnProveedores.setBorder(null);
		btnProveedores.setBounds(140, 311, 132, 31);
		btnProveedores.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoProveedores.png")));
		btnProveedores.setForeground(new Color(255, 255, 255));
		btnProveedores.setBackground(new Color(0, 0, 51));
		add(btnProveedores);
		
		btnProductos = new JButton("Productos");
		btnProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				producto = new Producto();
				consultasProducto = new ConsultasProducto();
				consultasSucursal = new ConsultasSucursal();
				panelProducto = new PanelProducto();
				ctrlProducto = new CtrlProducto(producto, consultasProducto, consultasSucursal, panelProducto);
				ctrlProducto.llenarComboSucursal();
				consultasProducto.llenarTabla(panelProducto.table);
				panelProducto.setSize(1200, 670);
				panelProducto.setLocation(0, 0);
				PanelSurtido.this.removeAll();
				PanelSurtido.this.add(panelProducto, BorderLayout.CENTER);
				PanelSurtido.this.revalidate();
				PanelSurtido.this.repaint();
			}
		});
		btnProductos.setBorder(null);
		btnProductos.setBounds(300, 311, 132, 31);
		btnProductos.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoProductos.png")));
		btnProductos.setForeground(new Color(255, 255, 255));
		btnProductos.setBackground(new Color(0, 0, 51));
		add(btnProductos);
		
		btnAcomodar = new JButton(getIcon("/recursos/iconoRegistrar.png", 22, 22));
		listaAcomodar = new ArrayList<>();
		listaRenglones = new ArrayList<>();
		btnAcomodar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelSurtido.this.textFieldFecha.setEditable(false);
				PanelSurtido.this.txtFieldTotal.setEditable(false);
				PanelSurtido.this.comboBoxSucursal.setEnabled(false);
				PanelSurtido.this.comboBoxProveedor.setEnabled(false);
				
				DetalleSurtido detalleSurtido = new DetalleSurtido();
				detalleSurtido.setCantidad(Integer.parseInt(PanelSurtido.this.textFieldCantidad.getText()));
				detalleSurtido.setBaja(Integer.parseInt(PanelSurtido.this.textFieldCantidad.getText()));
				Date fechaCaducidadUtil = stringToUtil(PanelSurtido.this.textFieldCaducidad.getText());
				detalleSurtido.setCaducidad(utilToSQL(fechaCaducidadUtil));
				detalleSurtido.setPrecio(Float.parseFloat(PanelSurtido.this.textFieldPrecio.getText().replace("$", "")));
				int codigoBarras = ((Producto) PanelSurtido.this.comboBoxProducto.getItemAt(PanelSurtido.this.comboBoxProducto.getSelectedIndex())).getCodigoBarras();
				detalleSurtido.setCodigoBarras(codigoBarras);
				listaRenglones.add(detalleSurtido);
				
				Acomodo acomodar = new Acomodo();
				Date fechaUtil = stringToUtil(PanelSurtido.this.textFieldFechaAcomodo.getText());
				acomodar.setFecha(utilToSQL(fechaUtil));
				acomodar.setCantidad(Integer.parseInt(PanelSurtido.this.textFieldCantidadAcomodo.getText()));
				acomodar.setLugar(PanelSurtido.this.textFieldLugar.getText());
				int claveSucursal = ((Sucursal) PanelSurtido.this.comboBoxSucursalAcomodar.getItemAt(PanelSurtido.this.comboBoxSucursalAcomodar.getSelectedIndex())).getClave();
				acomodar.setClaveSucursal(claveSucursal);
				listaAcomodar.add(acomodar);
				
				PanelSurtido.this.textFieldCaducidad.setText(null);
				PanelSurtido.this.textFieldCantidad.setText(null);
				PanelSurtido.this.textFieldPrecio.setText(null);
				PanelSurtido.this.textFieldFechaAcomodo.setText(null);
				PanelSurtido.this.textFieldCantidadAcomodo.setText(null);
				PanelSurtido.this.textFieldLugar.setText(null);
			}
		});
		btnAcomodar.setBounds(106, 156, 25, 25);
		//btnAcomodar.setIcon(new Icon(getIcon("/recursos/iconoRegistrar.png", 22, 22)));
		btnAcomodar.setBorder(null);
		btnAcomodar.setForeground(new Color(255, 255, 255));
		btnAcomodar.setBackground(new Color(0, 0, 51));
		add(btnAcomodar);

	}
	
	public ArrayList<String> listaAcomodar(){
		ArrayList<String> list = new ArrayList<>();
		list.add(PanelSurtido.this.textFieldFechaAcomodo.getText());
		list.add(PanelSurtido.this.textFieldCantidadAcomodo.getText());
		list.add(PanelSurtido.this.textFieldLugar.getText());
		int claveSucursal = ((Sucursal) PanelSurtido.this.comboBoxSucursalAcomodar.getItemAt(PanelSurtido.this.comboBoxSucursalAcomodar.getSelectedIndex())).getClave();
		list.add(String.valueOf(claveSucursal));
		return list;
	}
	
	public Icon getIcon(String ruta, int width, int heigth){
		Icon miIcono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, heigth, Image.SCALE_AREA_AVERAGING));
		return miIcono;
	}
}

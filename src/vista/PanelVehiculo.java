package vista;

import static utileria.Validaciones.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import consultas.ConsultasContrato;
import consultas.ConsultasEnvio;
import consultas.ConsultasVehiculo;
import controlador.CtrlEnvio;
import modelo.Contrato;
import modelo.Enviar;
import modelo.ListaCarga;
import modelo.Persona;
import modelo.StatusEnvio;
import modelo.Vehiculo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelVehiculo extends JPanel {
	public JTextField txtCanPuertas;
	public JTextField txtAnio;
	public JTextField txtModelo;
	public JTextField txtMarca;
	public JTextField txtColor;
	public JTextField txtPrecioCompra;
	public JTextField txtTipoVeh;
	
	public JTable table;
	public JTable tableSTATUS;
	
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnChecador;
	public JButton btnRegresar;
	public JButton btnLimpiar;

	
	public JComboBox comboBoxTienda;
	public JTextField txtPlacas;
	public JTextField txtEstadoVehiculo;
	
	public PanelEnvio panelEnvio;
	private Enviar enviar;
	private ListaCarga listaCarga;
	private StatusEnvio statusEnvio;
	private ConsultasEnvio consultasEnvio;
	private ConsultasVehiculo consultasVehiculo;
	private ConsultasContrato consultasContrato;	
	private CtrlEnvio ctrlEnvio;
	private Vehiculo vehiculo;
	private Persona persona;
	private Contrato contrato;
	
	/**
	 * Create the panel.
	 */
	public PanelVehiculo() {

		setBorder(new EmptyBorder(1, 1, 5, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setBounds(0, 0, 1180, 700);

		txtCanPuertas = new JTextField();
		txtCanPuertas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e3) {
				soloNumeros(e3);
			}
		});
		txtCanPuertas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtCanPuertas.setBackground(new Color(176, 196, 222));
		txtCanPuertas.setBorder(null);
		txtCanPuertas.setBounds(282, 220, 112, 20);
		add(txtCanPuertas);
		txtCanPuertas.setColumns(10);
		
		JLabel lblCantPuertas = new JLabel("Cantidad de puertas");
		lblCantPuertas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCantPuertas.setBounds(156, 222, 120, 14);
		add(lblCantPuertas);

		JLabel lblCiudad = new JLabel("A\u00F1o del vehiculo");
		lblCiudad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCiudad.setBounds(156, 138, 99, 14);
		add(lblCiudad);

		JLabel lblPrecioCompra = new JLabel("Precio de compra");
		lblPrecioCompra.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblPrecioCompra.setBounds(432, 180, 99, 14);
		add(lblPrecioCompra);

		JLabel lblTipoVehi = new JLabel("Tipo de vehiculo");
		lblTipoVehi.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTipoVehi.setBounds(156, 180, 91, 14);
		add(lblTipoVehi);

		txtAnio = new JTextField();
		txtAnio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtAnio.setBackground(new Color(176, 196, 222));
		txtAnio.setBorder(null);
		txtAnio.setBounds(282, 136, 112, 20);
		add(txtAnio);
		txtAnio.setColumns(10);
		
		
		
		// TABLA
		table = new JTable();
		table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(255, 255, 204));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				//"No. Serie", "Modelo", "Marca", "Color", "Cant. Puertas", "Año vehiculo", "Costo", "Tipo vehiculo","Folio Sucursal","Numero Placas"
					"No. Serie", "Modelo", "Tipo", "Numero de placas", "Fecha placas","Status", "No.Status"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false //, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(35, 419, 1070, 222);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(57, 447, 990, 203);					// AGREGADO
		add(js);
		
		// COMBOBOX


		
		// BOTONES
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(860, 90, 250, 31);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(860, 132, 250, 31);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(860, 178, 250, 31);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(null);
		btnEliminar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoEliminar.png")));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 0, 51));
		btnEliminar.setBounds(860, 220, 250, 31);
		add(btnEliminar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(860, 262, 250, 31);
		add(btnLimpiar);
		
		// ETIQUETAS DISEÑO
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(34, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblIngresarDatos = new JLabel("Dato del vehiculo");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(65, 36, 139, 23);
		add(lblIngresarDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOperaciones.setBounds(935, 65, 130, 14);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle vehiculo");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 403, 130, 14);
		add(lblDetalle);
		
		// SEPARADORES
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(836, 365, 298, 2);
		add(separator);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(836, 54, 2, 313);
		add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(182, 54, 590, 2);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(57, 345, 715, 2);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(57, 51, 2, 296);
		add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(770, 54, 2, 293);
		add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(101, 23, 715, 2);
		add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(22, 32, 2, 348);
		add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(24, 378, 792, 2);
		add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(814, 23, 2, 357);
		add(separator_11);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(137, 415, 950, 2);
		add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(22, 418, 2, 257);
		add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1085, 415, 2, 260);
		add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(22, 675, 1065, 2);
		add(separator_15);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblModelo.setBounds(432, 92, 99, 14);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e5) {
				numerosLetras(e5);
			}
		});
		txtModelo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtModelo.setBackground(new Color(176, 196, 222));
		txtModelo.setBorder(null);
		txtModelo.setBounds(562, 90, 112, 20);
		add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblMarca.setBounds(156, 268, 99, 14);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e4) {
				soloLetras(e4);
			}
		});
		txtMarca.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtMarca.setBackground(new Color(176, 196, 222));
		txtMarca.setBorder(null);
		txtMarca.setBounds(282, 266, 112, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblColor.setBounds(432, 138, 46, 14);
		add(lblColor);
		
		txtColor = new JTextField();
		txtColor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e6) {
				soloLetras(e6);
			}
		});
		txtColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtColor.setBackground(new Color(176, 196, 222));
		txtColor.setBorder(null);
		txtColor.setBounds(562, 136, 112, 20);
		add(txtColor);
		txtColor.setColumns(10);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e7) {
				numerosDecimales(e7);
			}
		});
		txtPrecioCompra.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtPrecioCompra.setBackground(new Color(176, 196, 222));
		txtPrecioCompra.setBorder(null);
		txtPrecioCompra.setBounds(562, 178, 112, 20);
		add(txtPrecioCompra);
		txtPrecioCompra.setColumns(10);
		
		txtTipoVeh = new JTextField();
		txtTipoVeh.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e2) {
				soloLetras(e2);
			}
		});
		txtTipoVeh.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtTipoVeh.setBackground(new Color(176, 196, 222));
		txtTipoVeh.setBorder(null);
		txtTipoVeh.setBounds(282, 178, 112, 20);
		add(txtTipoVeh);
		txtTipoVeh.setColumns(10);
		

		
		JLabel lblTienda = new JLabel("Tienda");
		lblTienda.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTienda.setBounds(432, 268, 46, 14);
		add(lblTienda);
		
		comboBoxTienda = new JComboBox();
		comboBoxTienda.setBackground(new Color(176, 196, 222));
		comboBoxTienda.setName("Tienda");
		comboBoxTienda.setBounds(562, 266, 112, 20);
		add(comboBoxTienda);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setBackground(new Color(0, 128, 128));
		separator_18.setBounds(836, 51, 298, 2);
		add(separator_18);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(0, 128, 128));
		separator_2_1.setBounds(1132, 54, 2, 313);
		add(separator_2_1);
		
		txtPlacas = new JTextField();
		txtPlacas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				numerosLetras(e);
			}
		});
		txtPlacas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtPlacas.setColumns(10);
		txtPlacas.setBorder(null);
		txtPlacas.setBackground(new Color(176, 196, 222));
		txtPlacas.setBounds(282, 90, 112, 20);
		add(txtPlacas);
		
		JLabel lblPlacas = new JLabel("Numero de Placas");
		lblPlacas.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblPlacas.setBounds(156, 92, 112, 14);
		add(lblPlacas);
		
		JLabel lblEstadoVehiculo = new JLabel("Status vehiculo");
		lblEstadoVehiculo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEstadoVehiculo.setBounds(432, 222, 89, 14);
		add(lblEstadoVehiculo);
		
		txtEstadoVehiculo = new JTextField();
		txtEstadoVehiculo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e8) {
				soloLetras(e8);
			}
		});
		txtEstadoVehiculo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtEstadoVehiculo.setColumns(10);
		txtEstadoVehiculo.setBorder(null);
		txtEstadoVehiculo.setBackground(new Color(176, 196, 222));
		txtEstadoVehiculo.setBounds(562, 220, 112, 20);
		add(txtEstadoVehiculo);
		
		btnRegresar = new JButton("Atr\u00E1s");
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				panelEnvio = new PanelEnvio();
				enviar = new Enviar();
				listaCarga = new ListaCarga();
				statusEnvio = new StatusEnvio();
				consultasEnvio = new ConsultasEnvio();
				consultasVehiculo = new ConsultasVehiculo();
				consultasContrato = new ConsultasContrato();
				
				ctrlEnvio = new CtrlEnvio(enviar, listaCarga, statusEnvio, consultasEnvio, panelEnvio, consultasVehiculo, consultasContrato, vehiculo, contrato, persona);
				ctrlEnvio.llenarComboVehiculo();
				System.out.println("Antes de llamar metodo");
				ctrlEnvio.llenarComboEmpleado();
				System.out.println("Despues de llamar metodo");
				ctrlEnvio.llenarComboEnvio();
				consultasEnvio.llenarTabla(panelEnvio.table);

				//consultasEnvio.llenarTablaEstado(panelEnvio.table2);
				panelEnvio.setSize(1300, 700);
				panelEnvio.setLocation(0, 0);
				PanelVehiculo.this.removeAll();
				PanelVehiculo.this.add(panelEnvio, BorderLayout.CENTER);
				PanelVehiculo.this.revalidate();
				PanelVehiculo.this.repaint();
			}
		});
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoVolver.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(860, 304, 250, 34);
		add(btnRegresar);


	

	}
}
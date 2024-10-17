package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
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
import javax.swing.table.DefaultTableModel;

import consultas.ConsultasDatoEnvio;
import consultas.ConsultasUbicacion;
import consultas.ConsultasVenta;
import controlador.CtrlDatoEnvio;

import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import modelo.DatoEnvio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelVenta extends JPanel {
	public JTextField txtFieldFechaVenta;
	public JTextField txtFieldDescuento;
	public JTextField txtFieldFinGarantia;
	public JTextField txtFieldPrecioVenta;
	public JTextField txtFieldCantidad;
	
	public JComboBox comboBoxCodigoProducto;
	public JComboBox comboBoxContrato;
	public JComboBox comboBoxCliente;
	public JComboBox comboBoxFormaPago;
	public JComboBox comboBoxTienda;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnDatoEnvio;
	public JButton btnLimpiar;
	
	public JTable table;
	public JTextField txtFieldNumeroTarjeta;
	public JTextField txtFieldInstitutcionBancaria;
	public JTextField txtFieldFolioAutorizacion;
	
	private ConsultasDatoEnvio consultasDatoEnvio;
	private PanelDatoEnvio panelDatoEnvio;
	private CtrlDatoEnvio ctrlDatoEnvio;
	public JTextField txtFieldFolio;
	
	public DatoEnvio datoEnvio;
	
	private ConsultasUbicacion consultasUbicacion;
	private ConsultasVenta consultasVenta;
	private PanelVenta panelVenta;

	/**
	 * Create the panel.
	 */
	public PanelVenta() {
		setBorder(new EmptyBorder(1, 1, 5, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setBounds(0, 0, 1150, 670);

		JLabel lblCodigoProducto = new JLabel("Producto");
		lblCodigoProducto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCodigoProducto.setBounds(302, 114, 60, 14);
		add(lblCodigoProducto);

		JLabel lblContrato = new JLabel("Contrato");
		lblContrato.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblContrato.setBounds(302, 170, 78, 14);
		add(lblContrato);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblDescuento.setBounds(54, 268, 66, 14);
		add(lblDescuento);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCantidad.setBounds(54, 314, 66, 14);
		add(lblCantidad);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCliente.setBounds(302, 290, 66, 14);
		add(lblCliente);

		txtFieldDescuento = new JTextField();
		txtFieldDescuento.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldDescuento.setBackground(new Color(176, 196, 222));
		txtFieldDescuento.setBorder(null);
		txtFieldDescuento.setBounds(152, 266, 112, 20);
		add(txtFieldDescuento);
		txtFieldDescuento.setColumns(10);
		
		// TABLA
		table = new JTable();
		table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(255, 255, 204));
		table.setRowHeight(25);
		/*JTableHeader header = table.getTableHeader();
		add(header);
		table.getTableHeader();*/
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Folio", "Fecha de Venta", "Fin de Garantia", "Precio", "Descuento", "Cantidad", "Forma de Pago", "Producto"
			, "Contrato", "Tienda", "Cliente"}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(35, 419, 1070, 222);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(35, 420, 1070, 222);					// AGREGADO
		add(js);
		
		// COMBOBOX
		comboBoxContrato = new JComboBox();
		comboBoxContrato.setBackground(new Color(176, 196, 222));
//		comboBoxCodigoProducto.setName("Contrato");
		comboBoxContrato.setBounds(387, 168, 112, 20);
		add(comboBoxContrato);

		comboBoxCliente = new JComboBox();
		comboBoxCliente.setBackground(new Color(176, 196, 222));
//		comboBoxCodigoProducto.setName("Cliente");
		comboBoxCliente.setBounds(387, 288, 112, 20);
		add(comboBoxCliente);

		comboBoxCodigoProducto = new JComboBox();
		comboBoxCodigoProducto.setBackground(new Color(176, 196, 222));
//		comboBoxCodigoProducto.setName("Producto");
		comboBoxCodigoProducto.setBounds(387, 108, 112, 20);
		add(comboBoxCodigoProducto);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(945, 91, 119, 31);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(945, 157, 119, 31);
		add(btnBuscar);
		
		// ETIQUETAS DISE�O
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(10, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblIngresarDatos = new JLabel("Datos de Venta");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(35, 35, 139, 23);
		add(lblIngresarDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(910, 50, 80, 20);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(11, 385, 60, 14);
		add(lblDetalle);
		
		// SEPARADORES
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(528, 355, 310, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(836, 54, 2, 213);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(528, 296, 2, 61);
		add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(528, 265, 310, 2);
		add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(160, 51, 350, 2);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(34, 355, 475, 2);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(35, 56, 2, 299);
		add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(509, 53, 2, 304);
		add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(58, 23, 811, 2);
		add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(10, 30, 2, 342);
		add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(9, 372, 860, 2);
		add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(868, 23, 2, 349);
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
		
		JLabel lblFechaVenta = new JLabel("Fecha De Venta");
		lblFechaVenta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechaVenta.setBounds(54, 130, 99, 14);
		add(lblFechaVenta);
		
		txtFieldFechaVenta = new JTextField();
		txtFieldFechaVenta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaVenta.setBackground(new Color(176, 196, 222));
		txtFieldFechaVenta.setBorder(null);
		txtFieldFechaVenta.setBounds(152, 128, 112, 20);
		add(txtFieldFechaVenta);
		txtFieldFechaVenta.setColumns(10);
		
		JLabel lblFinGarantia = new JLabel("Fin de Garantia");
		lblFinGarantia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFinGarantia.setBounds(54, 176, 99, 14);
		add(lblFinGarantia);
		
		txtFieldFinGarantia = new JTextField();
		txtFieldFinGarantia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFinGarantia.setBackground(new Color(176, 196, 222));
		txtFieldFinGarantia.setBorder(null);
		txtFieldFinGarantia.setBounds(152, 174, 112, 20);
		add(txtFieldFinGarantia);
		txtFieldFinGarantia.setColumns(10);
		
		JLabel lblPrecioVenta = new JLabel("Precio Venta");
		lblPrecioVenta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblPrecioVenta.setBounds(54, 222, 78, 14);
		add(lblPrecioVenta);
		
		txtFieldPrecioVenta = new JTextField();
		txtFieldPrecioVenta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldPrecioVenta.setBackground(new Color(176, 196, 222));
		txtFieldPrecioVenta.setBorder(null);
		txtFieldPrecioVenta.setBounds(152, 220, 112, 20);
		add(txtFieldPrecioVenta);
		txtFieldPrecioVenta.setColumns(10);
		
		txtFieldCantidad = new JTextField();
		txtFieldCantidad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCantidad.setBackground(new Color(176, 196, 222));
		txtFieldCantidad.setBorder(null);
		txtFieldCantidad.setBounds(152, 308, 112, 20);
		add(txtFieldCantidad);
		txtFieldCantidad.setColumns(10);
		
		JLabel lblDatosLaborales = new JLabel("Datos De Pago");
		lblDatosLaborales.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosLaborales.setBounds(529, 39, 113, 14);
		add(lblDatosLaborales);
		
		JLabel lblFormaPago = new JLabel("Forma de Pago");
		lblFormaPago.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFormaPago.setBounds(544, 83, 98, 14);
		add(lblFormaPago);
		
		JLabel lblNumeroTarjeta = new JLabel("N\u00FAmero de Tarjeta");
		lblNumeroTarjeta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNumeroTarjeta.setBounds(544, 127, 107, 14);
		add(lblNumeroTarjeta);
		
		txtFieldNumeroTarjeta = new JTextField();
		txtFieldNumeroTarjeta.setBorder(null);
		txtFieldNumeroTarjeta.setBackground(new Color(176, 196, 222));
		txtFieldNumeroTarjeta.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumeroTarjeta.setBounds(671, 125, 112, 20);
		add(txtFieldNumeroTarjeta);
		txtFieldNumeroTarjeta.setColumns(10);
		
		JLabel lblInstitutcionBancaria = new JLabel("Instituci\u00F3n Bancaria");
		lblInstitutcionBancaria.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblInstitutcionBancaria.setBounds(544, 174, 112, 14);
		add(lblInstitutcionBancaria);
		
		txtFieldInstitutcionBancaria = new JTextField();
		txtFieldInstitutcionBancaria.setBorder(null);
		txtFieldInstitutcionBancaria.setBackground(new Color(176, 196, 222));
		txtFieldInstitutcionBancaria.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldInstitutcionBancaria.setBounds(671, 172, 112, 20);
		add(txtFieldInstitutcionBancaria);
		txtFieldInstitutcionBancaria.setColumns(10);
		
		JLabel lblFolioAutorizacion  = new JLabel("Folio Autorizaci\u00F3n ");
		lblFolioAutorizacion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFolioAutorizacion.setBounds(544, 216, 112, 14);
		add(lblFolioAutorizacion);
		
		txtFieldFolioAutorizacion = new JTextField();
		txtFieldFolioAutorizacion.setBorder(null);
		txtFieldFolioAutorizacion.setBackground(new Color(176, 196, 222));
		txtFieldFolioAutorizacion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFolioAutorizacion.setBounds(671, 214, 112, 20);
		add(txtFieldFolioAutorizacion);
		txtFieldFolioAutorizacion.setColumns(10);
		
		comboBoxFormaPago = new JComboBox();
		comboBoxFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Efectivo", "Tarjeta Credito/Debito", "Cheque", "Transferencia Bancaria"}));
		comboBoxFormaPago.setBackground(new Color(176, 196, 222));
		comboBoxFormaPago.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxFormaPago.setBounds(671, 80, 112, 22);
		add(comboBoxFormaPago);
		
		btnDatoEnvio = new JButton("Envio");
		btnDatoEnvio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				datoEnvio = new DatoEnvio();
				consultasDatoEnvio = new ConsultasDatoEnvio();
				panelVenta = new PanelVenta();
				panelDatoEnvio= new PanelDatoEnvio();
				consultasDatoEnvio = new ConsultasDatoEnvio();
				consultasUbicacion = new ConsultasUbicacion();
				consultasVenta = new ConsultasVenta();
				consultasDatoEnvio.llenarTablaDatoEnvio(panelDatoEnvio.table);
				ctrlDatoEnvio = new CtrlDatoEnvio(datoEnvio, consultasDatoEnvio, panelDatoEnvio, consultasUbicacion, consultasVenta, panelVenta);
				ctrlDatoEnvio.llenarComboColonia();
				ctrlDatoEnvio.llenarComboTicket();
				panelDatoEnvio.setSize(1200, 670);
//				panelDatoEnvio.setLocation(0, 0);
				PanelVenta.this.removeAll();
				PanelVenta.this.add(panelDatoEnvio, BorderLayout.CENTER);
				PanelVenta.this.revalidate();
				PanelVenta.this.repaint();
			}
		});
		btnDatoEnvio.setBorder(null);
		btnDatoEnvio.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoChecador.png")));
		btnDatoEnvio.setForeground(Color.WHITE);
		btnDatoEnvio.setBackground(new Color(0, 0, 51));
		btnDatoEnvio.setBounds(622, 307, 126, 31);
		add(btnDatoEnvio);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setBackground(new Color(0, 128, 128));
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setBounds(526, 57, 2, 209);
		add(separator_16);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setBackground(new Color(0, 128, 128));
		separator_17.setBounds(641, 54, 197, 2);
		add(separator_17);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(531, 293, 46, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nuevos Datos de Envio");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(538, 282, 193, 14);
		add(lblNewLabel_3);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setBackground(new Color(0, 128, 128));
		separator_18.setBounds(689, 293, 149, 2);
		add(separator_18);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(0, 128, 128));
		separator_2_1.setBounds(836, 296, 2, 61);
		add(separator_2_1);
		
		JLabel lblTienda = new JLabel("Tienda");
		lblTienda.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTienda.setBounds(302, 230, 78, 14);
		add(lblTienda);
		
		comboBoxTienda = new JComboBox();
		comboBoxTienda.setBackground(new Color(176, 196, 222));
		comboBoxTienda.setName("Tienda");
		comboBoxTienda.setBounds(387, 228, 112, 20);
		add(comboBoxTienda);
		
		JSeparator separator_19 = new JSeparator();
		separator_19.setOrientation(SwingConstants.VERTICAL);
		separator_19.setForeground(new Color(211, 211, 211));
		separator_19.setBackground(new Color(0, 128, 128));
		separator_19.setBounds(907, 70, 2, 197);
		add(separator_19);
		
		JSeparator separator_20 = new JSeparator();
		separator_20.setBackground(new Color(0, 128, 128));
		separator_20.setBounds(910, 264, 188, 2);
		add(separator_20);
		
		JSeparator separator_21 = new JSeparator();
		separator_21.setOrientation(SwingConstants.VERTICAL);
		separator_21.setBackground(new Color(0, 128, 128));
		separator_21.setBounds(1095, 68, 2, 197);
		add(separator_21);
		
		JSeparator separator_22 = new JSeparator();
		separator_22.setBackground(new Color(0, 128, 128));
		separator_22.setBounds(998, 67, 99, 2);
		add(separator_22);
		
		JLabel lblFolioTicket = new JLabel("Folio Ticket");
		lblFolioTicket.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFolioTicket.setBounds(54, 84, 78, 14);
		add(lblFolioTicket);
		
		txtFieldFolio = new JTextField();
		txtFieldFolio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFolio.setColumns(10);
		txtFieldFolio.setBorder(null);
		txtFieldFolio.setBackground(new Color(176, 196, 222));
		txtFieldFolio.setBounds(152, 81, 112, 20);
		add(txtFieldFolio);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBorder(null);
//		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(945, 214, 119, 31);
		add(btnLimpiar);

	}
	
	public Icon getIcon(String ruta, int width, int heigth){
		Icon miIcono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, heigth, 0));
		return miIcono;
	}
}
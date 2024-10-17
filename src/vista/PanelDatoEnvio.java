package vista;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import modelo.Contrato;
import modelo.Estado;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Persona;
import modelo.Ticket;
import modelo.DatoEnvio;
import consultas.ConsultasCliente;
import consultas.ConsultasContrato;
import consultas.ConsultasDatoEnvio;
import consultas.ConsultasProducto;
import consultas.ConsultasSucursal;
import consultas.ConsultasUbicacion;
import consultas.ConsultasVenta;
import controlador.CtrlContrato;
import controlador.CtrlVenta;
import controlador.CtrlDatoEnvio;

import java.util.List;

public class PanelDatoEnvio extends JPanel {

	public JTextField txtFieldCalleEnvio;
	public JTextField txtFieldNumeroEnv;
	public JTextField txtFieldEntreCallesEnv;
	public JTextField txtField;
	
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnRegresar;
	public JButton btnLimpiar;
	
	public JTable table;
	
	public JComboBox comboBoxOrientacion;
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxCodigo;
	public JComboBox comboBoxCiudad;
	public JComboBox comboBoxFolioTic;
	public JComboBox comboBoxColonia; 
	
	
	public PanelVenta panelVenta;
	public PanelDatoEnvio panelDatoEnvio;
	public Ticket venta;
	public DatoEnvio datoEnvio;
	
	public CtrlDatoEnvio ctrlDatoEnvio; 
	public CtrlVenta ctrlVenta;
	
	private ConsultasUbicacion consultasUbicacion;
	private ConsultasVenta consultasVenta;
	private ConsultasDatoEnvio consultasDatoEnvio;
	private ConsultasContrato consultasContrato;
	private ConsultasCliente consultasCliente;
	private ConsultasSucursal consultasSucursal;
	private ConsultasProducto consultasProducto;
//	public JTextField txtFieldColonia;
//	public JTextField txtFieldTicket;
	

	public PanelDatoEnvio() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);
		//setMaximumSize(new Dimension(700, 600));

		JLabel lblDatosDeSucursal = new JLabel("Datos");
		lblDatosDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDeSucursal.setBounds(30, 16, 139, 14);
		add(lblDatosDeSucursal);

		JLabel lblCalleEnvio = new JLabel("Calle");
		lblCalleEnvio.setBounds(117, 131, 40, 14);
		add(lblCalleEnvio);

		txtFieldCalleEnvio = new JTextField();
		txtFieldCalleEnvio.setBorder(null);
		txtFieldCalleEnvio.setBackground(new Color(176, 196, 222));
		txtFieldCalleEnvio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCalleEnvio.setBounds(226, 128, 145, 20);
		add(txtFieldCalleEnvio);
		txtFieldCalleEnvio.setColumns(10);

		JLabel lblNumeroEnvio = new JLabel("N\u00FAmero");
		lblNumeroEnvio.setBounds(117, 177, 46, 14);
		add(lblNumeroEnvio);

		txtFieldNumeroEnv = new JTextField();
		txtFieldNumeroEnv.setBorder(null);
		txtFieldNumeroEnv.setBackground(new Color(176, 196, 222));
		txtFieldNumeroEnv.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumeroEnv.setBounds(226, 174, 145, 20);
		add(txtFieldNumeroEnv);
		txtFieldNumeroEnv.setColumns(10);

		txtFieldEntreCallesEnv = new JTextField();
		txtFieldEntreCallesEnv.setBorder(null);
		txtFieldEntreCallesEnv.setBackground(new Color(176, 196, 222));
		txtFieldEntreCallesEnv.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEntreCallesEnv.setBounds(226, 223, 145, 20);
		add(txtFieldEntreCallesEnv);
		txtFieldEntreCallesEnv.setColumns(10);

		JLabel lblEntreCallesEnvio = new JLabel("Entre calles");
		lblEntreCallesEnvio.setBounds(117, 226, 66, 14);
		add(lblEntreCallesEnvio);

		JLabel lblOrientacionEnv = new JLabel("Orientaci\u00F3n");
		lblOrientacionEnv.setBounds(468, 112, 78, 14);
		add(lblOrientacionEnv);

		JLabel lblColoniaEnv = new JLabel("Colonia");
		lblColoniaEnv.setBounds(468, 160, 46, 14);
		add(lblColoniaEnv);

		JLabel lblFolio_tic = new JLabel("Folio Ticket");
		lblFolio_tic.setBounds(468, 208, 66, 14);
		add(lblFolio_tic);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setBorder(null);
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(910, 68, 156, 34);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(910, 105, 156, 34);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setBorder(null);
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(910, 142, 156, 34);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoEliminar.png")));
		btnEliminar.setBorder(null);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 0, 51));
		btnEliminar.setBounds(910, 178, 156, 34);
		add(btnEliminar);

		table = new JTable();
		table.setSelectionBackground(new Color(204, 204, 255));
		table.setRowHeight(25);
		/*JTableHeader header = table.getTableHeader();
		add(header);
		table.getTableHeader();*/
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Cliente", "Calle", "N\u00FAmero", "Entre calles", "Orientaci\u00F3n", "Colonia"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(54, 380, 1024, 258);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(54, 380, 1024, 258);					// AGREGADO
		add(js);

		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(874, 41, 99, 14);
		add(lblOperaciones);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(962, 54, 140, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1100, 56, 2, 209);
		add(separator_1);

		JLabel lblIngresarDatos = new JLabel("Datos Envio");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(54, 37, 106, 23);
		add(lblIngresarDatos);

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 342, 60, 14);
		add(lblDetalle);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(874, 58, 2, 209);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(876, 264, 225, 2);
		add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(138, 54, 670, 2);
		add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(54, 283, 751, 2);
		add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(54, 58, 2, 227);
		add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(806, 56, 2, 229);
		add(separator_7);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(78, 28, 757, 2);
		add(separator_8);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(30, 35, 2, 280);
		add(separator_9);

		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(31, 313, 804, 2);
		add(separator_10);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(834, 29, 2, 286);
		add(separator_11);

		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(89, 354, 1013, 2);
		add(separator_12);

		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(30, 365, 2, 291);
		add(separator_13);

		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1100, 355, 2, 302);
		add(separator_14);

		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(30, 655, 1072, 4);
		add(separator_15);

		comboBoxOrientacion = new JComboBox();
		comboBoxOrientacion.setBackground(new Color(176, 196, 222));
		comboBoxOrientacion.setForeground(Color.BLACK);
		comboBoxOrientacion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxOrientacion.setModel(new DefaultComboBoxModel(new String[] {"Norte", "Sur", "Este", "Oeste"}));
		comboBoxOrientacion.setBounds(589, 107, 145, 22);
		add(comboBoxOrientacion);
		
		btnRegresar = new JButton("Atr\u00E1s");
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				venta = new Ticket();
				consultasUbicacion = new ConsultasUbicacion();
				consultasVenta = new ConsultasVenta();
				panelVenta = new PanelVenta();
				consultasContrato = new ConsultasContrato();
				consultasCliente = new ConsultasCliente();
				consultasSucursal= new ConsultasSucursal();
				consultasProducto = new ConsultasProducto();
				ctrlVenta = new CtrlVenta(venta, consultasVenta, panelVenta, consultasContrato, consultasCliente, consultasSucursal, consultasUbicacion, consultasProducto);
				ctrlVenta.llenarComboSucursal();
				ctrlVenta.llenarComboContrato();
				ctrlVenta.llenarComboCliente();
				ctrlVenta.llenarComboProducto();
				consultasVenta.llenarTablaVenta(panelVenta.table);
				panelVenta.setSize(1300, 700);
				panelVenta.setLocation(0, 0);
				PanelDatoEnvio.this.removeAll();
				PanelDatoEnvio.this.add(panelVenta, BorderLayout.CENTER);
				PanelDatoEnvio.this.revalidate();
				PanelDatoEnvio.this.repaint();

			}
		});
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoVolver.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(911, 286, 156, 31);
		add(btnRegresar);
		
		comboBoxFolioTic = new JComboBox();
		comboBoxFolioTic.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxFolioTic.setBackground(new Color(176, 196, 222));
		comboBoxFolioTic.setBounds(589, 205, 145, 20);
		add(comboBoxFolioTic);

		comboBoxColonia = new JComboBox();
		comboBoxColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxColonia.setBackground(new Color(176, 196, 222));
		comboBoxColonia.setBounds(589, 156, 139, 20);
		add(comboBoxColonia);
		
		txtField = new JTextField();
		txtField.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtField.setColumns(10);
		txtField.setBorder(null);
		txtField.setBackground(new Color(176, 196, 222));
		txtField.setBounds(226, 85, 145, 20);
		add(txtField);
		
		JLabel label = new JLabel("Id DatoEnv");
		label.setBounds(117, 88, 60, 14);
		add(label);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBorder(null);
//		btnLimpiar.setIcon(new ImageIcon(PanelCliente.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(910, 217, 156, 31);
		add(btnLimpiar);


	}
}

package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static utileria.Validaciones.*;

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

import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import consultas.ConsultasSucursal;
import consultas.ConsultasVehiculo;
import controlador.CtrlProducto;
import controlador.CtrlProveedor;
import controlador.CtrlVehiculo;
import modelo.Placas;
import modelo.Producto;
import modelo.Proveedor;
import modelo.Status;
import modelo.Vehiculo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class PanelEnvio extends JPanel {
	
	public JButton btnRegistrar;
	public JButton btnBuscar;
	
	public JTable table;
	private Vehiculo vehiculo;
	private Placas placas;
	private Status status;
	private ConsultasVehiculo consultasVehiculo;
	private CtrlVehiculo ctrlVehiculo;
	private PanelVehiculo panelVehiculo;
	private JButton btnVehiculos;
	
	public JComboBox comboBoxFolioContrato;
	public JComboBox comboBoxDatoEnvio;
	public JComboBox comboBoxNsvehiculo;
	public JComboBox comboBoxStatusEnvio;
	public JTextField txtFechaStatus;
	public JTextField textFieldClaveEnviar;
	
	public PanelEnvio() {
		
		setBorder(new EmptyBorder(1, 1, 5, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setBounds(0, 0, 1170, 700);
		

		//TABLA
		table = new JTable();
		table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(255, 255, 204));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				
					"Numero de lista carga","Clave de envio","Numero de serie vehiculo", "Folio de contrato", "Clave de datos envio", "Fecha de status","Status del envio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(500, 30, 1070, 222);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js2 = new JScrollPane(table);			// AGREGADO
		js2.setBounds(52, 424, 920, 205);					// AGREGADO
		add(js2);

		
		// BOTONES
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(528, 131, 220, 34);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(528, 176, 220, 34);
		add(btnBuscar);
	
		
		// ETIQUETAS DISEÑO
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(32, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOperaciones.setBounds(528, 106, 145, 14);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(20, 377, 60, 14);
		add(lblDetalle);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(106, 23, 344, 2);
		add(separator_8);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(20, 356, 430, 2);
		add(separator_10);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(70, 388, 937, 2);
		add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(20, 424, 2, 235);
		add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1005, 388, 2, 271);
		add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(20, 657, 987, 2);
		add(separator_15);
		
		btnVehiculos = new JButton("Vehiculos");
		btnVehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				vehiculo = new Vehiculo();
				placas = new Placas();
				status = new Status();
				consultasVehiculo = new ConsultasVehiculo();
				panelVehiculo = new PanelVehiculo();
				ctrlVehiculo = new CtrlVehiculo(vehiculo,placas,status,consultasVehiculo, panelVehiculo);
				consultasVehiculo.llenarTabla(panelVehiculo.table);
				//consultasVehiculo.llenarTablaStatus(panelVehiculo.tableSTATUS);
				ctrlVehiculo.llenarComboSucursal();
				panelVehiculo.setSize(1200, 670);
				panelVehiculo.setLocation(0, 0);
				PanelEnvio.this.removeAll();
				PanelEnvio.this.add(panelVehiculo, BorderLayout.CENTER);
				PanelEnvio.this.revalidate();
				PanelEnvio.this.repaint();
			}
		});
		btnVehiculos.setBorder(null);
		btnVehiculos.setBounds(528, 221, 220, 31);
		btnVehiculos.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoEnvios.png")));
		btnVehiculos.setForeground(new Color(255, 255, 255));
		btnVehiculos.setBackground(new Color(0, 0, 51));
		add(btnVehiculos);
		
		JLabel lblNsEnviar = new JLabel("Numero de serie");
		lblNsEnviar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNsEnviar.setBounds(68, 142, 113, 14);
		add(lblNsEnviar);
		
		JLabel lblContrato = new JLabel("Folio contrato");
		lblContrato.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblContrato.setBounds(68, 185, 113, 14);
		add(lblContrato);
		
		JLabel lblDatosEnviar = new JLabel("Descripcion Envio");
		lblDatosEnviar.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosEnviar.setBounds(43, 55, 113, 14);
		add(lblDatosEnviar);
		
		JSeparator separator_10_1_1 = new JSeparator();
		separator_10_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_10_1_1.setBackground(new Color(0, 128, 128));
		separator_10_1_1.setBounds(448, 23, 2, 335);
		add(separator_10_1_1);
		
		JLabel lblFolioDelEnvio = new JLabel("Dato de envio");
		lblFolioDelEnvio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFolioDelEnvio.setBounds(68, 227, 91, 14);
		add(lblFolioDelEnvio);
		
		JLabel lblStatusDelEnvio = new JLabel("Status del envio");
		lblStatusDelEnvio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblStatusDelEnvio.setBounds(68, 313, 113, 14);
		add(lblStatusDelEnvio);
		
		JLabel lblFechastatus = new JLabel("Fecha de status");
		lblFechastatus.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechastatus.setBounds(68, 268, 91, 14);
		add(lblFechastatus);
		
		txtFechaStatus = new JTextField();
		txtFechaStatus.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFechaStatus.setColumns(10);
		txtFechaStatus.setBorder(null);
		txtFechaStatus.setBackground(new Color(176, 196, 222));
		txtFechaStatus.setBounds(212, 268, 160, 20);
		add(txtFechaStatus);
		
		JSeparator separator_10_2 = new JSeparator();
		separator_10_2.setBackground(new Color(0, 128, 128));
		separator_10_2.setBounds(495, 280, 281, 2);
		add(separator_10_2);
		
		JSeparator separator_11_1 = new JSeparator();
		separator_11_1.setOrientation(SwingConstants.VERTICAL);
		separator_11_1.setBackground(new Color(0, 128, 128));
		separator_11_1.setBounds(776, 88, 2, 194);
		add(separator_11_1);
		
		comboBoxNsvehiculo = new JComboBox();
		comboBoxNsvehiculo.setBackground(new Color(176, 196, 222));
		comboBoxNsvehiculo.setName("Tienda");
		comboBoxNsvehiculo.setBounds(212, 142, 160, 20);
		add(comboBoxNsvehiculo);
		
		comboBoxFolioContrato = new JComboBox();
		comboBoxFolioContrato.setName("Tienda");
		comboBoxFolioContrato.setBackground(new Color(176, 196, 222));
		comboBoxFolioContrato.setBounds(212, 185, 160, 20);
		add(comboBoxFolioContrato);
		
		comboBoxDatoEnvio = new JComboBox();
		comboBoxDatoEnvio.setName("Tienda");
		comboBoxDatoEnvio.setBackground(new Color(176, 196, 222));
		comboBoxDatoEnvio.setBounds(212, 227, 160, 20);
		add(comboBoxDatoEnvio);
		
		JLabel lblCveEnviar = new JLabel("Numero de status envio");
		lblCveEnviar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCveEnviar.setBounds(68, 100, 133, 14);
		add(lblCveEnviar);
		
		textFieldClaveEnviar = new JTextField();
		textFieldClaveEnviar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		textFieldClaveEnviar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldClaveEnviar.setColumns(10);
		textFieldClaveEnviar.setBorder(null);
		textFieldClaveEnviar.setBackground(new Color(176, 196, 222));
		textFieldClaveEnviar.setBounds(212, 100, 160, 20);
		add(textFieldClaveEnviar);
		
		JSeparator separator_11_1_1 = new JSeparator();
		separator_11_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_11_1_1.setBackground(new Color(0, 128, 128));
		separator_11_1_1.setBounds(495, 88, 2, 194);
		add(separator_11_1_1);
		
		JSeparator separator_10_2_1 = new JSeparator();
		separator_10_2_1.setBackground(new Color(0, 128, 128));
		separator_10_2_1.setBounds(496, 88, 280, 2);
		add(separator_10_2_1);
		
		JSeparator separator_10_1_1_1 = new JSeparator();
		separator_10_1_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_10_1_1_1.setBackground(new Color(0, 128, 128));
		separator_10_1_1_1.setBounds(20, 23, 2, 335);
		add(separator_10_1_1_1);
		
		comboBoxStatusEnvio = new JComboBox();
		comboBoxStatusEnvio.setModel(new DefaultComboBoxModel(new String[] {"En espera", "En camino", "Rechazado"}));
		comboBoxStatusEnvio.setBounds(212, 312, 160, 22);
		add(comboBoxStatusEnvio);
		

		
		

	}
}

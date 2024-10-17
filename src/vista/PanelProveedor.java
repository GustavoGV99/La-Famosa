package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import static utileria.Validaciones.*;
import consultas.ConsultasContrato;
import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import consultas.ConsultasSurtido;
import consultas.ConsultasSucursal;
import consultas.ConsultasUbicacion;
import controlador.CtrlContrato;
import controlador.CtrlSurtido;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Contrato;
import modelo.Estado;
import modelo.Persona;
import modelo.Surtido;

import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelProveedor extends JPanel {
	public JTextField txtFieldCalle;
	public JTextField txtFieldNumero;
	public JTextField txtFieldTelefono;
	public JTextField txtFieldCorreo;
	public JTextField textFieldRazonSocial;
	
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnLimpiar;
	public JButton btnRegresar;
	
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxCiudad;
	public JComboBox comboBoxCP;
	public JComboBox comboBoxColonia;
	public JComboBox comboBoxOrientacion;
	
	public JTable table;
	
	private ConsultasUbicacion consultasUbicacion;
	private PanelSurtido panelSurtido;

	private Surtido surtido;
	private ConsultasSucursal consultasSucursal;
	private ConsultasSurtido consultasSurtido;
	private ConsultasProducto consultasProducto;
	private ConsultasProveedor consultasProveedor;
	private CtrlSurtido ctrlSurtido;
	/**
	 * Create the panel.
	 */
	public PanelProveedor() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);

		JLabel lblDatosDeSucursal = new JLabel("Datos");
		lblDatosDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDeSucursal.setBounds(30, 16, 139, 14);
		add(lblDatosDeSucursal);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCalle.setBounds(480, 84, 46, 14);
		add(lblCalle);

		txtFieldCalle = new JTextField();
		txtFieldCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		txtFieldCalle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtFieldCalle.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo obligatorio");
					txtFieldCalle.requestFocus();
				} else {
					txtFieldNumero.requestFocus();
				}
			}
		});
		txtFieldCalle.setBorder(null);
		txtFieldCalle.setBackground(new Color(176, 196, 222));
		txtFieldCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCalle.setBounds(589, 81, 172, 20);
		add(txtFieldCalle);
		txtFieldCalle.setColumns(10);

		JLabel lblNewLabel = new JLabel("N\u00FAmero");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel.setBounds(480, 116, 46, 14);
		add(lblNewLabel);

		txtFieldNumero = new JTextField();
		txtFieldNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
			}
		});
		txtFieldNumero.setBorder(null);
		txtFieldNumero.setBackground(new Color(176, 196, 222));
		txtFieldNumero.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumero.setBounds(589, 113, 172, 20);
		add(txtFieldNumero);
		txtFieldNumero.setColumns(10);

		JLabel lblOrientacin = new JLabel("Orientaci\u00F3n");
		lblOrientacin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblOrientacin.setBounds(480, 149, 78, 14);
		add(lblOrientacin);

		txtFieldTelefono = new JTextField();
		txtFieldTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
				if(txtFieldTelefono.getText().length() >= 10) {
					arg0.consume();
				}
			}
		});
		txtFieldTelefono.setBorder(null);
		txtFieldTelefono.setBackground(new Color(176, 196, 222));
		txtFieldTelefono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldTelefono.setBounds(589, 177, 172, 20);
		add(txtFieldTelefono);
		txtFieldTelefono.setColumns(10);

		consultasUbicacion = new ConsultasUbicacion();
		comboBoxEstado = new JComboBox();
		comboBoxEstado.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxEstado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Estado estado = (Estado) comboBoxEstado.getSelectedItem();
				int id = estado.getId();
				
				consultasUbicacion.listarCiudades(comboBoxCiudad, id);
			}
		});
		comboBoxEstado.setBackground(new Color(176, 196, 222));
		comboBoxEstado.setBounds(226, 112, 172, 20);
		consultasUbicacion.listarEstados(comboBoxEstado);
		add(comboBoxEstado);

		comboBoxCiudad = new JComboBox();
		comboBoxCiudad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxCiudad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Ciudad ciudad = (Ciudad) comboBoxCiudad.getSelectedItem();
				int id = ciudad.getId();
				
				consultasUbicacion.listarCodigo(comboBoxCP, id);
			}
		});
		comboBoxCiudad.setBackground(new Color(176, 196, 222));
		comboBoxCiudad.setBounds(226, 143, 172, 20);
		add(comboBoxCiudad);

		comboBoxCP = new JComboBox();
		comboBoxCP.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxCP.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				CodigoPostal cp = (CodigoPostal) comboBoxCP.getSelectedItem();
				int codigo = cp.getCodigo();
				
				consultasUbicacion.listarColonia(comboBoxColonia, codigo);
			}
		});
		comboBoxCP.setBackground(new Color(176, 196, 222));
		comboBoxCP.setBounds(226, 174, 172, 20);
		add(comboBoxCP);

		comboBoxColonia = new JComboBox();
		comboBoxColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxColonia.setBackground(new Color(176, 196, 222));
		comboBoxColonia.setBounds(226, 209, 172, 20);
		add(comboBoxColonia);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEstado.setBounds(117, 115, 46, 14);
		add(lblEstado);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCiudad.setBounds(117, 146, 46, 14);
		add(lblCiudad);

		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo postal");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(117, 177, 99, 14);
		add(lblNewLabel_1);

		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblColonia.setBounds(117, 212, 46, 14);
		add(lblColonia);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTelfono.setBounds(480, 184, 66, 14);
		add(lblTelfono);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.setBorder(null);
		txtFieldCorreo.setBackground(new Color(176, 196, 222));
		txtFieldCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCorreo.setBounds(589, 208, 172, 20);
		add(txtFieldCorreo);
		txtFieldCorreo.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCorreo.setBounds(480, 215, 46, 14);
		add(lblCorreo);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setBorder(null);
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(903, 41, 172, 34);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(903, 82, 172, 34);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setBorder(null);
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(903, 124, 172, 34);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoEliminar.png")));
		btnEliminar.setBorder(null);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 0, 51));
		btnEliminar.setBounds(903, 206, 172, 34);
		add(btnEliminar);
		
		btnRegresar = new JButton("Atr\u00E1s");
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				surtido = new Surtido();
				consultasSurtido = new ConsultasSurtido();
				consultasProducto = new ConsultasProducto();
				consultasProveedor = new ConsultasProveedor();
				consultasSucursal = new ConsultasSucursal();
				panelSurtido = new PanelSurtido();
				ctrlSurtido = new CtrlSurtido(surtido, consultasSurtido, consultasProducto, consultasProveedor, consultasSucursal, panelSurtido);
				ctrlSurtido.llenarComboSucursal();
				ctrlSurtido.llenarComboProducto();
				ctrlSurtido.llenarComboProveedor();
				panelSurtido.setSize(1300, 700);
				panelSurtido.setLocation(0, 0);
				PanelProveedor.this.removeAll();
				PanelProveedor.this.add(panelSurtido, BorderLayout.CENTER);
				PanelProveedor.this.revalidate();
				PanelProveedor.this.repaint();
			}
		});
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoVolver.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(903, 246, 172, 34);
		add(btnRegresar);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Razon Social", "Tel\u00E9fono", "Estado", "Ciudad", "Codigo Postal", "Colonia", "Calle", "Orientaci\u00F3n", "N\u00FAmero"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, true, true, true, true, true, true, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.setSelectionBackground(new Color(204, 204, 255));
		table.setRowHeight(25);
		table.setBounds(54, 380, 1024, 258);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(54, 351, 1024, 284);					// AGREGADO
		add(js);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(903, 165, 172, 34);
		add(btnLimpiar);

		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(877, 16, 99, 14);
		add(lblOperaciones);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(965, 29, 140, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1103, 31, 2, 260);
		add(separator_1);

		JLabel lblIngresarDatos = new JLabel("Proveedor");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(54, 37, 106, 23);
		add(lblIngresarDatos);

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 313, 60, 14);
		add(lblDetalle);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(877, 33, 2, 258);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(877, 289, 228, 2);
		add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(127, 54, 681, 2);
		add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(57, 259, 751, 2);
		add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(54, 58, 2, 202);
		add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(806, 56, 2, 205);
		add(separator_7);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(78, 28, 757, 2);
		add(separator_8);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(30, 35, 2, 249);
		add(separator_9);

		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(31, 283, 804, 2);
		add(separator_10);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(834, 29, 2, 256);
		add(separator_11);

		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(89, 325, 1013, 2);
		add(separator_12);

		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(30, 336, 2, 321);
		add(separator_13);

		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1100, 326, 2, 331);
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
		comboBoxOrientacion.setBounds(589, 145, 172, 22);
		add(comboBoxOrientacion);
		
		JLabel lblNewLabel_2 = new JLabel("Razon social");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(117, 84, 99, 14);
		add(lblNewLabel_2);
		
		textFieldRazonSocial = new JTextField();
		textFieldRazonSocial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		textFieldRazonSocial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldRazonSocial.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Campo obligatorio");
					textFieldRazonSocial.requestFocus();
				} else {
					txtFieldCalle.requestFocus();
				}
			}
		});
		textFieldRazonSocial.setBorder(null);
		textFieldRazonSocial.setBackground(new Color(176, 196, 222));
		textFieldRazonSocial.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldRazonSocial.setBounds(226, 81, 172, 20);
		add(textFieldRazonSocial);
		textFieldRazonSocial.setColumns(10);
	}
	
}

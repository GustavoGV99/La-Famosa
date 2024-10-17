package vista;

import java.awt.Dimension;
import java.awt.event.ItemEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import modelo.Estado;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JSeparator;

import static utileria.Validaciones.*;
import consultas.ConsultasUbicacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelSucursal extends JPanel {
	public JTextField txtFieldApertura;
	public JTextField txtFieldCalle;
	public JTextField txtFieldNumero;
	public JTextField txtFieldEntreCalles;
	public JTextField txtFieldTelefono;
	public JTextField txtFieldCorreo;
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxCiudad;
	public JComboBox comboBoxCP;
	public JComboBox comboBoxColonia;
	public JTextField txtFieldCveSuc;
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JTable table;
	public JButton btnLimpiar;
	private JButton btnNuevaUbicacin;
	public JComboBox comboBoxOrientacion;
	
	private ConsultasUbicacion consultasUbicacion;

	/**
	 * Create the panel.
	 */
	public PanelSucursal() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);

		JLabel lblDatosDeSucursal = new JLabel("Datos");
		lblDatosDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDeSucursal.setBounds(30, 16, 139, 14);
		add(lblDatosDeSucursal);

		JLabel lblFechaDeApertura = new JLabel("Fecha de apertura");
		lblFechaDeApertura.setBounds(117, 115, 106, 14);
		add(lblFechaDeApertura);

		txtFieldApertura = new JTextField();
		txtFieldApertura.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(txtFieldApertura.getText().equals("dd/mm/aaaa")) {
					txtFieldApertura.setText("");
					txtFieldApertura.setForeground(Color.BLACK);
				}
			}
		});
		txtFieldApertura.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		txtFieldApertura.setBorder(null);
		txtFieldApertura.setBackground(new Color(176, 196, 222));
		txtFieldApertura.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldApertura.setForeground(Color.GRAY);
		txtFieldApertura.setText("dd/mm/aaaa");
		txtFieldApertura.setBounds(226, 112, 145, 20);
		add(txtFieldApertura);
		txtFieldApertura.setColumns(10);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(480, 84, 46, 14);
		add(lblCalle);

		txtFieldCalle = new JTextField();
		txtFieldCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		txtFieldCalle.setBorder(null);
		txtFieldCalle.setBackground(new Color(176, 196, 222));
		txtFieldCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCalle.setBounds(589, 81, 145, 20);
		add(txtFieldCalle);
		txtFieldCalle.setColumns(10);

		JLabel lblNewLabel = new JLabel("N\u00FAmero");
		lblNewLabel.setBounds(480, 112, 46, 14);
		add(lblNewLabel);

		txtFieldNumero = new JTextField();
		txtFieldNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
				if(txtFieldNumero.getText().length() > 4) {
					arg0.consume();
				}
			}
		});
		txtFieldNumero.setBorder(null);
		txtFieldNumero.setBackground(new Color(176, 196, 222));
		txtFieldNumero.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumero.setBounds(589, 109, 145, 20);
		add(txtFieldNumero);
		txtFieldNumero.setColumns(10);

		txtFieldEntreCalles = new JTextField();
		txtFieldEntreCalles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		txtFieldEntreCalles.setBorder(null);
		txtFieldEntreCalles.setBackground(new Color(176, 196, 222));
		txtFieldEntreCalles.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEntreCalles.setBounds(589, 140, 145, 20);
		add(txtFieldEntreCalles);
		txtFieldEntreCalles.setColumns(10);

		JLabel lblEntreCalles = new JLabel("Entre calles");
		lblEntreCalles.setBounds(480, 143, 78, 14);
		add(lblEntreCalles);

		JLabel lblOrientacin = new JLabel("Orientaci\u00F3n");
		lblOrientacin.setBounds(480, 174, 78, 14);
		add(lblOrientacin);

		txtFieldTelefono = new JTextField();
		txtFieldTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String telefono = txtFieldTelefono.getText();
				soloNumeros(arg0);
				if(telefono.length() >= 10) {
					arg0.consume();
				}
			}
		});
		txtFieldTelefono.setBorder(null);
		txtFieldTelefono.setBackground(new Color(176, 196, 222));
		txtFieldTelefono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldTelefono.setBounds(589, 202, 145, 20);
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
		comboBoxEstado.setBounds(226, 143, 145, 20);
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
		comboBoxCiudad.setBounds(226, 174, 145, 20);
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
		comboBoxCP.setBounds(226, 205, 145, 20);
		add(comboBoxCP);

		comboBoxColonia = new JComboBox();
		comboBoxColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxColonia.setBackground(new Color(176, 196, 222));
		comboBoxColonia.setBounds(226, 240, 145, 20);
		add(comboBoxColonia);

		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(117, 146, 46, 14);
		add(lblEstado);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(117, 177, 46, 14);
		add(lblCiudad);

		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo postal");
		lblNewLabel_1.setBounds(117, 208, 99, 14);
		add(lblNewLabel_1);

		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setBounds(117, 243, 46, 14);
		add(lblColonia);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setBounds(480, 209, 66, 14);
		add(lblTelfono);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.setBorder(null);
		txtFieldCorreo.setBackground(new Color(176, 196, 222));
		txtFieldCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCorreo.setBounds(589, 233, 145, 20);
		add(txtFieldCorreo);
		txtFieldCorreo.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(480, 240, 46, 14);
		add(lblCorreo);

		JLabel lblClave = new JLabel("Clave");
		lblClave.setBounds(117, 87, 46, 14);
		add(lblClave);

		txtFieldCveSuc = new JTextField();
		txtFieldCveSuc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		txtFieldCveSuc.setBorder(null);
		txtFieldCveSuc.setBackground(new Color(176, 196, 222));
		txtFieldCveSuc.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCveSuc.setBounds(226, 84, 145, 20);
		add(txtFieldCveSuc);
		txtFieldCveSuc.setColumns(10);

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
		btnEliminar.setBounds(910, 216, 156, 34);
		add(btnEliminar);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Apertura", "Tel\u00E9fono", "Ciudad", "C\u00F3digo Postal", "Colonia", "Calle", "N\u00FAmero", "Orientaci\u00F3n"
			}
		));
		table.setSelectionBackground(new Color(204, 204, 255));
		table.setRowHeight(25);
		table.setBounds(54, 380, 1024, 258);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(54, 380, 1024, 258);					// AGREGADO
		add(js);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(910, 179, 156, 34);
		add(btnLimpiar);

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

		JLabel lblIngresarDatos = new JLabel("Sucursal");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(54, 37, 106, 23);
		add(lblIngresarDatos);

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 342, 60, 14);
		add(lblDetalle);

		btnNuevaUbicacin = new JButton("Nueva ubicaci\u00F3n");
		btnNuevaUbicacin.setBorder(null);
		btnNuevaUbicacin.setVisible(false);
		btnNuevaUbicacin.setForeground(new Color(255, 255, 255));
		btnNuevaUbicacin.setBackground(new Color(0, 0, 51));
		btnNuevaUbicacin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Ubicacion ubicacion = new Ubicacion();
				ubicacion.setVisible(true);
			}
		});
		btnNuevaUbicacin.setBounds(910, 273, 130, 23);
		add(btnNuevaUbicacin);

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
		separator_4.setBounds(127, 54, 681, 2);
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
		comboBoxOrientacion.setBounds(589, 170, 145, 22);
		add(comboBoxOrientacion);

	}
}

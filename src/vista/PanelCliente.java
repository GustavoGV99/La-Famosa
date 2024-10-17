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
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Estado;
import consultas.ConsultasUbicacion;

import utileria.Validaciones;

import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelCliente extends JPanel {
	public JTable table;
	
	public JTextField txtFieldCalle;
	public JTextField txtFieldNumero;
	public JTextField txtFieldEntreCalles;
	public JTextField txtFieldTelefono;
	public JTextField txtFieldCorreo;
	public JTextField txtFieldPaterno;
	public JTextField txtFieldMaterno;
	public JTextField txtFieldNombre;
	public JTextField txtFieldFechaNac;
	public JTextField txtFieldEdoCivil;
	public JTextField txtFieldFechaCli;
	public JTextField txtFieldCve_Cliente;
	public JTextField txtFieldIdPer;
	
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxCiudad;
	public JComboBox comboBoxCP;
	public JComboBox comboBoxColonia;
	public JComboBox comboBoxSexo;
	public JComboBox comboBoxOrientacion;
	
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnLimpiar;
	
	private ConsultasUbicacion consultasUbicacion;

	public PanelCliente() {
		setBorder(new EmptyBorder(1, 1, 5, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setBounds(0, 0, 1150, 670);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCalle.setBounds(75, 327, 46, 14);
		add(lblCalle);

		txtFieldCalle = new JTextField();
		txtFieldCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		txtFieldCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCalle.setBackground(new Color(176, 196, 222));
		txtFieldCalle.setBorder(null);
		txtFieldCalle.setBounds(190, 324, 112, 20);
		add(txtFieldCalle);
		txtFieldCalle.setColumns(10);

		JLabel lblNewLabel = new JLabel("N\u00FAmero");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel.setBounds(350, 77, 46, 14);
		add(lblNewLabel);

		txtFieldNumero = new JTextField();
		txtFieldNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.numerosLetras(arg0);
			}
		});
		txtFieldNumero.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumero.setBackground(new Color(176, 196, 222));
		txtFieldNumero.setBorder(null);
		txtFieldNumero.setBounds(440, 75, 112, 20);
		add(txtFieldNumero);
		txtFieldNumero.setColumns(10);

		txtFieldEntreCalles = new JTextField();
		txtFieldEntreCalles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.numerosLetras(arg0);
			}
		});
		txtFieldEntreCalles.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEntreCalles.setBackground(new Color(176, 196, 222));
		txtFieldEntreCalles.setBorder(null);
		txtFieldEntreCalles.setBounds(440, 108, 112, 20);
		add(txtFieldEntreCalles);
		txtFieldEntreCalles.setColumns(10);

		JLabel lblEntreCalles = new JLabel("Entre calles");
		lblEntreCalles.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEntreCalles.setBounds(350, 110, 78, 14);
		add(lblEntreCalles);

		JLabel lblOrientacin = new JLabel("Orientaci\u00F3n");
		lblOrientacin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblOrientacin.setBounds(350, 142, 78, 14);
		add(lblOrientacin);

		txtFieldTelefono = new JTextField();
		txtFieldTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.soloNumeros(arg0);
			}
		});
		txtFieldTelefono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldTelefono.setBackground(new Color(176, 196, 222));
		txtFieldTelefono.setBorder(null);
		txtFieldTelefono.setBounds(440, 170, 112, 20);
		add(txtFieldTelefono);
		txtFieldTelefono.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEstado.setBounds(75, 203, 46, 14);
		add(lblEstado);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCiudad.setBounds(75, 234, 46, 14);
		add(lblCiudad);

		JLabel lblCP = new JLabel("C\u00F3digo postal");
		lblCP.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCP.setBounds(75, 265, 99, 14);
		add(lblCP);

		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblColonia.setBounds(75, 296, 46, 14);
		add(lblColonia);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTelfono.setBounds(350, 174, 66, 14);
		add(lblTelfono);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
//				Validaciones.validarCorreo(correo);
			}
		});
		txtFieldCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCorreo.setBackground(new Color(176, 196, 222));
		txtFieldCorreo.setBorder(null);
		txtFieldCorreo.setBounds(440, 201, 112, 20);
		add(txtFieldCorreo);
		txtFieldCorreo.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCorreo.setBounds(350, 205, 46, 14);
		add(lblCorreo);
		
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
				"Folio", "Paterno", "Materno", "Nombre", "Tel\u00E9fono", "Id Cliente", "Fecha Registro"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(35, 419, 1070, 222);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(35, 419, 1070, 222);					// AGREGADO
		add(js);
		
		// COMBOBOX
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
		comboBoxEstado.setBounds(190, 199, 112, 20);
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
		comboBoxCiudad.setBounds(190, 229, 112, 20);
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
		comboBoxCP.setBounds(190, 259, 112, 20);
		add(comboBoxCP);

		comboBoxColonia = new JComboBox();
		comboBoxColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxColonia.setBackground(new Color(176, 196, 222));
		comboBoxColonia.setBounds(190, 289, 112, 20);
		add(comboBoxColonia);
		
		// BOTONES
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBorder(null);
		btnRegistrar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(1012, 35, 119, 31);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(1012, 77, 119, 31);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBorder(null);
		btnModificar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(1012, 119, 119, 31);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(null);
		btnEliminar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoEliminar.png")));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 0, 51));
		btnEliminar.setBounds(1012, 161, 119, 31);
		add(btnEliminar);
		
		// ETIQUETAS DISEÑO
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(10, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblIngresarDatos = new JLabel("Datos personales");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(50, 34, 139, 23);
		add(lblIngresarDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(1022, 11, 99, 14);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(11, 385, 60, 14);
		add(lblDetalle);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(962, 90, 2, 166);
		add(separator_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(623, 254, 341, 2);
		add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(174, 52, 408, 2);
		add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(45, 352, 537, 2);
		add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(45, 54, 2, 299);
		add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(580, 51, 2, 304);
		add(separator_7);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(58, 23, 942, 2);
		add(separator_8);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(10, 30, 2, 342);
		add(separator_9);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(9, 372, 992, 2);
		add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(1000, 23, 2, 349);
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
		
		JLabel lblApellidoPaterno = new JLabel("Apellido Paterno");
		lblApellidoPaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblApellidoPaterno.setBounds(75, 106, 99, 14);
		add(lblApellidoPaterno);
		
		txtFieldPaterno = new JTextField();
		txtFieldPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.soloLetras(arg0);
			}
		});

		txtFieldPaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldPaterno.setBackground(new Color(176, 196, 222));
		txtFieldPaterno.setBorder(null);
		txtFieldPaterno.setBounds(190, 105, 112, 20);
		add(txtFieldPaterno);
		txtFieldPaterno.setColumns(10);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblApellidoMaterno.setBounds(75, 141, 99, 14);
		add(lblApellidoMaterno);
		
		txtFieldMaterno = new JTextField();
		txtFieldMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.soloLetras(arg0);
			}
		});
		txtFieldMaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldMaterno.setBackground(new Color(176, 196, 222));
		txtFieldMaterno.setBorder(null);
		txtFieldMaterno.setBounds(190, 138, 112, 20);
		add(txtFieldMaterno);
		txtFieldMaterno.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNombre.setBounds(75, 172, 46, 14);
		add(lblNombre);
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.soloLetras(arg0);
			}
		});
		txtFieldNombre.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNombre.setBackground(new Color(176, 196, 222));
		txtFieldNombre.setBorder(null);
		txtFieldNombre.setBounds(190, 169, 112, 20);
		add(txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblSexo.setBounds(350, 233, 46, 14);
		add(lblSexo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha Nac.");
		lblFechaDeNacimiento.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(350, 264, 93, 14);
		add(lblFechaDeNacimiento);
		
		txtFieldFechaNac = new JTextField();
		txtFieldFechaNac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
//				Validaciones.validarFecha(fecha);
			}
		});
		txtFieldFechaNac.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaNac.setBackground(new Color(176, 196, 222));
		txtFieldFechaNac.setBorder(null);
		txtFieldFechaNac.setBounds(440, 261, 112, 20);
		add(txtFieldFechaNac);
		txtFieldFechaNac.setColumns(10);
		
		JLabel lblEdoCivil = new JLabel("Estado Civil");
		lblEdoCivil.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEdoCivil.setBounds(350, 296, 66, 14);
		add(lblEdoCivil);
		
		txtFieldEdoCivil = new JTextField();
		txtFieldEdoCivil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				Validaciones.soloLetras(arg0);
			}
		});
		txtFieldEdoCivil.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEdoCivil.setBackground(new Color(176, 196, 222));
		txtFieldEdoCivil.setBorder(null);
		txtFieldEdoCivil.setBounds(440, 293, 112, 20);
		add(txtFieldEdoCivil);
		txtFieldEdoCivil.setColumns(10);
		
		JLabel lblDatosLaborales = new JLabel("Datos Cliente");
		lblDatosLaborales.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosLaborales.setBounds(632, 77, 113, 14);
		add(lblDatosLaborales);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setBackground(new Color(0, 128, 128));
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setBounds(623, 94, 2, 162);
		add(separator_16);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setBackground(new Color(0, 128, 128));
		separator_17.setBounds(732, 89, 230, 2);
		add(separator_17);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(531, 293, 46, 14);
		add(lblNewLabel_2);
		
		comboBoxOrientacion = new JComboBox();
		comboBoxOrientacion.setModel(new DefaultComboBoxModel(new String[] {"Norte", "Sur", "Este", "Oeste"}));
		comboBoxOrientacion.setBackground(new Color(176, 196, 222));
		comboBoxOrientacion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxOrientacion.setBounds(440, 139, 112, 22);
		add(comboBoxOrientacion);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Femenino", "Masculino"}));
		comboBoxSexo.setBackground(new Color(176, 196, 222));
		comboBoxSexo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxSexo.setBounds(440, 231, 112, 22);
		add(comboBoxSexo);
		
		JLabel lblFechaCli = new JLabel("Fecha de Registro");
		lblFechaCli.setBounds(646, 167, 99, 14);
		add(lblFechaCli);
		
		txtFieldFechaCli = new JTextField();
		txtFieldFechaCli.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaCli.setColumns(10);
		txtFieldFechaCli.setBorder(null);
		txtFieldFechaCli.setBackground(new Color(176, 196, 222));
		txtFieldFechaCli.setBounds(774, 164, 145, 20);
		add(txtFieldFechaCli);
		
		txtFieldCve_Cliente = new JTextField();
		txtFieldCve_Cliente.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCve_Cliente.setColumns(10);
		txtFieldCve_Cliente.setBorder(null);
		txtFieldCve_Cliente.setBackground(new Color(176, 196, 222));
		txtFieldCve_Cliente.setBounds(774, 124, 145, 20);
		add(txtFieldCve_Cliente);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblId.setBounds(75, 78, 46, 14);
		add(lblId);
		
		JLabel labelIdCliente = new JLabel("Id Cliente");
		labelIdCliente.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		labelIdCliente.setBounds(646, 127, 66, 14);
		add(labelIdCliente);
		
		txtFieldIdPer = new JTextField();
		txtFieldIdPer.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldIdPer.setColumns(10);
		txtFieldIdPer.setBorder(null);
		txtFieldIdPer.setBackground(new Color(176, 196, 222));
		txtFieldIdPer.setBounds(190, 75, 112, 20);
		add(txtFieldIdPer);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBorder(null);
//		btnLimpiar.setIcon(new ImageIcon(PanelCliente.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(1012, 203, 119, 31);
		add(btnLimpiar);

	}
	
	public Icon getIcon(String ruta, int width, int heigth){
		Icon miIcono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, heigth, 0));
		return miIcono;
	}
}

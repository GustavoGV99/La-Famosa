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

import static utileria.Validaciones.*;
import consultas.ConsultasChecador;
import consultas.ConsultasUbicacion;
import controlador.CtrlChecador;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Estado;
import modelo.Persona;

import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelContrato extends JPanel {
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
	
	public JComboBox comboBoxEstado;
	public JComboBox comboBoxCiudad;
	public JComboBox comboBoxCP;
	public JComboBox comboBoxColonia;
	public JComboBox comboBoxTienda;
	
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnChecador;
	
	public JTable table;
	public JTextField txtFieldFechaInicio;
	public JTextField txtFieldFechaFin;
	public JTextField txtFieldPuesto;
	public JTextField txtFieldSueldo;
	public JTextField txtFieldPeriodoSueldo;
	public JTextField txtFieldHoraEntrada;
	public JTextField txtFieldHoraSalida;
	public JTextField txtFieldInicioComida;
	public JTextField txtFieldFinComida;
	public JButton btnRegresar;
	public JComboBox comboBoxSexo;
	public JComboBox comboBoxOrientacion;
	
	private ConsultasChecador consultasChecador;
	private ConsultasUbicacion consultasUbicacion;
	private PanelChecador panelChecador;
	private CtrlChecador ctrlChecador;

	public DefaultComboBoxModel modeloEstados;
	public DefaultComboBoxModel modeloCiudades;
	public DefaultComboBoxModel modeloCodigos;
	public DefaultComboBoxModel modeloColonias;
	/**
	 * Create the panel.
	 */
	public PanelContrato() {
		setBorder(new EmptyBorder(1, 1, 5, 1));
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		setBounds(0, 0, 1150, 670);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCalle.setBounds(58, 299, 46, 14);
		add(lblCalle);

		txtFieldCalle = new JTextField();
		txtFieldCalle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		txtFieldCalle.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCalle.setBackground(new Color(176, 196, 222));
		txtFieldCalle.setBorder(null);
		txtFieldCalle.setBounds(156, 296, 112, 20);
		add(txtFieldCalle);
		txtFieldCalle.setColumns(10);

		JLabel lblNewLabel = new JLabel("N\u00FAmero");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel.setBounds(302, 77, 46, 14);
		add(lblNewLabel);

		txtFieldNumero = new JTextField();
		txtFieldNumero.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
				if(txtFieldNumero.getText().length() > 3) {
					arg0.consume();
				}
			}
		});
		txtFieldNumero.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNumero.setBackground(new Color(176, 196, 222));
		txtFieldNumero.setBorder(null);
		txtFieldNumero.setBounds(387, 77, 112, 20);
		add(txtFieldNumero);
		txtFieldNumero.setColumns(10);

		txtFieldEntreCalles = new JTextField();
		txtFieldEntreCalles.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		txtFieldEntreCalles.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEntreCalles.setBackground(new Color(176, 196, 222));
		txtFieldEntreCalles.setBorder(null);
		txtFieldEntreCalles.setBounds(387, 108, 112, 20);
		add(txtFieldEntreCalles);
		txtFieldEntreCalles.setColumns(10);

		JLabel lblEntreCalles = new JLabel("Entre calles");
		lblEntreCalles.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEntreCalles.setBounds(302, 108, 78, 14);
		add(lblEntreCalles);

		JLabel lblOrientacin = new JLabel("Orientaci\u00F3n");
		lblOrientacin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblOrientacin.setBounds(302, 142, 78, 14);
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
		txtFieldTelefono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldTelefono.setBackground(new Color(176, 196, 222));
		txtFieldTelefono.setBorder(null);
		txtFieldTelefono.setBounds(387, 170, 112, 20);
		add(txtFieldTelefono);
		txtFieldTelefono.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEstado.setBounds(58, 175, 46, 14);
		add(lblEstado);

		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCiudad.setBounds(58, 206, 46, 14);
		add(lblCiudad);

		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo postal");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(58, 237, 99, 14);
		add(lblNewLabel_1);

		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblColonia.setBounds(58, 268, 46, 14);
		add(lblColonia);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono");
		lblTelfono.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTelfono.setBounds(302, 174, 66, 14);
		add(lblTelfono);

		txtFieldCorreo = new JTextField();
		txtFieldCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldCorreo.setBackground(new Color(176, 196, 222));
		txtFieldCorreo.setBorder(null);
		txtFieldCorreo.setBounds(387, 201, 112, 20);
		add(txtFieldCorreo);
		txtFieldCorreo.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblCorreo.setBounds(302, 205, 46, 14);
		add(lblCorreo);
		
		// TABLA
		table = new JTable();
		table.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		table.setSelectionBackground(new Color(255, 255, 204));
		table.setRowHeight(25);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Folio", "Paterno", "Materno", "Nombre", "Tel\u00E9fono", "Fin contrato", "Hora entrada", "Hora salida"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
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
		comboBoxEstado.setBounds(156, 172, 112, 20);
		consultasUbicacion.listarEstados(comboBoxEstado); /////// IMPORTANTE ///////
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
		comboBoxCiudad.setBounds(156, 203, 112, 20);
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
		comboBoxCP.setBounds(156, 234, 112, 20);
		add(comboBoxCP);

		comboBoxColonia = new JComboBox();
		comboBoxColonia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxColonia.setBackground(new Color(176, 196, 222));
		comboBoxColonia.setBounds(156, 265, 112, 20);
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
		btnEliminar.setBounds(1012, 159, 119, 31);
		add(btnEliminar);
		
		// ETIQUETAS DISEÑO
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatos.setBounds(10, 11, 139, 14);
		add(lblDatos);
		
		JLabel lblIngresarDatos = new JLabel("Datos personales");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(35, 35, 139, 23);
		add(lblIngresarDatos);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(1022, 11, 99, 14);
		add(lblOperaciones);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(11, 385, 60, 14);
		add(lblDetalle);
		
		// SEPARADORES
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(528, 355, 459, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(985, 52, 2, 213);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(528, 296, 2, 61);
		add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(528, 265, 458, 2);
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
		lblApellidoPaterno.setBounds(58, 78, 99, 14);
		add(lblApellidoPaterno);
		
		txtFieldPaterno = new JTextField();
		txtFieldPaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldPaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldPaterno.setBackground(new Color(176, 196, 222));
		txtFieldPaterno.setBorder(null);
		txtFieldPaterno.setBounds(156, 77, 112, 20);
		add(txtFieldPaterno);
		txtFieldPaterno.setColumns(10);
		
		JLabel lblApellidoMaterno = new JLabel("Apellido Materno");
		lblApellidoMaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblApellidoMaterno.setBounds(58, 113, 99, 14);
		add(lblApellidoMaterno);
		
		txtFieldMaterno = new JTextField();
		txtFieldMaterno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldMaterno.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldMaterno.setBackground(new Color(176, 196, 222));
		txtFieldMaterno.setBorder(null);
		txtFieldMaterno.setBounds(156, 110, 112, 20);
		add(txtFieldMaterno);
		txtFieldMaterno.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNombre.setBounds(58, 144, 46, 14);
		add(lblNombre);
		
		txtFieldNombre = new JTextField();
		txtFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldNombre.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldNombre.setBackground(new Color(176, 196, 222));
		txtFieldNombre.setBorder(null);
		txtFieldNombre.setBounds(156, 141, 112, 20);
		add(txtFieldNombre);
		txtFieldNombre.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblSexo.setBounds(302, 233, 46, 14);
		add(lblSexo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha Nac.");
		lblFechaDeNacimiento.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(302, 264, 93, 14);
		add(lblFechaDeNacimiento);
		
		txtFieldFechaNac = new JTextField();
		txtFieldFechaNac.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(txtFieldFechaNac.getText().equals("dd/mm/aaaa")) {
					txtFieldFechaNac.setText("");
					txtFieldFechaNac.setForeground(Color.BLACK);
				}
			}
		});
		txtFieldFechaNac.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		txtFieldFechaNac.setForeground(Color.GRAY);
		txtFieldFechaNac.setText("dd/mm/aaaa");
		txtFieldFechaNac.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaNac.setBackground(new Color(176, 196, 222));
		txtFieldFechaNac.setBorder(null);
		txtFieldFechaNac.setBounds(387, 261, 112, 20);
		add(txtFieldFechaNac);
		txtFieldFechaNac.setColumns(10);
		
		JLabel lblEdoCivil = new JLabel("Estado Civil");
		lblEdoCivil.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblEdoCivil.setBounds(302, 296, 66, 14);
		add(lblEdoCivil);
		
		txtFieldEdoCivil = new JTextField();
		txtFieldEdoCivil.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldEdoCivil.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldEdoCivil.setBackground(new Color(176, 196, 222));
		txtFieldEdoCivil.setBorder(null);
		txtFieldEdoCivil.setBounds(387, 293, 112, 20);
		add(txtFieldEdoCivil);
		txtFieldEdoCivil.setColumns(10);
		
		JLabel lblDatosLaborales = new JLabel("Datos Laborales");
		lblDatosLaborales.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosLaborales.setBounds(529, 39, 113, 14);
		add(lblDatosLaborales);
		
		JLabel lblFechaInicio = new JLabel("Fecha inicio");
		lblFechaInicio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechaInicio.setBounds(544, 83, 78, 14);
		add(lblFechaInicio);
		
		txtFieldFechaInicio = new JTextField();
		txtFieldFechaInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(txtFieldFechaInicio.getText().equals("dd/mm/aaaa")) {
					txtFieldFechaInicio.setText("");
					txtFieldFechaInicio.setForeground(Color.BLACK);
				}
			}
		});
		txtFieldFechaInicio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		txtFieldFechaInicio.setBorder(null);
		txtFieldFechaInicio.setBackground(new Color(176, 196, 222));
		txtFieldFechaInicio.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaInicio.setForeground(Color.GRAY);
		txtFieldFechaInicio.setText("dd/mm/aaaa");
		txtFieldFechaInicio.setBounds(632, 77, 112, 20);
		add(txtFieldFechaInicio);
		txtFieldFechaInicio.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFechaFin.setBounds(544, 111, 66, 14);
		add(lblFechaFin);
		
		txtFieldFechaFin = new JTextField();
		txtFieldFechaFin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(txtFieldFechaFin.getText().equals("dd/mm/aaaa")) {
					txtFieldFechaFin.setText("");
					txtFieldFechaFin.setForeground(Color.BLACK);
				}
			}
		});
		txtFieldFechaFin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				elementosFecha(arg0);
			}
		});
		txtFieldFechaFin.setForeground(Color.GRAY);
		txtFieldFechaFin.setBorder(null);
		txtFieldFechaFin.setBackground(new Color(176, 196, 222));
		txtFieldFechaFin.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFechaFin.setText("dd/mm/aaaa");
		txtFieldFechaFin.setBounds(632, 108, 112, 20);
		add(txtFieldFechaFin);
		txtFieldFechaFin.setColumns(10);
		
		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblPuesto.setBounds(544, 145, 46, 14);
		add(lblPuesto);
		
		txtFieldPuesto = new JTextField();
		txtFieldPuesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldPuesto.setBorder(null);
		txtFieldPuesto.setBackground(new Color(176, 196, 222));
		txtFieldPuesto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldPuesto.setBounds(632, 139, 112, 20);
		add(txtFieldPuesto);
		txtFieldPuesto.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo");
		lblSueldo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblSueldo.setBounds(544, 174, 46, 14);
		add(lblSueldo);
		
		txtFieldSueldo = new JTextField();
		txtFieldSueldo.setText("$");
		txtFieldSueldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosDecimales(arg0);
			}
		});
		txtFieldSueldo.setBorder(null);
		txtFieldSueldo.setBackground(new Color(176, 196, 222));
		txtFieldSueldo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldSueldo.setBounds(632, 169, 112, 20);
		add(txtFieldSueldo);
		txtFieldSueldo.setColumns(10);
		
		JLabel lblPeriodoSueldo = new JLabel("Periodo Sueldo");
		lblPeriodoSueldo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblPeriodoSueldo.setBounds(544, 202, 93, 14);
		add(lblPeriodoSueldo);
		
		txtFieldPeriodoSueldo = new JTextField();
		txtFieldPeriodoSueldo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloLetras(arg0);
			}
		});
		txtFieldPeriodoSueldo.setBorder(null);
		txtFieldPeriodoSueldo.setBackground(new Color(176, 196, 222));
		txtFieldPeriodoSueldo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldPeriodoSueldo.setBounds(632, 199, 112, 20);
		add(txtFieldPeriodoSueldo);
		txtFieldPeriodoSueldo.setColumns(10);
		
		JLabel lblHoraEntrada = new JLabel("Hora entrada");
		lblHoraEntrada.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblHoraEntrada.setBounds(774, 77, 78, 14);
		add(lblHoraEntrada);
		
		txtFieldHoraEntrada = new JTextField();
		txtFieldHoraEntrada.setBorder(null);
		txtFieldHoraEntrada.setBackground(new Color(176, 196, 222));
		txtFieldHoraEntrada.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldHoraEntrada.setBounds(863, 74, 112, 20);
		add(txtFieldHoraEntrada);
		txtFieldHoraEntrada.setColumns(10);
		
		JLabel lblHoraSalida = new JLabel("Hora salida");
		lblHoraSalida.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblHoraSalida.setBounds(774, 109, 78, 14);
		add(lblHoraSalida);
		
		txtFieldHoraSalida = new JTextField();
		txtFieldHoraSalida.setBorder(null);
		txtFieldHoraSalida.setBackground(new Color(176, 196, 222));
		txtFieldHoraSalida.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldHoraSalida.setBounds(863, 106, 112, 20);
		add(txtFieldHoraSalida);
		txtFieldHoraSalida.setColumns(10);
		
		JLabel lblInicioComi = new JLabel("Inicio comida");
		lblInicioComi.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblInicioComi.setBounds(774, 143, 78, 14);
		add(lblInicioComi);
		
		txtFieldInicioComida = new JTextField();
		txtFieldInicioComida.setBorder(null);
		txtFieldInicioComida.setBackground(new Color(176, 196, 222));
		txtFieldInicioComida.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldInicioComida.setBounds(863, 140, 112, 20);
		add(txtFieldInicioComida);
		txtFieldInicioComida.setColumns(10);
		
		JLabel lblFinComida = new JLabel("Fin comida");
		lblFinComida.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblFinComida.setBounds(774, 174, 66, 14);
		add(lblFinComida);
		
		txtFieldFinComida = new JTextField();
		txtFieldFinComida.setBorder(null);
		txtFieldFinComida.setBackground(new Color(176, 196, 222));
		txtFieldFinComida.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		txtFieldFinComida.setBounds(863, 171, 112, 20);
		add(txtFieldFinComida);
		txtFieldFinComida.setColumns(10);
		
		JLabel lblTienda = new JLabel("Tienda");
		lblTienda.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblTienda.setBounds(774, 205, 46, 14);
		add(lblTienda);
		
		comboBoxTienda = new JComboBox();
		comboBoxTienda.setBackground(new Color(176, 196, 222));
		comboBoxTienda.setName("Tienda");
		comboBoxTienda.setBounds(863, 202, 112, 20);
		add(comboBoxTienda);
		
		btnChecador = new JButton("Checador");
		btnChecador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				consultasChecador = new ConsultasChecador();
				panelChecador = new PanelChecador();
				ctrlChecador = new CtrlChecador(consultasChecador, panelChecador);
				consultasChecador.generalChecador(panelChecador.table);
				panelChecador.setSize(1200, 670);
				panelChecador.setLocation(0, 0);
				PanelContrato.this.removeAll();
				PanelContrato.this.add(panelChecador, BorderLayout.CENTER);
				PanelContrato.this.revalidate();
				PanelContrato.this.repaint();
			}
		});
		btnChecador.setBorder(null);
		btnChecador.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoChecador.png")));
		btnChecador.setForeground(Color.WHITE);
		btnChecador.setBackground(new Color(0, 0, 51));
		btnChecador.setBounds(694, 307, 126, 31);
		add(btnChecador);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setBackground(new Color(0, 128, 128));
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setBounds(526, 57, 2, 209);
		add(separator_16);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setBackground(new Color(0, 128, 128));
		separator_17.setBounds(641, 52, 344, 2);
		add(separator_17);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(531, 293, 46, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cumplimiento de horarios");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(538, 282, 193, 14);
		add(lblNewLabel_3);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setBackground(new Color(0, 128, 128));
		separator_18.setBounds(710, 293, 277, 2);
		add(separator_18);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(0, 128, 128));
		separator_2_1.setBounds(985, 296, 2, 61);
		add(separator_2_1);
		
		btnRegresar = new JButton("Limpiar");
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(1012, 200, 119, 31);
		add(btnRegresar);
		
		comboBoxOrientacion = new JComboBox();
		comboBoxOrientacion.setModel(new DefaultComboBoxModel(new String[] {"Norte", "Sur", "Este", "Oeste"}));
		comboBoxOrientacion.setBackground(new Color(176, 196, 222));
		comboBoxOrientacion.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxOrientacion.setBounds(387, 139, 112, 22);
		add(comboBoxOrientacion);
		
		comboBoxSexo = new JComboBox();
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] {"Femenino", "Masculino"}));
		comboBoxSexo.setBackground(new Color(176, 196, 222));
		comboBoxSexo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxSexo.setBounds(387, 231, 112, 22);
		add(comboBoxSexo);

	}
	
	public Icon getIcon(String ruta, int width, int heigth){
		Icon miIcono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, heigth, 0));
		return miIcono;
	}
}

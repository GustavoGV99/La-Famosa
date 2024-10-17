package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import static controlador.CtrlProducto.existe;
import static utileria.Validaciones.*;
import controlador.CtrlProducto;
import controlador.CtrlSurtido;
import modelo.Colonia;
import modelo.DetalleProducto;
import modelo.Surtido;
import modelo.Sucursal;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import javax.swing.table.DefaultTableModel;

import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import consultas.ConsultasSurtido;
import consultas.ConsultasSucursal;

public class PanelProducto extends JPanel {
	public JButton btnEliminar;
	public JButton btnModificar;
	public JButton btnBuscar;
	public JButton btnRegistrar;
	public JButton btnLimpiar;
	public JButton btnRegresar;
	
	public JTable table;
	public JTextField textFieldCodBar;
	public JTextField textFieldNombre;
	public JTextField textFieldMarca;
	public JTextField textFieldColor;
	public JTextField textFieldGarantia;
	public JTextField textFieldModelo;
	public JTextField textFieldContenido;
	public JTextField textFieldAlto;
	public JTextField textFieldAncho;
	public JTextField textFieldLargo;
	public JTextField textFieldMinimo;
	public JTextField textFieldMaximo;

	private PanelSurtido panelSurtido;
	public JButton btnMinMax;
	
	public JComboBox comboBoxTipo;
	public JComboBox comboBoxMedidaGarantia;
	public JComboBox comboBoxUnidadMedida;
	public JComboBox comboBoxPresentacion;
	public JComboBox comboBoxSucursal;
	
	public ArrayList<DetalleProducto> listaMinMax;
	
	private Surtido surtido;
	private ConsultasSucursal consultasSucursal;
	private ConsultasSurtido consultasSurtido;
	private ConsultasProducto consultasProducto;
	private ConsultasProveedor consultasProveedor;
	private CtrlSurtido ctrlSurtido;
	/**
	 * Create the panel.
	 */
	public PanelProducto() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);

		JLabel lblDatosDeSucursal = new JLabel("Datos");
		lblDatosDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDeSucursal.setBounds(30, 16, 139, 14);
		add(lblDatosDeSucursal);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoRegistrar.png")));
		btnRegistrar.setBorder(null);
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 0, 51));
		btnRegistrar.setBounds(900, 66, 172, 34);
		add(btnRegistrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(900, 107, 172, 34);
		add(btnBuscar);

		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoModificar.png")));
		btnModificar.setBorder(null);
		btnModificar.setForeground(new Color(255, 255, 255));
		btnModificar.setBackground(new Color(0, 0, 51));
		btnModificar.setBounds(900, 149, 172, 34);
		add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoEliminar.png")));
		btnEliminar.setBorder(null);
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(0, 0, 51));
		btnEliminar.setBounds(900, 231, 172, 34);
		add(btnEliminar);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo de barras", "Nombre", "Modelo", "Tipo", "Marca", "Garant\u00EDa", "Plazo", "Presentaci\u00F3n"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
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
		js.setBounds(54, 381, 1024, 254);					// AGREGADO
		add(js);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBorder(null);
		btnLimpiar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoLimpiar.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 0, 51));
		btnLimpiar.setBounds(900, 190, 172, 34);
		add(btnLimpiar);
		
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
				PanelProducto.this.removeAll();
				PanelProducto.this.add(panelSurtido, BorderLayout.CENTER);
				PanelProducto.this.revalidate();
				PanelProducto.this.repaint();
			}
		});
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoVolver.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(900, 272, 172, 34);
		add(btnRegresar);

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
		separator_1.setBounds(1100, 56, 2, 264);
		add(separator_1);

		JLabel lblIngresarDatos = new JLabel("Producto");
		lblIngresarDatos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIngresarDatos.setBounds(50, 37, 106, 23);
		add(lblIngresarDatos);

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 347, 60, 14);
		add(lblDetalle);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(874, 58, 2, 262);
		add(separator_2);

		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(874, 318, 225, 2);
		add(separator_3);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 128, 128));
		separator_4.setBounds(127, 54, 455, 2);
		add(separator_4);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 128, 128));
		separator_5.setBounds(50, 306, 532, 2);
		add(separator_5);

		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBackground(new Color(0, 128, 128));
		separator_6.setBounds(50, 58, 2, 249);
		add(separator_6);

		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 128, 128));
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(580, 54, 2, 254);
		add(separator_7);

		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 128, 128));
		separator_8.setBounds(78, 28, 772, 2);
		add(separator_8);

		JSeparator separator_9 = new JSeparator();
		separator_9.setOrientation(SwingConstants.VERTICAL);
		separator_9.setBackground(new Color(0, 128, 128));
		separator_9.setBounds(30, 35, 2, 297);
		add(separator_9);

		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 128, 128));
		separator_10.setBounds(31, 330, 819, 2);
		add(separator_10);

		JSeparator separator_11 = new JSeparator();
		separator_11.setBackground(new Color(0, 128, 128));
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(848, 29, 2, 303);
		add(separator_11);

		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(89, 359, 1013, 2);
		add(separator_12);

		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(30, 368, 2, 289);
		add(separator_13);

		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(1100, 360, 2, 297);
		add(separator_14);

		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(30, 655, 1072, 4);
		add(separator_15);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo de barras");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel.setBounds(78, 74, 106, 14);
		add(lblNewLabel);
		
		textFieldCodBar = new JTextField();
		textFieldCodBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String codigoBarras = textFieldCodBar.getText();
				soloNumeros(e);
				if(codigoBarras.length() >= 7) {
					e.consume();
				}
			}
		});
		textFieldCodBar.setBorder(null);
		textFieldCodBar.setBackground(new Color(176, 196, 222));
		textFieldCodBar.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldCodBar.setBounds(180, 71, 126, 20);
		add(textFieldCodBar);
		textFieldCodBar.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(78, 105, 46, 14);
		add(lblNewLabel_1);
		
		textFieldNombre = new JTextField();
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		textFieldNombre.setBorder(null);
		textFieldNombre.setBackground(new Color(176, 196, 222));
		textFieldNombre.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldNombre.setBounds(180, 102, 126, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(78, 137, 46, 14);
		add(lblNewLabel_2);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Cocina", "Limpieza"}));
		comboBoxTipo.setBounds(180, 133, 126, 22);
		add(comboBoxTipo);
		
		JLabel lblNewLabel_3 = new JLabel("Marca");
		lblNewLabel_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(78, 171, 46, 14);
		add(lblNewLabel_3);
		
		textFieldMarca = new JTextField();
		textFieldMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				numerosLetras(arg0);
			}
		});
		textFieldMarca.setBorder(null);
		textFieldMarca.setBackground(new Color(176, 196, 222));
		textFieldMarca.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldMarca.setBounds(180, 168, 126, 20);
		add(textFieldMarca);
		textFieldMarca.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Color");
		lblNewLabel_4.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(78, 204, 46, 14);
		add(lblNewLabel_4);
		
		textFieldColor = new JTextField();
		textFieldColor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		textFieldColor.setBorder(null);
		textFieldColor.setBackground(new Color(176, 196, 222));
		textFieldColor.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldColor.setBounds(180, 200, 126, 20);
		add(textFieldColor);
		textFieldColor.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Garant\u00EDa");
		lblNewLabel_5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(78, 238, 71, 14);
		add(lblNewLabel_5);
		
		textFieldGarantia = new JTextField();
		textFieldGarantia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		textFieldGarantia.setBorder(null);
		textFieldGarantia.setBackground(new Color(176, 196, 222));
		textFieldGarantia.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldGarantia.setBounds(180, 234, 126, 20);
		add(textFieldGarantia);
		textFieldGarantia.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Plazo garant\u00EDa");
		lblNewLabel_6.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(78, 269, 79, 14);
		add(lblNewLabel_6);
		
		comboBoxMedidaGarantia = new JComboBox();
		comboBoxMedidaGarantia.setModel(new DefaultComboBoxModel(new String[] {"Semana(s)", "Meses", "A\u00F1o(s)"}));
		comboBoxMedidaGarantia.setBounds(180, 265, 126, 22);
		add(comboBoxMedidaGarantia);
		
		JLabel lblNewLabel_7 = new JLabel("Modelo");
		lblNewLabel_7.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(336, 77, 46, 14);
		add(lblNewLabel_7);
		
		textFieldModelo = new JTextField();
		textFieldModelo.setBorder(null);
		textFieldModelo.setBackground(new Color(176, 196, 222));
		textFieldModelo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldModelo.setBounds(425, 74, 126, 20);
		add(textFieldModelo);
		textFieldModelo.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Contenido");
		lblNewLabel_8.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_8.setBounds(336, 109, 79, 14);
		add(lblNewLabel_8);
		
		textFieldContenido = new JTextField();
		textFieldContenido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		textFieldContenido.setBorder(null);
		textFieldContenido.setBackground(new Color(176, 196, 222));
		textFieldContenido.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldContenido.setBounds(425, 106, 126, 20);
		add(textFieldContenido);
		textFieldContenido.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("U. Medida");
		lblNewLabel_9.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_9.setBounds(336, 143, 92, 14);
		add(lblNewLabel_9);
		
		comboBoxUnidadMedida = new JComboBox();
		comboBoxUnidadMedida.setModel(new DefaultComboBoxModel(new String[] {"Litros"}));
		comboBoxUnidadMedida.setBounds(425, 138, 126, 22);
		add(comboBoxUnidadMedida);
		
		JLabel lblNewLabel_10 = new JLabel("Alto");
		lblNewLabel_10.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_10.setBounds(336, 176, 46, 14);
		add(lblNewLabel_10);
		
		textFieldAlto = new JTextField();
		textFieldAlto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				numerosDecimales(e);
			}
		});
		textFieldAlto.setBorder(null);
		textFieldAlto.setBackground(new Color(176, 196, 222));
		textFieldAlto.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldAlto.setBounds(425, 173, 126, 20);
		add(textFieldAlto);
		textFieldAlto.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Ancho");
		lblNewLabel_11.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_11.setBounds(336, 210, 46, 14);
		add(lblNewLabel_11);
		
		textFieldAncho = new JTextField();
		textFieldAncho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				numerosDecimales(e);
			}
		});
		textFieldAncho.setBorder(null);
		textFieldAncho.setBackground(new Color(176, 196, 222));
		textFieldAncho.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldAncho.setBounds(425, 206, 126, 20);
		add(textFieldAncho);
		textFieldAncho.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Largo");
		lblNewLabel_12.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_12.setBounds(336, 241, 46, 14);
		add(lblNewLabel_12);
		
		textFieldLargo = new JTextField();
		textFieldLargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				numerosDecimales(e);
			}
		});
		textFieldLargo.setBorder(null);
		textFieldLargo.setBackground(new Color(176, 196, 222));
		textFieldLargo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldLargo.setBounds(425, 237, 126, 20);
		add(textFieldLargo);
		textFieldLargo.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Presentaci\u00F3n");
		lblNewLabel_13.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(336, 272, 92, 14);
		add(lblNewLabel_13);
		
		comboBoxPresentacion = new JComboBox();
		comboBoxPresentacion.setModel(new DefaultComboBoxModel(new String[] {"Caja de cart\u00F3n", "En pl\u00E1stico"}));
		comboBoxPresentacion.setBounds(425, 266, 126, 22);
		add(comboBoxPresentacion);
		
		JLabel lblDetalle_1 = new JLabel("Detalle");
		lblDetalle_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle_1.setBounds(598, 37, 106, 23);
		add(lblDetalle_1);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setBackground(new Color(0, 128, 128));
		separator_16.setBounds(656, 54, 177, 2);
		add(separator_16);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBackground(new Color(0, 128, 128));
		separator_3_1.setBounds(598, 209, 236, 2);
		add(separator_3_1);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setForeground(new Color(211, 211, 211));
		separator_2_1.setBackground(new Color(0, 128, 128));
		separator_2_1.setBounds(598, 60, 2, 149);
		add(separator_2_1);
		
		JSeparator separator_2_1_1 = new JSeparator();
		separator_2_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1_1.setForeground(new Color(211, 211, 211));
		separator_2_1_1.setBackground(new Color(0, 128, 128));
		separator_2_1_1.setBounds(832, 54, 2, 157);
		add(separator_2_1_1);
		
		JLabel lblNewLabel_14 = new JLabel("M\u00EDnimo");
		lblNewLabel_14.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_14.setBounds(608, 73, 46, 14);
		add(lblNewLabel_14);
		
		textFieldMinimo = new JTextField();
		textFieldMinimo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		textFieldMinimo.setBorder(null);
		textFieldMinimo.setBackground(new Color(176, 196, 222));
		textFieldMinimo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldMinimo.setBounds(696, 73, 126, 20);
		add(textFieldMinimo);
		textFieldMinimo.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("M\u00E1ximo");
		lblNewLabel_15.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_15.setBounds(610, 106, 46, 14);
		add(lblNewLabel_15);
		
		textFieldMaximo = new JTextField();
		textFieldMaximo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNumeros(e);
			}
		});
		textFieldMaximo.setBorder(null);
		textFieldMaximo.setBackground(new Color(176, 196, 222));
		textFieldMaximo.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldMaximo.setBounds(696, 104, 126, 20);
		add(textFieldMaximo);
		textFieldMaximo.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Sucursal");
		lblNewLabel_16.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_16.setBounds(610, 139, 60, 14);
		add(lblNewLabel_16);
		
		comboBoxSucursal = new JComboBox();
		comboBoxSucursal.setBounds(696, 136, 126, 22);
		add(comboBoxSucursal);
		
		btnMinMax = new JButton("");
		listaMinMax = new ArrayList<>();
		btnMinMax.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PanelProducto.this.textFieldCodBar.setEditable(false);
				PanelProducto.this.textFieldNombre.setEditable(false);
				PanelProducto.this.comboBoxTipo.setEnabled(false);
				PanelProducto.this.textFieldMarca.setEditable(false);
				PanelProducto.this.textFieldColor.setEditable(false);
				PanelProducto.this.textFieldGarantia.setEditable(false);
				PanelProducto.this.comboBoxMedidaGarantia.setEnabled(false);
				PanelProducto.this.comboBoxPresentacion.setEnabled(false);
				PanelProducto.this.textFieldModelo.setEditable(false);
				PanelProducto.this.textFieldAlto.setEditable(false);
				PanelProducto.this.textFieldAncho.setEditable(false);
				PanelProducto.this.textFieldLargo.setEditable(false);
				PanelProducto.this.textFieldContenido.setEditable(false);
				PanelProducto.this.comboBoxUnidadMedida.setEnabled(false);
				
				DetalleProducto minMax = new DetalleProducto();
				minMax.setMinimo(Integer.parseInt(PanelProducto.this.textFieldMinimo.getText()));
				minMax.setMaximo(Integer.parseInt(PanelProducto.this.textFieldMaximo.getText()));
				//minMax.setCodigoBarras(Integer.parseInt(PanelProducto.this.textFieldCodBar.getText()));
				//System.out.println("Codbar minmax: " + PanelProducto.this.textFieldCodBar.getText());
				int claveSucursal = ((Sucursal) PanelProducto.this.comboBoxSucursal.getItemAt(PanelProducto.this.comboBoxSucursal.getSelectedIndex())).getClave();
				minMax.setClaveSucursal(claveSucursal);
				listaMinMax.add(minMax);
				
				PanelProducto.this.textFieldMinimo.setText(null);
				PanelProducto.this.textFieldMaximo.setText(null);
			}
		});
		btnMinMax.setBounds(678, 170, 89, 29);
		btnMinMax.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoRegistrar.png")));
		btnMinMax.setBorder(null);
		btnMinMax.setForeground(new Color(255, 255, 255));
		btnMinMax.setBackground(new Color(0, 0, 51));
		add(btnMinMax);

	}
}

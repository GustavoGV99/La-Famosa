package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import consultas.ConsultasContrato;
import consultas.ConsultasSucursal;
import consultas.ConsultasUbicacion;
import controlador.CtrlContrato;
import modelo.Contrato;
import modelo.Persona;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelInventario extends JPanel {
	public JTable table;
	public JButton btnBuscar;

	public PanelContrato panelContrato;
	private Persona persona;
	private Contrato contrato;
	private ConsultasContrato consultasContrato;
	private ConsultasSucursal consultasSucursal;
	private ConsultasUbicacion consultasUbicacion;
	private CtrlContrato ctrlContrato;
	
//	public JComboBox comboBoxSucursal;
	public JTextField txtFieldSucursal;
	public JTextField txtFieldProducto;
	
	public PanelInventario() {
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setBounds(0, 0, 1150, 670);
		
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
				"Codigo", "Nombre", "Tipo", "Marca", "Color", "Garantia", "UMedidaGarantia", "Sucursal","Cantidad"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(54, 79, 751, 520);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(50, 79, 790, 520);						// AGREGADO
		add(js);											// AGREGADO add(table)
		
		// ETIQUETAS
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 41, 60, 14);
		add(lblDetalle);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(903, 35, 99, 14);
		add(lblOperaciones);
		
		// BOTON
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(935, 190, 156, 34);
		add(btnBuscar);
		
		// SEPARADORES
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(987, 54, 140, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1125, 56, 2, 209);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(895, 56, 2, 209);
		add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(895, 263, 232, 2);
		add(separator_3);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(89, 54, 778, 2);
		add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(25, 64, 2, 563);
		add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(865, 54, 2, 573);
		add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(24, 627, 843, 2);
		add(separator_15);
		
		JLabel lblNewLabel = new JLabel("Filtrar");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		lblNewLabel.setBounds(911, 66, 62, 22);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Producto");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(910, 100, 62, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sucursal");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(910, 142, 58, 14);
		add(lblNewLabel_2);
		
//		comboBoxSucursal = new JComboBox();
//		comboBoxSucursal.setModel(new DefaultComboBoxModel(new String[] {"Selecciona", "Tienda", "Producto Una Tienda"}));
//		comboBoxSucursal.setBounds(980, 140, 130, 22);
//		add(comboBoxSucursal);
		
//		comboBoxSucursal = new JComboBox();
//		comboBoxSucursal.setBackground(new Color(176, 196, 222));
//		comboBoxSucursal.setName("Tienda");
//		comboBoxSucursal.setBounds(980, 140, 112, 20);
//		add(comboBoxSucursal);
		
		txtFieldProducto = new JTextField();
		txtFieldProducto.setBounds(980, 97, 105, 22);
		add(txtFieldProducto);
		txtFieldProducto.setColumns(10);
		
		txtFieldSucursal = new JTextField();
		txtFieldSucursal.setBounds(980, 140, 112, 20);
		add(txtFieldSucursal);
		txtFieldSucursal.setColumns(10);
	}
}

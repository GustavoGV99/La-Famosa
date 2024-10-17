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

import static utileria.Validaciones.*;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelChecador extends JPanel {
	public JTable table;
	public JButton btnBuscar;
	public JButton btnRegresar;
	public JTextField textFieldEmpleado;
	public JComboBox comboBoxFecha;

	public PanelContrato panelContrato;
	private Persona persona;
	private Contrato contrato;
	private ConsultasContrato consultasContrato;
	private ConsultasSucursal consultasSucursal;
	private ConsultasUbicacion consultasUbicacion;
	private CtrlContrato ctrlContrato;
	
	public PanelChecador() {
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
				"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setBounds(54, 79, 751, 520);
		table.setBackground(new Color(204, 204, 255));
		JScrollPane js = new JScrollPane(table);			// AGREGADO
		js.setBounds(54, 79, 751, 520);						// AGREGADO
		add(js);											// AGREGADO add(table)
		
		// ETIQUETAS
		JLabel lblDetalle = new JLabel("Detalle checador");
		lblDetalle.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDetalle.setBounds(30, 41, 118, 14);
		add(lblDetalle);
		
		JLabel lblOperaciones = new JLabel("Operaciones");
		lblOperaciones.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblOperaciones.setBounds(874, 41, 99, 14);
		add(lblOperaciones);
		
		// BOTON
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelSucursal.class.getResource("/recursos/iconoBuscar.png")));
		btnBuscar.setBorder(null);
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 0, 51));
		btnBuscar.setBounds(911, 179, 156, 34);
		add(btnBuscar);
		
		btnRegresar = new JButton("Atr\u00E1s");
		btnRegresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				persona = new Persona();
				contrato = new Contrato();
				consultasContrato = new ConsultasContrato();
				consultasSucursal = new ConsultasSucursal();
				consultasUbicacion = new ConsultasUbicacion();
				panelContrato = new PanelContrato();
				ctrlContrato = new CtrlContrato(persona, contrato, consultasContrato, consultasSucursal, consultasUbicacion, panelContrato);
				ctrlContrato.llenarComboSucursal();
				consultasContrato.llenarTablaContrato(panelContrato.table);
				panelContrato.setSize(1200, 670);
				panelContrato.setLocation(0, 0);
				PanelChecador.this.removeAll();
				PanelChecador.this.add(panelContrato, BorderLayout.CENTER);
				PanelChecador.this.revalidate();
				PanelChecador.this.repaint();
				
				//PanelChecador.this.setVisible(false);
				//PanelChecador.this.removeAll();
				//PanelChecador.this.add(panelContrato, BorderLayout.CENTER);
				//PanelChecador.this.revalidate();
				//PanelChecador.this.repaint();
			}
		});
		btnRegresar.setBorder(null);
		btnRegresar.setIcon(new ImageIcon(PanelContrato.class.getResource("/recursos/iconoVolver.png")));
		btnRegresar.setForeground(new Color(255, 255, 255));
		btnRegresar.setBackground(new Color(0, 0, 51));
		btnRegresar.setBounds(911, 224, 156, 34);
		add(btnRegresar);
		
		// SEPARADORES
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 128, 128));
		separator.setBounds(962, 54, 140, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 128, 128));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(1100, 56, 2, 226);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(0, 128, 128));
		separator_2.setForeground(new Color(211, 211, 211));
		separator_2.setBounds(874, 58, 2, 224);
		add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 128, 128));
		separator_3.setBounds(876, 280, 225, 2);
		add(separator_3);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBackground(new Color(0, 128, 128));
		separator_12.setBounds(158, 54, 673, 1);
		add(separator_12);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBackground(new Color(0, 128, 128));
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(30, 64, 2, 563);
		add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBackground(new Color(0, 128, 128));
		separator_14.setBounds(830, 54, 2, 573);
		add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBackground(new Color(0, 128, 128));
		separator_15.setBounds(30, 627, 801, 2);
		add(separator_15);
		
		JLabel lblNewLabel = new JLabel("Filtrar");
		lblNewLabel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 13));
		lblNewLabel.setBounds(899, 66, 62, 22);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Empleado");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(899, 99, 62, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fecha");
		lblNewLabel_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(899, 142, 46, 14);
		add(lblNewLabel_2);
		
		comboBoxFecha = new JComboBox();
		comboBoxFecha.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		comboBoxFecha.setModel(new DefaultComboBoxModel(new String[] {"Selecciona", "Este dia", "Quincena actual", "Este mes"}));
		comboBoxFecha.setBounds(967, 135, 105, 22);
		add(comboBoxFecha);
		
		textFieldEmpleado = new JTextField();
		textFieldEmpleado.setBackground(new Color(176, 196, 222));
		textFieldEmpleado.setBorder(null);
		textFieldEmpleado.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 12));
		textFieldEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				soloNumeros(arg0);
				if(textFieldEmpleado.getText().length() > 3) {
					arg0.consume();
				}
			}
		});
		textFieldEmpleado.setBounds(967, 97, 105, 20);
		add(textFieldEmpleado);
		textFieldEmpleado.setColumns(10);
	}
}

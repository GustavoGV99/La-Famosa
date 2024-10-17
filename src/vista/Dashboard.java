package vista;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JLabel;

import consultas.ConsultasCliente;
import consultas.ConsultasContrato;
import consultas.ConsultasEnvio;
import consultas.ConsultasInventario;
import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import consultas.ConsultasSurtido;
import consultas.ConsultasSucursal;
import consultas.ConsultasUbicacion;
import consultas.ConsultasVehiculo;
import consultas.ConsultasVenta;
import controlador.CtrlCliente;
import controlador.CtrlContrato;
import controlador.CtrlEnvio;
import controlador.CtrlInventario;
import controlador.CtrlSurtido;
import controlador.CtrlSucursal;
import controlador.CtrlVenta;
import modelo.Acomodo;
import modelo.Cliente;
import modelo.Contrato;
import modelo.DatoEnvio;
import modelo.Enviar;
import modelo.ListaCarga;
import modelo.Persona;
import modelo.DetalleSurtido;
import modelo.Surtido;
import modelo.StatusEnvio;
import modelo.Sucursal;
import modelo.Vehiculo;
import modelo.Ticket;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JPanel panelBotones;
	public JPanel panelDinamico;
	private GroupLayout gl_panelDinamico;
	
	public PanelContrato panelContrato;
	public PanelSucursal panelSucursales;
	public PanelSurtido panelSurtido;
	public PanelEnvio panelEnvio;
	public PanelVenta panelVenta;
	public PanelCliente panelCliente;
	public PanelInventario panelInventario;
	
	private Sucursal sucursal;
	private Persona persona;
	private Contrato contrato;
	private Surtido surtido;
	private Enviar enviar;
	private ListaCarga listaCarga;
	private StatusEnvio statusEnvio;
	private Vehiculo vehiculo;
	private Ticket venta;
	private Cliente cliente;
	private DatoEnvio datoEnvio;
	
	private ConsultasSucursal consultasSucursal;
	private ConsultasContrato consultasContrato;
	private ConsultasUbicacion consultasUbicacion;
	private ConsultasSurtido consultasSurtido;
	private ConsultasProducto consultasProducto;
	private ConsultasProveedor consultasProveedor;
	private ConsultasVehiculo consultasVehiculo;
	private ConsultasEnvio consultasEnvio;
	private ConsultasCliente consultasCliente;
	private ConsultasInventario consultasInventario;
	private ConsultasVenta consultasVenta;
	
	private CtrlSucursal ctrlSucursal;
	private CtrlContrato ctrlContrato;
	private CtrlSurtido ctrlSurtido;
	private CtrlEnvio ctrlEnvio;
	private CtrlInventario ctrlInventario;
	private CtrlVenta ctrlVenta;
	private CtrlCliente ctrlCliente; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dashboard() {
		setTitle("La Famosa");
		Color colorPanelSuperior =new Color(7, 7, 7); 			//Negro
		Color colorBotonesBg =new Color(64, 67, 78); 			//Gris
		Color color3 =new Color(216, 226, 220); 				//Blanco-Gris
		Color colorTitulos =new Color(255, 255, 255); 			//Blanco
		Color colorPanelDinamico =new Color(255, 255, 255); 	//Blanco
		
		Font fuenteBotones = new Font("Segoe UI", Font.PLAIN, 20);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 700);
		setExtendedState(this.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(colorPanelSuperior);
		
		panelBotones = new JPanel();

		JPanel panelDinamico = new JPanel();
		panelDinamico.setBackground(new Color(230, 230, 250));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panelSuperior, GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE).addGroup(gl_contentPane.createSequentialGroup().addComponent(panelBotones, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addComponent(panelDinamico, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane.createSequentialGroup().addComponent(panelSuperior, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panelBotones, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE).addComponent(panelDinamico, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))));
		
		gl_panelDinamico = new GroupLayout(panelDinamico);
		gl_panelDinamico.setHorizontalGroup(
			gl_panelDinamico.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1125, Short.MAX_VALUE)
		);
		gl_panelDinamico.setVerticalGroup(
			gl_panelDinamico.createParallelGroup(Alignment.LEADING)
				.addGap(0, 616, Short.MAX_VALUE)
		);
		panelDinamico.setLayout(gl_panelDinamico);
		panelBotones.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel btnTrabajadores = new JPanel();
		btnTrabajadores.setBackground(colorBotonesBg);
		btnTrabajadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnTrabajadores.setBackground(new Color(12, 24, 40));
				btnTrabajadores.setForeground(colorTitulos);
				
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
				panelDinamico.removeAll();
				panelDinamico.add(panelContrato, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnTrabajadores.setBackground(new Color(92, 147, 150));
				btnTrabajadores.setForeground(colorTitulos);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnTrabajadores.setBackground(new Color(12, 24, 40));
				btnTrabajadores.setForeground(colorTitulos);
			}
		});
		panelBotones.add(btnTrabajadores);
		
		JLabel icnTrabajadores = new JLabel(getIcon("/recursos/iconoEmpleados.png", 26, 26));
		
		JLabel lblTrabajores = new JLabel("Trabajadores");
		lblTrabajores.setFont(fuenteBotones);
		lblTrabajores.setForeground(colorTitulos);
		GroupLayout gl_btnTrabajadores = new GroupLayout(btnTrabajadores);
		gl_btnTrabajadores.setHorizontalGroup(gl_btnTrabajadores.createParallelGroup(Alignment.LEADING).addGroup(gl_btnTrabajadores.createSequentialGroup().addGap(35).addComponent(icnTrabajadores).addGap(5).addComponent(lblTrabajores)));
		gl_btnTrabajadores.setVerticalGroup(gl_btnTrabajadores.createParallelGroup(Alignment.LEADING).addGroup(gl_btnTrabajadores.createSequentialGroup().addGap(18).addComponent(icnTrabajadores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnTrabajadores.createSequentialGroup().addGap(5).addComponent(lblTrabajores, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnTrabajadores.setLayout(gl_btnTrabajadores);
		
		JPanel btnSucursales = new JPanel();
		btnSucursales.setBackground(colorBotonesBg);
		btnSucursales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSucursales.setBackground(new Color(92, 147, 150));
				btnSucursales.setForeground(Color.BLACK);
				
				sucursal = new Sucursal();
				consultasSucursal = new ConsultasSucursal();
				panelSucursales = new PanelSucursal();
				ctrlSucursal = new CtrlSucursal(sucursal, consultasSucursal, panelSucursales);
				consultasSucursal.llenarTabla(panelSucursales.table);
				//showPanel(panelSucursales);
				panelSucursales.setSize(1300, 700);
				panelSucursales.setLocation(0, 0);
				panelDinamico.removeAll();
				panelDinamico.add(panelSucursales, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSucursales.setBackground(new Color(92, 147, 150));
				btnSucursales.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSucursales.setBackground(new Color(12, 24, 40));
				btnSucursales.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnSucursales);
		
		JLabel icnSucursales = new JLabel(getIcon("/recursos/iconoSucursales.png", 26, 26));
		
		JLabel lblSucursales = new JLabel("Sucursales");
		lblSucursales.setFont(fuenteBotones);
		lblSucursales.setForeground(colorTitulos);
		GroupLayout gl_btnSucursales = new GroupLayout(btnSucursales);
		gl_btnSucursales.setHorizontalGroup(gl_btnSucursales.createParallelGroup(Alignment.LEADING).addGroup(gl_btnSucursales.createSequentialGroup().addGap(47).addComponent(icnSucursales).addGap(5).addComponent(lblSucursales)));
		gl_btnSucursales.setVerticalGroup(gl_btnSucursales.createParallelGroup(Alignment.LEADING).addGroup(gl_btnSucursales.createSequentialGroup().addGap(18).addComponent(icnSucursales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnSucursales.createSequentialGroup().addGap(5).addComponent(lblSucursales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnSucursales.setLayout(gl_btnSucursales);
		
		JPanel btnVentas = new JPanel();
		btnVentas.setBackground(colorBotonesBg);
		btnVentas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnVentas.setBackground(new Color(92, 147, 150));
				btnVentas.setForeground(Color.WHITE);
					
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
				panelDinamico.removeAll();
				panelDinamico.add(panelVenta, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnVentas.setBackground(new Color(92, 147, 150));
				btnVentas.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnVentas.setBackground(new Color(12, 24, 40));
				btnVentas.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnVentas);
		
		JLabel icnVentas = new JLabel(getIcon("/recursos/iconoVentas.png", 26, 26));
		
		JLabel lblVentas = new JLabel("Ventas");
		lblVentas.setFont(fuenteBotones);
		lblVentas.setForeground(colorTitulos);
		GroupLayout gl_btnVentas = new GroupLayout(btnVentas);
		gl_btnVentas.setHorizontalGroup(gl_btnVentas.createParallelGroup(Alignment.LEADING).addGroup(gl_btnVentas.createSequentialGroup().addGap(63).addComponent(icnVentas).addGap(5).addComponent(lblVentas)));
		gl_btnVentas.setVerticalGroup(gl_btnVentas.createParallelGroup(Alignment.LEADING).addGroup(gl_btnVentas.createSequentialGroup().addGap(18).addComponent(icnVentas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnVentas.createSequentialGroup().addGap(5).addComponent(lblVentas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnVentas.setLayout(gl_btnVentas);
		
		JPanel btnInventario = new JPanel();
		btnInventario.setBackground(colorBotonesBg);
		btnInventario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnInventario.setBackground(new Color(92, 147, 150));
				btnInventario.setForeground(Color.WHITE);
				
				consultasProducto = new ConsultasProducto();
				consultasSucursal = new ConsultasSucursal();
				consultasInventario = new ConsultasInventario();
				panelInventario = new PanelInventario();
				ctrlInventario = new CtrlInventario(consultasProducto,consultasSucursal,consultasInventario, panelInventario);
				consultasInventario.llenarTablaInventario(panelInventario.table);
				panelInventario.setSize(1300, 700);
				panelInventario.setLocation(0, 0);
				panelDinamico.removeAll();
				panelDinamico.add(panelInventario, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnInventario.setBackground(new Color(92, 147, 150));
				btnInventario.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnInventario.setBackground(new Color(12, 24, 40));
				btnInventario.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnInventario);
		
		JLabel icnInventario = new JLabel(getIcon("/recursos/iconoInventario.png", 26, 26));
		
		JLabel lblInventario = new JLabel("Inventario");
		lblInventario.setFont(fuenteBotones);
		lblInventario.setForeground(colorTitulos);
		GroupLayout gl_btnInventario = new GroupLayout(btnInventario);
		gl_btnInventario.setHorizontalGroup(gl_btnInventario.createParallelGroup(Alignment.LEADING).addGroup(gl_btnInventario.createSequentialGroup().addGap(48).addComponent(icnInventario).addGap(5).addComponent(lblInventario)));
		gl_btnInventario.setVerticalGroup(gl_btnInventario.createParallelGroup(Alignment.LEADING).addGroup(gl_btnInventario.createSequentialGroup().addGap(18).addComponent(icnInventario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnInventario.createSequentialGroup().addGap(5).addComponent(lblInventario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnInventario.setLayout(gl_btnInventario);
		
		JPanel btnEnvios = new JPanel();
		btnEnvios.setBackground(colorBotonesBg);
		btnEnvios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnEnvios.setBackground(new Color(12, 24, 40));
				btnEnvios.setForeground(Color.WHITE);
				
				panelEnvio = new PanelEnvio();
				enviar = new Enviar();
				listaCarga = new ListaCarga();
				statusEnvio = new StatusEnvio();
				consultasEnvio = new ConsultasEnvio();
				consultasVehiculo = new ConsultasVehiculo();
				consultasContrato = new ConsultasContrato();
				
				ctrlEnvio = new CtrlEnvio(enviar, listaCarga, statusEnvio, consultasEnvio, panelEnvio, consultasVehiculo, consultasContrato, vehiculo, contrato, persona);
				ctrlEnvio.llenarComboVehiculo();
				System.out.println("Antes de llamar metodo");
				ctrlEnvio.llenarComboEmpleado();
				System.out.println("Despues de llamar metodo");
				ctrlEnvio.llenarComboEnvio();
				consultasEnvio.llenarTabla(panelEnvio.table);

				//consultasEnvio.llenarTablaEstado(panelEnvio.table2);
				panelEnvio.setSize(1300, 700);
				panelEnvio.setLocation(0, 0);
				panelDinamico.removeAll();
				panelDinamico.add(panelEnvio, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnEnvios.setBackground(new Color(92, 147, 150));
				btnEnvios.setForeground(Color.WHITE);
			}
			public void mouseExited(MouseEvent arg0) {
				btnEnvios.setBackground(new Color(12, 24, 40));
				btnEnvios.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnEnvios);
		
		JLabel icnEnvios = new JLabel(getIcon("/recursos/iconoEnvios.png", 26, 26));
		
		JLabel lblEnvios = new JLabel("Envios");
		lblEnvios.setFont(fuenteBotones);
		lblEnvios.setForeground(colorTitulos);
		GroupLayout gl_btnEnvios = new GroupLayout(btnEnvios);
		gl_btnEnvios.setHorizontalGroup(gl_btnEnvios.createParallelGroup(Alignment.LEADING).addGroup(gl_btnEnvios.createSequentialGroup().addGap(64).addComponent(icnEnvios).addGap(5).addComponent(lblEnvios)));
		gl_btnEnvios.setVerticalGroup(gl_btnEnvios.createParallelGroup(Alignment.LEADING).addGroup(gl_btnEnvios.createSequentialGroup().addGap(18).addComponent(icnEnvios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnEnvios.createSequentialGroup().addGap(5).addComponent(lblEnvios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnEnvios.setLayout(gl_btnEnvios);
		
		JPanel btnSurtidos = new JPanel();
		btnSurtidos.setBackground(colorBotonesBg);
		btnSurtidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnSurtidos.setBackground(new Color(92, 147, 150));
				btnSurtidos.setForeground(Color.WHITE);
				surtido = new Surtido();
				consultasSurtido = new ConsultasSurtido();
				consultasProducto = new ConsultasProducto();
				consultasProveedor = new ConsultasProveedor();
				consultasSucursal = new ConsultasSucursal();
				panelSurtido = new PanelSurtido();
				ctrlSurtido = new CtrlSurtido(surtido, consultasSurtido, consultasProducto, consultasProveedor, consultasSucursal, panelSurtido);
				//consultasSucursal.llenarTabla("tienda", panelSucursales.table);
				ctrlSurtido.llenarComboSucursal();
				ctrlSurtido.llenarComboProducto();
				ctrlSurtido.llenarComboProveedor();
				//showPanel(panelSucursales);
				panelSurtido.setSize(1300, 700);
				panelSurtido.setLocation(0, 0);
				panelDinamico.removeAll();
				panelDinamico.add(panelSurtido, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSurtidos.setBackground(new Color(92, 147, 150));
				btnSurtidos.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnSurtidos.setBackground(new Color(12, 24, 40));
				btnSurtidos.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnSurtidos);
		
		JLabel icnSurtidos = new JLabel(getIcon("/recursos/iconoSurtidos.png", 26, 26));
		
		JLabel lblSurtidos = new JLabel("Surtidos");
		lblSurtidos.setFont(fuenteBotones);
		lblSurtidos.setForeground(colorTitulos);
		GroupLayout gl_btnSurtidos = new GroupLayout(btnSurtidos);
		gl_btnSurtidos.setHorizontalGroup(gl_btnSurtidos.createParallelGroup(Alignment.LEADING).addGroup(gl_btnSurtidos.createSequentialGroup().addGap(55).addComponent(icnSurtidos).addGap(5).addComponent(lblSurtidos)));
		gl_btnSurtidos.setVerticalGroup(gl_btnSurtidos.createParallelGroup(Alignment.LEADING).addGroup(gl_btnSurtidos.createSequentialGroup().addGap(18).addComponent(icnSurtidos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnSurtidos.createSequentialGroup().addGap(5).addComponent(lblSurtidos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnSurtidos.setLayout(gl_btnSurtidos);
		
		JPanel btnClientes = new JPanel();
		btnClientes.setBackground(colorBotonesBg);
		btnClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnClientes.setBackground(new Color(92, 147, 150));
				btnClientes.setForeground(Color.WHITE);
				
				cliente = new Cliente();
				persona = new Persona();
				consultasUbicacion = new ConsultasUbicacion();
				consultasCliente = new ConsultasCliente();
				panelCliente = new PanelCliente();
				ctrlCliente = new CtrlCliente(persona, cliente,consultasUbicacion, panelCliente, consultasCliente);
				consultasCliente.llenarTablaCliente( panelCliente.table);
				//showPanel(panelSucursales);
				panelCliente.setSize(1300, 700);
				panelCliente.setLocation(0, 0);
				panelDinamico.removeAll();
				panelDinamico.add(panelCliente, BorderLayout.CENTER);
				panelDinamico.revalidate();
				panelDinamico.repaint();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnClientes.setBackground(new Color(92, 147, 150));
				btnClientes.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				btnClientes.setBackground(new Color(12, 24, 40));
				btnClientes.setForeground(Color.WHITE);
			}
		});
		panelBotones.add(btnClientes);
		
		JLabel icnClientes = new JLabel(getIcon("/recursos/iconoClientes.png", 26, 26));
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setFont(fuenteBotones);
		lblClientes.setForeground(colorTitulos);
		GroupLayout gl_btnClientes = new GroupLayout(btnClientes);
		gl_btnClientes.setHorizontalGroup(gl_btnClientes.createParallelGroup(Alignment.LEADING).addGroup(gl_btnClientes.createSequentialGroup().addGap(58).addComponent(icnClientes).addGap(5).addComponent(lblClientes)));
		gl_btnClientes.setVerticalGroup(gl_btnClientes.createParallelGroup(Alignment.LEADING).addGroup(gl_btnClientes.createSequentialGroup().addGap(18).addComponent(icnClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(29)).addGroup(gl_btnClientes.createSequentialGroup().addGap(5).addComponent(lblClientes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(15)));
		btnClientes.setLayout(gl_btnClientes);
		contentPane.setLayout(gl_contentPane);
	}
	
	public Icon getIcon(String ruta, int width, int heigth){
		Icon miIcono = new ImageIcon(new ImageIcon(getClass().getResource(ruta)).getImage().getScaledInstance(width, heigth, 0));
		return miIcono;
	}
}

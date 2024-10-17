package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import consultas.ConsultasUbicacion;
import controlador.CtrlUbicacion;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Estado;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Ubicacion extends JFrame {

	private JPanel contentPane;
	public JTextField txtFieldEstado;
	public JTextField txtFieldCiudad;
	public JTextField txtFieldCP;
	public JTextField txtFieldColonia;
	public JButton btnRegistrarUbicacion;
	public JButton btnBuscarUbicacion;
	public JButton btnModificarUbicacion;
	public JButton btnEliminarUbicacion;
	private Estado estado;
	private Ciudad ciudad;
	private CodigoPostal codigo;
	private Colonia colonia;
	private ConsultasUbicacion consultasUbicacion;
	private CtrlUbicacion ctrlUbicacion;
	public JTextField txtFieldIdEst;
	public JTextField txtFieldIdCiu;
	public JTextField txtFieldIdColonia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ubicacion frame = new Ubicacion();
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
	public Ubicacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(124, 11, 61, 14);
		contentPane.add(lblUbicacin);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(51, 48, 87, 14);
		contentPane.add(lblEstado);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(51, 84, 46, 14);
		contentPane.add(lblCiudad);
		
		JLabel lblCdigoPostal = new JLabel("C\u00F3digo postal");
		lblCdigoPostal.setBounds(51, 120, 76, 14);
		contentPane.add(lblCdigoPostal);
		
		JLabel lblColonia = new JLabel("Colonia");
		lblColonia.setBounds(51, 159, 46, 14);
		contentPane.add(lblColonia);
		
		txtFieldEstado = new JTextField();
		txtFieldEstado.setBounds(130, 45, 111, 20);
		contentPane.add(txtFieldEstado);
		txtFieldEstado.setColumns(10);
		
		txtFieldCiudad = new JTextField();
		txtFieldCiudad.setBounds(130, 81, 111, 20);
		contentPane.add(txtFieldCiudad);
		txtFieldCiudad.setColumns(10);
		
		txtFieldCP = new JTextField();
		txtFieldCP.setBounds(130, 117, 111, 20);
		contentPane.add(txtFieldCP);
		txtFieldCP.setColumns(10);
		
		txtFieldColonia = new JTextField();
		txtFieldColonia.setBounds(130, 156, 111, 20);
		contentPane.add(txtFieldColonia);
		txtFieldColonia.setColumns(10);
		
		btnRegistrarUbicacion = new JButton("Registrar");
		btnRegistrarUbicacion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				estado = new Estado();
				ciudad = new Ciudad();
				codigo = new CodigoPostal();
				colonia = new Colonia();
				consultasUbicacion = new ConsultasUbicacion();
				ctrlUbicacion = new CtrlUbicacion(estado, ciudad, codigo, colonia, consultasUbicacion, Ubicacion.this);
			}
		});
		btnRegistrarUbicacion.setBounds(51, 203, 89, 23);
		contentPane.add(btnRegistrarUbicacion);
		
		btnBuscarUbicacion = new JButton("Buscar");
		btnBuscarUbicacion.setBounds(152, 203, 89, 23);
		contentPane.add(btnBuscarUbicacion);
		
		btnModificarUbicacion = new JButton("Modificar");
		btnModificarUbicacion.setBounds(49, 237, 89, 23);
		contentPane.add(btnModificarUbicacion);
		
		btnEliminarUbicacion = new JButton("Eliminar");
		btnEliminarUbicacion.setBounds(152, 237, 89, 23);
		contentPane.add(btnEliminarUbicacion);
		
		txtFieldIdEst = new JTextField();
		txtFieldIdEst.setBounds(247, 45, 41, 20);
		contentPane.add(txtFieldIdEst);
		txtFieldIdEst.setColumns(10);
		
		txtFieldIdCiu = new JTextField();
		txtFieldIdCiu.setBounds(247, 81, 41, 20);
		contentPane.add(txtFieldIdCiu);
		txtFieldIdCiu.setColumns(10);
		
		txtFieldIdColonia = new JTextField();
		txtFieldIdColonia.setBounds(247, 156, 41, 20);
		contentPane.add(txtFieldIdColonia);
		txtFieldIdColonia.setColumns(10);
	}
}

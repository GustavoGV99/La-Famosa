package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import consultas.ConsultasUbicacion;
import vista.Ubicacion;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Estado;

public class CtrlUbicacion implements ActionListener {
	private Estado estado;
	private Ciudad ciudad;
	private CodigoPostal codigo;
	private Colonia colonia;
	private ConsultasUbicacion consultasUbicacion;
	private Ubicacion ubicacion;

	public CtrlUbicacion(Estado estado, Ciudad ciudad, CodigoPostal codigo, Colonia colonia, ConsultasUbicacion consultasUbicacion, Ubicacion ubicacion) {
		this.estado = estado;
		this.ciudad = ciudad;
		this.codigo = codigo;
		this.colonia = colonia;
		this.consultasUbicacion = consultasUbicacion;
		this.ubicacion = ubicacion;
		
		this.ubicacion.btnRegistrarUbicacion.addActionListener(this);
		this.ubicacion.btnBuscarUbicacion.addActionListener(this);
		this.ubicacion.btnModificarUbicacion.addActionListener(this);
		this.ubicacion.btnEliminarUbicacion.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ubicacion.btnRegistrarUbicacion){
			estado.setId(Integer.parseInt(ubicacion.txtFieldIdEst.getText()));
			estado.setNombre(ubicacion.txtFieldEstado.getText());
			
			ciudad.setId(Integer.parseInt(ubicacion.txtFieldIdCiu.getText()));
			ciudad.setNombre(ubicacion.txtFieldCiudad.getText());
			ciudad.setCveEst(Integer.parseInt(ubicacion.txtFieldIdEst.getText()));
			
			codigo.setCodigo(Integer.parseInt(ubicacion.txtFieldCP.getText()));
			codigo.setCveCiu(Integer.parseInt(ubicacion.txtFieldIdCiu.getText()));
			
			colonia.setId(Integer.parseInt(ubicacion.txtFieldIdColonia.getText()));
			colonia.setNombre(ubicacion.txtFieldColonia.getText());
			colonia.setCodigoPostal(Integer.parseInt(ubicacion.txtFieldCP.getText()));
			
			try {
				if(consultasUbicacion.registrarUbicacion(estado, ciudad, codigo, colonia)){
					JOptionPane.showMessageDialog(null, "Ubicacion registrada");
					limpiarCajas();
				} else {
					System.err.println();
					JOptionPane.showMessageDialog(null, "Error al registrar la ubicación");
					limpiarCajas();
				}
			} catch (HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public void limpiarCajas(){
		ubicacion.txtFieldIdEst.setText(null);
		ubicacion.txtFieldEstado.setText(null);
		ubicacion.txtFieldIdCiu.setText(null);
		ubicacion.txtFieldCiudad.setText(null);
		ubicacion.txtFieldCP.setText(null);
		ubicacion.txtFieldIdColonia.setText(null);
		ubicacion.txtFieldColonia.setText(null);
	}

}

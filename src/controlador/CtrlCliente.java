package controlador;

import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;
import static utileria.DateAndTime.stringToTime;
import static utileria.DateAndTime.SQLToString;
import static utileria.DateAndTime.TimeToString;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Estado;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Persona;
import modelo.Cliente;
import vista.PanelCliente;
import consultas.ConsultasUbicacion;
import consultas.ConsultasCliente;

public class CtrlCliente implements ActionListener {
	private Persona persona;
	private Cliente cliente;
	private ConsultasUbicacion consultasUbicacion;
	private ConsultasCliente consultasCliente;
	private PanelCliente panelCliente;

	
	public CtrlCliente(Persona persona, Cliente cliente, ConsultasUbicacion consultasUbicacion, PanelCliente panelCliente, ConsultasCliente consultasCliente){
		this.persona = persona;
		this.cliente = cliente;
		this.consultasCliente = consultasCliente;
		this.consultasUbicacion = consultasUbicacion;
		this.panelCliente = panelCliente;
		
		this.panelCliente.btnRegistrar.addActionListener(this);
		this.panelCliente.btnBuscar.addActionListener(this);
		this.panelCliente.btnModificar.addActionListener(this);
		this.panelCliente.btnEliminar.addActionListener(this);
		this.panelCliente.btnLimpiar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelCliente.btnRegistrar){
			persona.setApellidoPaterno(panelCliente.txtFieldPaterno.getText());
			persona.setApellidoMaterno(panelCliente.txtFieldMaterno.getText());
			persona.setNombre(panelCliente.txtFieldNombre.getText());
			persona.setCalle(panelCliente.txtFieldCalle.getText());
			persona.setNumeroDomicilio(panelCliente.txtFieldNumero.getText());
			String orientacion = (String) panelCliente.comboBoxOrientacion.getSelectedItem();
			persona.setOrientacion(orientacion);
			persona.setEntreCalles(panelCliente.txtFieldEntreCalles.getText());
			persona.setMail(panelCliente.txtFieldCorreo.getText());
			String sexo = (String) panelCliente.comboBoxSexo.getSelectedItem();
			persona.setSexo(sexo);
			Date utilNacimiento = stringToUtil(panelCliente.txtFieldFechaNac.getText());
			persona.setFechaNacimiento(utilToSQL(utilNacimiento));
			persona.setEstadoCivil(panelCliente.txtFieldEdoCivil.getText());
			int claveColonia = ((Colonia) panelCliente.comboBoxColonia.getItemAt(panelCliente.comboBoxColonia.getSelectedIndex())).getId();
			persona.setColonia(claveColonia);
			persona.setTelefono(panelCliente.txtFieldTelefono.getText());
			
			Date utilInicio = stringToUtil(panelCliente.txtFieldFechaCli.getText());
			cliente.setFecha_cli(utilToSQL(utilInicio));
			
			try {
				if(consultasCliente.registrarCliente(persona, cliente)){
					JOptionPane.showMessageDialog(null, "Cliente Registrado");
					consultasCliente.llenarTablaCliente(panelCliente.table);
					limpiarCajas();
				} else {
					System.err.println();
					JOptionPane.showMessageDialog(null, "Error al Registrar Cliente");
					limpiarCajas();
				}
			} catch(HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if(e.getSource() == panelCliente.btnModificar){
			persona.setApellidoPaterno(panelCliente.txtFieldPaterno.getText());
			persona.setApellidoMaterno(panelCliente.txtFieldMaterno.getText());
			persona.setNombre(panelCliente.txtFieldNombre.getText());
			persona.setCalle(panelCliente.txtFieldCalle.getText());
			persona.setNumeroDomicilio(panelCliente.txtFieldNumero.getText());
			String orientacion = (String) panelCliente.comboBoxOrientacion.getSelectedItem();
			persona.setOrientacion(orientacion);
			persona.setEntreCalles(panelCliente.txtFieldEntreCalles.getText());
			persona.setMail(panelCliente.txtFieldCorreo.getText());
			String sexo = (String) panelCliente.comboBoxSexo.getSelectedItem();
			persona.setSexo(sexo);
			Date utilNacimiento = stringToUtil(panelCliente.txtFieldFechaNac.getText());
			persona.setFechaNacimiento(utilToSQL(utilNacimiento));
			persona.setEstadoCivil(panelCliente.txtFieldEdoCivil.getText());
			int claveColonia = ((Colonia) panelCliente.comboBoxColonia.getItemAt(panelCliente.comboBoxColonia.getSelectedIndex())).getId();
			persona.setColonia(claveColonia);
			persona.setTelefono(panelCliente.txtFieldTelefono.getText());
			
			Date utilInicio = stringToUtil(panelCliente.txtFieldFechaCli.getText());
			cliente.setFecha_cli(utilToSQL(utilInicio));
			
			try {
				if(consultasCliente.modificarCliente(persona, cliente)){
					JOptionPane.showMessageDialog(null, "Cliente Modificado");
					consultasCliente.llenarTablaCliente(panelCliente.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error al Modificar Cliente");
					limpiarCajas();
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == panelCliente.btnBuscar){
			cliente.setCve_cli(Integer.parseInt(panelCliente.txtFieldCve_Cliente.getText()));
			
			if(consultasCliente.buscarCliente(persona, cliente)){
				panelCliente.txtFieldIdPer.setText(String.valueOf(persona.getClave()));
				panelCliente.txtFieldPaterno.setText(persona.getApellidoPaterno());
				panelCliente.txtFieldMaterno.setText(persona.getApellidoMaterno());
				panelCliente.txtFieldNombre.setText(persona.getNombre());
				panelCliente.txtFieldCalle.setText(persona.getCalle());
				panelCliente.txtFieldNumero.setText(persona.getNumeroDomicilio());
				panelCliente.comboBoxOrientacion.setSelectedItem(persona.getOrientacion());
				panelCliente.txtFieldEntreCalles.setText(persona.getEntreCalles());
				panelCliente.txtFieldCorreo.setText(persona.getMail());
				panelCliente.comboBoxSexo.setSelectedItem(persona.getSexo());
				panelCliente.txtFieldFechaNac.setText(SQLToString(persona.getFechaNacimiento()));
				panelCliente.txtFieldEdoCivil.setText(persona.getEstadoCivil());
				panelCliente.comboBoxEstado.setSelectedItem(persona.getEstado());
				panelCliente.comboBoxCiudad.setSelectedItem(persona.getCiudad());
				panelCliente.comboBoxCP.setSelectedItem(persona.getCodigoPostal());
				panelCliente.comboBoxColonia.setSelectedItem(persona.getColonia());
				panelCliente.txtFieldTelefono.setText(persona.getTelefono());
				
				panelCliente.txtFieldFechaCli.setText(SQLToString(cliente.getFecha_cli()));
				
			} else {
				JOptionPane.showMessageDialog(null, "No esta Registrado el Cliente que intenta buscar");
				limpiarCajas();
			}
		}
		
		if(e.getSource() == panelCliente.btnEliminar){
			cliente.setCve_cli(Integer.parseInt(panelCliente.txtFieldCve_Cliente.getText()));

			try {
				if(consultasCliente.eliminarCliente(cliente)){
					JOptionPane.showMessageDialog(null, "Cliente Eliminado");
					consultasCliente.llenarTablaCliente(panelCliente.table);
					limpiarCajas();
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error al Eliminar Cliente");
				limpiarCajas();
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == panelCliente.btnLimpiar) {
			limpiarCajas();
		}
	}
	
	public void limpiarCajas(){
		panelCliente.txtFieldIdPer.setText(null);
		panelCliente.txtFieldCve_Cliente.setText(null);
		panelCliente.txtFieldPaterno.setText(null);
		panelCliente.txtFieldMaterno.setText(null);
		panelCliente.txtFieldNombre.setText(null);
		panelCliente.txtFieldCalle.setText(null);
		panelCliente.txtFieldNumero.setText(null);
		//panelContrato.txtFieldOrientacion.setText(null);
		panelCliente.txtFieldEntreCalles.setText(null);
		panelCliente.txtFieldTelefono.setText(null);
		panelCliente.txtFieldCorreo.setText(null);
		//panelContrato.txtFieldSexo.setText(null);
		panelCliente.txtFieldFechaNac.setText(null);
		panelCliente.txtFieldEdoCivil.setText(null);
		panelCliente.txtFieldFechaCli.setText(null);
	}

}

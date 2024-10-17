package controlador;

import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;
import static utileria.DateAndTime.stringToTime;
import static utileria.DateAndTime.SQLToString;
import static utileria.DateAndTime.TimeToString;
import static utileria.Validaciones.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Colonia;
import modelo.Contrato;
import modelo.Estado;
import modelo.Persona;
import modelo.Sucursal;
import vista.Dashboard;
import vista.PanelChecador;
import vista.PanelContrato;
import consultas.ConsultasContrato;
import consultas.ConsultasSucursal;
import consultas.ConsultasUbicacion;

public class CtrlContrato implements ActionListener {
	private Persona persona;
	private Contrato contrato;
	private ConsultasContrato consultasContrato;
	private ConsultasSucursal consultasSucursal;
	private ConsultasUbicacion consultasUbicacion;
	public PanelContrato panelContrato;
	private PanelChecador panelChecador;

	String err = "";

	public CtrlContrato(Persona persona, Contrato contrato, ConsultasContrato consultasContrato, ConsultasSucursal consultasSucursal, ConsultasUbicacion consultasUbicacion, PanelContrato panelContrato){
		this.persona = persona;
		this.contrato = contrato;
		this.consultasContrato = consultasContrato;
		this.consultasSucursal = consultasSucursal;
		this.consultasUbicacion = consultasUbicacion;
		this.panelContrato = panelContrato;

		this.panelContrato.btnRegistrar.addActionListener(this);
		this.panelContrato.btnBuscar.addActionListener(this);
		this.panelContrato.btnModificar.addActionListener(this);
		this.panelContrato.btnEliminar.addActionListener(this);
		this.panelContrato.btnChecador.addActionListener(this);
		this.panelContrato.btnRegresar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelContrato.btnRegistrar){
			try {
				persona.setApellidoPaterno(panelContrato.txtFieldPaterno.getText());
				persona.setApellidoMaterno(panelContrato.txtFieldMaterno.getText());
				persona.setNombre(panelContrato.txtFieldNombre.getText());
				persona.setCalle(panelContrato.txtFieldCalle.getText());
				persona.setNumeroDomicilio(panelContrato.txtFieldNumero.getText());
				String orientacion = (String) panelContrato.comboBoxOrientacion.getSelectedItem();
				persona.setOrientacion(orientacion);
				persona.setEntreCalles(panelContrato.txtFieldEntreCalles.getText());
				persona.setMail(panelContrato.txtFieldCorreo.getText());
				String sexo = (String) panelContrato.comboBoxSexo.getSelectedItem();
				persona.setSexo(sexo);
				if(!validarFecha(panelContrato.txtFieldFechaNac.getText())) {
					err += "* Error en fecha de nacimiento";
				} else {
					Date utilNacimiento = stringToUtil(panelContrato.txtFieldFechaNac.getText());
					persona.setFechaNacimiento(utilToSQL(utilNacimiento));
				}
				persona.setEstadoCivil(panelContrato.txtFieldEdoCivil.getText());
				int claveColonia = ((Colonia) panelContrato.comboBoxColonia.getItemAt(panelContrato.comboBoxColonia.getSelectedIndex())).getId();
				persona.setColonia(claveColonia);
				persona.setTelefono(panelContrato.txtFieldTelefono.getText());

				if(!validarFecha(panelContrato.txtFieldFechaInicio.getText())) {
					err += "* Error en fecha de inicio";
				} else {
					Date utilInicio = stringToUtil(panelContrato.txtFieldFechaInicio.getText());
					contrato.setFechaInicio(utilToSQL(utilInicio));
				}

				if(!validarFecha(panelContrato.txtFieldFechaFin.getText())) {
					err += "* Error en fecha fin";
				} else {
					Date utilFin = stringToUtil(panelContrato.txtFieldFechaFin.getText());
					contrato.setFechaFin(utilToSQL(utilFin));
				}
				contrato.setPuesto(panelContrato.txtFieldPuesto.getText());
				String sueldo = panelContrato.txtFieldSueldo.getText().replace("$", "");
				contrato.setSueldo(Float.parseFloat(sueldo));
				contrato.setPeriodoSueldo(panelContrato.txtFieldPeriodoSueldo.getText());
				contrato.setHoraEntrada(stringToTime(panelContrato.txtFieldHoraEntrada.getText()));
				contrato.setHoraSalida(stringToTime(panelContrato.txtFieldHoraSalida.getText()));
				contrato.setHoraInicioComida(stringToTime(panelContrato.txtFieldInicioComida.getText()));
				contrato.setHoraFinComida(stringToTime(panelContrato.txtFieldFinComida.getText()));
				int claveSucursal = ((Sucursal) panelContrato.comboBoxTienda.getItemAt(panelContrato.comboBoxTienda.getSelectedIndex())).getClave();
				contrato.setTienda(claveSucursal);

			} catch(Exception ex) {

			}
			try {
				if(consultasContrato.registrarContrato(persona, contrato)){
					JOptionPane.showMessageDialog(null, "Trabajador registrado");
					consultasContrato.llenarTablaContrato(panelContrato.table);
					limpiarCajas();
				} else {
					System.err.println();
					JOptionPane.showMessageDialog(null, "Error en los datos ingresados.");
				}
			} catch(HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

		if(e.getSource() == panelContrato.btnModificar){
			try {
				persona.setApellidoPaterno(panelContrato.txtFieldPaterno.getText());
				persona.setApellidoMaterno(panelContrato.txtFieldMaterno.getText());
				persona.setNombre(panelContrato.txtFieldNombre.getText());
				persona.setCalle(panelContrato.txtFieldCalle.getText());
				persona.setNumeroDomicilio(panelContrato.txtFieldNumero.getText());
				String orientacion = (String) panelContrato.comboBoxOrientacion.getSelectedItem();
				persona.setOrientacion(orientacion);
				persona.setEntreCalles(panelContrato.txtFieldEntreCalles.getText());
				persona.setMail(panelContrato.txtFieldCorreo.getText());
				String sexo = (String) panelContrato.comboBoxSexo.getSelectedItem();
				persona.setSexo(sexo);
				if(!validarFecha(panelContrato.txtFieldFechaNac.getText())) {
					err += "* Error en fecha de nacimiento";
				} else {
					Date utilNacimiento = stringToUtil(panelContrato.txtFieldFechaNac.getText());
					persona.setFechaNacimiento(utilToSQL(utilNacimiento));
				}
				persona.setEstadoCivil(panelContrato.txtFieldEdoCivil.getText());
				int claveColonia = ((Colonia) panelContrato.comboBoxColonia.getItemAt(panelContrato.comboBoxColonia.getSelectedIndex())).getId();
				persona.setColonia(claveColonia);
				persona.setTelefono(panelContrato.txtFieldTelefono.getText());

				if(!validarFecha(panelContrato.txtFieldFechaInicio.getText())) {
					err += "* Error en fecha de inicio";
				} else {
					Date utilInicio = stringToUtil(panelContrato.txtFieldFechaInicio.getText());
					contrato.setFechaInicio(utilToSQL(utilInicio));
				}

				if(!validarFecha(panelContrato.txtFieldFechaFin.getText())) {
					err += "* Error en fecha fin";
				} else {
					Date utilFin = stringToUtil(panelContrato.txtFieldFechaFin.getText());
					contrato.setFechaFin(utilToSQL(utilFin));
				}
				contrato.setPuesto(panelContrato.txtFieldPuesto.getText());
				String sueldo = panelContrato.txtFieldSueldo.getText().replace("$", "");
				contrato.setSueldo(Float.parseFloat(sueldo));
				contrato.setPeriodoSueldo(panelContrato.txtFieldPeriodoSueldo.getText());
				contrato.setHoraEntrada(stringToTime(panelContrato.txtFieldHoraEntrada.getText()));
				contrato.setHoraSalida(stringToTime(panelContrato.txtFieldHoraSalida.getText()));
				contrato.setHoraInicioComida(stringToTime(panelContrato.txtFieldInicioComida.getText()));
				contrato.setHoraFinComida(stringToTime(panelContrato.txtFieldFinComida.getText()));
				int claveSucursal = ((Sucursal) panelContrato.comboBoxTienda.getItemAt(panelContrato.comboBoxTienda.getSelectedIndex())).getClave();
				contrato.setTienda(claveSucursal);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No se ha ingresado ningún empleado para modificar.");
			}

			try {
				if(consultasContrato.modificarContrato(persona, contrato)){
					JOptionPane.showMessageDialog(null, "Trabajador modificado");
					consultasContrato.llenarTablaContrato(panelContrato.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error en los datos ingresados.");
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getSource() == panelContrato.btnBuscar){
			contrato.setApellidoPaterno(panelContrato.txtFieldPaterno.getText());
			if(consultasContrato.buscarContrato(persona, contrato)){
				try {
					panelContrato.txtFieldPaterno.setText(persona.getApellidoPaterno());
					panelContrato.txtFieldMaterno.setText(persona.getApellidoMaterno());
					panelContrato.txtFieldNombre.setText(persona.getNombre());
					panelContrato.txtFieldCalle.setText(persona.getCalle());
					panelContrato.txtFieldNumero.setText(persona.getNumeroDomicilio());
					panelContrato.comboBoxOrientacion.setSelectedItem(persona.getOrientacion());
					panelContrato.txtFieldEntreCalles.setText(persona.getEntreCalles());
					panelContrato.txtFieldCorreo.setText(persona.getMail());
					panelContrato.comboBoxSexo.setSelectedItem(persona.getSexo());
					panelContrato.txtFieldFechaNac.setForeground(Color.BLACK);
					panelContrato.txtFieldFechaNac.setText(SQLToString(persona.getFechaNacimiento()));
					panelContrato.txtFieldEdoCivil.setText(persona.getEstadoCivil());

					panelContrato.txtFieldTelefono.setText(persona.getTelefono());
					panelContrato.comboBoxEstado.setSelectedIndex(persona.getEstado() - 1); // Cambiar a String
					//panelContrato.comboBoxCiudad.setSelectedIndex(persona.getCiudad());
					panelContrato.comboBoxCP.setSelectedItem(persona.getCodigoPostal());
					panelContrato.comboBoxColonia.setSelectedItem(persona.getColonia());

					panelContrato.txtFieldFechaInicio.setForeground(Color.BLACK);
					panelContrato.txtFieldFechaInicio.setText(SQLToString(contrato.getFechaInicio()));
					panelContrato.txtFieldFechaFin.setForeground(Color.BLACK);
					panelContrato.txtFieldFechaFin.setText(SQLToString(contrato.getFechaFin()));
					panelContrato.txtFieldPuesto.setText(contrato.getPuesto());
					panelContrato.txtFieldSueldo.setText(String.valueOf("$"+contrato.getSueldo()));
					panelContrato.txtFieldPeriodoSueldo.setText(contrato.getPeriodoSueldo());
					panelContrato.txtFieldHoraEntrada.setText(TimeToString(contrato.getHoraEntrada()));
					panelContrato.txtFieldHoraSalida.setText(TimeToString(contrato.getHoraSalida()));
					panelContrato.txtFieldInicioComida.setText(TimeToString(contrato.getHoraInicioComida()));
					panelContrato.txtFieldFinComida.setText(TimeToString(contrato.getHoraFinComida()));
					panelContrato.comboBoxTienda.setSelectedItem(contrato.getClave());
				} catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "No se ingresó ningún dato para realizar la búsqueda.");
				}

			} else {
				JOptionPane.showMessageDialog(null, "El trabajador que intenta buscar no se encuentra registrado");
				limpiarCajas();
			}
		}

		if(e.getSource() == panelContrato.btnEliminar){
			contrato.setApellidoPaterno(panelContrato.txtFieldPaterno.getText());
			if(panelContrato.txtFieldPaterno.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "No se ingresó ningún dato para poder eliminar al trabajador.");
			} else {
				try {
					if(consultasContrato.eliminarContrato(contrato)){
						JOptionPane.showMessageDialog(null, "Contrato eliminado");
						consultasContrato.llenarTablaContrato(panelContrato.table);
						limpiarCajas();
					} else {
						JOptionPane.showMessageDialog(null, "No se pueden eliminar los datos del empleado porque estan siendo utilizados.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "No se pueden eliminar los datos del empleado porque estan siendo utilizados.");
					limpiarCajas();
					e1.printStackTrace();
				}
			}
		}

		if(e.getSource() == panelContrato.btnRegresar) {
			limpiarCajas();
		}
	}

	public void llenarComboSucursal(){
		ArrayList<Sucursal> listaSucursales = consultasSucursal.getSucursales();
		panelContrato.comboBoxTienda.removeAllItems();

		for(int i = 0; i < listaSucursales.size(); i++){
			panelContrato.comboBoxTienda.addItem(new Sucursal(listaSucursales.get(i).getClave(), listaSucursales.get(i).getCalle()));
		}
	}

	public void limpiarCajas(){
		panelContrato.txtFieldPaterno.setText(null);
		panelContrato.txtFieldMaterno.setText(null);
		panelContrato.txtFieldNombre.setText(null);
		panelContrato.txtFieldCalle.setText(null);
		panelContrato.txtFieldNumero.setText(null);
		panelContrato.txtFieldEntreCalles.setText(null);
		panelContrato.txtFieldTelefono.setText(null);
		panelContrato.txtFieldCorreo.setText(null);
		panelContrato.txtFieldFechaNac.setText(null);
		panelContrato.txtFieldEdoCivil.setText(null);

		panelContrato.txtFieldFechaInicio.setText(null);
		panelContrato.txtFieldFechaFin.setText(null);
		panelContrato.txtFieldPuesto.setText(null);
		panelContrato.txtFieldSueldo.setText("$");
		panelContrato.txtFieldPeriodoSueldo.setText(null);
		panelContrato.txtFieldHoraEntrada.setText(null);
		panelContrato.txtFieldHoraSalida.setText(null);
		panelContrato.txtFieldInicioComida.setText(null);
		panelContrato.txtFieldFinComida.setText(null);
	}

}

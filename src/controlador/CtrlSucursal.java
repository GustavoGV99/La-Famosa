package controlador;

import static utileria.DateAndTime.SQLToString;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import consultas.ConsultasSucursal;
import modelo.Sucursal;
import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Estado;
import vista.PanelSucursal;

import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;
import static utileria.Validaciones.*;
import static utileria.DateAndTime.stringToTime;

public class CtrlSucursal implements ActionListener {
	private Sucursal sucursal;
	private ConsultasSucursal consultasSucursal;
	private PanelSucursal panelSucursal;

	public CtrlSucursal(Sucursal sucursal, ConsultasSucursal consultasSucursal, PanelSucursal panelSucursal){
		this.sucursal = sucursal;
		this.consultasSucursal = consultasSucursal;
		this.panelSucursal = panelSucursal;

		this.panelSucursal.btnRegistrar.addActionListener((ActionListener) this);
		this.panelSucursal.btnModificar.addActionListener((ActionListener) this);
		this.panelSucursal.btnEliminar.addActionListener((ActionListener) this);
		this.panelSucursal.btnBuscar.addActionListener((ActionListener) this);
		this.panelSucursal.btnLimpiar.addActionListener((ActionListener) this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelSucursal.btnRegistrar){
			try {
				Date dateUtil = stringToUtil(panelSucursal.txtFieldApertura.getText());
				sucursal.setFechaApertura(utilToSQL(dateUtil));
				sucursal.setCalle(panelSucursal.txtFieldCalle.getText());
				sucursal.setNumeroDomicilio(panelSucursal.txtFieldNumero.getText());
				sucursal.setEntreCalles(panelSucursal.txtFieldEntreCalles.getText());
				String orientacion = (String) panelSucursal.comboBoxOrientacion.getSelectedItem();
				sucursal.setOrientacion(orientacion);
				if(panelSucursal.txtFieldTelefono.getText().length() < 10) {
					sucursal.setTelefono("");
				} else {
					sucursal.setTelefono(panelSucursal.txtFieldTelefono.getText());
				}
				sucursal.setMail(panelSucursal.txtFieldCorreo.getText());
				int claveColonia = ((Colonia) panelSucursal.comboBoxColonia.getItemAt(panelSucursal.comboBoxColonia.getSelectedIndex())).getId();
				sucursal.setCveColonia(claveColonia);

			} catch(Exception ex) {	
			}
			if(consultasSucursal.registrar(sucursal)){
				JOptionPane.showMessageDialog(null, "Sucursal registrada");
				consultasSucursal.llenarTabla(panelSucursal.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor verifiquelos.");
			}
		}

		if(e.getSource() == panelSucursal.btnModificar){
			try {
				sucursal.setClave(Integer.parseInt(panelSucursal.txtFieldCveSuc.getText()));
				Date dateUtil = stringToUtil(panelSucursal.txtFieldApertura.getText());
				sucursal.setFechaApertura(utilToSQL(dateUtil));
				sucursal.setCalle(panelSucursal.txtFieldCalle.getText());
				sucursal.setNumeroDomicilio(panelSucursal.txtFieldNumero.getText());
				sucursal.setEntreCalles(panelSucursal.txtFieldEntreCalles.getText());
				String orientacion = (String) panelSucursal.comboBoxOrientacion.getSelectedItem();
				sucursal.setOrientacion(orientacion);
				if(panelSucursal.txtFieldTelefono.getText().length() < 10) {
					sucursal.setTelefono("");
				} else {
					sucursal.setTelefono(panelSucursal.txtFieldTelefono.getText());
				}
				sucursal.setMail(panelSucursal.txtFieldCorreo.getText());
				int claveColonia = ((Colonia) panelSucursal.comboBoxColonia.getItemAt(panelSucursal.comboBoxColonia.getSelectedIndex())).getId();
				sucursal.setCveColonia(claveColonia);

			} catch(Exception ex) {

			}
			if(consultasSucursal.modificar(sucursal)){
				JOptionPane.showMessageDialog(null, "Sucursal modificada");
				consultasSucursal.llenarTabla(panelSucursal.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Datos incorrectos, por favor verifiquelos.");
			}
		}

		if(e.getSource() == panelSucursal.btnEliminar){
			sucursal.setClave(Integer.parseInt(panelSucursal.txtFieldCveSuc.getText()));
			try {
				if(consultasSucursal.eliminar(sucursal)){
					JOptionPane.showMessageDialog(null, "Sucursal eliminada");
					consultasSucursal.llenarTabla(panelSucursal.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "No es posible eliminar los datos de la sucursal porque están siendo utilizados.");
					limpiarCajas();
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No es posible eliminar los datos de la sucursal porque están siendo utilizados.");
				limpiarCajas();
			}

		}

		if(e.getSource() == panelSucursal.btnBuscar){
			if(panelSucursal.txtFieldCveSuc.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "No ha ingresado ninguna clave para efectuar la búsqueda.");
			} else {
				sucursal.setClave(Integer.parseInt(panelSucursal.txtFieldCveSuc.getText()));
				if(consultasSucursal.buscar(sucursal)){
					try{
						panelSucursal.txtFieldApertura.setForeground(Color.BLACK);
						panelSucursal.txtFieldApertura.setText(SQLToString(sucursal.getFechaApertura()));
						panelSucursal.txtFieldCalle.setText(sucursal.getCalle());
						panelSucursal.txtFieldNumero.setText(sucursal.getNumeroDomicilio());
						panelSucursal.txtFieldEntreCalles.setText(sucursal.getEntreCalles());
						panelSucursal.comboBoxOrientacion.setSelectedItem((sucursal.getOrientacion()));
						panelSucursal.txtFieldTelefono.setText(sucursal.getTelefono());
						panelSucursal.txtFieldCorreo.setText(sucursal.getMail());
						panelSucursal.comboBoxEstado.setSelectedIndex(sucursal.getClaveEst());
						panelSucursal.comboBoxCiudad.setSelectedIndex(sucursal.getClaveCiu());
						panelSucursal.comboBoxCP.setSelectedItem(sucursal.getCodigoPostal());
						panelSucursal.comboBoxColonia.setSelectedItem(sucursal.getCveColonia());
					} catch(Exception ex) {

					}
				} else {
					JOptionPane.showMessageDialog(null, "La sucursal que intenta buscar no se encuentra registrada.");
					limpiarCajas();
				}
			}
		}
		
		if(e.getSource() == panelSucursal.btnLimpiar){
			limpiarCajas();
		}

	}

	public void limpiarCajas(){
		panelSucursal.txtFieldCveSuc.setText(null);
		panelSucursal.txtFieldApertura.setText(null);
		panelSucursal.txtFieldCalle.setText(null);
		panelSucursal.txtFieldNumero.setText(null);
		panelSucursal.txtFieldEntreCalles.setText(null);
		panelSucursal.txtFieldTelefono.setText(null);
		panelSucursal.txtFieldCorreo.setText(null);
	}

}

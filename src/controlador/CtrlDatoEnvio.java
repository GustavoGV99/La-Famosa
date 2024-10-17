package controlador;

import static utileria.DateAndTime.SQLToString;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import consultas.ConsultasDatoEnvio;
import consultas.ConsultasVenta;
import consultas.ConsultasUbicacion;
import modelo.DatoEnvio;
import modelo.Sucursal;
import modelo.Colonia;
import modelo.Ticket;
import vista.PanelDatoEnvio;
import vista.PanelVenta;
import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;
import static utileria.DateAndTime.stringToTime;

public class CtrlDatoEnvio implements ActionListener {
	private DatoEnvio datoEnvio;
	private ConsultasDatoEnvio consultasDatoEnvio;
	private PanelDatoEnvio panelDatoEnvio;
	private PanelVenta panelVenta;
	private ConsultasUbicacion consultasUbicacion;
	private ConsultasVenta consultasVenta;
	
	public CtrlDatoEnvio(DatoEnvio datoEnvio, ConsultasDatoEnvio consultasDatoEnvio, PanelDatoEnvio panelDatoEnvio, ConsultasUbicacion consultasUbicacion, ConsultasVenta consultasVenta, PanelVenta panelVenta){
		this.datoEnvio = datoEnvio;
		this.consultasDatoEnvio = consultasDatoEnvio;
		this.panelDatoEnvio = panelDatoEnvio;
		this.consultasVenta = consultasVenta;
		this.consultasUbicacion = consultasUbicacion;
		this.panelVenta = panelVenta;
		
		this.panelDatoEnvio.btnRegistrar.addActionListener((ActionListener) this);
		this.panelDatoEnvio.btnModificar.addActionListener((ActionListener) this);
		this.panelDatoEnvio.btnEliminar.addActionListener((ActionListener) this);
		this.panelDatoEnvio.btnBuscar.addActionListener((ActionListener) this);
		this.panelDatoEnvio.btnRegresar.addActionListener(this);
		this.panelDatoEnvio.btnLimpiar.addActionListener((ActionListener)this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelDatoEnvio.btnRegistrar){
			datoEnvio.setCalle_datenv(panelDatoEnvio.txtFieldCalleEnvio.getText());
			datoEnvio.setEntrecalles_datenv(panelDatoEnvio.txtFieldEntreCallesEnv.getText());
			datoEnvio.setNum_datenv(panelDatoEnvio.txtFieldNumeroEnv.getText());
			String orientacion = (String) panelDatoEnvio.comboBoxOrientacion.getSelectedItem();
			datoEnvio.setOrientacion_datenv(orientacion);
			int colonia = ((Colonia) panelDatoEnvio.comboBoxColonia.getItemAt(panelDatoEnvio.comboBoxColonia.getSelectedIndex())).getId();
			datoEnvio.setCve_col(colonia);
			int ticket = ((Ticket) panelDatoEnvio.comboBoxFolioTic.getItemAt(panelDatoEnvio.comboBoxFolioTic.getSelectedIndex())).getFolio();
			datoEnvio.setFolio_tic(ticket);
			
			if(consultasDatoEnvio.registrarDatoEnvio(datoEnvio)){
				JOptionPane.showMessageDialog(null, "Datos de Envio Registrados");
				consultasDatoEnvio.llenarTablaDatoEnvio(panelDatoEnvio.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Error al Registrar Datos de Envio");
				limpiarCajas();
			}
		}
		
		if(e.getSource() == panelDatoEnvio.btnModificar){
			datoEnvio.setCve_datenv(Integer.parseInt(panelDatoEnvio.txtField.getText()));
			datoEnvio.setEntrecalles_datenv(panelDatoEnvio.txtFieldEntreCallesEnv.getText());
			datoEnvio.setCalle_datenv(panelDatoEnvio.txtFieldCalleEnvio.getText());
			datoEnvio.setNum_datenv(panelDatoEnvio.txtFieldNumeroEnv.getText());
			int colonia = ((Colonia) panelDatoEnvio.comboBoxColonia.getItemAt(panelDatoEnvio.comboBoxColonia.getSelectedIndex())).getId();
			datoEnvio.setCve_col(colonia);
			int ticket = ((Ticket) panelDatoEnvio.comboBoxFolioTic.getItemAt(panelDatoEnvio.comboBoxFolioTic.getSelectedIndex())).getFolio();
			datoEnvio.setFolio(ticket);
			String orientacion = (String) panelDatoEnvio.comboBoxOrientacion.getSelectedItem();
			datoEnvio.setOrientacion_datenv(orientacion);
			
			if(consultasDatoEnvio.modificarDatoEnvio(datoEnvio)){
				JOptionPane.showMessageDialog(null, "Datos de Envio Modificados");
				consultasDatoEnvio.llenarTablaDatoEnvio(panelDatoEnvio.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Error al Modificar Datos de Envio");
				limpiarCajas();
			}
		}
		
		if(e.getSource() == panelDatoEnvio.btnEliminar){
			datoEnvio.setFolio(Integer.parseInt(panelDatoEnvio.txtField.getText()));
			
			if(consultasDatoEnvio.eliminar(datoEnvio)){
				JOptionPane.showMessageDialog(null, "Datos de Envio Eliminados");
				consultasDatoEnvio.llenarTablaDatoEnvio(panelDatoEnvio.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Error al Eliminar Datos de Envio");
				limpiarCajas();
			}
		}
		
		if(e.getSource() == panelDatoEnvio.btnBuscar){
			datoEnvio.setCve_datenv(Integer.parseInt(panelDatoEnvio.txtField.getText()));

			if(consultasDatoEnvio.buscarDatoEnvio(datoEnvio)){
				panelDatoEnvio.txtFieldEntreCallesEnv.setText(datoEnvio.getEntrecalles_datenv());
				panelDatoEnvio.txtFieldCalleEnvio.setText(datoEnvio.getCalle_datenv());
				panelDatoEnvio.txtFieldNumeroEnv.setText(datoEnvio.getNum_datenv());
				panelDatoEnvio.comboBoxColonia.setSelectedItem((datoEnvio.getCve_col()));
				panelDatoEnvio.comboBoxFolioTic.setSelectedItem((datoEnvio.getFolio()));
				panelDatoEnvio.comboBoxOrientacion.setSelectedItem((datoEnvio.getOrientacion_datenv()));
				
			} else {
				JOptionPane.showMessageDialog(null, "No se Encunetran los Datos de Envio");
				limpiarCajas();
			}
		}
		if(e.getSource() == panelDatoEnvio.btnRegresar) {
		}
		if(e.getSource() == panelDatoEnvio.btnLimpiar){
			limpiarCajas();
		}
	}
	
	public void llenarComboColonia(){
		ArrayList<Colonia> listaColonia = consultasUbicacion.getColonia();
		panelDatoEnvio.comboBoxColonia.removeAllItems();
		
		for(int i = 0; i < listaColonia.size(); i++){
			panelDatoEnvio.comboBoxColonia.addItem(new Colonia(listaColonia.get(i).getId(), listaColonia.get(i).getNombre()));
		}
	}
	
	public void llenarComboTicket(){
		ArrayList<Ticket> listaVentas = consultasVenta.getVentas();
		panelDatoEnvio.comboBoxFolioTic.removeAllItems();
		
		for(int i = 0; i < listaVentas.size(); i++){
			panelDatoEnvio.comboBoxFolioTic.addItem(new Ticket(listaVentas.get(i).getFolio()));
		}
	}


	
	public void limpiarCajas(){
		panelDatoEnvio.txtField.setText(null);
		panelDatoEnvio.txtFieldCalleEnvio.setText(null);
		panelDatoEnvio.txtFieldNumeroEnv.setText(null);
		panelDatoEnvio.txtFieldEntreCallesEnv.setText(null);
//		panelDatoEnvio.txtFieldColonia.setText(null);
//		panelDatoEnvio.txtFieldTicket.setText(null);
		

	}

}

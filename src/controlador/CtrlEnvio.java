package controlador;

import static utileria.DateAndTime.SQLToString;
import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import consultas.ConsultasContrato;
import consultas.ConsultasEnvio;
import consultas.ConsultasVehiculo;
import modelo.Contrato;
import modelo.DatoEnvio;
import modelo.Enviar;
import modelo.ListaCarga;
import modelo.Persona;
import modelo.StatusEnvio;
import modelo.Sucursal;
import modelo.Vehiculo;
import vista.PanelEnvio;

public class CtrlEnvio implements ActionListener{
	
	private Enviar enviar;
	private ListaCarga listaCarga;
	private StatusEnvio statusEnvio;
	private Contrato contrato;
	private Vehiculo vehiculo;
	private Persona persona;
	private ConsultasEnvio consultasEnvio;
	private ConsultasVehiculo consultasVehiculo;
	private ConsultasContrato consultasContrato;
	public PanelEnvio panelEnvio;
	
	public CtrlEnvio(Enviar enviar, ListaCarga listaCarga, StatusEnvio statusEnvio, ConsultasEnvio consultasEnvio, PanelEnvio panelEnvio, ConsultasVehiculo consultasVehiculo, ConsultasContrato consultasContrato, Vehiculo vehiculo, Contrato contrato, Persona persona) {
		
		this.enviar = enviar;
		this.listaCarga = listaCarga;
		this.statusEnvio = statusEnvio;
		this.contrato = contrato;
		this.vehiculo = vehiculo;
		this.persona = persona;
		
		this.consultasContrato = consultasContrato;
		this.consultasVehiculo = consultasVehiculo;
		this.consultasEnvio = consultasEnvio;
		this.panelEnvio = panelEnvio;
		
		this.panelEnvio.btnRegistrar.addActionListener(this);
		this.panelEnvio.btnBuscar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panelEnvio.btnRegistrar) {
			
			
			int nsVehiculo = ((Vehiculo) panelEnvio.comboBoxNsvehiculo.getItemAt(panelEnvio.comboBoxNsvehiculo.getSelectedIndex())).getNs_veh();
			enviar.setNs_veh(nsVehiculo);
			
			int clavePersona = ((Persona) panelEnvio.comboBoxFolioContrato.getItemAt(panelEnvio.comboBoxFolioContrato.getSelectedIndex())).getClave();
			System.out.println(clavePersona + "desde ctrl");
			int folio = consultasEnvio.getFolioContrato(clavePersona);
			enviar.setFolio_cont(folio);
			
			int cveDatEnv = ((DatoEnvio) panelEnvio.comboBoxDatoEnvio.getItemAt(panelEnvio.comboBoxDatoEnvio.getSelectedIndex())).getCve_datenv();
			listaCarga.setCve_datenv(cveDatEnv);
			
			
			Date utilFecha = stringToUtil(panelEnvio.txtFechaStatus.getText());
			statusEnvio.setFecha_staen(utilToSQL(utilFecha));
			
			
			String status = (String) panelEnvio.comboBoxStatusEnvio.getSelectedItem();
			statusEnvio.setStatus_staen(status);
			
			try {
				if(consultasEnvio.registrar(enviar, listaCarga, statusEnvio)){
					JOptionPane.showMessageDialog(null, "Envio listado Registrado");
					consultasEnvio.llenarTabla(panelEnvio.table);
					limpiarCajas();
				} else {
					System.err.println();
					System.out.println("Dentro de else");
					JOptionPane.showMessageDialog(null, "Error al listar envio");
					limpiarCajas();
				}
			} catch(HeadlessException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	if (e.getSource() == panelEnvio.btnBuscar) {
		
		statusEnvio.setNum_staen((Integer.parseInt(panelEnvio.textFieldClaveEnviar.getText())));
		
		if (consultasEnvio.buscar(enviar, listaCarga, statusEnvio) ) {
									
			panelEnvio.comboBoxNsvehiculo.setSelectedItem(enviar.getNs_veh());
			panelEnvio.comboBoxFolioContrato.setSelectedItem(enviar.getFolio_cont());
						
			//panelEnvio.comboBoxDatoEnvio.setSelectedItem(listaCarga.getCve_datenv());
			
			panelEnvio.txtFechaStatus.setText(SQLToString(statusEnvio.getFecha_staen()));
			panelEnvio.comboBoxStatusEnvio.setSelectedItem(statusEnvio.getStatus_staen());
			
		} else {
			JOptionPane.showMessageDialog(null, "Error al buscar esta envio");
			limpiarCajas();
		} 
		
	}
	
	}
	public void llenarComboVehiculo(){
		ArrayList<Vehiculo> listaVehiculos = consultasVehiculo.getVehiculos();
		panelEnvio.comboBoxNsvehiculo.removeAllItems();
		
		for(int i = 0; i < listaVehiculos.size(); i++){
			panelEnvio.comboBoxNsvehiculo.addItem(new Vehiculo(listaVehiculos.get(i).getNs_veh(), listaVehiculos.get(i).getMarca()));
		}
	} 
	
	public void llenarComboEmpleado(){
		System.out.println("Entrada a llenarComboContrato");
		ArrayList<Persona> listaChofer = consultasContrato.getChofer();
		panelEnvio.comboBoxFolioContrato.removeAllItems();
		
		for(int i = 0; i < listaChofer.size(); i++){
			//for(int j = 0; j < listaContratos.size(); j++){
			panelEnvio.comboBoxFolioContrato.addItem(new Persona (listaChofer.get(i).getClave(), listaChofer.get(i).getApellidoPaterno(), listaChofer.get(i).getNombre()));
			//panelEnvio.comboBoxFolioContrato.addItem(new Persona (listaContratos.get(j).getCve_per(), listaContratos.get(j).getNombre()));
			
			//}
		}
		System.out.println("Salida de metodo llenarComboContrato");
	}
	
	public void llenarComboEnvio() {
		ArrayList<DatoEnvio> listaDatos = consultasEnvio.getDatoEnvio();
		panelEnvio.comboBoxDatoEnvio.removeAllItems();
		
		for (int i = 0; i < listaDatos.size(); i++) {
			panelEnvio.comboBoxDatoEnvio.addItem(new DatoEnvio (listaDatos.get(i).getCve_datenv(), listaDatos.get(i).getNum_datenv(), listaDatos.get(i).getCalle_datenv(), listaDatos.get(i).getOrientacion_datenv()));
		}
	}
	
	
	public void limpiarCajas() {
	panelEnvio.txtFechaStatus.setText(null);
	//panelEnvio.txtStatusEnvio.setText(null);
	
	}
	
}
	
	
/*if(e.getSource() == panelEnvio.btnModificar){

int nsVehiculo = ((Vehiculo) panelEnvio.comboBoxNsvehiculo.getItemAt(panelEnvio.comboBoxNsvehiculo.getSelectedIndex())).getNs_veh();
enviar.setNs_veh(nsVehiculo);

int clavePersona = ((Persona) panelEnvio.comboBoxFolioContrato.getItemAt(panelEnvio.comboBoxFolioContrato.getSelectedIndex())).getClave();
System.out.println(clavePersona + "desde ctrl");
int folio = consultasEnvio.getFolioContrato(clavePersona);
enviar.setFolio_cont(folio);

int cveDatEnv = ((DatoEnvio) panelEnvio.comboBoxDatoEnvio.getItemAt(panelEnvio.comboBoxDatoEnvio.getSelectedIndex())).getCve_datenv();
listaCarga.setCve_datenv(cveDatEnv);

Date utilFecha = stringToUtil(panelEnvio.txtFechaStatus.getText());
statusEnvio.setFecha_staen(utilToSQL(utilFecha));
//statusEnvio.setStatus_staen(panelEnvio.txtStatusEnvio.getText());

try {
	if(consultasEnvio.modificar(enviar, listaCarga, statusEnvio)){
		JOptionPane.showMessageDialog(null, "Lista de Envio modificada");
		consultasEnvio.llenarTabla(panelEnvio.table);

		limpiarCajas();
	} else {
		JOptionPane.showMessageDialog(null, "Error al modificar la lista");
		limpiarCajas();
	}
} catch (HeadlessException | SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
}*/

/* if(e.getSource() == panelEnvio.btnEliminar){
statusEnvio.setNum_staen(Integer.parseInt(panelEnvio.textFieldClaveEnviar.getText()));
try {
	if(consultasEnvio.eliminar(statusEnvio) ){
		JOptionPane.showMessageDialog(null, "Estado del envio eliminado");
		consultasEnvio.llenarTabla(panelEnvio.table);
		limpiarCajas();
	}
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	JOptionPane.showMessageDialog(null, "Error al eliminar el envio");
	limpiarCajas();
	e1.printStackTrace();
}
} */
	



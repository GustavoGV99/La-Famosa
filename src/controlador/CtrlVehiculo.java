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

import consultas.ConsultasVehiculo;
import modelo.Placas;
import modelo.Status;
import modelo.Sucursal;
import modelo.Vehiculo;
import vista.PanelVehiculo;


public class CtrlVehiculo implements ActionListener{
	
	private Vehiculo vehiculo;
	private Placas placas;
	private Status status;
	private ConsultasVehiculo consultasVehiculo;
	public PanelVehiculo panelVehiculo;
	
	public CtrlVehiculo(Vehiculo vehiculo,Placas placas,Status status,ConsultasVehiculo consultasVehiculo, PanelVehiculo panelVehiculo) {
		
		this.vehiculo = vehiculo;
		this.placas = placas;
		this.status = status;
		this.consultasVehiculo = consultasVehiculo;
		this.panelVehiculo = panelVehiculo;
		
		this.panelVehiculo.btnRegistrar.addActionListener(this);
		this.panelVehiculo.btnBuscar.addActionListener(this);
		this.panelVehiculo.btnModificar.addActionListener(this);
		this.panelVehiculo.btnEliminar.addActionListener(this);
		this.panelVehiculo.btnLimpiar.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panelVehiculo.btnRegistrar) {
			vehiculo.setModelo(panelVehiculo.txtModelo.getText());
			vehiculo.setMarca(panelVehiculo.txtMarca.getText());
			vehiculo.setColor(panelVehiculo.txtColor.getText());
			vehiculo.setCantPuertas(Integer.parseInt(panelVehiculo.txtCanPuertas.getText()));
			Date utilFecha = stringToUtil(panelVehiculo.txtAnio.getText());
			vehiculo.setFechavehiculo(utilToSQL(utilFecha));
			
			
			vehiculo.setPreCompra(Float.parseFloat(panelVehiculo.txtPrecioCompra.getText()));
			vehiculo.setTipoVeh(panelVehiculo.txtTipoVeh.getText());
			int claveSucursal = ((Sucursal) panelVehiculo.comboBoxTienda.getItemAt(panelVehiculo.comboBoxTienda.getSelectedIndex())).getClave();
			vehiculo.setCve_tie(claveSucursal);
			
			placas.setNum_pla(panelVehiculo.txtPlacas.getText());
			status.setStatus_sta(panelVehiculo.txtEstadoVehiculo.getText());

				try {
					if(consultasVehiculo.registrar(vehiculo, placas, status)){
						JOptionPane.showMessageDialog(null, "Vehiculo registrado");
						consultasVehiculo.llenarTabla(panelVehiculo.table);
						//consultasVehiculo.llenarTablaStatus(panelVehiculo.tableSTATUS);
						limpiarCajas();
					} else {
						System.err.println();
						System.out.println("Dentro de else");
						JOptionPane.showMessageDialog(null, "Error al registrar vehiculo");
						limpiarCajas();
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
		
		if(e.getSource() == panelVehiculo.btnModificar){
			
			vehiculo.setModelo(panelVehiculo.txtModelo.getText());
			vehiculo.setMarca(panelVehiculo.txtMarca.getText());
			vehiculo.setColor(panelVehiculo.txtColor.getText());
			vehiculo.setCantPuertas(Integer.parseInt(panelVehiculo.txtCanPuertas.getText()));
			Date utilFecha = stringToUtil(panelVehiculo.txtAnio.getText());
			vehiculo.setFechavehiculo(utilToSQL(utilFecha));
			vehiculo.setPreCompra(Float.parseFloat(panelVehiculo.txtPrecioCompra.getText()));
			vehiculo.setTipoVeh(panelVehiculo.txtTipoVeh.getText());
			int claveSucursal = ((Sucursal) panelVehiculo.comboBoxTienda.getItemAt(panelVehiculo.comboBoxTienda.getSelectedIndex())).getClave();
			vehiculo.setCve_tie(claveSucursal);
			
			placas.setNum_pla(panelVehiculo.txtPlacas.getText());
			
			status.setStatus_sta(panelVehiculo.txtEstadoVehiculo.getText());
			
			System.out.println("AL ESCRIBIR MODIFICACIONES");
			
			try {
				if(consultasVehiculo.modificar(vehiculo, placas, status)){
					JOptionPane.showMessageDialog(null, "Vehiculo modificado");
					System.out.println("ANTES DE APLICAR MODIFICACIONES");

					consultasVehiculo.llenarTabla(panelVehiculo.table);
					//consultasVehiculo.llenarTablaStatus(panelVehiculo.tableSTATUS);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar vehiculo");
					limpiarCajas();
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == panelVehiculo.btnBuscar) {
			
			placas.setNum_pla(panelVehiculo.txtPlacas.getText());
			//status.setNum_sta(Integer.parseInt(panelVehiculo.txtNumeroStatus.getText()));
			
			if (consultasVehiculo.buscar(vehiculo, placas, status) /*&& consultasVehiculo.buscarStatus(status)*/) {
				panelVehiculo.txtModelo.setText(vehiculo.getModelo());
				panelVehiculo.txtMarca.setText(vehiculo.getMarca());
				panelVehiculo.txtColor.setText(vehiculo.getColor());
				panelVehiculo.txtCanPuertas.setText(String.valueOf(vehiculo.getCantPuertas()));
				panelVehiculo.txtAnio.setText(SQLToString(vehiculo.getFechavehiculo()));
				panelVehiculo.txtPrecioCompra.setText(String.valueOf(vehiculo.getPreCompra()));
				panelVehiculo.txtTipoVeh.setText(vehiculo.getTipoVeh());
				panelVehiculo.comboBoxTienda.setSelectedItem(vehiculo.getCve_tie());
				
				panelVehiculo.txtPlacas.setText(placas.getNum_pla());
				//panelVehiculo.txtNsPlacas.setText(String.valueOf(placas.getNs_veh()));
				
				//panelVehiculo.txtNumeroStatus.setText(String.valueOf(status.getNum_sta()));
				panelVehiculo.txtEstadoVehiculo.setText(status.getStatus_sta());
				//panelVehiculo.txtNoSerieStatus.setText(String.valueOf(status.getNs_vehsta()));
				
			} else {
				JOptionPane.showMessageDialog(null, "Error al buscar vehiculo");
				limpiarCajas();
			}
			
		}
		
		if(e.getSource() == panelVehiculo.btnEliminar){
			//contrato.setFolio(Integer.parseInt(panelContrato.txtFieldFolio.getText()));
			//vehiculo.setModelo(panelVehiculo.txtPlacas.getText());
			  placas.setNum_pla(panelVehiculo.txtPlacas.getText());
			  //status.setNum_sta(Integer.parseInt(panelVehiculo.txtNumeroStatus.getText()));
			try {
				if(consultasVehiculo.eliminar(placas)){
					JOptionPane.showMessageDialog(null, "Placas eliminadas");
					consultasVehiculo.llenarTabla(panelVehiculo.table);
					//consultasVehiculo.llenarTablaStatus(panelVehiculo.tableSTATUS);
					limpiarCajas();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error al eliminar las placas de los vehiculos");
				limpiarCajas();
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == panelVehiculo.btnLimpiar) {
			limpiarCajas();
		}
	}
		
		
		
	
	public void llenarComboSucursal(){
		ArrayList<Sucursal> listaSucursales = consultasVehiculo.getSucursales();
		panelVehiculo.comboBoxTienda.removeAllItems();
		
		for(int i = 0; i < listaSucursales.size(); i++){
			panelVehiculo.comboBoxTienda.addItem(new Sucursal(listaSucursales.get(i).getClave(), listaSucursales.get(i).getCalle()));
		}
	}

	public void limpiarCajas() {
		panelVehiculo.txtModelo.setText(null);
		panelVehiculo.txtMarca.setText(null);
		panelVehiculo.txtColor.setText(null);
		panelVehiculo.txtCanPuertas.setText(null);
		panelVehiculo.txtAnio.setText(null);
		panelVehiculo.txtPrecioCompra.setText(null);
		panelVehiculo.txtTipoVeh.setText(null);
		
		panelVehiculo.txtPlacas.setText(null);
		//panelVehiculo.txtNsPlacas.setText(null);
		
		//panelVehiculo.txtNumeroStatus.setText(null);
		panelVehiculo.txtEstadoVehiculo.setText(null);
		//panelVehiculo.txtNoSerieStatus.setText(null);
		
		
		
	}
	
	
	
	

}
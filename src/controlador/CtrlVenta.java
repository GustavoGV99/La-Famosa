package controlador;

import static utileria.DateAndTime.SQLToString;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import consultas.ConsultasSucursal;
import consultas.ConsultasVenta;
import consultas.ConsultasContrato;
import consultas.ConsultasCliente;
import consultas.ConsultasProducto;
import consultas.ConsultasUbicacion;

import modelo.Producto;
import modelo.Sucursal;
import modelo.Ticket;
import modelo.Contrato;
import modelo.Cliente;

import vista.PanelVenta;

import static utileria.DateAndTime.stringToUtil;
import static utileria.DateAndTime.utilToSQL;
import static utileria.DateAndTime.stringToTime;

public class CtrlVenta implements ActionListener {
	private Ticket venta;
	private ConsultasVenta consultasVenta;
	private ConsultasContrato consultasContrato;
	private ConsultasSucursal consultasSucursal;
	private ConsultasCliente consultasCliente;
	private ConsultasProducto consultasProducto;
	private ConsultasUbicacion consultsUbicacion;
	private PanelVenta panelVenta;
	
	public CtrlVenta(Ticket venta, ConsultasVenta consultasVenta,PanelVenta panelVenta, ConsultasContrato consultasContrato,ConsultasCliente consultasCliente,ConsultasSucursal consultasSucursal, ConsultasUbicacion consultasUbicacion,ConsultasProducto consultasProducto){
		this.venta = venta;
		this.consultasVenta = consultasVenta;
		this.consultasContrato = consultasContrato;
		this.consultasSucursal = consultasSucursal;
		this.consultasCliente = consultasCliente;
		this.consultasProducto = consultasProducto;
		this.panelVenta = panelVenta;
		
		this.panelVenta.btnRegistrar.addActionListener((ActionListener) this);
		this.panelVenta.btnBuscar.addActionListener((ActionListener) this);
		this.panelVenta.btnDatoEnvio.addActionListener((ActionListener) this);
		this.panelVenta.btnLimpiar.addActionListener((ActionListener)this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelVenta.btnRegistrar){
			Date dateUtil = stringToUtil(panelVenta.txtFieldFechaVenta.getText());
			venta.setFechaventa_tic(utilToSQL(dateUtil));
			Date dateUtilF = stringToUtil(panelVenta.txtFieldFinGarantia.getText());
			venta.setFfingarantia_tic(utilToSQL(dateUtilF));
			venta.setPreciovta_tic(Float.parseFloat(panelVenta.txtFieldPrecioVenta.getText()));
			venta.setDescuento_tic(Integer.parseInt(panelVenta.txtFieldDescuento.getText()));
			venta.setCantidad_tic(Integer.parseInt(panelVenta.txtFieldCantidad.getText()));
			String formaPago = (String) panelVenta.comboBoxFormaPago.getSelectedItem();
			venta.setFormapago_tic(formaPago);
			venta.setInstitucionbancaria_tic(panelVenta.txtFieldInstitutcionBancaria.getText());
			venta.setFolioauntbanco_tic(Integer.parseInt(panelVenta.txtFieldFolioAutorizacion.getText()));
			venta.setNumtar_tic(Integer.parseInt(panelVenta.txtFieldNumeroTarjeta.getText()));
			int codigoProducto = ((Producto) panelVenta.comboBoxCodigoProducto.getItemAt(panelVenta.comboBoxCodigoProducto.getSelectedIndex())).getCodigoBarras();
			venta.setCodbar_pro(codigoProducto);
			int claveContrato = ((Contrato) panelVenta.comboBoxContrato.getItemAt(panelVenta.comboBoxContrato.getSelectedIndex())).getFolio();
			venta.setFolio_cont(claveContrato);
			int claveSucursal = ((Sucursal) panelVenta.comboBoxTienda.getItemAt(panelVenta.comboBoxTienda.getSelectedIndex())).getClave();
			venta.setCve_tie(claveSucursal);
			int claveCliente = ((Cliente) panelVenta.comboBoxCliente.getItemAt(panelVenta.comboBoxCliente.getSelectedIndex())).getCve_cli();
			venta.setCve_cli(claveCliente);
			
			if(consultasVenta.registrar(venta)){
				JOptionPane.showMessageDialog(null, "Venta Registrada");
				consultasVenta.llenarTablaVenta(panelVenta.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Error al registrar Venta");
				limpiarCajas();
			}
		}
		
		if(e.getSource() == panelVenta.btnBuscar){
			venta.setFolio(Integer.parseInt(panelVenta.txtFieldFolio.getText()));

			if(consultasVenta.buscar(venta)){
				panelVenta.txtFieldFechaVenta.setText(SQLToString(venta.getFechaventa_tic()));
				panelVenta.txtFieldFinGarantia.setText(SQLToString(venta.getFfingarantia_tic()));
				panelVenta.txtFieldPrecioVenta.setText(String.valueOf(venta.getPreciovta_tic()));
				panelVenta.txtFieldDescuento.setText(String.valueOf(venta.getDescuento_tic()));
				panelVenta.txtFieldCantidad.setText(String.valueOf(venta.getCantidad_tic()));
				panelVenta.comboBoxFormaPago.setSelectedItem((venta.getFormapago_tic()));
				panelVenta.txtFieldInstitutcionBancaria.setText(venta.getInstitucionbancaria_tic());
				panelVenta.txtFieldNumeroTarjeta.setText(String.valueOf(venta.getNumtar_tic()));
				panelVenta.txtFieldFolioAutorizacion.setText(String.valueOf(venta.getFolioauntbanco_tic()));
				panelVenta.comboBoxTienda.setSelectedItem(venta.getCve_tie());
				panelVenta.comboBoxCodigoProducto.setSelectedItem(venta.getCodbar_pro());
				panelVenta.comboBoxCliente.setSelectedItem(venta.getCve_cli());
				panelVenta.comboBoxContrato.setSelectedItem(venta.getFolio_cont());
			} else {
				JOptionPane.showMessageDialog(null, "Error al buscar sucursal");
				limpiarCajas();
			}
		}
		if(e.getSource() == panelVenta.btnDatoEnvio) {

		}
		
		if(e.getSource() == panelVenta.btnLimpiar){
			limpiarCajas();
		}

	}
	
	public void llenarComboProducto(){
		ArrayList<Producto> listaProductos = consultasProducto.getProductos();
		panelVenta.comboBoxCodigoProducto.removeAllItems();
		
		for(int i = 0; i < listaProductos.size(); i++){
			panelVenta.comboBoxCodigoProducto.addItem(new Producto(listaProductos.get(i).getCodigoBarras(), listaProductos.get(i).getNombre(), listaProductos.get(i).getModelo()));
		}
	}
	
	public void llenarComboContrato(){
		ArrayList<Contrato> listaContratos = consultasContrato.getContrato();
		panelVenta.comboBoxContrato.removeAllItems();
		
		for(int i = 0; i < listaContratos.size(); i++){
			panelVenta.comboBoxContrato.addItem(new Contrato(listaContratos.get(i).getFolio()));
		}
	}
	
	public void llenarComboSucursal(){
		ArrayList<Sucursal> listaSucursales = consultasSucursal.getSucursales();
		panelVenta.comboBoxTienda.removeAllItems();
		
		for(int i = 0; i < listaSucursales.size(); i++){
			panelVenta.comboBoxTienda.addItem(new Sucursal(listaSucursales.get(i).getClave(), listaSucursales.get(i).getCalle()));
		}
	}
	
	public void llenarComboCliente(){
		ArrayList<Cliente> listaCliente = consultasCliente.getCliente();
		panelVenta.comboBoxCliente.removeAllItems();
		
		for(int i = 0; i < listaCliente.size(); i++){
			panelVenta.comboBoxCliente.addItem(new Cliente(listaCliente.get(i).getCve_cli()));
		}
	}
	
	public void limpiarCajas(){
		panelVenta.txtFieldFolio.setText(null);
		panelVenta.txtFieldFechaVenta.setText(null);
		panelVenta.txtFieldFinGarantia.setText(null);
		panelVenta.txtFieldPrecioVenta.setText(null);
		panelVenta.txtFieldDescuento.setText(null);
		panelVenta.txtFieldCantidad.setText(null);
		panelVenta.txtFieldInstitutcionBancaria.setText(null);
		panelVenta.txtFieldNumeroTarjeta.setText(null);
		panelVenta.txtFieldFolioAutorizacion.setText(null);
	}

}

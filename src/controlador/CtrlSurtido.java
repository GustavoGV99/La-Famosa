package controlador;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import static utileria.DateAndTime.*;

import consultas.ConsultasProducto;
import consultas.ConsultasProveedor;
import consultas.ConsultasSurtido;
import consultas.ConsultasSucursal;
import modelo.Acomodo;
import modelo.Producto;
import modelo.Proveedor;
import modelo.DetalleSurtido;
import modelo.Surtido;
import modelo.Sucursal;
import vista.PanelSurtido;

public class CtrlSurtido implements ActionListener {
	private Surtido surtido;
	private DetalleSurtido detalleSurtido;
	private Acomodo acomodar;
	private ConsultasSurtido consultasSurtido;
	private ConsultasProducto consultasProducto;
	private ConsultasProveedor consultasProveedor;
	private ConsultasSucursal consultasSucursal;
	private static PanelSurtido panelSurtido;
	public int numeroRenglon;
	public int claveResurtir;

	public CtrlSurtido(Surtido surtido, ConsultasSurtido consultasSurtido, ConsultasProducto consultasProducto, ConsultasProveedor consultasProveedor, ConsultasSucursal consultasSucursal, PanelSurtido panelSurtido) {
		this.surtido = surtido;
		this.consultasSurtido = consultasSurtido;
		this.consultasProducto = consultasProducto;
		this.consultasProveedor = consultasProveedor;
		this.consultasSucursal = consultasSucursal;
		this.panelSurtido = panelSurtido;

		this.panelSurtido.btnRegistrar.addActionListener(this);
		this.panelSurtido.btnBuscar.addActionListener(this);
		this.panelSurtido.btnModificar.addActionListener(this);
		this.panelSurtido.btnLimpiar.addActionListener(this);
		this.panelSurtido.btnAcomodar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == panelSurtido.btnRegistrar) {
			Date fechaUtil = stringToUtil(panelSurtido.textFieldFecha.getText());
			surtido.setFecha(utilToSQL(fechaUtil));
			surtido.setTotal(Float.parseFloat(panelSurtido.txtFieldTotal.getText().replace("$", "")));
			int claveProveedor = ((Proveedor) panelSurtido.comboBoxProveedor.getItemAt(panelSurtido.comboBoxProveedor.getSelectedIndex())).getClave();
			surtido.setClaveProveedor(claveProveedor);
			int claveSucursal = ((Sucursal) panelSurtido.comboBoxSucursal.getItemAt(panelSurtido.comboBoxSucursal.getSelectedIndex())).getClave();
			surtido.setClaveSucursal(claveSucursal);

			panelSurtido.textFieldFecha.setEditable(true);
			panelSurtido.txtFieldTotal.setEditable(true);
			panelSurtido.comboBoxSucursal.setEnabled(true);
			panelSurtido.comboBoxProveedor.setEnabled(true);
			try {
				if(consultasSurtido.registrar(surtido, panelSurtido.listaRenglones, panelSurtido.listaAcomodar)) {
					JOptionPane.showMessageDialog(null, "Surtido registrado");
					limpiarCajas();
				} else {
					System.err.println();
					JOptionPane.showMessageDialog(null, "Error al registrar surtido, revise los datos ingresados.");
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, "Error al registrar surtido, revise los datos ingresados.");
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == panelSurtido.btnModificar) {
			Date fechaUtil = stringToUtil(panelSurtido.textFieldFecha.getText());
			surtido.setFecha(utilToSQL(fechaUtil));
			surtido.setTotal(Float.parseFloat(panelSurtido.txtFieldTotal.getText().replace("$", "")));
			int claveProveedor = ((Proveedor) panelSurtido.comboBoxProveedor.getItemAt(panelSurtido.comboBoxProveedor.getSelectedIndex())).getClave();
			surtido.setClaveProveedor(claveProveedor);
			int claveSucursal = ((Sucursal) panelSurtido.comboBoxSucursal.getItemAt(panelSurtido.comboBoxSucursal.getSelectedIndex())).getClave();
			surtido.setClaveSucursal(claveSucursal);

			panelSurtido.textFieldFecha.setEditable(true);
			panelSurtido.txtFieldTotal.setEditable(true);
			panelSurtido.comboBoxSucursal.setEnabled(true);
			panelSurtido.comboBoxProveedor.setEnabled(true);
			try {
				if(consultasSurtido.modificar(surtido, panelSurtido.listaRenglones, panelSurtido.listaAcomodar, numeroRenglon)) {
					JOptionPane.showMessageDialog(null, "Surtido modificado");
					consultasSurtido.llenarTablaBusqueda(panelSurtido.table, claveResurtir);
					limpiarCajas();
					limpiarCajasProducto();
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar surtido, revise los datos ingresados.");
				}
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error al modificar surtido, revise los datos ingresados.");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error al modificar surtido, revise los datos ingresados.");
			}

		}

		if(e.getSource() == panelSurtido.btnBuscar) {
			try {
			Date fechaUtil = stringToUtil(panelSurtido.textFieldFecha.getText());
			java.sql.Date fechaSQL = utilToSQL(fechaUtil);
			int claveProveedor = ((Proveedor) panelSurtido.comboBoxProveedor.getItemAt(panelSurtido.comboBoxProveedor.getSelectedIndex())).getClave();
			int claveSucursal = ((Sucursal) panelSurtido.comboBoxSucursal.getItemAt(panelSurtido.comboBoxSucursal.getSelectedIndex())).getClave();
			claveResurtir = consultasSurtido.getClaveResurtir(fechaSQL, claveProveedor, claveSucursal);
			int codigoBarras = ((Producto) panelSurtido.comboBoxProducto.getItemAt(panelSurtido.comboBoxProducto.getSelectedIndex())).getCodigoBarras();
			numeroRenglon = consultasSurtido.getNumeroRenglon(claveResurtir, codigoBarras);
			consultasSurtido.llenarTablaBusqueda(panelSurtido.table, claveResurtir);
			detalleSurtido = new DetalleSurtido();
			acomodar = new Acomodo();
			
			if(consultasSurtido.buscar(surtido, detalleSurtido, acomodar, claveResurtir, codigoBarras)) {
				panelSurtido.txtFieldTotal.setText(String.valueOf("$"+surtido.getTotal()));
				panelSurtido.textFieldCaducidad.setForeground(Color.BLACK);
				panelSurtido.textFieldCaducidad.setText(String.valueOf(SQLToString(detalleSurtido.getCaducidad())));
				panelSurtido.textFieldCantidad.setText(String.valueOf(detalleSurtido.getCantidad()));
				panelSurtido.textFieldPrecio.setText(String.valueOf("$"+detalleSurtido.getPrecio()));
				panelSurtido.textFieldFechaAcomodo.setForeground(Color.BLACK);
				panelSurtido.textFieldFechaAcomodo.setText(String.valueOf(SQLToString(acomodar.getFecha())));
				panelSurtido.textFieldCantidadAcomodo.setText(String.valueOf(acomodar.getCantidad()));
				panelSurtido.textFieldLugar.setForeground(Color.BLACK);
				panelSurtido.textFieldLugar.setText(acomodar.getLugar());
				panelSurtido.comboBoxSucursalAcomodar.setSelectedIndex(acomodar.getClaveSucursal());
			} else {
				JOptionPane.showMessageDialog(null, "Sin resultados en la búsqueda");
				limpiarCajasProducto();
			}
			}catch(Exception ex) {
				
			}
		}
		
		if(e.getSource() == panelSurtido.btnLimpiar) {
			limpiarCajas();
		}

	}

	public void limpiarCajas() {
		panelSurtido.textFieldFecha.setForeground(Color.GRAY);
		panelSurtido.textFieldFecha.setText("dd/mm/aaaa");
		panelSurtido.txtFieldTotal.setText("$");
		panelSurtido.textFieldCaducidad.setForeground(Color.GRAY);
		panelSurtido.textFieldCaducidad.setText("dd/mm/aaaa");
		panelSurtido.textFieldCantidad.setText(null);
		panelSurtido.textFieldPrecio.setText("$");
		panelSurtido.textFieldFechaAcomodo.setForeground(Color.GRAY);
		panelSurtido.textFieldFechaAcomodo.setText("dd/mm/aaaa");
		panelSurtido.textFieldCantidadAcomodo.setText(null);
		panelSurtido.textFieldCaducidad.setForeground(Color.GRAY);
		panelSurtido.textFieldCaducidad.setText("dd/mm/aaaa");
	}

	public void limpiarCajasProducto() {
		panelSurtido.textFieldCaducidad.setForeground(Color.GRAY);
		panelSurtido.textFieldCaducidad.setText("dd/mm/aaaa");
		panelSurtido.textFieldCantidad.setText(null);
		panelSurtido.textFieldPrecio.setText("$");
		panelSurtido.textFieldFechaAcomodo.setForeground(Color.GRAY);
		panelSurtido.textFieldFechaAcomodo.setText("dd/mm/aaaa");
		panelSurtido.textFieldCantidadAcomodo.setText(null);
		panelSurtido.textFieldLugar.setForeground(Color.GRAY);
		panelSurtido.textFieldLugar.setText("dd/mm/aaaa");
	}

	public void llenarComboSucursal(){
		ArrayList<Sucursal> listaSucursales = consultasSucursal.getSucursales();
		panelSurtido.comboBoxSucursal.removeAllItems();
		panelSurtido.comboBoxSucursalAcomodar.removeAllItems();

		for(int i = 0; i < listaSucursales.size(); i++){
			Sucursal sucursal = new Sucursal(listaSucursales.get(i).getClave(), listaSucursales.get(i).getCalle());
			panelSurtido.comboBoxSucursal.addItem(sucursal);
			panelSurtido.comboBoxSucursalAcomodar.addItem(sucursal);
		}
	}

	public void llenarComboProveedor(){
		ArrayList<Proveedor> listaProveedores = consultasProveedor.getProveedores();
		panelSurtido.comboBoxProveedor.removeAllItems();

		for(int i = 0; i < listaProveedores.size(); i++){
			panelSurtido.comboBoxProveedor.addItem(new Proveedor(listaProveedores.get(i).getClave(), listaProveedores.get(i).getRazonSocial()));
		}
	}

	public void llenarComboProducto(){
		ArrayList<Producto> listaProducto = consultasProducto.getProductos();
		panelSurtido.comboBoxProducto.removeAllItems();

		for(int i = 0; i < listaProducto.size(); i++){
			panelSurtido.comboBoxProducto.addItem(new Producto(listaProducto.get(i).getCodigoBarras(), listaProducto.get(i).getNombre(), listaProducto.get(i).getModelo()));
		}
	}

}

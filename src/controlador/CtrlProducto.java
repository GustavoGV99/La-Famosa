package controlador;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import consultas.ConsultasProducto;
import consultas.ConsultasSucursal;
import modelo.Colonia;
import modelo.DetalleProducto;
import modelo.Producto;
import modelo.Sucursal;
import vista.PanelProducto;

public class CtrlProducto implements ActionListener {
	private Producto producto;
	private static ConsultasProducto consultasProducto;
	private ConsultasSucursal consultasSucursal;
	private PanelProducto panelProducto;
	
	public CtrlProducto(Producto producto, ConsultasProducto consultasProducto, ConsultasSucursal consultasSucursal, PanelProducto panelProducto) {
		this.producto = producto;
		this.consultasProducto = consultasProducto;
		this.consultasSucursal = consultasSucursal;
		this.panelProducto = panelProducto;
		
		this.panelProducto.btnRegistrar.addActionListener(this);
		this.panelProducto.btnBuscar.addActionListener(this);
		this.panelProducto.btnModificar.addActionListener(this);
		this.panelProducto.btnEliminar.addActionListener(this);
		this.panelProducto.btnLimpiar.addActionListener(this);
		this.panelProducto.btnRegresar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == panelProducto.btnRegistrar) {
			try {
			producto.setCodigoBarras(Integer.parseInt(panelProducto.textFieldCodBar.getText()));
			System.out.println(producto.getCodigoBarras());
			producto.setNombre(panelProducto.textFieldNombre.getText());
			producto.setTipo((String) panelProducto.comboBoxTipo.getSelectedItem());
			producto.setMarca(panelProducto.textFieldMarca.getText());
			producto.setColor(panelProducto.textFieldColor.getText());
			producto.setGarantia(panelProducto.textFieldGarantia.getText());
			producto.setMedidaGarantia((String) panelProducto.comboBoxMedidaGarantia.getSelectedItem());
			producto.setPresentacion((String) panelProducto.comboBoxPresentacion.getSelectedItem());
			producto.setModelo(panelProducto.textFieldModelo.getText());
			producto.setAlto(panelProducto.textFieldAlto.getText());
			producto.setAncho(panelProducto.textFieldAncho.getText());
			producto.setLargo(panelProducto.textFieldLargo.getText());
			producto.setContenido(panelProducto.textFieldContenido.getText());
			producto.setuMedida((String) panelProducto.comboBoxUnidadMedida.getSelectedItem());
			
			panelProducto.textFieldCodBar.setEditable(true);
			panelProducto.textFieldNombre.setEditable(true);
			panelProducto.comboBoxTipo.setEnabled(true);
			panelProducto.textFieldMarca.setEditable(true);
			panelProducto.textFieldColor.setEditable(true);
			panelProducto.textFieldGarantia.setEditable(true);
			panelProducto.comboBoxMedidaGarantia.setEnabled(true);
			panelProducto.comboBoxPresentacion.setEnabled(true);
			panelProducto.textFieldModelo.setEditable(true);
			panelProducto.textFieldAlto.setEditable(true);
			panelProducto.textFieldAncho.setEditable(true);
			panelProducto.textFieldLargo.setEditable(true);
			panelProducto.textFieldContenido.setEditable(true);
			panelProducto.comboBoxUnidadMedida.setEnabled(true);
			
			System.out.println("Try, codbar producto: " + producto.getCodigoBarras());
			if(consultasProducto.registrar(producto, panelProducto.listaMinMax)) {
				JOptionPane.showMessageDialog(null, "Producto registrado y mínimos/máximos establecidos");
				consultasProducto.llenarTabla(panelProducto.table);
				limpiarCajas();
			} else {
				JOptionPane.showMessageDialog(null, "Error al registrar producto, datos incorrectos.");
			}
			} catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al registrar producto");
			}
		}
		
		if(e.getSource() == panelProducto.btnModificar) {
			try {
			producto.setCodigoBarras(Integer.parseInt(panelProducto.textFieldCodBar.getText()));
			producto.setNombre(panelProducto.textFieldNombre.getText());
			producto.setTipo((String) panelProducto.comboBoxTipo.getSelectedItem());
			producto.setMarca(panelProducto.textFieldMarca.getText());
			producto.setColor(panelProducto.textFieldColor.getText());
			producto.setGarantia(panelProducto.textFieldGarantia.getText());
			producto.setMedidaGarantia((String) panelProducto.comboBoxMedidaGarantia.getSelectedItem());
			producto.setPresentacion((String) panelProducto.comboBoxPresentacion.getSelectedItem());
			producto.setModelo(panelProducto.textFieldModelo.getText());
			producto.setAlto(panelProducto.textFieldAlto.getText());
			producto.setAncho(panelProducto.textFieldAncho.getText());
			producto.setLargo(panelProducto.textFieldLargo.getText());
			producto.setContenido(panelProducto.textFieldContenido.getText());
			producto.setuMedida((String) panelProducto.comboBoxUnidadMedida.getSelectedItem());
			
			panelProducto.textFieldCodBar.setEditable(true);
			panelProducto.textFieldNombre.setEditable(true);
			panelProducto.comboBoxTipo.setEnabled(true);
			panelProducto.textFieldMarca.setEditable(true);
			panelProducto.textFieldColor.setEditable(true);
			panelProducto.textFieldGarantia.setEditable(true);
			panelProducto.comboBoxMedidaGarantia.setEnabled(true);
			panelProducto.comboBoxPresentacion.setEnabled(true);
			panelProducto.textFieldModelo.setEditable(true);
			panelProducto.textFieldAlto.setEditable(true);
			panelProducto.textFieldAncho.setEditable(true);
			panelProducto.textFieldLargo.setEditable(true);
			panelProducto.textFieldContenido.setEditable(true);
			panelProducto.comboBoxUnidadMedida.setEnabled(true);
			} catch(Exception ex) {
			}
			try {
				if(consultasProducto.modificar(producto, panelProducto.listaMinMax)) {
					JOptionPane.showMessageDialog(null, "Producto modificado y mínimos/máximos establecidos");
					consultasProducto.llenarTabla(panelProducto.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar producto, verifique sus datos.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error al modificar producto, verifique sus datos.");
			}
		}
		
		if(e.getSource() == panelProducto.btnBuscar) {
			if(panelProducto.textFieldCodBar.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "No se ha ingresado ningún codigo de barras para efectuar la búsqueda.");
			} else {
			producto.setCodigoBarras(Integer.parseInt(panelProducto.textFieldCodBar.getText()));
			DetalleProducto minmax = new DetalleProducto();
			int claveSucursal = ((Sucursal) panelProducto.comboBoxSucursal.getItemAt(panelProducto.comboBoxSucursal.getSelectedIndex())).getClave();
			
			if(consultasProducto.buscar(producto, minmax, claveSucursal)) {
				panelProducto.textFieldNombre.setText(producto.getNombre());
				panelProducto.comboBoxTipo.setSelectedItem(producto.getTipo());
				panelProducto.textFieldMarca.setText(producto.getMarca());
				panelProducto.textFieldColor.setText(producto.getColor());
				panelProducto.textFieldGarantia.setText(producto.getGarantia());
				panelProducto.comboBoxMedidaGarantia.setSelectedItem(producto.getMedidaGarantia());
				panelProducto.comboBoxPresentacion.setSelectedItem(producto.getPresentacion());
				panelProducto.textFieldModelo.setText(producto.getModelo());
				panelProducto.textFieldAlto.setText(producto.getAlto());
				panelProducto.textFieldAncho.setText(producto.getAncho());
				panelProducto.textFieldLargo.setText(producto.getLargo());
				panelProducto.textFieldContenido.setText(producto.getContenido());
				panelProducto.comboBoxUnidadMedida.setSelectedItem(producto.getuMedida());
				panelProducto.textFieldMinimo.setText(String.valueOf(minmax.getMinimo()));
				panelProducto.textFieldMaximo.setText(String.valueOf(minmax.getMaximo()));
				
			} else {
				JOptionPane.showMessageDialog(null, "El producto que intenta buscar no se encuentra registrado.");
			}
			}
		}
		
		if(e.getSource() == panelProducto.btnEliminar) {
			producto.setCodigoBarras(Integer.parseInt(panelProducto.textFieldCodBar.getText()));
			try {
				if(consultasProducto.eliminar(producto)) {
					JOptionPane.showMessageDialog(null, "Producto eliminado");
					consultasProducto.llenarTabla(panelProducto.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "No es posible eliminara el producto porque sus datos están siendo utilizados.");
					limpiarCajas();
				}
			} catch (HeadlessException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == panelProducto.btnLimpiar) {
			limpiarCajas();
		}
		
	}
	
	public static boolean existe(int codigo) {
		if(consultasProducto.existe(codigo)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void llenarComboSucursal(){
		ArrayList<Sucursal> listaSucursales = consultasSucursal.getSucursales();
		panelProducto.comboBoxSucursal.removeAllItems();
		
		for(int i = 0; i < listaSucursales.size(); i++){
			panelProducto.comboBoxSucursal.addItem(new Sucursal(listaSucursales.get(i).getClave(), listaSucursales.get(i).getCalle()));
		}
	}
	
	public void limpiarCajas() {
		panelProducto.textFieldCodBar.setText(null);
		panelProducto.textFieldNombre.setText(null);
		panelProducto.textFieldMarca.setText(null);
		panelProducto.textFieldColor.setText(null);
		panelProducto.textFieldGarantia.setText(null);
		panelProducto.textFieldModelo.setText(null);
		panelProducto.textFieldAlto.setText(null);
		panelProducto.textFieldAncho.setText(null);
		panelProducto.textFieldLargo.setText(null);
		panelProducto.textFieldContenido.setText(null);
		panelProducto.textFieldMinimo.setText(null);
		panelProducto.textFieldMaximo.setText(null);
	}
	
}

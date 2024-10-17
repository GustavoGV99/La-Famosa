package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Sucursal;
import consultas.ConsultasInventario;
import consultas.ConsultasProducto;
import consultas.ConsultasSucursal;
import vista.PanelInventario;
import vista.PanelContrato;

public class CtrlInventario implements ActionListener {
	private ConsultasProducto consultasProducto;
	private ConsultasInventario consultasInventario;
	private ConsultasSucursal consultasSucursal;
	private PanelInventario panelInventario;

	public CtrlInventario(ConsultasProducto consultasproducto,ConsultasSucursal consultasSucursal, ConsultasInventario consultasInventario, PanelInventario panelInventario) {
		this.consultasInventario = consultasInventario;
		this.consultasProducto = consultasproducto;
		this.consultasSucursal = consultasSucursal;
		this.panelInventario = panelInventario;

		this.panelInventario.btnBuscar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelInventario.btnBuscar && !(panelInventario.txtFieldProducto.getText().equals("")) && panelInventario.txtFieldSucursal.getText().equals("")) {
			try {
				consultasInventario.filtrarProducto((panelInventario.txtFieldProducto.getText()), panelInventario.table);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			limpiarCajas();
		}

		if(e.getSource() == panelInventario.btnBuscar && !(panelInventario.txtFieldSucursal.getText().equals("")) && panelInventario.txtFieldProducto.getText().equals("")) {
			try {
				consultasInventario.filtrarPorTienda(Integer.parseInt(panelInventario.txtFieldSucursal.getText()), panelInventario.table);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			limpiarCajas();
		}

		if(e.getSource() == panelInventario.btnBuscar && !(panelInventario.txtFieldProducto.getText().equals("")) && !(panelInventario.txtFieldSucursal.getText().equals(""))) {
			try {
				consultasInventario.filtrarProductoTienda(Integer.parseInt(panelInventario.txtFieldSucursal.getText()), panelInventario.txtFieldProducto.getText(), panelInventario.table);
				limpiarCajas();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}else
			if(e.getSource() != panelInventario.btnBuscar && !(panelInventario.txtFieldProducto.getText().equals("")) && !(panelInventario.txtFieldSucursal.getText().equals(""))) {
				JOptionPane.showMessageDialog(null, "No existe este producto en esta sucursal");
				limpiarCajas();
		}

	}

	public void limpiarCajas(){
		panelInventario.txtFieldProducto.setText(null);
		panelInventario.txtFieldSucursal.setText(null);
	}

}

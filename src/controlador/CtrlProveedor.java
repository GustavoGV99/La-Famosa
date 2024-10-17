package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import consultas.ConsultasProveedor;
import modelo.Colonia;
import modelo.Proveedor;
import vista.PanelProveedor;

public class CtrlProveedor implements ActionListener {
	private Proveedor proveedor;
	private ConsultasProveedor consultasProveedor;
	private PanelProveedor panelProveedor;

	public CtrlProveedor(Proveedor proveedor, ConsultasProveedor consultasProveedor, PanelProveedor panelProveedor) {
		this.proveedor = proveedor;
		this.consultasProveedor = consultasProveedor;
		this.panelProveedor = panelProveedor;

		this.panelProveedor.btnRegistrar.addActionListener(this);
		this.panelProveedor.btnBuscar.addActionListener(this);
		this.panelProveedor.btnModificar.addActionListener(this);
		this.panelProveedor.btnEliminar.addActionListener(this);
		this.panelProveedor.btnLimpiar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelProveedor.btnRegistrar) {
			try {
				proveedor.setRazonSocial(panelProveedor.textFieldRazonSocial.getText());
				proveedor.setCalle(panelProveedor.txtFieldCalle.getText());
				proveedor.setNumero(panelProveedor.txtFieldNumero.getText());
				proveedor.setOrientacion((String) panelProveedor.comboBoxOrientacion.getSelectedItem());
				if(panelProveedor.txtFieldTelefono.getText().length() < 10) {
					proveedor.setTelefono("");
				} else {
					proveedor.setTelefono(panelProveedor.txtFieldTelefono.getText());
				}
				proveedor.setMail(panelProveedor.txtFieldCorreo.getText());
				int claveColonia = ((Colonia) panelProveedor.comboBoxColonia.getItemAt(panelProveedor.comboBoxColonia.getSelectedIndex())).getId();
				proveedor.setClaveColonia(((Colonia) panelProveedor.comboBoxColonia.getItemAt(panelProveedor.comboBoxColonia.getSelectedIndex())).getId());
			} catch(Exception ex) {
			}
			String r = validarCampos(proveedor);	
			if(r.equals("")) {
				if(consultasProveedor.registrar(proveedor)){
					JOptionPane.showMessageDialog(null, "Proveedor registrado");
					consultasProveedor.llenarTabla(panelProveedor.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error al registrar proveedor, revise los datos ingresados.");
				}
			} else {
				JOptionPane.showMessageDialog(null, r);
			}
		}

		if(e.getSource() == panelProveedor.btnModificar) {
			try {
				proveedor.setRazonSocial(panelProveedor.textFieldRazonSocial.getText());
				proveedor.setCalle(panelProveedor.txtFieldCalle.getText());
				proveedor.setNumero(panelProveedor.txtFieldNumero.getText());
				proveedor.setOrientacion((String) panelProveedor.comboBoxOrientacion.getSelectedItem());
				if(panelProveedor.txtFieldTelefono.getText().length() < 10) {
					proveedor.setTelefono("");
				} else {
					proveedor.setTelefono(panelProveedor.txtFieldTelefono.getText());
				}
				proveedor.setMail(panelProveedor.txtFieldCorreo.getText());
				int claveColonia = ((Colonia) panelProveedor.comboBoxColonia.getItemAt(panelProveedor.comboBoxColonia.getSelectedIndex())).getId();
				proveedor.setClaveColonia(claveColonia);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "No se seleccionó un proveedor para modificar.");
			}

			String r = validarCampos(proveedor);
			if(r.equals("")) {
				if(consultasProveedor.modificar(proveedor)){
					JOptionPane.showMessageDialog(null, "Proveedor modificado");
					consultasProveedor.llenarTabla(panelProveedor.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "Error al modificar proveedor, revise los datos ingresados.");
				}
			} else {
				JOptionPane.showMessageDialog(null, r);
			}

		}

		if(e.getSource() == panelProveedor.btnEliminar){
			proveedor.setRazonSocial(panelProveedor.textFieldRazonSocial.getText());
			if(proveedor.getRazonSocial().equals("")) {
				JOptionPane.showMessageDialog(null, "No se seleccionó ningún proveedor para eliminar.");
			} else {
				if(consultasProveedor.eliminar(proveedor)){
					JOptionPane.showMessageDialog(null, "Proveedor eliminado");
					consultasProveedor.llenarTabla(panelProveedor.table);
					limpiarCajas();
				} else {
					JOptionPane.showMessageDialog(null, "No es posible eliminar al proveedor porque sus datos están siendo utilizados.");
					limpiarCajas();
				}
			}
		}

		if(e.getSource() == panelProveedor.btnBuscar) {
			proveedor.setRazonSocial(panelProveedor.textFieldRazonSocial.getText());

			if(consultasProveedor.buscar(proveedor)) {
				try {
					panelProveedor.textFieldRazonSocial.setText(proveedor.getRazonSocial());
					panelProveedor.txtFieldCalle.setText(proveedor.getCalle());
					panelProveedor.txtFieldNumero.setText(proveedor.getNumero());
					panelProveedor.comboBoxOrientacion.setSelectedItem(proveedor.getOrientacion());
					panelProveedor.txtFieldTelefono.setText(proveedor.getTelefono());
					panelProveedor.txtFieldCorreo.setText(proveedor.getMail());
					panelProveedor.comboBoxEstado.setSelectedIndex(proveedor.getClaveEstado());
					panelProveedor.comboBoxCiudad.setSelectedIndex(proveedor.getClaveCiudad());
					panelProveedor.comboBoxCP.setSelectedItem(proveedor.getCp());
					panelProveedor.comboBoxColonia.setSelectedItem(proveedor.getClaveColonia());
				} catch(Exception er) {

				}
			} else {
				JOptionPane.showMessageDialog(null, "Ese proveedor no se encuentra registrado.");
			}
		}

		if(e.getSource() == panelProveedor.btnLimpiar){
			limpiarCajas();
		}
	}

	public void limpiarCajas(){
		panelProveedor.textFieldRazonSocial.setText(null);
		panelProveedor.txtFieldCalle.setText(null);
		panelProveedor.txtFieldNumero.setText(null);
		panelProveedor.txtFieldTelefono.setText(null);
		panelProveedor.txtFieldCorreo.setText(null);
	}

	public String validarCampos(Proveedor proveedor) {
		String r = "";
		if(proveedor.getRazonSocial().equals("")) {
			r += "Campo 'Razon social' obligatorio\n";
		}
		if(proveedor.getCalle().equals("")) {
			r += "Campo 'Calle' obligatorio\n";
		}
		if(proveedor.getNumero().equals("")) {
			r += "Campo 'Numero' obligatorio\n";
		}
		if(proveedor.getTelefono().equals("")) {
			r += "Campo 'Telefono' obligatorio\n";
		}
		if(proveedor.getMail().equals("")) {
			r += "Campo 'Correo' obligatorio\n";
		}
		if(proveedor.getClaveColonia() == 0) {
			r += "Campos de dirección obligatorios\n";
		}
		return r;
	}

}

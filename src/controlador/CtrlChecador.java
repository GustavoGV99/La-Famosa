package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import consultas.ConsultasChecador;
import vista.PanelChecador;
import vista.PanelContrato;

public class CtrlChecador implements ActionListener {
	private ConsultasChecador consultasChecador;
	private PanelChecador panelChecador;

	public CtrlChecador(ConsultasChecador consultasChecador, PanelChecador panelChecador) {
		this.consultasChecador = consultasChecador;
		this.panelChecador = panelChecador;

		this.panelChecador.btnBuscar.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == panelChecador.btnBuscar && panelChecador.textFieldEmpleado.getText() != null) {
			try {
				consultasChecador.filtrarEmpleado(Integer.parseInt(panelChecador.textFieldEmpleado.getText()), panelChecador.table);
				if(panelChecador.table.equals(null)) {
					JOptionPane.showMessageDialog(null, "Sin registros");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error al conusltar");
				ex.printStackTrace();
			}
		}
		
		if(e.getSource() == panelChecador.btnBuscar && panelChecador.textFieldEmpleado.getText() != null && panelChecador.comboBoxFecha.getSelectedItem() == "Este dia") {
			try {
				consultasChecador.filtrarEmpleadoDia(Integer.parseInt(panelChecador.textFieldEmpleado.getText()), panelChecador.table);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(e.getSource() == panelChecador.btnBuscar && panelChecador.textFieldEmpleado.getText() != null && panelChecador.comboBoxFecha.getSelectedItem() == "Quincena actual") {
			try {
				consultasChecador.filtrarEmpleadoQuincena(Integer.parseInt(panelChecador.textFieldEmpleado.getText()), panelChecador.table);
				System.out.println("POR QUINCENA ACTUAL: Ejecuta pero está vacío");
				// VALIDAR QUE SI NO HAY REGISTROS LO INDIQUE
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(e.getSource() == panelChecador.btnBuscar && panelChecador.textFieldEmpleado.getText() != null && panelChecador.comboBoxFecha.getSelectedItem() == "Este mes") {
			try {
				consultasChecador.filtrarEmpleadoMes(Integer.parseInt(panelChecador.textFieldEmpleado.getText()), panelChecador.table);
				System.out.println("POR MES ACTUAL: Ejecuta pero está vacío");
				// VALIDAR QUE SI NO HAY REGISTROS LO INDIQUE
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}

}

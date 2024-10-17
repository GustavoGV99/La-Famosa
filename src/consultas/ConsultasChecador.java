package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.CallableStatement;

import database.Database;

public class ConsultasChecador {
	Database conexion = new Database();

	public void generalChecador(JTable visor) {
		Statement st = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select checador.folio_cont, nom_per, ap_per, am_per, puesto_cont, date(hora_che), time(hora_che), tipo_che from persona join contrato on "
				+ "persona.cve_per = contrato.cve_per join checador on contrato.folio_cont = checador.folio_cont where month(curdate()) = month(date(hora_che)) order by "
				+ "hora_che desc";

		String[]columnas = {"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"};
		DefaultTableModel model = new DefaultTableModel(null, columnas);
		visor.setModel(model);

		String [] datos = new String[8];
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				datos[4] = rs.getString(5);
				datos[5] = rs.getString(6);
				datos[6] = rs.getString(7);
				datos[7] = rs.getString(8);
				model.addRow(datos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void filtrarEmpleado(int folio, JTable visor) {
		if(existe(folio)) {
			Statement st = null;
			Connection con = conexion.getConexion();
			ResultSet rs = null;
			String sql;
			sql = "select checador.folio_cont, nom_per, ap_per, am_per, puesto_cont, date(hora_che), time(hora_che), tipo_che from persona join contrato on "
					+ "persona.cve_per = contrato.cve_per join checador on contrato.folio_cont = checador.folio_cont where checador.folio_cont = " + folio;

			String[]columnas = {"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"};
			DefaultTableModel model = new DefaultTableModel(null, columnas);
			visor.setModel(model);
			
			String [] datos = new String[8];
			try {
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()){
					datos[0] = rs.getString(1);
					datos[1] = rs.getString(2);
					datos[2] = rs.getString(3);
					datos[3] = rs.getString(4);
					datos[4] = rs.getString(5);
					datos[5] = rs.getString(6);
					datos[6] = rs.getString(7);
					datos[7] = rs.getString(8);
					model.addRow(datos);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe ningún empleado con el folio que ingresó.");
		}
	}

	public void filtrarEmpleadoDia(int folio, JTable visor) {
		if(existe(folio)) {
			Statement st = null;
			Connection con = conexion.getConexion();
			ResultSet rs = null;
			String sql;
			sql = "select checador.folio_cont, nom_per, ap_per, am_per, puesto_cont, date(hora_che), time(hora_che), tipo_che from persona join contrato on "
					+ "persona.cve_per = contrato.cve_per join checador on contrato.folio_cont = checador.folio_cont where date(hora_che) = curdate() and "
					+ "checador.folio_cont = " + folio;


			String[]columnas = {"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"};
			DefaultTableModel model = new DefaultTableModel(null, columnas);
			visor.setModel(model);

			String [] datos = new String[8];
			try {
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()){
					datos[0] = rs.getString(1);
					datos[1] = rs.getString(2);
					datos[2] = rs.getString(3);
					datos[3] = rs.getString(4);
					datos[4] = rs.getString(5);
					datos[5] = rs.getString(6);
					datos[6] = rs.getString(7);
					datos[7] = rs.getString(8);
					model.addRow(datos);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe ningún empleado con el folio que ingresó.");
		}
	}

	public void filtrarEmpleadoQuincena(int folio, JTable visor) {
		if(existe(folio)) {
			Statement st = null;
			Connection con = conexion.getConexion();
			ResultSet rs = null;
			String sql;
			sql = "call sp_checadorquincena(?)";

			String[]columnas = {"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"};
			DefaultTableModel model = new DefaultTableModel(null, columnas);
			visor.setModel(model);

			String [] datos = new String[8];
			try {
				CallableStatement cs = (CallableStatement) con.prepareCall(sql);
				cs.setInt(1, folio);
				rs = cs.executeQuery();
				while(rs.next()){
					datos[0] = rs.getString(1);
					datos[1] = rs.getString(2);
					datos[2] = rs.getString(3);
					datos[3] = rs.getString(4);
					datos[4] = rs.getString(5);
					datos[5] = rs.getString(6);
					datos[6] = rs.getString(7);
					datos[7] = rs.getString(8);
					model.addRow(datos);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe ningún empleado con el folio que ingresó.");
		}
	}

	public void filtrarEmpleadoMes(int folio, JTable visor) {
		if(existe(folio)) {
			Statement st = null;
			Connection con = conexion.getConexion();
			ResultSet rs = null;
			String sql;

			sql = "select checador.folio_cont, nom_per, ap_per, am_per, puesto_cont, date(hora_che), time(hora_che), tipo_che from persona join contrato on "
					+ "persona.cve_per = contrato.cve_per join checador on contrato.folio_cont = checador.folio_cont where month(date(hora_che)) = month(curdate()) "
					+ "and checador.folio_cont = " + folio;

			String[]columnas = {"Folio", "Nombre", "Paterno", "Materno", "Puesto", "Fecha", "Hora", "Tipo"};
			DefaultTableModel model = new DefaultTableModel(null, columnas);
			visor.setModel(model);

			String [] datos = new String[8];
			try {
				st = con.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()){
					datos[0] = rs.getString(1);
					datos[1] = rs.getString(2);
					datos[2] = rs.getString(3);
					datos[3] = rs.getString(4);
					datos[4] = rs.getString(5);
					datos[5] = rs.getString(6);
					datos[6] = rs.getString(7);
					datos[7] = rs.getString(8);
					model.addRow(datos);
				}
				
				if(model.getRowCount() <= 0) {
					JOptionPane.showMessageDialog(null, "No hay registros de ese empleado aún.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No existe ningún empleado con el folio que ingresó.");
		}
	}

	public boolean existe(int folio) {
		try {
			Connection con = conexion.getConexion();
			Statement st = null;
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from contrato where folio_cont = " + folio);
			return rs.next() == true ? true : false;
		}catch(java.sql.SQLException e) {
			System.out.println("No funciona");
			return false;
		}
	}

}

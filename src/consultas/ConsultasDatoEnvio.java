package consultas;

import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.Database;
import modelo.DatoEnvio;

public class ConsultasDatoEnvio extends Database {
	
	public int getLastIdDatoEnvio() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_datenv) as cve_datenv from datoenvio";
		int lastIdDatEnv = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastIdDatEnv = rs.getInt("cve_datenv");
			}
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
		return lastIdDatEnv + 1;
	}

	public boolean registrarDatoEnvio(DatoEnvio datoEnvio){
		PreparedStatement insertarDatoEnvio = null;
		Connection con = getConexion();
		String sqlDatoEnvio;

		sqlDatoEnvio = "insert into datoenvio values(?, ?, ?, ?, ?, ?, ?)";
		try {
			insertarDatoEnvio = con.prepareStatement(sqlDatoEnvio);
			insertarDatoEnvio.setInt(1, getLastIdDatoEnvio());
			insertarDatoEnvio.setString(2, datoEnvio.getEntrecalles_datenv());
			insertarDatoEnvio.setString(3, datoEnvio.getCalle_datenv());
			insertarDatoEnvio.setString(4, datoEnvio.getNum_datenv());
			insertarDatoEnvio.setInt(5, datoEnvio.getCve_col());
			System.out.println("Desde consultas " + datoEnvio.getFolio_tic());
			insertarDatoEnvio.setInt(6, datoEnvio.getFolio_tic());
			insertarDatoEnvio.setString(7, datoEnvio.getOrientacion_datenv());
			insertarDatoEnvio.execute();
			System.out.println("Sí ejecuta");
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}

	public boolean modificarDatoEnvio(DatoEnvio datoEnvio){
		PreparedStatement modificarDatoEnvio = null;
		Connection con = getConexion();
		String sql;
		sql = "update datoenvio set entrecalles_datenv=? ,calle_datenv=?, num_datenv=?, cve_col=?, folio_tic=?, orientacion_datenv=? where cve_datenv=?";
		try {
			modificarDatoEnvio = con.prepareStatement(sql);
			modificarDatoEnvio.setString(1, datoEnvio.getEntrecalles_datenv());
			modificarDatoEnvio.setString(2, datoEnvio.getCalle_datenv());
			modificarDatoEnvio.setString(3, datoEnvio.getNum_datenv());
			modificarDatoEnvio.setInt(4, datoEnvio.getCve_col());
			modificarDatoEnvio.setInt(5, datoEnvio.getFolio_tic());
			modificarDatoEnvio.setString(6, datoEnvio.getOrientacion_datenv());
			modificarDatoEnvio.setInt(7, datoEnvio.getCve_datenv());
			modificarDatoEnvio.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}

	public boolean eliminar(DatoEnvio datoEnvio){
		PreparedStatement ps = null;
		Connection con = getConexion();
		String sql;
		sql = "delete from datoenvio where cve_datenv=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, datoEnvio.getCve_datenv());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}

	public boolean buscarDatoEnvio(DatoEnvio datoEnvio){
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select * from datoenvio where cve_datenv = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, datoEnvio.getCve_datenv());
			rs = ps.executeQuery();

			// Usamos if porque sólo vamos a traer una fila, cuando son más usamos while
			if(rs.next()){
				datoEnvio.setCve_datenv(Integer.parseInt(rs.getString("cve_datenv")));
				datoEnvio.setEntrecalles_datenv(rs.getString("entrecalles_datenv"));
				datoEnvio.setCalle_datenv(rs.getString("calle_datenv"));
				datoEnvio.setNum_datenv(rs.getString("num_datenv"));
				datoEnvio.setOrientacion_datenv(rs.getString("orientacion_datenv"));
				datoEnvio.setCve_col(Integer.parseInt(rs.getString("cve_col")));
				datoEnvio.setFolio_tic(Integer.parseInt(rs.getString("folio_tic")));
				return true;
			}

			return false;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.err.println(e2);
			}
		}
	}
	

	public void llenarTablaDatoEnvio(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select * from datoenvio";

		String[]columnas = {"Id", "Calle", "Numero", "Entre Calles", "Orientacion", "Colonia", "Folio Ticket"};
		DefaultTableModel model = new DefaultTableModel(null, columnas);
		visor.setModel(model);

		String [] datos = new String[7];

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
				model.addRow(datos);
			}
			//visor.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public ArrayList<DatoEnvio> getDatoEnvios(){
		Connection con = getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<DatoEnvio> listaDatoEnvio = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from datoenvio");

			while(rs.next()){
				DatoEnvio datoEnvio = new DatoEnvio();	
				datoEnvio.setCve_datenv(rs.getInt("cve_datenv"));
				datoEnvio.setCalle_datenv(rs.getString("calle_datenv"));
				datoEnvio.setEntrecalles_datenv(rs.getString("entrecalles_datenv"));
				datoEnvio.setNum_datenv(rs.getString("num_datenv"));
				datoEnvio.setOrientacion_datenv(rs.getString("orientacion_datenv"));
				datoEnvio.setCve_col(rs.getInt("cve_col"));
				datoEnvio.setFolio_tic(rs.getInt("folio_tic"));
				listaDatoEnvio.add(datoEnvio);
			}

		} catch (Exception e) {

		}
		return listaDatoEnvio;
	}

}

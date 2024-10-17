package consultas;

import java.awt.Color;
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

import static utileria.Validaciones.*;
import database.Database;
import modelo.Sucursal;

public class ConsultasSucursal {
	Database conexion = new Database();
	static String errores = "\tERROR\n";
	
	public int getUltimoId() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_tie) as cve_tie from tienda";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_tie");
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
		return lastId + 1;
	}

	public boolean registrar(Sucursal sucursal){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "insert into tienda values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, getUltimoId());
			ps.setDate(2, sucursal.getFechaApertura());
			ps.setString(3, sucursal.getCalle());
			ps.setString(4, sucursal.getNumeroDomicilio());
			ps.setString(5, sucursal.getEntreCalles());
			ps.setString(6, sucursal.getOrientacion());
			if(sucursal.getTelefono().length() < 10) {
				sucursal.setTelefono("");
			} else {
				ps.setString(7, sucursal.getTelefono());
			}
			if(!validarCorreo(sucursal.getMail())) {
				sucursal.setMail("");
			} else {
				ps.setString(8, sucursal.getMail());
			}
			ps.setInt(9, sucursal.getCveColonia());
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
			}
		}
	}

	public boolean modificar(Sucursal sucursal){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "update tienda set fechaaper_tie=?, calle_tie=?, numdomicilio_tie=?, entrecalle_tie=?, orientacioncalle_tie=?, tel_tie=?, "
				+ "mail_tie=?, cve_col=? where cve_tie=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setDate(1, sucursal.getFechaApertura());
			ps.setString(2, sucursal.getCalle());
			ps.setString(3, sucursal.getNumeroDomicilio());
			ps.setString(4, sucursal.getEntreCalles());
			ps.setString(5, sucursal.getOrientacion());
			ps.setString(6, sucursal.getTelefono());
			ps.setString(7, sucursal.getMail());
			ps.setInt(8, sucursal.getCveColonia());
			ps.setInt(9, sucursal.getClave());
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
			}
		}
	}

	public boolean eliminar(Sucursal sucursal){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "delete from tienda where cve_tie=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, sucursal.getClave());
			ps.execute();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
			}
		}
	}

	public boolean buscar(Sucursal sucursal){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select cve_tie, fechaaper_tie, calle_tie, numdomicilio_tie, " 
				+ "entrecalle_tie, orientacioncalle_tie, tel_tie, mail_tie, estado.cve_est, ciudad.cve_ciu, codigo.cp_cod, colonia.cve_col "
				+ "from tienda join colonia on tienda.cve_col = colonia.cve_col join "
				+ "codigo on colonia.cp_cod = codigo.cp_cod join ciudad on codigo.cve_ciu = ciudad.cve_ciu join estado on "
				+ "ciudad.cve_est = estado.cve_est where cve_tie = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, sucursal.getClave());
			rs = ps.executeQuery();

			if(rs.next()){
				sucursal.setClave(Integer.parseInt(rs.getString("cve_tie")));
				sucursal.setFechaApertura(rs.getDate("fechaaper_tie"));
				sucursal.setCalle(rs.getString("calle_tie"));
				sucursal.setNumeroDomicilio(rs.getString("numdomicilio_tie"));
				sucursal.setEntreCalles(rs.getString("entrecalle_tie"));
				sucursal.setOrientacion(rs.getString("orientacioncalle_tie"));
				sucursal.setTelefono(rs.getString("tel_tie"));
				sucursal.setMail(rs.getString("mail_tie"));
				sucursal.setClaveEst(rs.getInt("estado.cve_est"));
				sucursal.setClaveCiu(rs.getInt("ciudad.cve_ciu"));
				sucursal.setCodigoPostal(rs.getInt("codigo.cp_cod"));
				sucursal.setCveColonia(Integer.parseInt(rs.getString("colonia.cve_col")));
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
			}
		}
	}

	public void llenarTabla(JTable visor){
		Statement st = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select cve_tie, fechaaper_tie, tel_tie, nom_ciu, codigo.cp_cod, nom_col, calle_tie, numdomicilio_tie, "
				+ "orientacioncalle_tie from tienda join colonia on tienda.cve_col = colonia.cve_col join codigo on "
				+ "colonia.cp_cod = codigo.cp_cod join ciudad on codigo.cve_ciu = ciudad.cve_ciu join estado on ciudad.cve_est = "
				+ "estado.cve_est";

		String[]columnas = {"Id", "Fecha", "Telefono", "Ciudad", "Código postal", "Colonia", "Calle", "Numero", "Orientacion"};
		DefaultTableModel model = new DefaultTableModel(null, columnas);
		visor.setModel(model);

		String [] datos = new String[9];

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
				datos[8] = rs.getString(9);
				model.addRow(datos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Sucursal> getSucursales(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Sucursal> listaSucursales = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from tienda");

			while(rs.next()){
				Sucursal sucursal = new Sucursal();	
				sucursal.setClave(rs.getInt("cve_tie"));
				sucursal.setFechaApertura(rs.getDate("fechaaper_tie"));
				sucursal.setCalle(rs.getString("calle_tie"));
				sucursal.setNumeroDomicilio(rs.getString("numdomicilio_tie"));
				sucursal.setEntreCalles(rs.getString("entrecalle_tie"));
				sucursal.setOrientacion(rs.getString("orientacioncalle_tie"));
				sucursal.setTelefono(rs.getString("tel_tie"));
				sucursal.setMail(rs.getString("mail_tie"));
				sucursal.setCveColonia(rs.getInt("cve_col"));
				listaSucursales.add(sucursal);
			}
			
		} catch (Exception e) {
		}
		return listaSucursales;
	}

}

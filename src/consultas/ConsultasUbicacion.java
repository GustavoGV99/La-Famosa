package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import modelo.Ciudad;
import modelo.CodigoPostal;
import modelo.Colonia;
import modelo.Estado;
import modelo.Sucursal;

import com.mysql.cj.jdbc.CallableStatement;

import database.Database;

public class ConsultasUbicacion {
	Database conexion = new Database();

	public boolean registrarUbicacion(Estado estado, Ciudad ciudad, CodigoPostal codigo, Colonia colonia) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlEstado = "insert into estado values(?, ?)";
		String sqlCiudad = "insert into ciudad values(?, ?, ?)";
		String sqlCodigo = "insert into codigo values(?, ?)";
		String sqlColonia = "insert into colonia values(?, ?, ?)";
		PreparedStatement insertarEstado = null, insertarCiudad = null, insertarCodigo = null, insertarColonia = null;
		try {
			con.setAutoCommit(false);
			insertarEstado = con.prepareStatement(sqlEstado);
			insertarEstado.setInt(1, estado.getId());
			insertarEstado.setString(2, estado.getNombre());
			insertarEstado.executeUpdate();
			System.out.println("Salida statement estado");

			insertarCiudad = con.prepareStatement(sqlCiudad);
			insertarCiudad.setInt(1, ciudad.getId());
			insertarCiudad.setString(2, ciudad.getNombre());
			insertarCiudad.setInt(3, ciudad.getCveEst());
			insertarCiudad.executeUpdate();
			System.out.println("Salida statement ciudad");

			insertarCodigo = con.prepareStatement(sqlCodigo);
			insertarCodigo.setInt(1, codigo.getCodigo());
			insertarCodigo.setInt(2, codigo.getCveCiu());
			insertarCodigo.executeUpdate();
			System.out.println("Salida statement codigo");

			insertarColonia = con.prepareStatement(sqlColonia);
			insertarColonia.setInt(1, colonia.getId());
			insertarColonia.setString(2, colonia.getNombre());
			insertarColonia.setInt(3, colonia.getCodigoPostal());
			insertarColonia.executeUpdate();
			System.out.println("Salida statement colonia");

			con.commit();
			System.out.println("Despues de commit");
			return true;
		} catch (Exception e) {
			con.rollback();
			System.out.println("Despues de rollback");
			return false;
		} finally {
			con.setAutoCommit(true);
			System.out.println("Despues de setAutoCommit(true)");
			if(insertarEstado != null){
				insertarEstado.close();
			}
			if(insertarCiudad != null){
				insertarCiudad.close();
			}
			if(insertarCodigo != null){
				insertarCodigo.close();
			}
			if(insertarColonia != null){
				insertarColonia.close();
			}
		}
	}
	
	public void listarEstados(JComboBox boxEstado) {
		DefaultComboBoxModel value;
		Connection con = conexion.getConexion();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from estado order by nom_est asc");
			value = new DefaultComboBoxModel();
			boxEstado.setModel(value);
			
			while(rs.next()) {
				value.addElement(new Estado(rs.getInt(1), rs.getString(2)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	public void listarCiudades(JComboBox boxCiudad, int cveEstado) {
		DefaultComboBoxModel value;
		Connection con = conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from ciudad where cve_est = ? order by nom_ciu asc");
			ps.setInt(1, cveEstado);
			rs = ps.executeQuery();
			value = new DefaultComboBoxModel();
			boxCiudad.setModel(value);
			
			while(rs.next()) {
				value.addElement(new Ciudad(rs.getInt(1), rs.getString(2)));
			}
		} catch(Exception e) {
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	

	public void listarCodigo(JComboBox boxCodigo, int cveCiudad) {
		DefaultComboBoxModel value;
		Connection con = conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from codigo where cve_ciu = ? order by cp_cod asc");
			ps.setInt(1, cveCiudad);
			rs = ps.executeQuery();
			value = new DefaultComboBoxModel();
			boxCodigo.setModel(value);
			
			while(rs.next()) {
				value.addElement(new CodigoPostal(rs.getInt(1)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public void listarColonia(JComboBox boxColonia, int codigo) {
		DefaultComboBoxModel value;
		Connection con = conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from colonia where cp_cod = ? order by nom_col asc");
			ps.setInt(1, codigo);
			rs = ps.executeQuery();
			value = new DefaultComboBoxModel();
			boxColonia.setModel(value);
			
			while(rs.next()) {
				value.addElement(new Colonia(rs.getInt(1), rs.getString(2)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				ps.close();
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public ArrayList<Colonia> getColonia(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Colonia> listaColonias = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from colonia");

			while(rs.next()){
				Colonia colonia = new Colonia();	
				colonia.setId(rs.getInt("cve_col"));
				colonia.setNombre(rs.getString("nom_col"));
				colonia.setCodigoPostal(rs.getInt("cp_cod"));
				listaColonias.add(colonia);
			}

		} catch (Exception e) {

		}
		return listaColonias;
	}

}
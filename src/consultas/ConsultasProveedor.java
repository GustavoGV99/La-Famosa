package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Database;
import modelo.Proveedor;
import modelo.Sucursal;

public class ConsultasProveedor {
	Database conexion = new Database();
	
	public int getLastId() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_prov) as cve_prov from proveedor";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_prov");
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
	
	public boolean registrar(Proveedor proveedor){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "insert into proveedor values(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, getLastId());
			ps.setString(2, proveedor.getRazonSocial());
			ps.setString(3, proveedor.getCalle());
			ps.setString(4, proveedor.getNumero());
			ps.setString(5, proveedor.getOrientacion());
			ps.setString(6, proveedor.getTelefono());
			ps.setString(7, proveedor.getMail());
			ps.setInt(8, proveedor.getClaveColonia());
			ps.execute();
			System.out.println("Sí ejecuta");
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
	
	public boolean modificar(Proveedor proveedor){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "update proveedor set nomempresa_prov = ?, calle_prov = ?, num_prov = ?, orientacion_prov = ?, telefono_prov = ?, mail_prov = ?, cve_col = ? "
				+ "where cve_prov = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, proveedor.getRazonSocial());
			ps.setString(2, proveedor.getCalle());
			ps.setString(3, proveedor.getNumero());
			ps.setString(4, proveedor.getOrientacion());
			ps.setString(5, proveedor.getTelefono());
			ps.setString(6, proveedor.getMail());
			ps.setInt(7, proveedor.getClaveColonia());
			ps.setInt(8, proveedor.getClave());
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
	
	public boolean eliminar(Proveedor proveedor){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		String sql;
		sql = "delete from proveedor where nomempresa_prov = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, proveedor.getRazonSocial());
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
	
	public boolean buscar(Proveedor proveedor){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql = "select cve_prov, nomempresa_prov, calle_prov, orientacion_prov, num_prov, telefono_prov, mail_prov, "
				+ "estado.cve_est, ciudad.cve_ciu, codigo.cp_cod, colonia.cve_col from proveedor join colonia on "
				+ "proveedor.cve_col = colonia.cve_col join codigo on colonia.cp_cod = codigo.cp_cod join ciudad on "
				+ "codigo.cve_ciu = ciudad.cve_ciu join estado on ciudad.cve_est = estado.cve_est where nomempresa_prov = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, proveedor.getRazonSocial());
			rs = ps.executeQuery();

			if(rs.next()){
				proveedor.setClave(Integer.parseInt(rs.getString("cve_prov")));
				proveedor.setRazonSocial(rs.getString("nomempresa_prov"));
				proveedor.setCalle(rs.getString("calle_prov"));
				proveedor.setNumero(rs.getString("num_prov"));
				proveedor.setOrientacion(rs.getString("orientacion_prov"));
				proveedor.setTelefono(rs.getString("telefono_prov"));
				proveedor.setMail(rs.getString("mail_prov"));
				proveedor.setClaveEstado(rs.getInt("estado.cve_est"));
				proveedor.setClaveCiudad(rs.getInt("ciudad.cve_ciu"));
				proveedor.setCp(rs.getInt("codigo.cp_cod"));
				proveedor.setClaveColonia(Integer.parseInt(rs.getString("colonia.cve_col")));
				return true;
			}
			return false;
		} catch (SQLException e) {
			//e.printStackTrace();
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
		sql = "select nomempresa_prov, telefono_prov, nom_est, nom_ciu, codigo.cp_cod, nom_col, calle_prov, orientacion_prov, num_prov "
				+ "from proveedor join colonia on proveedor.cve_col = colonia.cve_col join codigo on colonia.cp_cod = codigo.cp_cod join "
				+ "ciudad on codigo.cve_ciu = ciudad.cve_ciu join estado on ciudad.cve_est = estado.cve_est";
		String[]columnas = {"Razon Social", "Teléfono", "Estado", "Ciudad", "Codigo Postal", "Colonia", "Calle", "Orientación", "Número"};
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
			
		}
	}
	
	public ArrayList<Proveedor> getProveedores(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Proveedor> listaProveedores = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from proveedor");

			while(rs.next()){
				Proveedor proveedor = new Proveedor();
				proveedor.setClave(Integer.parseInt(rs.getString("cve_prov")));
				proveedor.setRazonSocial(rs.getString("nomempresa_prov"));
				proveedor.setCalle(rs.getString("calle_prov"));
				proveedor.setNumero(rs.getString("num_prov"));
				proveedor.setOrientacion(rs.getString("orientacion_prov"));
				proveedor.setTelefono(rs.getString("telefono_prov"));
				proveedor.setMail(rs.getString("mail_prov"));
				proveedor.setClaveColonia(Integer.parseInt(rs.getString("cve_col")));
				listaProveedores.add(proveedor);
			}

		} catch (Exception e) {
			
		}
		return listaProveedores;
	}

}

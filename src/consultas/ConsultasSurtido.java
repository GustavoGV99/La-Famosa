package consultas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Database;
import modelo.Acomodo;
import modelo.DetalleProducto;
import modelo.Producto;
import modelo.DetalleSurtido;
import modelo.Surtido;
import vista.PanelSurtido;

public class ConsultasSurtido {
	Database conexion = new Database();

	public int getUltimaClave() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_res) as cve_res from resurtir";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_res");
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

	public int getUltimoNumRenglon() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(num_renres) as num_renres from renglonresurtir";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("num_renres");
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

	public int getClaveResurtir(Date fecha, int claveProveedor, int claveSucursal) {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select cve_res from resurtir where fecha_res = '"+fecha+"' and cve_tie = "+claveSucursal+" and cve_prov = " + claveProveedor + ";";
		int clave = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				clave = rs.getInt("cve_res");
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
		return clave;
	}
	
	public int getNumeroRenglon(int claveResurtir, int codigoBarras) {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select num_renres from renglonresurtir where codbar_pro = '"+ codigoBarras +"' and cve_res = "+claveResurtir;
		int numeroRenglon = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				numeroRenglon = rs.getInt("num_renres");
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
		return numeroRenglon;
	}

	public boolean registrar(Surtido surtido, ArrayList<DetalleSurtido> listaRenglones, ArrayList<Acomodo> listaAcomodo) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlResurtir = "insert into resurtir values(?, ?, ?, ?, ?)";
		String sqlRenglon = "insert into renglonresurtir values(?, ?, ?, ?, ?, ?, ?)";
		String sqlAcomodar = "insert into acomodar values(null, ?, ?, ?, ?, ?)";
		PreparedStatement insertarResurtido = null, insertarRenglon = null, insertarAcomodo = null;
		int claveResurtir = getUltimaClave();

		try {
			con.setAutoCommit(false);
			insertarResurtido = con.prepareStatement(sqlResurtir);
			insertarResurtido.setInt(1, claveResurtir);
			insertarResurtido.setDate(2, surtido.getFecha());
			insertarResurtido.setFloat(3, surtido.getTotal());
			insertarResurtido.setInt(4, surtido.getClaveProveedor());
			insertarResurtido.setInt(5, surtido.getClaveSucursal());
			insertarResurtido.executeUpdate();

			int numeroRenglon = 0;
			numeroRenglon = getUltimoNumRenglon();
			insertarRenglon = con.prepareStatement(sqlRenglon);
			for(int i = 0; i < listaRenglones.size(); i++) {

				insertarRenglon.setInt(1, numeroRenglon);
				insertarRenglon.setInt(2, listaRenglones.get(i).getCantidad());
				insertarRenglon.setInt(3, listaRenglones.get(i).getBaja());
				insertarRenglon.setDate(4, listaRenglones.get(i).getCaducidad());
				insertarRenglon.setFloat(5, listaRenglones.get(i).getPrecio());
				insertarRenglon.setInt(6, listaRenglones.get(i).getCodigoBarras());
				insertarRenglon.setInt(7, claveResurtir);
				insertarRenglon.executeUpdate();

				insertarAcomodo = con.prepareStatement(sqlAcomodar);
				insertarAcomodo.setDate(1, listaAcomodo.get(i).getFecha());
				insertarAcomodo.setInt(2, listaAcomodo.get(i).getCantidad());
				insertarAcomodo.setString(3, listaAcomodo.get(i).getLugar());
				insertarAcomodo.setFloat(4, numeroRenglon);
				insertarAcomodo.setInt(5, listaAcomodo.get(i).getClaveSucursal());
				insertarAcomodo.executeUpdate();
				numeroRenglon ++;
			}
			listaRenglones.clear();
			listaAcomodo.clear();

			con.commit();
			return true;
		}catch(Exception e) {
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(insertarResurtido != null){
				insertarResurtido.close();
			}
			if(insertarRenglon != null){
				insertarRenglon.close();
			}
		}
	}
	
	public boolean modificar(Surtido surtido, ArrayList<DetalleSurtido> listaRenglones, ArrayList<Acomodo> listaAcomodo, int renglon) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlResurtir = "update resurtir set fecha_res = ?, total_res = ?, cve_prov = ?, cve_tie = ? where cve_res = ?";
		String sqlRenglon = "update renglonresurtir set cant_renres = ?, baja_renres = ?, fcad_renres = ?, precio_renres = ? where codbar_pro = ? and cve_res = ?";
		String sqlAcomodar = "update acomodar set fecha_aco = ?, cant_aco = ?, lugar_aco = ? where num_ren = ?"; 
		PreparedStatement modificarResurtido = null, modificarRenglon = null, modificarAcomodo = null;

		try {
			con.setAutoCommit(false);
			modificarResurtido = con.prepareStatement(sqlResurtir);
			modificarResurtido.setDate(1, surtido.getFecha());
			modificarResurtido.setFloat(2, surtido.getTotal());
			modificarResurtido.setInt(3, surtido.getClaveProveedor());
			modificarResurtido.setInt(4, surtido.getClaveSucursal());
			modificarResurtido.setInt(5, surtido.getClave());
			modificarResurtido.executeUpdate();

			modificarRenglon = con.prepareStatement(sqlRenglon);
			for(int i = 0; i < listaRenglones.size(); i++) {

				modificarRenglon.setInt(1, listaRenglones.get(i).getCantidad());
				modificarRenglon.setInt(2, listaRenglones.get(i).getBaja());
				modificarRenglon.setDate(3, listaRenglones.get(i).getCaducidad());
				modificarRenglon.setFloat(4, listaRenglones.get(i).getPrecio());
				modificarRenglon.setInt(5, listaRenglones.get(i).getCodigoBarras());
				modificarRenglon.setInt(6,  surtido.getClave());
				modificarRenglon.executeUpdate();

				modificarAcomodo = con.prepareStatement(sqlAcomodar);
				modificarAcomodo.setDate(1, listaAcomodo.get(i).getFecha());
				modificarAcomodo.setInt(2, listaAcomodo.get(i).getCantidad());
				modificarAcomodo.setString(3, listaAcomodo.get(i).getLugar());
				modificarAcomodo.setInt(4, renglon);
				modificarAcomodo.executeUpdate();
			}
			listaRenglones.clear();
			listaAcomodo.clear();

			con.commit();
			return true;
		}catch(Exception e) {
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(modificarResurtido != null){
				modificarResurtido.close();
			}
			if(modificarRenglon != null){
				modificarRenglon.close();
			}
		}
	}

	public boolean buscar(Surtido surtido, DetalleSurtido detalleSurtido, Acomodo acomodar, int claveResurtir, int codigoBarras) {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql = "select resurtir.cve_res, fecha_res, total_res, cve_prov, resurtir.cve_tie, num_renres, cant_renres, baja_renres, "
				+ "fcad_renres, precio_renres, codbar_pro, num_aco, fecha_aco, cant_aco, lugar_aco, acomodar.cve_tie from resurtir join "
				+ "renglonresurtir on resurtir.cve_res = renglonresurtir.cve_res join acomodar on renglonresurtir.num_renres = acomodar.num_ren "
				+ "where resurtir.cve_res = " + claveResurtir + " and codbar_pro = " + codigoBarras;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()) {
				surtido.setClave(rs.getInt("cve_res"));
				surtido.setFecha(rs.getDate("fecha_res"));
				surtido.setTotal(rs.getFloat("total_res"));
				surtido.setClaveProveedor(rs.getInt("cve_prov"));
				surtido.setClaveSucursal(rs.getInt("cve_tie"));
				
				detalleSurtido.setNumeroRenglon(rs.getInt("num_renres"));
				detalleSurtido.setCantidad(rs.getInt("cant_renres"));
				detalleSurtido.setBaja(rs.getInt("baja_renres"));
				detalleSurtido.setCaducidad(rs.getDate("fcad_renres"));
				detalleSurtido.setPrecio(rs.getFloat("precio_renres"));
				detalleSurtido.setCodigoBarras(rs.getInt("codbar_pro"));
				
				acomodar.setNumeroAcomodo(rs.getInt("num_aco"));
				acomodar.setFecha(rs.getDate("fecha_aco"));
				acomodar.setCantidad(rs.getInt("cant_aco"));
				acomodar.setLugar(rs.getString("lugar_aco"));
				acomodar.setClaveSucursal(rs.getInt("cve_tie"));
				return true;
			}

			return false;
		} catch(Exception e) {
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				return false;
			}
		}
	}

	public void llenarTablaBusqueda(JTable visor, int claveResurtir){
		Statement st = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select producto.codbar_pro, nombre_pro, modelo_pro, marca_pro, cant_renres, fcad_renres, precio_renres, lugar_aco from resurtir join renglonresurtir on "
				+ "resurtir.cve_res = renglonresurtir.cve_res join producto on renglonresurtir.codbar_pro = producto.codbar_pro join acomodar on "
				+ "renglonresurtir.num_renres = acomodar.num_ren where resurtir.cve_res = " + claveResurtir;

		String[]columnas = {"Codigo de barras", "Nombre", "Modelo", "Marca", "Cantidad", "Caducidad", "Precio", "Lugar Acomodo"};
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
			
		}
	}

}

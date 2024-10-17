package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.Database;
import modelo.Contrato;
import modelo.DatoEnvio;
import modelo.Enviar;
import modelo.StatusEnvio;
import modelo.Vehiculo;
import modelo.ListaCarga;
import modelo.Persona;
import modelo.Placas;
import modelo.Status;


public class ConsultasEnvio extends Database{
	
	public int getLastIdVehiculo() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(ns_veh) as ns_veh from vehiculo";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("ns_veh");
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
	
/*	public int getLastIFolioContrato() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(folio_cont) as folio_cont from contrato";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("folio_cont");
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
	} */
	
	public int getLastIListaCarga() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(num_listcar) as num_listcar from listacarga";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("num_listcar");
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
	
	public int getLastIdDatoEnvio() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_datenv) as cve_datenv from datoenvio";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_datenv");
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
	
	public int getLastIdCveEnvio() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_env) as cve_env from enviar";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_env");
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
	
	public int getLastIdStatusEnvio() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(num_staen) as num_staen from statusenvio";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("num_staen");
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
	
	
	public boolean registrar(Enviar enviar, ListaCarga listaCarga, StatusEnvio statusEnvio) throws SQLException{
		Connection con = getConexion();
		String sqlEnviar;
		String sqlLista;
		String sqlStatus;
		sqlEnviar = "insert into enviar values(?, curdate(), ?, ?)";
		sqlLista = "insert into listacarga values(?, ?, ?)";
		sqlStatus = "insert into statusenvio values(?, ?, ?, ?)";
		PreparedStatement psEnv = null;
		PreparedStatement psLis = null;
		PreparedStatement psSta = null;
		
		int ultimoIdEnvio = getLastIdCveEnvio();
		int ultimoNsveh = getLastIdVehiculo();
		int ultimoListaCarga = getLastIListaCarga();
		int ultimoDatoEnvio = getLastIdDatoEnvio();
		int ultimaCveEnv = getLastIdCveEnvio();
		int ultimoStaEnv = getLastIdStatusEnvio();
		
		
		try {
			
			psEnv = con.prepareStatement(sqlEnviar);
			psEnv.setInt(1, ultimoIdEnvio);
			psEnv.setInt(2, enviar.getNs_veh());
			psEnv.setInt(3, enviar.getFolio_cont());

			psEnv.execute();
			
			psLis = con.prepareStatement(sqlLista);
			psLis.setInt(1, ultimoListaCarga );
			psLis.setInt(2, listaCarga.getCve_datenv());
			psLis.setInt(3, ultimaCveEnv);
			psLis.execute();
			
			psSta = con.prepareStatement(sqlStatus);
			psSta.setInt(1, ultimoStaEnv);
			psSta.setDate(2, statusEnvio.getFecha_staen());
			psSta.setString(3, statusEnvio.getStatus_staen());
			psSta.setInt(4, ultimoListaCarga);
			psSta.execute();
			
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
	
	public boolean modificar(Enviar enviar, ListaCarga listaCarga, StatusEnvio statusEnvio)throws  SQLException{
		PreparedStatement ps = null;
		PreparedStatement list = null;
		PreparedStatement sta = null;
		Connection con = getConexion();
		String sql;
		String sqllis;
		String sqlS;
		sql = "update enviar set fecha_env, ns_veh = ?, folio_cont = ? where cve_env = ?";
		sqllis = "update listacarga set cve_datenv = ?,  cve_env = ? where num_listcar = ?";
		sqlS = "update statusenvio set fecha_staen, status_staen = ?, num_listcar = ? where num_staen = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, enviar.getNs_veh());
			ps.setInt(2, enviar.getFolio_cont());
			ps.setInt(3, enviar.getCve_env());
			

			list = con.prepareStatement(sqllis);
			
			list.setInt(1, listaCarga.getCve_datenv());
			list.setInt(2, listaCarga.getCve_env());
			list.setInt(3, listaCarga.getNum_listcar());
			
			sta = con.prepareStatement(sqlS);
			sta.setString(1, statusEnvio.getStatus_staen()); 
			sta.setInt(2, statusEnvio.getNum_liscar());
			sta.setInt(3, statusEnvio.getNum_staen());
			
			ps.execute();
			list.execute();
			//sta.execute();
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
	
	public boolean eliminar(StatusEnvio statusEnvio)throws SQLException{
		
		PreparedStatement ps = null;
		
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		
		Connection con = getConexion();
		String sql2 = "delete from statusenvio where num_staen = ?";
		String sql1 = "delete from listacarga where num_listcar = ?";
		String sql0 = "delete from enviar where cve_env = ?";
		
		//String sql = "delete from statusenvio where num_staen = ? and delete from listacarga where num_listcar and delete from enviar  where cve_env";


		try {
			
			ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, statusEnvio.getNum_staen());
			ps2.execute();
			
			ps3 = con.prepareStatement(sql1);
			ps3.setInt(1, getLastIListaCarga());
			ps3.execute();
			
			ps4 = con.prepareStatement(sql0);
			ps4.setInt(1, getLastIdCveEnvio());
			ps4.execute();
			
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
	
	public boolean buscar(Enviar enviar, ListaCarga listaCarga, StatusEnvio statusEnvio){
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
	//	sql = "select * from listacarga inner join statusenvio on listacarga.num_listcar = statusenvio.num_listcar inner join enviar on listacarga.cve_env = enviar.cve_env where num_staen = ?";
		//sql = "select num_listcar, cve_datenv, ns_veh, folio_cont from listacarga join enviar on listacarga.cve_env = enviar.cve_env where enviar.cve_env = ? ";
		sql = "select num_staen, ns_veh, folio_cont,cve_datenv, fecha_staen, status_staen from listacarga join statusenvio on listacarga.num_listcar = statusenvio.num_listcar join enviar on listacarga.cve_env = enviar.cve_env where num_staen = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, statusEnvio.getNum_staen());
			rs = ps.executeQuery();
			if(rs.next()){
				//enviar.setCve_env(Integer.parseInt(rs.getString("cve_env")));
				enviar.setNs_veh(rs.getInt("ns_veh"));
				enviar.setFolio_cont(rs.getInt("folio_cont"));
				
				listaCarga.setCve_datenv(rs.getInt("cve_datenv"));
				//statusEnvio.setNum_liscar(rs.getInt("num_listcar"));
				
				statusEnvio.setFecha_staen(rs.getDate("fecha_staen"));
				statusEnvio.setStatus_staen(rs.getString("status_staen"));
				statusEnvio.setNum_staen(rs.getInt("num_staen"));
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
	
	//sql = "select enviar.cve_env, fecha_env, ns_veh, folio_cont, cve_datenv from listacarga join enviar on listacarga.cve_env = enviar.cve_env;";

	
	public void llenarTabla(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql = "select num_staen, enviar.cve_env, ns_veh, folio_cont, cve_datenv, fecha_staen, status_staen from enviar join listacarga on enviar.cve_env = listacarga.cve_env join statusenvio on listacarga.num_listcar = statusenvio.num_listcar";
				
		String[]columnas = {"Numero de status","Clave de envio","Numero de serie vehiculo", "Folio de contrato", "Clave de dato envio", "Fecha de status","Status del envio"};

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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void llenarTablaEstado(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql = "select listacarga.num_listcar, num_staen, fecha_staen, status_staen from listacarga join statusenvio on listacarga.num_listcar = statusenvio.num_listcar";

		String[]columnas = {"No.Lista","No.Status","Fecha lista","Descripcion"};

		DefaultTableModel model = new DefaultTableModel(null, columnas);
		visor.setModel(model);
		
		// Modificar ancho de columnas
		/*int[] anchos = {20, 50, 150, 20, 200, 30, 50, 100, 20};
		
		for(int x = 0; x <= 8; x++){
			visor.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
		}*/
		
		String [] datos = new String[6];
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				datos[0] = rs.getString(1);
				datos[1] = rs.getString(2);
				datos[2] = rs.getString(3);
				datos[3] = rs.getString(4);
				
				model.addRow(datos);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<DatoEnvio> getDatoEnvio(){
		Connection con = getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<DatoEnvio> listaDatos = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from datoenvio where cve_datenv not in(select cve_datenv from listacarga)");
			while (rs.next()) {
				DatoEnvio datoEnvio = new DatoEnvio();
				datoEnvio.setCve_datenv(rs.getInt("cve_datenv"));
				datoEnvio.setEntrecalles_datenv(rs.getString("entrecalles_datenv"));
				datoEnvio.setCalle_datenv(rs.getString("calle_datenv"));
				datoEnvio.setNum_datenv(rs.getString("num_datenv"));
				datoEnvio.setCve_col(rs.getInt("cve_col"));
				datoEnvio.setFolio_tic(rs.getInt("folio_tic"));
				datoEnvio.setOrientacion_datenv(rs.getString("orientacion_datenv"));
				listaDatos.add(datoEnvio);
			}
		} catch (Exception e) {
			
		}
		System.out.println(listaDatos);
		return listaDatos;
	}
	
	public int getFolioContrato(int clavePersona) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		System.out.println(clavePersona + "desde consultas");
		sql = "select folio_cont from contrato where cve_per in(select cve_per from persona where cve_per = " + clavePersona + ")";
		
		int folio = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				folio = rs.getInt("folio_cont");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch(SQLException e2) {
				System.err.println(e2);
			}
		}
		System.out.println(folio);
		return folio;
	}


	
	
	
}

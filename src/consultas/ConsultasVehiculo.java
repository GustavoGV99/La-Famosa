package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;

import database.Database;
import modelo.Placas;
import modelo.Sucursal;
import modelo.Vehiculo;
import modelo.Status;

public class ConsultasVehiculo extends Database {
	
	public int getLastIdCveVehiculo() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(ns_veh) as ns_veh from vehiculo";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				lastId = rs.getInt("ns_veh");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2);
			}
		}
		return lastId + 1;
	}
	
	public int getLastIdNumStatus() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(num_sta) as num_sta from status";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				lastId = rs.getInt("num_sta");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e2) {
				System.out.println(e2);
			}
		}
		return lastId + 1;
	}
	
	public boolean registrar(Vehiculo vehiculo, Placas placas, Status status) throws SQLException{
		Connection con = getConexion();
		String sqlRegistro;
		String sqlPlacas;
		String sqlStatus;
		sqlRegistro = "insert into vehiculo values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		sqlPlacas = "insert into placas values(?, curdate(), ?)";
		sqlStatus = "insert into status values(?, curdate(), ?, ?)";
		PreparedStatement psVeh = null;
		PreparedStatement psPla = null;
		PreparedStatement psSta = null;
		
		int ultimoDato = getLastIdCveVehiculo();
		int ultimoSta = getLastIdNumStatus();
		
		try {
			
			psVeh = con.prepareStatement(sqlRegistro);
			
			psVeh.setInt(1, ultimoDato);
			psVeh.setString(2, vehiculo.getModelo());
			psVeh.setString(3, vehiculo.getMarca());
			psVeh.setString(4, vehiculo.getColor());
			psVeh.setInt(5, vehiculo.getCantPuertas());
			psVeh.setDate(6, vehiculo.getFechavehiculo());
			psVeh.setFloat(7, vehiculo.getPreCompra());
			psVeh.setString(8, vehiculo.getTipoVeh());
			psVeh.setInt(9, vehiculo.getCve_tie());
			
			psVeh.execute();
			
			psPla = con.prepareStatement(sqlPlacas);
			
			psPla.setString(1, placas.getNum_pla());
			psPla.setInt(2, ultimoDato);
			
			psPla.execute();
			
			psSta = con.prepareStatement(sqlStatus);
			
			psSta.setInt(1, ultimoSta);
			psSta.setString(2, status.getStatus_sta());
			psSta.setInt(3, ultimoDato);
			
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
	
	public boolean modificar(Vehiculo vehiculo, Placas placas, Status status)throws  SQLException{
		PreparedStatement ps = null;
		PreparedStatement placa = null;
		PreparedStatement sta = null;
		Connection con = getConexion();
		String sql;
		String sqlP;
		String sqlS;
		sql = "update vehiculo set modelo_veh = ?, marca_veh = ?, color_veh = ?, cantpuertas_veh = ?, anio_veh = ?, preciocompra_veh = ?, tipo_veh = ?, cve_tie = ? where ns_veh = ?";
		sqlP = "update placas set ns_veh = ? where num_pla = ?";
		sqlS = "update status set status_sta = ?, ns_veh = ? where num_sta = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, vehiculo.getModelo());
			ps.setString(2, vehiculo.getMarca());
			ps.setString(3, vehiculo.getColor());
			ps.setInt(4, vehiculo.getCantPuertas());
			ps.setDate(5, vehiculo.getFechavehiculo());
			ps.setFloat(6, vehiculo.getPreCompra());
			ps.setString(7, vehiculo.getTipoVeh());
			ps.setInt(8, vehiculo.getCve_tie());
			ps.setInt(9, vehiculo.getNs_veh());
			ps.executeUpdate();
			System.out.println("Salida de statement modificar vehiculo");
			
			placa = con.prepareStatement(sqlP);
			
			placa.setInt(1, placas.getNs_veh());
			placa.setString(2, placas.getNum_pla());
			
			placa.executeUpdate();
			System.out.println("Salida de statement modificar placas");
			
			sta = con.prepareStatement(sqlS);
			
			sta.setString(1, status.getStatus_sta());
			sta.setInt(2, status.getNs_vehsta());
			sta.setInt(3, status.getNum_sta());
			
			sta.executeUpdate();
			System.out.println("Salida de statement modificar status");
			
			//sta.setInt(2, status.getNum_sta());
			
			ps.execute();
			placa.execute();
			sta.execute();
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
	
	public boolean eliminar(Placas placas)throws SQLException{
		PreparedStatement ps = null;
		//PreparedStatement ps2 = null;
		
		Connection con = getConexion();
		String sql;
		sql = "delete from placas where num_pla = ?";
		
		//String sql2;
		//sql2 = "delete from status where num_sta = ?";
		try {
			ps = con.prepareStatement(sql);
			//ps2 = con.prepareStatement(sql2);
			
			ps.setString(1, placas.getNum_pla());
			//ps2.setInt(1, status.getNum_sta());
			ps.execute();
			//ps2.execute();
			
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
	

	
	
	public boolean buscar(Vehiculo vehiculo, Placas placas, Status status){
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		//sql = "select vehiculo.ns_veh, modelo_veh, marca_veh, color_veh, cantpuertas_veh, anio_veh,preciocompra_veh,tipo_veh, cve_tie, num_pla from placas join vehiculo on placas.ns_veh = vehiculo.ns_veh where modelo_veh = ?";
		sql = "select * from vehiculo join placas on vehiculo.ns_veh = placas.ns_veh join status on placas.ns_veh = status.ns_veh where num_pla = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, placas.getNum_pla());
			rs = ps.executeQuery();

			if(rs.next()){
				vehiculo.setNs_veh(Integer.parseInt(rs.getString("ns_veh")));
				vehiculo.setModelo(rs.getString("modelo_veh"));
				vehiculo.setMarca(rs.getString("marca_veh"));
				vehiculo.setColor(rs.getString("color_veh"));
				vehiculo.setCantPuertas(Integer.parseInt(rs.getString("cantpuertas_veh")));
				vehiculo.setFechavehiculo(rs.getDate("anio_veh"));
				vehiculo.setPreCompra(Float.parseFloat(rs.getString("preciocompra_veh")));
				vehiculo.setTipoVeh(rs.getString("tipo_veh"));
				vehiculo.setCve_tie(rs.getInt("cve_tie"));
				
				placas.setNum_pla(rs.getString("num_pla"));
				placas.setNs_veh(Integer.parseInt(rs.getString("ns_veh")));
				
				status.setNum_sta(Integer.parseInt(rs.getString("num_sta")));
				status.setStatus_sta(rs.getString("status_sta"));
				status.setNs_vehsta(Integer.parseInt(rs.getString("ns_veh")));
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
	
	public void llenarTabla(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		//sql = "select ns_veh, modelo_veh, marca_veh, color_veh, cantpuertas_veh, anio_veh, preciocompra_veh, tipo_veh, cve_tie, num_pla from placas join vehiculo on placas.ns_veh = vehiculo.ns_veh;";
		//sql = "select num_pla, fecha_pla, modelo_veh, marca_veh, tipo_veh from vehiculo join placas on vehiculo.ns_veh=placas.ns_veh;";
		
		sql = "select vehiculo.ns_veh, modelo_veh, tipo_veh, num_pla, fecha_pla, status_sta, num_sta  from vehiculo inner join placas on vehiculo.ns_veh = placas.ns_veh inner join status on vehiculo.ns_veh = status.ns_veh";

		
		String[]columnas = {"No. Serie", "Modelo", "Tipo", "Numero de placas", "Fecha placas","Status", "No.Status"}; //,"Año vehiculo", "Costo", "Tipo vehiculo","Folio Sucursal","Numero Placas"};
		
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
	

	public ArrayList<Sucursal> getSucursales(){
		Connection con = getConexion();
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
	
	public ArrayList<Vehiculo> getVehiculos(){
		Connection con = getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from vehiculo");

			while(rs.next()){
				Vehiculo vehiculo = new Vehiculo();	
			
				vehiculo.setNs_veh(rs.getInt("ns_veh"));
				vehiculo.setModelo(rs.getString("modelo_veh"));
				vehiculo.setMarca(rs.getString("marca_veh"));
				vehiculo.setColor(rs.getString("color_veh"));
				vehiculo.setCantPuertas(rs.getInt("cantpuertas_veh"));
				vehiculo.setFechavehiculo(rs.getDate("anio_veh"));
				vehiculo.setPreCompra(rs.getFloat("preciocompra_veh"));
				vehiculo.setTipoVeh(rs.getString("tipo_veh"));
				vehiculo.setCve_tie(rs.getInt("cve_tie"));
				listaVehiculos.add(vehiculo);
			}

		} catch (Exception e) {

		}
		System.out.println(listaVehiculos);
		return listaVehiculos;
	}
	
	
}
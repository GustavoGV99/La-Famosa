package consultas;

import static utileria.Validaciones.validarCorreo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Contrato;
import modelo.Persona;
import modelo.Sucursal;
import database.Database;

public class ConsultasContrato {
	Database conexion = new Database();
	static String err = "";

	public int getLastIdPersona() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_per) as cve_per from persona";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("cve_per");
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
	
	public int getLastIdContrato() {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
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
	}
	
	public boolean registrarContrato(Persona persona, Contrato contrato) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlPersona = "insert into persona values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlContrato = "insert into contrato values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insertarPersona = null, insertarContrato = null;
		int ultimoIdPersona = getLastIdPersona();
		
		try {
			con.setAutoCommit(false);
			insertarPersona = con.prepareStatement(sqlPersona);
			insertarPersona.setInt(1, ultimoIdPersona);
			insertarPersona.setString(2, persona.getApellidoPaterno());
			insertarPersona.setString(3, persona.getApellidoMaterno());
			insertarPersona.setString(4, persona.getNombre());
			insertarPersona.setString(5, persona.getCalle());
			insertarPersona.setString(6, persona.getNumeroDomicilio());
			insertarPersona.setString(7, persona.getOrientacion());
			insertarPersona.setString(8, persona.getEntreCalles());
			if(!validarCorreo(persona.getMail())) {
				persona.setMail("");
			} else {
				insertarPersona.setString(9, persona.getMail());
			}
			insertarPersona.setString(10, persona.getSexo());
			insertarPersona.setDate(11, persona.getFechaNacimiento());
			insertarPersona.setString(12, persona.getEstadoCivil());
			insertarPersona.setInt(13, persona.getColonia());
			if(persona.getTelefono().length() < 10) {
				persona.setTelefono("");
			} else {
				insertarPersona.setString(14, persona.getTelefono());
			}
			insertarPersona.executeUpdate();
			
			insertarContrato = con.prepareStatement(sqlContrato);
			insertarContrato.setInt(1, getLastIdContrato());
			insertarContrato.setDate(2, contrato.getFechaInicio());
			insertarContrato.setDate(3, contrato.getFechaFin());
			insertarContrato.setString(4, contrato.getPuesto());
			insertarContrato.setFloat(5, contrato.getSueldo());
			insertarContrato.setString(6, contrato.getPeriodoSueldo());
			insertarContrato.setTime(7, contrato.getHoraEntrada());
			insertarContrato.setTime(8, contrato.getHoraSalida());
			insertarContrato.setTime(9, contrato.getHoraInicioComida());
			insertarContrato.setTime(10, contrato.getHoraFinComida());
			insertarContrato.setInt(11, contrato.getTienda());
			insertarContrato.setInt(12, ultimoIdPersona);
			insertarContrato.executeUpdate();
			
			con.commit();
			return true;
		} catch (Exception e) {
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(insertarPersona != null){
				insertarPersona.close();
			}
			if(insertarContrato != null){
				insertarContrato.close();
			}
		}
	}
	
	public boolean modificarContrato(Persona persona, Contrato contrato) throws SQLException {
		PreparedStatement modificarPersona = null, modificarContrato = null;
		Connection con = conexion.getConexion();
		String sqlPersona = "update persona set ap_per = ?, am_per = ?, nom_per = ?, calle_per = ?, numdomicilio_per = ?, orientacioncalle_per = ?, entrecalles_per = ?, "
				+ "mail_per = ?, sexo_per = ?, fnac_per = ?, edocivil_per = ?, cve_col = ?, tel_per = ? where cve_per = ?";
		String sqlContrato = "update contrato set fechainicio_con = ?, fechafin_cont = ?, puesto_cont = ?, sueldo_cont = ?, periodosueldo_cont = ?, hentrada = ?, "
				+ "hsalida = ?, hiniciocomida = ?, hfcomida = ?, cve_tie = ?, cve_per = ? where folio_cont = ?";
		
		try {
			con.setAutoCommit(false);
			modificarPersona = con.prepareStatement(sqlPersona);
			modificarPersona.setString(1, persona.getApellidoPaterno());
			modificarPersona.setString(2, persona.getApellidoMaterno());
			modificarPersona.setString(3, persona.getNombre());
			modificarPersona.setString(4, persona.getCalle());
			modificarPersona.setString(5, persona.getNumeroDomicilio());
			modificarPersona.setString(6, persona.getOrientacion());
			modificarPersona.setString(7, persona.getEntreCalles());
			if(!validarCorreo(persona.getMail())) {
				persona.setMail("");
			} else {
				modificarPersona.setString(8, persona.getMail());
			}
			modificarPersona.setString(9, persona.getSexo());
			modificarPersona.setDate(10, persona.getFechaNacimiento());
			modificarPersona.setString(11, persona.getEstadoCivil());
			modificarPersona.setInt(12, persona.getColonia());
			if(persona.getTelefono().length() < 10) {
				persona.setTelefono("");
			} else {
				modificarPersona.setString(13, persona.getTelefono());
			}
			modificarPersona.setInt(14, persona.getClave());
			modificarPersona.executeUpdate();
			
			modificarContrato = con.prepareStatement(sqlContrato);
			modificarContrato.setDate(1, contrato.getFechaInicio());
			modificarContrato.setDate(2, contrato.getFechaFin());
			modificarContrato.setString(3, contrato.getPuesto());
			modificarContrato.setFloat(4, contrato.getSueldo());
			modificarContrato.setString(5, contrato.getPeriodoSueldo());
			modificarContrato.setTime(6, contrato.getHoraEntrada());
			modificarContrato.setTime(7, contrato.getHoraSalida());
			modificarContrato.setTime(8, contrato.getHoraInicioComida());
			modificarContrato.setTime(9, contrato.getHoraFinComida());
			modificarContrato.setInt(10, contrato.getTienda());
			modificarContrato.setInt(11, contrato.getPersona());
			modificarContrato.setInt(12, contrato.getFolio());
			modificarContrato.executeUpdate();
			
			con.commit();
			return true;
		} catch (Exception e) {
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(modificarPersona != null){
				modificarPersona.close();
			}
			if(modificarContrato != null){
				modificarContrato.close();
			}
		}
	}
	
	public boolean buscarContrato(Persona persona, Contrato contrato){
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql = "select contrato.cve_per, ap_per, am_per, nom_per, calle_per, numdomicilio_per, orientacioncalle_per, entrecalles_per, "
				+ "mail_per, sexo_per, fnac_per, edocivil_per, colonia.cve_col, tel_per, contrato.folio_cont, fechainicio_con, fechafin_cont, "
				+ "puesto_cont, sueldo_cont, periodosueldo_cont, hentrada, hsalida, hiniciocomida, hfcomida, cve_tie, estado.cve_est, "
				+ "ciudad.cve_ciu, codigo.cp_cod, colonia.cve_col from contrato join persona on contrato.cve_per = persona.cve_per join colonia on "
				+ "persona.cve_col = colonia.cve_col join codigo on codigo.cp_cod = colonia.cp_cod join ciudad on codigo.cve_ciu = "
				+ "ciudad.cve_ciu join estado on ciudad.cve_est = estado.cve_est where ap_per = ?";
	
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, contrato.getApellidoPaterno());
			rs = ps.executeQuery();
			
			if(rs.next()){
				persona.setClave(Integer.parseInt(rs.getString("contrato.cve_per")));
				persona.setApellidoPaterno(rs.getString("ap_per"));
				persona.setApellidoMaterno(rs.getString("am_per"));
				persona.setNombre(rs.getString("nom_per"));
				persona.setCalle(rs.getString("calle_per"));
				persona.setNumeroDomicilio(rs.getString("numdomicilio_per"));
				persona.setOrientacion(rs.getString("orientacioncalle_per"));
				persona.setEntreCalles(rs.getString("entrecalles_per"));
				persona.setMail(rs.getString("mail_per"));
				persona.setSexo(rs.getString("sexo_per"));
				persona.setFechaNacimiento(rs.getDate("fnac_per"));
				persona.setEstadoCivil(rs.getString("edocivil_per"));
				persona.setColonia(Integer.parseInt(rs.getString("colonia.cve_col")));
				persona.setTelefono(rs.getString("tel_per"));
				
				contrato.setFolio(Integer.parseInt(rs.getString("folio_cont")));
				contrato.setFechaInicio(rs.getDate("fechainicio_con"));
				contrato.setFechaFin(rs.getDate("fechafin_cont"));
				contrato.setPuesto(rs.getString("puesto_cont"));
				contrato.setSueldo(Float.parseFloat(rs.getString("sueldo_cont")));
				contrato.setPeriodoSueldo(rs.getString("periodosueldo_cont"));
				contrato.setHoraEntrada(rs.getTime("hentrada"));
				contrato.setHoraSalida(rs.getTime("hsalida"));
				contrato.setHoraInicioComida(rs.getTime("hiniciocomida"));
				contrato.setHoraFinComida(rs.getTime("hfcomida"));
				contrato.setTienda(rs.getInt("cve_tie"));
				contrato.setPersona(rs.getInt("contrato.cve_per"));
				
				persona.setEstado(rs.getInt("estado.cve_est"));
				persona.setCiudad(rs.getInt("ciudad.cve_ciu"));
				persona.setCodigoPostal(rs.getInt("codigo.cp_cod"));
				return true;
			}
			
			return false;
		} catch (Exception e) {
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
	
	public boolean eliminarContrato(Contrato contrato) throws SQLException{
		PreparedStatement eliminarContrato = null;
		Connection con = conexion.getConexion();
		String sqlContrato = "delete from contrato where folio_cont = ?";
		
		try {
			eliminarContrato = con.prepareStatement(sqlContrato);
			eliminarContrato.setInt(1, contrato.getFolio());
			eliminarContrato.execute();
			System.out.println("Después de statement contrato");

			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if(eliminarContrato != null){
				eliminarContrato.close();
			}
		}
	}
	
	public void llenarTablaContrato(JTable visor){
		Statement st = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql = "select folio_cont, ap_per, am_per, nom_per, tel_per, fechafin_cont, hentrada, hsalida from persona join contrato on persona.cve_per = "
				+ "contrato.cve_per";

		String[]columnas = {"Folio", "Paterno", "Materno", "Nombre", "Telefono", "Fin Contrato", "Entrada", "Salida"};
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<Contrato> getContrato(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Contrato> listaContrato = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from contrato");

			while(rs.next()){
				Contrato contrato = new Contrato();	
				contrato.setFolio(rs.getInt("folio_cont"));
				contrato.setFechaInicio(rs.getDate("fechainicio_con"));
				contrato.setFechaFin(rs.getDate("fechafin_cont"));
				contrato.setPuesto(rs.getString("puesto_cont"));
				contrato.setSueldo(rs.getFloat("sueldo_cont"));
				contrato.setPeriodoSueldo(rs.getString("periodosueldo_cont"));
				contrato.setHoraEntrada(rs.getTime("hentrada"));
				contrato.setHoraSalida(rs.getTime("hsalida"));
				contrato.setHoraInicioComida(rs.getTime("hiniciocomida"));
				contrato.setHoraFinComida(rs.getTime("hfcomida"));
				contrato.setTienda(rs.getInt("cve_tie"));
				contrato.setPersona(rs.getInt("cve_per"));
				listaContrato.add(contrato);
			}

		} catch (Exception e) {

		}
		return listaContrato;
	}
	
	public ArrayList<Persona> getChofer(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Persona> listaChofer = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from contrato join persona on contrato.cve_per = persona.cve_per where puesto_cont = 'Chofer'");

			while(rs.next()){
				Persona persona = new Persona();
				persona.setClave(rs.getInt("cve_per"));
				persona.setApellidoPaterno(rs.getString("ap_per"));
				persona.setApellidoMaterno(rs.getString("am_per"));
				persona.setNombre(rs.getString("nom_per"));
				persona.setCalle(rs.getString("calle_per"));
				persona.setNumeroDomicilio(rs.getString("numdomicilio_per"));
				persona.setOrientacion(rs.getString("orientacioncalle_per"));
				persona.setEntreCalles(rs.getString("entrecalles_per"));
				persona.setMail(rs.getString("mail_per"));
				persona.setSexo(rs.getString("sexo_per"));
				persona.setFechaNacimiento(rs.getDate("fnac_per"));
				persona.setEstadoCivil(rs.getString("edocivil_per"));
				persona.setColonia(rs.getInt("cve_col"));
				persona.setTelefono(rs.getString("tel_per"));
				listaChofer.add(persona);
			}
		} catch (Exception e) {
			
		}
		return listaChofer;
	}


	   public static String getMensajes() {
	    	String errores = err;
	    	err = " ";
	    	return errores;
	    }
}

package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Cliente;
import modelo.Contrato;
import modelo.Persona;
import database.Database;

public class ConsultasCliente extends Database{
	
	public int getLastIdPer() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_per) as cve_per from persona";
		int lastIdPer = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastIdPer = rs.getInt("cve_per");
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
		return lastIdPer + 1;
	}
	
	public int getLastIdCli() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(cve_cli) as cve_cli from cliente";
		int lastIdCli = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastIdCli = rs.getInt("cve_cli");
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
		return lastIdCli + 1;
	}
	
	public boolean registrarCliente(Persona persona, Cliente cliente) throws SQLException {
		Connection con = getConexion();
		String sqlPersona = "insert into persona values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlCliente = "insert into cliente values(?, ?, ?)";
		PreparedStatement insertarPersona = null, insertarCliente = null;
		int ultimoIdPersona = getLastIdPer();
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
			insertarPersona.setString(9, persona.getMail());
			insertarPersona.setString(10, persona.getSexo());
			insertarPersona.setDate(11, persona.getFechaNacimiento());
			insertarPersona.setString(12, persona.getEstadoCivil());
			insertarPersona.setInt(13, persona.getColonia());
			insertarPersona.setString(14, persona.getTelefono());
			insertarPersona.executeUpdate();
			System.out.println("Salida de statement persona");
			
			insertarCliente = con.prepareStatement(sqlCliente);
			insertarCliente.setInt(1, getLastIdCli());
			insertarCliente.setDate(2, cliente.getFecha_cli());
			insertarCliente.setInt(3, ultimoIdPersona);
			insertarCliente.executeUpdate();
			System.out.println("Salida de statement cliente");
			
			con.commit();
			System.out.println("Después de commit");
			return true;
		} catch (Exception e) {
			con.rollback();
			System.out.println("Despues de rollback");
			return false;
		} finally {
			con.setAutoCommit(true);
			System.out.println("Despues de setAutoCommit(true)");
			if(insertarPersona != null){
				insertarPersona.close();
			}
			if(insertarCliente != null){
				insertarCliente.close();
			}
		}
	}
	
	public boolean modificarCliente(Persona persona, Cliente cliente) throws SQLException {
		PreparedStatement modificarPersona = null, modificarCliente = null;
		Connection con = getConexion();
		String sqlPersona = "update persona set ap_per = ?, am_per = ?, nom_per = ?, calle_per = ?, numdomicilio_per = ?, orientacioncalle_per = ?, entrecalles_per = ?, mail_per = ?, sexo_per = ?, fnac_per = ?, edocivil_per = ?, cve_col = ?, tel_per = ? where cve_per = ?";
		String sqlCliente = "update cliente set fecha_cli = ?, cve_cli=? where cve_cli = ?";
		
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
			modificarPersona.setString(8, persona.getMail());
			modificarPersona.setString(9, persona.getSexo());
			modificarPersona.setDate(10, persona.getFechaNacimiento());
			modificarPersona.setString(11, persona.getEstadoCivil());
			modificarPersona.setInt(12, persona.getColonia());
			modificarPersona.setString(13, persona.getTelefono());
			modificarPersona.setInt(14, persona.getClave());
			modificarPersona.executeUpdate();
			System.out.println("Salida de statement modificar persona");
			
			modificarCliente = con.prepareStatement(sqlCliente);
			modificarCliente.setInt(1, cliente.getCve_cli());
			modificarCliente.setDate(2, cliente.getFecha_cli());
			modificarCliente.setInt(3, cliente.getCve_per());

			modificarCliente.executeUpdate();
			System.out.println("Salida de statement modificar cliente");
			
			con.commit();
			System.out.println("Después de commit");
			return true;
		} catch (Exception e) {
			con.rollback();
			System.out.println("Despues de rollback");
			return false;
		} finally {
			con.setAutoCommit(true);
			System.out.println("Despues de setAutoCommit(true)");
			if(modificarPersona != null){
				modificarPersona.close();
			}
			if(modificarCliente != null){
				modificarCliente.close();
			}
		}
	}
	
	public boolean buscarCliente(Persona persona, Cliente cliente){
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql = "select cliente.cve_per, ap_per, am_per, nom_per, calle_per, numdomicilio_per, orientacioncalle_per, entrecalles_per, mail_per, sexo_per, fnac_per, edocivil_per, cve_col, tel_per, cve_cli, fecha_cli from persona join cliente on persona.cve_per = cliente.cve_per where cve_cli = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cliente.getCve_cli());
			rs = ps.executeQuery();
			
			if(rs.next()){
				persona.setClave(Integer.parseInt(rs.getString("cve_per")));
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
				persona.setColonia(Integer.parseInt(rs.getString("cve_col")));
				persona.setTelefono(rs.getString("tel_per"));
				
				cliente.setCve_cli(Integer.parseInt(rs.getString("cve_cli")));
				cliente.setFecha_cli(rs.getDate("fecha_cli"));
				cliente.setCve_per(rs.getInt("cve_per"));
				
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
	
		public boolean eliminarCliente(Cliente cliente) throws SQLException{
			PreparedStatement eliminarCliente = null;
			Connection con = getConexion();
//			String sqlPersona = "delete from persona where cve_per in(select cve_per from cliente where cve_cli = ?)";
			String sqlCliente = "delete from cliente where cve_cli = ?";
			
			try {
				eliminarCliente = con.prepareStatement(sqlCliente);
				eliminarCliente.setInt(1, cliente.getCve_cli());
				eliminarCliente.execute();
				System.out.println("Después de statement cliente");

				return true;
			} catch (Exception e) {
				return false;
			} finally {
				if(eliminarCliente != null){
					eliminarCliente.close();
				}
			}
		}
	
	public void llenarTablaCliente(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql = "select cliente.cve_cli, ap_per, am_per, nom_per, tel_per, cliente.fecha_cli from persona join cliente on persona.cve_per = cliente.cve_per";
		String[]columnas = {"Folio", "Paterno", "Materno", "Nombre", "Telefono", "Fecha Registro"};
		DefaultTableModel model = new DefaultTableModel(null, columnas);
		visor.setModel(model);
		
		String [] datos = new String[6];
		
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
				model.addRow(datos);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cliente> getCliente(){
		Connection con = getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Cliente> listaCliente = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from cliente");

			while(rs.next()){
				Cliente cliente = new Cliente();	
				cliente.setCve_cli(rs.getInt("cve_cli"));
				cliente.setFecha_cli(rs.getDate("fecha_cli"));
				cliente.setCve_per(rs.getInt("cve_per"));
				listaCliente.add(cliente);
			}

		} catch (Exception e) {

		}
		return listaCliente;
	}
	
	//public void checador CONSULTA UN EMPLEADO EN CONCRETO SIN CONTEMPLAR FECHA
	/*select ap_per, am_per, nom_per, puesto_cont, hora_che, tipo_che from persona join contrato on persona.cve_per = contrato.cve_per join checador on contrato.folio_cont = checador.folio_cont where checador.folio_cont = 1;*/
	
}

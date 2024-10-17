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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import database.Database;
import modelo.Estado;
import modelo.Ticket;
import modelo.Cliente;
import modelo.Contrato;
import modelo.Sucursal;
//import modelo.Producto;

public class ConsultasVenta extends Database {
	
	public int getLastId() {
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select max(folio_tic) as folio_tic from ticket";
		int lastId = 0;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if(rs.next()){
				lastId = rs.getInt("folio_tic");
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

	public boolean registrar(Ticket venta){
		PreparedStatement insertarTicket = null;
		Connection con = getConexion();
		String sqlTicket;

		sqlTicket = "insert into ticket values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			insertarTicket = con.prepareStatement(sqlTicket);
			insertarTicket.setInt(1, getLastId());
			insertarTicket.setDate(2, venta.getFechaventa_tic());
			insertarTicket.setDate(3, venta.getFfingarantia_tic());
			insertarTicket.setFloat(4, venta.getPreciovta_tic());
			insertarTicket.setInt(5, venta.getDescuento_tic());
			insertarTicket.setInt(6, venta.getCantidad_tic());
			insertarTicket.setString(7, venta.getFormapago_tic());
			insertarTicket.setInt(8, venta.getNumtar_tic());
			insertarTicket.setString(9, venta.getInstitucionbancaria_tic());
			insertarTicket.setInt(10, venta.getFolioauntbanco_tic());
			insertarTicket.setInt(11, venta.getCodbar_pro());
			insertarTicket.setInt(12, venta.getFolio_cont());
			insertarTicket.setInt(13, venta.getCve_tie());
			insertarTicket.setInt(14, venta.getCve_cli());
			insertarTicket.execute();
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

	public boolean buscar(Ticket venta){
		PreparedStatement ps = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select * from ticket where folio_tic = ?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, venta.getFolio());
			rs = ps.executeQuery();

			// Usamos if porque sólo vamos a traer una fila, cuando son más usamos while
			if(rs.next()){
				venta.setFolio(Integer.parseInt(rs.getString("folio_tic")));
				venta.setFechaventa_tic(rs.getDate("fechaventa_tic"));
				venta.setFfingarantia_tic(rs.getDate("ffingarantia_tic"));
				venta.setPreciovta_tic(rs.getFloat("preciovta_tic"));
				venta.setDescuento_tic(rs.getInt("descuento_tic"));
				venta.setCantidad_tic(rs.getInt("cant_tic"));
				venta.setFormapago_tic(rs.getString("formapago_tic"));
				venta.setInstitucionbancaria_tic(rs.getString("insitucionbancaria_tic"));
				venta.setNumtar_tic(rs.getInt("numtar_tic"));
				venta.setFolioauntbanco_tic(rs.getInt("folioauntbanco_tic"));
				venta.setCodbar_pro(Integer.parseInt(rs.getString("codbar_pro")));
				venta.setFolio_cont(Integer.parseInt(rs.getString("folio_cont")));
				venta.setCve_tie(Integer.parseInt(rs.getString("cve_tie")));
				venta.setCve_cli(Integer.parseInt(rs.getString("cve_cli")));
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

	public void llenarTablaVenta(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select folio_tic, fechaventa_tic, ffingarantia_tic, preciovta_tic, descuento_tic, cant_tic, formapago_tic from ticket";

		String[]columnas = {"Id", "Fecha Venta", "Fin Garantia", "Precio Venta", "Descuento", "Cantidad", "Forma de Pago"};
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
//				datos[7] = rs.getString(8);
				model.addRow(datos);
			}
			//visor.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Ticket> getVentas(){
		Connection con = getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Ticket> listaVentas = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from ticket");

			while(rs.next()){
				Ticket venta = new Ticket();	
				venta.setFolio(rs.getInt("folio_tic"));
				venta.setFechaventa_tic(rs.getDate("fechaventa_tic"));
				venta.setFfingarantia_tic(rs.getDate("ffingarantia_tic"));
				venta.setPreciovta_tic(rs.getFloat("preciovta_tic"));
				venta.setDescuento_tic(rs.getInt("descuento_tic"));
				venta.setCantidad_tic(rs.getInt("cant_tic"));
				venta.setFormapago_tic(rs.getString("formapago_tic"));
				venta.setNumtar_tic(rs.getInt("numtar_tic"));
				venta.setInstitucionbancaria_tic(rs.getString("insitucionbancaria_tic"));
				venta.setFolioauntbanco_tic(rs.getInt("folioauntbanco_tic"));
				venta.setCodbar_pro(rs.getInt("codbar_pro"));
				venta.setFolio_cont(rs.getInt("folio_cont"));
				venta.setCve_tie(rs.getInt("cve_tie"));
				venta.setCve_cli(rs.getInt("cve_cli"));
				listaVentas.add(venta);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaVentas ;
	}

/*	public void listarVentas(JComboBox boxTicket) {
		DefaultComboBoxModel value;
		Connection con = getConexion();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from ticket order by folio_tic asc");
			value = new DefaultComboBoxModel();
			boxTicket.setModel(value);
			
			while(rs.next()) {
				value.addElement(new Venta(rs.getInt(1)));
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
*/
}

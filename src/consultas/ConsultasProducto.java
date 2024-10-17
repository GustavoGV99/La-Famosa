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
import modelo.DetalleProducto;
import modelo.Producto;
import modelo.Sucursal;

public class ConsultasProducto {
	Database conexion = new Database();
	static String errores = "\tERROR\n";
		
	public boolean registrar(Producto producto, ArrayList<DetalleProducto> listaMinMax) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlProducto = "insert into producto values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sqlMinMax = "insert into minmax values(null, ?, ?, ?, ?)";
		PreparedStatement insertarProducto = null, insertarMinMax = null;
		
		try {
			con.setAutoCommit(false);
			insertarProducto = con.prepareStatement(sqlProducto);
			if(existe(producto.getCodigoBarras())) {
				producto.setCodigoBarras(-1);
			} else {
				if(String.valueOf(producto.getCodigoBarras()).length() < 7) {
					producto.setCodigoBarras(-1);
				} else if(String.valueOf(producto.getCodigoBarras()).length() == 7) {
					insertarProducto.setInt(1, producto.getCodigoBarras());	
				}
			}
			insertarProducto.setString(2, producto.getNombre());
			insertarProducto.setString(3, producto.getTipo());
			insertarProducto.setString(4, producto.getMarca());
			insertarProducto.setString(5, producto.getColor());
			insertarProducto.setString(6, producto.getGarantia());
			insertarProducto.setString(7, producto.getMedidaGarantia());
			insertarProducto.setString(8, producto.getPresentacion());
			insertarProducto.setString(9, producto.getModelo());
			insertarProducto.setString(10, producto.getAlto());
			insertarProducto.setString(11, producto.getAncho());
			insertarProducto.setString(12, producto.getLargo());
			insertarProducto.setString(13, producto.getContenido());
			insertarProducto.setString(14, producto.getuMedida());
			insertarProducto.executeUpdate();
			
			insertarMinMax = con.prepareStatement(sqlMinMax);
			for(int i = 0; i < listaMinMax.size(); i++) {
				insertarMinMax.setInt(1, listaMinMax.get(i).getMinimo());
				insertarMinMax.setInt(2, listaMinMax.get(i).getMaximo());
				insertarMinMax.setInt(3, producto.getCodigoBarras());
				insertarMinMax.setInt(4, listaMinMax.get(i).getClaveSucursal());
				insertarMinMax.executeUpdate();
			}
			listaMinMax.clear();
			con.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(insertarProducto != null){
				insertarProducto.close();
			}
			if(insertarMinMax != null){
				insertarMinMax.close();
			}
		}
	}
	
	public boolean modificar(Producto producto, ArrayList<DetalleProducto> listaMinMax) throws SQLException {
		Connection con = conexion.getConexion();
		String sqlProducto = "update producto set nombre_pro = ?, tipo_pro = ?, marca_pro = ?, color_pro = ?, garantia_pro = ?, umedidagarantia = ?, "
				+ "presentacion_pro = ?, modelo_pro = ?, alto_pro = ?, ancho_pro = ?, largo_pro = ?, contenido_pro = ?, umedida_pro = ? where codbar_pro = ?";
		String sqlMinMax = "update minmax set  min_minmax = ?, max_minmax = ? where codbar_pro = ? and cve_tie = ?";
		PreparedStatement modificarProducto = null, modificarMinMax = null;
		
		try {
			modificarProducto = con.prepareStatement(sqlProducto);
			modificarProducto.setString(1, producto.getNombre());
			modificarProducto.setString(2, producto.getTipo());
			modificarProducto.setString(3, producto.getMarca());
			modificarProducto.setString(4, producto.getColor());
			modificarProducto.setString(5, producto.getGarantia());
			modificarProducto.setString(6, producto.getMedidaGarantia());
			modificarProducto.setString(7, producto.getPresentacion());
			modificarProducto.setString(8, producto.getModelo());
			modificarProducto.setString(9, producto.getAlto());
			modificarProducto.setString(10, producto.getAncho());
			modificarProducto.setString(11, producto.getLargo());
			modificarProducto.setString(12, producto.getContenido());
			modificarProducto.setString(13, producto.getuMedida());
			con.setAutoCommit(false);
			if(String.valueOf(producto.getCodigoBarras()).length() < 7) {
				producto.setCodigoBarras(-1);
			} else if(String.valueOf(producto.getCodigoBarras()).length() == 7) {
				modificarProducto.setInt(14, producto.getCodigoBarras());
			}
			
			modificarProducto.executeUpdate();
			
			modificarMinMax = con.prepareStatement(sqlMinMax);
			for(int i = 0; i < listaMinMax.size(); i++) {
				modificarMinMax.setInt(1, listaMinMax.get(i).getMinimo());
				modificarMinMax.setInt(2, listaMinMax.get(i).getMaximo());
				modificarMinMax.setInt(3, listaMinMax.get(i).getCodigoBarras());
				modificarMinMax.setInt(4, listaMinMax.get(i).getClaveSucursal());
				modificarMinMax.executeUpdate();
			}
			listaMinMax.clear();
			con.commit();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(modificarProducto != null){
				modificarProducto.close();
			}
			if(modificarMinMax != null){
				modificarMinMax.close();
			}
		}
	}
	
	public boolean buscar(Producto producto, DetalleProducto minmax, int claveSucursal) {
		PreparedStatement ps = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql = "select producto.codbar_pro, nombre_pro, tipo_pro, marca_pro, color_pro, garantia_pro, umedidagarantia, "
				+ "presentacion_pro, modelo_pro, alto_pro, ancho_pro, largo_pro, contenido_pro, umedida_pro, num_minmax, min_minmax, "
				+ "max_minmax, cve_tie from producto join minmax on producto.codbar_pro = minmax.codbar_pro where producto.codbar_pro = ? "
				+ "and cve_tie = "+ claveSucursal;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, producto.getCodigoBarras());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				producto.setCodigoBarras(rs.getInt("codbar_pro"));
				producto.setNombre(rs.getString("nombre_pro"));
				producto.setTipo(rs.getString("tipo_pro"));
				producto.setMarca(rs.getString("marca_pro"));
				producto.setColor(rs.getString("color_pro"));
				producto.setGarantia(rs.getString("garantia_pro"));
				producto.setMedidaGarantia(rs.getString("umedidagarantia"));
				producto.setPresentacion(rs.getString("presentacion_pro"));
				producto.setModelo(rs.getString("modelo_pro"));
				producto.setAlto(rs.getString("alto_pro"));
				producto.setAncho(rs.getString("ancho_pro"));
				producto.setLargo(rs.getString("largo_pro"));
				producto.setContenido(rs.getString("contenido_pro"));
				producto.setuMedida(rs.getString("umedida_pro"));
				
				minmax.setNumero(rs.getInt("num_minmax"));
				minmax.setMinimo(rs.getInt("min_minmax"));
				minmax.setMaximo(rs.getInt("max_minmax"));
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
	
	public boolean eliminar(Producto producto) throws SQLException{
		PreparedStatement eliminarMinMax = null, eliminarProducto = null;
		Connection con = conexion.getConexion();
		String sqlMinMax = "delete from minmax where codbar_pro = ?";
		String sqlProducto = "delete from producto where codbar_pro = ?";
		try {
			con.setAutoCommit(false);
			eliminarMinMax = con.prepareStatement(sqlMinMax);
			eliminarMinMax.setInt(1, producto.getCodigoBarras());
			eliminarMinMax.execute();
			
			eliminarProducto = con.prepareStatement(sqlProducto);
			eliminarProducto.setInt(1, producto.getCodigoBarras());
			eliminarProducto.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			con.rollback();
			return false;
		} finally {
			con.setAutoCommit(true);
			if(eliminarMinMax != null){
				eliminarMinMax.close();
			}
			if(eliminarProducto != null){
				eliminarProducto.close();
			}
		}
	}
	
	public void llenarTabla(JTable visor){
		Statement st = null;
		Connection con = conexion.getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select codbar_pro, nombre_pro, modelo_pro, tipo_pro, marca_pro, garantia_pro, umedidagarantia, presentacion_pro from producto;";

		String[]columnas = {"Codigo de barras", "Nombre", "Modelo", "Tipo", "Marca", "Garantia", "Plazo", "Presentacion"};
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
	
	public ArrayList<Producto> getProductos(){
		Connection con = conexion.getConexion();
		Statement st;
		ResultSet rs;
		ArrayList<Producto> listaProductos = new ArrayList<>();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select * from producto");

			while(rs.next()){
				Producto producto = new Producto();	
				producto.setCodigoBarras(rs.getInt("codbar_pro"));
				producto.setNombre(rs.getString("nombre_pro"));
				producto.setTipo(rs.getString("tipo_pro"));
				producto.setMarca(rs.getString("marca_pro"));
				producto.setColor(rs.getString("color_pro"));
				producto.setGarantia(rs.getString("garantia_pro"));
				producto.setMedidaGarantia(rs.getString("umedidagarantia"));
				producto.setPresentacion(rs.getString("presentacion_pro"));
				producto.setModelo(rs.getString("modelo_pro"));
				producto.setAlto(rs.getString("alto_pro"));
				producto.setAncho(rs.getString("ancho_pro"));
				producto.setLargo(rs.getString("largo_pro"));
				producto.setContenido(rs.getString("contenido_pro"));
				producto.setuMedida(rs.getString("umedida_pro"));
				listaProductos.add(producto);
			}

		} catch (Exception e) {
			
		}
		return listaProductos;
	}
	
    public boolean existe(int codigoBarras) {
		try {
			Connection con = conexion.getConexion();
			Statement st = null;
			st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from producto where codbar_pro = " + codigoBarras);
			return rs.next() == true ? true : false;
		}catch(java.sql.SQLException e) {
			return false;
		}
	}
    
    public static String getMensajes() {
    	String err = errores;
    	errores = "";
    	return err;
    }
    

}

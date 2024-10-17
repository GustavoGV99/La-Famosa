package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Sucursal;

import com.mysql.cj.jdbc.CallableStatement;

import database.Database;

public class ConsultasInventario extends Database {

	public void llenarTablaInventario(JTable visor){
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql = "select producto.codbar_pro, nombre_pro, tipo_pro, marca_pro, color_pro, garantia_pro, umedidagarantia, tienda.cve_tie, renglonresurtir.baja_renres from producto join renglonresurtir on producto.codbar_pro=renglonresurtir.codbar_pro join resurtir on renglonresurtir.cve_res=resurtir.cve_res join tienda on resurtir.cve_tie=tienda.cve_tie";
		String[]columnas = {"Codigo", "Nombre", "Tipo", "Marca", "Color", "Garantia", "UMedidaGarantia", "Sucursal","Cantidad"};
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
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void filtrarProducto(String nombre, JTable visor) {
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select producto.codbar_pro, nombre_pro, tipo_pro, marca_pro, color_pro, garantia_pro, umedidagarantia , tienda.cve_tie, renglonresurtir.baja_renres from producto join renglonresurtir on producto.codbar_pro = renglonresurtir.codbar_pro join resurtir on renglonresurtir.cve_res = resurtir.cve_res join tienda on resurtir.cve_tie = tienda.cve_tie where producto.nombre_pro = '"+ nombre +"'";

		String[]columnas = {"Codigo", "Nombre", "Tipo", "Marca", "Color", "Garantia", "UMedidaGarantia", "Sucursal","Cantidad"};
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

	public void filtrarPorTienda(int cve_tie, JTable visor) {
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = " select producto.codbar_pro, nombre_pro, tipo_pro, marca_pro, color_pro, garantia_pro, umedidagarantia , tienda.cve_tie, renglonresurtir.baja_renres from producto join renglonresurtir on producto.codbar_pro = renglonresurtir.codbar_pro join resurtir on renglonresurtir.cve_res = resurtir.cve_res join tienda on resurtir.cve_tie = tienda.cve_tie where tienda.cve_tie = "+cve_tie;

		String[]columnas = {"Codigo", "Nombre", "Tipo", "Marca", "Color", "Garantia", "UMedidaGarantia", "Sucursal"};
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
			e.printStackTrace();
		}
	}

	public void filtrarProductoTienda(int cve_tie, String nombre, JTable visor) {
		Statement st = null;
		Connection con = getConexion();
		ResultSet rs = null;
		String sql;
		sql = "select producto.codbar_pro, nombre_pro, tipo_pro, marca_pro, color_pro, garantia_pro, umedidagarantia , "
				+ "tienda.cve_tie, renglonresurtir.baja_renres from producto join renglonresurtir on producto.codbar_pro "
				+ "= renglonresurtir.codbar_pro join resurtir on renglonresurtir.cve_res = resurtir.cve_res join tienda on "
				+ "resurtir.cve_tie = tienda.cve_tie where tienda.cve_tie = " + cve_tie + " and producto.nombre_pro = '"+ nombre +"'";

		String[]columnas = {"Codigo", "Nombre", "Tipo", "Marca", "Color", "Garantia", "UMedidaGarantia", "Sucursal"};
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
			e.printStackTrace();
		}
	} 

}

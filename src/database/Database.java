package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	private String base = "la_famosa";
	private String user = "root";
	private String psw = "7183";
	private String url = "jdbc:mysql://localhost:3306/" + base + "?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&useTimezone=true&serverTimezone=UTC";
	private Connection con = null;
	
	public Connection getConexion(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(this.url, this.user, this.psw);
		} catch (Exception e) {
			System.err.println("Error en Database" + e);
		} 

		return con;
	}
	
}

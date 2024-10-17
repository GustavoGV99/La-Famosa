package utileria;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class DateAndTime {
	//Time 
	public static java.sql.Time stringToTime(String hora){
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		java.sql.Time timeValue;
		try {
			timeValue = new java.sql.Time(formatter.parse(hora).getTime());
			return timeValue;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date stringToUtil(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaUtil = null;
		try {
			fechaUtil = formato.parse(fecha);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null, "Error en la fecha");
			return null;
		}
		return fechaUtil;
	}
	
	public static java.sql.Date utilToSQL(Date fecha) {
		long timeInMilliSeconds = fecha.getTime();
		java.sql.Date dateSQL = new java.sql.Date(timeInMilliSeconds);
		return dateSQL;
	}
	
	public static String SQLToString(java.sql.Date formatoSQL) {
		String[] splitFecha = String.valueOf(formatoSQL).split("-");
		String fecha = splitFecha[2] + "/" + splitFecha[1] + "/" + splitFecha[0];
		return fecha;
	}
	
	public static String TimeToString(Time horaTime) {
		String[] splitHora = String.valueOf(horaTime).split(":");
		String hora = splitHora[0] + ":" + splitHora[1];
		return hora;
	}

}

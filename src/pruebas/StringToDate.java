package pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringToDate {

	public static void main(String[] args) {
		Date utilDate = stringToUtil("17/02/2001");
		System.out.println("String to Util Date: " + utilDate);
		System.out.println("Util Date to SQL Date: " + utilToSQL(utilDate));
	}

	// Forma uno
	public static LocalDate stringToDate(String fechaString){
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(fechaString, formato);
		return fecha;
	}

	// Forma 2
	public static Date stringToUtil(String fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaDate = null;
		try {
			fechaDate = formato.parse(fecha);
		} 
		catch (ParseException ex) 
		{
			System.out.println(ex);
		}
		return fechaDate;
	}
	
	// Forma 2.1 -> Parte de forma 2
	public static java.sql.Date utilToSQL(Date fecha) {
		long timeInMilliSeconds = fecha.getTime();
		java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
		return date1;
	}
}

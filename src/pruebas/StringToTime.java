package pruebas;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToTime {
	
	// Las horas en MySQL aparecen : hora + 6 hrs
	public static void main(String[] args) {
		String hora = "15:00";
		System.out.println("String to Time: " + stringToTime(hora));
	}

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

}

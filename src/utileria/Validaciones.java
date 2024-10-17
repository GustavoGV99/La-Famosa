package utileria;

import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.regex.Matcher;

public class Validaciones {
	
	public static void soloLetras(KeyEvent e) {
		int key = e.getKeyChar();						// Almacenar el código ASCII de la tecla presionada
		boolean mayusculas = key >= 65 && key <= 90;	// Valores en tabla ASCII que corresponden a los caracteres permitidos
		boolean minusculas = key >= 97 && key <= 122;
		boolean espacio = key == 32;

        if(!(mayusculas || minusculas || espacio)) {
            e.consume();
            //JOptionPane.showMessageDialog(null, "No se permite ingresar números ni caracteres especiales.");
        } 
	}
	
	public static void soloNumeros(KeyEvent e) {
        if(!Character.isDigit(e.getKeyChar())) {
            e.consume();
        }
	}
	
	public static void numerosDecimales(KeyEvent e) {
		int key = e.getKeyChar();
		boolean punto = key == 46;
		boolean numeros = key >= 48 && key <= 57;
		
		if(!(punto || numeros)) {
			e.consume();
		}
	}
	
	public static void numerosLetras(KeyEvent e) {
		int key = e.getKeyChar();
		boolean mayusculas = key >= 65 && key <= 90;
		boolean minusculas = key >= 97 && key <= 122;
		boolean espacio = key == 32;
		boolean numeros = key >= 48 && key <= 57;
		
		if(!(mayusculas || minusculas || espacio || numeros)) {
			e.consume();
		}
	}
	
	public static void elementosFecha(KeyEvent e) {
		int key = e.getKeyChar();
		boolean numeros = key >= 48 && key <= 57;
		boolean barra = key == 47;
		
		if(!(numeros || barra)) {
			e.consume();
		}
	}
	
	public static boolean validarCorreo(String correo) {
		// Expresión original "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
		Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = patron.matcher(correo);
		return matcher.find();
	}
	
	public static boolean validarFecha(String fecha) {
		Pattern patron = Pattern.compile("^[0-9][0-9]*/[0-9][0-9]*/[0-9][0-9][0-9][0-9]$");
		Matcher matcher = patron.matcher(fecha);
		return matcher.find();
	}

}

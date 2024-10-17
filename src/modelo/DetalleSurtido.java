package modelo;

import java.sql.Date;

public class DetalleSurtido {
	private int numeroRenglon;
	private int cantidad;
	private int baja;
	private Date caducidad;
	private float precio;
	private int codigoBarras;
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public int getNumeroRenglon() {
		return numeroRenglon;
	}
	
	public void setNumeroRenglon(int numeroRenglon) {
		this.numeroRenglon = numeroRenglon;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getBaja() {
		return baja;
	}
	
	public void setBaja(int baja) {
		this.baja = baja;
	}
	
	public Date getCaducidad() {
		return caducidad;
	}
	
	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}
	
	public int getCodigoBarras() {
		return codigoBarras;
	}
	
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	/*public int getClaveResurtido() {
		return claveResurtido;
	}
	
	public void setClaveResurtido(int claveResurtido) {
		this.claveResurtido = claveResurtido;
	}*/

}

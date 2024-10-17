package modelo;

import java.sql.Date;

public class Acomodo {
	private int numeroAcomodo;
	private Date fecha;
	private int cantidad;
	private String lugar;
	private int claveSucursal;
	
	public int getNumeroAcomodo() {
		return numeroAcomodo;
	}
	
	public void setNumeroAcomodo(int numeroAcomodo) {
		this.numeroAcomodo = numeroAcomodo;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getLugar() {
		return lugar;
	}
	
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	/*public int getNumeroRenglon() {
		return numeroRenglon;
	}
	
	public void setNumeroRenglon(int numeroRenglon) {
		this.numeroRenglon = numeroRenglon;
	}*/
	
	public int getClaveSucursal() {
		return claveSucursal;
	}
	
	public void setClaveSucursal(int claveSucursal) {
		this.claveSucursal = claveSucursal;
	}
	
}

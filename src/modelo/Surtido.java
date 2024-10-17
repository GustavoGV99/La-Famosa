package modelo;

import java.sql.Date;

public class Surtido {
	private int clave;
	private Date fecha;
	private float total;
	private int claveProveedor;
	private int claveSucursal;
	
	public int getClave() {
		return clave;
	}
	
	public void setClave(int clave) {
		this.clave = clave;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public int getClaveProveedor() {
		return claveProveedor;
	}
	
	public void setClaveProveedor(int claveProveedor) {
		this.claveProveedor = claveProveedor;
	}
	
	public int getClaveSucursal() {
		return claveSucursal;
	}
	
	public void setClaveSucursal(int claveSucursal) {
		this.claveSucursal = claveSucursal;
	}

}

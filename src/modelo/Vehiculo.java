package modelo;

import java.sql.Date;

public class Vehiculo {
	
	private int ns_veh;
	private String modelo;
	private String marca;
	private String color;
	private int cantPuertas;
	private Date fechavehiculo;
	private float preCompra;
	private String tipoVeh;
	private int cve_tie;
	
	
	public Vehiculo() {}
	
	public Vehiculo(int ns_veh, String marca){
		this.ns_veh = ns_veh;
		this.marca = marca;
	}
	
	public int getNs_veh() {
		return ns_veh;
	}
	public void setNs_veh(int ns_veh) {
		this.ns_veh = ns_veh;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getCantPuertas() {
		return cantPuertas;
	}
	public void setCantPuertas(int cantPuertas) {
		this.cantPuertas = cantPuertas;
	}
	public Date getFechavehiculo() {
		return fechavehiculo;
	}
	public void setFechavehiculo(Date fechavehiculo) {
		this.fechavehiculo = fechavehiculo;
	}
	public float getPreCompra() {
		return preCompra;
	}
	public void setPreCompra(float preCompra) {
		this.preCompra = preCompra;
	}
	public String getTipoVeh() {
		return tipoVeh;
	}
	public void setTipoVeh(String tipoVeh) {
		this.tipoVeh = tipoVeh;
	}
	public int getCve_tie() {
		return cve_tie;
	}
	public void setCve_tie(int cve_tie) {
		this.cve_tie = cve_tie;
	}

	@Override
	public String toString() {
		return ns_veh + " - " + marca;
	}
	
	
	
	

}

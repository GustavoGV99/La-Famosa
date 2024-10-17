package modelo;

import java.sql.Date;

public class Enviar {
	
	private int cve_env;
	private Date fecha_env;
	private int ns_veh;
	private int folio_cont;
	
	 //public Enviar() {}
	
	/*public Enviar(int ns_veh, int folio_cont) {
		this.ns_veh = ns_veh;
		this.folio_cont = folio_cont;
	}*/
	
	
	public int getCve_env() {
		return cve_env;
	}
	public void setCve_env(int cve_env) {
		this.cve_env = cve_env;
	}
	public Date getFecha_env() {
		return fecha_env;
	}
	public void setFecha_env(Date fecha_env) {
		this.fecha_env = fecha_env;
	}
	public int getNs_veh() {
		return ns_veh;
	}
	public void setNs_veh(int ns_veh) {
		this.ns_veh = ns_veh;
	}
	public int getFolio_cont() {
		return folio_cont;
	}
	public void setFolio_cont(int folio_cont) {
		this.folio_cont = folio_cont;
	}
	/*
	@Override
	public String toString() {
		return ns_veh + "-" + folio_cont ;
	}*/
	
	
	

}

package modelo;

import java.sql.Date;

public class Placas {
	
	private String num_pla;
	private Date flecha_pla;
	private int ns_veh;
	
	

	public String getNum_pla() {
		return num_pla;
	}
	public void setNum_pla(String num_pla) {
		this.num_pla = num_pla;
	}
	public Date getFlecha_pla() {
		return flecha_pla;
	}
	public void setFlecha_pla(Date flecha_pla) {
		this.flecha_pla = flecha_pla;
	}
	public int getNs_veh() {
		return ns_veh;
	}
	public void setNs_veh(int ns_veh) {
		this.ns_veh = ns_veh;
	}
	
	

}

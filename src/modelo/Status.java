package modelo;

import java.sql.Date;

public class Status {
	
	private int num_sta;
	private Date fecha_sta;
	private String status_sta;
	private int ns_vehsta;
	
	public int getNum_sta() {
		return num_sta;
	}
	public void setNum_sta(int num_sta) {
		this.num_sta = num_sta;
	}
	public Date getFecha_sta() {
		return fecha_sta;
	}
	public void setFecha_sta(Date fecha_sta) {
		this.fecha_sta = fecha_sta;
	}
	public String getStatus_sta() {
		return status_sta;
	}
	public int getNs_vehsta() {
		return ns_vehsta;
	}
	public void setNs_vehsta(int ns_vehsta) {
		this.ns_vehsta = ns_vehsta;
	}
	public void setStatus_sta(String status_sta) {
		this.status_sta = status_sta;
	}


}

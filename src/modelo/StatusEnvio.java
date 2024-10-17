package modelo;

import java.sql.Date;

public class StatusEnvio {
	
	private int num_staen;
	private Date fecha_staen;
	private String status_staen;
	private int num_liscar;
	
	public int getNum_staen() {
		return num_staen;
	}
	public void setNum_staen(int num_staen) {
		this.num_staen = num_staen;
	}
	public Date getFecha_staen() {
		return fecha_staen;
	}
	public void setFecha_staen(Date fecha_staen) {
		this.fecha_staen = fecha_staen;
	}
	public String getStatus_staen() {
		return status_staen;
	}
	public void setStatus_staen(String status_staen) {
		this.status_staen = status_staen;
	}
	public int getNum_liscar() {
		return num_liscar;
	}
	public void setNum_liscar(int num_liscar) {
		this.num_liscar = num_liscar;
	}
	

}

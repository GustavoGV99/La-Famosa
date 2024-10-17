package modelo;

import java.sql.Date;

public class Cliente extends Persona{
	
	private int cve_cli;
	private Date fecha_cli;
	private int cve_per;
	
	public Cliente(){
	}
	
	public Cliente(int cve_cli){
		this.cve_cli=cve_cli;
	}
	
	public int getCve_cli() {
		return cve_cli;
	}
	public void setCve_cli(int cve_cli) {
		this.cve_cli = cve_cli;
	}
	public Date getFecha_cli() {
		return fecha_cli;
	}
	public void setFecha_cli(Date fecha_cli) {
		this.fecha_cli = fecha_cli;
	}
	public int getCve_per() {
		return cve_per;
	}
	public void setCve_per(int cve_per) {
		this.cve_per = cve_per;
	}

	@Override
	public String toString() {
		return cve_cli + "";
	}

}

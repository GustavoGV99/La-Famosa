package modelo;

import java.sql.Date;
import java.sql.Time;

public class Contrato extends Persona {
	private int folio;
	private Date fechaInicio;
	private Date fechaFin;
	private String puesto;
	private float sueldo;
	private String periodoSueldo;
	private Time horaEntrada;
	private Time horaSalida;
	private Time horaInicioComida;
	private Time horaFinComida;
	private int tienda;
	private int persona;
	
	public Contrato() {}
	
	public Contrato(int folio) {
		this.folio = folio;
	}
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public float getSueldo() {
		return sueldo;
	}
	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}
	public String getPeriodoSueldo() {
		return periodoSueldo;
	}
	public void setPeriodoSueldo(String periodoSueldo) {
		this.periodoSueldo = periodoSueldo;
	}
	public Time getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}
	public Time getHoraSalida() {
		return horaSalida;
	}
	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}
	public Time getHoraInicioComida() {
		return horaInicioComida;
	}
	public void setHoraInicioComida(Time horaInicioComida) {
		this.horaInicioComida = horaInicioComida;
	}
	public Time getHoraFinComida() {
		return horaFinComida;
	}
	public void setHoraFinComida(Time horaFinComida) {
		this.horaFinComida = horaFinComida;
	}
	
	public int getTienda() {
		return tienda;
	}

	public void setTienda(int tienda) {
		this.tienda = tienda;
	}

	public int getPersona() {
		return persona;
	}

	public void setPersona(int persona) {
		this.persona = persona;
	}

	public String toString() {
		return String.valueOf(folio);
	}
	
}

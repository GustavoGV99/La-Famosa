package modelo;

import java.sql.Date;

public class Sucursal {
	private int clave;
	private Date fechaApertura;
	private String calle;
	private String numeroDomicilio;
	private String entreCalles;
	private String orientacion;
	private String telefono;
	private String mail;
	private int claveEst;
	private int claveCiu;
	private int codigoPostal;
	private int cveColonia;
	
	public Sucursal(){}
	
	public Sucursal(int clave, String calle){
		this.clave = clave;
		this.calle = calle;
	}
	
	public int getClaveEst() {
		return claveEst;
	}

	public void setClaveEst(int claveEst) {
		this.claveEst = claveEst;
	}

	public int getClaveCiu() {
		return claveCiu;
	}

	public void setClaveCiu(int claveCiu) {
		this.claveCiu = claveCiu;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getClave() {
		return clave;
	}
	public void setClave(int clave) {
		this.clave = clave;
	}
	public Date getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroDomicilio() {
		return numeroDomicilio;
	}
	public void setNumeroDomicilio(String numeroDomicilio) {
		this.numeroDomicilio = numeroDomicilio;
	}
	public String getEntreCalles() {
		return entreCalles;
	}
	public void setEntreCalles(String entreCalles) {
		this.entreCalles = entreCalles;
	}
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getCveColonia() {
		return cveColonia;
	}
	public void setCveColonia(int cveColonia) {
		this.cveColonia = cveColonia;
	}

	@Override
	public String toString() {
		return clave + " - " + calle;
	}

	
}

package modelo;

public class Proveedor {
	private int clave;
	private String razonSocial;
	private String calle;
	private String numero;
	private String orientacion;
	private String telefono;
	private String mail;
	private int claveEstado;
	private int claveCiudad;
	private int cp;
	private int claveColonia;
	
	public Proveedor() {}
	
	public Proveedor(int clave, String razonSocial) {
		this.clave = clave;
		this.razonSocial = razonSocial;
	}
	
	public int getClave() {
		return clave;
	}
	public int getClaveEstado() {
		return claveEstado;
	}

	public void setClaveEstado(int claveEstado) {
		this.claveEstado = claveEstado;
	}

	public int getClaveCiudad() {
		return claveCiudad;
	}

	public void setClaveCiudad(int claveCiudad) {
		this.claveCiudad = claveCiudad;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
	public int getClaveColonia() {
		return claveColonia;
	}
	public void setClaveColonia(int claveColonia) {
		this.claveColonia = claveColonia;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return razonSocial;
	}

}

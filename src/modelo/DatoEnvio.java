package modelo;

public class DatoEnvio extends Ticket{
	
	private int cve_datenv;
	private String calle_datenv;
	private String entrecalles_datenv;
	private String num_datenv;
	private String orientacion_datenv;
	private int cve_col;
	private int folio_tic;
	
	public DatoEnvio() {}
	
	public DatoEnvio(int clave, String calle, String numero, String orientacion) {
		this.cve_datenv = clave;
		this.calle_datenv = calle;
		this.num_datenv = numero;
		this.orientacion_datenv = orientacion;
	}
	
	public int getCve_datenv() {
		return cve_datenv;
	}
	public void setCve_datenv(int cve_datenv) {
		this.cve_datenv = cve_datenv;
	}
	public String getCalle_datenv() {
		return calle_datenv;
	}
	public void setCalle_datenv(String calle_datenv) {
		this.calle_datenv = calle_datenv;
	}
	public String getEntrecalles_datenv() {
		return entrecalles_datenv;
	}
	public void setEntrecalles_datenv(String entrecalles_datenv) {
		this.entrecalles_datenv = entrecalles_datenv;
	}
	public String getNum_datenv() {
		return num_datenv;
	}
	public void setNum_datenv(String num_datenv) {
		this.num_datenv = num_datenv;
	}
	public String getOrientacion_datenv() {
		return orientacion_datenv;
	}
	public void setOrientacion_datenv(String orientacion_datenv) {
		this.orientacion_datenv = orientacion_datenv;
	}
	public int getCve_col() {
		return cve_col;
	}
	public void setCve_col(int cve_col) {
		this.cve_col = cve_col;
	}
	public int getFolio_tic() {
		return folio_tic;
	}
	public void setFolio_tic(int folio_tic) {
		this.folio_tic = folio_tic;
	}
	
	public String toString() {
		return num_datenv + " - " + calle_datenv + " - " + orientacion_datenv;
	}

}

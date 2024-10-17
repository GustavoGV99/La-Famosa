package modelo;

import java.sql.Date;

public class Ticket {
	private int folio;
	private Date fechaventa_tic;
	private Date ffingarantia_tic;
	private float preciovta_tic;
	private int descuento_tic;
	private int cantidad_tic;
	private String formapago_tic;
	private String institucionbancaria_tic;
	private int numtar_tic;
	private int folioauntbanco_tic;
	private int codbar_pro;
	private int folio_cont;
	private int cve_tie;
	private int cve_cli;
	
	public Ticket(){
		
	}
	
	public Ticket(int folio){
		this.folio=folio;
	}
	
	
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public Date getFechaventa_tic() {
		return fechaventa_tic;
	}
	public void setFechaventa_tic(Date fechaventa_tic) {
		this.fechaventa_tic = fechaventa_tic;
	}
	public Date getFfingarantia_tic() {
		return ffingarantia_tic;
	}
	public void setFfingarantia_tic(Date ffingarantia_tic) {
		this.ffingarantia_tic = ffingarantia_tic;
	}
	public float getPreciovta_tic() {
		return preciovta_tic;
	}
	public void setPreciovta_tic(float preciovta_tic) {
		this.preciovta_tic = preciovta_tic;
	}
	public int getDescuento_tic() {
		return descuento_tic;
	}
	public void setDescuento_tic(int descuento_tic) {
		this.descuento_tic = descuento_tic;
	}
	public int getCantidad_tic() {
		return cantidad_tic;
	}
	public void setCantidad_tic(int cantidad_tic) {
		this.cantidad_tic = cantidad_tic;
	}
	public String getFormapago_tic() {
		return formapago_tic;
	}
	public void setFormapago_tic(String formapago_tic) {
		this.formapago_tic = formapago_tic;
	}
	public String getInstitucionbancaria_tic() {
		return institucionbancaria_tic;
	}
	public void setInstitucionbancaria_tic(String institucionbancaria_tic) {
		this.institucionbancaria_tic = institucionbancaria_tic;
	}
	public int getNumtar_tic() {
		return numtar_tic;
	}
	public void setNumtar_tic(int numtar_tic) {
		this.numtar_tic = numtar_tic;
	}
	public int getFolioauntbanco_tic() {
		return folioauntbanco_tic;
	}
	public void setFolioauntbanco_tic(int folioauntbanco_tic) {
		this.folioauntbanco_tic = folioauntbanco_tic;
	}
	public int getCodbar_pro() {
		return codbar_pro;
	}
	public void setCodbar_pro(int codbar_pro) {
		this.codbar_pro = codbar_pro;
	}
	public int getFolio_cont() {
		return folio_cont;
	}
	public void setFolio_cont(int folio_cont) {
		this.folio_cont = folio_cont;
	}
	public int getCve_tie() {
		return cve_tie;
	}
	public void setCve_tie(int cve_tie) {
		this.cve_tie = cve_tie;
	}
	public int getCve_cli() {
		return cve_cli;
	}
	public void setCve_cli(int cve_cli) {
		this.cve_cli = cve_cli;
	}

	@Override
	public String toString() {
		return folio +"";
	}
	
	

}

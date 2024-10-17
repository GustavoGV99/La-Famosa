package modelo;

public class Producto {
	private int codigoBarras;
	private String nombre;
	private String tipo;
	private String marca;
	private String color;
	private String garantia;
	private String medidaGarantia;
	private String presentacion;
	private String modelo;
	private String alto;
	private String largo;
	private String ancho;
	private String contenido;
	private String uMedida;
	
	public Producto() {}
	
	public Producto(int codigo, String nombre, String modelo) {
		this.codigoBarras = codigo;
		this.nombre = nombre;
		this.modelo = modelo;
	}
	
	public int getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public String getMedidaGarantia() {
		return medidaGarantia;
	}
	public void setMedidaGarantia(String medidaGarantia) {
		this.medidaGarantia = medidaGarantia;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getAlto() {
		return alto;
	}
	public void setAlto(String alto) {
		this.alto = alto;
	}
	public String getLargo() {
		return largo;
	}
	public void setLargo(String largo) {
		this.largo = largo;
	}
	public String getAncho() {
		return ancho;
	}
	public void setAncho(String ancho) {
		this.ancho = ancho;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getuMedida() {
		return uMedida;
	}
	public void setuMedida(String uMedida) {
		this.uMedida = uMedida;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nombre + " " + modelo;
	}

}

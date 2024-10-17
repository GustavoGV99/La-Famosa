package modelo;

public class DetalleProducto {
	private int numero;
	private int minimo;
	private int maximo;
	private int codigoBarras;
	private int claveSucursal;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getMinimo() {
		return minimo;
	}
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}
	public int getMaximo() {
		return maximo;
	}
	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}
	public int getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public int getClaveSucursal() {
		return claveSucursal;
	}
	public void setClaveSucursal(int claveSucursal) {
		this.claveSucursal = claveSucursal;
	}
	
}

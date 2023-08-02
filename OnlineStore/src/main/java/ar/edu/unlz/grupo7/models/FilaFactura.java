package ar.edu.unlz.grupo7.models;

public class FilaFactura {
	private int id;
	private int factura_id;
	private int articulo_id;
	private int cantidad;
	private double precio_unitario;
	private double precio_total;
	private String descripcion;

	public FilaFactura(int factura_id, int articulo_id, int cantidad, double precio_unitario, double precio_total,
			String descripcion) {
		super();
		this.factura_id = factura_id;
		this.articulo_id = articulo_id;
		this.cantidad = cantidad;
		this.precio_unitario = precio_unitario;
		this.precio_total = precio_total;
		this.descripcion = descripcion;
	}

	public FilaFactura() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(int factura_id) {
		this.factura_id = factura_id;
	}

	public int getArticulo_id() {
		return articulo_id;
	}

	public void setArticulo_id(int articulo_id) {
		this.articulo_id = articulo_id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		this.precio_unitario = precio_unitario;
	}

	public double getPrecio_total() {
		return precio_total;
	}

	public void setPrecio_total(double precio_total) {
		this.precio_total = precio_total;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void addCantidad(double cant) {
		this.cantidad += cant;
	}

}

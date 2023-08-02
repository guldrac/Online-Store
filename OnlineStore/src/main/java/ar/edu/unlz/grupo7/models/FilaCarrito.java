package ar.edu.unlz.grupo7.models;

public class FilaCarrito {
	private int id;
	private int carrito_id;
	private int articulo_id;
	private int cantidad;
	private double precio;
	private String descripcion;

	// private Articulo articulo;

	public FilaCarrito(int articulo_id, int cantidad, double precio, int carrito_id, String descripcion) {
		super();
		this.articulo_id = articulo_id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.carrito_id = carrito_id;
		this.descripcion = descripcion;
	}

	public FilaCarrito() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCarrito_id() {
		return carrito_id;
	}

	public void setCarrito_id(int carrito_id) {
		this.carrito_id = carrito_id;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void addCantidad(double cant) {
		this.cantidad += cant;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}

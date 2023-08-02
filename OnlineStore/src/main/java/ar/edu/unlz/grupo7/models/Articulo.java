package ar.edu.unlz.grupo7.models;

public class Articulo {
	private int id;
	private String descripcion;
	private double precio;
	private int cantidad;

	public Articulo(int id, String descripcion, double precio, int stock) {

		this.id = id;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = stock;
	}

	public Articulo(String descripcion, double precio, int stock) {

		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = stock;
	}

	public Articulo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


}

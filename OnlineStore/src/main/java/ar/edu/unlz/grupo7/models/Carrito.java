package ar.edu.unlz.grupo7.models;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private int id;
	private int id_usuario;
	private double monto_total = 0;
	private List<FilaCarrito> filas;

	public Carrito(int id_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.filas = new ArrayList<FilaCarrito>();
	}

	public Carrito(int id, int id_usuario, double monto_total) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.monto_total = monto_total;
		this.filas = new ArrayList<FilaCarrito>();
	}

	public double getMonto_total() {
		return monto_total;
	}

	public void setMonto_total(double monto_total) {
		this.monto_total = monto_total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public List<FilaCarrito> getFilas() {
		return filas.stream().toList();
	}

	public void setFilas(List<FilaCarrito> filas) {
		this.filas = filas;
	}

	public void addFila(FilaCarrito fila, int cantidad) {

		var filaOpt = this.filas.stream().filter(f -> f.getArticulo_id() == fila.getArticulo_id()).findAny();

		if (filaOpt.isPresent()) {
			var filaT = filaOpt.get();
			filaT.addCantidad(cantidad);

		} else {
			var filaT = new FilaCarrito(fila.getArticulo_id(), cantidad, fila.getPrecio(), fila.getCarrito_id(),
					fila.getDescripcion());

			this.filas.add(filaT);
		}
	}

	public void deleteArticulo(int id, int cantidad) {
		FilaCarrito filaArt = this.filas.stream().filter(f -> f.getArticulo_id() == id).findAny().orElse(null);

		if (filaArt.getCantidad() <= cantidad) {

			this.filas.removeIf(f -> f.getArticulo_id() == id);

		} else {
			filaArt.setCantidad(filaArt.getCantidad() - cantidad);
		}
	}

	public void deleteFila(String descripcion) {
		FilaCarrito filaArt = this.filas.stream().filter(f -> f.getDescripcion().equals(descripcion)).findAny()
				.orElse(null);
		this.filas.remove(filaArt);
		this.monto_total = this.monto_total - filaArt.getPrecio();
	}

	@Override
	public String toString() {

		for (FilaCarrito filaCarrito : filas) {
			System.out.println(" [descripcion=" + filaCarrito.getDescripcion() + "  cantidad= "
					+ filaCarrito.getCantidad() + "   precio= " + filaCarrito.getPrecio() + " ]");

		}
		return null;

	}

}

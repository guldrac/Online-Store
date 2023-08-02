package ar.edu.unlz.grupo7.models;

import java.util.ArrayList;
import java.util.List;

public class Factura {
	private int id;
	private int id_usuario;
	private int nro_factura;
	private String sucursal;
	private String tipo;
	private double total;
	private List<FilaFactura> filas;

	public Factura(int id, int id_usuario, int nro_factura, String sucursal, String tipo, double total) {
		super();
		this.id = id;
		this.id_usuario = id_usuario;
		this.nro_factura = nro_factura;
		this.sucursal = sucursal;
		this.tipo = tipo;
		this.total = total;
		this.filas = new ArrayList<FilaFactura>();
	}

	public Factura(int id_usuario, int nro_factura, String sucursal, String tipo, double total) {
		super();
		this.id_usuario = id_usuario;
		this.nro_factura = nro_factura;
		this.sucursal = sucursal;
		this.tipo = tipo;
		this.total = total;
		this.filas = new ArrayList<FilaFactura>();
	}

	public Factura(int id_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.filas = new ArrayList<FilaFactura>();
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

	public int getNro_factura() {
		return nro_factura;
	}

	public void setNro_factura(int nro_factura) {
		this.nro_factura = nro_factura;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<FilaFactura> getFilas() {
		return filas.stream().toList();
	}

	public void setFilas(List<FilaFactura> filas) {
		this.filas = filas;
	}

	public void addFila(FilaFactura fila, int cantidad) {

		var filaOpt = this.filas.stream().filter(f -> f.getArticulo_id() == fila.getArticulo_id()).findAny();

		if (filaOpt.isPresent()) {
			var filaT = filaOpt.get();
			filaT.addCantidad(cantidad);

		} else {
			var filaT = new FilaFactura(fila.getFactura_id(), fila.getArticulo_id(), cantidad,
					fila.getPrecio_unitario(), fila.getPrecio_total(), fila.getDescripcion());

			this.filas.add(filaT);
		}
	}

}

package ar.edu.unlz.grupo7.models;

public class Usuario {
	private int id;
	private String nombre;
	private String contrasenia;
	private double credito;
	private String rol;

	public Usuario(String nombre, String contrasenia, String rol) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.credito = 0;
		this.rol = rol;
	}

	public Usuario(int id, String nombre, String contrasenia, double credito, String rol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
		this.credito = credito;
		this.rol = rol;
	}

	public Usuario() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean validarContrasenia(String contrasenia) {
		return this.contrasenia.equals(contrasenia);
	}

}

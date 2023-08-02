package ar.edu.unlz.grupo7.daos;

import java.util.List;

public interface Dao<T> {

	/**
	 * Método que inserta en una tabla el objeto pasado por parámetro t.
	 * @param t
	 */
	public void insert(T t);

	/**
	 * Método que actualiza en una tabla el objeto pasado por parámetro t.
	 * @param t
	 */
	public void update(T t);

	/**
	 * Método que elimina de una tabla la fila con id pasada por parámetro.
	 * @param id
	 */
	public void delete(int id);

	/**
	 * Método que busca en una tabla la fila con id pasada por parámetro.
	 * @param id
	 */
	public T getById(int id);

	/**
	 * Método devuelve una lista entera de todas las filas de una tabla.
	 * @return List<t>
	 */
	public List<T> all();

}

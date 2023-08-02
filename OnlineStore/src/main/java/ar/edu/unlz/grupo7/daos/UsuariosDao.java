package ar.edu.unlz.grupo7.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlz.grupo7.factories.ConexionFactory;
import ar.edu.unlz.grupo7.models.Usuario;

public class UsuariosDao implements Dao<Usuario> {

	public void insert(Usuario usuario) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "INSERT INTO usuarios";
			query += " (nombre, contrasenia, credito, rol)";
			query += " values (?, ?, ?, ?)";

			var ps = con.prepareStatement(query);

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getContrasenia());
			ps.setDouble(3, usuario.getCredito());
			ps.setString(4, usuario.getRol());

			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Usuario usuario) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "UPDATE usuarios";
			query += " SET nombre = ?,";
			query += " contrasenia = ?,";
			query += " credito = ?,";
			query += " rol = ?,";
			query += " WHERE id = ?";

			var ps = con.prepareStatement(query);

			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getContrasenia());
			ps.setDouble(3, usuario.getCredito());
			ps.setString(4, usuario.getRol());
			ps.setInt(5, usuario.getId());

			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/**
	 * Método que actualiza el crédito del usuario en la base de datos.
	 * @param usuario
	 */
	public void updateCredito(Usuario usuario) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "UPDATE usuarios";
			query += " SET credito = ?";
			query += " WHERE id = ?";

			var ps = con.prepareStatement(query);

			ps.setDouble(1, usuario.getCredito());
			ps.setInt(2, usuario.getId());

			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "DELETE usuarios";
			query += " WHERE id = ?";

			var ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Usuario getById(int id) {
		Usuario usu = null;

		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement("select id, nombre, contrasenia, credito, rol from usuarios where id = ?");

			ps.setInt(1, id);

			var rs = ps.executeQuery();

			if (rs.next()) {
				var _id = rs.getInt("id");
				var _nombre = rs.getString("nombre");
				var _contrasenia = rs.getString("contrasenia");
				var _credito = rs.getDouble("credito");
				var _rol = rs.getString("rol");

				usu = new Usuario(_id, _nombre, _contrasenia, _credito, _rol);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usu;
	}

	public boolean existsNombre(String nombre) throws SQLException {

		return this.getByNombre(nombre) != null;
	}

	/**
	 * Método que búsca al usuario que tenga el nombre que se pasó por parámetro
	 * @param nombre
	 * @return Usuario
	 */
	public Usuario getByNombre(String nombre) {
		Usuario usuario = null;
		try {

			var conn = ConexionFactory.getConexion();

			String query = "select id, nombre, contrasenia, credito, rol";
			query += " from usuarios";
			query += " where nombre = ?";

			var ps = conn.prepareStatement(query);

			ps.setString(1, nombre);

			var rs = ps.executeQuery();

			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setContrasenia(rs.getString("contrasenia"));
				usuario.setCredito(rs.getDouble("credito"));
				usuario.setRol(rs.getString("rol"));

			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public List<Usuario> all() {
		var list = new ArrayList<Usuario>();

		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement("select * from usuarios");

			var rs = ps.executeQuery();

			while (rs.next()) {
				var _id = rs.getInt("id");
				var _nombre = rs.getString("nombre");
				var _contrasenia = rs.getString("contrasenia");
				var _credito = rs.getDouble("credito");
				var _rol = rs.getString("rol");

				var usu = new Usuario(_id, _nombre, _contrasenia, _credito, _rol);

				list.add(usu);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}

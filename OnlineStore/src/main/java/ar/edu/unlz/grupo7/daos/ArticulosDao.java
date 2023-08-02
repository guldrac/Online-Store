package ar.edu.unlz.grupo7.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlz.grupo7.factories.ConexionFactory;
import ar.edu.unlz.grupo7.models.Articulo;

public class ArticulosDao implements Dao<Articulo> {

	public void insert(Articulo articulo) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "INSERT INTO articulos";
			query += " (descripcion, precio, cantidad)";
			query += " values (?, ?, ?)";

			var ps = con.prepareStatement(query);

			ps.setString(1, articulo.getDescripcion());
			ps.setDouble(2, articulo.getPrecio());
			ps.setInt(3, articulo.getCantidad());

			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Articulo articulo) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "UPDATE articulos";
			query += " SET descripcion = ?,";
			query += " precio = ?,";
			query += " cantidad = ?";
			query += " WHERE id = ?";

			var ps = con.prepareStatement(query);

			ps.setString(1, articulo.getDescripcion());
			ps.setDouble(2, articulo.getPrecio());
			ps.setInt(3, articulo.getCantidad());
			ps.setInt(4, articulo.getId());

			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void delete(int id) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "DELETE FROM articulos";
			query += " WHERE id = ?";

			var ps = con.prepareStatement(query);

			ps.setInt(1, id);

			ps.executeUpdate();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Articulo getById(int id) {
		Articulo art = null;

		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement(
					"select id, descripcion, precio, cantidad" + " from articulos " + " where id = ?");

			ps.setInt(1, id);

			var rs = ps.executeQuery();

			if (rs.next()) {
				var _id = rs.getInt("id");
				var _descripcion = rs.getString("descripcion");
				var _precio = rs.getDouble("precio");
				var _cantidad = rs.getInt("cantidad");

				art = new Articulo(_id, _descripcion, _precio, _cantidad);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return art;
	}

	@Override
	public List<Articulo> all() {
		var list = new ArrayList<Articulo>();

		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement("select * from articulos");

			var rs = ps.executeQuery();

			while (rs.next()) {
				var _id = rs.getInt("id");
				var _descripcion = rs.getString("descripcion");
				var _precio = rs.getDouble("precio");
				var _cantidad = rs.getInt("cantidad");

				var art = new Articulo(_id, _descripcion, _precio, _cantidad);

				list.add(art);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}

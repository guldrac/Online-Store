package ar.edu.unlz.grupo7.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlz.grupo7.factories.ConexionFactory;
import ar.edu.unlz.grupo7.models.FilaFactura;

public class FilaFacturaDao implements Dao<FilaFactura> {

	@Override
	public void insert(FilaFactura fila) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "INSERT INTO FILAS_FACTURA";
			query += " (factura_id, articulo_id, cantidad, precio_unitario, precio_total, descripcion)";
			query += " values (?, ?, ?, ?, ?, ?)";

			var ps = con.prepareStatement(query);

			ps.setInt(1, fila.getFactura_id());
			ps.setInt(2, fila.getArticulo_id());
			ps.setInt(3, fila.getCantidad());
			ps.setDouble(4, fila.getPrecio_unitario());
			ps.setDouble(5, fila.getPrecio_total());
			ps.setString(6, fila.getDescripcion());

			ps.executeUpdate();

			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Método que devuelve las filas asociadas a la id de factura que se pasa por parámetro.
	 * 
	 * @param factura_id
	 * @return List<FilaFactura>
	 * @throws SQLException
	 */
	public List<FilaFactura> getByFactura(int factura_id) throws SQLException {

		List<FilaFactura> lista = new ArrayList<FilaFactura>();

		String query = "select id, factura_id, articulo_id, cantidad, precio_unitario, precio_total, descripcion";
		query += " from filas_factura";
		query += " where factura_id = ?";

		var conexion = ConexionFactory.getConexion();

		var ps = conexion.prepareStatement(query);

		ps.setInt(1, factura_id);

		var rs = ps.executeQuery();

		while (rs.next()) {
			var fila = new FilaFactura();
			fila.setId(rs.getInt("id"));
			fila.setFactura_id(factura_id);
			fila.setArticulo_id(rs.getInt("articulo_id"));
			fila.setCantidad(rs.getInt("cantidad"));
			fila.setPrecio_unitario(rs.getDouble("precio_unitario"));
			fila.setPrecio_total(rs.getDouble("precio_total"));
			fila.setDescripcion(rs.getString("descripcion"));

			lista.add(fila);

		}

		return lista;
	}

	@Override
	public void update(FilaFactura t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public FilaFactura getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilaFactura> all() {
		// TODO Auto-generated method stub
		return null;
	}

}

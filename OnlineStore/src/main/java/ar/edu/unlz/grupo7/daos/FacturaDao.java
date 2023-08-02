package ar.edu.unlz.grupo7.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlz.grupo7.factories.ConexionFactory;
import ar.edu.unlz.grupo7.models.Factura;
import ar.edu.unlz.grupo7.models.FilaFactura;

public class FacturaDao implements Dao<Factura> {

	private FilaFacturaDao fdao;

	public FacturaDao() {
		super();
		this.fdao = new FilaFacturaDao();
	}

	@Override
	public void insert(Factura factura) {
		try {
			var con = ConexionFactory.getConexion();

			var query = "INSERT INTO facturas";
			query += " (id_usuario, nro_factura, sucursal, tipo, total )";
			query += " values (?, ?, ?, ?, ?)";

			var ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1, factura.getId_usuario());
			ps.setInt(2, factura.getNro_factura());
			ps.setString(3, factura.getSucursal());
			ps.setString(4, factura.getTipo());
			ps.setDouble(5, factura.getTotal());

			ps.executeUpdate();

			int id = 0;
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

			factura.setId(id);

			for (FilaFactura fila : factura.getFilas()) {
				fila.setFactura_id(id);
				fdao.insert(fila);
			}

			con.close();
		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Factura t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Factura getById(int id) {
		Factura factura = null;
		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement(
					"select id, id_usuario, nro_factura, sucursal, tipo, total from facturas where id = ?");

			ps.setInt(1, id);

			var rs = ps.executeQuery();

			if (rs.next()) {
				var _id = rs.getInt("id");
				var _id_usuario = rs.getInt("id_usuario");
				var _nro_factura = rs.getInt("nro_factura");
				var _sucursal = rs.getString("sucursal");
				var _tipo = rs.getString("tipo");
				var _total = rs.getDouble("total");

				factura = new Factura(_id, _id_usuario, _nro_factura, _sucursal, _tipo, _total);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factura;
	}

	/**
	 * Método que verifica el último número de factura emitido.
	 * 
	 * @return Integer
	 */
	public Integer getMax() {
		Integer _nro_factura = 0;
		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement(
					"select distinct nro_factura from facturas where nro_factura = (SELECT distinct MAX(nro_factura) FROM facturas)");

			var rs = ps.executeQuery();

			if (rs.next()) {
				_nro_factura = rs.getInt("nro_factura");
			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return _nro_factura;
	}

	public Factura getByIdUsuario(int id) {
		Factura factura = null;
		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement(
					"select id, id_usuario, nro_factura, sucursal, tipo, total from facturas where id_usuario = ?");

			ps.setInt(1, id);
			var rs = ps.executeQuery();

			if (rs.next()) {
				var _id = rs.getInt("id");
				var _id_usuario = rs.getInt("id_usuario");
				var _nro_factura = rs.getInt("nro_factura");
				var _sucursal = rs.getString("sucursal");
				var _tipo = rs.getString("tipo");
				var _total = rs.getDouble("total");

				factura = new Factura(_id, _id_usuario, _nro_factura, _sucursal, _tipo, _total);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factura;
	}

	public Factura getByIdUsuario(int id, int nroFac) {
		Factura factura = null;
		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement(
					"select id, id_usuario, nro_factura, sucursal, tipo, total from facturas where id_usuario = ? and nro_factura = ?");

			ps.setInt(1, id);
			ps.setInt(2, nroFac);
			var rs = ps.executeQuery();

			if (rs.next()) {
				var _id = rs.getInt("id");
				var _id_usuario = rs.getInt("id_usuario");
				var _nro_factura = rs.getInt("nro_factura");
				var _sucursal = rs.getString("sucursal");
				var _tipo = rs.getString("tipo");
				var _total = rs.getDouble("total");

				factura = new Factura(_id, _id_usuario, _nro_factura, _sucursal, _tipo, _total);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factura;
	}

	/**
	 * Método que devuelve una lista de facturas asociadas a la id de usuario que se pasa por parámetro.
	 * 
	 * @param id
	 * @return List<Factura>
	 */
	
	public List<Factura> all(int id) {
		var list = new ArrayList<Factura>();

		try {
			var con = ConexionFactory.getConexion();

			var ps = con.prepareStatement("select * from facturas where id_usuario = ?");

			ps.setInt(1, id);
			var rs = ps.executeQuery();

			while (rs.next()) {
				var _id = rs.getInt("id");
				var _id_usuario = rs.getInt("id_usuario");
				var _nro_factura = rs.getInt("nro_factura");
				var _sucursal = rs.getString("sucursal");
				var _tipo = rs.getString("tipo");
				var _total = rs.getDouble("total");

				var factura = new Factura(_id, _id_usuario, _nro_factura, _sucursal, _tipo, _total);

				list.add(factura);

			}

			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<Factura> all() {
		return null;
	}

}

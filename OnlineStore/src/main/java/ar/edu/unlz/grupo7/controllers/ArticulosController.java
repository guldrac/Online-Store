package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.unlz.grupo7.daos.ArticulosDao;
import ar.edu.unlz.grupo7.iterators.StringIterator;
import ar.edu.unlz.grupo7.models.Articulo;

/**
 * Servlet implementation class ArticulosController
 */
@WebServlet("/articulos/*")
public class ArticulosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticulosDao dao;

	public ArticulosController() {
		dao = new ArticulosDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/index");

		switch (accion) {
		case "/index" -> getIndex(request, response);
		case "/crear" -> getCrear(request, response);
		case "/editar" -> getEditar(request, response);
		case "/eliminar" -> eliminarArticulo(request, response);
		case "/abmArticulos" -> getABM(request, response);

		default -> response.sendError(404, "No existe la accion: " + accion);
		}

	}

	/**
	 * Método que redirige a la vista de editar artículo.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getEditar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var sId = request.getParameter("id");
		var id = Integer.parseInt(sId);

		Articulo art = dao.getById(id);

		request.setAttribute("articulo", art);

		var rd = request.getRequestDispatcher("/views/articulos/editar.jsp");
		rd.forward(request, response);

	}

	/**
	 * Método que redirige a la vista de eliminar artículo. 
	 * 
	 * @throws ServletException, IOException
	 * */
	private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var sId = request.getParameter("id");
		var id = Integer.parseInt(sId);

		Articulo art = dao.getById(id);

		request.setAttribute("articulo", art);

		var rd = request.getRequestDispatcher("/views/articulos/eliminar.jsp");
		rd.forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getParameter("accion");

		switch (accion) {
		case "insert" -> postInsert(request, response);
		case "update" -> postUpdate(request, response);
		case "delete" -> postDelete(request, response);
		}
	}

	/**
	 * Método que elimina de la base de datos el artículo a partir de su id.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void postDelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		var sId = request.getParameter("id");
		var id = Integer.parseInt(sId);

		dao.delete(id);

		response.sendRedirect(request.getContextPath() + "/articulos/abmArticulos");
	}

	/**
	 * Método que inserta un artículo nuevo en la base de datos.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void postInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String descripcion = request.getParameter("descripcion");
		String precioS = request.getParameter("precio");
		String stockS = request.getParameter("cantidad");

		double precio = Double.parseDouble(precioS);
		int stock = Integer.parseInt(stockS);

		// Crear un iterador que recorra la descripci�n:
		var sIterador = new StringIterator(descripcion);

		// Validar inputs:
		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 37) // Signos excluye "&" y "-".
					|| (letra >= 39 && letra <= 44) || (letra >= 46 && letra <= 47) || (letra >= 58 && letra <= 64)
					|| (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sint�xis inv�lida.");
				return;
			}
		}

		if (precio < 0 || stock < 0) {
			response.sendError(400, "El numero ingresado no puede ser negativo.");
			return;
		}

		if (descripcion.equals("")|| descripcion == null || precio == 0 || stock == 0) {
			response.sendError(400, "No se pueden dejar campos en blanco.");
			return;
		}

		var art = new Articulo(descripcion, precio, stock);

		dao.insert(art);

		response.sendRedirect(request.getContextPath() + "/articulos/abmArticulos");

	}

	/**
	 * Método que modifica los parametros indicados del artículo en la base de datos.
	 * 
	 * @throws IOException
	 * */
	private void postUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String descripcion = request.getParameter("descripcion");
		String precioS = request.getParameter("precio");
		String stockS = request.getParameter("cantidad");

		double precio = Double.parseDouble(precioS);
		int stock = Integer.parseInt(stockS);

		var sId = request.getParameter("id");
		var id = Integer.parseInt(sId);

		// Crear un iterador que recorra la descripci�n:
		var sIterador = new StringIterator(descripcion);

		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 37) // Signos excluye "&" y "-".
					|| (letra >= 39 && letra <= 44) || (letra >= 46 && letra <= 47) || (letra >= 58 && letra <= 64)
					|| (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sintaxis invalida.");
				return;
			}
		}

		if (precio < 0 || stock < 0) {
			response.sendError(400, "El n�mero ingresado no puede ser negativo.");
			return;
		}

		if (descripcion.equals("") || descripcion == null || precio == 0 || stock == 0) {
			response.sendError(400, "No se pueden dejar campos en blanco.");
			return;
		}

		var art = dao.getById(id);

		art.setId(id);
		art.setDescripcion(descripcion);
		art.setPrecio(precio);
		art.setCantidad(stock);

		dao.update(art);

		response.sendRedirect(request.getContextPath() + "/articulos/abmArticulos");

	}

	/**
	 * Método que envía al usuario a homeController.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/home");
		rd.forward(request, response);
	}

	/**
	 * Método que redirige a la vista de ABM de artículos.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getABM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		var listado = dao.all();

		request.setAttribute("articulos", listado);

		var rd = request.getRequestDispatcher("/views/articulos/ABM_articulos.jsp");
		rd.forward(request, response);
	}

	/**
	 * Método que redirige a la vista de crear artículos. 
	 * 
	 * @throws ServletException, IOException
	 * */
	private Object getCrear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/articulos/crear.jsp");
		rd.forward(request, response);

		return null;
	}

}

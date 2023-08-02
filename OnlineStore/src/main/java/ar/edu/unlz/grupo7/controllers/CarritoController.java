package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.unlz.grupo7.daos.ArticulosDao;
import ar.edu.unlz.grupo7.daos.FacturaDao;
import ar.edu.unlz.grupo7.daos.FilaFacturaDao;
import ar.edu.unlz.grupo7.daos.UsuariosDao;
import ar.edu.unlz.grupo7.models.Carrito;
import ar.edu.unlz.grupo7.models.Factura;
import ar.edu.unlz.grupo7.models.FilaCarrito;
import ar.edu.unlz.grupo7.models.FilaFactura;

@WebServlet(urlPatterns = {"/carrito/*", "/compra-realizada.jsp"})
public class CarritoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticulosDao artDao;
	private UsuariosDao uDao;
	private FacturaDao fDao;
	private FilaFacturaDao filaDao;

	public CarritoController() {
		super();
		this.artDao = new ArticulosDao();
		this.uDao = new UsuariosDao();
		this.fDao = new FacturaDao();
		this.filaDao = new FilaFacturaDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/catalogo");

		switch (accion.toLowerCase()) {
		case "/catalogo" -> getCatalogo(request, response);
		case "/carrito" -> getCarrito(request, response);
		case "/pagar" -> getPagar(request, response);

		default -> response.sendError(404, "No existe la accion: " + accion);
		}

	}
	
	/**
	 * Método que corrobora si el carrito está vacío, si hay stock suficiente y si el usuario tiene crédito suficiente.
	 * Si todo lo anterior se cumple entonces se procede a crear la factura asociada al usuario y reducir el crédito del mismo
	 * y el stock en los artículos.
	 * 
	 * @throws ServletException, IOException 
	 * */
	private void getPagar(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		var session = request.getSession();
		var id_usuario = (int) session.getAttribute("usuario_id");

		Carrito carrito = (Carrito) session.getAttribute("carrito_nuevo");

		var usu = uDao.getById(id_usuario);

		
		//se controla si el carrito está vacío
		if(carrito.getMonto_total()==0) {
			response.sendError(500, "carrito vacío");
			return;
		}
		
		
		// se controla si hay stock y el usuario tiene credito suficiente
				
		if (usu.getCredito() < carrito.getMonto_total()) {
			response.sendError(500, "credito insuficiente");
			return;
		}

		for (FilaCarrito filaCarrito : carrito.getFilas()) {
			var art = artDao.getById(filaCarrito.getArticulo_id());

			if (art.getCantidad() < filaCarrito.getCantidad()) {
				response.sendError(500, "Stock de " + art.getDescripcion() + " insuficiente");
				return;
			}
		}

		// una vez validado que se tenga crédito y stock se procede a crear la factura
		// y descontar el crédito y stock

		var ultimaFactura = fDao.getMax();

		

		int nroFac = 0;

		if (ultimaFactura == null) {
			nroFac = 1;
		} else {
			nroFac = ultimaFactura + 1;
		}

		Factura factura = new Factura(id_usuario, nroFac, "sede_principal", "C", carrito.getMonto_total());

		fDao.insert(factura);

		factura = fDao.getByIdUsuario(id_usuario, nroFac);

		for (FilaCarrito filaCarrito : carrito.getFilas()) {
			var art = artDao.getById(filaCarrito.getArticulo_id());
			art.setCantidad(art.getCantidad() - filaCarrito.getCantidad());
			FilaFactura fila = new FilaFactura(factura.getId(), filaCarrito.getArticulo_id(), filaCarrito.getCantidad(),
					art.getPrecio(), filaCarrito.getPrecio(), filaCarrito.getDescripcion());
			filaDao.insert(fila);
			artDao.update(art);
		}
		var usuario = uDao.getById(id_usuario);

		usuario.setCredito(usuario.getCredito() - carrito.getMonto_total());

		uDao.updateCredito(usuario);

		carrito = null;
		
		var rd = request.getRequestDispatcher("/views/carrito/compra-realizada.jsp");
		
		session.setAttribute("carrito_nuevo", carrito);
		session.setAttribute("usuario", usuario);
		
		rd.forward(request, response);
		
	}

	/**
	 * Método que verifica si existe un carrito en la sesión. En caso de que no exista se crea y se lo setea en la sesión.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getCarrito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/carrito/carrito.jsp");
		var session = request.getSession();
		var id_usuario = (int) session.getAttribute("usuario_id");
		
		
		Carrito carrito = (Carrito) session.getAttribute("carrito_nuevo");

		if (carrito == null) {
			
			carrito = new Carrito(id_usuario);

			session.setAttribute("carrito_nuevo", carrito);
		}

		rd.forward(request, response);
	}

	/**
	 * Método que obtiene la lista de articulos y rellena el catálogo con la misma.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getCatalogo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var listado = artDao.all();
		
		request.setAttribute("articulos", listado);

		var rd = request.getRequestDispatcher("/views/carrito/catalogo.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("");

		switch (accion.toLowerCase()) {
		case "/add" -> postAdd(request, response);
		case "/remove" -> postRemove(request, response);
		default -> response.sendError(404, "No existe la accion: " + accion);
		}
	}

	
	/**
	 * fMétodo que remueve una fila del mismo.
	 * 
	 * @throws IOException
	 * */
	private void postRemove(HttpServletRequest request, HttpServletResponse response) throws IOException {
		var session = request.getSession();
		var descripcion = request.getParameter("desc_articulo_quitar");
		Carrito carrito = (Carrito) session.getAttribute("carrito_nuevo");

		carrito.deleteFila(descripcion);
		response.sendRedirect(request.getContextPath() + "/carrito/carrito");

	}

	/**
	 * Método que agrega una fila al carrito. Verifica si ya hay un artículo existente y en caso de que exista
	 * le suma la cantidad y modifica el monto total.
	 * 
	 * @throws IOException
	 * */
	private void postAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		var session = request.getSession();
		var sArtId = request.getParameter("id_articulo_agregar");
		var id_usuario = (int) session.getAttribute("usuario_id");

		int artId = Integer.parseInt(sArtId);
		int cantArt = 1;
		var articulo = artDao.getById(artId);
		var precioArt = articulo.getPrecio();
		var descArt = articulo.getDescripcion();

		Carrito carrito = (Carrito) session.getAttribute("carrito_nuevo");
		boolean encontrado = false;

		if (carrito == null) {
			carrito = new Carrito(id_usuario);

			session.setAttribute("carrito_nuevo", carrito);
		}

		carrito = (Carrito) session.getAttribute("carrito_nuevo");

		var idCarrito = carrito.getId();

		for (FilaCarrito filaCarrito : carrito.getFilas()) {
			if (filaCarrito.getArticulo_id() == artId) {
				filaCarrito.setCantidad(filaCarrito.getCantidad() + cantArt);
				filaCarrito.setPrecio((filaCarrito.getCantidad()) * precioArt);
				filaCarrito.setDescripcion(descArt);
				encontrado = true;
				carrito.setMonto_total(carrito.getMonto_total() + (cantArt * precioArt));

			}
		}

		if (encontrado == false) {
			var filaCarrito = new FilaCarrito(artId, cantArt, cantArt * precioArt, idCarrito, descArt);

			carrito.addFila(filaCarrito, cantArt);

			carrito.setMonto_total(carrito.getMonto_total() + (cantArt * precioArt));

		}

		response.sendRedirect(request.getContextPath() + "/carrito/catalogo");

	}


}

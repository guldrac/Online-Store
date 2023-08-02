package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.unlz.grupo7.daos.FacturaDao;
import ar.edu.unlz.grupo7.daos.FilaFacturaDao;
import ar.edu.unlz.grupo7.models.FilaFactura;

/**
 * Servlet implementation class HistoricoController
 */
@WebServlet("/historico/*")
public class HistoricoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FacturaDao fDao;
	private FilaFacturaDao filaDao;

	public HistoricoController() {
		super();
		this.fDao = new FacturaDao();
		this.filaDao = new FilaFacturaDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/catalogo");

		switch (accion.toLowerCase()) {
		case "/historico" -> getHistorico(request, response);
		case "/detalle" -> getDetalle(request, response);

		default -> response.sendError(404, "No existe la accion: " + accion);
		}
	}

	/**
	 * Método que muestra la vista del detalle de una factura efectuada.
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getDetalle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession();
		var sId_factura = request.getParameter("id_factura_ver_detalle");

		var id_factura = Integer.parseInt(sId_factura);
		
		double total = 0;

		List<FilaFactura> listaFac = null;
		try {
			listaFac = filaDao.getByFactura(id_factura);
			total = fDao.getById(id_factura).getTotal();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		session.setAttribute("total", total);
		session.setAttribute("detalle_factura", listaFac);
		var rd = request.getRequestDispatcher("/views/facturas/detalle_factura.jsp");
		rd.forward(request, response);

	}

	/**
	 * Método que muestra la lista de facturas efectuadas. 
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getHistorico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession();
		var id_usuario = (int) session.getAttribute("usuario_id");

		var lista = fDao.all(id_usuario);

		
		session.setAttribute("historico_facturas", lista);

		var rd = request.getRequestDispatcher("/views/facturas/historico_facturas.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

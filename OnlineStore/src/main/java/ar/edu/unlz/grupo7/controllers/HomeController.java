package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.unlz.grupo7.daos.UsuariosDao;

@WebServlet(urlPatterns = { "/home", "/casa", "/index.jsp" })
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuariosDao uDao;

	public HomeController() {
		this.uDao = new UsuariosDao();
	}

	/**
	 * Se controla en este punto si el usuario es un cliente o un administrador.
	 * 
	 * @throws ServletException, IOException
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int usuarioId = (int) request.getSession().getAttribute("usuario_id");
		String rol = (String) request.getSession().getAttribute("rol");
		var session = request.getSession();
		var usuario = uDao.getById(usuarioId);

		if (rol.equals("cliente")) {
			var rd = request.getRequestDispatcher("/views/clientes/index.jsp");
			
			session.setAttribute("usuario", usuario);
			

			rd.forward(request, response);
		}

		if (rol.equals("administrador")) {
			var rd = request.getRequestDispatcher("/views/administradores/index.jsp");
			
			session.setAttribute("usuario", usuario);
			

			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendError(405);
	}

}

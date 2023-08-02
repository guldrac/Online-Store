package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.unlz.grupo7.daos.UsuariosDao;
import ar.edu.unlz.grupo7.factories.ConexionFactory;
import ar.edu.unlz.grupo7.models.Usuario;
import ar.edu.unlz.grupo7.iterators.StringIterator;

@WebServlet("/ingreso/*")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuariosDao uDao;

	public UsuariosController() {
		super();
		this.uDao = new UsuariosDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/login");

		switch (accion.toLowerCase()) {
		case "/login" -> getLogin(request, response);
		case "/register" -> getRegister(request, response);
		case "/logout" -> getLogout(request, response);

		default -> response.sendError(404, "No existe la accion: " + accion);
		}

	}

	/**
	 * Método que redirige a la vista de login 
	 * 
	 * @throws ServletException, IOException
	 * */
	private void getLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/usuarios/login.jsp");

		rd.forward(request, response);

		return;
	}

	/**
	 * Método que se encarga de cerrar la sesión del usuario y hace una redirección hacia home  
	 *  @throws ServletException, IOException
	 * 
	 * */
	private void getLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var session = request.getSession();

		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/home");

		return;
	}

	/**
	 * Método que redirige a la vista del registro de usuario  
	 *  @throws ServletException, IOException
	 * 
	 * */
	private void getRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/usuarios/register.jsp");

		rd.forward(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/");
		try {
			switch (accion.toLowerCase()) {
			case "/login" -> postLogin(request, response);
			case "/register" -> postRegister(request, response);

			default -> response.sendError(404, "No existe la accion: " + accion);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500, "Algo salio mal");
		}
	}

	
	/**
	 * Método que se encarga de traer los parametros del formulario de register, asigna un rol por defecto al
	 * usuario, hace las validaciones necesarias, inserta el usuario en la base de datos y redirige a home.
	 *  @throws ServletException, IOException
	 * */
	private void postRegister(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		var nombre = request.getParameter("nombre");
		var contrasenia = request.getParameter("contrasenia");
		var rol = "cliente";
		

		// Crear un iterador que recorra el nombre:
		var sIterador = new StringIterator(nombre);

		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 47) // Signos
					|| (letra >= 58 && letra <= 64) || (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sintaxis invalida.");
				return;
			}
		}
		// Crear un iterador que recorra la contraseï¿½a:
		sIterador = new StringIterator(contrasenia);

		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 47) // Signos
					|| (letra >= 58 && letra <= 64) || (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sintaxis invalida.");
				return;
			}
		}

		if ( nombre.equals("") || nombre == null || contrasenia.equals("") || contrasenia == null ) {
			response.sendError(400, "No se pueden dejar campos en blanco.");
			return;
		}

		
		if (!uDao.existsNombre(nombre)) {
			var usuario = new Usuario(nombre, contrasenia, rol);

			uDao.insert(usuario);

		} else {
			response.sendError(500, "Ya existe el usuario.");
			return;
		}

		response.sendRedirect(request.getContextPath() + "/ingreso");
	}

	
	/**
	 * Método que se encarga de traer los parametros del formulario del login, hace las validaciones necesarias,
	 * buscar el usuario ingresado en la base de datos, si existe, se settea el usuario_id y el rol en la sesión.
	 * Por último, redirige a home.  
	 * @throws SQLException IOException
	 * 
	 * */
	private void postLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		var nombre = request.getParameter("nombre");
		var contrasenia = request.getParameter("contrasenia");
		
		// Crear un iterador que recorra el nombre:
		var sIterador = new StringIterator(nombre);

		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 47) // Signos
					|| (letra >= 58 && letra <= 64) || (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sintaxis invalida.");
				return;
			}
		}
		// Crear un iterador que recorra el nombre:
		sIterador = new StringIterator(contrasenia);

		while (sIterador.hasNext()) {
			var letra = sIterador.next();
			if ((letra >= 32 && letra <= 47) // Signos
					|| (letra >= 58 && letra <= 64) || (letra >= 91 && letra <= 96) || (letra >= 123 && letra <= 126)) {
				response.sendError(400, "Sintaxis invalida.");
				return;
			}
		}

		if ( nombre.equals("") || nombre == null || contrasenia.equals("") || contrasenia == null ) {
			response.sendError(400, "No se pueden dejar campos en blanco.");
			return;
		}
		
		
		var usuario = uDao.getByNombre(nombre);

		if (usuario == null) {
			response.sendError(500, "El usuario o contraseña son incorrectos.");
			return;
		} else if (!usuario.validarContrasenia(contrasenia)) {
			response.sendError(500, "El usuario o contraseña son incorrectos.");
			return;
		}
		 
		
		var session = request.getSession();

		session.setAttribute("usuario_id", usuario.getId());
		session.setAttribute("rol", usuario.getRol());

		response.sendRedirect(request.getContextPath() + "/home");

	}
}
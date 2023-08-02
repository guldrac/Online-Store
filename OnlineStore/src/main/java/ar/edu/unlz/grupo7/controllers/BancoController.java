package ar.edu.unlz.grupo7.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ar.edu.unlz.grupo7.daos.UsuariosDao;


/**
 * Servlet implementation class BancoController
 */
@WebServlet("/ModBanco/*")
public class BancoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuariosDao uDao;

	public BancoController() {
		super();
		this.uDao = new UsuariosDao();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getPathInfo();
		accion = Optional.ofNullable(accion).orElse("/mostrarCredito");

		switch (accion) {
		case "/mostrarCredito" -> getMostrarCredito(request, response);
		case "/transferencias" -> getTransferencias(request, response);

		default -> response.sendError(404, "No existe la accion: " + accion);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var accion = request.getParameter("accion");

		switch (accion) {

		case "sumar" -> postModificarCredito(request, response);
		case "remover" -> postModificarCredito(request, response);
		case "transferir" -> postTransferir(request, response);

		default -> response.sendError(404, "No exissste la accion: " + accion);
		}
	}

	/**Este mï¿½todo trae de la sesiï¿½n el id del usuario, lo busca en el dao, settea dicho usuario y
	 *lo reenvia hacia la vista de miCredito.jsp
	 *@throws IOException, ServletException**/
	private void getMostrarCredito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/banco/miCredito.jsp");
		rd.forward(request, response);
	}

	/**Este mï¿½todo trae de la sesiï¿½n el id del usuario, lo busca en el dao, settea dicho usuario y
	 *lo reenvia hacia la vista de transferencias.jsp
	 *
	 *@throws IOException, ServletException
	 ***/
	private void getTransferencias(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		var rd = request.getRequestDispatcher("/views/banco/transferencias.jsp");
		rd.forward(request, response);
	}

	
	/**Este método post trae del request los parametros y la acción, valida que el usuario no ingrese numeros
	 * negativos o cero y dependiendo la acción ingresada, suma o remueve el credito en la cuenta
	 * 
	 * @throws IOException
	 *  **/
	
	private void postModificarCredito(HttpServletRequest request, HttpServletResponse response) throws IOException {
		var session = request.getSession();
		var id = (int) session.getAttribute("usuario_id");
		var sCreditoDisp = request.getParameter("credito");
		var sCredito = request.getParameter("agregarCantidad");

		var accion = request.getParameter("accion");

		var credito = Double.parseDouble(sCredito);
		var creditoDisp = Double.parseDouble(sCreditoDisp);
		double valor = 0;

		// Validar inputs:
		if (credito < 0) {
			response.sendError(400, "El nï¿½mero ingresado no puede ser negativo.");
			return;
		}
		if (credito < 1) {
			response.sendError(400, "No se pueden dejar campos en blanco. Mï¿½nimo posible= $1.");
			return;
		}
		
		//acciones a realizar 
		if(accion.equals("sumar")) {

			valor = creditoDisp += credito;
		}
		if(accion.equals("remover")) {

			if ((creditoDisp - credito) < 0) {
				response.sendError(400, "No puede retirar mï¿½s dinero del que posee.");
				return;
			}
			
			valor = creditoDisp -= credito;
		}
		
		//setteo de datos en los dao
		var usu = uDao.getById(id);
		usu.setId(id);
		usu.setCredito(valor);

		uDao.updateCredito(usu);
		
		session.setAttribute("usuario", usu);
		
		response.sendRedirect(request.getContextPath() + "/ModBanco");
	}


	/**Este método post trae del request los parametros, id del usuario que se encuentra en la sesion, la 
	 * cantidad que se desea transfererir y el nombre del usuario al que se va transferir. Hace las validaciones
	 * necesarias en los formularios y settea los creditos del usuario que envia y del que recibe la transferencia
	 * 
	 * @throws IOException
	 * **/
	private void postTransferir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// parametros del usuario que va recibir la transf
		var nombre = request.getParameter("nombreUsuario");
		var cant = request.getParameter("cantidadTransf");
		var cantidad = Double.parseDouble(cant);

		// parametros del usuario que va enviar credito:
		var session = request.getSession();
		var id = (int) session.getAttribute("usuario_id");
		

		double nuevoValor = 0;
		var usuActual = uDao.getById(id);
		var credUsuAct = usuActual.getCredito();

		// Validar inputs y si posee suficiente saldo para efectuar la transferencia:
		if ( nombre.equals("") || nombre == null) {
			response.sendError(400, "No se pueden dejar campos en blanco.");
			return;
		}
		
		if (credUsuAct == 0 || (credUsuAct - cantidad) <= 1) {
			response.sendError(400,
					"No tiene saldo suficiente para efectuar una transferencia. Ingrese dinero en su cuenta e intente nuevamente.");
			return;
		}

		if (cantidad < 0) {
			response.sendError(400, "El numero ingresado no puede ser negativo.");
			return;
		}

		nuevoValor = credUsuAct -= cantidad;
		usuActual.setId(id);
		usuActual.setCredito(nuevoValor);
		uDao.updateCredito(usuActual);

		double valor = 0;
		
		var usu = uDao.getByNombre(nombre);
		if ( usu == null ) {
			response.sendError(500, "El usuario al que desea transferir no existe.");
			return;
		}
		
		// parametros del usuario que va recibir la transf
		// Setear el nuevo credito al usuario que recibe la transferencia:
		var credUsu = usu.getCredito();
		valor = credUsu += cantidad;
		usu.setNombre(nombre);
		usu.setCredito(valor);
		uDao.updateCredito(usu);
		
		session.setAttribute("usuario", usuActual);
		
		response.sendRedirect(request.getContextPath() + "/ModBanco");

	}

}

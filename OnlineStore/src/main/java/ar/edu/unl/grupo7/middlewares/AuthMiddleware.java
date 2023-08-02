package ar.edu.unl.grupo7.middlewares;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AuthMiddleware
 */
@WebFilter(urlPatterns = { "/home/*", "/casa/*", "/index.jsp", "/articulos/*", "/carrito/*", "/historico/*","/ModBanco/*"  })
public class AuthMiddleware extends HttpFilter implements Filter {

	/**
	 * Este metodo trae el parametro del usuario_id en la sesión, si es null redirige a ingreso y sino continua
	 * con la cadena del doFilter.	 * 
	 * @throws IOException, ServletException
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;

		String appPath = httpRequest.getContextPath();

		var session = httpRequest.getSession();

		System.out.println(session.getAttribute("usuario_id"));

		Integer usuarioID = (Integer) session.getAttribute("usuario_id");

		if (usuarioID == null) {
			httpResponse.sendRedirect(appPath + "/ingreso");
			return;
		}

		chain.doFilter(request, response);
	}

}

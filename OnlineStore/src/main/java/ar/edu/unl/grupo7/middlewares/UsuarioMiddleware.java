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
 * Servlet Filter implementation class ClienteMiddleware
 */
@WebFilter(urlPatterns = { "/articulos/*", "/administradores/*"})
public class UsuarioMiddleware extends HttpFilter implements Filter {

	/**
	 * Este método trae el rol del usuario en la sesión y redirige hacia home
	 * 
	 *  @throws ServletException, IOException
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		var httpRequest = (HttpServletRequest) request;
		var httpResponse = (HttpServletResponse) response;

		String appPath = httpRequest.getContextPath();

		var session = httpRequest.getSession();

		String rol = (String) session.getAttribute("rol");

		if (rol.equals("cliente")) {
			httpResponse.sendRedirect(appPath + "/home");
			return;
		}

		chain.doFilter(request, response);
	}

}

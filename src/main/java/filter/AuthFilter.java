package filter;

import jakarta.servlet.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.quickbite.utils.SessionUtil;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/")
public class AuthFilter extends HttpFilter implements Filter {
       
	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ROOT = "/";

    public AuthFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// type cast the req res to the required class
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// get the requested URI
		String uri = req.getRequestURI();
		
		if (uri.endsWith(".css") || uri.endsWith(HOME) || uri.endsWith(ROOT)) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean isLoggedIn = (boolean) SessionUtil.getAttribute(req, "username");
		if (!isLoggedIn) {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath()+HOME);
			}
		} else {
			if (uri.endsWith(LOGIN) || uri.endsWith(REGISTER)) {
				res.sendRedirect(req.getContextPath()+HOME);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization logic if needed
		
	}
}

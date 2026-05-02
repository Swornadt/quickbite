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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.quickbite.model.UserModel;
import com.quickbite.utils.SessionUtil;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter extends HttpFilter implements Filter {
       
	private static final String LOGIN = "/login";
	private static final String REGISTER = "/register";
	private static final String HOME = "/home";
	private static final String ROOT = "/";
	
	// For RBAC
	private static final String ADMIN = "/admin";
	private static final String[] CUSTOMER = {"/checkout", "profile"};

    public AuthFilter() {
        super();
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// type cast the req res to the required class
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		
		String path = req.getServletPath();
		UserModel user = (session == null) ? null : (UserModel) session.getAttribute("user");
		
		// Admin Access
		if (path.startsWith("/admin")) {
			if (user == null || !"admin".equals(user.getRole())) {
				res.sendRedirect(req.getContextPath()+"/home");
				return;
			}
		}
		
		// Customer Access
		if (path.startsWith("/checkout") || path.startsWith("/profile")) {
			if (user == null) {
				res.sendRedirect(req.getContextPath()+"/login");
				return;
			}
		}
		
		// User logged in shouldnt go back to auth pages
		if (path.equals("/login") || path.equals("/register")) {
			if (user != null) {
				res.sendRedirect(req.getContextPath()+"/home");
				return;
			}
		}
		
		chain.doFilter(request, response);
	}
		
	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization logic if needed
		
	}
}

package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.quickbite.model.UserModel;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(urlPatterns="/*", asyncSupported = true)
public class AuthFilter extends HttpFilter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthFilter() {
        super();
    }

	public void destroy() {
	}

	@Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) 
            throws IOException, ServletException {

        HttpSession session = req.getSession(false);
        String path = req.getServletPath();
        
        UserModel user = (session == null) ? null : (UserModel) session.getAttribute("user");
        String role = (user != null) ? user.getRole() : null;
        
        // Logged-in users shouldn't revisit auth pages
        if (path.equals("/login") || path.equals("/register")) {
        	if (user != null) {
        		
        		if ("admin".equalsIgnoreCase(role)) {
                	res.sendRedirect(req.getContextPath() + "/admin");
                	
                } else if ("staff".equalsIgnoreCase(role)) {
                	res.sendRedirect(req.getContextPath() + "/kitchen");
                	
                } else if ("customer".equalsIgnoreCase(role)) {
                	res.sendRedirect(req.getContextPath() + "/home");
                }
        		return;
        	}
        }
        
        // Admin Access
        if (path.startsWith("/admin")) {
            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            if (!"admin".equalsIgnoreCase(role)) {
            	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized: Admin access required.");
            	return;
            }
        }
        
        // Staff Access
        else if (path.startsWith("/kitchen")) {
            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            if (!"staff".equalsIgnoreCase(role)) {
            	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized: Staff access required.");
            	return;
            }
        }
        
        // Customer Access
        else if (path.startsWith("/checkout") || path.startsWith("/profile") || path.startsWith("/cart") || path.startsWith("/profile")) {
            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
            if (!"customer".equalsIgnoreCase(role)) {
            	res.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized: Customer access required.");
            	return;
            }
        }
        
        // for public path or permissions align we proceed
        chain.doFilter(req, res);
    }
		
	public void init(FilterConfig fConfig) throws ServletException {	
	}
}

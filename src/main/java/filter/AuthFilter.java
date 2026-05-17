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
@WebFilter(urlPatterns="/*", asyncSupported = true)
public class AuthFilter extends HttpFilter {

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

        
        
        // Admin Access
        if (path.startsWith("/admin")) {
            if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
                res.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        
        // Customer Access
        if (path.startsWith("/checkout") || path.startsWith("/profile")) {
            if (user == null) {
                res.sendRedirect(req.getContextPath() + "/login");
                return;
            }
        }
        
        // Logged-in users shouldn't revisit auth pages
        if (path.equals("/login") || path.equals("/register")) {
            if (user != null) {
                res.sendRedirect(req.getContextPath() + "/home");
                return;
            }
        }
        
        chain.doFilter(req, res);
    }
		
	public void init(FilterConfig fConfig) throws ServletException {
		// Initialization logic if needed
		
	}
}

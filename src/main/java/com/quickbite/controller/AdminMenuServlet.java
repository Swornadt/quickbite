package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.quickbite.dao.OutletDAO;
import com.quickbite.dao.OutletItemDAO;
import com.quickbite.model.Outlet;
import com.quickbite.model.OutletItem;

/**
 * Servlet implementation class AdminMenuServlet
 */
@WebServlet("/AdminMenuServlet")
public class AdminMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMenuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	OutletItemDAO outletItemDAO = new OutletItemDAO();
    	OutletDAO outletDAO = new OutletDAO();

        String outletIdStr = request.getParameter("outletId");

        // Load all outlets for dropdown
        List<Outlet> outlets = outletDAO.getAllOutlets();
        request.setAttribute("outlets", outlets);

        List<OutletItem> outletItems = null;

        if (outletIdStr != null && !outletIdStr.isEmpty()) {
            try {
                int outletId = Integer.parseInt(outletIdStr);
                outletItems = outletItemDAO.getItemsByOutlet(outletId);
                request.setAttribute("selectedOutletId", outletId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            // If no outlet selected, load from first outlet (optional)
            if (!outlets.isEmpty()) {
                int firstOutletId = outlets.get(0).getOutletId();
                outletItems = outletItemDAO.getItemsByOutlet(firstOutletId);
                request.setAttribute("selectedOutletId", firstOutletId);
            }
        }

        request.setAttribute("outletItems", outletItems);

        request.getRequestDispatcher("/WEB-INF/views/admin/admin-menu-view.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

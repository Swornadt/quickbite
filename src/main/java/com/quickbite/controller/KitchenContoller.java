package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;

import com.quickbite.dao.OrderOutletItemDAO;
import com.quickbite.model.OrderOutletItem;

/**
 * Servlet implementation class OrderManagement
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/kitchen/*" })
public class KitchenContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KitchenContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getPathInfo();
		
		if (path == null || path.equals("/")) {
			handleOrderManagement(request,response);
		} else {
			handleOrderDetails(request,response, path.substring(1));
		}
	}
	
	private void handleOrderManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//session stores attibutes as object
		Integer outletId = (Integer) request.getSession().getAttribute("outletId");
		
		if (outletId==null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
		
		OrderOutletItemDAO dao = new OrderOutletItemDAO();
		List<OrderOutletItem> orders = dao.getOrdersByOutlet(outletId);
		
		List<OrderOutletItem> pending = dao.getOrderByStatus(orders, 0);
		List<OrderOutletItem> ongoing = dao.getOrderByStatus(orders, 1);
		List<OrderOutletItem> complete = dao.getOrderByStatus(orders, 2);
		
		request.setAttribute("pending", pending);
		request.setAttribute("ongoing", ongoing);
		request.setAttribute("complete", complete);
		request.getRequestDispatcher("/WEB-INF/views/staff/order-management.jsp").forward(request,response);
	}
	
	private void handleOrderDetails(HttpServletRequest request, HttpServletResponse response, String orderId ) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

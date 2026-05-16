package com.quickbite.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.sql.Timestamp;

import com.quickbite.dao.OrderOutletItemDAO;
import com.quickbite.model.OrderOutletItemModel;
import com.quickbite.service.KitchenService;

/**
 * Servlet implementation class OrderManagement
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/kitchen/*" })
public class KitchenContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitchenService kitchenService = new KitchenService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KitchenContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles HTTP GET requests to route back relevant order tracking information for kitchen staff.
	 * 
	 * @param request
	 * @param response 
	 * @throws ServletException
	 * @throws IOException
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
		List<OrderOutletItemModel> orders = dao.getOrdersByOutlet(outletId);
		
		List<OrderOutletItemModel> pending = dao.getOrderByStatus(orders, 0);
		List<OrderOutletItemModel> ongoing = dao.getOrderByStatus(orders, 1);
		List<OrderOutletItemModel> complete = dao.getOrderByStatus(orders, 2);
		
		request.setAttribute("pending", pending);
		request.setAttribute("ongoing", ongoing);
		request.setAttribute("complete", complete);
		request.getRequestDispatcher("/WEB-INF/views/staff/order-management.jsp").forward(request,response);
	}
	
	private void handleOrderDetails(HttpServletRequest request, HttpServletResponse response, String orderId ) throws ServletException, IOException {
		
		Integer outletId = (Integer) request.getSession().getAttribute("outletId");
	    
		if (outletId == null) {
	        response.sendRedirect(request.getContextPath() + "/login");
	        return;
	    }

	    int order = Integer.parseInt(orderId);
	    OrderOutletItemDAO dao = new OrderOutletItemDAO();
	    List<OrderOutletItemModel> orderdetails = dao.getOrderDetail(order, outletId);

	    int totalQty = 0;
	    String orderStatus = "";
	    String orderType = "";
	    String outletName = "";
	    String orderNote = "";
	    String orderDateString = "";
	    String orderTime = "";


	    for (OrderOutletItemModel item : orderdetails) {
	        totalQty += item.getItemQty();
	        orderStatus = item.getOrderStatusLabel();
	        orderType = item.getOrderTypeLabel();
	        outletName = item.getOutletName();
	        orderNote = item.getOrderNote();
	        Timestamp orderDate = item.getOrderDate();
	        orderDateString = new java.text.SimpleDateFormat("MM/dd/yyyy").format(orderDate);
	        orderTime = new java.text.SimpleDateFormat("HH:mm").format(orderDate);
	    }

	    request.setAttribute("orderId", order);
	    request.setAttribute("orderStatus", orderStatus);
	    request.setAttribute("orderType", orderType);
	    request.setAttribute("outletName", outletName);
	    request.setAttribute("orderNote", orderNote);
	    request.setAttribute("orderDate", orderDateString);
	    request.setAttribute("orderTime", orderTime);
	    request.setAttribute("totalQty", totalQty);
	    request.setAttribute("order", orderdetails);
	    request.getRequestDispatcher("/WEB-INF/views/staff/order-details.jsp")
	           .forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();
        int orderId = Integer.parseInt(path.substring(1));
        String action = request.getParameter("action");

        if (action.equals("initiate")) {
            kitchenService.initiateOrder(orderId);
        } else if (action.equals("ready")) {
            kitchenService.markReady(orderId);
        } else if (action.equals("itemDone")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            kitchenService.markItemDone(orderId, itemId);
        }

        response.sendRedirect(request.getContextPath() + "/kitchen/" + orderId);
    }
	
}
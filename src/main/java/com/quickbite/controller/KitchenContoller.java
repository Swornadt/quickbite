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
	
	/**
	 * Organizes outlet orders by status.
	 * 
	 * @param request  
	 * @param response
	 * @throws ServletException 
	 * @throws IOException     
	 */
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
	
	/**
	  Generates a detailed dashboard view for a single customer order inside the kitchen view.
	 * 
	 * Checks whether the user session consists of authorized outletId
	 * Gets the orderId
	 * Uses OrderOutletItemDAO to retrieve all items related to that order
	 * Then collects information related to the order
	 * 
	 * 
	 * @param request  
	 * @param 
	 * @param orderId
	 * @throws ServletException
	 * @throws IOException  
	 */
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
	    String outletOrderStatus = "";
	    String orderType = "";
	    String outletName = "";
	    String orderNote = "";
	    String orderDateString = "";
	    String orderTime = "";
	    boolean itemReady = true;

	    for (OrderOutletItemModel item : orderdetails) {
	        totalQty += item.getItemQty();
	        outletOrderStatus = item.getOutletOrderStatusLabel();
	        orderType = item.getOrderTypeLabel();
	        outletName = item.getOutletName();
	        orderNote = item.getOrderNote();
	        Timestamp orderDate = item.getOrderDate();
	        orderDateString = new java.text.SimpleDateFormat("MM/dd/yyyy").format(orderDate);
	        orderTime = new java.text.SimpleDateFormat("HH:mm").format(orderDate);
	        if (item.getItemStatus() == 0) {
	        	itemReady = false;
	        }
	    }

	    request.setAttribute("orderId", order);
	    request.setAttribute("orderStatus", outletOrderStatus);
	    request.setAttribute("orderType", orderType);
	    request.setAttribute("outletName", outletName);
	    request.setAttribute("orderNote", orderNote);
	    request.setAttribute("orderDate", orderDateString);
	    request.setAttribute("orderTime", orderTime);
	    request.setAttribute("totalQty", totalQty);
	    request.setAttribute("error", request.getSession().getAttribute("error"));
	    request.getSession().removeAttribute("error");
	    request.setAttribute("itemReady", itemReady);
	    request.setAttribute("order", orderdetails);
	    request.getRequestDispatcher("/WEB-INF/views/staff/order-details.jsp")
	           .forward(request, response);
	}
	

	/**
	 * Handles POST requests to process state changes and status updates for food preparation items.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getPathInfo();
        int orderId = Integer.parseInt(path.substring(1));
        String action = request.getParameter("action");
        Integer outletId = (Integer) request.getSession().getAttribute("outletId");
        
        if (action.equals("initiate")) {
            kitchenService.initiateOrder(orderId, outletId);
        } else if (action.equals("ready")) {
        	 boolean success = kitchenService.markReady(orderId, outletId);
             if (!success) {
                 request.getSession().setAttribute("error", "Please complete all items before marking as ready.");
                 response.sendRedirect(request.getContextPath() + "/kitchen/" + orderId);
                 return;
             }
        } else if (action.equals("itemDone")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            kitchenService.markItemDone(orderId, itemId);
        }

        response.sendRedirect(request.getContextPath() + "/kitchen/" + orderId);
    }
	
}
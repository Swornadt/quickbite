package com.quickbite.service;

import com.quickbite.dao.OrderDAO;
import com.quickbite.dao.PaymentDAO;
import com.quickbite.model.CartItemModel;
import com.quickbite.model.PaymentModel;
import com.quickbite.model.UserModel;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {

    private static final String CART_SESSION_KEY = "cart";
    private OrderDAO orderDAO = new OrderDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();

    /**
     * Retrieve the cart from session, or create a new one if it doesn't exist
     * 
     * @param session The current HttpSession where the cart is stored.
     * @return cart - In the form of List<CartItemModel> as a list of items in the model of CartItem.
     */
    @SuppressWarnings("unchecked")
    public List<CartItemModel> getCart(HttpSession session) {
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    /**
     * Adds an item to the in-session cart
     * 
     * If the item already exists, the quantity is increased,
     * otherwise the new item is appended to the list.
     * 
     * @param session
     * @param newItem - The CartItemModel representing the menu item to be added.
     */
    public void addToCart(HttpSession session, CartItemModel newItem) {
        List<CartItemModel> cart = getCart(session);
        
        // Check if item already exists, if so, just update quantity
        for (CartItemModel item : cart) {
            if (item.getItemId() == newItem.getItemId()) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        cart.add(newItem);
    }
    
    /**
     * Removes all the instances of a specific item from the cart
     *     
     * @param session
     * @param itemId - The primary key of the item to be removed.
     */
    public void removeFromCart(HttpSession session, int itemId) {
    	List<CartItemModel> cart = getCart(session);
    	cart.removeIf(item -> item.getItemId() == itemId);
    }

    /**
     * Updates the quantity of an existing item in the cart.
     * 
     * This method instantiates the cart in the session and updates amount
     * by adding or subtracting from the current quantity.
     * It includes a validation check to ensure resulting quantity never
     * drops below 1.
     * 
     * @param session
     * @param itemId - The ID of the item to update
     * @param amount - The amount to add or subtracted {-1, 1}
     */
    public void updateQuantity(HttpSession session, int itemId, int amount) {
    	List<CartItemModel> cart = getCart(session);
    	for (CartItemModel item : cart) {
    		if (item.getItemId() == itemId) {
    			int newQty = item.getQuantity()+amount;
    			// validation for non-negative
    			if (newQty > 0) {
    				item.setQuantity(newQty);
    			}
    			return;
    		}
    	}
    }
    
    /**
     * Calculates the total cost of all items currently in the cart.
     * 
     * @param cart - The list of items to process.
     * @return The sum of (unit price * quantity) for every item in the cart list.
     */
    public double calculateSubtotal(List<CartItemModel> cart) {
        return cart.stream().mapToDouble(CartItemModel::getTotalPrice).sum();
    }

    /**
     * Orchestration method to handle checkout process by preparing delivery data 
     * and placing the order.
     * 
     * @param user
     * @param cart
     * @param deliveryTimeType - {"asap", "later"}
     * @param deliveryDate - The raw date string from UI
     * @param timeSlot - The raw time string from UI
     * @param notes - the user's custom notes/special instructions
     * @return true if the order is successful
     * @throws Exception
     */
    public boolean processOrder(UserModel user, List<CartItemModel> cart, String deliveryTimeType, 
			String deliveryDate, String timeSlot, String notes) throws Exception {
		if (cart == null || cart.isEmpty()) {
			return false;
		}
		// Encapsulating the date and time for Schedule Later
		String finalPreferredDate = null;
		String finalInstructions = (notes != null) ? notes : "";
			
		if ("later".equals(deliveryTimeType)) {
			if (deliveryDate != null && !deliveryDate.isEmpty() && timeSlot != null && !timeSlot.isEmpty()) {
				finalPreferredDate = deliveryDate + " " + timeSlot + ":00";
			}
		} else {
			finalInstructions = "[ASAP] " + finalInstructions;
		}
		
		// calling method for final validation and placing order
		return placeOrder(user, cart, finalInstructions.trim(), finalPreferredDate);
	}
    
    /**
     * Finalizes the checkout process by inserting the cart data into the database.
     * 
     * Validates the state of the user and cart before calling the DAO to create
     * the order in the database.
     * 
     * @param user - The UserModel of the customer placing the order.
     * @param cart - The list of items to be purchased.
     * @param specialInstructions - Notes or requests for the kitchen.
     * @param preferredDate - The requested date/time of completion.
     * @return true if the order was successfully created in the database; false otherwise.
     * @throws Exception - If a database connection error or integrity violation occurs.
     */
	public boolean placeOrder(UserModel user, List<CartItemModel> cart, String specialInstructions, String preferredDate) throws Exception{
		if (user == null || cart == null || cart.isEmpty()) {
	        return false;
	    }
		
		double totalAmount = calculateSubtotal(cart);
	    PaymentModel payment = new PaymentModel();
	    payment.setAmount(totalAmount);
	    payment.setPaymentStatus("Completed");
	    payment.setPaymentDate(new java.sql.Timestamp(System.currentTimeMillis()));
	   
	    int paymentId = paymentDAO.createPayment(payment);
	    if (paymentId <= 0) {
	        return false;
		}
	    int orderId = orderDAO.createOrder(user.getUserId(), cart, specialInstructions, preferredDate, paymentId);

	    return orderId > 0;
	}
	
	/**
	 * Organizes the cart items into groups based on the outlet they belong to.
	 * 
	 * This is essential for the UI rendering where the orders need to be
	 * separated by the outlet. 
	 * 
	 * @param session
	 * @return Map where: key - outlet name; value - list of items from that outlet.
	 */
	public Map<String, List<CartItemModel>> getGroupedCart(HttpSession session) {
		List<CartItemModel> cart = getCart(session);
		
		return cart.stream().collect(Collectors.groupingBy(CartItemModel::getOutletName));
	}
}
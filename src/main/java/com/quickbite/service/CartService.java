package com.quickbite.service;

import com.quickbite.model.CartItemModel;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    // Retrieve the cart from session, or create a new one if it doesn't exist
    @SuppressWarnings("unchecked")
    public List<CartItemModel> getCart(HttpSession session) {
        List<CartItemModel> cart = (List<CartItemModel>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

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

    public double calculateSubtotal(List<CartItemModel> cart) {
        return cart.stream().mapToDouble(CartItemModel::getTotalPrice).sum();
    }
}
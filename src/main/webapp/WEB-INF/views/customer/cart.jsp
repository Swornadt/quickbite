<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../css/cart.css">
<title>Home | QuickBite</title>
</head>

<body>
<!-- Header -->
<%@ include file="../common/navbar1.jsp" %>

<!-- Main container -->
<div class="container">

    <!-- Left Cart items List -->
    <div class="left-container">

        <!-- Selection Heading -->
        <div class="selection-heading">
            <div class="heading-group"> 
                <input type="checkbox" id="select-all"/>
                <label for="select-all">Select All</label>
            </div>
            <div style="display:flex; justify-content: space-between;">
                <span class="icon-trash">🗑️</span>
                <span>Delete All</span>
            </div>
        </div>

        <!-- Items List -->
        <div class="selection-list">
        <!--  TODO: Integrate data from DAO -->
        <%
        List<Map<String, Object>> cartItems = (List<Map<String, Object>>) request.getAttribute("cartItems");
        
        double subtotal = 0;
        
        if (cartItems != null && !cartItems.isEmpty()) {
            for (Map<String, Object> item : cartItems) {
                String name = (String) item.get("name");
                double price = (Double) item.get("price");
                int quantity = (Integer) item.get("quantity");
                int id = (Integer) item.get("id");
                
                subtotal += (price * quantity);
        %>

            <!-- Rendered forEach -->
            <div class="selection-item" data-id="<%= id %>">
                <div class="item-main">
                    <input type="checkbox" class="item-check">
                    <span class="item-name"><%= name %></span>
                </div>
                <div class="item-details">
                    <span class="item-price"><%= String.format("%.2f", price) %></span>
                    <div class="item-actions">
                        <span class="icon-heart">♡</span> <!-- TODO: toggle fav/unfav -->
                        <a href="RemoveFromCart?id=<%= id %>" class="icon-trash">🗑️</a> <!-- TODO: remove from cart -->
                    </div>
                </div>
                <div class="quantity-control">
                	<!--  TODO: manage Cart state -->
                    <button onclick="updateQty(<%= id %>, -1)">-</button>
                    <span class="qty"><%= quantity %></span>
                	<button onclick="updateQty(<%= id %>, 1)">+</button>
                </div>
            </div>
		<% 
                }
            } else { 
        %>
            <div class="empty-cart-msg" style="padding: 20px; text-align: center;">
                Your cart is empty. <a href="outlets.jsp">Browse Outlets</a>
            </div>
        <% 
            }
        %>
        </div>
    </div>

    <!-- Order Summary -->
    <div class="right-container">
        <!-- Details -->
         <h3>Order Summary</h3>
        <div class="summary-row">
            <span>SUB TOTAL</span>
            <span>Rs. <%= String.format("%.2f", subtotal) %></span>
        </div>
        <div class="summary-row">
            <span>VAT</span>
            <span class="muted">Included in price</span>
        </div>
        <div class="summary-divider"></div>
        <div class="summary-row grand-total">
            <span>GRAND TOTAL</span>
            <span class="total-price">Rs. <%= String.format("%.2f", subtotal) %></span>
        </div>
        <!-- Button -->
        <button class="checkout-btn">Proceed To Checkout</button>
    </div>
</div>

<!-- Footer -->
<%@ include file="../common/footer.jsp" %>

</body>

</html>
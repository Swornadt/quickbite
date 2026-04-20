<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.quickbite.model.CartItemModel" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/checkout.css" />
<title>Checkout | QuickBite</title>
</head>

<body>

<!-- Header -->
<%@ include file="../common/navbar1.jsp"%>

<!-- Page Hero -->
<div class="checkout-heading">
    <div class="heading-inner">
        <h1 class="page-title">Checkout</h1>
    </div>
</div>

<!-- main Container-->
<div class="container">

    <!-- Left Pane -->
    <div class="left-container">

        <!-- DATE AND TIME SECTION -->
        <div class="checkout-card">
            <div class="checkout-card-heading">
                <p>DATE AND TIME</p>
            </div>
            <div class="checkout-card-body">

                <!-- Radio Options -->
                <div class="radio-group">
                    <label class="radio-label">
                        <input type="radio" name="deliveryTime" value="asap" id="radioAsap">
                        <span class="radio-custom"></span>
                        As Soon As Possible
                    </label>
                    <label class="radio-label">
                        <input type="radio" name="deliveryTime" value="later" id="radioLater" checked>
                        <span class="radio-custom"></span>
                        Schedule for Later
                    </label>
                </div>

                <!-- Date & Time Pickers (shown only when "Schedule for Later" is selected) -->
                <div class="datetime-row" id="datetimeRow">
                    <div class="datetime-field">
                        <label class="field-label">DATE</label>
                        <input type="date" class="field-input" id="deliveryDate" name="deliveryDate"/>
                    </div>
                    <div class="datetime-field">
                        <label class="field-label">TIME</label>
                        <select class="field-input" id="deliveryTime" name="deliveryTimeSlot">
                            <option value="" selected disabled>--Select--</option>
                            <option value="07:00">07:00 AM</option>
                            <option value="07:30">07:30 AM</option>
                            <option value="08:00">08:00 AM</option>
                            <option value="08:30">08:30 AM</option>
                            <option value="09:00">09:00 AM</option>
                            <option value="09:30">09:30 AM</option>
                            <option value="10:00">10:00 AM</option>
                            <option value="10:30">10:30 AM</option>
                            <option value="11:00">11:00 AM</option>
                            <option value="11:30">11:30 AM</option>
                            <option value="12:00">12:00 PM</option>
                            <option value="12:30">12:30 PM</option>
                            <option value="13:00">01:00 PM</option>
                            <option value="13:30">01:30 PM</option>
                            <option value="14:00">02:00 PM</option>
                            <option value="14:30">02:30 PM</option>
                            <option value="15:00">03:00 PM</option>
                            <option value="15:30">03:30 PM</option>
                            <option value="16:00">04:00 PM</option>
                            <option value="16:30">04:30 PM</option>
                            <option value="17:00">05:00 PM</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <!-- Special note Section -->
        <div class="checkout-card" style="margin-top: 20px;">
            <div class="checkout-card-heading">
                <p>SPECIAL INSTRUCTIONS</p>
            </div>
            <div class="checkout-card-body">
                <textarea
                    class="notes-input"
                    name="specialInstructions"
                    placeholder="Add Notes"
                    rows="4"
                ></textarea>
            </div>
        </div>

        <!-- Action Buttons -->
        <div class="action-buttons">
            <a href="${pageContext.request.contextPath}/CartServlet" class="back-button">GO BACK</a>
            <button type="submit" class="continue-button" onclick="submitCheckout()">CONTINUE</button>
        </div>

    </div>

    <!-- Right Sidepane — MY CART SUMMARY ===== -->
    <div class="right-container">
        <h3 class="cart-summary-title">MY CART</h3>

        <%
            List<CartItemModel> cartItems = (List<CartItemModel>) request.getAttribute("cartItems");
            String locationOfFood = (String) request.getAttribute("locationOfFood");
            double subtotal = 0;

            if (locationOfFood == null) locationOfFood = "Location of Food";
        %>

        <div class="cart-location-banner">
            <span><%= locationOfFood %></span>
        </div>

        <div class="cart-items-list">
            <c:choose>
            	<c:when test="${not empty cartItems}">
            		<c:forEach var="item" items="${cartItems}">
            			<div class="cart-item-row">
			                <span class="cart-item-qty">${item.quantity}x</span>
			                <span class="cart-item-name">${item.itemName}</span>
			                <span class="cart-item-price">
			                	Rs. <fmt:formatNumber value="${item.totalPrice}" pattern="#,##0.00"/>
							</span>
			            </div>
            		</c:forEach>
            	</c:when>
            	<c:otherwise>
            		<div class="empty-cart-msg">
		                Your cart is empty. <a href="<%=request.getContextPath() %>/views/customer/outlets">Browse Outlets</a>
		            </div>
            	</c:otherwise>
            </c:choose>
        </div>

        <a href="<%=request.getContextPath() %>/views/customer/outlets" class="add-more-link">+ Add More Items</a>

        <div class="summary-divider"></div>

        <div class="summary-row">
            <span>SUB TOTAL</span>
            <span>Rs. ${subtotal}</span>
        </div>
        <div class="summary-row">
            <span>VAT</span>
            <span class="muted">included in price</span>
        </div>

        <div class="summary-divider"></div>

        <div class="summary-row grand-total">
            <span>GRAND TOTAL</span>
            <span class="total-price">Rs. ${subtotal}</span>
        </div>
    </div>

</div>

<!-- Footer -->
<%@ include file="../common/footer.jsp" %>

<script>
	const radioLater = document.getElementById('radioLater');
	const radioAsap = document.getElementById('radioAsap');
	const datetimeRow = document.getElementById('datetimeRow');
	
	radioLater.addEventListener('change', () => datetimeRow.style.display = 'flex');
	radioAsap.addEventListener('change', () => datetimeRow.style.display = 'none');
</script>
</body>
</html>

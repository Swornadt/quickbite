<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/cart.css">
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
		<c:choose>
			<c:when test="${not empty userCart}">
				<c:forEach var="item" items="${userCart}">
				
					<div class="selection-item" data-id="${item.itemId}">
		                <div class="item-main">
		                    <input type="checkbox" class="item-check">
		                    <span class="item-name">${item.itemName}</span>
		                </div>
		                
		                <div class="item-details">
		                    <span class="item-price">
		                    	Rs. <fmt:formatNumber value="${item.unitPrice}" type="number" minFractionDigits="2"/>
		                    </span>
		                    <div class="item-actions">
		                        <span class="icon-heart">♡</span> <!-- TODO: toggle fav/unfav -->
		                        <a href="${pageContext.request.contextPath}/remove-from-cart?itemId=${item.itemId}" class="icon-trash">🗑️</a> <!-- TODO: remove from cart -->
		                    </div>
		                </div>
		                
		                <div class="quantity-control">
		                	<!-- Manage Cart state -->
		                    <button onclick="updateQty(${item.itemId}, -1)">-</button>
		                    <span class="qty">${item.quantity}</span>
		                	<button onclick="updateQty(${item.itemId}, 1)">+</button>
		                </div>
		            </div>
				</c:forEach>
			</c:when>
		</c:choose>
        </div>
    </div>

    <!-- Order Summary -->
    <div class="right-container">
        <!-- Details -->
         <h3>Order Summary</h3>
        <div class="summary-row">
            <span>SUB TOTAL</span>
            <span>Rs. <fmt:formatNumber value="${subtotal}" type="number" minFractionDigits="2"/></span>
   		</div>
        <div class="summary-row">
            <span>VAT</span>
            <span class="muted">Included in price</span>
        </div>
        <div class="summary-divider"></div>
        <div class="summary-row grand-total">
            <span>GRAND TOTAL</span>
            <span class="total-price">Rs. <fmt:formatNumber value="${subtotal}" type="number" minFractionDigits="2"/></span>
        </div>
        <!-- Button -->
        <a href="${pageContext.request.contextPath}/checkout">
		    <button class="checkout-btn">Proceed To Checkout</button>
		</a>
    </div>
</div>

<!-- Footer -->
<%@ include file="../common/footer.jsp" %>

<script src="${pageContext.request.contextPath}/js/cart.js"></script>
</body>

</html>
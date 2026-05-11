<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBite | Payment</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/payment.css">
</head>
<body>
    <%@ include file="../common/navbar1.jsp" %>

    <div class="main-container">
        <h1>Complete Payment Now!</h1>

        <div class="payment-container">
            
            <!-- QR Section -->
            <div class="qr-section">
                <div class="qr-box">
                    <img src="${pageContext.request.contextPath}/assets/qr.jpeg" 
                         alt="Payment QR Code">
                </div>
            </div>

            <!-- Order Details -->
            <div class="order-section">
                <div class="order-card">
                    <h2 class="section-title">Order Details</h2>
                    
                    <div class="detail-row">
                        <span class="label">Order ID:</span>
                        <strong>#2732026</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Order Type:</span>
                        <strong>${pending_type == 'later' ? 'Scheduled' : 'As Soon As Possible'}</strong> 
                    </div>
                </div>
                    
				<c:if test="${pending_type == 'later'}">
					<div class="detail-row">
						<span class="label">Scheduled Date:</span>
						<strong>${pending_date}</strong>
					</div>
			        <div class="detail-row">
			            <span class="label">Scheduled Time:</span>
			            <strong>${pending_slot}</strong>
			        </div>
			    </c:if>
			        
			    <hr>
			        
			    <h3 class="section-title">Ordered Items</h3>
			    <c:forEach var="item" items="${flatCart}">
			        <div class="item">
			            <span>${item.quantity}x ${item.itemName}</span>
						<strong>Rs. <fmt:formatNumber value="${item.totalPrice}" pattern="#,##0.00"/></strong>
			        </div>
			    </c:forEach>
			        
			    <div class="total-row">
					<span>SUB TOTAL</span>
					<strong>Rs. <fmt:formatNumber value="${subtotal}" pattern="#,##0.00"/></strong>
				</div>
			        
				<div class="grand-total">
					<span>GRAND TOTAL</span>
					<strong>Rs. <fmt:formatNumber value="${subtotal}" pattern="#,##0.00"/></strong>
				</div>

				<div class="buttons">
					<a href="${pageContext.request.contextPath}/checkout" class="btn btn-back">← Go Back</a>
			            
					<form action="${pageContext.request.contextPath}/payment" method="POST" style="display:inline;">
						<button type="submit" class="btn btn-complete">Complete!</button>
			        </form>
			    </div>
			</div>
		</div>
	</div>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>
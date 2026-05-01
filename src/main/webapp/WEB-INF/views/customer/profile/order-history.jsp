<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Order History | QuickBite</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-history.css">
</head>

<body>
	<%@ include file="../../common/navbar1.jsp" %>
	
	<div class="history-container">
		<!-- Filter -->
		<div class="filter">
			<button class="filter-btn">
				<span class="filter-icon">=</span>
				Filter Order History
			</button>
		</div>
		
		<!-- Order Section -->
		<h2 class="section-title">ORDER HISTORY</h2>
		<div class="divider"></div>
		
		<!-- Current Order Section -->
		<div class="orders-section">
			<h3 class="order-label">CURRENT ORDERS</h3>
			
			<c:choose>
				<c:when test="${not empty currentOrders}">
					<c:forEach var="order" items="${currentOrders}">
						<div class="divider"></div>
						<div class="order-card">
							<p> Order ID: <span>#${order.orderId}</span></p>
							<p> Order Date: <span><fmt:formatDate value="${order.orderDate}" pattern="dd MMM yyyy"/></span></p>
							<p> Order Status: <span class="status-pending">${order.status}</span></p>
							<p> Order ID: <span>${order.locationName}</span></p>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p class="no-orders">No current orders found.</p>
				</c:otherwise>
			</c:choose>
			<div class="divider"></div>
		</div>
		
		<!-- Past Order Section -->
		<div class="orders-section">
			<h3 class="order-label">PAST ORDERS</h3>
			
			<c:choose>
				<c:when test="${not empty pastOrders}">
					<c:forEach var="order" items="${currentOrders}">
						<div class="divider"></div>
						<div class="order-card">
							<p> Order ID: <span>#${order.orderId}</span></p>
							<p> Order Date: <span><fmt:formatDate value="${order.orderDate}" pattern="dd MMM yyyy"/></span></p>
							<p> Order Status: <span class="status-pending">Completed</span></p>
							<p> Order ID: <span>${order.locationName}</span></p>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p class="no-orders">No records for past orders found.</p>
				</c:otherwise>
			</c:choose>
		</div>
		
		<!-- Pagination -->
        <div class="pagination">
            <span class="page-btn">Previous</span>
            <span class="pager-current">1</span>
            <span class="pager-btn">Next</span>
        </div>
	</div>
	
	<%@ include file="../../common/footer.jsp" %>
</body>
</html>
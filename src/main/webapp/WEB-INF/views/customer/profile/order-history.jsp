<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Order History | QuickBite</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-history.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user-profile.css" />
</head>

<body>
	<%@ include file="/WEB-INF/views/common/navbar1.jsp" %>
      <%@ include file="/WEB-INF/views/common/utility-bar.jsp" %>
<div class="user-profile-section-container">
<section class="user-profile-section">
<%@ include file="/WEB-INF/views/common/user-profile-sidenav.jsp" %>
<div class="user-profile-container">
	
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
			
			<!-- Scroll Section -->
			<div class="order-list-scroll">
				<c:choose>
					<c:when test="${not empty currentOrders}">
						<c:forEach var="order" items="${currentOrders}">
							<div class="divider"></div>
							<div class="order-card">
								<p> Order ID: <span>#${order.orderId}</span></p>
								<p> Order Date: <span><fmt:formatDate value="${order.orderDateAsDate}" pattern="dd MMM yyyy"/></span></p>
								<p> Order Status: 
								    <span class="${order.orderStatus == 2 ? 'status-completed' : 'status-current'}">
								        ${order.statusText}
								    </span>
								</p>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="no-orders">No current orders found.</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="divider"></div>
		</div>
		
		<!-- Past Order Section -->
		<div class="orders-section">
			<h3 class="order-label">PAST ORDERS</h3>
			
			<!-- Scroll Section -->
			<div class="order-list-scroll">
				<c:choose>
					<c:when test="${not empty pastOrders}">
						<c:forEach var="order" items="${pastOrders}">
							<div class="divider"></div>
							<div class="order-card-complete">
							    <div class="order-card-complete-info">
							        <p>Order ID: <span>#${order.orderId}</span></p>
							        <p>Order Date: <span><fmt:formatDate value="${order.orderDateAsDate}" pattern="dd MMM yyyy"/></span></p>
							        <p>Order Status:
							            <span class="${order.orderStatus == 2 ? 'status-completed' : 'status-current'}">
							                ${order.statusText}
							            </span>
							        </p>
							    </div>
							    <c:if test="${order.feedbackId == 0}">
							        <a href="${pageContext.request.contextPath}/profile/contact?orderId=${order.orderId}">
							            <button class="rate-btn">Rate Order</button>
							        </a>
							    </c:if>
							    <c:if test="${order.feedbackId != 0}">
							        <button class="rate-btn-done" disabled>Reviewed</button>
							    </c:if>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p class="no-orders">No records for past orders found.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		
		<!-- Pagination -->
        <div class="pagination">
            <span class="page-btn">Previous</span>
            <span class="pager-current">1</span>
            <span class="pager-btn">Next</span>
        </div>
	</div>
	</div>
	</section>
	</div>
	
	<%@ include file="../../common/footer.jsp" %>
</body>
</html>
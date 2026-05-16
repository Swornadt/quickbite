<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Management</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-management.css" />
</head>
<body>

	<%@ include file="/WEB-INF/views/staff/kitchen-nav.jsp" %>
	
	<div class="orderStatus">
		<h2 class="title">Pending Orders</h2>
		<div class="cards">
			<c:forEach var="order" items="${pending}">
				<a href="${pageContext.request.contextPath}/kitchen/${order.orderId}">
					<div class="card">Order ID: ${order.orderId}</div>
				</a>
			</c:forEach>
		</div>
	</div> 
	<div class="orderStatus">
		<h2 class="title">Ongoing Orders</h2>
		<div class="cards">
			<c:forEach var="order" items="${ongoing}">
				<a href="${pageContext.request.contextPath}/kitchen/${order.orderId}">
					<div class="card">Order ID: ${order.orderId}</div>
				</a>
			</c:forEach>
		</div>
	</div> 
	<div class="orderStatus">
		<h2 class="title">Completed Orders</h2>
		<div class="cards">
			<c:forEach var="order" items="${complete}">
				<a href="${pageContext.request.contextPath}/kitchen/${order.orderId}">
					<div class="card">Order ID: ${order.orderId}</div>
				</a>
			</c:forEach>
		</div>
	</div> 
</body>
</html>
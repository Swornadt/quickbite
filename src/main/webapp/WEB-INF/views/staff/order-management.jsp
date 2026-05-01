<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Management</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-management.css" />
</head>
<body>
<div class="orderStatus">
	<h2 class="title">Pending Orders</h2>
	<div class="cards">
		<c:forEach var="order" items="${pending}">
			<div class="card">Order ID: ${order.orderId}</div>
		</c:forEach>
	</div>
</div> 
<div class="orderStatus">
	<h2 class="title">Ongoing Orders</h2>
	<c:forEach var="order" items="${ongoing}">
			<div class="card">Order ID: ${order.orderId}}</div>
	</c:forEach>
</div> 
<div class="orderStatus">
	<h2 class="title">Completed Orders</h2>
	<c:forEach var="order" items="${complete}">
			<div class="card">Order ID: ${order.orderId}}</div>
	</c:forEach>
</div> 
</body>
</html>
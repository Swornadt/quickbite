<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customerProfile.css" />
</head>
<body>

<div class="admin-body">

	<!-- Side Navigation Bar -->
	<div class="admin-sidenav">
		<%@ include file="../common/side-nav.jsp" %>
	</div>

	<!-- Top Navigation Bar -->
	<div class="customer-profile-side">
        <%@ include file='../common/admin-nav.jsp' %>
        
        <!-- Profile Picture and Name with Role -->
        <div class="customer-brief" style="margin: 20px 0;">
            <div class="customer-image">
            	<!-- Checks if image exists. If not, displays a placeholder image -->
                <img src="${pageContext.request.contextPath}/${not empty user.image ? customerData.image : 'uploads/default.png'}" alt="Profile Picture">            
            </div>
            <div class="customer-brief-detail">
                <h4>${customerData.fullName}</h4>
                <p>${customerData.role}</p>
            </div>
        </div>

		<!-- Grid Layout for Customer Details -->
        <div class="customer-detail-grid" id="customerDetailGrid">
            <div class="customer-credentials-grid" id="customerCredentialsGrid">
                <div class="user-id">
                    <h4>User ID</h4>
                    <p>${customerData.userId}</p>
                </div>
                <div class="customer-first-name">
                    <h4>First Name</h4>
                    <p>${customerData.fname}</p>
                </div>
                <div class="customer-last-name">
                    <h4>Last Name</h4>
                    <p>${customerData.lname}</p>
                </div>

                <div class="customer-dob">
                    <h4>DOB</h4>
                    <p>
                        ${customerData.dob}
                    </p>
                </div>
                <div class="customer-gender">
                    <h4>Gender</h4>
                    <p>${customerData.gender}</p>
                </div>
                <div class="customer-role">
                    <h4>Role</h4>
                    <p>${customerData.role}</p>
                </div>
            </div>

            <div class="customer-contact-details">
                <div class="customer-email">
                    <h4>Email Address</h4>
                    <p>
                        ${customerData.email}
                    </p>
                </div>
                <div class="customer-contact1">
                    <h4>Primary Contact</h4>
                    <p>${customerData.number}</p>
                </div>
            </div>
        </div>
        
        <!-- Order History -->
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
						<p class="no-orders">No records for past orders found.</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
        
        
    </div>
</div>
</body>
</html>
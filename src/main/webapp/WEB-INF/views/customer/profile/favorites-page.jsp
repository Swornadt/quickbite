<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Favorites | QuickBite</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-history.css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/userProfile.css" />
</head>

<body>
	<%@ include file="/WEB-INF/views/common/navbar1.jsp" %>
	
    <%@ include file="/WEB-INF/views/common/utilityBar.jsp" %>
    
	<div class="user-profile-section-container">
		<section class="user-profile-section">
		
		<%@ include file="/WEB-INF/views/common/userProfileSideNav.jsp" %>
		
			<div class="user-profile-container">
		
				<div class="history-container">
					
					<!-- Favorite Section -->
					<h2 class="section-title">FAVOURITE FOODS</h2>
					<div class="divider"></div>
					
					<!-- Scroll Section -->
					<div class="order-list-scroll">
						<c:choose>
							<c:when test="${not empty currentOrders}">
								<c:forEach var="order" items="${currentOrders}">
									<div class="divider"></div>
									<div class="order-card">
										<p> <span> ${item.name} </span></p>
										<p> From: <span>{outlet.name}</span></p>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<p class="no-orders">
									You do not have any favorite food added yet.
									Start adding now by clicking <a href="${pageContext.request.contextPath}/outlets">here.</a> 
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<%@ include file="../../common/footer.jsp" %>
	
</body>
</html>
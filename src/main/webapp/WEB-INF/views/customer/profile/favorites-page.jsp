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
							<c:when test="${not empty favoriteList}">
								<c:forEach var="fav" items="${favoriteList}">
									<div class="order-card">
										<p> <span> ${fav.item.itemName} </span></p>
										<p> From: <span>${fav.outlet.outletName}</span></p>
										
										<form action="${pageContext.request.contextPath}/profile/favorites/remove" method="POST" style="display:inline;">
						                    <input type="hidden" name="itemId" value="${fav.item.itemId}">
						                    <input type="hidden" name="outletId" value="${fav.outlet.outletId}">
						                    <button type="submit" class="remove-btn">Remove</button>
						                </form>
						                <div class="divider"></div>
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
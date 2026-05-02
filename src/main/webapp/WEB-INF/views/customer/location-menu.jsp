<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location Menu</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/location-menu.css">
</head>
<body>

<!-- Header -->
<%@ include file="../common/navbar1.jsp" %>
	
<div class="main-container">
        <div class="canteen-name">
            <h1>${outlet.outletName}</h1>
        </div>
<div class="main-category-container">
            <div class="category-container">
                <button class="category-btn active" data-category="all">All</button>
                <%
                	List <String> categories = (List<String>) request.getAttribute("categories");
               
                if(categories !=null){
                	for(String cat: categories){
                		%>
                		<button class="category-btn" data-category="<%= cat.toLowerCase()%>"> <%= cat %>
                		</button>
                <%
                	}
                }
                %>
            </div>
            <div class="search-bar">
                <input type="text" id="searchBar" placeholder="Search your cravings!">
            </div>
        </div>
<div class="cards" id="cardsDiv">
    <c:forEach var="item" items="${outletItems}">
        <div class="card" data-category="${item.item.category}">
            <div class="card-image">
                <img src="${item.item.itemImage}" alt="${item.item.itemName}">
            </div>
            
            <div class="card-details">
                <div class="item-name">
                    <h2>${item.item.itemName}</h2>
                </div>
                <div class="item-price">
                	<h4>Rs. ${item.outletItemPrice}</h4>
                </div>

                <div class="item-ingredients">
                    <div class="ingredients-title">
                        <p>Ingredients</p>
                    </div>
                    <div class="ingredients">
                        <ul>
                            <li>${item.item.itemIngredient}</li>
                        </ul>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/cart/add" method="POST">
	                <input type="hidden" name="itemId" value="${item.item.itemId}">
		            <input type="hidden" name="itemName" value="${item.item.itemName}">
		            <input type="hidden" name="unitPrice" value="${item.outletItemPrice}">
		            <input type="hidden" name="outletId" value="${outlet.outletId}">
		            <input type="hidden" name="outletName" value="${outlet.outletName}">
		             <input type="hidden" name="quantity" value="1">
				    <input type="hidden" name="itemId" value="${item.item.itemId}">
				    <input type="hidden" name="outletName" value="${outlet.outletName}">
				    
				    <button type="submit" class="Add-to-Cart">
				    <span class="btn-text">Add to Cart</span>
				    </button>
				</form>
			</div>
        </div>
    </c:forEach>
	</div>
</div>

<!-- Footer -->
<%@ include file="../common/footer.jsp" %>
</body>

</html>
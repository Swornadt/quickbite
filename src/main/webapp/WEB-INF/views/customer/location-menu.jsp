<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location Menu</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/location-menu.css">
</head>
<body>
<div class="cards" id="cardsDiv">
    <c:forEach var="item" items="${outletitems}">
        <div class="card" data-category="${item.item.category}">
            <div class="card-image">
                <img src="${item.item.itemImage}" alt="${item.item.itemName}">
            </div>
            
            <div class="card-details">
                <div class="item-name">
                    <h2>${item.item.itemName}</h2>
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
		            <input type="hidden" name="outletId" value="${outlet}"> <input type="hidden" name="quantity" value="1">
				    <input type="hidden" name="itemId" value="${item.item.itemId}">
				    
				    <button type="submit" class="Add-to-Cart">
				    <span class="btn-text">Add to Cart</span>
				    </button>
				</form>
			</div>
        </div>
    </c:forEach>
</div>
</body>

</html>
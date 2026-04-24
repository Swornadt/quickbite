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
<p>Number of items: ${fn:length(itemList)}</p>
<div class="cards" id="cardsDiv">
    <c:forEach var="item" items="${outletitems}">
        <div class="card" data-category="${item.category}">
            <div class="card-image">
                <img src="${item.itemImage}" alt="${item.itemName}">
            </div>
            
            <div class="card-details">
                <div class="item-name">
                    <h2>${item.itemName}</h2>
                </div>

                <div class="item-ingredients">
                    <div class="ingredients-title">
                        <p>Ingredients</p>
                    </div>
                    <div class="ingredients">
                        <ul>
                            <li>${item.itemIngredient}</li>
                        </ul>
                    </div>
                </div>

                <button class="Add-to-Cart">
                    <span class="circle"></span>
                    <span class="btn-text">Add to Cart</span>
                </button>
            </div>
        </div>
    </c:forEach>
</div>
</body>

</html>
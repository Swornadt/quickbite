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
<%@ include file="../common/navbar1.jsp" %>
<div class="main-container">
        <div class="canteen-name">
            <h1>Main Canteen</h1>
        </div>
<div class="main-category-container">
            <div class="category-container">
                <button class="category-btn active" data-category="all">All</button>
                <button class="category-btn" data-category="breakfast">Breakfast</button>
                <button class="category-btn" data-category="snacks">Snacks</button>
            </div>
            <div class="search-bar">
                <input type="text" id="searchBar" placeholder="Search your cravings!">
            </div>
        </div>
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

                <button class="Add-to-Cart">
                    <span class="circle"></span>
                    <span class="btn-text">Add to Cart</span>
                </button>
            </div>
        </div>
    </c:forEach>
</div>
</div>
</body>

</html>
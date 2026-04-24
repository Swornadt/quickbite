<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<section class="popular-items-section">
    <div class="popular-title">
            <h2>Popular Among Students</h2>
            <p>Most ordered & highly rated items on campus right now</p>
        </div>
        <div class="popular-items-container">
            <div class="cards-grid" id="cardsDiv">
                <c:forEach var="item" items="${itemList}">
                    <div class="card" data-category="${item.category}">
                        
                        <div class="card-image">
                            <img src="${item.itemImage}" alt="${item.itemName}">
                        </div>
                
                        <div class="card-details">
                            <div class="item-name">
                                <h2>${item.itemName}</h2>
                            </div>
                
                            <div class="item-location">

                            </div>
                
                            <div class="item-ingredients">
                                <div class="ingredients-title">
                                    <h4>Ingredients</h4>
                                </div>
                                <div class="ingredients">
                                    <p>${item.itemIngredient}</p>
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
</section>






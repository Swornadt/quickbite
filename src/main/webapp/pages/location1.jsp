<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location </title>
<link rel="stylesheet" href="../css/location1.css">
<link rel="stylesheet" href="../css/location-menu.css">
</head>
<body>
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

        <div class="card-container">
            <div class="cards" id="cardsDiv">
            </div>
        </div>

    </div>
</body>
<script src="../js/cards.js"></script>
<script src="../js/location1.js"></script>

</html>
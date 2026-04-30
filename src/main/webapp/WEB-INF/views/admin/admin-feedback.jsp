<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Feedback | Quickbite</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-feedback.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-main-dashboard.css" />
</head>
<body>
<div class="admin-body">
    <%@ include file="../common/side-nav.jsp" %>
    
    <div class="admin-right-body">
    <div class="container">
        <h1>Feedbacks</h1>

        <div class="header">
            <button class="filter-btn">Filter</button>
            <div class="date-range">All Feedbacks</div>
        </div>

        <!-- Stats Section -->
        <div class="stats">
            <div class="stat-card">
                <div class="stat-title">Total Feedback</div>
                <div class="total-feedback">${totalFeedback}</div>
                <div style="color:#10b981; font-size:14px; margin-top:8px;">
                    Total feedbacks received
                </div>
            </div>

            <div class="stat-card">
                <div class="stat-title">Average Rating</div>
                <div style="display:flex; align-items:baseline; gap:12px;">
                    <span class="avg-rating">${avgRating}</span>
                    <span class="stars">
                        ★★★★☆
                    </span>
                </div>
                <div style="color:#6b7280; font-size:14px; margin-top:8px;">
                    Average rating this period
                </div>
            </div>
            
            <!-- Rating Distribution -->
            <div class="stat-card">
                <h4 style="margin-bottom: 15px; color:#374151;">Rating Distribution</h4>
                
                <c:forEach var="star" begin="1" end="5">
                	<c:set var="star" value="${6 - star }" />
                    <c:set var="count" value="${ratingCount[star]}" />
                    <c:set var="percentage" value="${totalFeedback > 0 ? (count / totalFeedback * 100) : 0}" />
                    
                    <div class="rating-bar">
                        <span>${star}</span>
                        <div class="bar-container">
                            <div class="bar" style="width: ${percentage}%;"></div>
                        </div>
                        <span>
                            <fmt:formatNumber value="${count}" pattern="#,###"/> 
                            <c:if test="${count >= 1000}"></c:if>
                        </span>
                    </div>
                </c:forEach>
            </div>
    </div>
        </div>

        <!-- Dynamic Feedback Cards -->
        <c:forEach var="fb" items="${feedbackList}">
            <div class="feedback-card">
                <img src="https://via.placeholder.com/85" 
                     alt="User" class="food-image"/>
                
                <div class="feedback-content">
                    <div class="feedback-header">
                        <div>
                            <h3 style="font-size:19px; font-weight:600;">
                                ${fb.userFullName}
                            </h3>
                            <p class="location">Customer</p>
                        </div>
                        <div class="rating-date">
                            <!-- Display stars dynamically -->
                            <c:forEach begin="1" end="5" var="i">
                                <span style="color: ${i <= fb.ratingValue ? '#f59e0b' : '#e5e7eb'};">
                                    ★
                                </span>
                            </c:forEach>
                            <span class="date">
                                <fmt:formatDate value="${fb.ratingDate}" pattern="dd-MM-yyyy" />
                            </span>
                        </div>
                    </div>
                    
                    <div class="feedback-text">
                        <p>${fb.feedbackDescription}</p>
                    </div>
                </div>
            </div>
        </c:forEach>

        <!-- Show message if no feedback -->
        <c:if test="${empty feedbackList}">
            <div style="text-align: center; padding: 60px 20px; color: #6b7280; font-size: 18px;">
                No feedbacks available yet.
            </div>
        </c:if>

    </div>
    </div>
</div>
</body>
</html>
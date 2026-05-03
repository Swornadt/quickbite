<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us | QuickBite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
</head>

<body>

    <!-- Header -->
    <%@ include file="../common/navbar1.jsp" %>

    <div class="hero-image">
        <div class="hero-title">Contact Us</div>
    </div>
    
    <!-- Success / Error Messages -->
        <% if (request.getAttribute("success") != null) { %>
            <div style="color:green; text-align:center; margin:25px 0; font-weight:bold; font-size:1.1em;">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        <% if (request.getAttribute("error") != null) { %>
            <div style="color:red; text-align:center; margin:25px 0; font-weight:bold; font-size:1.1em;">
                <%= request.getAttribute("error") %>
            </div>
        <% } %>

    <div class="contact-container">

        <div class="info">
            <h2>Islington College</h2>
            <p>Kamal Pokhari, Kathmandu, Nepal</p>
            <p>Phone: +977 - 4433221</p>
        </div>

        <!-- Login Check -->
        <%
            boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        %>

        <% if (isLoggedIn) { %>
            <!-- Logged-in Feedback Form -->
            <form action="${pageContext.request.contextPath}/contact" method="post" class="feedback-form">
                <h2>Send us your feedback</h2>

                <div class="rating-row">
                    <label for="rating">Rating:</label>
                    <div class="star-rating">
                        <input type="radio" id="star5" name="rating" value="5" required>
                        <label for="star5">★</label>
                        <input type="radio" id="star4" name="rating" value="4">
                        <label for="star4">★</label>
                        <input type="radio" id="star3" name="rating" value="3">
                        <label for="star3">★</label>
                        <input type="radio" id="star2" name="rating" value="2">
                        <label for="star2">★</label>
                        <input type="radio" id="star1" name="rating" value="1">
                        <label for="star1">★</label>
                    </div>
                </div>

                <label for="message">Your Message</label>
                <textarea id="message" name="message" placeholder="Write your feedback here..." rows="5" required></textarea>

                <button type="submit">Submit Feedback</button>
            </form>
        <% } else { %>
            <!-- Non-logged-in version -->
            <div class="feedback-form" style="text-align:center; padding:40px; background:#f9f9f9; border-radius:8px;">
                <h2>Send us your feedback</h2>
                <p style="color:#666; margin:20px 0; font-size:1.1em;">
                    You need to be <strong>logged in</strong> to submit feedback.
                </p>
                <a href="${pageContext.request.contextPath}/login" 
                   class="login-btn">Login to Submit Feedback</a>
            </div>
        <% } %>

    </div>

    <div class="map">
        <img src="${pageContext.request.contextPath}/assets/Location.jpg" alt="Islington College Location">
    </div>

    <!-- Footer -->
    <%@ include file="../common/footer.jsp" %>

</body>
</html>
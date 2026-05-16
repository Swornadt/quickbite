<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us | QuickBite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/feedback.css">
</head>

<body>

    <!-- Header -->
    <%@ include file="../common/navbar1.jsp" %>

    <div class="hero-image">
        <div class="hero-title">Send Us Your Feedback</div>
    </div>
    
    <!-- Success / Error Messages -->
        <c:if test="${not empty success}">
        <div style="color:green; text-align:center; margin:25px 0; font-weight:bold; font-size:1.1em;">
            ${success}
        </div>
    </c:if>
    
    <c:if test="${not empty error}">
        <div style="color:red; text-align:center; margin:25px 0; font-weight:bold; font-size:1.1em;">
            ${error}
        </div>
    </c:if>

    <div class="feedback-container">

            <form action="${pageContext.request.contextPath}/profile/feedback" method="post" class="feedback-form">
            <input type="hidden" name="orderId" value="${param.orderId}"/>
                <h2>Feedback Form</h2>

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
        

    </div>

    <!-- Footer -->
    <%@ include file="../common/footer.jsp" %>

</body>
</html>
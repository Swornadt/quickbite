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

    <div class="contact-container">

        <div class="info">
            <h2>Islington College</h2>
            <p>Kamal Pokhari, Kathmandu, Nepal</p>
            <p>Phone: +977 - 4433221</p>
        </div>
        

    </div>

    <div class="map">
        <img src="${pageContext.request.contextPath}/assets/Location.jpg" alt="Islington College Location">
    </div>

    <!-- Footer -->
    <%@ include file="../common/footer.jsp" %>

</body>
</html>
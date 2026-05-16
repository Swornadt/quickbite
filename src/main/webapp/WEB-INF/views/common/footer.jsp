<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Footer | Quick Bite</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />

    <!-- Google Fonts Preconnect and Link -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <footer>
      <div class="footer-container">
        <div class="footer-top">
          <!-- QuickBite Logo  -->
          <div class="footer-logo-container">
            <p class="footer-logo">QuickBite</p>
          </div>

          <!-- Quick Links Section -->
          <div class="footer-info-link">
            <ul class="footer-links">
              <li class="footer-header">Quick Links</li>
              <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets">Menu</a></li>
              <li><a href="${pageContext.request.contextPath}/about">About Us</a></li>
              <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
            </ul>
            <ul class="footer-links">
              <li class="footer-header">Location</li>
              <li><a href="${pageContext.request.contextPath}/outlets/Coffee%20Station">Coffee Station</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets/Canteen">Canteen</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets/Momo%20Station">Momo Station</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets/Brit%20Cafe">Brit Cafe</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets/Chautari">Chautari</a></li>
              <li><a href="${pageContext.request.contextPath}/outlets/Kumari">Kumari Cafe</a></li>
            </ul>
          </div>
        </div>

        <!-- Copyright section -->
        <div class="footer-bottom">
          <p>
            Copyright &copy; 2026 QuickBite. All rights reserved. Accessibility,
            User Agreement, Privacy, Consumer Health Data, Payment Terms of Use
            Cookies, CA Privacy Notice
          </p>
        </div>
      </div>
    </footer>
  </body>
</html>

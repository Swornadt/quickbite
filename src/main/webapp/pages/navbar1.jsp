<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Navbar | Quick Bite</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>>/css/navbar1.css" />

    <!-- Google Fonts Preconnect and Link -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <nav>
      <div class="nav-container">
        <!-- Navbar left side (logo and hamburger menu)  -->
        <div class="nav-left">
          <div class="hamburger-container">
            <button class="hamburger">
              <span class="hamburger-line" id="hamburger-first-line"></span>
              <span class="hamburger-line" id="hamburger-second-line"></span>
              <span class="hamburger-line" id="hamburger-third-line"></span>
            </button>
          </div>

          <a href="#" class="nav-logo-container">
            <span class="logo-text">Quick</span>
            <span class="logo-text">Bite</span>
          </a>
        </div>

        <!-- Center nav links  -->
        <div class="nav-links">
          <a href="#" class="nav-link">Home</a>
          <a href="#" class="nav-link">Location</a>
          <a href="#" class="nav-link">About Us</a> 
          <a href="#" class="nav-link">Contact</a>
        </div>

        <!-- Navbar login button  -->
        <a href="#" class="nav-login-btn-container">
          <button class="nav-login-btn">Login</button>
        </a>
      </div>

      <!-- For Mobile Responsiveness  -->
      <div class="nav-mobile-menu">
        <a href="#" class="mobile-nav-link">Home</a>
        <a href="#" class="mobile-nav-link">Location</a>
        <a href="#" class="mobile-nav-link">About Us</a>
        <a href="#" class="mobile-nav-link">Contact</a>
      </div>
    </nav>
    <div class="below-nav"></div>

    <script src="<%=request.getContextPath() %>/js/navbar.js"></script>
  </body>
</html>

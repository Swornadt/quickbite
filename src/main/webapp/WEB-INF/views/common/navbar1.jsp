<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<%
	HttpSession userSession = request.getSession(false);
	String currentUser = (String) (userSession != null ? userSession.getAttribute("number") : null);
	String contextPath = request.getContextPath();
	
	String actionUrl;
	String formMethod;
	String buttonLabel;
	
	if(currentUser != null) {
		actionUrl = contextPath + "/logout";
		formMethod = "post";
		buttonLabel = "Logout";
	} else {
		actionUrl = contextPath + "/login";
		formMethod = "get";
		buttonLabel = "Login";
	}
%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Navbar | Quick Bite</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/navbar1.css" />

    <!-- Google Fonts Preconnect and Link -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
    
     <!-- Font awesome cdn for icons -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
      integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    <nav>
    
    <!--If user isnot  logged in -->
    <c:if test= "${empty sessionScope.number}">
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
        <div class="nav-login-btn-container">
        	<form action="<%= actionUrl %>" method="<%= formMethod %>">
        		<input class="nav-login-btn" type="submit" value="<%= buttonLabel %>"/>
        	</form>
        </div>
      </div>

      <!-- For Mobile Responsiveness  -->
      <div class="nav-mobile-menu">
        <a href="#" class="mobile-nav-link">Home</a>
        <a href="#" class="mobile-nav-link">Location</a>
        <a href="#" class="mobile-nav-link">About Us</a>
        <a href="#" class="mobile-nav-link">Contact</a>
      </div>
    </c:if>
    
    <!-- If user is logged in -->
    <c:if test="${not empty sessionScope.number }">
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

        <!-- Navbar right side  -->
        <div class="nav-right">
          <div class="loggedin-nav-links">
            <a href="#" class="nav-link">Menu</a>
            <a href="#" class="nav-link">Contact Us</a>
          </div>

          <div class="nav-symbols">
            <i class="fa-solid fa-bell" id="nav-bell"></i>
            <i class="fa-solid fa-cart-shopping" id="nav-cart"></i>
          </div>

          <div class="nav-image-container">
            <img
              src="<%=request.getContextPath() %>/assets/user-image.jpg"
              alt="Profile Image"
              class="nav-profile-image"
            />
          </div>
        </div>
      </div>

      <!-- For Mobile Responsiveness  -->
      <div class="nav-mobile-menu">
        <a href="#" class="mobile-nav-link">Menu</a>
        <a href="#" class="mobile-nav-link">Contact Us</a>
      </div>
    </c:if>
    </nav>
    
    <div class="below-nav"></div>
	
    <script src="<%=request.getContextPath() %>/js/navbar.js"></script>
  </body>
</html>

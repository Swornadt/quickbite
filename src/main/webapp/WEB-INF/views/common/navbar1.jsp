<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="com.quickbite.model.UserModel" %>

<%
	HttpSession userSession = request.getSession(false);
	UserModel currentUserObj = (userSession != null) ? (UserModel) userSession.getAttribute("user") : null;
	
	String currentUser = (currentUserObj != null) ? currentUserObj.getNumber() : null;
	String contextPath = request.getContextPath();
	
	String actionUrl;
	String formMethod;
	String buttonLabel;
	
	if(currentUser != null) {
		actionUrl = contextPath + "/logout";
		formMethod = "  post";
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar1.css" />

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
    
    <!-- If user is logged in -->
    <c:if test="${not empty sessionScope.user }">
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

          <a href="<%=request.getContextPath()%>/home" class="nav-logo-container">
            <span class="logo-text">Quick</span>
            <span class="logo-text">Bite</span>
          </a>
        </div>

        <!-- Navbar right side  -->
        <div class="nav-right">
          <div class="loggedin-nav-links">
            <a href="<%=request.getContextPath()%>/outlets" class="nav-link">Location</a>
            <a href="<%=request.getContextPath()%>/contact" class="nav-link">Contact Us</a>
          </div>

          <div class="nav-symbols">
            <i class="fa-solid fa-bell" id="nav-bell"></i>
            <a href="${pageContext.request.contextPath}/cart" class="fa-solid fa-cart-shopping" id="nav-cart"></a>
          </div>

          <div class="nav-image-container" id="profileTrigger">
            <img
              src="${pageContext.request.contextPath}/${user.image}"
              alt="Profile Image"
              class="nav-profile-image"
              style = "cursor: pointer;"
            />
          </div>
        </div>
      </div>
      
      <!-- Profile Popup -->
<div id="profilePopup" class="profile-popup-overlay" style="display: none;">
    <div class="profile-popup-content">
        <div class="popup-header">
            <button class="close-popup">&times;</button>
        </div>
        
        <!-- Content will be loaded here -->
        <jsp:include page="/WEB-INF/views/customer/profile-pop-up.jsp" />
    </div>
</div>

      <!-- For Mobile Responsiveness  -->
      <div class="nav-mobile-menu">
        <a href="<%=request.getContextPath()%>/outlet" class="mobile-nav-link">Outlet</a>
        <a href="<%=request.getContextPath()%>/contact" class="mobile-nav-link">Contact Us</a>
      </div>
    </c:if>
    
    <!--If user is not  logged in -->
    <c:if test= "${empty sessionScope.user}">
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

          <a href="<%=request.getContextPath()%>/home" class="nav-logo-container">
	            <p class="logo-text">Quick</p>
	            <p class="logo-text">Bite</p>
          </a>
        </div>

        <!-- Center nav links  -->
        <div class="nav-links">
          <a href="<%=request.getContextPath()%>/home" class="nav-link">Home</a>
          <a href="<%=request.getContextPath()%>/outlets" class="nav-link">Location</a>
          <a href="<%=request.getContextPath()%>/about" class="nav-link">About Us</a>
          <a href="${pageContext.request.contextPath}/contact" class="nav-link">Contact</a>
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
        <a href="<%=request.getContextPath()%>/home" class="mobile-nav-link">Home</a>
        <a href="<%=request.getContextPath()%>/outlets" class="mobile-nav-link">Location</a>
        <a href="<%=request.getContextPath()%>/about" class="mobile-nav-link">About Us</a>
        <a href="<%=request.getContextPath()%>/contact" class="mobile-nav-link">Contact</a>
      </div>
    </c:if>

    </nav>
    
    <div class="below-nav"></div>
	
	<!-- Toast Notification  -->
	<div id="cart-toast" class="toast">
	    <div class="toast-content">
	        <i class="fa-solid fa-circle-check"></i>
	        <span>Item added to cart!</span>
	    </div>
	</div>
	
    <script src="${pageContext.request.contextPath}/js/navbar.js"></script>
    <script src="${pageContext.request.contextPath}/js/toast.js"></script>
    
    <script>
document.addEventListener('DOMContentLoaded', function() {
    const trigger = document.getElementById('profileTrigger');
    const popup = document.getElementById('profilePopup');
    const closeBtn = document.querySelector('.close-popup');

    if (trigger && popup) {
        trigger.addEventListener('click', function(e) {
            e.stopPropagation();
            popup.style.display = 'flex';
        });

        closeBtn.addEventListener('click', function() {
            popup.style.display = 'none';
        });

        // Close when clicking outside the popup content
        popup.addEventListener('click', function(e) {
            if (e.target === popup) {
                popup.style.display = 'none';
            }
        });

        // Close on Escape key
        document.addEventListener('keydown', function(e) {
            if (e.key === 'Escape' && popup.style.display === 'flex') {
                popup.style.display = 'none';
            }
        });
    }
});
</script>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login | Quick Bite</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
</head>
<body>
	 <div class="login-page-container">
      <!-- Left Side (Image Container) -->
      <div class="login-page-image-container">
        <img src="${pageContext.request.contextPath}/assets/Login.jpeg" class="login-page-image" />
      </div>

      <!-- Right Side (Form Container) -->
      <div class="login-page-section">
        <div class="signin-container">
          <h1>Sign in to your account</h1>

			<!-- Error / Success Messages -->
          <% if (request.getAttribute("error") != null) { %>
               <p style="color: red; text-align:center; margin:10px 0;"> <%= request.getAttribute("error") %> </p>
          <% } %>
          <% if (request.getAttribute("success") != null) { %>
               <p style="color: green; text-align:center; margin:10px 0;"> <%= request.getAttribute("success") %> </p>
          <% } %>
          <!-- Actual Form  -->
          <form class="signin-section" action="${pageContext.request.contextPath}/login" method="post">
            <!-- Section 1 -->
            <div class="signin-label">
              <label for="number">Phone Number:</label>
              <input
                id="number"
                type="text"
                name="number"
                class="signin-input"
              />
            </div>

            <!-- Section 2 -->
            <div class="signin-label">
              <label for="pass">Password:</label>
              <input
                id="pass"
                type="password"
                name="pass"
                class="signin-input"
              />
            </div>

            <!-- Sign in Button -->
            <button class="signin-btn" type="submit">Sign in</button>
          </form>

          <!-- Other Bottom Info's  -->
          <a href="#" class="forget-pass-link">Forgot your password?</a>
          <p class="site-info">
            This site is protected by reCAPTCHA and the Google
            <span>Privacy Policy</span> and <span>Terms of Service</span> apply
          </p>
          <a href="${pageContext.request.contextPath}/register" class="no-acc-btn"
            >Don't have an account? <span>Sign up</span></a
          >
        </div>
      </div>
    </div>

</body>
</html>
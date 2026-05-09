<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password | Quick Bite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/resetPassword.css" />
</head>
<body>
	 <div class="reset-page-container">
      <!-- Left Side (Image Container) -->
      <div class="reset-page-image-container">
        <img src="<%=request.getContextPath() %>/assets/reset.jpg" class="reset-page-image" />
      </div>

      <!-- Right Side (Form Container) -->
      <div class="reset-page-section">
        <div class="reset-container">
          <h1>Request Password Reset</h1>
			
          <!-- Actual Form  -->
          <form class="reset-section" action="" method="post">
            <!-- Section 1 -->
            <div class="reset-label">
              <label for="number">Phone Number:</label>
              <input
                id="number"
                type="text"
                name="number"
                class="reset-input"
              />
            </div>

            <!-- Section 2 -->
            <div class="reset-label">
              <label for="email">Email</label>
              <input
                id="email"
                type="email"
                name="email"
                class="reset-input"
              />
            </div>
            
            <!-- Section 3 -->
            <div class="reset-label">
              <label for="username">Username</label>
              <input
                id="username"
                type="text"
                name="username"
                class="reset-input"
              />
            </div>

            <!-- Sign in Button -->
            <button class="reset-btn" type="submit">Send Request</button>
          </form>

          <!-- Other Bottom Info's  -->
          
          <a href="<%=request.getContextPath()%>/register" class="no-acc-btn"
            >Don't have an account? <span>Sign up</span></a
          >
        </div>
      </div>
    </div>

</body>
</html>
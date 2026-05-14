<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Register | Quick Bites</title>

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />

</head>
<body>
<div class="register-page-container">
      <!-- Left Side (Image Container) -->
      <div class="register-page-image-container">
        <img src="${pageContext.request.contextPath}/assets/Register.jpeg" class="register-page-image" />
      </div>

      <!-- Right Side (Form Container) -->
      <div class="register-page-section">
      <!-- Error / Success Messages -->
		<% if (request.getAttribute("error") != null) { %>
    		<p style="color: red;" class='register-message'>
        		<%= request.getAttribute("error") %>
    		</p>
		<% } %>
		<% if (request.getAttribute("success") != null) { %>
    		<p style="color: green;"  class='register-message'>
        		<%= request.getAttribute("success") %>
    		</p>
		<% } %>

        <div class="register-container">
          <h1 class="register-heading">Register</h1>
          <!-- Actual Form  -->
          <form
            class="register-section"
            action="<%=request.getContextPath()%>/register"
            method="post" 
            enctype="multipart/form-data"
          >
            <!-- First Name and Last Name -->
            <div class="register-name">
              <div class="register-label">
                <label for="fname">First Name:</label>
                <input
                  id="fname"
                  type="text"
                  name="fname"
                  class="register-input"
                />
              </div>
              <div class="register-label">
                <label for="lname">Last Name:</label>
                <input
                  id="lname"
                  type="text"
                  name="lname"
                  class="register-input"
                />
              </div>
            </div>

            <!-- Phone Number Label  -->
            <div class="register-label">
              <label for="number">Phone Number:</label>
              <input
                id="number"
                type="text"
                name="number"
                class="register-input"
              />
            </div>

            <!-- Email Label  -->
            <div class="register-label">
              <label for="email">Email:</label>
              <input
                id="email"
                type="email"
                name="email"
                class="register-input"
              />
            </div>

            <!-- Gender and DOB -->
            <div class="register-name">
              <div class="register-label">
                <label for="gender">Gender:</label>
                <select name="gender" class="register-input" id="gender">
                  <option value="Male">Male</option>
                  <option value="Female">Female</option>
                  <option value="Female">Others</option>
                </select>
              </div>
              <div class="register-label">
                <label for="dob">DOB:</label>
                <input id="dob" type="date" name="dob" class="register-input" />
              </div>
            </div>

            <!-- New Password -->
            <div class="register-label">
              <label for="newpass">New Password:</label>
              <input
                id="newpass"
                type="password"
                name="newpass"
                class="register-input"
              />
            </div>

            <!-- Confirm Password -->
            <div class="register-label">
              <label for="confirmpass">Confirm Password:</label>
              <input
                id="confirmpass"
                type="password"
                name="confirmpass"
                class="register-input"
              />
            </div>

			<!-- Profile Image -->
            <div class="profile-image">
              <label for="profile-img">Profile Image:</label>
              <input
                id="profile-img"
                type="file"
                name="image"
                class="register-input"
              />
            </div>

            <!-- Terms of Use Checkbox -->
            <div class="register-tou">
              <input type="checkbox" id="terms-of-use" name="terms" />
              <label for="terms-of-use"
                >I have read and agreed to <span>Terms of use</span></label
              >
            </div>

            <button type="submit" class="register-btn">
              Create my account
            </button>
          </form>

          <a href="${pageContext.request.contextPath}/login" class="no-acc-btn"
            >Already have an account? <span>Login</span></a
          >
        </div>
      </div>
    </div>
</body>
</html>
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
<link rel="stylesheet" href="../css/register.css" />
</head>
<body>
<div class="register-page-container">
      <!-- Left Side (Image Container) -->
      <div class="register-page-image-container">
        <img src="../assets/Register.jpeg" class="register-page-image" />
      </div>

      <!-- Right Side (Form Container) -->
      <div class="register-page-section">
        <div class="register-container">
          <h1 class="register-heading">Register</h1>
          <!-- Actual Form  -->
          <form class="register-section">
            <!-- First Name and Last Name -->
            <div class="register-name">
              <div class="register-label">
                <label for="fname">First Name:</label>
                <input
                  id="fname"
                  type="text"
                  placeholder=""
                  class="register-input"
                />
              </div>
              <div class="register-label">
                <label for="lname">Last Name:</label>
                <input
                  id="lname"
                  type="text"
                  text
                  placeholder=""
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
                placeholder=""
                class="register-input"
              />
            </div>

            <!-- Email Label  -->
            <div class="register-label">
              <label for="email">Email:</label>
              <input
                id="email"
                type="email"
                placeholder=""
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
                <input
                  id="dob"
                  type="date"
                  placeholder=""
                  class="register-input"
                />
              </div>
            </div>

            <!-- New Password -->
            <div class="register-label">
              <label for="newpass">New Password:</label>
              <input
                id="newpass"
                type="password"
                placeholder=""
                class="register-input"
              />
            </div>

            <!-- Confirm Password -->
            <div class="register-label">
              <label for="confirmpass">Confirm Password:</label>
              <input
                id="confirmpass"
                type="password"
                placeholder=""
                class="register-input"
              />
            </div>

            <!-- Terms of Use Checkbox -->
            <div class="register-tou">
              <input type="checkbox" id="terms-of-use" />
              <label for="terms-of-use"
                >I have read and agreed to <span>Terms of use</span></label
              >
            </div>

            <a href="#" class="register-btn">Create my account</a>
          </form>

          <a href=" # " class="no-acc-btn"
            >Already have an account? <span>Login</span></a
          >
        </div>
      </div>
    </div>
</body>
</html>
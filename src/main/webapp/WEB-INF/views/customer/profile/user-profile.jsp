<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/userProfile.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
</head>
<body>
<%@ include file="../../common/navbar1.jsp" %>
<%@ include file="../../common/utilityBar.jsp" %>
<div class="user-profile-section-container">
<section class="user-profile-section">
<%@ include file="../../common/userProfileSideNav.jsp" %>
      <div class="user-profile-container">
        <!-- Profile image container -->
        <div class="profile-picture-wrapper">
          <h2>PROFILE PICTURE</h2>

          <div class="profile-picture">
            <img src="${pageContext.request.contextPath}/${user.image}" alt="Profile Picture" />
          </div>
        </div>

        <!-- user details form  -->
        <form class="user-profile-form">
          <!-- first name and last name row -->
          <div class="form-row">
            <div class="input-group">
              <label>FIRST NAME</label>
              <input type="text" placeholder="First Name" />
            </div>

            <div class="input-group">
              <label>LAST NAME</label>
              <input type="text" placeholder="Last Name" />
            </div>
          </div>

          <!-- Phone number and email row -->
          <div class="form-row">
            <div class="input-group">
              <label>PHONE NUMBER</label>
              <input type="text" placeholder="9712345678" />
            </div>

            <div class="input-group">
              <label>EMAIL</label>
              <input type="email" placeholder="example@gmail.com" />
            </div>
          </div>

          <!-- BUTTONS -->
          <div class="profile-btn-container">
            <button type="submit" class="save-btn">SAVE</button>
            <button type="button" class="cancel-btn">CANCEL</button>
          </div>
        </form>
      </div>
    </section>
   </div>
<%@ include file="../../common/footer.jsp" %>
</body>
</html>
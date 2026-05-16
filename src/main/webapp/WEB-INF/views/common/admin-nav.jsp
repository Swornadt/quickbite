<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>QuickBite | Admin Nav</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin-nav.css" />
    <!-- Font Awesome Cdn link  -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
      integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <!-- Google Font Cdn link -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
  </head>
  <body>
    <!-- Top tools  -->
    <div class="admin-top-info-container">
      <div class="admin-top-info">
        <div class="admin-right-tools">
          <div class="notification-section">
            <i class="fa-solid fa-bell" id="bell-icon"></i>
            <i class="fa-solid fa-message" id="message-icon"></i>
          </div>
          <div class="admin-info-container">
            <div class="admin-name-role">
              <p class="admin-name">${user.fname} ${user.lname}</p>
              <p class="admin-role">Admin</p>
            </div>
            <a href="${pageContext.request.contextPath}/admin/profile" class="admin-profile-link">
            	<div class="admin-image">
              		<img src="${pageContext.request.contextPath}/${user.image}" />
            	</div>
            </a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
    

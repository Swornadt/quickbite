<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password | QuickBite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-change-password.css" />
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-main-dashboard.css" />
<link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
</head>
<body>
<div class="admin-body">
	<%@ include file="../common/side-nav.jsp" %>
    
    <div class="admin-right-body">
    
    <%@ include file='../common/adminNav.jsp' %>
    
    <!-- Bottom Body -->
	      <div class="admin-bottom-info">
	      	
	      	<div class="container">
	      	<!-- Back Button -->
	      	<div class="btn-container">
	      		<button class="back-btn">BACK</button>
	      	</div>
	      	
	      	<!-- User Profile -->
	      	<div class="profile">
	      		<div class="profile-img">
	      			<img src="#" alt="User Profile">
	      		</div>
	      		
	      		<div class="user-info">
	      			<h2>Gallile Gallieo</h2>
	      			<p>9867543465</p>
	      		</div>
	      	</div>
	      	
	      	<!-- Change Password Form -->
	      	<div class="form-section">
	      		<div class="input-group">
	      			<label>NEW PASSWORD</label>
	      			<input type="password" placeholder="New Password" />
	      		</div>
	      		
	      		<div class="input-group">
	      			<label>CONFIRM NEW PASSWORD</label>
	      			<input type="password" placeholder="Re-write New Password" />
	      		</div>
	      	</div>
	      	
	      	<!-- Save Button -->
	      	<div class="btn-container">
	      	<button class="save-btn">SAVE</button>
	      	</div>
	      	
	      </div>
	      
	      </div>
	      </div>
	      </div>
</body>
</html>
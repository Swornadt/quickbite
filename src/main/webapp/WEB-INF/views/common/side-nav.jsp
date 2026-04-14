<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Side Nav</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/side-nav.css">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body>
    <div class="side-nav">
        <div class="side-nav-logo">
			<a href="#" class="nav-logo-container">
		    	<p class="logo-text">Quick Bite</p>
		    </a>
        </div>
        <div class="side-nav-content poppins-light">
            <a href="" class="active"><i class="fa-regular fa-chart-bar"></i> Dashboard</a>
            <a href=""><i class="fa-solid fa-user-group"></i> Cutomer Management</a>
            <a href=""><i class="fa-solid fa-utensils"></i> Menu Management</a>
            <a href=""><i class="fa-solid fa-chart-line"></i> Report</a>
            <a href=""><i class="fa-regular fa-calendar-check"></i> View Feedbacks</a>
            <button class="add-new-item">
            	<span class="btn-text"> + Add New Item</span>
            </button>
        </div>
        <div class="side-nav-footer">
			<a href=""><i class="fa-solid fa-gear"></i> Settings</a>
			<a href=""><i class="fa-regular fa-circle-question"></i> Support</a>
			<a href=""><i class="fa-solid fa-arrow-right-from-bracket"></i> Logout</a>
		</div>
    </div>
<script src="<%=request.getContextPath() %>/js/side-nav.js"></script>
</body>
</html>
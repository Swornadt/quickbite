<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Side Nav</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/side-nav.css">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
</head>
<body>

<!-- This stores the current URI to apply active class in the side-nav bar -->
<c:set var="uri" value="${pageContext.request.requestURI}">
</c:set>

    <div class="side-nav">
        <div class="side-nav-logo">
			<a href="#" class="nav-logo-container">
		    	<p class="logo-text"> Quick Bite</p>
		    </a>
        </div>

<!-- Use of ternary operator to add active class for styling -->        
        <div class="side-nav-content poppins-light">
            <a href="${pageContext.request.contextPath}/admin/" class="${uri == ('/QuickBite/WEB-INF/views/admin/admin-main-dashboard.jsp') ? 'active' : '' }"><i class="fa-regular fa-chart-bar"></i> Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/customers" class="${uri == ('/QuickBite/WEB-INF/views/admin/adminCustomerApproval.jsp') ? 'active' : '' }"><i class="fa-solid fa-user-group"></i> Customer Management</a>
            <a href="${pageContext.request.contextPath}/admin/menu" class="${uri == ('/QuickBite/WEB-INF/views/admin/admin-menu-view.jsp') ? 'active' : '' }"><i class="fa-solid fa-utensils"></i> Menu Management</a>
            <a href="${pageContext.request.contextPath}/admin/report" class="${uri == ('/QuickBite/WEB-INF/views/admin/admin-report.jsp') ? 'active' : '' }"><i class="fa-solid fa-chart-line"></i> Report</a>
            <a href="${pageContext.request.contextPath}/admin/feedback" class="${uri == ('/QuickBite/WEB-INF/views/admin/admin-feedback.jsp') ? 'active' : '' }"><i class="fa-regular fa-calendar-check"></i> View Feedbacks</a>
            <a href="${pageContext.request.contextPath}/admin/menu">

            <button class="add-new-item">
            	<span class="btn-text"> + Add New Item</span>
            </button>
            </a>
        </div>
        <div class="side-nav-footer">
			<a href="${pageContext.request.contextPath}/admin/profile"><i class="fa-solid fa-gear"></i> Settings</a>
			<a href=""><i class="fa-regular fa-circle-question"></i> Support</a>
			<a href="${pageContext.request.contextPath}/logout"><i class="fa-solid fa-arrow-right-from-bracket"></i> Logout</a>
		</div>
    </div>
    
<!-- Overlay div used for layout alignment -->
    <div class="under-side-nav">
   	</div>
</body>
</html>
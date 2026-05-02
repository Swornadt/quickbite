<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-profile.css" />
</head>
<body>
<div class="admin-body">
	<div class="admin-sidenav">
<%@ include file="../common/side-nav.jsp" %>

</div>

	<div class="admin-profile-side">
        <div class="admin-brief">
            <div class="admin-image">
                <img src="<%=request.getContextPath()%>/assets/user-image.jpg" alt="">
            </div>
            <div class="admin-brief-detail">
                <h4>${adminData.fullName }</h4>
                <p>${adminData.role }</p>
            </div>
        </div>
        

        <div class="admin-detail-grid" id="adminDetailGrid">
            <div class="admin-credentials-grid" id="adminCredentialsGrid">
                <div class="admin-id">
                    <h4>Admin ID</h4>
                    <p>${adminData.userId}</p>
                </div>
                <div class="admin-first-name">
                    <h4>First Name</h4>
                    <p>${adminData.fname}</p>
                </div>
                <div class="admin-last-name">
                    <h4>Last Name</h4>
                    <p>${adminData.lname}</p>
                </div>
                
                <div class="admin-dob">
                    <h4>DOB</h4>
                    <p>
						${adminData.dob}
                    </p>
                </div>
                <div class="admin-gender">
                    <h4>Gender</h4>
                    <p>${adminData.gender}</p>
                </div>
                <div class="admin-role">
                    <h4>Role</h4>
                    <p>${adminData.role}</p>
                </div>
            </div>

            <div class="admin-contact-details">
                 <div class="admin-email">
                    <h4>Email Address</h4>
                    <p>
                        ${adminData.email}
                    </p>
                </div>
                <div class="admin-contact1">
                    <h4>Primary Contact</h4>
                    <p>${adminData.number}</p>
                </div>
            </div>

            <div class="admin-dates">
                <div class="admin-creation-date">
                    <h4>User Creation Date</h4>
                    <p>10/01/2025</p>
                </div>
                <div class="last-updated-date">
                    <h4>Last Updated Date</h4>
                    <p>10/02/2026</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
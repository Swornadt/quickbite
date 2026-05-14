<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-profile.css" />
</head>
<body>
    <div class="admin-body">
    	<!-- Side Navigation Bar -->
        <div class="admin-sidenav">
            <%@ include file="../common/side-nav.jsp" %>

        </div>

		<!-- Top Navigation Bar -->
        <div class="admin-profile-side">
        <%@ include file='../common/adminNav.jsp' %>
            <div class="admin-brief" style="margin: 20px 0;">
                <div class="admin-image">
                	<!-- Retrieves admin's profile image -->
                    <img src="${pageContext.request.contextPath}/${user.image}" alt="">
                </div>
                <div class="admin-brief-detail">
                    <h4>${userData.fullName }</h4>
                    <p>${userData.role }</p>
                </div>
            </div>


            <div class="admin-detail-grid" id="adminDetailGrid">
            	<!-- Form submits the data to the same servlet path but as a POST request-->
                <form action="${pageContext.request.contextPath}/admin/profile" method="post">

                    <div class="admin-credentials-grid" id="adminCredentialsGrid">
                        <div class="user-id">
                            <h4>Admin ID</h4>
                            <p>${userData.userId}</p>
                            <input type="hidden" name="user_id" value="${userData.userId}">
                        </div>
                        <div class="admin-first-name">
                            <h4>First Name</h4>
                            <input type="text" name="fname" value="${userData.fname}">
                        </div>
                        <div class="admin-last-name">
                            <h4>Last Name</h4>
                            <input type="text" name="lname" value="${userData.lname}">
                        </div>

                        <div class="admin-dob">
                            <h4>DOB</h4>
                            <input id="dob" type="date" name="dob" class="register-input" value="${userData.dob}">
                        </div>
                        <div class="admin-gender">
                            <h4>Gender</h4>
                            	<select name="gender" id="gender">
								    <option value="Male" ${userData.gender == 'Male' ? 'selected' : ''}>Male</option>
								    <option value="Female" ${userData.gender == 'Female' ? 'selected' : ''}>Female</option>
								    <option value="Others" ${userData.gender == 'Others' ? 'selected' : ''}>Others</option>
								</select>
                        </div>
                        <div class="admin-role">
                            <h4>Role</h4>
                            <p>${userData.role }</p>
                        </div>
                    </div>

                    <div class="admin-contact-details">
                        <div class="admin-email">
                            <h4>Email Address</h4>
                            <input type="text" name="email" value="${userData.email}">
                        </div>
                        <div class="admin-contact1">
                            <h4>Primary Contact</h4>
                            <input type="text" name="number" value="${userData.number}">
                        </div>
                    </div>
                    <button type="submit" class="save-btn"> Update Details </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
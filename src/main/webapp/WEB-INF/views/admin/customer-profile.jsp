<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Profile</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/customerProfile.css" />
</head>
<body>

<div class="admin-body">
	<div class="admin-sidenav">
		<%@ include file="../common/side-nav.jsp" %>
	</div>

	<div class="customer-profile-side">
        <%@ include file='../common/adminNav.jsp' %>
        <div class="customer-brief" style="margin: 20px 0;">
            <div class="customer-image">
                <img src="${empty customerData.image ? pageContext.request.contextPath.concat('/assets/user-image.jpg') : customerData.image}" alt="Profile Picture">
            </div>
            <div class="customer-brief-detail">
                <h4>${customerData.fullName}</h4>
                <p>${customerData.role}</p>
            </div>
        </div>

        <div class="customer-detail-grid" id="customerDetailGrid">
            <div class="customer-credentials-grid" id="customerCredentialsGrid">
                <div class="user-id">
                    <h4>User ID</h4>
                    <p>${customerData.userId}</p>
                </div>
                <div class="customer-first-name">
                    <h4>First Name</h4>
                    <p>${customerData.fname}</p>
                </div>
                <div class="customer-last-name">
                    <h4>Last Name</h4>
                    <p>${customerData.lname}</p>
                </div>

                <div class="customer-dob">
                    <h4>DOB</h4>
                    <p>
                        ${customerData.dob}
                    </p>
                </div>
                <div class="customer-gender">
                    <h4>Gender</h4>
                    <p>${customerData.gender}</p>
                </div>
                <div class="customer-role">
                    <h4>Role</h4>
                    <p>${customerData.role}</p>
                </div>
            </div>

            <div class="customer-contact-details">
                <div class="customer-email">
                    <h4>Email Address</h4>
                    <p>
                        ${customerData.email}
                    </p>
                </div>
                <div class="customer-contact1">
                    <h4>Primary Contact</h4>
                    <p>${customerData.number}</p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
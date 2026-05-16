<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password | QuickBite</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-change-password.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-main-dashboard.css" />
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"/>
</head>
<body>
<div class="admin-body">
    <%@ include file="../common/side-nav.jsp" %>

    <div class="admin-right-body">
        <%@ include file='../common/admin-nav.jsp' %>

        <div class="admin-bottom-info">
            <div class="container">

                <!-- Back Button -->
                <div class="btn-container">
                    <a class="back-btn" href="${pageContext.request.contextPath}/admin/customers">&larr; BACK </a>
                </div>

                <!-- User Profile -->
                <div class="profile">
                    <div class="profile-img">
                        <img src="${pageContext.request.contextPath}/${not empty userData.image ? userData.image : 'uploads/default.png'}"
                             alt="User Profile">
                    </div>
                    <div class="user-info">
                        <h2>${userData.fname} ${userData.lname}</h2>
                        <p>${userData.number}</p>
                    </div>
                </div>

                <!-- Error Message -->
                <c:if test="${not empty errorMessage}">
                    <div class="error-msg">${errorMessage}</div>
                </c:if>

                <!-- Change Password Form -->
                <form method="post" action="${pageContext.request.contextPath}/admin/customers/savePassword">
                    <!-- Pass userId as hidden field so handleSavePassword() knows who to update -->
                    <input type="hidden" name="user_id" value="${userData.userId}" />

                    <div class="form-section">
                        <div class="input-group">
                            <label>NEW PASSWORD</label>
                            <input type="password" name="newPassword" placeholder="New Password" />
                        </div>

                        <div class="input-group">
                            <label>CONFIRM NEW PASSWORD</label>
                            <input type="password" name="confirmPassword" placeholder="Re-write New Password" />
                        </div>
                    </div>

                    <!-- Save Button -->
                    <div class="btn-container">
                        <button type="submit" class="save-btn">SAVE</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
</body>
</html>
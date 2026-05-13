<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password | QuickBite</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/userProfile.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet" />
</head>
<body>

    <%@ include file="../../common/navbar1.jsp" %>
    <%@ include file="../../common/utilityBar.jsp" %>

    <div class="user-profile-section-container">
        <section class="user-profile-section">
            
            <%@ include file="../../common/userProfileSideNav.jsp" %>

            <div class="user-profile-container">

				<c:if test="${not empty error}">
				    <div class="error-message">${error}</div>
				</c:if>
				<c:if test="${not empty success}">
				    <div class="success-message">${success}</div>
				</c:if>
                <form class="user-profile-form" id="passwordForm" action="${pageContext.request.contextPath}/profile/change-password" method="POST">
                    
                    <div class="input-group full-width">
                        <label>OLD PASSWORD</label>
                        <input type="password" name="oldPassword" id="oldPassword" placeholder="Enter Old Password" required>
                    </div>

                    <div class="input-group full-width">
                        <label>NEW PASSWORD</label>
                        <input type="password" name="newPassword" id="newPassword" placeholder="Enter New Password" required>
                    </div>

                    <div class="input-group full-width">
                        <label>CONFIRM NEW PASSWORD</label>
                        <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Re-write New Password" required>
                    </div>

                    <div class="profile-btn-container">
                        <button type="submit" class="save-btn">SAVE PASSWORD</button>
                        <button type="button" class="cancel-btn" onclick="clearFields()">CANCEL</button>
                    </div>
                </form>
            </div>
        </section>
    </div>

    <%@ include file="../../common/footer.jsp" %>

    <script>
        function clearFields() {
            document.getElementById("passwordForm").reset();
        }
    </script>

</body>
</html>
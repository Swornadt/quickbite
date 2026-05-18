<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>My Profile</title>
    <style>
        

        .profile-container {
            background: #ffffff;
            width: 300px;
            padding: 20px;
            text-align: center;
            border-radius: 4px;
        }

        .title {
            text-align: left;
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }

        .profile-header {
            margin-bottom: 20px;
        }

        .avatar {
            width: 90px;
            height: 90px;
            border-radius: 50%;
            object-fit: cover;
        }

        .username {
            margin: 10px 0 5px;
            font-weight: 500;
            color: #444;
        }

        .edit-profile {
            color: #ff5a5f;
            text-decoration: none;
            font-size: 14px;
        }

        .edit-profile:hover {
            text-decoration: underline;
        }

        hr {
            border: none;
            border-top: 1px solid #eee;
            margin: 15px 0;
        }

        .menu {
            text-align: left;
        }

        .menu a {
            display: block;
            padding: 8px 0;
            color: #666;
            text-decoration: none;
        }

        .menu a:hover {
            color: #000;
        }

        .logout {
            background: none;
            border: none;
            color: #ff5a5f;
            font-size: 14px;
            cursor: pointer;
        }

        .logout:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

    <div class="profile-container">
        <h2 class="title">MY PROFILE</h2>

        <div class="profile-header">
            <img src="${pageContext.request.contextPath}/${user.image}" alt="Profile Image" class="avatar" onerror="this.style.display='none'">
            <h3 class="username">${user.fname} ${user.lname}</h3>
            <a href="${pageContext.request.contextPath}/profile" class="edit-profile">Edit Profile</a>
        </div>

        <hr>

        <div class="menu">
            <a href="${pageContext.request.contextPath}/profile/order-history">Order History</a>
            <a href="${pageContext.request.contextPath}/profile/favorites">Favourites</a>
        </div>

        <hr>

        <a href="${pageContext.request.contextPath}/logout" class="logout">Logout</a>
    </div>

</body>

</html>
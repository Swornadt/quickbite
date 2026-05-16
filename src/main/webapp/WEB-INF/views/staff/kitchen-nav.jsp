<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kitchen | Quick Bite</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/kitchen-nav.css" />
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet" />
</head>
<body>
<nav class="kitchen-nav">
  <a href="${pageContext.request.contextPath}/kitchen" class="outlet-info">
    <img src="${pageContext.request.contextPath}/${outletImage}" class="outlet-image" />
    <span class="outlet-name">${outletName}</span>
  </a>
  <form method="post" action="${pageContext.request.contextPath}/logout">
    <button type="submit" class="btn-logout">Logout</button>
  </form>
</nav>
</body>
</html>
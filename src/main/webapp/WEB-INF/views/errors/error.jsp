<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${pageContext.errorData.statusCode != 0 ? pageContext.errorData.statusCode : "Error"} | QuickBite </title>
  
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&family=Lobster&display=swap" rel="stylesheet">
  
</head>
<body>

  <!-- HEADER -->
  <%@ include file="../common/navbar1.jsp" %>

  <!-- MAIN -->
  <section class="main">
  <div class="oops">OOPS!</div>

    <div class="error-title">
        <c:choose>
            <c:when test="${pageContext.errorData.statusCode == 404}">404 - PAGE NOT FOUND</c:when>
            <c:when test="${pageContext.errorData.statusCode == 403}">403 - FORBIDDEN</c:when>
            <c:when test="${pageContext.errorData.statusCode == 500}">500 - INTERNAL SERVER ERROR</c:when>
            <c:otherwise>SOMETHING WENT WRONG</c:otherwise>
        </c:choose>
    </div>

    <div class="error-text">
      <c:choose>
            <c:when test="${pageContext.errorData.statusCode == 404}">
                The page you are looking for might have been removed, <br>
                had its name changed, or is temporarily unavailable.
            </c:when>
            <c:when test="${pageContext.errorData.statusCode == 403}">
                Unauthorized access. If you believe this is a mistake, <br>
                please contact support.
            </c:when>
            <c:otherwise>
                We encountered an unexpected problem while processing your request. <br>
                Please try again later or contact support if the issue persists.
            </c:otherwise>
        </c:choose>
    </div>

    <a href="${pageContext.request.contextPath}/home">
    <button class="home-btn">
      GO TO HOMEPAGE
    </button>
    </a>
  </section>

  <!-- FOOTER -->
  <%@ include file="../common/footer.jsp" %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Outlet Selection | QuickBite</title>
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/outlet.css" />
  </head>
  <body>
  
  <!-- Header -->
  <%@ include file="../common/navbar1.jsp" %>
  
    <main class="app-container">
      <header class="hero">
        <h1>QuickBite Navigator</h1>
        <p>Select your favorite campus canteen to pre-order</p>
      </header>

      <section class="selector-section">
        <h2 class="question">Where do you want to eat today?</h2>

		<div class="location-grid" id="locationGrid">
			<c:forEach var="loc" items="${locations}">
		        <c:set var="hasPhoto" value="${not empty loc.outletImage}" />
		        <a href="${pageContext.request.contextPath}/outlets/${loc.outletName}" style="text-decoration: none; color: inherit; width: 30%;">
		            <div class="location-card ${hasPhoto ? 'card-with-photo' : ''}" 
		                 <c:if test="${hasPhoto}">
		                     style="--bg-image: url('${pageContext.request.contextPath}/${loc.outletImage}');"
		                 </c:if>>
		                
		                <span class="location-name">
		                    ${loc.outletName}
		                </span>
		            </div>
		        </a>
		    </c:forEach>
		</div>
		
      </section>
    </main>
  <!-- Footer -->
  <%@ include file="../common/footer.jsp" %>

  </body>
</html>

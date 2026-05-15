<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.quickbite.model.OutletModel" %>
<%@ page import="java.util.*" %>

<%-- <%
    List<Map<String, String>> locations = new ArrayList<>();
    
    locations.add(Map.of("id", "1", "name", "Coffee Station", "photo", "../assets/outlet/coffee-station.png"));
    locations.add(Map.of("id", "2", "name", "Momo Station", "photo", "../assets/outlet/momo-station.png"));
    locations.add(Map.of("id", "3", "name", "Cafeteria", "photo", "../assets/outlet/main-canteen.png"));
    locations.add(Map.of("id", "4", "name", "Chautari", "photo", "../assets/outlet/chautari.png"));
    locations.add(Map.of("id", "5", "name", "Brit Cafe", "photo", "../assets/outlet/brit-cafe.png")); // Empty photo
    locations.add(Map.of("id", "6", "name", "Kumari Cafe", "photo", "../assets/outlet/kumari-cafe.png"));

    request.setAttribute("locations", locations);
%> 

--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
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
					<div class="location-card ${hasPhoto ? 'card-with-photo' : ''}" onclick="selectLocation(this, '${loc.outletId}', '${loc.outletName}')"
						<c:if test="${hasPhoto}">
							style= "--bg-image: url('${loc.outletImage}');"
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

    <script>
    function selectLocation(element, id, name) {
        const currentActive = document.querySelector(".location-card.active");
        if (currentActive) {
            currentActive.classList.remove("active");
        }
        element.classList.add("active");
        console.log("Selected Location ID:", id, "Name:", name);
		//TODO: implement redirect
    }
    </script>
  </body>
</html>

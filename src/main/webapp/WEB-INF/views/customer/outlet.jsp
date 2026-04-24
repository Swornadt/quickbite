<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.quickbite.model.Outlet" %>
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
    <main class="app-container">
      <header class="hero">
        <h1>QuickBite Navigator</h1>
        <p>Select your favorite campus canteen to pre-order</p>
      </header>

      <section class="selector-section">
        <h2 class="question">Where do you want to eat today?</h2>

        <div class="location-grid" id="locationGrid">
		    <%
		        List<Outlet> locList = (List<Outlet>) request.getAttribute("locations");
		        
		        if (locList != null) {
		            for (Outlet loc : locList) {
		                int id = loc.getOutletId();
		                String name = loc.getOutletName();
		                String photo = loc.getOutletImage();
		                
		                boolean hasPhoto = (photo != null && !photo.trim().isEmpty());
		                String photoClass = hasPhoto ? "card-with-photo" : "";
		                String styleAttr = hasPhoto ? "style=\"--bg-image: url('" + photo + "');\"" : "";
		    %>
		    <a href="${pageContext.request.contextPath}/location-menu?outletId=<%=id%>" style="text-decoration: none; color: inherit; width: 30%;"></a>
				<div class="location-card <%= photoClass %>" 
		             onclick="selectLocation(this, '<%= id %>', '<%= name %>')"
		             <%= styleAttr %>>
		            
		            <span class="location-name"><%= name %></span>
		        </div>
			<%
		            }
		        }
			%>
		</div>
      </section>
    </main>

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

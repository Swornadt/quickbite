<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>QuickBite | Admin Dashboard</title>
    <!-- Font Awesome Cdn link  -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
      integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <!-- Google Font Cdn link -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-main-dashboard.css" />
  </head>
  <body>
  	<div class="admin-body">
  		<%@ include file="../common/side-nav.jsp" %>
	    <div class="admin-right-body">
	      <!-- Top tools  -->
	      <div class="admin-top-info-container">
	        <div class="admin-top-info">
	          <div class="admin-input-section">
	            <input placeholder="Search" class="admin-input" />
	            <i
	              class="fa-solid fa-magnifying-glass"
	              id="input-magnifying-glass"
	            ></i>
	          </div>
	          <div class="admin-right-tools">
	            <div class="notification-section">
	              <i class="fa-solid fa-bell" id="bell-icon"></i>
	              <i class="fa-solid fa-message" id="message-icon"></i>
	            </div>
	            <div class="admin-info-container">
	              <div class="admin-name-role">
	                <p class="admin-name">Sanskar Piya</p>
	                <p class="admin-role">Admin</p>
	              </div>
	              <div class="admin-image">
	                <img src="<%=request.getContextPath()%>/assets/user-image.jpg" />
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	
	      <!-- Bottom Body -->
	      <div class="admin-bottom-info">
	        <!-- Card Info -->
	        <div class="admin-card-infos">
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-1.png" class="admn-card-img" />
	            <p class="gained-number">2.3K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Users</p>
	              <p class="card-info-number-percent">1.1% &uarr;</p>
	            </div>
	          </div>
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-2.png" class="admn-card-img" />
	            <p class="gained-number">Rs. 150.8K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Proft</p>
	              <p class="card-info-number-percent">4.40% &uarr;</p>
	            </div>
	          </div>
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-3.png" class="admn-card-img" />
	            <p class="gained-number">100K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Orders</p>
	              <p class="card-info-number-percent">3.6% &uarr;</p>
	            </div>
	          </div>
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-4.png" class="admn-card-img" />
	            <p class="gained-number">100K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Expense</p>
	              <p class="card-info-number-percent">3.6% &uarr;</p>
	            </div>
	          </div>
	        </div>
	
	        <!-- Line Chart and Pie Chart Container -->
	        <div class="linechart-piechart">
	          <!-- Line Chart  -->
	          <div class="line-chart-container">
	            <div class="line-chart-details">
	              <div class="line-chart-legends">
	                <p class="total-revenue">Total Revenue</p>
	                <p class="total-sales">Total Sales</p>
	              </div>
	              <div class="line-chart-dates">
	                <input
	                  type="date"
	                  id="start"
	                  name="info-start"
	                  value="2026-12-02"
	                />
	                <p>to</p>
	                <input
	                  type="date"
	                  id="start"
	                  name="info-start"
	                  value="2026-12-03"
	                />
	              </div>
	            </div>
	            <div class="line-chart-image-container">
	              <img
	                src="<%=request.getContextPath()%>/assets/Line Graph.png"
	                class="line-chart"
	                alt="line-graph-image"
	              />
	            </div>
	          </div>
	
	          <!-- Pie Chart  -->
	          <div class="pie-chart-container">
	            <p class="pie-chart-title">Profit by Location</p>
	            <div class="pie-chart-image-container">
	              <img
	                src="<%=request.getContextPath()%>/assets/pie-chart.png"
	                alt="pie-chart img"
	                class="pie-chart-image"
	              />
	            </div>
	          </div>
	        </div>
	
	        <div class="barcharts-container">
	          <div class="barchart-highrate">
	            <p class="barchart-title">Highest Rated Items</p>
	            <div
	              class="barchart-image-container"
	              id="barchart-highrate-image-container"
	            >
	              <img src="<%=request.getContextPath()%>/assets/barchart1.png" class="barchart" />
	            </div>
	          </div>
	          <div class="barchart-profit">
	            <p class="barchart-title">Profit this week</p>
	            <div class="week-dropdown">
	              <select id="cars" name="cars">
	                <option value="volvo" selected>This Week</option>
	              </select>
	            </div>
	            <div
	              class="barchart-image-container"
	              id="barchart-profit-image-container"
	            >
	              <img src="<%=request.getContextPath()%>/assets/barchart2.png" class="barchart" />
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
  	</div>
  </body>
</html>
    
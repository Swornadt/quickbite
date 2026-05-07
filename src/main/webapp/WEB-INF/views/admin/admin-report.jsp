<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Report | QuickBite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-report.css" />
</head>
<body>
<div class="admin-body">
  		<%@ include file="../common/side-nav.jsp" %>
	    <div class="admin-right-body">
	      <%@ include file='../common/adminNav.jsp' %>
	      
	
	      <!-- Bottom Body -->
	      <div class="admin-bottom-info">
	      <div class="top-container">
	      <h1>Report</h1>
	      
	      <form method="GET" action="<%=request.getContextPath()%>/admin/report">
                    <select name="outletId" class="canteen-btn" onchange="this.form.submit()">
                      <c:forEach var="outlet" items="${outlets}">
                        <option value="${outlet.outletId}" ${outlet.outletId==selectedOutletId ? 'selected' : '' }>
                          ${outlet.outletName}
                        </option>
                      </c:forEach>
                    </select>
                  </form>
                  </div>
                  
	        <!-- Card Info -->
	        <div class="admin-card-infos">
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-1.jpeg" class="admn-card-img" />
	            <p class="gained-number">2.3K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Users</p>
	              <p class="card-info-number-percent">1.1% &uarr;</p>
	            </div>
	          </div>
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-2.jpeg" class="admn-card-img" />
	            <p class="gained-number">Rs. 150.8K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Sales</p>
	              <p class="card-info-number-percent">4.40% &uarr;</p>
	            </div>
	          </div>
	          <!-- Card Info -->
	          <div class="admin-card-info">
	            <img src="<%=request.getContextPath()%>/assets/info-img-3.jpeg" class="admn-card-img" />
	            <p class="gained-number">100K</p>
	            <div class="card-info-numbers">
	              <p class="card-info-number-title">Total Orders</p>
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
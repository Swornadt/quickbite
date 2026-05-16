<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib uri="jakarta.tags.core" prefix="c" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Admin Report | QuickBite</title>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-report.css" />
		</head>

		<body>
			<div class="admin-body">
				<%@ include file="../common/side-nav.jsp" %>
					<div class="admin-right-body">
						<%@ include file='../common/admin-nav.jsp' %>

							<div class="admin-bottom-info">
								<div class="top-container">
									<h2>Report</h2>

									<form method="GET" action="${pageContext.request.contextPath}/admin/report">
										<select name="outletId" class="canteen-btn" onchange="this.form.submit()">
											<option value="0" ${selectedOutletId==0 ? 'selected' : '' }>All Outlets
											</option>
											<c:forEach var="outlet" items="${outlets}">
												<option value="${outlet.outletId}" ${selectedOutletId==outlet.outletId
													? 'selected' : '' }>
													${outlet.outletName}
												</option>
											</c:forEach>
										</select>
									</form>
								</div>

								<!-- Cards -->
								<div class="admin-card-infos">
									<div class="admin-card-info">
										<img src="${pageContext.request.contextPath}/assets/info-img-1.jpeg"
											class="admin-card-img" />
										<p class="gained-number">${report.totalUsers}</p>
										<div class="card-info-numbers">
											<p class="card-info-number-title">Total Users</p>
										</div>
									</div>

									<div class="admin-card-info">
										<img src="${pageContext.request.contextPath}/assets/info-img-2.jpeg"
											class="admin-card-img" />
										<p class="gained-number">Rs. ${report.totalSales}</p>
										<div class="card-info-numbers">
											<p class="card-info-number-title">Total Sales</p>
										</div>
									</div>

									<div class="admin-card-info">
										<img src="${pageContext.request.contextPath}/assets/info-img-3.jpeg"
											class="admin-card-img" />
										<p class="gained-number">${report.totalOrders}</p>
										<div class="card-info-numbers">
											<p class="card-info-number-title">Total Orders</p>
										</div>
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
											<input type="date" id="start" name="info-start" value="2026-12-02" />
											<p>to</p>
											<input type="date" id="start" name="info-start" value="2026-12-03" />
										</div>
									</div>
									<div class="line-chart-image-container">
										<img src="${pageContext.request.contextPath}/assets/Line Graph.png"
											class="line-chart" alt="line-graph-image" />
									</div>
								</div>


							</div>

							<div class="barcharts-container">
								<div class="barchart-highrate">
									<p class="barchart-title">Highest Rated Items</p>
									<div class="barchart-image-container" id="barchart-highrate-image-container">
										<img src="${pageContext.request.contextPath}/assets/barchart1.png"
											class="barchart" />
									</div>
								</div>
								<div class="barchart-profit">
									<p class="barchart-title">Profit this week</p>
									<div class="week-dropdown">
										<select id="cars" name="cars">
											<option value="volvo" selected>This Week</option>
										</select>
									</div>
									<div class="barchart-image-container" id="barchart-profit-image-container">
										<img src="${pageContext.request.contextPath}/assets/barchart2.png"
											class="barchart" />
									</div>
								</div>
							</div>
					</div>
			</div>

		</body>

		</html>
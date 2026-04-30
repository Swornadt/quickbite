<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Admin Feedback | Quickbite</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-feedback.css" />
</head>
<body>
	<%@ include file="../common/side-nav.jsp" %>
	<div class="main-content">
		<h1>Customer Feedback</h1>
		<table>
			<thead>
				<tr>
					<th>Customer Name</th>
					<th>Email</th>
					<th>Rating</th>
					<th>Comments</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="feedback" items="${feedbackList}">
					<tr>
						<td>${feedback.customerName}</td>
						<td>${feedback.email}</td>
						<td>${feedback.rating}</td>
						<td>${feedback.comments}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
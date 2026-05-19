<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Customer Management</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminCustomerApproval.css">
</head>
<body>

<%@ include file="../common/side-nav.jsp" %>

<div class="overall-customerApproval-container">

    <%@ include file="../common/admin-nav.jsp" %>

    <div class="container">

        <h1>Customer Management</h1>

       <c:if test="${param.status == 'passwordReset'}">
    		<div class="success-banner">
        		Password has been reset successfully.
    		</div>
		</c:if>

        <!-- TABS -->
        <div class="tabs">
            <button class="tab onactive">Pending Customers</button>
            <button class="tab">Active Customers</button>
            <button class="tab">Password Reset Requests</button>
        </div>

        <!-- PENDING CUSTOMERS -->
        <div class="grid" id="pending">
            <c:choose>
                <c:when test="${empty pendingUsers}">
                    <div class="empty-state">No pending customers at the moment.</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="user" items="${pendingUsers}">
                        <div class="card">
                            <div class="card-top">
                                <img class="photo"
                                     src="${pageContext.request.contextPath}/${not empty user.image ? user.image : 'uploads/default.png'}"
                                     alt="Customer Photo">
                                <div class="card-info">
                                    <div class="name">${user.fname} ${user.lname}</div>
                                    <div class="phone">${user.number}</div>
                                    <div class="email">${user.email}</div>
                                </div>
                            </div>
                            <div class="buttons">
                                <form method="post"
                                      action="${pageContext.request.contextPath}/admin/customers/update">
                                    <input type="hidden" name="user_id" value="${user.userId}">
                                    <input type="hidden" name="action" value="approve">
                                    <button type="submit" class="btn approve">Approve</button>
                                </form>
                                <form method="post"
                                      action="${pageContext.request.contextPath}/admin/customers/update">
                                    <input type="hidden" name="user_id" value="${user.userId}">
                                    <input type="hidden" name="action" value="reject">
                                    <button type="submit" class="btn reject">Reject</button>
                                </form>
								<a href="${pageContext.request.contextPath}/admin/customers/profile?userId=${user.userId}">

							        <button type="button" style="color: #d13c3c; background-color:white;" class="btn approve">View Profile</button>

							    </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- ACTIVE CUSTOMERS -->
        <div class="grid" id="active" style="display: none;">
            <c:choose>
                <c:when test="${empty activeCustomers}">
                    <div class="empty-state">No active customers yet.</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="user" items="${activeCustomers}">
                        <div class="card active-card">
                            <div class="card-top">
                                <img class="photo"
                                     src="${pageContext.request.contextPath}/${not empty user.image ? user.image : 'uploads/default.png'}"
                                     alt="Customer Photo">
                                <div class="card-info">
                                    <div class="name">${user.fname} ${user.lname}</div>
                                    <div class="phone">${user.number}</div>
                                    <div class="email">${user.email}</div>
                                </div>
                            </div>
                            <div class="status-badge">&check; Active</div>
                            <a href="${pageContext.request.contextPath}/admin/customers/profile?userId=${user.userId}">
								<button type="button" class="btn approve">View Profile</button>
							</a>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        
        <!-- PASSWORD RESET REQUESTS -->
        <div class="grid" id="password-reset-requests" style="display: none;">
        <c:choose>
                <c:when test="${empty passwordResetRequests}">
                    <div class="empty-state">No password reset requests at the moment.</div>
                </c:when>
                <c:otherwise>
                    <c:forEach var="user" items="${passwordResetRequests}">
                        <div class="card">
                            <div class="card-top">
                                <img class="photo"
                                     src="${pageContext.request.contextPath}/${not empty user.image ? user.image : 'uploads/default.png'}"
                                     alt="Customer Photo">
                                <div class="card-info">
                                    <div class="name">${user.fname} ${user.lname}</div>
                                    <div class="email">${user.email}</div>
                                </div>
                                

                            </div>
                            <div class="buttons">
                                <form method="post"
                                      action="${pageContext.request.contextPath}/admin/customers/reset">
                                    <input type="hidden" name="user_id" value="${user.userId}">
                                    <input type="hidden" name="action" value="approve">
                                    <button type="submit" class="btn approve">Set Password</button>
                                </form>
                                <form method="post"
                                      action="${pageContext.request.contextPath}/admin/customers/reset">
                                    <input type="hidden" name="user_id" value="${user.userId}">
                                    <input type="hidden" name="action" value="reject">
                                    <button type="submit" class="btn reject">Reject</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>

<script src="${pageContext.request.contextPath}/js/admin-customer-approval.js"></script>

</body>
</html>
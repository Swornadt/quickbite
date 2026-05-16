<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Delete Item</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminMenuForm.css" />
        </head>

        <body>
            <div class="admin-right-body">
                <main class="content">
                    <div class="page-header">
                        <h2>Delete Confirmation</h2>
                    </div>

                    <div class="form-container">
                        <c:if test="${not empty message}">
                            <div class="alert ${status == 'success' ? 'alert-success' : 'alert-error'}">
                                ${message}
                            </div>
                        </c:if>

                        <c:if test="${empty message}">
                            <form action="${pageContext.request.contextPath}/admin/menu/delete" method="post">
                                <input type="hidden" name="itemId" value="${param.itemId}">
                                <input type="hidden" name="outletId" value="${param.outletId}">
                                <input type="hidden" name="action" value="confirm">

                                <div class="form-group">
                                    <label>Are you sure?</label>
                                    <p><strong>Item ID: ${param.itemId}</strong></p>
                                </div>
                                <button type="submit" class="submit-btn">
                                    Yes, Delete Item
                                </button>
                            </form>
                        </c:if>

                    </div>
                </main>
            </div>
        </body>

        </html>
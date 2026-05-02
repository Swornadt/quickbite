<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Delete Item</title>
            <link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminMenuForm.css" />
            <style>
                .alert {
                    padding: 15px;
                    margin: 15px 0;
                    border-radius: 5px;
                    text-align: center;
                }

                .alert-success {
                    background: #d4edda;
                    color: #155724;
                }

                .alert-error {
                    background: #f8d7da;
                    color: #721c24;
                }
            </style>
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
                            <form action="<%=request.getContextPath()%>/DeleteItem" method="post">
                                <input type="hidden" name="itemId" value="${param.itemId}">
                                <input type="hidden" name="action" value="delete"> <!-- Important -->

                                <div class="form-group">
                                    <label>Are you sure?</label>
                                    <p><strong>Item ID: ${param.itemId}</strong></p>
                                </div>
                                <button type="submit" class="submit-btn" style="background-color:#dc3545;">
                                    Yes, Delete Item
                                </button>
                            </form>
                        </c:if>

                    </div>
                </main>
            </div>
        </body>

        </html>
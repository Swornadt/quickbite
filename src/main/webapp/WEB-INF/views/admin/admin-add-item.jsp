<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Add New Item | Quickbite</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminMenuForm.css" />
        </head>

        <body>
            <div class="admin-body">
                <div class="admin-right-body">
                    <main class="content">

                        <!-- Success / Error Message -->
                        <c:if test="${not empty message}">
                            <div class="alert ${status == 'success' ? 'alert-success' : 'alert-error'}">
                                ${message}
                            </div>
                        </c:if>

                        <div class="page-header">
                            <h2>Add New Item</h2>
                        </div>

                        <div class="form-container">
                            <form action="${pageContext.request.contextPath}/admin/menu/add" method="post"
                                enctype="multipart/form-data">
                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>Item Name</label>
                                        <input type="text" name="itemName" placeholder="Enter item name">
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label>
                                        <input type="text" name="category" placeholder="Enter category (e.g., Snacks)">
                                    </div>
                                    <div class="form-group">
                                        <label>Item Type</label>
                                        <input type="text" name="itemType" placeholder="Enter type (e.g., Veg/Non-Veg)">
                                    </div>
                                    <div class="form-group">
                                        <label>Price</label>
                                        <input type="number" step="0.01" name="price" placeholder="0.00">
                                    </div>
                                    <div class="form-group">
                                        <label>Location</label>
                                        <select name="outletId">
                                            <option value="" disabled selected>-- Choose a location --</option>
                                            <c:forEach var="outlet" items="${outlets}">
                                                <option value="${outlet.outletId}">
                                                    ${outlet.outletName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Status</label>
                                        <select name="itemStatus">
                                            <option value="Available">Available</option>
                                            <option value="Not Available">Not Available</option>
                                        </select>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Description</label>
                                        <textarea name="itemDescription"
                                            placeholder="Enter item description"></textarea>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Ingredients</label>
                                        <textarea name="itemIngredient" placeholder="Enter ingredients"></textarea>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Allergies</label>
                                        <textarea name="itemAllergy" placeholder="Enter potential allergies"></textarea>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Item Image</label>
                                        <input type="file" name="itemImage" accept="image/*">
                                    </div>
                                </div>
                                <button type="submit" class="submit-btn">Add Item</button>
                            </form>
                        </div>
                    </main>
                </div>
            </div>
        </body>

        </html>
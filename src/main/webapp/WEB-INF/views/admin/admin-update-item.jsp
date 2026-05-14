<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Update Item | Quickbite</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminMenuForm.css" />
        </head>

        <body>

            <div class="admin-body">
                <div class="admin-right-body">
                    <main class="content">
                        <div class="page-header">
                            <h2>Edit Item</h2>
                        </div>

                        <div class="form-container">

                            <form action="${pageContext.request.contextPath}/admin/menu/edit" method="post"
                                enctype="multipart/form-data">
                                <input type="hidden" name="itemId" value="${item.itemId}">
                                <input type="hidden" name="existingImage" value="${item.itemImage}">

                                <div class="form-grid">
                                    <div class="form-group">
                                        <label>Item Name</label>
                                        <input type="text" name="itemName" value="${item != null ? item.itemName : ''}"
                                            required>
                                    </div>
                                    <div class="form-group">
                                        <label>Category</label>
                                        <input type="text" name="category" value="${item != null ? item.category : ''}">
                                    </div>
                                    <div class="form-group">
                                        <label>Item Type</label>
                                        <input type="text" name="itemType" value="${item != null ? item.itemType : ''}">
                                    </div>
                                    <div class="form-group">
                                        <label>Price <small>(Optional)</small></label>
                                        <input type="number" step="0.01" name="price" value="" placeholder="0.00">
                                    </div>

                                    <div class="form-group">
                                        <label>Location (for price update)</label>
                                        <select name="outletId">
                                            <option value="">-- Select Outlet --</option>
                                            <c:forEach var="outlet" items="${outlets}">
                                                <option value="${outlet.outletId}">${outlet.outletName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label>Status</label>
                                        <select name="itemStatus">
                                            <option value="Available" ${item !=null && item.itemStatus=='Available'
                                                ? 'selected' : '' }>Available</option>
                                            <option value="Not Available" ${item !=null &&
                                                item.itemStatus=='Not Available' ? 'selected' : '' }>Not Available
                                            </option>
                                        </select>
                                    </div>

                                    <div class="form-group full-width">
                                        <label>Description</label>
                                        <textarea
                                            name="itemDescription">${item != null ? item.itemDescription : ''}</textarea>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Ingredients</label>
                                        <textarea
                                            name="itemIngredient">${item != null ? item.itemIngredient : ''}</textarea>
                                    </div>
                                    <div class="form-group full-width">
                                        <label>Allergies</label>
                                        <textarea name="itemAllergy">${item != null ? item.itemAllergy : ''}</textarea>
                                    </div>

                                    <div class="form-group full-width current-image-container">
                                        <label>Current Image:</label>
                                        <c:if
                                            test="${item != null && item.itemImage != null && not empty item.itemImage}">
                                            <img src="${pageContext.request.contextPath}/${item.itemImage}"
                                                class="form-image-preview" alt="Current Image" />
                                        </c:if>
                                    </div>

                                    <div class="form-group full-width">
                                        <label>Change Image</label>
                                        <input type="file" name="itemImage" accept="image/*">
                                    </div>
                                </div>

                                <button type="submit" class="submit-btn">Update Item</button>
                            </form>
                        </div>
                    </main>
                </div>
            </div>
        </body>

        </html>

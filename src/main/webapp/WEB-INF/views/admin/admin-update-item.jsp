<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Item | Quickbite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminAddMenu.css" />
</head>
<body>
<div class="admin-body">
        <div class="admin-right-body">
            <main class="content">
                <div class="page-header">
                    <h2>Edit Item</h2>
                    <a href="<%=request.getContextPath()%>/AdminMenuServlet?outletId=${item.outletId}" class="back-btn"><i class="fas fa-arrow-left"></i> Back to Menu</a>
                </div>

                <div class="form-container">
                    <% Item item = (Item) request.getAttribute("item"); %>
                    <form action="<%=request.getContextPath()%>/EditMenuServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="itemId" value="<%=item.getItemId()%>">
                        <input type="hidden" name="existingImage" value="<%=item.getItemImage()%>">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Item Name</label>
                                <input type="text" name="itemName" value="<%=item.getItemName()%>" required placeholder="Enter item name">
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <input type="text" name="category" value="<%=item.getCategory()%>" placeholder="Enter category">
                            </div>
                            <div class="form-group">
                                <label>Item Type</label>
                                <input type="text" name="itemType" value="<%=item.getItemType()%>" placeholder="Enter type">
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" step="0.01" name="price" value="<%=item.getPrice()%>" required placeholder="0.00">
                            </div>
                            <div class="form-group">
                                <label>Location</label>
                                <select name="outletId" required>
                                    <% 
                                        List<Outlet> outletList = (List<Outlet>) request.getAttribute("outletList");
                                        for (Outlet outlet : outletList) { 
                                    %>
                                        <option value="<%=outlet.getOutletId()%>" <%= (outlet.getOutletId() == item.getOutletId() ? "selected" : "") %>><%=outlet.getOutletName()%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <select name="itemStatus">
                                    <option value="Available" <%= ("Available".equals(item.getItemStatus()) ? "selected" : "") %>>Available</option>
                                    <option value="Not Available" <%= ("Not Available".equals(item.getItemStatus()) ? "selected" : "") %>>Not Available</option>
                                </select>
                            </div>
                            <div class="form-group full-width">
                                <label>Description</label>
                                <textarea name="itemDescription" placeholder="Enter item description"><%= (item.getItemDescription() == null ? "" : item.getItemDescription()) %></textarea>
                            </div>
                            <div class="form-group full-width">
                                <label>Ingredients</label>
                                <textarea name="itemIngredient" placeholder="Enter ingredients"><%= (item.getItemIngredient() == null ? "" : item.getItemIngredient()) %></textarea>
                            </div>
                            <div class="form-group full-width">
                                <label>Allergies</label>
                                <textarea name="itemAllergy" placeholder="Enter potential allergies"><%= (item.getItemAllergy() == null ? "" : item.getItemAllergy()) %></textarea>
                            </div>
                            <div class="form-group full-width current-image-container">
                                <label>Current Image:</label>
                                <p><%=item.getItemImage()%></p>
                                <img src="<%=request.getContextPath()%>/assets/item-images/<%=item.getItemImage()%>" class="form-image-preview" alt="Current Item Image"/>
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
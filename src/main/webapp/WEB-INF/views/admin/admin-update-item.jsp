<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Item | Quickbite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminMenuForm.css" />
</head>
<body>
<div class="admin-body">
        <div class="admin-right-body">
            <main class="content">
                <div class="page-header">
                    <h2>Edit Item</h2>
                </div>

                <div class="form-container">
                    
                    <form action="<%=request.getContextPath()%>/EditMenuServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="itemId" value="">
                        <input type="hidden" name="existingImage" value="">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Item Name</label>
                                <input type="text" name="itemName" value="" placeholder="Enter item name">
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <input type="text" name="category" value="" placeholder="Enter category">
                            </div>
                            <div class="form-group">
                                <label>Item Type</label>
                                <input type="text" name="itemType" value="" placeholder="Enter type">
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" step="0.01" name="price" value="" placeholder="0.00">
                            </div>
                            
                            <div class="form-group">
                                <label>Status</label>
                                <select name="itemStatus">
                                    <option value="Available" >Available</option>
                                    <option value="Not Available">Not Available</option>
                                </select>
                            </div>
                            <div class="form-group full-width">
                                <label>Description</label>
                                <textarea name="itemDescription" placeholder="Enter item description"></textarea>
                            </div>
                            <div class="form-group full-width">
                                <label>Ingredients</label>
                                <textarea name="itemIngredient" placeholder="Enter ingredients"></textarea>
                            </div>
                            <div class="form-group full-width">
                                <label>Allergies</label>
                                <textarea name="itemAllergy" placeholder="Enter potential allergies"></textarea>
                            </div>
                            <div class="form-group full-width current-image-container">
                                <label>Current Image:</label>
                                <p></p>
                                <img src="<%=request.getContextPath()%>/assets/item-images/" class="form-image-preview" alt="Current Item Image"/>
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
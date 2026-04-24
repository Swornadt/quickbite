<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Item | Quickbite</title>
</head>
<body>
<div class="admin-right-body">
            <main class="content">
                <div class="page-header">
                    <h2>Add New Item</h2>
                    <a href="<%=request.getContextPath()%>/AdminMenuServlet" class="back-btn"><i class="fas fa-arrow-left"></i> Back to Menu</a>
                </div>

                <div class="form-container">
                    <form action="<%=request.getContextPath()%>/AddMenuServlet" method="post" enctype="multipart/form-data">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Enter Item Name:</label>
                                <input type="text" name="itemName">
                            </div>
 						</div>
                        <button type="submit" class="submit-btn">Delete Item</button>
                    </form>
                </div>
            </main>
        </div>
    </div>
</body>
</html>
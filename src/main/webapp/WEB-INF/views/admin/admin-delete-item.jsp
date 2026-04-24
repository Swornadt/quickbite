<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Item | Quickbite</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminAddMenu.css" />
</head>
<body>
<div class="admin-right-body">
            <main class="content">
                <div class="page-header">
                    <h2>Delete Item</h2>
                </div>

                <div class="form-container">
                    <form action="<%=request.getContextPath()%>/AddMenuServlet" method="post" enctype="multipart/form-data">
                        <div class="form-grid">
                            <div class="form-group">
                                <label>Enter item name again to confirm:</label>
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
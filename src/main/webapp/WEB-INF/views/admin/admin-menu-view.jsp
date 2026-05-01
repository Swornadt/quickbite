<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html >

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Update Menu | Quickbite</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-menu.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin-main-dashboard.css" />
</head>

<body>
  <div class="admin-body">
    <%@ include file="../common/side-nav.jsp" %>
      <div class="admin-right-body">
        <!-- Top tools  -->
        <div class="admin-top-info-container">
          <div class="admin-top-info">
            <div class="admin-input-section">
              <input placeholder="Search" class="admin-input" />
              <i class="fa-solid fa-magnifying-glass" id="input-magnifying-glass"></i>
            </div>
            <div class="admin-right-tools">
              <div class="notification-section">
                <i class="fa-solid fa-bell" id="bell-icon"></i>
                <i class="fa-solid fa-message" id="message-icon"></i>
              </div>
              <div class="admin-info-container">
                <div class="admin-name-role">
                  <p class="admin-name">Alison Burgeres</p>
                  <p class="admin-role">Admin</p>
                </div>
                <div class="admin-image">
                  <img src="<%=request.getContextPath()%>/assets/user-image.jpg" />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="admin-bottom-info">
                <div class="container">
                    <header class="header">
                        <h2>Menu Management</h2>
                        
                        <!-- Outlet Selector -->
                        <form method="GET" action="<%=request.getContextPath()%>/AdminMenuServlet">
                            <select name="outletId" class="canteen-btn" onchange="this.form.submit()">
                                <c:forEach var="outlet" items="${outlets}">
                                    <option value="${outlet.outletId}" 
                                            ${outlet.outletId == selectedOutletId ? 'selected' : ''}>
                                        ${outlet.outletName}
                                    </option>
                                </c:forEach>
                            </select>
                        </form>
                        
                        <button class="add-item-btn" onclick="openAddModal()">+ Add Item</button>
                    </header>

                    <!-- Dynamic Table -->
                    <table id="menuTable">
                        <thead>
                            <tr>
                                <th>Item ID</th>
                                <th>Item Name</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Item Description</th>
                                <th>Status</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
    <c:forEach var="oi" items="${outletItems}">
        <tr>
            <td>${oi.item.itemId}</td>
            <td>${oi.item.itemName}</td>
            <td>${oi.item.category}</td>
            <td>${oi.outletItemPrice}</td>
            <td>${oi.item.itemDescription}</td>
            <td>${oi.item.itemStatus}</td>
            <td>
                <form action="<%=request.getContextPath()%>/EditItem" method="post" target="updateIframe" style="display: inline;">
        <input type="hidden" name="itemId" value="${oi.item.itemId}" />
        <button type="button" class="edit-btn" onclick="this.form.submit(); openUpdateModal()">Edit</button>
    </form>
                        </td>
                        <td>
                <button class="delete-btn" 
                        onclick="deleteItem(${oi.item.itemId})">Delete</button>
            </td>
        </tr>
    </c:forEach>
    
    <c:if test="${empty outletItems}">
        <tr>
            <td colspan="6" style="text-align:center; padding:20px;">
                No items found for selected outlet. Please select a location.
            </td>
        </tr>
    </c:if>
</tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
      
<!-- ADD ITEM MODAL -->
<div id="addItemModal" class="modal">
  <div class="modal-content">
    <span class="close-btn" onclick="closeAddModal()">&times;</span>
    <iframe src="<%=request.getContextPath()%>/AddItem" class="modal-iframe"></iframe>
  </div>
</div>

<!-- UPDATE ITEM MODAL -->
<div id="updateItemModal" class="modal">
  <div class="modal-content">
    <span class="close-btn" onclick="closeUpdateModal()">&times;</span>
    <iframe name="updateIframe" id="updateIframe" src="" class="modal-iframe" 
            style="width:100%;  border:none;"></iframe>
  </div>
</div>

<!-- DELETE ITEM MODAL -->
<div id="deleteItemModal" class="modal">
  <div class="modal-content">
    <span class="close-btn" onclick="closeDeleteModal()">&times;</span>
    <iframe src="<%=request.getContextPath()%>/DeleteItem" class="modal-iframe"></iframe>
  </div>
</div>

<script>

function openAddModal() {
  document.getElementById("addItemModal").style.display = "block";
}

function closeAddModal() {
  document.getElementById("addItemModal").style.display = "none";
}

function openUpdateModal() {
	  document.getElementById("updateItemModal").style.display = "block";
	}

	function closeUpdateModal() {
	  document.getElementById("updateItemModal").style.display = "none";
	}

	function openDeleteModal() {
		  document.getElementById("deleteItemModal").style.display = "block";
		}

		function closeDeleteModal() {
		  document.getElementById("deleteItemModal").style.display = "none";
		}
</script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Admin Update Menu | Quickbite</title>
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-menu.css">
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-main-dashboard.css" />
    </head>

    <body>
      <div class="admin-body">
        <%@ include file="../common/side-nav.jsp" %>
          <div class="admin-right-body">
          <%@ include file='../common/adminNav.jsp' %>
    
    <!-- Bottom Body -->
	      <div class="admin-bottom-info">
            
              <div class="container">
                <header class="header">
                  <h2>Menu Management</h2>

                  <!-- Outlet Selector -->
                  <form method="GET" action="${pageContext.request.contextPath}/admin/menu">
                    <select name="outletId" class="canteen-btn" onchange="this.form.submit()">
                      <c:forEach var="outlet" items="${outlets}">
                        <option value="${outlet.outletId}" ${outlet.outletId==selectedOutletId ? 'selected' : '' }>
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
						    <form action="${pageContext.request.contextPath}/admin/menu/edit" method="get" target="updateIframe"
						        style="display: inline;">
						        <input type="hidden" name="itemId" value="${oi.item.itemId}" />
						        <button type="button" class="edit-btn"
						            onclick="this.form.submit(); openUpdateModal()">Edit</button>
						    </form>
						</td>
                        <td>
                          <form action="${pageContext.request.contextPath}/admin/menu/delete" method="post" target="deleteIframe"
                            style="display: inline;">
                            <input type="hidden" name="itemId" value="${oi.item.itemId}" />
                            <button type="button" class="delete-btn"
                              onclick="this.form.submit(); openDeleteModal()">Delete</button>
                          </form>

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
          <iframe src="${pageContext.request.contextPath}/admin/menu/add" class="modal-iframe"></iframe>
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
          <iframe name="deleteIframe" id="deleteIframe" src="" class="modal-iframe"></iframe>
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

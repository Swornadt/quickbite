<!DOCTYPE html>
<html lang="en">

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
              <select class="canteen-btn">
                <option value="" disabled selected>-- Choose a location --</option>
                <option>Main Canteen</option>
                <option>Coffee Station</option>
              </select>
              <button class="add-item-btn">+ Add Item</button>
            </header>

            <!-- Table -->
            <table>
              <thead>
                <tr>
                  <th>Item ID</th>
                  <th>Item Name</th>
                  <th>Category</th>
                  <th>Price</th>
                  <th></th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>001</td>
                  <td>Chicken momo</td>
                  <td>Snacks</td>
                  <td>150</td>
                  <td><button class="edit-btn">Edit</button></td>
                  <td><button class="delete-btn">Delete</button></td>
                </tr>
                <tr>
                  <td>002</td>
                  <td>Veg Sandwich</td>
                  <td>Breakfast</td>
                  <td>140</td>
                  <td><button class="edit-btn">Edit</button></td>
                  <td><button class="delete-btn">Delete</button></td>
                </tr>
                <tr>
                  <td>003</td>
                  <td>French Fries</td>
                  <td>Snacks</td>
                  <td>110</td>
                  <td><button class="edit-btn">Edit</button></td>
                  <td><button class="delete-btn">Delete</button></td>
                </tr>
              </tbody>
            </table>

          </div>
        </div>
      </div>
      </div>

</body>

</html>
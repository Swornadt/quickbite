
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Update Menu | Quickbite</title>
    <link rel="stylesheet" href="../../../css/admin-menu.css">
</head>
<body>
    <div class="admin-body">
  		<%@ include file="../common/side-nav.jsp" %>
	    <div class="admin-right-body">

<div class="container">
  <header class="header">
    <h2>Menu Management</h2>
    <select class="canteen-btn">
        <option value="" disabled selected>-- Choose a location --</option>
        <option>Main Canteen</option>
        <option>Coffee Station</option>
      </select>
  </header>

  <!-- Table -->
  <table>
    <thead>
      <tr>
        <th>Item ID</th>
        <th>Item Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Availability</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>001</td>
        <td>Chicken momo</td>
        <td>Snacks</td>
        <td>150</td>
        <td>available</td>
      </tr>
      <tr>
        <td>002</td>
        <td>Veg Sandwich</td>
        <td>Breakfast</td>
        <td>140</td>
        <td>available</td>
      </tr>
      <tr>
        <td>003</td>
        <td>French Fries</td>
        <td>Snacks</td>
        <td>110</td>
        <td>not available</td>
      </tr>
    </tbody>
  </table>

  <!-- Buttons -->
  <div class="actions">
    <button class="active">Add Item</button>
    <button>Update Item</button>
    <button>Delete Item</button>
  </div>

  <!-- Form -->
  <div class="form">
    <div class="row">
      <input type="text" placeholder="Item ID">
      <input type="text" placeholder="Item Name">
    </div>

    <div class="row">
      <select>
        <option value="" disabled selected>-- Choose an option --</option>
        <option>Snacks</option>
        <option>Breakfast</option>
      </select>

      <input type="number" placeholder="Price">
    </div>

    <div class="row">
      <input type="text" placeholder="Image URL">

      <select>
        <option value="" disabled selected>-- Choose a location --</option>
        <option>Main Canteen</option>
        <option>Coffee Station</option>
      </select>
    </div>

    <textarea placeholder="Description"></textarea>

    <button class="submit">Add Item</button>
  </div>

</div>
</div>

</body>
</html>
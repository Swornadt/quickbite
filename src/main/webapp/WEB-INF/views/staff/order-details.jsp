<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-details.css" />
</head>
<body>
  <div class="order">
 
    <!-- Letf div for order details -->
    <div class="order-details">
      <div class="order-details-title">Order Details</div>
      <div class="details">
        <div class="details-label">
          <div>Order ID:</div>
          <div>Order Type:</div>
          <div>Order Status:</div>
          <div>Total Quantity:</div>
          <div>Estimated Time:</div>
          <div>Date:</div>
          <div>Time:</div>
          <div>Location:</div>
        </div>
        <div class="details-value">
          <div>#2732026</div>
          <div>Takeout</div>
          <div>Ongoing</div>
          <div>3</div>
          <div>20 minutes</div>
          <div>3/27/2026</div>
          <div>13:40 NPT</div>
          <div>Main Canteen</div>
        </div>
      </div>
    </div>
      
       <!-- Right section order item + additional note + buttons -->
    <div class="order-right">

      <div class="order-items">
        <div class="order-items-title">Order Items</div>
        <table class="items-table">
          <thead>
            <tr>
              <th>S.N.</th>
              <th>Order Item</th>
              <th>Quantity</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>Potato Wedges</td>
              <td>2</td>
              <td><div class="checkbox"></div></td>
            </tr>
            <tr>
              <td>2</td>
              <td>Veg Thukpa</td>
              <td>1</td>
              <td><div class="checkbox"></div></td>
            </tr>
          </tbody>
        </table>
      </div>
 
      <div class="additional-note">
        <div class="note-title">Additional Note</div>
        <div class="note-box">
          Can you please make the potato wedges less spicy for both quantities? And do add an extra sashe of ketchup?
        </div>
      </div>
      
     <div class="order-actions">
        <button class="btn-initiate">Initiate Order</button>
        <button class="btn-ready">Mark as Ready</button>
      </div> 
      
    </div>
</body>
</html>
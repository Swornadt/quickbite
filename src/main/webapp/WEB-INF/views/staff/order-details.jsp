<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/order-details.css" />
</head>
<body>
  
	<%@ include file="/WEB-INF/views/staff/kitchen-nav.jsp" %>
  
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
	        <div>Date:</div>
	        <div>Time:</div>
	        <div>Location:</div>
        </div>
        <div class="details-value">
          	<div>#${orderId}</div>
         	<div>${orderType}</div>
          	<div>${orderStatus}</div>
          	<div>${totalQty}</div>
          	<div>${orderDate}</div>
		  	<div>${orderTime}</div>
          	<div>${outletName}</div>
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
	          <c:forEach var="item" items="${order}" varStatus="loop">
	            <tr>
	              <td>${loop.count}</td>
	              <td>${item.itemName}</td>
	              <td>${item.itemQty}</td>
	              <td>
	                <form method="post" action="${pageContext.request.contextPath}/kitchen/${orderId}">
		                <input type="hidden" name="action" value="itemDone" />
		                <input type="hidden" name="itemId" value="${item.itemId}" />
		                <button type="submit" class="checkbox ${item.itemStatus eq 1 ? 'checked' : ''}" ${orderStatus eq 'Ongoing' ? '' : 'disabled'}>
    						${item.itemStatus eq 1 ? '✓' : ''}
						</button>

           	 		</form>
	              </td>
	            </tr>
	          </c:forEach>
	        </tbody>
	      </table>
      </div>
 
      <div class="additional-note">
        <div class="note-title">Additional Note</div>
        <div class="note-box">${orderNote}</div>
      </div>
      
      <c:if test="${error != null}">
    	<div class="error-msg">${error}</div>
	</c:if>
      
      <div class="order-actions">
      	<form method="post" action="${pageContext.request.contextPath}/kitchen/${orderId}">
         	<input type="hidden" name="action" value="initiate" />
        	<button type="submit" class="btn-initiate" ${orderStatus eq 'Pending' ? '' : 'disabled'}>
    			Initiate Order
			</button>
    	</form>
    	
    	<form method="post" action="${pageContext.request.contextPath}/kitchen/${orderId}">
        	<input type="hidden" name="action" value="ready" />
        	<button type="submit" class="btn-ready" ${orderStatus eq 'Ongoing' ? '' : 'disabled'}>
    			Mark as Ready
			</button>
    	</form>
	  </div>
      
    </div>
</body>
</html>
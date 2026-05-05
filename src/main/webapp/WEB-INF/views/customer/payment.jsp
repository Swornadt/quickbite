<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBite | Payment</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/payment.css">
</head>
<body>
    <%@ include file="../common/navbar1.jsp" %>

    <div class="main-container">
        <h1>Complete Payment Now!</h1>

        <div class="payment-container">
            
            <!-- QR Section -->
            <div class="qr-section">
                <div class="qr-box">
                    <img src="${pageContext.request.contextPath}/assets/qr.jpeg" 
                         alt="Payment QR Code">
                </div>
            </div>

            <!-- Order Details -->
            <div class="order-section">
                <div class="order-card">
                    <h2 class="section-title">Order Details</h2>
                    
                    <div class="detail-row">
                        <span class="label">Order ID:</span>
                        <strong>#2732026</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Order Type:</span>
                        <strong>Takeout</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Total Quantity:</span>
                        <strong>3</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Estimated Time:</span>
                        <strong>20 minutes</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Date:</span>
                        <strong>05/05/2026</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Time:</span>
                        <strong>13:40 NPT</strong>
                    </div>
                    <div class="detail-row">
                        <span class="label">Location:</span>
                        <strong>Main Canteen</strong>
                    </div>

                    <hr>

                    <h3 class="section-title">Ordered Items</h3>
                    <div class="item">
                        <span>1x Chicken Burger</span>
                        <strong>Rs. 280.00</strong>
                    </div>

                    <div class="total-row">
                        <span>SUB TOTAL</span>
                        <strong>Rs. 280.00</strong>
                    </div>
                    <div class="total-row">
                        <span>VAT</span>
                        <strong>included in price</strong>
                    </div>
                    
                    <div class="grand-total">
                        <span>GRAND TOTAL</span>
                        <strong>Rs. 280.00</strong>
                    </div>

                    <div class="buttons">
                        <button class="btn btn-back">← Go Back</button>
                        <button class="btn btn-complete">Complete! </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>
</body>
</html>
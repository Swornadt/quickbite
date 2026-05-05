<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>QuickBite | Payment</title>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/css/payment.css">
    </head>

    <body>
        <!-- Header -->
        <%@ include file="../common/navbar1.jsp" %>

            <div class="main-container">
                <h1>Complete Payment Now!</h1>

                <div class="payment-container">

                    <div class="qr-section">
                        <img src="${pageContext.request.contextPath}/assets/qr.jpeg" alt="Payment QR Code">
                    </div>

                    <div class="order-section">
                        <div class="order-card">
                            <h2 class="section-title">Order Details</h2>

                            <div class="detail-row">
                                <span class="label">Order ID:</span>
                                <span><strong>#2732026</strong></span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Order Type:</span>
                                <span>Takeout</span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Total Quantity:</span>
                                <span>3</span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Estimated Time:</span>
                                <span>20 minutes</span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Date:</span>
                                <span>05/05/2026</span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Time:</span>
                                <span>13:40 NPT</span>
                            </div>
                            <div class="detail-row">
                                <span class="label">Location:</span>
                                <span>Main Canteen</span>
                            </div>

                            <hr>

                            <h3 class="section-title" style="font-size:19px;">Ordered Items</h3>
                            <div class="item">
                                <span>1x Chicken Burger</span>
                                <span><strong>Rs. 280.00</strong></span>
                            </div>

                            <hr>

                            <div class="total-row">
                                <span class="label">SUB TOTAL</span>
                                <span><strong>Rs. 280.00</strong></span>
                            </div>
                            <div class="total-row">
                                <span class="label">VAT</span>
                                <span style="color:green;">included in price</span>
                            </div>
                            <div class="grand-total">
                                <span>GRAND TOTAL</span>
                                <span>Rs. 280.00</span>
                            </div>

                        </div>

                        <!-- Buttons -->
                        <div class="buttons">
                            <button class="btn btn-back">← Go Back</button>
                            <button class="btn btn-complete">Complete! </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <%@ include file="../common/footer.jsp" %>
    </body>

    </html>
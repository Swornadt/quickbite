<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>

            <html>

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Payment | QuickBite</title>
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
                    rel="stylesheet">
                <link rel="stylesheet" href="<%=request.getContextPath() %>/css/payment.css">
            </head>

            <body>
                <%@ include file="../common/navbar1.jsp" %>

                    <div class="main-container">
                        <h1>Complete Payment Now!</h1>

                        <div class="payment-container">
                            <div class="qr-column">
                                <div class="qr-section">
                                    <div class="qr-box">
                                        <img src="${pageContext.request.contextPath}/assets/qr.jpeg"
                                            alt="Payment QR Code">
                                    </div>
                                </div>

                                <div class="buttons-container">
                                    <a href="${pageContext.request.contextPath}/checkout" class="btn btn-back">← Go
                                        Back</a>
                                    <form action="${pageContext.request.contextPath}/payment" method="POST"
                                        style="flex:1;">
                                        <button type="submit" class="btn btn-complete">Complete ✓</button>
                                    </form>
                                </div>
                            </div>

                            <div class="order-section">
                                <div class="order-card">
                                    <h2 class="section-title">Order Details</h2>

                                    <div class="detail-row">
                                        <span class="label">Order ID:</span>
                                        <strong>#2732026</strong>
                                    </div>
                                    <div class="detail-row">
                                        <span class="label">Order Type:</span>
                                        <strong>${pending_type == 'later' ? 'Scheduled' : 'Takeout'}</strong>
                                    </div>
                                    <div class="detail-row">
                                        <span class="label">Total Quantity:</span>
                                        <c:set var="totalQty" value="0" />
                                        <c:forEach var="item" items="${flatCart}">
                                            <c:set var="totalQty" value="${totalQty + item.quantity}" />
                                        </c:forEach>
                                        <strong>${totalQty}</strong>
                                    </div>
                                    <div class="detail-row">
                                        <span class="label">Location:</span>
                                        <strong>Main Canteen</strong>
                                    </div>

                                    <c:if test="${pending_type == 'later'}">
                                        <div class="detail-row">
                                            <span class="label">Date:</span>
                                            <strong>${pending_date}</strong>
                                        </div>
                                        <div class="detail-row">
                                            <span class="label">Time:</span>
                                            <strong>${pending_slot} NPT</strong>
                                        </div>
                                    </c:if>

                                    <div class="items-list-container">
                                        <h3 class="items-title">Ordered Items</h3>
                                        <c:forEach var="item" items="${flatCart}">
                                            <div class="item-entry">
                                                <span>${item.quantity}x &nbsp; ${item.itemName}</span>
                                                <strong>Rs.
                                                    <fmt:formatNumber value="${item.totalPrice}" pattern="#,##0.00" />
                                                </strong>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <div class="price-details">
                                        <div class="total-row">
                                            <span>SUB TOTAL</span>
                                            <strong>Rs.
                                                <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" />
                                            </strong>
                                        </div>
                                        <div class="total-row">
                                            <span>VAT</span>
                                            <span class="muted">included in price</span>
                                        </div>
                                        <div class="grand-total">
                                            <span>GRAND TOTAL</span>
                                            Rs.
                                            <fmt:formatNumber value="${subtotal}" pattern="#,##0.00" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%@ include file="../common/footer.jsp" %>
            </body>

            </html>
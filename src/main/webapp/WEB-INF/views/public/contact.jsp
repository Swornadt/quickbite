<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Contact Us | QuickBite</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contact.css">
    </head>

    <body>

        <!-- Header -->
        <%@ include file="../common/navbar1.jsp" %>

            <div class="hero-image">
                <div class="hero-title">Contact Us</div>
            </div>

            <div class="contact-container">
                <h2>Need More Help?</h2>

                <div class="info">

                    <!-- Call Card -->
                    <div class="info-card">
                        <h2>Call us at</h2>
                        <div class="number">9765432108</div>
                        <div class="subtitle">
                            Available 7am–5pm, 6 days a week
                        </div>
                        <a href="#" class="btn">Call Us</a>
                    </div>

                    <!-- Email Card -->
                    <div class="info-card">
                        <h2>Send us an email</h2>
                        <div class="subtitle">
                            We’d love to hear from you
                        </div>
                        <a href="#" class="btn">Email Us</a>
                    </div>
                </div>
            </div>


            </div>

            <div class="map">
                <img src="${pageContext.request.contextPath}/assets/Location.jpg" alt="Islington College Location">
            </div>

            <!-- Footer -->
            <%@ include file="../common/footer.jsp" %>

    </body>

    </html>
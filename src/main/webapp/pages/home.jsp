<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="../css/home.css">
        <!-- <link rel="stylesheet" href="../css/faq.css"> -->
        <title>Home | QuickBite</title>
    </head>

    <body>
    <!-- Header -->
	<%@ include file="./navbar1.jsp" %>
	
	<!-- Hero Section -->
        <div class="banner">
            <div class="content">
                <span class="sub-heading">Today's Special</span>
                <h1>Mo:Mo thoughts?</h1>
                <h2 class="green-text">We are listening</h2>

                <p class="description">
                    Pre-order fresh momo from your campus canteens. Ready exactly when your break starts. No queues, no
                    waiting.
                </p>

                <div class="thumbnails">
                    <div class="active"> <img src="../assets/home/momo1.png" alt="Momo 1"> </div>
                    <div> <img src="../assets/home/momo2.png" alt="Momo 2"> </div>
                    <div> <img src="../assets/home/momo3.png" alt="Momo 3"> </div>
                    <div> <img src="../assets/home/momo4.png" alt="Momo 4"> </div>
                </div>

                <button class="order-btn">Order Now</button>
            </div>

            <div class="image-container">
                <img src="../assets/home/momo1.png" alt="Main Plate" class="main-img">
            </div>
        </div>

	<!-- FAQ Section -->
	<%@ include file="./faq.jsp" %>
	
	<!-- Contact Us Section -->
	<%@ include file="./contact-us.jsp" %>
        
    <!-- Footer Section -->
	<%@ include file="./footer.jsp" %>

        <script>
            const thumbnails = document.querySelectorAll('.thumbnails div');
            const mainImg = document.querySelector('.main-img');
            const container = document.querySelector('.image-container');

            thumbnails.forEach(thumb => {
                thumb.addEventListener('click', () => {
                    const currentActive = document.querySelector('.thumbnails .active');
                    if (thumb === currentActive) return;

                    const ghostImg = mainImg.cloneNode();
                    ghostImg.classList.add('main-img');
                    container.appendChild(ghostImg);

                    requestAnimationFrame(() => {
                        ghostImg.classList.add('exit-bottom');
                    });

                    mainImg.style.transition = 'none';
                    mainImg.classList.add('exit-top');
                    mainImg.classList.remove('exit-bottom');
                    mainImg.offsetHeight;

                    mainImg.src = thumb.querySelector('img').src;
                    currentActive.classList.remove('active');
                    thumb.classList.add('active');

                    requestAnimationFrame(() => {
                        mainImg.style.transition = 'transform 0.8s cubic-bezier(0.4, 0, 0.2, 1), opacity 0.5s ease';
                        mainImg.classList.remove('exit-top');
                    });

                    setTimeout(() => {
                        ghostImg.remove();
                    }, 800);
        </script>
    </body>

    </html>
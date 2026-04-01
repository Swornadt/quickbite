<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700&display=swap"
            rel="stylesheet">
        <link rel="stylesheet" href="../css/faq.css">
        <title>Home | QuickBite</title>
    </head>

    <body>
       
        <!-- FAQ Section-->
        <div class="faq-container">
            <div class="faq-title">Frequently Asked Questions</div>

            <div class="faq-item">
                <div class="questions">Can I order during my short 30-minute break?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    Yes! That's exactly why we built this. Most orders are ready within 10-15 minutes. Just select a
                    pickup
                    time that matches your class schedule.
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">How do I know when my order is ready?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    You can track your order live using the Track Order option.
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">Is there a minimum order value?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    No minimum order value for most canteens. You can order even a single item.
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">Are the prices the same as the canteen counter?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    Yes, the prices are the same as the physical canteen. No extra platform fees for students.
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">Does it work for all canteens at Islington College?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    Yes, currently we support all active canteens inside the campus, ensuring a seamless ordering
                    experience.
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">What if I have food allergies?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    Every item shows common allergens (like gluten, dairy, soy, mustard, etc). you can also write
                    special
                    requests while placing the oreder (eg. "no soy sauce" or "less spicy").
                </div>
            </div>

            <div class="faq-item">
                <div class="questions">Is my order guaranteed to be fresh?
                    <span class="arrow">▼</span>
                </div>
                <div class="answer">
                    Yes. Unlike ready-made food, your order is prepared only after you place it, so you always get hot
                    and
                    fresh food.
                </div>
            </div>
        </div>

        <script>
            const thumbnails = document.querySelectorAll('.thumbnails div');
            const mainImg = document.querySelector('.main-img');
            const container = document.querySelector('.image-container'); // Added this missing line

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
                });
            });

            <!-- FAQ Toggle-->
            const faqItems = document.querySelectorAll('.faq-item');

            faqItems.forEach(item => {
                const question = item.querySelector('.questions');
                const answer = item.querySelector('.answer');

                question.addEventListener('click', () => {
                    if (item.classList.contains('active')) {
                        // Closing
                        answer.style.maxHeight = answer.scrollHeight + "px";
                        setTimeout(() => {
                            answer.style.maxHeight = "0";
                            item.classList.remove('active');
                        }, 10);
                    } else {
                        // Opening
                        item.classList.add('active');
                        answer.style.maxHeight = answer.scrollHeight + "px";
                    }
                });
            });
        </script>
    </body>

    </html>
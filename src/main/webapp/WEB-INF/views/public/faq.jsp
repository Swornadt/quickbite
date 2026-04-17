<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ | QuickBite</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/faq.css">
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
	                answer.style.maxHeight = 500 + "px";
	            }
	        });
	    });
	</script>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBite | About Us</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/about-page.css">
</head>
<body>

<!-- HEADER -->
<%@ include file="../common/navbar1.jsp" %>

<main class="page-wrap">

<!-- TEAM -->
<section class="team-section">
    <div class="team-image-box">
        <img src="assets/about/team.png">
    </div>

    <div class="team-content">
        <h2>Our Team</h2>
        <p>
            We are a group of passionate and dedicated students who came together 
            to solve long canteen queues and make QuickBite fast and easy.
        </p>
    </div>
</section>

<section class="members-section">
    <h2>Members</h2>

    <div class="members-wrapper">
        <div class="members-row" id="slider">

            <a href="about/hridishna">
                <div class="member-card">
                    <img src="hridishna.jpg">
                    <div class="member-name">Hridishna Deula</div>
                </div>
            </a>

            <a href="about/resha">
                <div class="member-card">
                    <img src="resha.jpg">
                    <div class="member-name">Resha Koju</div>
                </div>
            </a>

            <a href="about/sabrina">
                <div class="member-card">
                    <img src="sabrina.jpg">
                    <div class="member-name">Sabrina Pradhan</div>
                </div>
            </a>

            <a href="about/sworna">
                <div class="member-card">
                    <img src="sworna.jpg">
                    <div class="member-name">Sworna D Tuladhar</div>
                </div>
            </a>

            <a href="about/sahil">
                <div class="member-card">
                    <img src="sahil.jpg">
                    <div class="member-name">Sahil Shrestha</div>
                </div>
            </a>

            <a href="about/sanskar">
                <div class="member-card">
                    <img src="sanskar.jpg">
                    <div class="member-name">Sanskar Piya</div>
                </div>
            </a>

        </div>
    </div>
</section>

</main>

<!-- FOOTER -->
<%@ include file="../common/footer.jsp" %>

<script>
const slider = document.getElementById("slider");

// duplicate cards for smooth infinite effect
slider.innerHTML += slider.innerHTML;
</script>

</body>
</html>
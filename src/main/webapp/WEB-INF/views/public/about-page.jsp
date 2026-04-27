<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickBite | About Us</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background: #ffffff;
            color: #111;
        }

        a {
            text-decoration: none;
            color: inherit;
        }

        /* MAIN */
        .page-wrap {
            max-width: 1200px;
            margin: auto;
            padding: 40px;
        }

        /* TEAM */
        .team-section {
            display: grid;
            grid-template-columns: 300px 1fr;
            gap: 50px;
            margin-bottom: 60px;
        }

        .team-image-box {
            width: 100%;
            height: 350px;
            border: 2px solid #4da8ff;
            border-radius: 10px;
            overflow: hidden;
        }

        .team-image-box img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .team-content {
            text-align: center;
        }

        .team-content h2 {
            font-size: 40px;
            margin-bottom: 15px;
        }

        /* MEMBERS */
        .members-section h2 {
            font-size: 35px;
            margin-bottom: 20px;
        }

        .members-wrapper {
            overflow: hidden;
            position: relative;
        }

        .members-row {
            display: flex;
            gap: 20px;
            width: max-content;
            animation: scroll 15s linear infinite;
        }

        .member-card {
            min-width: 200px;
            height: 260px;
            position: relative;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            flex-shrink: 0;
        }

        .member-card img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .member-name {
            position: absolute;
            bottom: 0;
            width: 100%;
            text-align: center;
            color: white;
            font-weight: bold;
            padding: 10px;
            background: rgba(0,0,0,0.6);
        }

        @keyframes scroll {
            0% {
                transform: translateX(0);
            }
            100% {
                transform: translateX(-50%);
            }
        }
    </style>
</head>

<body>

<!-- HEADER -->
<%@ include file="../common/navbar1.jsp" %>

<main class="page-wrap">

<!-- TEAM -->
<section class="team-section">
    <div class="team-image-box">
        <img src="team.png">
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

<!-- JS FOR SMOOTH LOOP -->
<script>
const slider = document.getElementById("slider");

// duplicate cards for smooth infinite effect
slider.innerHTML += slider.innerHTML;
</script>

</body>
</html>
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

        /* NAVBAR */
        .navbar {
            width: 100%;
            background: #ffffff;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 14px 45px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .logo img {
            width: 110px;
        }

        .nav-links {
            display: flex;
            gap: 30px;
        }

        .nav-links a:hover {
            color: #e86a1d;
        }

        .login-btn {
            background: #e86a1d;
            color: #fff;
            padding: 10px 20px;
            border-radius: 6px;
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
            min-width: 300px;
            height: 360px;
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

        /* FOOTER */
        .footer {
            background: #ffffff;
            margin-top: 60px;
            padding: 50px 40px 20px;
            border-top: 1px solid #eee;
        }

        .footer-top {
            display: flex;
            justify-content: space-between;
        }

        .footer-logo img {
            width: 250px;
        }

        .footer-links {
            display: flex;
            gap: 80px;
        }

        .footer-col a {
            display: block;
            font-size: 14px;
            color: #555;
            margin-bottom: 6px;
        }

        .footer-bottom {
            text-align: center;
            font-size: 11px;
            margin-top: 40px;
            color: #666;
        }
    </style>
</head>

<body>

<header class="navbar">
    <a href="index.html" class="logo">
        <img src="logo.png">
    </a>

    <nav class="nav-links">
        <a href="index.html">Home</a>
        <a href="#">Menu</a>
        <a href="about-final.html">About Us</a>
        <a href="#">Contact</a>
    </nav>

    <a href="#" class="login-btn">Login</a>
</header>

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

            <a href="hridishna.html">
                <div class="member-card">
                    <img src="hridishna.jpg">
                    <div class="member-name">Hridishna Deula</div>
                </div>
            </a>

            <a href="resha.html">
                <div class="member-card">
                    <img src="resha.jpg">
                    <div class="member-name">Resha Koju</div>
                </div>
            </a>

            <a href="sabrina.html">
                <div class="member-card">
                    <img src="sabrina.jpg">
                    <div class="member-name">Sabrina Pradhan</div>
                </div>
            </a>

            <a href="sworna.html">
                <div class="member-card">
                    <img src="sworna.jpg">
                    <div class="member-name">Sworna D Tuladhar</div>
                </div>
            </a>

            <a href="sahil.html">
                <div class="member-card">
                    <img src="sahil.jpg">
                    <div class="member-name">Sahil Shrestha</div>
                </div>
            </a>

            <a href="sanskar.html">
                <div class="member-card">
                    <img src="sanskar.jpg">
                    <div class="member-name">Sanskar Piya</div>
                </div>
            </a>

        </div>
    </div>
</section>

        </div>
    </div>
</section>

</main>

<!-- FOOTER -->
<footer class="footer">
    <div class="footer-top">
        <div class="footer-logo">
            <img src="footer.png">
        </div>

        <div class="footer-links">
            <div class="footer-col">
                <h4>Quick Links</h4>
                <a href="index.html">Home</a>
                <a href="#">Menu</a>
                <a href="about-final.html">About Us</a>
                <a href="#">Contact Us</a>
                <a href="#">Location</a>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        © 2026 QuickBite. All Rights Reserved.
    </div>
</footer>

<script>
const slider = document.getElementById("slider");

// duplicate cards for smooth infinite effect
slider.innerHTML += slider.innerHTML;
</script>

</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>404 - Page Not Found | QuickBite</title>

  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&family=Lobster&display=swap" rel="stylesheet">

  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
    }

    body {
      font-family: 'Poppins', sans-serif;
      background: #ffffff;
      color: #111;
    }

    /* HEADER */
    .header {
      width: 100%;
      height: 78px;
      background: white;
      border-bottom: 1px solid #e5d7d0;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 45px;
    }

    /* LOGO */
    .logo-box {
  width: 95px;
  height: 42px;
  border-radius: 12px;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

.logo-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

    .logo-box span {
      color: white;
      font-family: 'Lobster', cursive;
      font-size: 26px;
      line-height: 0.9;
      text-align: center;
    }

    /* NAV */
    .nav {
      display: flex;
      align-items: center;
      gap: 38px;
    }

    .nav a {
      text-decoration: none;
      color: #222;
      font-size: 18px;
      font-weight: 400;
    }

    .icon {
      width: 24px;
      height: 24px;
      cursor: pointer;
    }

    .profile {
      width: 42px;
      height: 42px;
      border-radius: 50%;
      object-fit: cover;
    }

    /* MAIN SECTION */
    .main {
      min-height: 500px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-align: center;
      padding: 60px 20px;
    }

    .oops {
      font-size: 88px;
      font-weight: 800;
      color: #DB4A1E;
      margin-bottom: 15px;
    }

    .error-title {
      font-size: 30px;
      font-weight: 700;
      margin-bottom: 18px;
    }

    .error-text {
      font-size: 18px;
      font-weight: 400;
      max-width: 650px;
      color: #222;
      line-height: 1.6;
      margin-bottom: 30px;
    }

    .home-btn {
      width: 210px;
      height: 52px;
      background: #DB4A1E;
      color: white;
      border: none;
      border-radius: 5px;
      font-size: 15px;
      font-weight: 500;
      cursor: pointer;
      transition: 0.3s;
    }

    .home-btn:hover {
      background: #c43f17;
    }

    /* FOOTER */
    .footer {
      width: 100%;
      border-top: 1px solid #e5d7d0;
      padding: 55px 50px 20px;
      background: #ffffff;
    }

    .footer-top {
      display: flex;
      justify-content: space-between;
      align-items: flex-start;
      margin-bottom: 50px;
    }

    .footer-logo {
  width: 393px;
  height: 126px;
  margin-left: 68px;
  margin-top: 105px;
  border-radius: 20px;
  overflow: hidden;
}

.footer-logo-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 20px;
}
    .footer-links {
      display: flex;
      gap: 70px;
    }

    .footer-column h4 {
      font-size: 18px;
      font-weight: 600;
      margin-bottom: 12px;
    }

    .footer-column ul {
      list-style: none;
    }

    .footer-column li {
      margin-bottom: 10px;
      color: #555;
      font-size: 15px;
      cursor: pointer;
    }

    .footer-bottom {
      text-align: center;
      font-size: 11px;
      color: #777;
      padding-top: 20px;
    }

    /* RESPONSIVE */
    @media (max-width: 900px) {
      .footer-top {
        flex-direction: column;
        gap: 40px;
      }

      .footer-links {
        flex-direction: column;
        gap: 30px;
      }

      .nav {
        gap: 20px;
      }

      .oops {
        font-size: 60px;
      }

      .footer-logo {
        font-size: 60px;
      }
    }
  </style>
</head>
<body>

  <!-- HEADER -->
  <header class="header">
    <div class="logo-box">
  <img src="quickbite.jpg" alt="QuickBite Logo" class="logo-img">
</div>

    <div class="nav">
      <a href="#">Menu</a>
      <a href="#">Contact Us</a>

      <!-- Notification -->
      <svg class="icon" viewBox="0 0 24 24" fill="black">
        <path d="M12 2C10.3 2 9 3.3 9 5V6.3C6.7 7.2 5 9.4 5 12V17L3 19V20H21V19L19 17V12C19 9.4 17.3 7.2 15 6.3V5C15 3.3 13.7 2 12 2ZM12 23C13.1 23 14 22.1 14 21H10C10 22.1 10.9 23 12 23Z"/>
      </svg>

      <!-- Cart -->
      <svg class="icon" viewBox="0 0 24 24" fill="black">
        <path d="M7 18C5.9 18 5 18.9 5 20S5.9 22 7 22 9 21.1 9 20 8.1 18 7 18ZM17 18C15.9 18 15 18.9 15 20S15.9 22 17 22 19 21.1 19 20 18.1 18 17 18ZM7.2 14H17.5C18.3 14 19 13.5 19.3 12.8L22 7H6.2L5.3 5H2V7H4L7.6 14.6L6.2 17C6.1 17.3 6 17.6 6 18H20V16H7L7.2 14Z"/>
      </svg>

      <img src="error.jpg" alt="Profile" class="profile">
    </div>
  </header>

  <!-- MAIN -->
  <section class="main">
    <div class="oops">OOPS!</div>

    <div class="error-title">404 - PAGE NOT FOUND</div>

    <div class="error-text">
      The page that you are looking for might have been removed,<br>
      had it’s name changed or temporarily unavailable.
    </div>

    <button class="home-btn" onclick="window.location.href='index.jsp'">
      GO TO HOMEPAGE
    </button>
  </section>

  <!-- FOOTER -->
  <footer class="footer">

    <div class="footer-top">

      <div class="footer-logo">
  <img src="footer.png" alt="QuickBite Footer Logo" class="footer-logo-img">
</div>

      <div class="footer-links">

        <div class="footer-column">
          <h4>Quick Links</h4>
          <ul>
            <li>Home</li>
            <li>Menu</li>
            <li>About Us</li>
            <li>Contact Us</li>
            <li>Location</li>
          </ul>
        </div>

        <div class="footer-column">
          <h4>Location</h4>
          <ul>
            <li>Coffee Station</li>
            <li>Momo Station</li>
            <li>Cafeteria</li>
            <li>Chautari</li>
            <li>Brit Cafe</li>
            <li>Kumari Hall</li>
          </ul>
        </div>

      </div>

    </div>

    <div class="footer-bottom">
      Copyright © 2026 QuickBite. All Rights Reserved. Accessibility, User Agreement,
      Privacy, Consumer Health Data, Payments Terms of Use, Cookies, CA Privacy Notice.
    </div>

  </footer>

</body>
</html>
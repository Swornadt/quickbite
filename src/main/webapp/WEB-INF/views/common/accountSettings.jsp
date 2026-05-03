<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Account Settings</title>

  <link rel="stylesheet" href="accountSettings.css">

  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>

  <header class="header">
    <div class="logo-box">
      <img src="quickbite.jpg" alt="QuickBite Logo" class="logo-img">
    </div>

<nav class="nav">
  <a href="menu.jsp">Menu</a>
  <a href="contact.jsp">Contact Us</a>

  <a href="notifications.jsp">
    <svg class="icon" viewBox="0 0 24 24" fill="black">
      <path d="M12 2C10.3 2 9 3.3 9 5V6.3C6.7 7.2 5 9.4 5 12V17L3 19V20H21V19L19 17V12C19 9.4 17.3 7.2 15 6.3V5C15 3.3 13.7 2 12 2ZM12 23C13.1 23 14 22.1 14 21H10C10 22.1 10.9 23 12 23Z"/>
    </svg>
  </a>

  <a href="cart.jsp">
    <svg class="icon" viewBox="0 0 24 24" fill="black">
      <path d="M7 18C5.9 18 5 18.9 5 20S5.9 22 7 22 9 21.1 9 20 8.1 18 7 18ZM17 18C15.9 18 15 18.9 15 20S15.9 22 17 22 19 21.1 19 20 18.1 18 17 18ZM7.2 14H17.5C18.3 14 19 13.5 19.3 12.8L22 7H6.2L5.3 5H2V7H4L7.6 14.6L6.2 17C6.1 17.3 6 17.6 6 18H20V16H7L7.2 14Z"/>
    </svg>
  </a>

  <a href="accountSettings.jsp">
    <img src="error.jpg" alt="Profile" class="profile">
  </a>
</nav>

  </header>

  <jsp:include page="utilityBar.jsp" />

  <div class="main-content">

    <jsp:include page="userProfileSideNav.jsp" />

    <div class="password-box">
      <label>OLD PASSWORD</label>
      <input type="password" placeholder="Old Password">

      <label>NEW PASSWORD</label>
      <input type="password" placeholder="New Password">

      <label>CONFIRM NEW PASSWORD</label>
      <input type="password" placeholder="Re-write New Password">

<div class="btn-row">
  <button class="save-btn" onclick="savePassword()">SAVE</button>
  <button class="cancel-btn" onclick="clearFields()">CANCEL</button>
</div>
    </div>

  </div>

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
      Copyright © 2026 QuickBite. All Rights Reserved. Accessibility, User Agreement, Privacy, Consumer Health Data, Payments Terms of Use, Cookies, CA Privacy Notice,
    </div>

  </footer>

<script>
function savePassword() {
  const inputs = document.querySelectorAll('.password-box input');
  const oldPass = inputs[0].value;
  const newPass = inputs[1].value;
  const confirmPass = inputs[2].value;

  if (!oldPass || !newPass || !confirmPass) {
    alert("Please fill all password fields.");
    return;
  }

  if (newPass !== confirmPass) {
    alert("New passwords do not match.");
    return;
  }

  alert("Password updated successfully!");
}

function clearFields() {
  document.querySelectorAll('.password-box input').forEach(input => {
    input.value = "";
  });
}
</script>
</body>
</html>
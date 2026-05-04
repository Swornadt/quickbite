<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>404 - Page Not Found | QuickBite</title>
  
  <link rel="stylesheet" href="<%=request.getContextPath() %>/css/404.css">
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800&family=Lobster&display=swap" rel="stylesheet">
  
</head>
<body>

  <!-- HEADER -->
  <%@ include file="../common/navbar1.jsp" %>

  <!-- MAIN -->
  <section class="main">
    <div class="oops">OOPS!</div>

    <div class="error-title">404 - PAGE NOT FOUND</div>

    <div class="error-text">
      The page that you are looking for might have been removed,<br>
      had it's name changed or temporarily unavailable.
    </div>

    <a href="<%=request.getContextPath()%>/home">
    <button class="home-btn">
      GO TO HOMEPAGE
    </button>
    </a>
  </section>

  <!-- FOOTER -->
  <%@ include file="../common/footer.jsp" %>

</body>
</html>
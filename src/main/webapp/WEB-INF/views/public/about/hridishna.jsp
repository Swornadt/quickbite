<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!doctype html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hridishna Deula | CV</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about-individual.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,opsz,wght@0,9..40,100..1000;1,9..40,100..1000&family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&family=Montserrat:ital,wght@0,100..900;1,100..900&family=Pacifico&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
      integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />
  </head>

  <body>
    <div class="header">
      <div class="back-btn">
        <a href="${pageContext.request.contextPath}/about">Back to About Us</a>
      </div>

      <div class="image-container">
        <img src="${pageContext.request.contextPath}/assets/about/hridishna.jpg" class="user-image" />
      </div>

      <div class="user-bio">
        <h1 class="user-bio-heading">Hridishna Deula</h1>
        <p class="user-bio-role">
          Student at Islington College Kathmandu
        </p>
        <p class="user-bio-content">
          Motivated computing student learning IT fundamentals and web development.
        </p>
        <div class="user-socials">
          <a href="https://github.com/Ridisha88" class="user-social-link" target="_blank"><i
              class="fa-brands fa-github"></i>
            <p>Github</p>
          </a>
          <a href="https://www.linkedin.com/in/hridishna-deula-707441404/" class="user-social-link" target="_blank"><i
              class="fa-brands fa-linkedin"></i>
            <p>LinkedIn</p>
          </a>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="grid">
        <div class="column-grid first-column">
          <!-- About Me -->
          <div class="grid-card">
            <h2>About Me</h2>
            <p class="user-aboutme-content">
              <span>I am a Computing student currently building my skills in web development and programming. Alongside
                my studies, I have experience working in sales and quotation development, which has improved my
                communication, problem-solving, and attention to detail. I am motivated to grow in the IT field and gain
                practical experience through real-world projects and opportunities.</span>
            </p>
          </div>

          <!-- Skills Section  -->
          <div class="grid-card">
            <h2>Technical Skills</h2>
            <div class="skills">
              <a class="user-skill">HTML</a>
              <a class="user-skill">Python</a>
              <a class="user-skill">CSS</a>
              <a class="user-skill">JavaScript (Basic)</a>
              <a class="user-skill">VS Code</a>
              <a class="user-skill">GitHub</a>
            </div>
          </div>
        </div>

        <div class="column-grid second-column">
          <div class="grid-card">
            <h2>Quick Info</h2>
            <p>Kathmandu, Nepal</p>
            <p>Working as a Student</p>
            <p>Hobbies: Discussing about Linux</p>
          </div>

          <!-- Position of Responsibility -->
          <div class="grid-card achievement-section">
            <h2>Achievements & Certifications</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">
                  &starf; Created Basic Websites
                </p>
                <p id="achievement-description">
                  College coursework projects
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

  </html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sabrina Pradhan | CV</title>
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
        <img src="${pageContext.request.contextPath}/assets/about/sabrina.jpg" class="user-image" />
      </div>

      <div class="user-bio">
        <h1 class="user-bio-heading">Sabrina Pradhan</h1>
        <p class="user-bio-role">
          BSc Computing Student · Aspiring Software Engineer
        </p>
        <p class="user-bio-content">
          Motivated computing student at Islington College learning IT fundamentals and web development.
          Looking for opportunities to grow and gain experience.
        </p>
        <div class="user-socials">
          <a href="https://github.com/Gladiolus-bat" class="user-social-link" target="_blank"><i
              class="fa-brands fa-github"></i>
            <p>Github</p>
          </a>
          <a href="https://www.linkedin.com/in/sabrina-pradhan-892a15319/" class="user-social-link" target="_blank"><i
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
              <span>I am a passionate and versatile full stack developer with a strong foundation in UI/UX design,
                frontend development, and backend systems. I enjoy building complete digital experiences - from
                designing intuitive and visually engaging interfaces to developing efficient, scalable, and secure
                backend solutions.</span>
              <span>
                My approach focuses on understanding user needs and translating them into functional, user-friendly
                applications. I believe that great software is not just about clean code, but also about delivering
                seamless experiences that are both practical and visually appealing.</span>
              <span>
                I have experience working with modern design tools and web technologies, allowing me to bridge the gap
                between design and development. I am comfortable creating wireframes and prototypes, developing
                responsive interfaces, and building robust APIs and database systems that support real-world
                applications.</span>

              <span>I am continuously learning and exploring new technologies to improve my skills and stay updated with
                industry trends. I enjoy problem-solving, collaborating with teams, and taking on challenges that help
                me grow both technically and creatively.</span>

              <span>My goal is to contribute to impactful projects where I can combine creativity with technical
                expertise to build meaningful and efficient digital solutions.</span>

          </div>

          <!-- Skills Section  -->
          <div class="grid-card">
            <h2>Technical Skills</h2>
            <div class="skills">
              <a class="user-skill">JavaScript</a>
              <a class="user-skill">Python</a>
              <a class="user-skill">Java</a>
              <a class="user-skill">HTML</a>
              <a class="user-skill">CSS</a>
              <a class="user-skill">Figma</a>
              <a class="user-skill">SQL</a>
              <a class="user-skill">C++</a>
            </div>
          </div>
        </div>

        <div class="column-grid second-column">
          <div class="grid-card">
            <h2>Quick Info</h2>
            <p>Lalitpur, Nepal</p>
            <p>Working as a Student</p>
            <p>Hobbies: Drawing, Swimming, Playing Badminton</p>
          </div>

          <!-- Position of Responsibility -->
          <div class="grid-card achievement-section">
            <h2>Achievements & Certifications</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">
                  &starf; 1st Place: Anugami Event, Airo Community
                </p>
                <p id="achievement-description">
                  Winner of the line following robot making competition organised by Airo Community.
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">
                  &starf; Best Mascot Design: Animanga Community
                </p>
                <p id="achievement-description">
                  Mascot design selected by the Animanga Community to represent them.
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">
                  &starf; 1st Runner-Up: “Build the Mini Web” Event, WebDev Community
                </p>
                <p id="achievement-description">
                  Runner up of the website making competition organised by Web Dev Community.
                </p>
              </div>
            </div>
          </div>
          <div class="grid-card achievement-section">
            <h2>Position of Responsibility</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">Web Developer</p>
                <p>2025</p>
                <p id="achievement-description">
                  Xiangtan Xuge Commerce, China
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

  </html>
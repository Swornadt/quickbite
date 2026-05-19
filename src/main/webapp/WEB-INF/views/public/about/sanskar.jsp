<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!doctype html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sanskar Piya | CV</title>
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
        <img src="${pageContext.request.contextPath}/assets/about/sanskar.jpg" class="user-image" />
      </div>

      <div class="user-bio">
        <h1 class="user-bio-heading">Sanskar Piya</h1>
        <p class="user-bio-role">
          BSc Computing Student · Aspiring Software Engineer
        </p>
        <p class="user-bio-content">
          Computing student at Islington College with a passion for Software
          Engineering, community leadership, and building things that matter.
        </p>
        <div class="user-socials">
          <a href="https://github.com/SanskarPiya" class="user-social-link" target="_blank"><i
              class="fa-brands fa-github"></i>
            <p>Github</p>
          </a>
          <a href="https://www.linkedin.com/in/sanskarpiya/" class="user-social-link" target="_blank"><i
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
              <span>console.log("Hello, World!") </span>
              <span>I currently serve as the Head of Operations for Islington Web
                Dev Community, overseeing the logistics and coordination
                necessary to ensure a smooth flow of events.</span>
              <span>
                I am also improving my skills in web development, at present
                more focusing on backend development in Node.js and
                Express.js</span>
              <span>
                I have also served as the President of Interact Club of
                Balkumari English School (RY 2021-22), where i honed my
                communication skills and my ability to collaborate, inspire and
                lead effectively.</span>
              <span>Aspiring for a challenging career in Software Engineering as to
                use my learned skills and experience to create meaningful and
                long-lasting results.</span>
              <span> Let’s connect and grow together!</span>
            </p>
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
              <a class="user-skill">Node.js</a>
              <a class="user-skill">Express.js</a>
            </div>
          </div>

          <!-- Projects Section -->
          <div class="grid-card">
            <h2>Projects</h2>

            <div class="project">
              <div class="project-header">
                <span class="project-name">Web Scraper</span>
                <a href="https://github.com/SanskarPiya/web-scraper" target="_blank" class="project-link">View →</a>
              </div>
              <p class="project-desc">
                A scraping tool that takes a URL and CSS selector as input and
                extracts matching elements in a readable format.
              </p>
              <div class="project-tags">
                <span class="tag">Node.js</span>
                <span class="tag">Express.js</span>
                <span class="tag">JavaScript</span>
              </div>
            </div>

            <div class="project">
              <div class="project-header">
                <span class="project-name">Spotify Clone</span>
                <a href="https://github.com/SanskarPiya/Spotify" target="_blank" class="project-link">View →</a>
              </div>
              <p class="project-desc">
                A pixel-faithful frontend recreation of the Spotify UI, built
                purely with HTML and CSS.
              </p>
              <div class="project-tags">
                <span class="tag">HTML</span>
                <span class="tag">CSS</span>
              </div>
            </div>

            <div class="project">
              <div class="project-header">
                <span class="project-name">Figma Portfolio Site</span>
                <a href="https://www.figma.com" target="_blank" class="project-link">View →</a>
              </div>
              <p class="project-desc">
                A personal portfolio site designed in Figma using original ideas
                and multiple design references.
              </p>
              <div class="project-tags">
                <span class="tag">Figma</span>
                <span class="tag">UI/UX</span>
              </div>
            </div>
          </div>
        </div>

        <div class="column-grid second-column">
          <div class="grid-card">
            <h2>Quick Info</h2>
            <p>Kathmandu, Nepal</p>
            <p>Working as a Student</p>
            <p>Hobbies: Guitar, Swimming, Singing</p>
          </div>

          <!-- Position of Responsibility -->
          <div class="grid-card achievement-section">
            <h2>Achievements & Certifications</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">
                  &starf; Top 10 Figma UI/UX Portfolio Showcase
                </p>
                <p id="achievement-description">
                  Selected among top presenters for excellence in design
                  thinking and prototyping
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">
                  &starf; Github & Figma Certification
                </p>
                <p id="achievement-description">
                  Completed a 10-week industry enrichment programme covering
                  UI/UX and version control.
                </p>
              </div>
            </div>
          </div>

          <div class="grid-card achievement-section">
            <h2>Position of Responsibility</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">Head of Operation</p>
                <p>2025 - Present</p>
                <p id="achievement-description">
                  Managing Club Logistics, registration, timelines, and event
                  coordination
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">President</p>
                <p>2021 - 2022</p>
                <p id="achievement-description">
                  Led an executive team of 15+ members in community service
                  initiatives.
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">Treasurer</p>
                <p>2020 - 2021</p>
                <p id="achievement-description">
                  Managed financial records and budgets for club event and
                  fundraisers.
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

  </html>
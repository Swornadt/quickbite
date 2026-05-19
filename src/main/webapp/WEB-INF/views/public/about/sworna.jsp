<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <!doctype html>
  <html lang="en">

  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sworna Dhan Tuladhar | CV</title>
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
        <img src="${pageContext.request.contextPath}/assets/about/sworna.jpg" class="user-image" />
      </div>

      <div class="user-bio">
        <h1 class="user-bio-heading">Sworna Dhan Tuladhar</h1>
        <p class="user-bio-role">
          Computing Undergraduate
        </p>
        <p class="user-bio-content">
          Computing student at Islington College with a passion for Software
          Engineering, community leadership, and building things that matter.
        </p>
        <div class="user-socials">
          <a href="https://github.com/Swornadt" class="user-social-link" target="_blank"><i
              class="fa-brands fa-github"></i>
            <p>Github</p>
          </a>
          <a href="https://www.linkedin.com/in/sworna-d-tuladhar-a96315311/" class="user-social-link" target="_blank"><i
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
              <span>Hello, person from the internet!! I'm a passionate programmer working towards Full-Stack Web
                Development, specializing in the MERN Stack Currently serving as the founder and Community Lead of
                Islington WebDev Community. I gravitate toward full-stack work because I believe the best technical
                decisions come from understanding the whole system. Whether it's choosing a database schema, designing
                an API contract, or structuring a component tree, I aim for decisions that remain sound six months
                later.</span>
              <span>
                What's my specialty? In my tech journey so far, I've worked mostly with: JavaScript, TypeScript,
                Express.js, Node.js, React.js, Next.js, Java, Python.</span>
              <span>
                What am I building? I take a research-driven and project-based approach; Currently, I'm exploring AI
                integration via APIs (REST and GraphQL specifically) to enhance web development for future
                projects.</span>
              <span>Comments beyond the code While I'm not debugging a simple issue that I created myself, you'll find
                me deep into Model United Nations (MUNs) discussing the latest geopolitics and economics to conflict
                resolution and problem-solving, or perhaps at the piano - composing and playing Western classical
                music.</span>
              <span>Always excited to connect with fellow developers, collaborate on projects or just have a
                philosophical chat on life over a cup of chai Let's level up together!</span>
              <span>Personal Portfolio: <a href="https://sworna-tuladhar.vercel.app/"
                  target="_blank">https://sworna-tuladhar.vercel.app</a></span>
            </p>
          </div>

          <!-- Skills Section  -->
          <div class="grid-card">
            <h2>Technical Skills</h2>
            <div class="skills">
              <a class="user-skill">MERN stack</a>
              <a class="user-skill">Python</a>
              <a class="user-skill">Project Management</a>
              <a class="user-skill">System Design</a>
              <a class="user-skill">TypeScript</a>
              <a class="user-skill">PostgreSQL</a>
              <a class="user-skill">API Design</a>
              <a class="user-skill">Docker</a>
              <a class="user-skill">Canva</a>
              <a class="user-skill">Figma</a>
            </div>
          </div>
        </div>

        <div class="column-grid second-column">
          <div class="grid-card">
            <h2>Quick Info</h2>
            <p>Kathmandu, Nepal</p>
            <p>Working as a Student</p>
            <p>Hobbies: Piano, Guitar, Singing</p>
          </div>

          <!-- Position of Responsibility -->
          <div class="grid-card achievement-section">
            <h2>Achievements & Certifications</h2>
            <div class="all-achievements">
              <div class="achieve">
                <p id="achievement-title">
                  &starf; Leapfrog Student Partner 2026 Cohort V
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
                <p id="achievement-title">Community Lead</p>
                <p>2025 - Present</p>
                <p id="achievement-description">
                  Islington WebDev Community
                </p>
              </div>
              <div class="achieve">
                <p id="achievement-title">Graphic Design IT Intern</p>
                <p>2021 - 2022</p>
                <p id="achievement-description">
                  Global Help Foundation
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>

  </html>
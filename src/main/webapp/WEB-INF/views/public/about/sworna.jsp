<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Sworna D Tuladhar | CV</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet">

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Inter', Arial, sans-serif;
    background: #f0f2f5;
    padding: 40px 20px;
    font-size: 18px;
    line-height: 1.7;
    color: black;
}

.cv-card {
    max-width: 1440px;
    margin: 0 auto;
    background: white;
    border-radius: 20px;
    overflow: hidden;
    box-shadow: 0 10px 40px rgba(0,0,0,0.1);
}

.header {
    position: relative;
    width: 1440px;
    height: 515px;
    background-image: url('header.jpg');
    background-size: cover;
    background-position: center;
}

.header::before {
    content: '';
    position: absolute;
    inset: 0;
    background: rgba(0,0,0,0.35);
}

.back-btn {
    position: absolute;
    top: 30px;
    left: 40px;
    z-index: 10;
}

.back-btn a {
    background: rgba(255,255,255,0.4);
    color: black;
    padding: 12px 24px;
    border-radius: 40px;
    text-decoration: none;
    font-weight: 600;
    font-size: 16px;
}

.header-content {
    position: relative;
    z-index: 2;
    display: flex;
    align-items: center;
    gap: 60px;
    padding: 80px 100px;
    height: 100%;
}

.profile-img {
    width: 340px;
    height: 320px;
    border-radius: 20px;
    border: 4px solid white;
    object-fit: cover;
}

.header-info {
    color: black;
    display: flex;
    flex-direction: column;
    gap: 14px;
    max-width: 520px;
}

.header-info h1 {
    font-size: 50px;
    font-weight: 700;
}

.header-info .title {
    font-size: 24px;
}

.header-info .desc {
    font-size: 18px;
}

.social-icons {
    display: flex;
    gap: 15px;
    margin-top: 10px;
}

.social-icons a {
    width: 45px;
    height: 45px;
    background: rgba(255,255,255,0.5);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    color: black;
    font-size: 18px;
}

.content {
    max-width: 1140px;
    margin: 60px auto;
    padding: 50px 20px;
}

.two-columns {
    display: flex;
    gap: 50px;
}

.left-col {
    flex: 2;
}

.right-col {
    flex: 1;
}

.section {
    margin-bottom: 50px;
}

.section h2 {
    font-size: 32px;
    font-weight: 700;
    color: black;
    border-left: 6px solid black;
    padding-left: 14px;
    margin-bottom: 20px;
}

.about-text {
    font-size: 18px;
    line-height: 1.9;
}

.skill-tags {
    display: flex;
    flex-wrap: wrap;
    gap: 12px;
}

.skill-tag {
    background: #E8C1BB;
    padding: 10px 20px;
    border-radius: 25px;
    font-size: 16px;
}

.info-box {
    background: rgba(232, 193, 187, 0.2);
    padding: 18px;
    border-radius: 12px;
    margin-bottom: 25px;
}

.info-box h3 {
    font-size: 20px;
    margin-bottom: 12px;
}

.info-row {
    display: flex;
    justify-content: space-between;
    padding: 8px 0;
    font-size: 16px;
    border-bottom: 1px solid #ddd;
}

.info-label {
    font-weight: 600;
}

.achievement-item strong {
    font-size: 16px;
}

.achievement-item span {
    font-size: 15px;
}

.interest-item {
    padding: 8px 0;
    font-size: 16px;
    border-bottom: 1px solid #ddd;
}

.footer-note {
    text-align: center;
    font-size: 14px;
    margin-top: 40px;
}
</style>
</head>

<body>

<div class="cv-card">

    <div class="header">
        <div class="back-btn">
            <a href="/about">← Back to About Us</a>
        </div>

        <div class="header-content">

            <img src="${pageContext.request.contextPath}/assets/about/${pageContext.request.contextPath}/assets/about/sworna.jpg" class="profile-img">

            <div class="header-info">
                <h1>Sworna D Tuladhar</h1>
                <div class="title">Computing Undergraduate</div>

                <div class="desc">Motivated computing student learning IT fundamentals and web development.</div>
                <div class="desc">Looking for opportunities to grow and gain experience.</div>

                <div class="social-icons">
                    <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    <a href="#"><i class="fab fa-github"></i></a>
                    <a href="#"><i class="fab fa-instagram"></i></a>
                    <a href="#"><i class="fab fa-whatsapp"></i></a>
                </div>
            </div>

        </div>
    </div>

    <div class="content">

        <div class="two-columns">

            <div class="left-col">

                <div class="section">
                    <h2>About Me</h2>
                    <div class="about-text">
                        <p> Hello, person from the internet!! I'm a passionate programmer working towards Full-Stack Web Development, specializing in the MERN Stack Currently serving as the founder and Community Lead of Islington WebDev Community.

                            I gravitate toward full-stack work because I believe the best technical decisions come from understanding the whole system. Whether it's choosing a database schema, designing an API contract, or structuring a component tree, I aim for decisions that remain sound six months later.
                        </p>
                        <p>
                            // What's my specialty?
                            In my tech journey so far, I've worked mostly with: JavaScript, TypeScript, Express.js, Node.js, React.js, Next.js, Java, Python.
                        </p>
                        <p>
                            // What am I building?
                            I take a research-driven and project-based approach; Currently, I'm exploring AI integration via APIs (REST and GraphQL specifically) to enhance web development for future projects.
                        </p>
                        <p>
                            // Comments beyond the code
                            While I'm not debugging a simple issue that I created myself, you'll find me deep into Model United Nations (MUNs) discussing the latest geopolitics and economics to conflict resolution and problem-solving, or perhaps at the piano - composing and playing Western classical music.
                        </p>
                        <p>
                            Always excited to connect with fellow developers, collaborate on projects or just have a philosophical chat on life over a cup of chai  Let's level up together!
                        </p>
                            Personal Portfolio: <a href="https://sworna-tuladhar.vercel.app/" target="_blank">https://sworna-tuladhar.vercel.app</a>    
                    </div>
                </div>

                <div class="section">
                    <h2>Technical Skills</h2>
                    <div class="skill-tags">
                        <span class="skill-tag">MERN stack</span>
                        <span class="skill-tag">Project Management</span>
                        <span class="skill-tag">Python</span>
                        <span class="skill-tag">System Design</span>
                        <span class="skill-tag">TypeScript</span>
                        <span class="skill-tag">PostgreSQL</span>
                        <span class="skill-tag">API Design</span>
                        <span class="skill-tag">Docker</span>
                        <span class="skill-tag">Canva</span>
                        <span class="skill-tag">Figma</span>
                    </div>
                </div>

            </div>

            <div class="right-col">

                <div class="info-box">
                    <h3>Quick Info</h3>
                    <div class="info-row"><span class="info-label">Location</span><span>Kathmandu</span></div>
                    <div class="info-row"><span class="info-label">Education</span><span>Computing Student</span></div>
                    <div class="info-row"><span class="info-label">Projects</span><span>College Assignments</span></div>
                </div>

                <div class="info-box">
                    <h3>Achievements</h3>
                    <div class="achievement-item">
                        <strong>Leapfrog Student Partner 2026 Cohort V</strong>
                        <strong>Community Lead, Islington WebDev Community</strong>
                        <strong>Graphic Design IT Intern, Global Help Foundation</strong>
                    </div>
                </div>

                <div class="info-box">
                    <h3>Interests</h3>
                    <div class="interest-item">Web Development</div>
                    <div class="interest-item">Programming</div>
                    <div class="interest-item">Technology</div>
                </div>

            </div>

        </div>

    </div>

</div>

</body>
</html>
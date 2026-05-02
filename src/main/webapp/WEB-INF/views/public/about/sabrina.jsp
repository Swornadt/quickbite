<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sabrina Pradhan | CV</title>
<style>
body { font-family: Arial; margin:0; background:#f5f5f5; }
.header { padding:40px; display:flex; gap:40px; align-items:center; background:#eee; }
.header img { width:220px; border-radius:15px; }
.container { padding:40px; }
.grid { display:grid; grid-template-columns:2fr 1fr; gap:40px; }
.card-container { display:grid; gap: 40px;}
.card { background:white; padding:20px; border-radius:10px; }
.skills { display:flex; gap:10px; flex-wrap:wrap; }
.skill { background:#eee; padding:8px 12px; border-radius:6px; }
</style>
</head>

<body>

<div class="header">
    <img src="sabrina.jpg">
    <div>
        <h1>Sabrina Pradhan</h1>
        <p>Full Stack Developer | UI/UX Enthusiast</p>
        <p>Building user-friendly interfaces backed by scalable and efficient systems.</p>
    </div>
</div>

<div class="container">
<div class="grid">

<div class="card-container">
    <div class="card">
        <h2>About Me</h2>
        <p>I am a passionate and versatile full stack developer with a strong foundation in UI/UX design, frontend development, and backend systems. I enjoy building complete digital experiences - from designing intuitive and visually engaging interfaces to developing efficient, scalable, and secure backend solutions.</p>
        
        <p>My approach focuses on understanding user needs and translating them into functional, user-friendly applications. I believe that great software is not just about clean code, but also about delivering seamless experiences that are both practical and visually appealing.</p>
        
        <p>I have experience working with modern design tools and web technologies, allowing me to bridge the gap between design and development. I am comfortable creating wireframes and prototypes, developing responsive interfaces, and building robust APIs and database systems that support real-world applications.</p>
        
        <p>I am continuously learning and exploring new technologies to improve my skills and stay updated with industry trends. I enjoy problem-solving, collaborating with teams, and taking on challenges that help me grow both technically and creatively.</p>
        
        <p>My goal is to contribute to impactful projects where I can combine creativity with technical expertise to build meaningful and efficient digital solutions.</p>
    </div>

    <div class="card">
        <h2>Technical Skills</h2>
        <div class="skills">
            <div class="skill">Figma</div>
            <div class="skill">UI/UX Design</div>
            <div class="skill">Java</div>
            <div class="skill">HTML</div>
            <div class="skill">CSS</div>
            <div class="skill">JavaScript</div>
            <div class="skill">Python</div>
            <div class="skill">SQL</div>
            <div class="skill">C++</div>
        </div>
    </div>
</div>

<div class="card-container">
    <div class="card">
        <h2>Quick Info</h2>
        <p>Lalitpur, Nepal</p>
        <p>Full Stack Developer | UI/UX Enthusiast</p>
        <p>7 Projects</p>
    </div>

    <div class="card">
        <h2>Achievements</h2>
        <p>1st Place – Anugami Event, Airo Community</p>
        <p>Best Mascot Design Award – Animanga Community</p>
		<p>1st Runner-Up – “Build the Mini Web” Event, WebDev Community</p>
		
    </div>
</div>

</div>
</div>

</body>
</html>
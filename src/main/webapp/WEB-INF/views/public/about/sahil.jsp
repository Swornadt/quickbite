<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Sahil Shrestha | CV</title>
<style>
body { font-family: Arial; margin:0; background:#f5f5f5; }
.header { padding:40px; display:flex; gap:40px; align-items:center; background:#eee; }
.header img { width:220px; border-radius:15px; }
.container { padding:40px; }
.grid { display:grid; grid-template-columns:2fr 1fr; gap:40px; }
.card { background:white; padding:20px; border-radius:10px; }
.skills { display:flex; gap:10px; flex-wrap:wrap; }
.skill { background:#eee; padding:8px 12px; border-radius:6px; }
</style>
</head>

<body>

<div class="header">
    <img src="${pageContext.request.contextPath}/assets/about/sahil.jpg">
    <div>
        <h1>Sahil Shrestha</h1>
        <p>Full Stack Developer</p>
        <p>Full stack developer focused on frontend, backend systems, and databases.</p>
    </div>
</div>

<div class="container">
<div class="grid">

<div>
    <div class="card">
        <h2>About Me</h2>
        <p>I am a full stack developer with a focus on building well-structured applications and working with system logic. I enjoy understanding how different parts of an application connect and function together.</p>
        <p>I have a strong interest in databases and data handling as part of building applications, focusing on how information is stored, organized, and used efficiently within a system. I also enjoy developing application logic that supports reliable and consistent functionality.</p>
        <p>My interest lies in designing and improving systems, solving problems through structured thinking, and creating applications that are practical and efficient across both frontend and backend components.</p>
    </div>

    <div class="card">
        <h2>Technical Skills</h2>
        <div class="skills">
        	<div class="skill">Figma</div>
            <div class="skill">HTML</div>
            <div class="skill">CSS</div>
            <div class="skill">JavaScript</div>
            <div class="skill">Java</div>
            <div class="skill">Python</div>
            <div class="skill">C</div>
            <div class="skill">SQL</div>
            <div class="skill">AWS (Basics)</div>
        </div>
    </div>
</div>

<div>
    <div class="card">
        <h2>Quick Info</h2>
        <p>Chitwan, Nepal</p>
        <p>Full Stack Developer</p>
        <p>5 Projects</p>
    </div>

    <div class="card">
        <h2>Achievements</h2>
        <p>1st Runner-Up - "Build the Mini Web" Event, WebDev Community</p>
    </div>
</div>

</div>
</div>

</body>
</html>
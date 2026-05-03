<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Resha Koju | CV</title>
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
    <img src="${pageContext.request.contextPath}/assets/about/resha.jpg">
    <div>
        <h1>Resha Koju</h1>
        <p>Frontend Developer</p>
        <p>Focused on building responsive and clean interfaces.</p>
    </div>
</div>

<div class="container">
<div class="grid">

<div>
    <div class="card">
        <h2>About Me</h2>
        <p>I enjoy creating modern and responsive websites using clean design principles.</p>
    </div>

    <div class="card">
        <h2>Technical Skills</h2>
        <div class="skills">
            <div class="skill">HTML</div>
            <div class="skill">CSS</div>
            <div class="skill">JavaScript</div>
        </div>
    </div>
</div>

<div>
    <div class="card">
        <h2>Quick Info</h2>
        <p>Kathmandu, Nepal</p>
        <p>Frontend Developer</p>
        <p>8+ Projects</p>
    </div>

    <div class="card">
        <h2>Achievements</h2>
        <p>Best UI Project</p>
        <p>Hackathon Participant</p>
    </div>
</div>

</div>
</div>

</body>
</html>
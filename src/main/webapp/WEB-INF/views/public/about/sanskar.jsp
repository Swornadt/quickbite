<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Sanskar Piya | CV</title>
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
    <img src="${pageContext.request.contextPath}/assets/about/sanskar.jpg">
    <div>
        <h1>Sanskar Piya</h1>
        <p>System Analyst</p>
        <p>Analyzes and improves system performance.</p>
    </div>
</div>

<div class="container">
<div class="grid">

<div>
    <div class="card">
        <h2>About Me</h2>
        <p>I analyze systems and improve efficiency and functionality.</p>
    </div>

    <div class="card">
        <h2>Technical Skills</h2>
        <div class="skills">
            <div class="skill">Analysis</div>
            <div class="skill">Documentation</div>
            <div class="skill">Problem Solving</div>
        </div>
    </div>
</div>

<div>
    <div class="card">
        <h2>Quick Info</h2>
        <p>Kathmandu, Nepal</p>
        <p>System Analyst</p>
        <p>9+ Projects</p>
    </div>

    <div class="card">
        <h2>Achievements</h2>
        <p>Best Analyst Award</p>
        <p>Research Excellence</p>
    </div>
</div>

</div>
</div>

</body>
</html>
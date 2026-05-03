<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Resha Koju | CV</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/about-individual.css" />
</head>
<style>
	.card{
		margin-bottom: 40px;
	}
</style>
<body>

<div class="header">
    <img src="${pageContext.request.contextPath}/assets/images/resha.jpg">
    <div>
        <h1>Resha Koju</h1>
        <p>Student at Islington College Kathmandu</p>
        <p>Focused on building responsive and clean interfaces.</p>
	</div>
</div>

<div class="container">
<div class="grid">

<div>
    <div class="card">
        <h2>About Me</h2>
        <p>I enjoy creating modern and responsive websites using clean design principles. I have worked on multiple UI/UX related projects apart from my Academic projects.</p>
    </div>

    <div class="card">
        <h2>Technical Skills</h2>
        <div class="skills">
            <div class="skill">HTML</div>
            <div class="skill">CSS</div>
            <div class="skill">JavaScript</div>
            <div class="skill">Java</div>
            <div class="skill">Figma</div>
            <div class="skill">Python</div>
        </div>
    </div>
</div>

<div>
    <div class="card">
        <h2>Quick Info</h2>
        <p>Bhaktapur, Nepal</p>
        <p>UI/UX Development</p>
        <button style="background-color:#ED7C97; border-radius:10px; padding: 6px 8px; border:none; height:40px;">
			<a href="https://www.figma.com/design/Mzgi8HOprG4ufaCJoZGk2q/Resha-Koju-Portfolio?node-id=0-1&t=a07PjOMQGk4Ci8uL-1" target="_blank" style="text-decoration: none; color: white;">Check out my projects here!</a>     
        </button>
    </div>

    <div class="card">
        <h2>Achievements</h2>
        <p>Design Lead, Project Team Alpha - Islington WebDev Community</p>
        <p>UI/UX Designer, Project Aalaya - Islington WebDev Community x R&D</p>
        <p>Secretary, Islington WebDev Community</p>
        <p>Voice Actor, ING Impact</p>
        <p>Experience Ambassador, ING Skill Academy</p>
        <p>Hackathon Participant</p>
	</div>
</div>

</div>
</div>

</body>
</html>
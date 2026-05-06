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
	
	.achievement-button{
		border-radius:10px; 
		padding: 20px 8px; 
		border:none;
		margin-bottom: 20px;
		width:100%;
		text-align: left;
	}
	
	.header {
    background: linear-gradient(to right, #FFDEB4, #FFB4B4, #F2BED1);
    }
    
</style>
<body>

<div class="cv-card">

<div class="header">
    <img src="${pageContext.request.contextPath}/assets/about/resha.jpg">
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
            <div class="skill" style="background-color:#C7E3E3;">HTML</div>
            <div class="skill" style="background-color:#DBDBA9;">CSS</div>
            <div class="skill" style="background-color:#ECC8BA;">JavaScript</div>
            <div class="skill" style="background-color:#C1C2D4;">Java</div>
            <div class="skill" style="background-color:#87CCA0;">Figma</div>
            <div class="skill" style="background-color:#F2DCC7;">Python</div>
        </div>
    </div>
    
    <div class="card">
	    <h2>BSc (Hons) Computing</h2>
	    <p><strong>London Metropolitan University</strong></p>
	    <p>Islington College, Kathmandu</p>
	    <p style="font-size: 0.9em; color: #666;">2024 - Present</p>
	</div>
    
    <div class="card">
	    <h2>School Leaving Certificate</h2>
	    <p><strong>National Examination Board</strong></p>
	    <p>DAV College, Lalitpur</p>
	    <p style="font-size: 0.9em; color: #666;">2022 - 2024</p>
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
        <button class= "achievement-button" style="background-color:#C7E3E3;"> Design Lead, Project Team Alpha - Islington WebDev Community</button>
        <button class= "achievement-button" style="background-color:#DBDBA9"> UI/UX Designer, Project Aalaya - Islington WebDev Community x R&D</button>
        <button class= "achievement-button" style="background-color:#ECC8BA"> Secretary, Islington WebDev Community</button>
        <button class= "achievement-button" style="background-color:#C1C2D4"> Voice Actor, ING Impact</button>
        <button class= "achievement-button" style="background-color:#87CCA0"> Experience Ambassador, ING Skill Academy</button>
        <button class= "achievement-button" style="background-color:#F2DCC7"> Hackathon Participant</button>
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
                        <strong>Created basic websites</strong>
                        <span>College coursework projects</span>
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
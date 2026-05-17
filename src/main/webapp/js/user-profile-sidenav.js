const navLinks = document.querySelectorAll(".user-profile-nav-link");

document.addEventListener("DOMContentLoaded", () => {
	
	const currentPath = window.location.pathname;
	
	const navLinks = document.querySelectorAll(".user-profile-nav-link");
	
	navLinks.forEach(link => {
	  const linkPath = new URL(link.href).pathname;
	  
	  if (currentPath == linkPath) {
		link.classList.add("active");
	  } else {
		link.classList.remove("active");
	  }
	});
	
})

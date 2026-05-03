const navLinks = document.querySelectorAll(".user-profile-nav-link");

navLinks.forEach((link) => {
  link.addEventListener("click", () => {
    //Removing active class from all links
    navLinks.forEach((item) => {
      item.classList.remove("active");
    });

    //Adding active class to the clicked link
    link.classList.add("active");
  });
});

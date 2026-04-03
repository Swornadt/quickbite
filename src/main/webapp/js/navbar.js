const hamburger = document.querySelector(".hamburger");
const mobileMenu = document.querySelector(".nav-mobile-menu");
const crossLine = document.querySelectorAll(".hamburger-line");

console.log(crossLine);

hamburger.addEventListener("click", () => {
  mobileMenu.classList.toggle("active");
  crossLine.forEach((e) => {
    e.classList.toggle("cross");
  });
});

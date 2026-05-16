const tabs = document.querySelectorAll(".tab");

const activeSection = document.getElementById("active");
const pendingSection = document.getElementById("pending");
const resetSection = document.getElementById("password-reset-requests");

tabs.forEach((tab, index) => {
  tab.addEventListener("click", () => {
    pendingSection.style.display = "none";
    activeSection.style.display = "none";
    resetSection.style.display = "none";

    if (index === 0) {
      pendingSection.style.display = "grid";
    } else if (index === 1) {
      activeSection.style.display = "grid";
    } else if (index === 2) {
      resetSection.style.display = "grid";
    }

    tabs.forEach((item) => {
      item.classList.remove("onactive");
    });

    tab.classList.add("onactive");
  });
});

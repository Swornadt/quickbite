const tabs = document.querySelectorAll(".tab");

const pendingSection = document.getElementById("pending");

const activeSection = document.getElementById("active");
const passwordResetSection = document.getElementById("password-reset-requests");

const sections = [pendingSection, activeSection, passwordResetSection];

tabs.forEach((tab, index) => {
    tab.addEventListener("click", () => {

        // Hiding all sections
        sections.forEach(section => section.style.display = "none");

        // Showing the section matching the clicked tab
        sections[index].style.display = "flex";

        // Updating active tab styling
        tabs.forEach(item => item.classList.remove("onactive"));
        tab.classList.add("onactive");
    });
});


const tabs = document.querySelectorAll(".tab");

const activeSection = document.getElementById("active");
const pendingSection = document.getElementById("pending");

tabs.forEach((tab, index) => {

    tab.addEventListener("click", () => {

        if (index === 0) {
            pendingSection.style.display = "grid";
            activeSection.style.display = "none";
        } else {
            pendingSection.style.display = "none";
            activeSection.style.display = "grid";
        }

        tabs.forEach((item) => {
            item.classList.remove("onactive");
        });

        tab.classList.add("onactive");
    });

});
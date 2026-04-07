/**
 * 
 */

document.addEventListener("DOMContentLoaded", () => {

    const buttons = document.querySelectorAll(".category-btn");
    const searchBar = document.getElementById("searchBar");

    let selectedCategory = "all";

    function filterCards() {
        const cards = document.querySelectorAll(".card");
        const searchValue = searchBar.value.toLowerCase();

        cards.forEach(card => {
            const category = card.getAttribute("data-category");
            const text = card.textContent.toLowerCase();

            const matchCategory =
                selectedCategory === "all" || category === selectedCategory;

            const matchSearch = text.includes(searchValue);

            if (matchCategory && matchSearch) {
                card.classList.remove("hidden");
            } else {
                card.classList.add("hidden");
            }
        });
    }

    buttons.forEach(button => {
        button.addEventListener("click", () => {
            buttons.forEach(btn => btn.classList.remove("active"));
            button.classList.add("active");

            selectedCategory = button.getAttribute("data-category");
            filterCards();
        });
    });

    searchBar.addEventListener("input", filterCards);

});
document.addEventListener("DOMContentLoaded", function() {
    // 1. Get parameters immediately
    const urlParams = new URLSearchParams(window.location.search);
    const hasAddedParam = urlParams.get('added');

    console.log("Toast Script Loaded. Added param:", hasAddedParam); // Debugging line

    if (hasAddedParam === 'true') {
        const toast = document.getElementById("cart-toast");
        
        if (toast) {
            // 2. Show the toast
            toast.classList.add("show");
            console.log("Showing toast now...");

            setTimeout(() => {
                toast.classList.remove("show");
            }, 3000);

            // 3. ONLY clean the URL after we are sure the logic ran
            const cleanUrl = window.location.origin + window.location.pathname;
            window.history.replaceState({}, document.title, cleanUrl);
        } else {
            console.error("Element #cart-toast not found in the DOM!");
        }
    }
});
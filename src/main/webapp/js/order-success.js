
document.addEventListener("DOMContentLoaded", function() {
    // check the URL for orderStatus=success
    const params = new URLSearchParams(window.location.search);
    const modal = document.getElementById("successModal");

    if (params.get('orderStatus') === 'success') {
        // show the modal
        modal.style.display = "flex";

        // clean the URL so refreshing doesn't show the popup again
        const cleanUrl = window.location.protocol + "//" + window.location.host + window.location.pathname;
        window.history.replaceState({path: cleanUrl}, '', cleanUrl);
    }
});

// hide the modal
function closeModal() {
    document.getElementById("successModal").style.display = "none";
}
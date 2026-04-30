document.addEventListener("DOMContentLoaded", () => {
    const selectAll = document.getElementById('select-all');
    const itemChecks = document.querySelectorAll('.item-check');

    if (selectAll) {
        selectAll.addEventListener('change', (e) => {
            itemChecks.forEach(check => {
                check.checked = e.target.checked;
            });
        });
    }
});

// Confirmation for the bulk delete triggered by the onclick
function confirmBulkDelete() {
    const anyChecked = Array.from(document.querySelectorAll('.item-check')).some(c => c.checked);
    if (!anyChecked) {
        alert("Please select at least one item.");
        return;
    }
    if (confirm("Remove selected items from cart?")) {
        document.getElementById('bulk-delete-form').submit();
    }
}
document.addEventListener('DOMContentLoaded', function() {
    const params = new URLSearchParams(window.location.search);
    const role = params.get('role');
    if (role) {
        document.getElementById('role').value = role;
    }
});
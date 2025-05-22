document.addEventListener("DOMContentLoaded", function () {
    const signinBtn = document.getElementById("signinBtn");

    if (signinBtn) {
        signinBtn.addEventListener("click", function () {
            window.location.href = "/select-role";
        });
    }
});

// Admin login
function togglePassword() {
    const passwordInput = document.getElementById('adminPassword');
    const toggleButton = document.querySelector('.toggle-password i');
    if (passwordInput.type === 'password') {
        passwordInput.type = 'text';
        toggleButton.classList.replace('fa-eye', 'fa-eye-slash');
    } else {
        passwordInput.type = 'password';
        toggleButton.classList.replace('fa-eye-slash', 'fa-eye');
    }
}

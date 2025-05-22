// Admin-Dashboard
// main.js
document.addEventListener('DOMContentLoaded', function() {
    // Initialize Charts
    initializeCharts();
    
    // Setup Event Listeners
    setupEventListeners();
});


function initializeCharts() {
    // Recruitment Trends Chart
    const recruitmentCtx = document.getElementById('recruitmentChart').getContext('2d');
    new Chart(recruitmentCtx, {
        type: 'line',
        data: {
            labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            datasets: [
                {
                    label: 'Số lượng nhà tuyển dụng',
                    data: [300, 350, 120, 180, 150, 250, 180, 300, 150, 250, 200, 200],
                    borderColor: '#ff7849',
                    backgroundColor: 'rgba(255, 120, 73, 0.1)',
                    tension: 0.4,
                    fill: true
                },
                {
                    label: 'Số lượng ứng viên',
                    data: [180, 250, 250, 250, 150, 200, 250, 250, 250, 180, 150, 180],
                    borderColor: '#9b51e0',
                    backgroundColor: 'rgba(155, 81, 224, 0.1)',
                    tension: 0.4,
                    fill: true
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'top',
                    align: 'end'
                }
            },
            scales: {
                y: {
                    beginAtZero: true,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)'
                    }
                },
                x: {
                    grid: {
                        display: false
                    }
                }
            }
        }
    });

    // User Distribution Chart
    const distributionCtx = document.getElementById('userDistributionChart').getContext('2d');
    new Chart(distributionCtx, {
        type: 'doughnut',
        data: {
            labels: ['Nhà tuyển dụng', 'Admin', 'Ứng viên', 'Nhân viên'],
            datasets: [{
                data: [20, 5, 43, 32],
                backgroundColor: [
                    '#ff7849',
                    '#9b51e0',
                    '#f1f5f9',
                    '#37516d'
                ],
                borderWidth: 0
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        usePointStyle: true,
                        padding: 20
                    }
                }
            },
            cutout: '70%'
        }
    });
}

// Add this to your existing JavaScript
document.querySelector('.user-profile').addEventListener('click', function(e) {
    e.stopPropagation();
    this.querySelector('.profile-dropdown').classList.toggle('show');
});

// Close dropdown when clicking outside
document.addEventListener('click', function(e) {
    const dropdown = document.querySelector('.profile-dropdown');
    if (dropdown.classList.contains('show')) {
        dropdown.classList.remove('show');
    }
});

function setupEventListeners() {
    // User Profile Dropdown
    const userProfile = document.querySelector('.user-profile');
    userProfile.addEventListener('click', function() {
        // Add your dropdown toggle logic
    });

    // Notification Click
    const notificationIcon = document.querySelector('.notification-icon');
    notificationIcon.addEventListener('click', function() {
        // Add your notification panel logic
    });

    // Search Input
    const searchInput = document.querySelector('.search-container input');
    searchInput.addEventListener('input', function(e) {
        // Add your search logic
    });

    // Time Filter
    const timeFilter = document.querySelector('.form-select');
    timeFilter.addEventListener('change', function(e) {
        // Add your time filter logic
    });

    // Mobile Menu Toggle
    const menuToggle = document.createElement('button');
    menuToggle.classList.add('menu-toggle');
    menuToggle.innerHTML = '<i class="fas fa-bars"></i>';
    document.querySelector('.top-nav').prepend(menuToggle);

    menuToggle.addEventListener('click', function() {
        document.querySelector('.sidebar').classList.toggle('show');
    });
}

// Responsive Chart Resizing
window.addEventListener('resize', function() {
    Chart.instances.forEach(chart => {
        chart.resize();
    });
})

function loadStats() {
    fetch("/admin/dashboard-data")
        .then(response => response.json())
        .then(data => {
            document.querySelector(".stat-number[data-type='users']").textContent = data.totalUsers;
            document.querySelector(".stat-number[data-type='recruiters']").textContent = data.totalRecruiters;
            document.querySelector(".stat-number[data-type='candidates']").textContent = data.totalCandidates;
        })
        .catch(error => console.error("Error loading stats:", error));
}

// Gọi lần đầu
loadStats();

// Cập nhật lại mỗi 5 giây
setInterval(loadStats, 5000);


document.addEventListener("DOMContentLoaded", function () {
    const currentYear = new Date().getFullYear();
    fetch('/admin/chart-data?year=' + currentYear)
        .then(response => response.json())
        .then(data => {
            const recruiterData = data.recruiters;
            const candidateData = data.candidates;

            const ctx = document.getElementById('recruitmentChart').getContext('2d');
            new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['Tháng 1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
                    datasets: [
                        {
                            label: 'Nhà tuyển dụng',
                            data: recruiterData,
                            borderWidth: 2
                        },
                        {
                            label: 'Ứng viên',
                            data: candidateData,
                            borderWidth: 2
                        }
                    ]
                }
            });
        });
});

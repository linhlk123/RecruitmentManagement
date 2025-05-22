// Theme Management
function initTheme() {
    const savedTheme = localStorage.getItem('theme');
    const systemPrefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
    
    if (savedTheme) {
        document.documentElement.setAttribute('data-theme', savedTheme);
    } else if (systemPrefersDark) {
        document.documentElement.setAttribute('data-theme', 'dark');
    }
}

function toggleTheme() {
    const currentTheme = document.documentElement.getAttribute('data-theme') || 'light';
    const newTheme = currentTheme === 'light' ? 'dark' : 'light';
    
    document.documentElement.setAttribute('data-theme', newTheme);
    localStorage.setItem('theme', newTheme);
}

document.querySelectorAll('input[type="radio"]').forEach(radio => {
    radio.addEventListener('change', () => {
        const groupName = radio.name;
        document.querySelectorAll(`input[name="${groupName}"]`).forEach(input => {
            const label = input.nextElementSibling;
            if (input.checked) {
                label.style.backgroundColor = '#007bff';
                label.style.color = '#fff';
            } else {
                label.style.backgroundColor = '#f1f1f1';
                label.style.color = '#333';
            }
        });
    });
});


// DOM Elements
const createJobButton = document.querySelector('.btn-primary');
const modal = document.getElementById('createJobModal');
const closeButton = document.querySelector('.close-modal');
const form = document.getElementById('createJobForm');
const candidatesSection = document.getElementById('candidates');
const candidateProfileModal = document.getElementById('candidateProfileModal');
const positionFilter = document.getElementById('positionFilter');
const statusFilter = document.getElementById('statusFilter');
const viewControls = document.querySelectorAll('.view-controls button');
const ratingInputs = document.querySelectorAll('.rating input[type="radio"]');
const logoUpload = document.querySelector('.logo-upload button');
const notificationFilters = document.querySelectorAll('.notification-filters button');

// Event Listeners
document.addEventListener('DOMContentLoaded', () => {
    initTheme();
    loadJobPosts();
    generateCalendar();
    
    // Theme toggle
    document.getElementById('themeToggle').addEventListener('click', toggleTheme);
    
    // System theme changes
    window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', (e) => {
        if (!localStorage.getItem('theme')) {
            document.documentElement.setAttribute('data-theme', e.matches ? 'dark' : 'light');
        }
    });

    // Job post modal
    createJobButton.addEventListener('click', openModal);
    closeButton.addEventListener('click', closeModal);
    modal.addEventListener('click', (e) => {
        if (e.target === modal) closeModal();
    });

    // Form submission
    form.addEventListener('submit', handleSubmit);

    // Navigation
    document.querySelectorAll('.nav-links a').forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            const sectionId = link.getAttribute('href').substring(1);
            showSection(sectionId);
        });
    });

    // Candidate filters
    if (positionFilter && statusFilter) {
        positionFilter.addEventListener('change', filterCandidates);
        statusFilter.addEventListener('change', filterCandidates);
    }

    // Interview view toggle
    viewControls.forEach(button => {
        button.addEventListener('click', () => {
            const view = button.dataset.view;
            viewControls.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            
            const calendarView = document.querySelector('.calendar-view');
            const listView = document.querySelector('.interview-list');
            
            if (view === 'calendar') {
                calendarView.classList.remove('hidden');
                listView.classList.add('hidden');
            } else {
                calendarView.classList.add('hidden');
                listView.classList.remove('hidden');
            }
        });
    });

    // Evaluation form
    ratingInputs.forEach(input => {
        input.addEventListener('change', () => {
            const group = input.closest('.criteria-group');
            group.querySelectorAll('span').forEach(span => {
                span.style.backgroundColor = '';
                span.style.color = '';
            });
            
            const selectedSpan = input.nextElementSibling;
            selectedSpan.style.backgroundColor = 'var(--primary-color)';
            selectedSpan.style.color = 'white';
        });
    });

    // Logo upload
    if (logoUpload) {
        logoUpload.addEventListener('click', () => {
            const input = document.createElement('input');
            input.type = 'file';
            input.accept = 'image/*';
            input.onchange = (e) => {
                const file = e.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                        document.querySelector('.logo-upload img').src = e.target.result;
                    };
                    reader.readAsDataURL(file);
                }
            };
            input.click();
        });
    }

    // Notification filters
    notificationFilters.forEach(button => {
        button.addEventListener('click', () => {
            notificationFilters.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');
            filterNotifications(button.textContent.trim());
        });
    });

    // Set default dates for job post form
    const today = new Date();
    const thirtyDaysFromNow = new Date(today);
    thirtyDaysFromNow.setDate(today.getDate() + 30);
    document.getElementById('createdAt').value = formatDate(today);
    document.getElementById('endDate').value = formatDate(thirtyDaysFromNow);
});

// Functions
function openModal() {
    modal.classList.add('show');
    document.body.style.overflow = 'hidden';
}

function closeModal() {
    modal.classList.remove('show');
    document.body.style.overflow = '';
    form.reset();
}

function formatDate(date) {
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

function formatDateForDisplay(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

async function handleSubmit(e) {
    e.preventDefault();

    const formData = {
        jobTitle: document.getElementById('jobTitle').value,
        companyId: document.getElementById('companyId').value, // Có thể lấy từ giá trị form hoặc session
        jobDescription: document.getElementById('jobDescription').value,
        jobQuantity: document.getElementById('jobQuantity').value,
        jobLevel: document.getElementById('jobLevel').value,
        jobSalary: document.getElementById('jobSalary').value,
        jobLocation: document.getElementById('jobLocation').value,
        createdAt: document.getElementById('createdAt').value,
        endDate: document.getElementById('endDate').value,
        jobRequirements: document.getElementById('jobRequirements').value,
        jobBenefits: document.getElementById('jobBenefits').value,
      };

    try {
        const response = await fetch('/recruiter/create-job', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (response.ok) {
            showNotification('Đã tạo tin tuyển dụng thành công!', 'success');
            closeModal();
            loadJobPosts();
        } else {
            showNotification('Có lỗi xảy ra khi tạo tin tuyển dụng', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Có lỗi xảy ra khi tạo tin tuyển dụng', 'error');
    }
}

// Format date function
function formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

// Load job posts from the backend
async function loadJobPosts() {
    try {
        const response = await fetch('/recruiter');
        const jobPosts = await response.json(); // Get data from the response
        if (response.ok) {
            displayJobPosts(jobPosts); // Display job posts
        } else {
            showNotification('Không thể tải tin tuyển dụng', 'error');
        }
    } catch (error) {
        console.error('Error loading job posts:', error);
        showNotification('Có lỗi xảy ra khi tải danh sách tin tuyển dụng', 'error');
    }
}

// Display job posts on the page
function displayJobPosts(jobPosts) {
    const tbody = document.querySelector('#jobPostsTableBody');
    tbody.innerHTML = ''; // Clear old rows

    jobPosts.forEach(post => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${post.jobTitle}</td>
            <td>${formatDate(post.createdAt)}</td>
            <td>${formatDate(post.endDate)}</td>
            <td>${post.jobQuantity}</td>
            <td><span class="status active">Đang tuyển</span></td>
            <td class="actions">
                <button class="btn-icon" onclick="editJobPost(${post.id})"><i class="fas fa-edit"></i></button>
                <button class="btn-icon" onclick="viewJobPost(${post.id})"><i class="fas fa-eye"></i></button>
                <button class="btn-icon" onclick="deleteJobPost(${post.id})"><i class="fas fa-trash"></i></button>
            </td>
        `;
        tbody.appendChild(row);
    });
}


function showSection(sectionId) {
    document.querySelectorAll('.content-section').forEach(section => {
        section.classList.add('hidden');
    });
    document.getElementById(sectionId).classList.remove('hidden');
    document.querySelectorAll('.nav-links li').forEach(li => {
        li.classList.remove('active');
    });
    document.querySelector(`a[href="#${sectionId}"]`).parentElement.classList.add('active');
}

function showNotification(message, type) {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.remove();
    }, 3000);
}

function filterNotifications(filter) {
    const notifications = document.querySelectorAll('.notification-item');
    notifications.forEach(notification => {
        if (filter === 'Tất cả') {
            notification.style.display = 'flex';
        } else if (filter === 'Chưa đọc' && notification.classList.contains('unread')) {
            notification.style.display = 'flex';
        } else if (filter === 'Ứng viên mới' && notification.querySelector('h4').textContent.includes('Ứng viên mới')) {
            notification.style.display = 'flex';
        } else if (filter === 'Phỏng vấn' && notification.querySelector('h4').textContent.includes('phỏng vấn')) {
            notification.style.display = 'flex';
        } else {
            notification.style.display = 'none';
        }
    });
}

function generateCalendar() {
    const calendar = document.querySelector('.calendar-grid');
    if (!calendar) return;

    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const firstDay = new Date(year, month, 1).getDay();
    const daysInMonth = new Date(year, month + 1, 0).getDate();
    
    calendar.innerHTML = '';
    
    for (let i = 0; i < firstDay; i++) {
        calendar.appendChild(createCalendarDay(''));
    }
    
    for (let day = 1; day <= daysInMonth; day++) {
        const dayElement = createCalendarDay(day);
        calendar.appendChild(dayElement);
    }
}

function createCalendarDay(day) {
    const div = document.createElement('div');
    div.className = 'calendar-day';
    div.textContent = day;
    return div;
}

// Form validation
const inputs = form.querySelectorAll('input[required], textarea[required]');
inputs.forEach(input => {
    input.addEventListener('invalid', (e) => {
        e.preventDefault();
        showError(input);
    });

    input.addEventListener('input', () => {
        clearError(input);
    });
});

function showError(input) {
    const formGroup = input.closest('.form-group');
    if (!formGroup.querySelector('.error-message')) {
        const error = document.createElement('div');
        error.className = 'error-message';
        error.textContent = 'Vui lòng điền thông tin này';
        formGroup.appendChild(error);
    }
}

function clearError(input) {
    const formGroup = input.closest('.form-group');
    const error = formGroup.querySelector('.error-message');
    if (error) {
        error.remove();
    }
}
async function loadJobPosts() {
    try {
        const response = await fetch('/recruiter/job-posts');
        if (response.ok) {
            const jobPosts = await response.json();
            displayJobPosts(jobPosts);  // Hàm để hiển thị dữ liệu lên giao diện
        } else {
            showNotification('Không thể tải tin tuyển dụng', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Có lỗi xảy ra khi tải tin tuyển dụng', 'error');
    }
}

function displayJobPosts(jobPosts) {
    const tableBody = document.getElementById('jobPostsTableBody');
    tableBody.innerHTML = '';  // Xóa dữ liệu cũ trong bảng

    jobPosts.forEach(post => {
        const row = document.createElement('tr');
        
        const titleCell = document.createElement('td');
        titleCell.textContent = post.jobTitle;
        
        const createdAtCell = document.createElement('td');
        createdAtCell.textContent = post.createdAt;
        
        const endDateCell = document.createElement('td');
        endDateCell.textContent = post.endDate;
        
        const quantityCell = document.createElement('td');
        quantityCell.textContent = post.jobQuantity;
        
        const statusCell = document.createElement('td');
        statusCell.textContent = post.status;

        row.appendChild(titleCell);
        row.appendChild(createdAtCell);
        row.appendChild(endDateCell);
        row.appendChild(quantityCell);
        row.appendChild(statusCell);

        tableBody.appendChild(row);
    });
}

// Gọi hàm khi trang tải xong
window.onload = loadJobPosts;



document.addEventListener('DOMContentLoaded', function () {
    loadApplicants(); // Gọi API để tải danh sách ứng viên khi trang tải xong
});



// Hàm để lấy ID của bài đăng hiện tại (Có thể lấy từ URL hoặc thẻ HTML)
function getBaiDangIdFromPage() {
    // Giả sử bạn lấy từ URL hoặc một biến toàn cục
    return 1; // Ví dụ ID bài đăng
}

// Hàm để định dạng ngày tháng
function formatDate(dateString) {
    const date = new Date(dateString);
    return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
}



document.addEventListener('DOMContentLoaded', function() {
    // Lắng nghe sự kiện click trên các nút "Gửi lời mời"
    document.querySelectorAll('.send-invite-btn').forEach((btn) => {
        btn.addEventListener('click', function() {
            const email = this.getAttribute('data-email');
            console.log('Gửi lời mời phỏng vấn cho: ' + email);

            // Gọi API gửi email
            sendInviteEmail(email);
        });
    });
});

// Gửi email qua API
function sendInviteEmail(email) {
    fetch('/send-invite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ 
            email: email, 
            subject: 'Lời mời phỏng vấn', 
            message: 'Chúng tôi vui mừng mời bạn tham gia phỏng vấn.' 
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert('Lời mời đã được gửi thành công!');
        } else {
            alert('Có lỗi xảy ra khi gửi lời mời!');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Có lỗi xảy ra!');
    });
}



// Hàm để gọi API và hiển thị danh sách ứng viên
async function loadApplicants() {
    try {
        const baiDangId = getBaiDangIdFromPage(); // Lấy ID của bài đăng hiện tại
        const response = await fetch(`/application/baidang/${baiDangId}`); // Lấy ứng viên theo bài đăng
        const applicants = await response.json();

        const candidatesGrid = document.getElementById('candidatesGrid');
        candidatesGrid.innerHTML = ''; // Xóa nội dung cũ

        // Duyệt qua danh sách ứng viên và hiển thị chúng
        applicants.forEach(applicant => {
            const applicantCard = document.createElement('div');
            applicantCard.classList.add('candidate-card');
            applicantCard.innerHTML = `
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${applicant.hoTen}</h5>
                        <p><strong>Email:</strong> ${applicant.email}</p>
                        <p><strong>Số điện thoại:</strong> ${applicant.soDienThoai}</p>
                        <p><strong>CV:</strong> <a href="${applicant.cvUrl}" target="_blank">Xem CV</a></p>
                        <p><strong>Ngày ứng tuyển:</strong> ${formatDate(applicant.ngayUngTuyen)}</p>
                        <p><strong>Trạng thái:</strong> ${applicant.trangThai}</p>
                        <!-- Nút "Gửi lời mời" -->
                        <button class="btn btn-primary send-invite-btn" data-email="${applicant.email}">Gửi bài test</button>
                        <!-- Nút "Từ chối" -->
                        <button class="btn btn-danger reject-btn" data-id="${applicant.id}">Từ chối</button>
                        
                        <!-- Nút "Xóa" -->
                        <button class="btn btn-warning delete-btn" data-id="${applicant.id}">Xóa</button>
                        <button class="btn btn-success accept-btn" data-id="${applicant.id}">Chấp nhận</button>
                        <button class="btn btn-info interview-btn" data-id="${applicant.id}">Gửi phỏng vấn</button>
                    </div>
                </div>
            `;
            candidatesGrid.appendChild(applicantCard);
        });

    // Gắn event cho các nút mới
    document.querySelectorAll('.accept-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = btn.getAttribute('data-id');
            acceptApplicant(id);
        });
    });
    document.querySelectorAll('.interview-btn').forEach(btn => {
        btn.addEventListener('click', () => {
            const id = btn.getAttribute('data-id');
            sendInterviewInvite(id);
        });
    });
        // Thêm sự kiện cho nút "Gửi lời mời"
        document.querySelectorAll('.send-invite-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const email = this.getAttribute('data-email');
                sendInviteEmail(email);  // Gửi lời mời email cho ứng viên
            });
        });
        // Thêm sự kiện cho nút "Từ chối"
        document.querySelectorAll('.reject-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const applicantId = this.getAttribute('data-id');
                rejectApplicant(applicantId);  // Từ chối ứng viên
            });
        });

        // Thêm sự kiện cho nút "Xóa"
        document.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                const applicantId = this.getAttribute('data-id');
                deleteApplicant(applicantId);  // Xóa ứng viên
            });
        });
    } catch (error) {
        console.error('Error loading applicants:', error);
    }
}

// Hàm để gửi email lời mời
function sendInviteEmail(email) {
    const data = {
        email: email,
        subject: "CONGRATULATIONS!",
        message: `We're pleased to let you know that your recent application has stood out! Your qualifications made a strong impression, and we found your background to be very compelling.

We believe your experience would be a valuable asset to our team.

To move forward, we'd like to invite you for an interview. This will be an opportunity to discuss more about the role and for you to learn about our work environment.

We'll be in touch shortly with details regarding the time and location.

Congratulations on this next step. We're excited about the possibility of you joining us.

Sincerely,
The Hiring Team`
    };

    // Gửi yêu cầu POST đến API /send-invite
    fetch('/send-invite', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(result => {
        if (result.success) {
            alert('Lời mời đã được gửi thành công!');
        } else {
            alert('Có lỗi xảy ra khi gửi lời mời.');
        }
    })
    .catch(error => {
        console.error('Error sending email:', error);
        alert('Có lỗi xảy ra khi gửi lời mời.');
    });
}


// Hàm gửi email từ chối
function sendRejectionEmail(email) {
    const data = {
        email: email,
        subject: "THANK YOU FOR YOUR APPLICATION!",
        message: `After careful review, we regret to inform you that you have not been selected to move forward to the next round. This year, we received an incredibly high volume of strong applications, and while your profile showed great potential, the competition was especially intense.

                Please know this decision is not a reflection of your ability or future potential — but rather the result of very difficult choices we had to make given the limited number of spots. We truly appreciate the time, effort, and passion you put into your application.

                We encourage you to apply again in the future, as we would love to see your continued growth and development.`
    };

    fetch('/send-invite', {  // hoặc endpoint email phù hợp
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            console.log('Email từ chối đã gửi thành công');
        } else {
            console.warn('Lỗi khi gửi email từ chối');
        }
    })
    .catch(err => {
        console.error('Lỗi gửi email từ chối:', err);
    });
}

// Hàm để từ chối ứng viên và gửi thư từ chối
function rejectApplicant(applicantId, email) {
    fetch(`/application/reject/${applicantId}`, { method: 'POST' })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            alert('Ứng viên đã bị từ chối');
            sendRejectionEmail(email);  // Gửi email từ chối
            loadApplicants(); // Tải lại danh sách ứng viên
        } else {
            alert('Có lỗi xảy ra khi từ chối ứng viên.');
        }
    })
    .catch(error => {
        console.error('Error rejecting applicant:', error);
        alert('Có lỗi xảy ra khi từ chối ứng viên.');
    });
}

// Hàm để xóa ứng viên
function deleteApplicant(applicantId) {
    // Thực hiện API gọi để xóa ứng viên
    fetch(`/application/delete/${applicantId}`, {
        method: 'DELETE'
    })
    .then(response => response.json())
    .then(result => {
        if (result.success) {
            alert('Ứng viên đã được xóa');
            loadApplicants(); // Reload danh sách ứng viên
        } else {
            alert('Có lỗi xảy ra khi xóa ứng viên.');
        }
    })
    .catch(error => {
        console.error('Error deleting applicant:', error);
        alert('Có lỗi xảy ra khi xóa ứng viên.');
    });
}function acceptApplicant(id) {
    fetch(`/application/accept/${id}`, { method: 'POST' })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            alert('Ứng viên đã được chấp nhận');
            loadApplicants();
        } else {
            alert('Lỗi khi chấp nhận ứng viên');
        }
    })
    .catch(err => {
        console.error(err);
        alert('Lỗi khi chấp nhận ứng viên');
    });
}

function sendInterviewInvite(id) {
    fetch(`/application/interview/${id}`, { method: 'POST' })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            alert('Lời mời phỏng vấn đã được gửi');
        } else {
            alert('Lỗi khi gửi lời mời phỏng vấn');
        }
    })
    .catch(err => {
        console.error(err);
        alert('Lỗi khi gửi lời mời phỏng vấn');
    });
}

// Hàm gửi email phỏng vấn
function sendInterviewEmail(email) {
    const data = {
        email: email,
        subject: "INTERVIEW INVITATION",
        message: `Dear Candidate,

Thank you for your interest in the position. We are pleased to inform you that after reviewing your application and qualifications, we would like to invite you to an interview.

The interview will be an opportunity to discuss your experience in more detail and to provide you with further insights into the role and our company culture.

Please find the interview details below:

- Date: August 30, 2025  
- Time: To be confirmed  
- Location: Online via Microsoft Teams

Kindly confirm your availability by replying to this email at your earliest convenience.

If you have any questions or require further information, please do not hesitate to contact us.

We look forward to meeting you.

Best regards,

The Hiring Team`
    };

    fetch('/send-invite', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            console.log('Email mời phỏng vấn đã gửi thành công');
        } else {
            console.warn('Lỗi khi gửi email mời phỏng vấn');
        }
    })
    .catch(err => {
        console.error('Lỗi gửi email mời phỏng vấn:', err);
    });
}



// Hàm gửi email chấp nhận
function sendAcceptanceEmail(email) {
    const data = {
        email: email,
        subject: "CONGRATULATIONS! APPLICATION ACCEPTED",
        message: `Dear Candidate,

We are excited to inform you that you have been accepted for the position! Your qualifications and experience impressed us greatly.

Our team will contact you soon with the next steps and onboarding information.

Congratulations and welcome aboard!

Best regards,

The Hiring Team`
    };

    fetch('/send-invite', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            console.log('Email chấp nhận đã gửi thành công');
        } else {
            console.warn('Lỗi khi gửi email chấp nhận');
        }
    })
    .catch(err => {
        console.error('Lỗi gửi email chấp nhận:', err);
    });
}


// Hàm lấy danh sách ứng viên
async function loadCandidates() {
    try {
        const baiDangId = getBaiDangIdFromPage(); // Lấy ID của bài đăng hiện tại
        const response = await fetch(`/application/baidang/${baiDangId}`); // Lấy ứng viên theo bài đăng
        
        // Nếu API trả về kết quả hợp lệ
        const applicants = await response.json();
        
        // Lấy phần tử dropdown
        const candidateSelect = document.getElementById('candidateSelect');
        
        // Xóa nội dung cũ trong dropdown
        candidateSelect.innerHTML = '<option value="">Chọn ứng viên</option>';

        // Lặp qua danh sách ứng viên và thêm vào dropdown
        applicants.forEach(applicant => {
            const option = document.createElement('option');
            option.value = applicant.id;  // Lưu ID của ứng viên vào giá trị của option
            option.textContent = applicant.hoTen;  // Hiển thị tên ứng viên trong dropdown
            candidateSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error loading candidates:', error);
    }
}

// Gọi hàm để tải danh sách ứng viên khi trang tải xong
document.addEventListener('DOMContentLoaded', loadCandidates);



document.querySelector('.evaluation-form .btn-primary').addEventListener('click', async () => {
    const ungTuyenId = document.getElementById('candidateSelect').value;
    const diemKyNangChuyenMon = document.querySelector('input[name="technical"]:checked')?.value;
    const diemKyNangGiaoTiep = document.querySelector('input[name="communication"]:checked')?.value;
    const nhanXet = document.querySelector('.evaluation-form textarea').value;
    const ketLuan = document.querySelector('.evaluation-form select[required]').value;

    if (!ungTuyenId || !diemKyNangChuyenMon || !diemKyNangGiaoTiep || !ketLuan) {
        alert('Vui lòng điền đầy đủ thông tin đánh giá!');
        return;
    }

    const evaluationData = {
        ungTuyenId: parseInt(ungTuyenId),
        diemKyNangChuyenMon: parseInt(diemKyNangChuyenMon),
        diemKyNangGiaoTiep: parseInt(diemKyNangGiaoTiep),
        nhanXet: nhanXet,
        ketLuan: ketLuan
    };

    try {
        const res = await fetch('/evaluation/save', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(evaluationData)
        });
        const data = await res.json();

        if (res.ok) {
            alert('Đánh giá đã được lưu thành công!');
            // Có thể reset form hoặc làm gì khác ở đây
        } else {
            alert('Lỗi khi lưu đánh giá: ' + (data.message || ''));
        }
    } catch (err) {
        console.error(err);
        alert('Lỗi khi lưu đánh giá!');
    }
});

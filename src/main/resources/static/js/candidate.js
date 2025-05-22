document.addEventListener('DOMContentLoaded', function() {
  let selectedBaiDangId = null;

 document.querySelectorAll('.btn-primary[data-bs-toggle="modal"]').forEach((btn) => {
  btn.addEventListener('click', function() {
    let jobId = this.getAttribute('data-job-id');
    
    // Nếu jobId không hợp lệ, gán một giá trị mặc định
    if (isNaN(jobId) || jobId === null || jobId === "") {
      jobId = 1; // Gán giá trị mặc định là 1 hoặc bất kỳ giá trị hợp lệ nào
    }
    
    // Chuyển jobId thành số
    selectedBaiDangId = parseInt(jobId, 10);
    console.log("Selected job ID:", selectedBaiDangId);
  });
});


  // Xử lý khi người dùng submit form ứng tuyển
  document.getElementById('applyForm').addEventListener('submit', function(e) {
    e.preventDefault();

    if (!selectedBaiDangId) {
      alert('Vui lòng chọn công việc trước!');
      return;
    }

    const data = {
      baiDangId: selectedBaiDangId, // Truyền đúng baiDangId vào form
      hoTen: document.getElementById('hoTen').value,
      email: document.getElementById('email').value,
      soDienThoai: document.getElementById('soDienThoai').value,
      cvUrl: document.getElementById('cvUrl').value
    };

    console.log("Sending data:", data); // Kiểm tra thông tin dữ liệu

    fetch('/application', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data)
    })
    .then(res => {
      if (res.ok) {
        alert('Ứng tuyển thành công!');
        document.getElementById('applyForm').reset();
        var modal = bootstrap.Modal.getInstance(document.getElementById('applyModal'));
        modal.hide();
      } else {
        return res.json().then(err => { throw err; });
      }
    })
    .catch(err => {
      alert('Có lỗi xảy ra khi ứng tuyển: ' + (err.message || JSON.stringify(err)));
    });
  });
});

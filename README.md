# Phát triển Website Quản lý Tuyển Dụng

## 1. Giới thiệu
Hệ thống Website Quản lý Tuyển Dụng giúp các doanh nghiệp đăng tin tuyển dụng, quản lý hồ sơ ứng viên và theo dõi quy trình tuyển dụng. Ứng viên có thể tìm kiếm công việc phù hợp, nộp hồ sơ trực tuyến và đặt lịch phỏng vấn.

## 2. Môi trường phát triển
- **Ngôn ngữ lập trình**: Java, JavaScript
- **Framework**: Spring Boot, Thymeleaf
- **Cơ sở dữ liệu**: PL/SQL
- **Công cụ**: Maven, Git
- **API**: LinkedIn API
- **Công cụ AI**: Được tích hợp để đề xuất ứng viên phù hợp.
- **Gửi thông báo**: Email và SMS khi có công việc mới hoặc nhắc nhở lịch phỏng vấn.

## 3. Môi trường doanh nghiệp
Đơn vị yêu cầu: **TalentHub**, một công ty chuyên cung cấp dịch vụ tuyển dụng cho doanh nghiệp.

- **Số lượng công ty tuyển dụng**: Trên 500 công ty.
- **Số lượng hồ sơ ứng viên**: Trên 5000 hồ sơ.
- **Người sử dụng**: Đại diện nhà tuyển dụng, ứng viên tìm việc, nhân viên phụ trách hồ sơ tuyển dụng và hồ sơ ứng viên.

## 4. Các nghiệp vụ chính
- **Quy trình đăng tuyển**:
  1. Nhà tuyển dụng tạo tin tuyển dụng.
  2. Xác nhận đăng tải.
  3. Tin tuyển dụng hiển thị trên hệ thống.

- **Quy trình ứng tuyển**:
  1. Ứng viên chọn tin tuyển dụng.
  2. Nộp hồ sơ.
  3. Hệ thống gửi hồ sơ đến nhà tuyển dụng.

- **Quy trình làm bài test**:
  1. Nhà tuyển dụng chọn ứng viên.
  2. Gửi lời mời phỏng vấn.
  3. Ứng viên xác nhận và tham gia làm bài test.

- **Quy trình phỏng vấn**:
  1. Nhà tuyển dụng chọn ứng viên.
  2. Gửi lời mời phỏng vấn.
  3. Ứng viên tham gia phỏng vấn.

- **Quy trình đánh giá**:
  1. Sau phỏng vấn, nhà tuyển dụng cập nhật đánh giá.
  2. Hệ thống ghi nhận kết quả.

## 5. Yêu cầu chức năng
- Đăng tin tuyển dụng theo danh mục ngành nghề.
- Hỗ trợ nhiều loại công việc: Full-time, Part-time, Freelance.
- Ứng viên tạo hồ sơ, tải CV và theo dõi tiến trình tuyển dụng.
- Nhà tuyển dụng có thể lọc hồ sơ, đặt lịch phỏng vấn, và tạo bài kiểm tra ngắn.
- Hệ thống gợi ý công việc phù hợp cho ứng viên.
- Giao diện hỗ trợ nhiều thiết bị và phân quyền người dùng.
- Bảo mật hệ thống và cơ sở dữ liệu.

## 6. Các tính năng nâng cao
- **AI đề xuất ứng viên**: Dựa trên hồ sơ và các yêu cầu công việc.
- **Chatbot hỗ trợ**: Giúp ứng viên và nhà tuyển dụng dễ dàng giao tiếp và giải quyết thắc mắc.

## 7. Phương thức bàn giao
Phát triển theo từng module với mốc kiểm tra cố định. Các tính năng quan trọng như đăng tin tuyển dụng và quản lý hồ sơ sẽ được triển khai trước, tiếp theo là các chức năng nâng cao.


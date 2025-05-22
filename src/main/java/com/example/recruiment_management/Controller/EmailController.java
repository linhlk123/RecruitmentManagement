package com.example.recruiment_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recruiment_management.Service.UngTuyenService;

@RestController
@RequestMapping("/send-invite")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UngTuyenService ungTuyenService;

    @PostMapping
    public ResponseEntity<?> sendInvite(@RequestBody EmailRequest emailRequest) {
        try {
            // Tạo nội dung email tự động
            String subject = "THANK YOU FOR YOUR APPLICATION!";
            String text = """
                          Dear Candidate,

                            We are pleased to inform you that your recent application has made a strong impression. Your qualifications and background are highly regarded by our team.

                            As a next step, we would like to invite you to complete an assessment test to further evaluate your suitability for the position.

                            Please kindly complete the test at the following link:
                            https://forms.gle/xg8xM1Wx5HCp8QPx5

                            Following successful completion, we will contact you to arrange an interview and provide further details regarding the role and our work environment.

                            Thank you for your interest in joining our company. We look forward to your participation.

                            Sincerely,

                            The Hiring Team
                            """;

            // Tạo email và thiết lập thông tin
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequest.getEmail());
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("23520846@gm.uit.edu.vn");  // Gửi từ email này

            // Gửi email
            javaMailSender.send(message);
            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"Gửi email thất bại!\"}");
        }
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> rejectApplicant(@PathVariable("id") Long id) {
        try {
            ungTuyenService.rejectApplicant(id);
            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"Từ chối ứng viên thất bại!\"}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteApplicant(@PathVariable("id") Long id) {
        try {
            ungTuyenService.deleteApplicant(id);
            return ResponseEntity.ok().body("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"Xóa ứng viên thất bại!\"}");
        }
    }

    // Lớp yêu cầu gửi email
    public static class EmailRequest {
        private String email;

        // Getter và setter
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

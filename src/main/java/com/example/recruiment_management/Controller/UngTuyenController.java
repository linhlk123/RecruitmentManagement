package com.example.recruiment_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recruiment_management.DTO.UngTuyenDTO;
import com.example.recruiment_management.Model.UngTuyen;
import com.example.recruiment_management.Service.EmailService;
import com.example.recruiment_management.Service.UngTuyenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/application")
public class UngTuyenController {

    @Autowired
    private UngTuyenService ungTuyenService;
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<UngTuyen> nopHoSo(@Valid @RequestBody UngTuyenDTO ungTuyenDTO) {
        UngTuyen ungTuyen = ungTuyenService.nopHoSo(ungTuyenDTO);
        return ResponseEntity.ok(ungTuyen);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<UngTuyen> getUngTuyen(@PathVariable Long id) {
    //     UngTuyen ungTuyen = ungTuyenService.getUngTuyen(id);
    //     return ResponseEntity.ok(ungTuyen);
    // }
    // Lấy danh sách ứng viên theo bài đăng
    @GetMapping("/baidang/{baiDangId}")
    public ResponseEntity<List<UngTuyen>> getUngTuyenByBaiDang(@PathVariable("baiDangId") Long baiDangId) {
        List<UngTuyen> danhSachUngTuyen = ungTuyenService.getUngTuyenByBaiDang(baiDangId);
        return ResponseEntity.ok(danhSachUngTuyen);
    }
    @PostMapping("/reject/{id}")
    public ResponseEntity<?> rejectApplicant(@PathVariable("id") Long id) {
        try {
            // Cập nhật trạng thái ứng viên
            ungTuyenService.rejectApplicant(id);

            // Lấy email ứng viên để gửi mail
            UngTuyen ungTuyen = ungTuyenService.getUngTuyen(id);
            String email = ungTuyen.getEmail();

            // Soạn nội dung mail
            String subject = "THANK YOU FOR YOUR APPLICATION!";
            String text = """
                Dear Candidate,

                After careful review, we regret to inform you that you have not been selected to move forward to the next round. This year, we received an incredibly high volume of strong applications, and while your profile showed great potential, the competition was especially intense.

                Please know this decision is not a reflection of your ability or future potential — but rather the result of very difficult choices we had to make given the limited number of spots. We truly appreciate the time, effort, and passion you put into your application.

                We encourage you to apply again in the future, as we would love to see your continued growth and development.
                Thank you once again for your interest in our company. We wish you all the best in your job search and future endeavors.

                Best regards,

                The Hiring Team
                """;
            
        

            // Gửi mail
            emailService.sendEmail(email, subject, text);

            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteApplicant(@PathVariable("id") Long id) {
        try {
            ungTuyenService.deleteApplicant(id);
            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
    @PostMapping("/interview/{id}")
    public ResponseEntity<?> sendInterviewInvite(@PathVariable("id") Long id) {
        try {
            UngTuyen ungTuyen = ungTuyenService.getUngTuyen(id);
            String email = ungTuyen.getEmail();

            ungTuyenService.inviteApplicant(id);  // Cập nhật trạng thái ĐANG XEM XÉT
            String subject = "INTERVIEW INVITATION";
            String text = """
                Dear Candidate,

                Thank you for your interest in the position. We are pleased to inform you that after reviewing your application and qualifications, we would like to invite you to an interview.

                The interview will be an opportunity to discuss your experience in more detail and to provide you with further insights into the role and our company culture.

                Please find the interview details below:

                - Date: August 30, 2025  
                - Time: 13h00 - 14h00 
                - Location: Online via Microsoft Teams

                Kindly confirm your availability by replying to this email at your earliest convenience.

                If you have any questions or require further information, please do not hesitate to contact us.

                We look forward to meeting you.

                Best regards,

                The Hiring Team
                """;

            emailService.sendEmail(email, subject, text);

            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptApplicant(@PathVariable("id") Long id) {
        try {
            UngTuyen ungTuyen = ungTuyenService.getUngTuyen(id);
            String email = ungTuyen.getEmail();
            
            ungTuyenService.acceptApplicant(id);  // Cập nhật trạng thái CHẤP NHẬN
            String subject = "CONGRATULATIONS! APPLICATION ACCEPTED";
            String text = """
                Dear Candidate,

                We are excited to inform you that you have been accepted for the position! Your qualifications and experience impressed us greatly.

                Our team will contact you soon with the next steps and onboarding information.

                Congratulations and welcome aboard!

                Best regards,

                The Hiring Team
                """;

            emailService.sendEmail(email, subject, text);

            return ResponseEntity.ok("{\"success\": true}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}

package com.example.recruiment_management.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recruiment_management.DTO.LoginRequest;
import com.example.recruiment_management.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    
    // 1. Trả về trang login.html
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html trong templates/
    }

    // Xử lý login và redirect
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request) {
        Map<String, Object> result = userService.login(
            request.getEmail(),
            request.getPassword(),
            request.getRole()
        );

        if ((Boolean) result.get("success")) {
            if (request.getRole().equalsIgnoreCase("recruiter")) {
                return "redirect:/user/recruiter";
            } else if (request.getRole().equalsIgnoreCase("candidate")) {
                return "redirect:/user/candidate";
            } else {
                return "redirect:/user/"; // Role khác, về trang chủ
            }
        } else {
            return "redirect:/user/login?error=true"; // Quay lại login kèm thông báo lỗi
        }
    }


    @GetMapping("/recruiter")
    public String recruiterPage() {
        return "recruiter"; // templates/recruiter.html
    }

    // @GetMapping("/candidate")
    // public String candidatePage() {
    //     return "candidate"; // templates/candidate.html
    // }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Hủy session, logout người dùng
        return "redirect:/home"; // Redirect về trang home
    }
}


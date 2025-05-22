package com.example.recruiment_management.Controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recruiment_management.Model.User;
import com.example.recruiment_management.Service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin-login")
    public String loginPage() {
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String handleLogin(@RequestParam("email") String email,
                          @RequestParam("password") String password,
                              HttpSession session,
                              Model model) {

        User user = userService.loginAdmin(email, password); // ✅ Gọi qua service

        if (user != null) {
            session.setAttribute("loggedInAdmin", user);
            return "redirect:/admin/dashboard";  // ✅ Chuyển hướng đến trang admin-dashboard
        } else {
            model.addAttribute("error", "Sai email, mật khẩu hoặc không có quyền Admin.");
            return "admin-login";
        }
    }

    @PostMapping("/user-login")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    

}


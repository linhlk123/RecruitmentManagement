package com.example.recruiment_management.Controller.Auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/select-role")
    public String selectRole() {
        return "select-role"; 
    }
}
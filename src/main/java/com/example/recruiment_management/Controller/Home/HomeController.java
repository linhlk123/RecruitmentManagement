package com.example.recruiment_management.Controller.Home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "home"; // Spring sẽ render file home.html trong /templates
    }
    @GetMapping("/home")
    public String homePage1() {
        return "home"; // Trả về view home.html trong templates/
    }
}

package com.example.recruiment_management.Controller.Candidate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.recruiment_management.DTO.BaiDangDTO;
import com.example.recruiment_management.Service.BaiDangService;

@Controller
@RequestMapping("/user/candidate")
public class CandidateController {

    @Autowired
    private BaiDangService baiDangService;

    @GetMapping
    public String showCandidatePage(Model model) {
    List<BaiDangDTO> jobs = baiDangService.getAllJobPostDTOs();
        System.out.println(">>> Tổng số job lấy ra: " + jobs.size());
        model.addAttribute("jobList", jobs);
        return "candidate";
    }
}
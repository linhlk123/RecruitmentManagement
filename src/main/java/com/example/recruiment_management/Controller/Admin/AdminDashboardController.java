package com.example.recruiment_management.Controller.Admin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recruiment_management.DTO.DashboardStatsDTO;
import com.example.recruiment_management.Service.DashboardService;


@Controller
@RequestMapping("/admin")
public class AdminDashboardController {
    @Autowired
    private  DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        int year = LocalDate.now().getYear();
        DashboardStatsDTO stats = dashboardService.getStatsForYear(year);

        model.addAttribute("stats", stats);
        model.addAttribute("totalUsers", stats.getTotalUsers());
        model.addAttribute("totalRecruiters", stats.getTotalRecruiters());
        model.addAttribute("totalCandidates", stats.getTotalCandidates());

        model.addAttribute("filter", "year");

        
        return "admin-dashboard";
    }


    // @GetMapping("/dashboard-data")
    // @ResponseBody // Trả JSON thay vì trả view
    // public DashboardStatsDTO getRealTimeStats() {
    //     int year = LocalDate.now().getYear();
    //     return dashboardService.getStatsForYear(year);
    // }

    @GetMapping("/chart-data")
    public Map<String, List<Integer>> getChartData(@RequestParam int year) {
        return dashboardService.getMonthlyChartData(year);
    }

}

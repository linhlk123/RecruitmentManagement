package com.example.recruiment_management.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recruiment_management.DTO.DashboardStatsDTO;
import com.example.recruiment_management.Repository.BaiDangRepository;
import com.example.recruiment_management.Repository.UserRepository;

@Service    // Đánh dấu lớp này là một Service trong Spring
public class DashboardService {
    @Autowired
    private UserRepository userRepository; // Giả sử bạn có một UserService để lấy thông tin người dùng

    @Autowired
    private BaiDangRepository baiDangRepository; // Giả sử bạn có một BaiDangRepository để lấy thông tin bài đăng

    public Long getTotalUsersByYear(int year) {
        return userRepository.countUsersByYear(year); // Gọi phương thức từ UserService
    }
    public Long getTotalRecruitersByYear(int year) {
        return userRepository.countRecruitersByYear(year); // Gọi phương thức từ UserService
    }
    public Long getTotalCandidatesByYear(int year) {
        return userRepository.countCandidatesByYear(year); // Gọi phương thức từ UserService
    }


    //top doanh nghiệp nổi bật trong tháng
    public List<Object[]> getTopDoanhNghiepTheoThang(int year, int month){
        return baiDangRepository.timTopDoanhNghieptheoThang(year, month); // Gọi phương thức từ BaiDangRepository
    }

    public DashboardStatsDTO getStatsForYear(int year){
        DashboardStatsDTO stats = new DashboardStatsDTO();

        stats.setTotalUsers(userRepository.countUsersByYear(year));
        stats.setTotalRecruiters(userRepository.countRecruitersByYear(year));
        stats.setTotalCandidates(userRepository.countCandidatesByYear(year));
        return stats;
    }


public Map<String, List<Integer>> getMonthlyChartData(int year) {
    List<Object[]> recruiters = userRepository.countRecruitersByMonthOfYear(year);
    List<Object[]> candidates = userRepository.countCandidatesByMonthOfYear(year);

    List<Integer> recruiterData = new ArrayList<>(Collections.nCopies(12, 0));
    List<Integer> candidateData = new ArrayList<>(Collections.nCopies(12, 0));

    for (Object[] row : recruiters) {
        int month = ((BigDecimal) row[0]).intValue(); // Oracle trả về BigDecimal
        int count = ((BigDecimal) row[1]).intValue();
        recruiterData.set(month - 1, count);
    }

    for (Object[] row : candidates) {
        int month = ((BigDecimal) row[0]).intValue();
        int count = ((BigDecimal) row[1]).intValue();
        candidateData.set(month - 1, count);
    }

    Map<String, List<Integer>> result = new HashMap<>();
    result.put("recruiters", recruiterData);
    result.put("candidates", candidateData);

    return result;
}

    // public DashboardStatsDTO getStatsForYear(int year) {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }


}
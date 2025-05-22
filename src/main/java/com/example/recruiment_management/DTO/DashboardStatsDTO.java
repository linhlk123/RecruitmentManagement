package com.example.recruiment_management.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardStatsDTO {
    private Long totalUsers;
    private Long totalCompanies;
    private Long totalRecruiters;
    private Long totalCandidates;
    private List<String> topCompanyNames;

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Long getTotalCompanies() {
        return totalCompanies;
    }

    public void setTotalCompanies(Long totalCompanies) {
        this.totalCompanies = totalCompanies;
    }

    public Long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(Long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public List<String> getTopCompanyNames() {
        return topCompanyNames;
    }

    public void setTopCompanyNames(List<String> topCompanyNames) {
        this.topCompanyNames = topCompanyNames;
    }

    public Long getTotalRecruiters() {
        return totalRecruiters;
    }

    public void setTotalRecruiters(Long totalRecruiters) {
        this.totalRecruiters = totalRecruiters;
    }
}
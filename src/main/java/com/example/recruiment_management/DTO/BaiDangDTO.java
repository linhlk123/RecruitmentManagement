package com.example.recruiment_management.DTO;

import java.time.LocalDate;

public class BaiDangDTO {
    private Long id;
    private String jobTitle;
    private String jobSalary;
    private String jobLocation;
    private String jobLevel;
    private int jobQuantity;
    private LocalDate createdAt;
    private LocalDate endDate;
    private Long companyId;        // Dùng khi POST
    private String companyName;    // Dùng khi hiển thị
    private String jobDescription; // Thêm jobDescription
    private String jobRequirements; // Thêm jobRequirements
    private String jobBenefits; // Thêm jobStatus

    public BaiDangDTO() {
    }
    // Constructor dùng cho việc tạo công việc (POST)
    public BaiDangDTO(Long companyId, String jobTitle, String jobSalary, String jobLocation, String jobLevel,
                      int jobQuantity, LocalDate createdAt, LocalDate endDate, String jobDescription, String jobRequirements, String jobBenefits) {
        this.companyId = companyId;
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.jobLevel = jobLevel;
        this.jobQuantity = jobQuantity;
        this.createdAt = createdAt;
        this.endDate = endDate;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.jobBenefits = jobBenefits;
    }

    // Constructor dùng khi hiển thị dữ liệu (lấy tên công ty từ DoanhNghiep)
    public BaiDangDTO(Long id, String jobTitle, String jobSalary, String jobLocation, String jobLevel,
                      int jobQuantity, LocalDate createdAt, LocalDate endDate, String companyName, String jobDescription, String jobRequirements, String jobBenefits) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobSalary = jobSalary;
        this.jobLocation = jobLocation;
        this.jobLevel = jobLevel;
        this.jobQuantity = jobQuantity;
        this.createdAt = createdAt;
        this.endDate = endDate;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.jobRequirements = jobRequirements;
        this.jobBenefits = jobBenefits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public int getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(int jobQuantity) {
        this.jobQuantity = jobQuantity;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getJobBenefits() {
        return jobBenefits;
    }

    public void setJobBenefits(String jobBenefits) {
        this.jobBenefits = jobBenefits;
    }
}
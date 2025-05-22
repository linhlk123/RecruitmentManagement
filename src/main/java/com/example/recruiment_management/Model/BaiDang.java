package com.example.recruiment_management.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "baidang")  // Tên bảng trong cơ sở dữ liệu
public class BaiDang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động sinh ID
    private Long id;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;  // Tiêu đề công việc

    @Column(name = "job_description", nullable = false)
    private String jobDescription;  // Mô tả công việc

    @Column(name = "job_quantity", nullable = false)
    private int jobQuantity;  // Số lượng cần tuyển

    @Column(name = "job_level", nullable = false)
    private String jobLevel;  // Cấp bậc công việc

    @Column(name = "job_salary", nullable = false)
    private String jobSalary;  // Mức lương

    @Column(name = "job_location", nullable = false)
    private String jobLocation;  // Địa điểm làm việc


    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;  // Hạn nộp hồ sơ

    @Column(name = "job_requirements", nullable = false)
    private String jobRequirements;  // Yêu cầu công việc

    @Column(name = "job_benefits")
    private String jobBenefits;  // Quyền lợi

    @Column(name = "created_at")
    private LocalDate createdAt;  // Ngày tạo bài đăng

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private DoanhNghiep doanhNghiep;  // Liên kết với công ty đăng tin

    // Getters and Setters
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public int getJobQuantity() {
        return jobQuantity;
    }

    public void setJobQuantity(int jobQuantity) {
        this.jobQuantity = jobQuantity;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
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


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public DoanhNghiep getDoanhNghiep() {
        return doanhNghiep;
    }

    public void setDoanhNghiep(DoanhNghiep doanhNghiep) {
        this.doanhNghiep = doanhNghiep;
    }
}

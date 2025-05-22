package com.example.recruiment_management.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UngTuyenDTO {
    @NotNull(message = "ID bài đăng là bắt buộc")
    private Long baiDangId;

    @NotBlank(message = "Họ tên là bắt buộc")
    private String hoTen;

    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại là bắt buộc")
    private String soDienThoai;


    @NotBlank(message = "URL CV là bắt buộc")
    private String cvUrl;

    public Long getBaiDangId() {
        return baiDangId;
    }

    public void setBaiDangId(Long baiDangId) {
        this.baiDangId = baiDangId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCvUrl() {
        return cvUrl;
    }

    public void setCvUrl(String cvUrl) {
        this.cvUrl = cvUrl;
    }
} 
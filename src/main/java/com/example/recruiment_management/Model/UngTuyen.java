package com.example.recruiment_management.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ungtuyen")
public class UngTuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "baidang_id", nullable = false)
    private BaiDang baiDang;

    @Column(nullable = false)
    private String hoTen;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String soDienThoai;


    @Column(nullable = false)
    private String cvUrl;

    @Column(nullable = false)
    private LocalDateTime ngayUngTuyen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrangThaiUngTuyen trangThai = TrangThaiUngTuyen.CHO_XU_LY;

    public UngTuyen() {
    }

    public void setBaiDang(BaiDang baiDang) {
        this.baiDang = baiDang;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public BaiDang getBaiDang() {
        return baiDang;
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

    public LocalDateTime getNgayUngTuyen() {
        return ngayUngTuyen;
    }

    public void setNgayUngTuyen(LocalDateTime ngayUngTuyen) {
        this.ngayUngTuyen = ngayUngTuyen;
    }

    public TrangThaiUngTuyen getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThaiUngTuyen trangThai) {
        this.trangThai = trangThai;
    }

    public enum TrangThaiUngTuyen {
        CHO_XU_LY,
        DANG_XEM_XET,
        CHAP_NHAN,
        TU_CHOI
    }
} 
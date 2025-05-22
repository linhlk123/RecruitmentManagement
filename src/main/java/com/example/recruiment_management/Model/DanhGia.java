package com.example.recruiment_management.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UngTuyen ungTuyen;

    private int diemKyNangChuyenMon;  // 1-5
    public int getDiemKyNangChuyenMon() {
        return diemKyNangChuyenMon;
    }

    public void setDiemKyNangChuyenMon(int diemKyNangChuyenMon) {
        this.diemKyNangChuyenMon = diemKyNangChuyenMon;
    }

    private int diemKyNangGiaoTiep;   // 1-5

    @Column(length = 1000)
    private String nhanXet;

    @Column(length = 500)
    private String ketLuan;

    private LocalDateTime ngayDanhGia;

    // getters/setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UngTuyen getUngTuyen() {
        return ungTuyen;
    }

    public void setUngTuyen(UngTuyen ungTuyen) {
        this.ungTuyen = ungTuyen;
    }

    public int getDiemKyNangGiaoTiep() {
        return diemKyNangGiaoTiep;
    }

    public void setDiemKyNangGiaoTiep(int diemKyNangGiaoTiep) {
        this.diemKyNangGiaoTiep = diemKyNangGiaoTiep;
    }

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }

    public String getKetLuan() {
        return ketLuan;
    }

    public void setKetLuan(String ketLuan) {
        this.ketLuan = ketLuan;
    }

    public LocalDateTime getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(LocalDateTime ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }
}

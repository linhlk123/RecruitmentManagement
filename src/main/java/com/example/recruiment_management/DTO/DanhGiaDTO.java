package com.example.recruiment_management.DTO;

public class DanhGiaDTO {
    private Long ungTuyenId;
    private int diemKyNangChuyenMon;
    private int diemKyNangGiaoTiep;
    private String nhanXet;
    private String ketLuan;

    public Long getUngTuyenId() {
        return ungTuyenId;
    }

    public void setUngTuyenId(Long ungTuyenId) {
        this.ungTuyenId = ungTuyenId;
    }

    public int getDiemKyNangChuyenMon() {
        return diemKyNangChuyenMon;
    }

    public void setDiemKyNangChuyenMon(int diemKyNangChuyenMon) {
        this.diemKyNangChuyenMon = diemKyNangChuyenMon;
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
}

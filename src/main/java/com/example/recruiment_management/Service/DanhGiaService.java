package com.example.recruiment_management.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.recruiment_management.DTO.DanhGiaDTO;
import com.example.recruiment_management.Model.DanhGia;
import com.example.recruiment_management.Model.UngTuyen;
import com.example.recruiment_management.Repository.DanhGiaRepository;
import com.example.recruiment_management.Repository.UngTuyenRepository;

@Service
public class DanhGiaService {

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    private UngTuyenRepository ungTuyenRepository;

    @Transactional
    public DanhGia saveEvaluation(DanhGiaDTO dto) {
        UngTuyen ungTuyen = ungTuyenRepository.findById(dto.getUngTuyenId())
                .orElseThrow(() -> new RuntimeException("Ứng viên không tồn tại"));

        DanhGia danhGia = new DanhGia();
        danhGia.setUngTuyen(ungTuyen);
        danhGia.setDiemKyNangChuyenMon(dto.getDiemKyNangChuyenMon());
        danhGia.setDiemKyNangGiaoTiep(dto.getDiemKyNangGiaoTiep());
        danhGia.setNhanXet(dto.getNhanXet());
        danhGia.setKetLuan(dto.getKetLuan());
        danhGia.setNgayDanhGia(LocalDateTime.now());

        return danhGiaRepository.save(danhGia);
    }
}

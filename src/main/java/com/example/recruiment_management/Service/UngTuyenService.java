package com.example.recruiment_management.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.recruiment_management.DTO.UngTuyenDTO;
import com.example.recruiment_management.Model.BaiDang;
import com.example.recruiment_management.Model.UngTuyen;
import com.example.recruiment_management.Repository.BaiDangRepository;
import com.example.recruiment_management.Repository.UngTuyenRepository;

@Service
public class UngTuyenService {

    @Autowired
    private UngTuyenRepository ungTuyenRepository;

    @Autowired
    private BaiDangRepository baiDangRepository;

    @Transactional
    public UngTuyen nopHoSo(UngTuyenDTO ungTuyenDTO) {
        BaiDang baiDang = baiDangRepository.findById(ungTuyenDTO.getBaiDangId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy bài đăng"));

        UngTuyen ungTuyen = new UngTuyen();
        ungTuyen.setBaiDang(baiDang);
        ungTuyen.setHoTen(ungTuyenDTO.getHoTen());
        ungTuyen.setEmail(ungTuyenDTO.getEmail());
        ungTuyen.setSoDienThoai(ungTuyenDTO.getSoDienThoai());
        ungTuyen.setCvUrl(ungTuyenDTO.getCvUrl());
        ungTuyen.setNgayUngTuyen(LocalDateTime.now());
        ungTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.CHO_XU_LY);

        return ungTuyenRepository.save(ungTuyen);
    }

    public UngTuyen getUngTuyen(Long id) {
        return ungTuyenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy hồ sơ ứng tuyển"));
    }

    public List<UngTuyen> getUngTuyenByBaiDang(Long baiDangId) {
        return ungTuyenRepository.findByBaiDangId(baiDangId);
    }
    @Transactional
    public void rejectApplicant(Long id) {
        UngTuyen ungTuyen = ungTuyenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ứng viên không tồn tại"));
        ungTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.TU_CHOI);
        ungTuyenRepository.save(ungTuyen);
    }

    @Transactional
    public void deleteApplicant(Long id) {
        UngTuyen ungTuyen = ungTuyenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ứng viên không tồn tại"));
        ungTuyenRepository.delete(ungTuyen);
    }
    @Transactional
    public void acceptApplicant(Long id) {
        UngTuyen ungTuyen = ungTuyenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ứng viên không tồn tại"));
        ungTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.CHAP_NHAN);
        ungTuyenRepository.save(ungTuyen);
    }

    @Transactional
    public void inviteApplicant(Long id) {
        UngTuyen ungTuyen = ungTuyenRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Ứng viên không tồn tại"));
        ungTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.DANG_XEM_XET);
        ungTuyenRepository.save(ungTuyen);
    }


} 
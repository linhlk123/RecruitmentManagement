package com.example.recruiment_management.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recruiment_management.DTO.BaiDangDTO;
import com.example.recruiment_management.Model.BaiDang;
import com.example.recruiment_management.Repository.BaiDangRepository;

@Service
public class BaiDangService {
    @Autowired
    private BaiDangRepository baiDangRepository;

    // Lấy tất cả các tin tuyển dụng và chuyển thành BaiDangDTO
    public List<BaiDangDTO> getAllJobPostDTOs() {
        return baiDangRepository.findAll().stream().map(bd -> {
            // Lấy tên công ty từ DoanhNghiep bằng companyId
            String companyName = bd.getDoanhNghiep() != null ? bd.getDoanhNghiep().getName() : "Không xác định";
            
            // Trả về BaiDangDTO với tất cả các thuộc tính bao gồm jobDescription và jobRequirements
            return new BaiDangDTO(
                    bd.getId(),
                    bd.getJobTitle(),
                    bd.getJobSalary(),
                    bd.getJobLocation(),
                    bd.getJobLevel(),
                    bd.getJobQuantity(),
                    bd.getCreatedAt(),
                    bd.getEndDate(),
                    companyName,
                    bd.getJobDescription(),  // Thêm jobDescription
                    bd.getJobRequirements()  // Thêm jobRequirements
            );
        }).collect(Collectors.toList());
    }

    // Lưu tin tuyển dụng vào database
    public void saveJobPost(BaiDang baiDang) {
        baiDangRepository.save(baiDang);
    }

    // Lấy tất cả tin tuyển dụng
    public List<BaiDang> getAllJobPosts() {
        return baiDangRepository.findAll();
    }
}

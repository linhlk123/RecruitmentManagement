package com.example.recruiment_management.Controller.BaiDang;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.recruiment_management.DTO.BaiDangDTO;
import com.example.recruiment_management.Model.BaiDang;
import com.example.recruiment_management.Model.DoanhNghiep;
import com.example.recruiment_management.Repository.DoanhNghiepRepository;
import com.example.recruiment_management.Service.BaiDangService;

@Controller     
@RequestMapping("/recruiter")
public class BaiDangController {
    
    @Autowired
    private BaiDangService baiDangService;
    
    @Autowired
    private DoanhNghiepRepository doanhNghiepRepository;

    // Endpoint hiển thị trang recruiter (Danh sách tin tuyển dụng)
    @GetMapping
    public String showRecruiterPage(Model model) {
        // Lấy danh sách tin tuyển dụng từ database và chuyển thành BaiDangDTO
        List<BaiDangDTO> jobPosts = baiDangService.getAllJobPostDTOs();
        model.addAttribute("jobPosts", jobPosts); // Trả về danh sách dưới dạng DTO
        return "recruiter"; // Trả về trang recruiter.html
    }

    // Tạo bài đăng tuyển dụng mới
@PostMapping("/create-job")
@ResponseBody
public ResponseEntity<?> createJobPost(@RequestBody BaiDangDTO dto) {
    try {
        Optional<DoanhNghiep> dnOpt = doanhNghiepRepository.findById(dto.getCompanyId());
        if (dnOpt.isEmpty()) return ResponseEntity.badRequest().body("Không tìm thấy công ty.");

        BaiDang baiDang = new BaiDang();
        baiDang.setJobTitle(dto.getJobTitle());
        baiDang.setJobSalary(dto.getJobSalary());
        baiDang.setJobLocation(dto.getJobLocation());
        baiDang.setJobLevel(dto.getJobLevel());
        baiDang.setJobQuantity(dto.getJobQuantity());
        baiDang.setCreatedAt(LocalDate.now());
        baiDang.setEndDate(dto.getEndDate());
        baiDang.setJobDescription(dto.getJobDescription());  // Set jobDescription
        baiDang.setJobRequirements(dto.getJobRequirements());  // Set jobRequirements

        baiDang.setDoanhNghiep(dnOpt.get());

        baiDangService.saveJobPost(baiDang); // Lưu tin tuyển dụng
        return ResponseEntity.ok("Lưu thành công");
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Lỗi: " + e.getMessage());
    }
}

    // Lấy danh sách tất cả công việc dưới dạng DTO
    @GetMapping("/job-posts")
    public ResponseEntity<List<BaiDang>> getAllJobPosts() {
        try {
            // Lấy tất cả công việc
            List<BaiDang> baiDangList = baiDangService.getAllJobPosts();  // Dùng service để lấy dữ liệu
            return ResponseEntity.ok(baiDangList);  // Trả về danh sách bài đăng
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(null);  // Trả về lỗi nếu không lấy được dữ liệu
        }
    }
}

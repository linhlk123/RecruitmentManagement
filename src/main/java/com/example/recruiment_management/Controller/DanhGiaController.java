package com.example.recruiment_management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.recruiment_management.DTO.DanhGiaDTO;
import com.example.recruiment_management.Model.DanhGia;
import com.example.recruiment_management.Service.DanhGiaService;

@RestController
@RequestMapping("/evaluation")
public class DanhGiaController {

    @Autowired
    private DanhGiaService danhGiaService;

    @PostMapping("/save")
    public ResponseEntity<?> saveEvaluation(@RequestBody DanhGiaDTO dto) {
        try {
            DanhGia saved = danhGiaService.saveEvaluation(dto);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"success\": false, \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}

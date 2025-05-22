package com.example.recruiment_management.Controller;

import com.example.recruiment_management.DTO.UngTuyenDTO;
import com.example.recruiment_management.Model.BaiDang;
import com.example.recruiment_management.Model.UngTuyen;
import com.example.recruiment_management.Service.UngTuyenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UngTuyenController.class)
class UngTuyenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UngTuyenService ungTuyenService;

    @Test
    void nopHoSo_Success() throws Exception {
        // Arrange
        UngTuyenDTO ungTuyenDTO = new UngTuyenDTO();
        ungTuyenDTO.setBaiDangId(1L);
        ungTuyenDTO.setHoTen("Nguyen Van A");
        ungTuyenDTO.setEmail("nguyenvana@example.com");
        ungTuyenDTO.setSoDienThoai("0123456789");
        ungTuyenDTO.setCvUrl("http://example.com/cv.pdf");

        UngTuyen mockUngTuyen = new UngTuyen();
        mockUngTuyen.setId(1L);
        mockUngTuyen.setHoTen(ungTuyenDTO.getHoTen());
        mockUngTuyen.setEmail(ungTuyenDTO.getEmail());
        mockUngTuyen.setSoDienThoai(ungTuyenDTO.getSoDienThoai());
        mockUngTuyen.setCvUrl(ungTuyenDTO.getCvUrl());
        mockUngTuyen.setNgayUngTuyen(LocalDateTime.now());
        mockUngTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.CHO_XU_LY);

        when(ungTuyenService.nopHoSo(any(UngTuyenDTO.class))).thenReturn(mockUngTuyen);

        // Act & Assert
        mockMvc.perform(post("/api/ungtuyen")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ungTuyenDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hoTen").value(ungTuyenDTO.getHoTen()))
                .andExpect(jsonPath("$.email").value(ungTuyenDTO.getEmail()))
                .andExpect(jsonPath("$.soDienThoai").value(ungTuyenDTO.getSoDienThoai()))
                .andExpect(jsonPath("$.cvUrl").value(ungTuyenDTO.getCvUrl()))
                .andExpect(jsonPath("$.trangThai").value("CHO_XU_LY"));
    }

    @Test
    void getUngTuyen_Success() throws Exception {
        // Arrange
        UngTuyen mockUngTuyen = new UngTuyen();
        mockUngTuyen.setId(1L);
        mockUngTuyen.setHoTen("Nguyen Van A");
        mockUngTuyen.setEmail("nguyenvana@example.com");
        mockUngTuyen.setSoDienThoai("0123456789");
        mockUngTuyen.setCvUrl("http://example.com/cv.pdf");
        mockUngTuyen.setNgayUngTuyen(LocalDateTime.now());
        mockUngTuyen.setTrangThai(UngTuyen.TrangThaiUngTuyen.CHO_XU_LY);

        when(ungTuyenService.getUngTuyen(1L)).thenReturn(mockUngTuyen);

        // Act & Assert
        mockMvc.perform(get("/api/ungtuyen/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.hoTen").value(mockUngTuyen.getHoTen()))
                .andExpect(jsonPath("$.email").value(mockUngTuyen.getEmail()))
                .andExpect(jsonPath("$.soDienThoai").value(mockUngTuyen.getSoDienThoai()))
                .andExpect(jsonPath("$.cvUrl").value(mockUngTuyen.getCvUrl()))
                .andExpect(jsonPath("$.trangThai").value("CHO_XU_LY"));
    }

    @Test
    void getUngTuyenByBaiDang_Success() throws Exception {
        // Arrange
        List<UngTuyen> mockUngTuyenList = Arrays.asList(
            new UngTuyen(),
            new UngTuyen()
        );
        when(ungTuyenService.getUngTuyenByBaiDang(1L)).thenReturn(mockUngTuyenList);

        // Act & Assert
        mockMvc.perform(get("/api/ungtuyen/baidang/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
} 
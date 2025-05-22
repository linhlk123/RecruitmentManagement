package com.example.recruiment_management.Service;

import com.example.recruiment_management.DTO.UngTuyenDTO;
import com.example.recruiment_management.Model.BaiDang;
import com.example.recruiment_management.Model.UngTuyen;
import com.example.recruiment_management.Repository.BaiDangRepository;
import com.example.recruiment_management.Repository.UngTuyenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UngTuyenServiceTest {

    @Mock
    private UngTuyenRepository ungTuyenRepository;

    @Mock
    private BaiDangRepository baiDangRepository;

    @InjectMocks
    private UngTuyenService ungTuyenService;

    private BaiDang mockBaiDang;
    private UngTuyenDTO mockUngTuyenDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup mock BaiDang
        mockBaiDang = new BaiDang();
        mockBaiDang.setId(1L);
        mockBaiDang.setJobTitle("Software Developer");

        // Setup mock UngTuyenDTO
        mockUngTuyenDTO = new UngTuyenDTO();
        mockUngTuyenDTO.setBaiDangId(1L);
        mockUngTuyenDTO.setHoTen("Nguyen Van A");
        mockUngTuyenDTO.setEmail("nguyenvana@example.com");
        mockUngTuyenDTO.setSoDienThoai("0123456789");
        mockUngTuyenDTO.setCvUrl("http://example.com/cv.pdf");
    }

    @Test
    void nopHoSo_Success() {
        // Arrange
        when(baiDangRepository.findById(1L)).thenReturn(Optional.of(mockBaiDang));
        when(ungTuyenRepository.save(any(UngTuyen.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        UngTuyen result = ungTuyenService.nopHoSo(mockUngTuyenDTO);

        // Assert
        assertNotNull(result);
        assertEquals(mockUngTuyenDTO.getHoTen(), result.getHoTen());
        assertEquals(mockUngTuyenDTO.getEmail(), result.getEmail());
        assertEquals(mockUngTuyenDTO.getSoDienThoai(), result.getSoDienThoai());
        assertEquals(mockUngTuyenDTO.getCvUrl(), result.getCvUrl());
        assertEquals(UngTuyen.TrangThaiUngTuyen.CHO_XU_LY, result.getTrangThai());
        assertNotNull(result.getNgayUngTuyen());

        verify(baiDangRepository).findById(1L);
        verify(ungTuyenRepository).save(any(UngTuyen.class));
    }

    @Test
    void nopHoSo_BaiDangNotFound() {
        // Arrange
        when(baiDangRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ungTuyenService.nopHoSo(mockUngTuyenDTO));
        verify(baiDangRepository).findById(1L);
        verify(ungTuyenRepository, never()).save(any(UngTuyen.class));
    }

    @Test
    void getUngTuyen_Success() {
        // Arrange
        UngTuyen mockUngTuyen = new UngTuyen();
        mockUngTuyen.setId(1L);
        when(ungTuyenRepository.findById(1L)).thenReturn(Optional.of(mockUngTuyen));

        // Act
        UngTuyen result = ungTuyenService.getUngTuyen(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(ungTuyenRepository).findById(1L);
    }

    @Test
    void getUngTuyen_NotFound() {
        // Arrange
        when(ungTuyenRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> ungTuyenService.getUngTuyen(1L));
        verify(ungTuyenRepository).findById(1L);
    }

    @Test
    void getUngTuyenByBaiDang_Success() {
        // Arrange
        List<UngTuyen> mockUngTuyenList = Arrays.asList(
            new UngTuyen(),
            new UngTuyen()
        );
        when(ungTuyenRepository.findByBaiDangId(1L)).thenReturn(mockUngTuyenList);

        // Act
        List<UngTuyen> result = ungTuyenService.getUngTuyenByBaiDang(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(ungTuyenRepository).findByBaiDangId(1L);
    }
} 
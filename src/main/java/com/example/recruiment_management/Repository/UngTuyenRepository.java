package com.example.recruiment_management.Repository;

import com.example.recruiment_management.Model.UngTuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UngTuyenRepository extends JpaRepository<UngTuyen, Long> {
    List<UngTuyen> findByBaiDangId(Long baiDangId);
} 
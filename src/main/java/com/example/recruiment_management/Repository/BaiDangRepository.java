package com.example.recruiment_management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.recruiment_management.Model.BaiDang;


@Repository
public interface BaiDangRepository extends JpaRepository<BaiDang, Long> {

    @Query("SELECT bd.doanhNghiep, COUNT(bd) AS totalPosts " +
           "FROM BaiDang bd " +
           "WHERE YEAR(bd.createdAt) = :year AND MONTH(bd.createdAt) = :month " +
           "GROUP BY bd.doanhNghiep " +
           "ORDER BY totalPosts DESC")
    List<Object[]> timTopDoanhNghieptheoThang(@Param("year") int year, @Param("month") int month);




}
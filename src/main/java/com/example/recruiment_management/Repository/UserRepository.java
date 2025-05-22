package com.example.recruiment_management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.recruiment_management.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email); // Optional: dùng để load user sau khi xác thực

    //đếm số user theo năm
    @Query("SELECT COUNT(u) FROM User u WHERE year(u.createdAt) = :year")
    Long countUsersByYear(@Param("year") int year);
    
    //đếm nhà tuyển dụng theo năm
    @Query("SELECT COUNT(u) FROM User u WHERE year(u.createdAt) = :year AND u.role.name='NHÀ TUYỂN DỤNG'")
    Long countRecruitersByYear(@Param("year") int year);

    //đếm ứng viên theo năm
    @Query("SELECT COUNT(u) FROM User u WHERE year(u.createdAt) = :year AND u.role.name='ỨNG VIÊN'")
    Long countCandidatesByYear(@Param("year") int year);

    // //đếm ứng viên theo tháng
    // @Query("SELECT COUNT(u) FROM User u WHERE year(u.createdAt) = :year AND month(u.createdAt) = :month AND u.role.name='ỨNG VIÊN'")
    // Long countCandidatesByMonth(@Param("year") int year, @Param("month") int month);


// Tổng số nhà tuyển dụng theo từng tháng trong năm
@Query(value = """
    SELECT 
        TO_NUMBER(TO_CHAR(u.CREATED_AT, 'MM')) AS month,
        COUNT(*) 
    FROM USERS u 
    JOIN ROLES r ON u.ROLE_ID = r.ID 
    WHERE r.NAME = 'RECRUITER'
      AND EXTRACT(YEAR FROM u.CREATED_AT) = :year
    GROUP BY TO_NUMBER(TO_CHAR(u.CREATED_AT, 'MM'))
    ORDER BY month
    """, nativeQuery = true)
    List<Object[]> countRecruitersByMonthOfYear(@Param("year") int year);


    // Tổng số ứng viên theo từng tháng trong năm
    @Query(value = """
    SELECT 
        TO_NUMBER(TO_CHAR(u.CREATED_AT, 'MM')) AS month,
        COUNT(*) 
    FROM USERS u 
    JOIN ROLES r ON u.ROLE_ID = r.ID 
    WHERE r.NAME = 'CANDIDATE'
      AND EXTRACT(YEAR FROM u.CREATED_AT) = :year
    GROUP BY TO_NUMBER(TO_CHAR(u.CREATED_AT, 'MM'))
    ORDER BY month
    """, nativeQuery = true)
    List<Object[]> countCandidatesByMonthOfYear(@Param("year") int year);



}



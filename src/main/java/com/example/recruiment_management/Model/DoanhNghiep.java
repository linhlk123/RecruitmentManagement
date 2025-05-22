package com.example.recruiment_management.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doanhnghiep")
@NoArgsConstructor
public class DoanhNghiep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "doanhNghiep")
    private List<BaiDang> danhsachBaiDang;


    public DoanhNghiep(List<BaiDang> danhsachBaiDang, String email, Long id, String name,  Role role) {
        this.danhsachBaiDang = danhsachBaiDang;
        this.email = email;
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setDanhsachBaiDang(List<BaiDang> danhsachBaiDang) {
        this.danhsachBaiDang = danhsachBaiDang;
    }

    public List<BaiDang> getDanhsachBaiDang() {
        return danhsachBaiDang;
    }

}

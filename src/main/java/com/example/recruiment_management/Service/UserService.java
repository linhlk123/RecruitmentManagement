package com.example.recruiment_management.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recruiment_management.Model.User;
import com.example.recruiment_management.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User loginAdmin(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null && "ADMIN".equalsIgnoreCase(user.getRole().getName())) {
            return user;
        }
        return null;
    }

    // Login cho các vai trò khác (Recruiter, Candidate, Staff, v.v)
    public Map<String, Object> login(String email, String password, String roleName) {
        Map<String, Object> result = new HashMap<>();

        User user = userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            String userRole = user.getRole().getName(); // User có liên kết với Role

            if (userRole.equalsIgnoreCase(roleName)){                
                result.put("success", true);
                result.put("message", "Đăng nhập thành công.");
            } else {
                result.put("success", false);
                result.put("message", "Bạn không có quyền đăng nhập với vai trò này.");
            }
        } else {
            result.put("success", false);
            result.put("message", "Email hoặc mật khẩu không đúng.");
        }

        return result;
    }
}

package com.example.recruiment_management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("23520846@gm.uit.edu.vn");  // Email gửi
        message.setTo(to);  // Email nhận
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}

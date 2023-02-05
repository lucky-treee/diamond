package com.luckytree.member_service.email.application.service;

import com.luckytree.member_service.email.domain.Mail;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    private JavaMailSender javaMailSender;
    private static final String FROM_ADDRESS = "ihcho620@gmail.com";

    public void sendMail(Mail mail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail.getAddress());
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        javaMailSender.send(message);
    }
}

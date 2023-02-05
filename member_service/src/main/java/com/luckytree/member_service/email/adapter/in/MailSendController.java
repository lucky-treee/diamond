package com.luckytree.member_service.email.adapter.in;



import com.luckytree.member_service.email.application.service.MailService;
import com.luckytree.member_service.email.domain.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MailSendController {

    @Autowired
    private final MailService mailService;

    @PostMapping("/mail")
    public void execMail(@RequestBody Mail mail){
        mailService.sendMail(mail);
    }
}

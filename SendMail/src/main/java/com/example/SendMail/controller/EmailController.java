package com.example.SendMail.controller;

import com.example.SendMail.EmailDetails;
import com.example.SendMail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendmail")
    public String sentMail(@RequestBody EmailDetails details) {
        String result = emailService.sendSimpleMail(details);
        return result;
    }
    @PostMapping("/sendmailwithattachment")
    public String sendEmailWithAttachment(@RequestBody EmailDetails details) {
        String status = emailService.sendMailWithAttachment(details);
        return status;
    }
}

package com.example.SendMail.service;

import com.example.SendMail.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Override
    public String sendSimpleMail(EmailDetails details) {

        try {
            //create a simple mail message
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //setting up the necessary details
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(details.getRecipitent());
            simpleMailMessage.setText(details.getMsgBody());
            simpleMailMessage.setSubject(details.getSubject());

            //sending mail
            javaMailSender.send(simpleMailMessage);
            return "Mail send successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Mail sent failed";
        }

    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            //setting multipart as true for attachment to send
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipitent());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(details.getSubject());

            //Adding the attachment
            FileSystemResource resource = new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(resource.getFilename(), resource);
            //sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Mail Sent Failed";
        }
    }
}

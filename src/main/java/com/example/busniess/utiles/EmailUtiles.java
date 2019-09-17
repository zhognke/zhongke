package com.example.busniess.utiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailUtiles {

    @Value("${${spring.mail.username}")
    private static String from;
    @Autowired
    private static JavaMailSender mailSender;

    public static Boolean sendEmail(String subject, String email, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setText(text);
        mimeMessageHelper.setFrom(from);

        return true;
    }
}

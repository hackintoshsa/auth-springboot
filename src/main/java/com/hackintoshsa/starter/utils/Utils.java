package com.hackintoshsa.starter.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

@Component
public class Utils {
    @Autowired
    Environment env;

    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.mail.port")))); // Port for TLS (Use 465 for SSL)
        mailSender.setUsername(env.getProperty("spring.mail.username"));
        mailSender.setPassword("spring.mail.password");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        props.put("mail.smtp.writetimeout", "5000");

        return mailSender;
    }

    public void sendResetEmail(String email, String token) throws MessagingException {
        MimeMessage mimeMessage = mailSender().createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String resetLink = "https://example.com/reset-password?token=" + token;
        String emailContent = String.format(
                "<p>You are receiving this because you (or someone else) have requested the reset of the password for your account.</p>" +
                        "<p>Please click on the following link, or paste this into your browser to complete the process:</p>" +
                        "<a href='%s'>Reset Password</a>" +
                        "<p>If you did not request this, please ignore this email and your password will remain unchanged.</p>",
                resetLink
        );
        helper.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        helper.setCc(Objects.requireNonNull(env.getProperty("spring.mail.username")));
        helper.setTo(email);
        helper.setSubject("Password Reset Request");
        helper.setText(emailContent, true);

        mailSender().send(mimeMessage);
    }
}

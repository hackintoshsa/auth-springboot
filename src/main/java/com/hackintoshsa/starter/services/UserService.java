package com.hackintoshsa.starter.services;

import com.hackintoshsa.starter.security.JwtConfig;
import com.hackintoshsa.starter.models.User;
import com.hackintoshsa.starter.repositories.UserRepository;
import com.hackintoshsa.starter.security.PasswordEncoderConfig;
import com.hackintoshsa.starter.utils.Utils;
import jakarta.mail.MessagingException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    @Autowired
    private JwtConfig tokenService;
    @Autowired
    private Utils utils;

    public Map<String, Object> registerUser(User user) {
        Map<String, Object> response = new HashMap<>();
        if(userRepository.findOneByEmail(user.getEmail()) != null) {
            response.put("message", "User already exists");
            response.put("Status", HttpStatus.CONFLICT);
        } else{
            String encodedPassword = passwordEncoderConfig.passEncoder().encode(user.getPassword());
            String token = tokenService.generateToken(user);
            user.setPassword(encodedPassword);
            userRepository.save(user);
            user.setPassword(null); //Not sending the password to response but saved in DB
            response.put("message", "User registered successfully");
            response.put("Status", HttpStatus.OK);
            response.put("data", user);
            response.put("token", token);
        }
        return response;
    }
    public Map<String, Object> loginUser(String email, String password) {
        Map<String, Object> response = new HashMap<>();
        User existingUser = userRepository.findOneByEmail(email);

        if (userRepository.findOneByEmail(email) == null) {
            response.put("message", "Incorrect Email");
            response.put("Status", HttpStatus.BAD_REQUEST);
        }
        if (!existingUser.getVerified()) {
            response.put("message", "Not Verified, Please Check Emails");
            response.put("Status", HttpStatus.ACCEPTED);
        }
        boolean isPasswordMatches = passwordEncoderConfig.comparePassword(password, existingUser.getPassword());
        if (isPasswordMatches) {
            existingUser.setPassword(null);
            response.put("status", HttpStatus.OK);
            response.put("data", existingUser);
            response.put("token", tokenService.generateToken(existingUser));
        }else {
            response.put("status", HttpStatus.BAD_REQUEST);
            response.put("token",  tokenService.generateToken(existingUser));
            response.put("message", "Incorrect Password");
        }
        return response;
    }
    public Map<String, Object>  forgotPassword(String email) throws MessagingException {
        Map<String, Object> response = new HashMap<>();
        User existingUser = userRepository.findOneByEmail(email);
        if (existingUser ==null) {
            response.put("status", HttpStatus.NOT_FOUND);
            response.put("message", "No account with that email address exists");
        }else{
           String token = UUID.randomUUID().toString();
           existingUser.setResetPasswordToken(token);
           existingUser.setResetPasswordExpires(new Date(System.currentTimeMillis() + 3600000));
           userRepository.save(existingUser);

           try{
               utils.sendResetEmail(existingUser.getEmail(), token);
               response.put("status", HttpStatus.OK);
               response.put("message", "Password reset email sent successfully");
               response.put("token", token);
               response.put("data", existingUser);
               response.put("reset", "info, An e-mail has been sent to " + existingUser.getEmail() +  "with further instructions.");
           } catch (MessagingException e) {
               throw new MessagingException(e.getMessage());
           }

        }
        return response;
    }

}

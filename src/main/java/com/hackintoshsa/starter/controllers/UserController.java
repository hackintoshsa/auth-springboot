package com.hackintoshsa.starter.controllers;

import com.hackintoshsa.starter.models.User;
import com.hackintoshsa.starter.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity <Map<String, Object>> registerUser(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity <Map<String, Object>> loginUser(@RequestBody User user) {
      return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(user.getEmail(), user.getPassword()));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity <Map<String, Object>> forgotUser(@RequestBody User user) throws MessagingException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.forgotPassword(user.getEmail()));
    }

}

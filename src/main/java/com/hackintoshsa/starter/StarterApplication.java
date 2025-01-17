package com.hackintoshsa.starter;

import com.hackintoshsa.starter.models.User;
import com.hackintoshsa.starter.services.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@SpringBootApplication
@Log
public class StarterApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user = new User().builder()
                .firstName("")
                .lastName("")
                .gender("")
                .email("")
                .password(encoder.encode(""))
                .build();

        // Call the service to register the user
        Map<String, Object> response = userService.registerUser(user);
        // Log the entire response map
        log.info("User registration response: " + response);

        // Alternatively, if you want to log a specific field in the response, for example:
        if (response != null && response.containsKey("status")) {
            log.info("Registration status: " + response.get("status"));
        } else {
            log.warning("Response does not contain 'status' key.");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

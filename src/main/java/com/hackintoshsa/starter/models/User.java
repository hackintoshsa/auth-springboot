package com.hackintoshsa.starter.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Builder
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String currentImage = "img/avatars/avatar.png";

    private String smsMobile;

    private Boolean verified = false;

    private String idVerificationStatus = "Pending";

    private String documentVerificationStatus = "Pending";

    private List<String> travels;

    private String gender;


    private String resetPasswordToken;

    private Date resetPasswordExpires;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

}

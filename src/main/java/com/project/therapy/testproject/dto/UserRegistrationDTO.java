package com.project.therapy.testproject.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String name;
    private String username;
    private String password;
    private String confirm_password;
    private String email;
    private String phone;
    private String role;

}

package com.project.therapy.testproject.controller;

import com.project.therapy.testproject.dto.UserRegistrationDTO;
import com.project.therapy.testproject.model.User;
import com.project.therapy.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @RequestMapping("/register")
    @ResponseBody
    public User registerUser(@RequestBody UserRegistrationDTO user_details){
        return userService.saveUser(user_details);
    }
}

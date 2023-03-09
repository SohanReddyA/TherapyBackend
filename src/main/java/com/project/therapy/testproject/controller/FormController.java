package com.project.therapy.testproject.controller;

import com.project.therapy.testproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.project.therapy.testproject.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class FormController {
    @Autowired
    private UserService userService;

    @RequestMapping("/show_form")
    @ResponseBody
    public String showForm(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if(user.isFirstTime()) {
            user.setFirstTime(false);
            userService.updateUser(user);
            return "Showing Form";
        }
        return "Not Showing Form";
    }
}

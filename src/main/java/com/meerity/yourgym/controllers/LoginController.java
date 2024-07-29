package com.meerity.yourgym.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(required = false) String error,
            @RequestParam(required = false) String logout,
            @RequestParam(required = false) String registered,
                                   Model model) {
        String errorMessage = null;
        String successMessage = null;
        if (logout != null){
            successMessage = "You have logged out successfully";
        } else if (registered != null) {
            successMessage = "You have registered successfully";
        }
        if (error != null) {
            errorMessage = "Invalid username or password";
        }
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
        return "login";
    }
}

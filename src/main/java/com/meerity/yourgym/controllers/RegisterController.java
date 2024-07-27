package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.RegistrationForm;
import com.meerity.yourgym.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RegisterController {

    private final PersonService personService;

    @Autowired
    public RegisterController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/do-register")
    public String doRegister(@Valid @ModelAttribute RegistrationForm registrationForm, Errors errors) {
        if (errors.hasErrors() || !personService.registerPerson(registrationForm)) {
            if (!errors.hasErrors()) {
                errors.reject("registrationForm", "Account with this card already exists, or this card was never registered");
            }
            return "register";
        }
        return "redirect:/login?registered=true";
    }
}

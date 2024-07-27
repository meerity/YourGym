package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.NewClient;
import com.meerity.yourgym.service.AddClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/operator")
public class OperatorController {

    AddClientService addClientService;

    public OperatorController(AddClientService addClientService) {
        this.addClientService = addClientService;
    }

    @GetMapping("/add-new-client")
    public String addNewClient(Model model) {
        model.addAttribute("newClient", new NewClient());
        return "add-new-client";
    }

    @PostMapping("/add-new-client")
    public String addNewClient(@Valid @ModelAttribute NewClient newClient, Errors errors) {
        if (errors.hasErrors() || !addClientService.addNewClient(newClient)){
            if (!errors.hasErrors()) {
                errors.reject("newClient", "Error with creating new client");
            }
            return "add-new-client";
        }
        return "profile";
    }
}

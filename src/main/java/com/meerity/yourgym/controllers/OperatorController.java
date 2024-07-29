package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.NewClient;
import com.meerity.yourgym.model.Trainer;
import com.meerity.yourgym.service.AddClientService;
import com.meerity.yourgym.service.TrainerService;
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

    private final TrainerService trainerService;
    AddClientService addClientService;

    public OperatorController(AddClientService addClientService, TrainerService trainerService) {
        this.addClientService = addClientService;
        this.trainerService = trainerService;
    }

    @GetMapping("/dashboard")
    public String displayDashboard() {
        return "dashboard";
    }

    @GetMapping("/add-new-client")
    public String addNewClient(Model model) {
        model.addAttribute("newClient", new NewClient());
        model.addAttribute("trainers", trainerService.getAllFreeTrainers());
        return "add-new-client";
    }

    @PostMapping("/add-new-client")
    public String addNewClient(@Valid @ModelAttribute NewClient newClient, Errors errors, Model model) {
        model.addAttribute("trainers", trainerService.getAllFreeTrainers());
        if (errors.hasErrors() || !addClientService.addNewClient(newClient)){
            if (!errors.hasErrors()) {
                errors.reject("newClient", "Error with creating new client");
            }
            return "add-new-client";
        }
        model.addAttribute("successMessage", "Successfully added new client");
        return "dashboard";
    }

    @GetMapping("/add-trainer")
    public String addTrainer(Model model) {
        model.addAttribute("newTrainer", new Trainer());
        return "add-trainer";
    }

    @PostMapping("/add-trainer")
    public String addNewClient(@Valid @ModelAttribute Trainer newTrainer, Errors errors, Model model) {
        if (errors.hasErrors() || !trainerService.addTrainer(newTrainer)){
            if (!errors.hasErrors()) {
                errors.reject("newTrainer", "Error with creating new trainer");
            }
            return "add-trainer";
        }
        model.addAttribute("successMessage", "Successfully added new trainer");
        return "dashboard";
    }
}

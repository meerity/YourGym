package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.NewClient;
import com.meerity.yourgym.model.Trainer;
import com.meerity.yourgym.service.AddClientService;
import com.meerity.yourgym.service.ClientCardService;
import com.meerity.yourgym.service.TrainerService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/operator")
public class OperatorController {

    private final TrainerService trainerService;
    private final AddClientService addClientService;
    private final ClientCardService clientCardService;

    public OperatorController(AddClientService addClientService,
                              TrainerService trainerService,
                              ClientCardService clientCardService) {
        this.clientCardService = clientCardService;
        this.addClientService = addClientService;
        this.trainerService = trainerService;
    }

    @GetMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
        model.addAttribute("clientsCount", clientCardService.getAllClientsCount());
        session.setAttribute("trainersAndTraineeCount", trainerService.getAllTrainersAndTraineeCount());
        return "dashboard";
    }

    @GetMapping("/add-new-client")
    public String addNewClient(Model model) {
        model.addAttribute("newClient", new NewClient());
        model.addAttribute("trainers", trainerService.getAllFreeTrainers());
        return "add-new-client";
    }

    @PostMapping("/add-new-client")
    public String addNewClient(@Valid @ModelAttribute NewClient newClient,
                               Errors errors, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("trainers", trainerService.getAllFreeTrainers());
        if (errors.hasErrors() || !addClientService.addNewClient(newClient)){
            if (!errors.hasErrors()) {
                errors.reject("newClient", "Error with creating new client");
            }
            return "add-new-client";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Successfully added new client");
        return "redirect:/operator/dashboard";
    }

    @GetMapping("/delete-client")
    public String displayDeleteClientPage() {
        return "delete-client";
    }

    @DeleteMapping("/delete-client")
    public String deleteClient(@RequestParam String idCard,
                               Model model, RedirectAttributes redirectAttributes) {
        if (!clientCardService.deleteClientCard(idCard)){
            model.addAttribute("errorMessage", "Error with deleting client card. Maybe this client card has not been registered?");
            return "delete-client";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Successfully deleted client");
            return "redirect:/operator/dashboard";
        }
    }

    @GetMapping("/add-trainer")
    public String addTrainer(Model model) {
        model.addAttribute("newTrainer", new Trainer());
        return "add-trainer";
    }

    @PostMapping("/add-trainer")
    public String addNewClient(@Valid @ModelAttribute Trainer newTrainer,
                               Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors() || !trainerService.addTrainer(newTrainer)){
            if (!errors.hasErrors()) {
                errors.reject("newTrainer", "Error with creating new trainer");
            }
            return "add-trainer";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Successfully added new trainer");
        return "redirect:/operator/dashboard";
    }

    @GetMapping("/delete-trainer")
    public String displayDeleteTrainerPage() {
        return "delete-trainer";
    }

    @DeleteMapping("/delete-trainer")
    public String deleteTrainer(@RequestParam String firstName,
                                @RequestParam String lastName,
                                Model model, RedirectAttributes redirectAttributes) {
        if (!trainerService.deleteTrainerByFullName(firstName, lastName)){
            model.addAttribute("errorMessage", "Error with deleting trainer. Please, check your data");
            return "delete-client";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Successfully deleted trainer");
            return "redirect:/operator/dashboard";
        }
    }
}

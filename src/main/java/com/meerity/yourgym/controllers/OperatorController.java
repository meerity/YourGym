package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.entity.ClientCard;
import com.meerity.yourgym.model.entity.Contact;
import com.meerity.yourgym.model.entity.Person;
import com.meerity.yourgym.model.entity.Trainer;
import com.meerity.yourgym.model.forms.EditFormWithTrainer;
import com.meerity.yourgym.model.forms.NewClient;
import com.meerity.yourgym.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/operator")
public class OperatorController {

    private final TrainerService trainerService;
    private final AddClientService addClientService;
    private final ClientCardService clientCardService;
    private final PersonService personService;
    private final ContactService contactService;

    public OperatorController(AddClientService addClientService,
                              TrainerService trainerService,
                              ClientCardService clientCardService, PersonService personService, ContactService contactService) {
        this.clientCardService = clientCardService;
        this.addClientService = addClientService;
        this.trainerService = trainerService;
        this.personService = personService;
        this.contactService = contactService;
    }

    @GetMapping("/dashboard")
    public String displayDashboard(HttpSession session, Model model) {
        model.addAttribute("clientsCount", clientCardService.getAllClientsCount());
        session.setAttribute("trainersAndTrainee", trainerService.getAllTrainersAndTraineeCount());
        return "dashboard";
    }

    @GetMapping("/admin-stats")
    public String displayAdminStats(Model model) {
        model.addAttribute("trainerClientsMap", trainerService.getClientInfoByTrainer());
        return "admin-stats";
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

    @GetMapping("/find-client")
    public String displayDeleteClientPage() {
        return "find-client";
    }

    @GetMapping("/client-info")
    public String displayClientInfoPage(@RequestParam String cardNum, Model model, HttpSession session) {
        Person person = personService.findByCardNumber(cardNum);
        List<Trainer> trainers = trainerService.getAllTrainers();
        if (person == null) {
            model.addAttribute("errorMessage", "Client not found");
            return "find-client";
        }

        EditFormWithTrainer editFormT = new EditFormWithTrainer();
        editFormT.setFormFirstName(person.getFirstName());
        editFormT.setFormLastName(person.getLastName());
        editFormT.setFormPhoneNum(person.getPhoneNum());
        editFormT.setFormEmail(person.getEmail());
        if (person.getCard().getTrainer() != null) {
            editFormT.setTrainerId(person.getCard().getTrainer().getTrainerId());
        }

        session.setAttribute("person", person);
        session.setAttribute("trainers", trainers);
        session.setAttribute("editFormT", editFormT);
        model.addAttribute("editFormT", editFormT);

        return "client-info";
    }

    @DeleteMapping("/delete-client")
    public String deleteClient(HttpSession session, Model model,
                               RedirectAttributes redirectAttributes) {
        Person person = (Person) session.getAttribute("person");

        if (!clientCardService.deleteClientCard(person.getCard())){
            model.addAttribute("person", person);
            model.addAttribute("trainers", session.getAttribute("trainers"));
            model.addAttribute("editFormT", session.getAttribute("editFormT"));
            model.addAttribute("errorMessage", "Error with deleting client card. Maybe this client card has not been registered?");
            return "client-info";
        } else {
            session.invalidate();
            redirectAttributes.addFlashAttribute("successMessage", "Successfully deleted client");
            return "redirect:/operator/dashboard";
        }
    }

    @PatchMapping("/update-payment-date")
    public String updatePaymentDate(HttpSession session, Model model) {
        Person person = (Person) session.getAttribute("person");
        ClientCard card = person.getCard();

        if (!clientCardService.updatePaymentDate(card)){
            model.addAttribute("errorMessage", "Error with deleting client card. Maybe this client card has not been registered?");
        } else {
            model.addAttribute("successMessage", "Successfully updated payment date");
        }

        model.addAttribute("editFormT", session.getAttribute("editFormT"));
        model.addAttribute("person", person);
        model.addAttribute("trainers", session.getAttribute("trainers"));
        return "client-info";
    }

    @PatchMapping("/edit-client")
    public String editClient(@Valid @ModelAttribute EditFormWithTrainer editFormT,
                             Errors errors, HttpSession session, Model model){
        Person person = (Person) session.getAttribute("person");

        if (person.getEmail() != null) {
            if (!person.getEmail().equals(editFormT.getFormEmail()) && personService.findByEmail(editFormT.getFormEmail()) != null) {
                errors.reject("editFormT", "This email is already registered");
            }
        }

        if (!person.getPhoneNum().equals(editFormT.getFormPhoneNum()) && personService.findByPhoneNum(editFormT.getFormPhoneNum()) != null) {
            errors.reject("editFormT",  "This phone number is already registered");
        }

        if (errors.hasErrors()) {
            model.addAttribute("editFormT", editFormT);
            model.addAttribute("errors", errors);
            return "client-info";
        }

        Person editedPerson = personService.updatePersonOP(editFormT, person);
        session.setAttribute("person", editedPerson);
        model.addAttribute("editFormT", editFormT);
        model.addAttribute("successMessage", "Successfully edited client");
        return "client-info";
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
            return "find-client";
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "Successfully deleted trainer");
            return "redirect:/operator/dashboard";
        }
    }

    @GetMapping("/messages/{pageNum}")
    public String displayMessagesPage(@PathVariable int pageNum,
                                      @RequestParam int size,
                                      @RequestParam String sortField,
                                      @RequestParam String sortDirection, Model model) {
        Page<Contact> msgPage = contactService.getContactsWithOpenStatusWithPagination(pageNum, size, sortField, sortDirection);
        List<Contact> contactMessages = msgPage.getContent();
        int[] pageSizeArray = {5, 10, 25};
        model.addAttribute("contactMessages", contactMessages);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("pageSize" , size);
        model.addAttribute("pageSizeArray" , pageSizeArray);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMessages", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDirection);
        model.addAttribute("reverseSortDir", sortDirection.equals("asc") ? "desc" : "asc");
        return "messages";
    }

    @PatchMapping("/message/close/{msgId}")
    public String closeMessage(@PathVariable long msgId,
                               @RequestParam int pageNum,
                               @RequestParam int size,
                               @RequestParam String sortField,
                               @RequestParam String sortDirection,
                               RedirectAttributes redirectAttributes){
        if (!contactService.closeMessage(msgId)){
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Error with closing message with id: " + msgId + ". Contact DB administrator");
            return "redirect:/operator/dashboard";
        }
        return "redirect:/operator/messages/" + pageNum + "?size=" + size + "&sortField=" + sortField + "&sortDirection=" + sortDirection;
    }
}

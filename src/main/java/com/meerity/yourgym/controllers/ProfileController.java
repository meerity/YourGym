package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.ClientCard;
import com.meerity.yourgym.model.EditForm;
import com.meerity.yourgym.model.Person;
import com.meerity.yourgym.model.Trainer;
import com.meerity.yourgym.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final PersonService personService;


    public ProfileController(PersonService personService) {
        this.personService = personService;

    }

    @GetMapping
    public String profile(Authentication authentication, HttpSession session) {
        String emailOrPhone = authentication.getName();
        Person person = personService.findByEmailOrPhoneNumber(emailOrPhone);
        session.setAttribute("person", person);

        ClientCard card = person.getCard();
        LocalDate nextPaymentDate = card.getLastPaymentDate().plusMonths(1);
        session.setAttribute("nextPaymentDate", nextPaymentDate);

        Trainer trainer = card.getTrainer();
        session.setAttribute("trainer", trainer);
        return "profile";
    }

    @GetMapping("/edit")
    public String displayEditForm(Model model, HttpSession session) {
        Person person = (Person) session.getAttribute("person");
        EditForm editForm = new EditForm();
        editForm.setFormFirstName(person.getFirstName());
        editForm.setFormLastName(person.getLastName());
        editForm.setFormPhoneNum(person.getPhoneNum());
        editForm.setFormEmail(person.getEmail());
        model.addAttribute("editForm", editForm);
        return "edit";
    }

    @PatchMapping("/edit")
    public String doEdit(@Valid EditForm editForm, Errors errors, HttpSession session){
        Person person = (Person) session.getAttribute("person");
        if (!person.getEmail().equals(editForm.getFormEmail()) && personService.findByEmail(editForm.getFormEmail()) != null) {
            errors.reject("formEmail", "This email is already registered");
        }
        if (!person.getPhoneNum().equals(editForm.getFormPhoneNum()) && personService.findByPhoneNum(editForm.getFormPhoneNum()) != null) {
            errors.reject("phoneNum", "This phone number is already registered");
        }
        if (errors.hasErrors()) {
            return "edit";
        }
        Person editedPerson = personService.updatePerson(person, editForm);
        session.setAttribute("person", editedPerson);
        return "profile";
    }
}

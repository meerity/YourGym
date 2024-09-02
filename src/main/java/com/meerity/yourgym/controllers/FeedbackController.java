package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.entity.Contact;
import com.meerity.yourgym.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    private final ContactService contactService;

    @Autowired
    public FeedbackController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping()
    public String displayContactUsPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "feedback";
    }

    @PostMapping("/saveMsg")
    public String saveMsg(@Valid @ModelAttribute Contact contact, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            log.error("Contact form is not valid, cause:{}", errors.getAllErrors());
            return "feedback";
        }
        boolean isSaved =  contactService.saveContact(contact);
        if (isSaved) {
            redirectAttributes.addFlashAttribute("successMessage", "Message sent successfully!");
        }
        else {
            throw new RuntimeException("Contact submission failed! Try again later");
        }
        return "redirect:/feedback";
    }
}

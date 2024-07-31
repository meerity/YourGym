package com.meerity.yourgym.controllers;

import com.meerity.yourgym.model.entity.Person;
import com.meerity.yourgym.service.ClientCardService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.time.LocalDate;


@Slf4j
@Controller
public class PaymentController {

    private final ClientCardService clientCardService;

    public PaymentController(ClientCardService clientCardService) {
        this.clientCardService = clientCardService;
    }

    @GetMapping("/payment")
    public String displayPaymentPage() {
        return "payment";
    }

    @PatchMapping("/payment")
    public String payment(HttpSession session) {
        Person person = (Person) session.getAttribute("person");
        LocalDate paymentDate = clientCardService.renewPaymentAndGetNewDate(person.getCard());
        LocalDate nextPaymentDate = paymentDate.plusMonths(1);
        session.setAttribute("nextPaymentDate", nextPaymentDate);
        return "profile";
    }
}

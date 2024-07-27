package com.meerity.yourgym.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        String errorMsg;
        if(e.getMessage()!=null){
            errorMsg = e.getMessage();
        }else if (e.getCause()!=null){
            errorMsg = e.getCause().toString();
        }else {
            errorMsg = e.toString();
        }
        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }
}

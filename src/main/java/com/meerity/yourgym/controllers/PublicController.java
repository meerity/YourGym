package com.meerity.yourgym.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PublicController {

    @GetMapping(value={"","/", "/home"})
    public String displayHomePage() {
        return "home";
    }

    @GetMapping(value="/features")
    public String displayFeaturesPage() {
        return "features";
    }

}

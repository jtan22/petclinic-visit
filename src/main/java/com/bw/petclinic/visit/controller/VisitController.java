package com.bw.petclinic.visit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
}

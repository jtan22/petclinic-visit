package com.bw.petclinic.visit.controller;

import com.bw.petclinic.visit.domain.Visit;
import com.bw.petclinic.visit.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;

    private static final Logger LOG = LoggerFactory.getLogger(VisitController.class);

    @GetMapping("/")
    public String welcome() {
        LOG.info("GET /");
        return "welcome";
    }

    @GetMapping("/visits/pet")
    public List<Visit> getVisits(@RequestParam("petId") int petId) {
        LOG.info("GET /visits/pet with petId [" + petId + "]");
        return visitRepository.findByPetId(petId);
    }

}

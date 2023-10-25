package com.bw.petclinic.visit.controller;

import com.bw.petclinic.visit.domain.Visit;
import com.bw.petclinic.visit.repository.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitController {

    private static final Logger LOG = LoggerFactory.getLogger(VisitController.class);

    @Autowired
    private VisitRepository visitRepository;

    /**
     * Health check.
     *
     * @return
     */
    @GetMapping("/")
    public String welcome() {
        LOG.info("GET /");
        return "welcome";
    }

    /**
     * Get Visits for a given petId.
     *
     * @param petId
     * @return
     */
    @GetMapping("/visits/pet")
    public List<Visit> getVisits(@RequestParam("petId") int petId) {
        LOG.info("GET /visits/pet with petId [" + petId + "]");
        return visitRepository.findByPetId(petId);
    }

    /**
     * Save a Visit, add or update.
     *
     * @param visit
     * @return
     */
    @PostMapping("/visits/visit")
    public Visit saveVisit(@RequestBody Visit visit) {
        LOG.info("POST /visits/visit with Visit [" + visit + "]");
        return visitRepository.save(visit);
    }

}

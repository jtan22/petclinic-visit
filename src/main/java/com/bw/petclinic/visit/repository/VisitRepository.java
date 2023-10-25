package com.bw.petclinic.visit.repository;

import com.bw.petclinic.visit.domain.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VisitRepository extends CrudRepository<Visit, Integer> {

    List<Visit> findByPetId(int petId);

}

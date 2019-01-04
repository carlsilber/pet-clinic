package com.carlsilber.petclinic.repositories;

import com.carlsilber.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}

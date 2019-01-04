package com.carlsilber.petclinic.repositories;

import com.carlsilber.petclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {

}

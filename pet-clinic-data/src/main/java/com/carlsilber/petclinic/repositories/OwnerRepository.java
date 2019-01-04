package com.carlsilber.petclinic.repositories;

import com.carlsilber.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}

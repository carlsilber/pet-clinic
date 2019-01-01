package com.carlsilber.petclinic.services;

import com.carlsilber.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

}

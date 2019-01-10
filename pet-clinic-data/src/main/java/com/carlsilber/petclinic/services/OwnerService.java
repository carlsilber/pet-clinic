package com.carlsilber.petclinic.services;

import com.carlsilber.petclinic.model.Owner;
import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

  List<Owner> findAllByLastNameLike(String lastName);

}

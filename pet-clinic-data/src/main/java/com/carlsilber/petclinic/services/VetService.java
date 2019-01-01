package com.carlsilber.petclinic.services;

import com.carlsilber.petclinic.model.Vet;
import java.util.Set;

public interface VetService {

  Vet findById(Long id);

  Vet save(Vet vet);

  Set<Vet> findAll();

}

package com.carlsilber.petclinic.services.springdatajpa;

import com.carlsilber.petclinic.model.Owner;
import com.carlsilber.petclinic.repositories.OwnerRepository;
import com.carlsilber.petclinic.repositories.PetRepository;
import com.carlsilber.petclinic.repositories.PetTypeRepository;
import com.carlsilber.petclinic.services.OwnerService;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {

  private final OwnerRepository ownerRepository;
  private final PetRepository petRepository;
  private final PetTypeRepository petTypeRepository;

  public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
      PetTypeRepository petTypeRepository) {
    this.ownerRepository = ownerRepository;
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
  }

  @Override
  public Owner findByLastName(String lastName) {
    return ownerRepository.findByLastName(lastName);
  }

  @Override
  public Set<Owner> findAll() {
    Set<Owner> owners = new HashSet<>();
    ownerRepository.findAll().forEach(owners::add);
    return owners;
  }

  @Override
  public Owner findById(Long aLong) {
    Optional<Owner> optionalOwner = ownerRepository.findById(aLong);
/*    if (optionalOwner.isPresent()){
      return optionalOwner.get();
    } else {
      return null;
    }*/
//  return optionalOwner.orElse(null);

    return ownerRepository.findById(aLong).orElse(null);
  }

  @Override
  public Owner save(Owner object) {
    return ownerRepository.save(object);
  }

  @Override
  public void delete(Owner object) {
    ownerRepository.delete(object);
  }

  @Override
  public void deleteById(Long aLong) {
    ownerRepository.deleteById(aLong);
  }
}

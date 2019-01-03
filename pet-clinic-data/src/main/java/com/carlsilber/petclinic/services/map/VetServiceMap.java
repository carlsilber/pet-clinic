package com.carlsilber.petclinic.services.map;

import com.carlsilber.petclinic.model.Specialty;
import com.carlsilber.petclinic.model.Vet;
import com.carlsilber.petclinic.services.SpecialtyService;
import com.carlsilber.petclinic.services.VetService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

  private final SpecialtyService specialtyService;

  public VetServiceMap(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Vet save(Vet object) {

    if (object.getSpecialties().size() > 0){
      object.getSpecialties().forEach(speciality -> {
        if(speciality.getId() == null){
          Specialty savedSpecialty = specialtyService.save(speciality);
          speciality.setId(savedSpecialty.getId());
        }
      });
    }

    return super.save(object);
  }

  @Override
  public void delete(Vet object) {
    super.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }
}

package com.carlsilber.petclinic.controllers;

import com.carlsilber.petclinic.model.Owner;
import com.carlsilber.petclinic.model.PetType;
import com.carlsilber.petclinic.services.OwnerService;
import com.carlsilber.petclinic.services.PetService;
import com.carlsilber.petclinic.services.PetTypeService;
import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

  private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

  private final PetService petService;
  private final OwnerService ownerService;
  private final PetTypeService petTypeService;

  public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
    this.petService = petService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
  }

  @InitBinder("owner")
  public void initOwnerBinder(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @ModelAttribute("types")
  public Collection<PetType> populatePetTypes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
    return ownerService.findById(ownerId);
  }

}

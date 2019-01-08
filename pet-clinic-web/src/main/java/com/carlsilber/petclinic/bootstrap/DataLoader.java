package com.carlsilber.petclinic.bootstrap;

import com.carlsilber.petclinic.model.Owner;
import com.carlsilber.petclinic.model.Pet;
import com.carlsilber.petclinic.model.PetType;
import com.carlsilber.petclinic.model.Specialty;
import com.carlsilber.petclinic.model.Vet;
import com.carlsilber.petclinic.model.Visit;
import com.carlsilber.petclinic.services.OwnerService;
import com.carlsilber.petclinic.services.PetTypeService;
import com.carlsilber.petclinic.services.SpecialtyService;
import com.carlsilber.petclinic.services.VetService;
import com.carlsilber.petclinic.services.VisitService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;
  private final VisitService visitService;

  public DataLoader(OwnerService ownerService,
      VetService vetService, PetTypeService petTypeService,
      SpecialtyService specialtyService, VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
    this.visitService = visitService;
  }

  @Override
  public void run(String... args) throws Exception {
    int count = petTypeService.findAll().size();

    if (count == 0 ){
      loadData();
    }
  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);

    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty savedRadiology = specialtyService.save(radiology);

    Specialty surgery = new Specialty();
    surgery.setDescription("Surgery");
    Specialty savedSurgery = specialtyService.save(surgery);

    Specialty dentistry = new Specialty();
    dentistry.setDescription("dentistry");
    Specialty savedDentistry = specialtyService.save(dentistry);

    Owner owner1 = new Owner();
    owner1.setFirstName("Mike");
    owner1.setLastName("Mueller");
    owner1.setAddress("Hauptstrasse 1");
    owner1.setCity("Berlin");
    owner1.setTelephone("2345234235");

    Pet mikesDog = new Pet();
    mikesDog.setName("Laika");
    mikesDog.setPetType(savedDogPetType);
    mikesDog.setOwner(owner1);
    mikesDog.setBirthDate(LocalDate.now());
    owner1.getPets().add(mikesDog);
    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Linda");
    owner2.setLastName("Carter");
    owner2.setAddress("Gaujas Iela 1");
    owner2.setCity("Riga");
    owner2.setTelephone("37329830452");

    Pet lindasCat = new Pet();
    lindasCat.setName("Kitty");
    lindasCat.setPetType(savedCatPetType);
    lindasCat.setOwner(owner2);
    lindasCat.setBirthDate(LocalDate.now());
    owner2.getPets().add(lindasCat);
    ownerService.save(owner2);


    Visit catVisit = new Visit();
    catVisit.setPet(lindasCat);
    catVisit.setDate(LocalDate.now());
    catVisit.setDescription("Kitty's leg is broken");

    visitService.save(catVisit);

    System.out.println("Loaded Owners....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");
    vet1.getSpecialties().add(savedRadiology);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessie");
    vet2.setLastName("Porter");
    vet2.getSpecialties().add(savedSurgery);

    vetService.save(vet2);

    System.out.println("Loaded Vets....");
  }
}

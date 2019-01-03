package com.carlsilber.petclinic.bootstrap;

import com.carlsilber.petclinic.model.Owner;
import com.carlsilber.petclinic.model.Pet;
import com.carlsilber.petclinic.model.PetType;
import com.carlsilber.petclinic.model.Vet;
import com.carlsilber.petclinic.services.OwnerService;
import com.carlsilber.petclinic.services.PetTypeService;
import com.carlsilber.petclinic.services.VetService;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  public DataLoader(OwnerService ownerService, VetService vetService,
      PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }

  @Override
  public void run(String... args) throws Exception {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);


    Owner owner1 = new Owner();
    owner1.setFirstName("Mike");
    owner1.setLastName("Mueller");
    owner1.setAddress("Hauptstrasse 1");
    owner1.setCity("Berlin");
    owner1.setTelephone("2345234235");

    Pet mikesPet = new Pet();
    mikesPet.setName("Laika");
    mikesPet.setPetType(savedDogPetType);
    mikesPet.setOwner(owner1);
    mikesPet.setBirthDate(LocalDate.now());
    owner1.getPets().add(mikesPet);
    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Linda");
    owner2.setLastName("Carter");
    owner1.setAddress("Gaujas Iela 1");
    owner1.setCity("Riga");
    owner1.setTelephone("37329830452");

    Pet lindasPet = new Pet();
    lindasPet.setName("Kitty");
    lindasPet.setPetType(savedCatPetType);
    lindasPet.setOwner(owner2);
    lindasPet.setBirthDate(LocalDate.now());
    owner2.getPets().add(lindasPet);
    ownerService.save(owner2);

    System.out.println("Loaded Owners....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessie");
    vet2.setLastName("Porter");

    vetService.save(vet2);

    System.out.println("Loaded Vets....");
  }
}

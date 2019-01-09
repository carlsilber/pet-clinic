package com.carlsilber.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.carlsilber.petclinic.model.Owner;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerMapServiceTest {

  OwnerMapService ownerMapService;

  final Long ownerId = 1L;
  final String lastName = "Lee";

  @BeforeEach
  void setUp() {
    //make DI
    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
    ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
  }

  @Test
  void findAll() {
    Set<Owner> ownerSet = ownerMapService.findAll();

    assertEquals(1, ownerSet.size());
  }

  @Test
  void findById() {
    Owner owner = ownerMapService.findById(ownerId);

    assertEquals(ownerId, owner.getId());
  }

  @Test
  void saveExistingId() {
    Long id = 2L;

    Owner owner2 = Owner.builder().id(id).build();
    Owner savedOwner = ownerMapService.save(owner2);

    assertEquals(id, savedOwner.getId());
  }

  @Test
  void saveNoId() {

    Owner savedOwner = ownerMapService.save(Owner.builder().build());

    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
    assertNull(savedOwner.getFirstName());
  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(ownerId)); //delete by Object

    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(ownerId);  //delete by id

    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void findByLastName() {
    Owner smith = ownerMapService.findByLastName(lastName);

    assertNotNull(smith);

    assertEquals(ownerId, smith.getId());

  }

  @Test
  void findByLastNameNotFound() {
    Owner lee = ownerMapService.findByLastName("foo");

    assertNull(lee);
  }
}
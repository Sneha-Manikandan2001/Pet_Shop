package com.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SupplierRelationshipTest {

   @Test
   public void testSupplierRelationshipConstructorAndGetters() {
       Suppliers supplier = new Suppliers();
       Pets pet = new Pets();
       SupplierRelationship relationship = new SupplierRelationship(supplier, pet);

       assertEquals(supplier, relationship.getSupplier());
       assertEquals(pet, relationship.getPet());
   }
}

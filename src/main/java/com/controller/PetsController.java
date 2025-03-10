package com.controller;

import com.model.Pets;
import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.model.FoodRelationship;
import com.model.GroomingRelationship;
import com.model.SupplierRelationship;
import com.model.VaccinationRelationship;
import com.model.Transactions;
import com.service.PetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/pets")
public class PetsController {

    @Autowired
    private PetsService petsService;

    @GetMapping
    public List<Pets> getAllPets() {
        return petsService.getAllPets();
    }

    @GetMapping("/{pet_id}")
    public ResponseEntity<Pets> getPetById(@PathVariable("pet_id") Long pet_id) {
        Optional<Pets> pets = petsService.getPetById(pet_id);
        if(pets.isEmpty()) {
        	throw new ResourceNotFoundException("Validation Failed");
        }
        return ResponseEntity.ok(pets.get());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Pets>> getPetsByCategory(@PathVariable String category) {
        List<Pets> pets = petsService.getPetsByCategory(category);
        if (pets != null && !pets.isEmpty()) {
            return ResponseEntity.ok(pets);
        } else {
            throw new ResourceNotFoundException("No pets found in category");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPet(@RequestBody Pets pet) {
        if (pet == null) {
            throw new  ValidationException("Validation failed");
        }
        petsService.addPet(pet);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Pet added successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{pet_id}")
    public ResponseEntity<String> updatePet(@PathVariable Long pet_id, @RequestBody Pets petDetails) {
        Optional<Pets> petsOptional = petsService.updatePet(pet_id, petDetails);
        if (petsOptional.isPresent()) {
            return ResponseEntity.ok("Pet updated successfully.");
        } else {
            throw new ResourceNotFoundException("Pet not found");
        }
    }

    @GetMapping("/grooming_services/{pet_id}")
    public ResponseEntity<List<GroomingRelationship>> getGroomingServicesByPetId(@PathVariable Long pet_id) {
        List<GroomingRelationship> services = petsService.getGroomingServicesByPetId(pet_id);
        if (services != null && !services.isEmpty()) {
            return ResponseEntity.ok(services);
        } else {
            throw new ResourceNotFoundException("No grooming services found for pet id");
        }
    }

    @GetMapping("/vaccinations/{pet_id}")
    public ResponseEntity<List<VaccinationRelationship>> getVaccinationsByPetId(@PathVariable Long pet_id) {
        List<VaccinationRelationship> vaccinations = petsService.getVaccinationsByPetId(pet_id);
        if (vaccinations != null && !vaccinations.isEmpty()) {
            return ResponseEntity.ok(vaccinations);
        } else {
            throw new ResourceNotFoundException("No vaccinations found for pet id");
        }
    }

    @GetMapping("/food_info/{pet_id}")
    public ResponseEntity<List<FoodRelationship>> getFoodInfoByPetId(@PathVariable Long pet_id) {
        List<FoodRelationship> foodInfo = petsService.getFoodInfoByPetId(pet_id);
        if (foodInfo != null && !foodInfo.isEmpty()) {
            return ResponseEntity.ok(foodInfo);
        } else {
            throw new ResourceNotFoundException("No food information found for pet id");
        }
    }

    @GetMapping("/suppliers/{pet_id}")
    public ResponseEntity<List<SupplierRelationship>> getSuppliersByPetId(@PathVariable Long pet_id) {
        List<SupplierRelationship> suppliers = petsService.getSuppliersByPetId(pet_id);
        if (suppliers != null && !suppliers.isEmpty()) {
            return ResponseEntity.ok(suppliers);
        } else {
            throw new ResourceNotFoundException("No suppliers found for pet id");
        }
    }

    @GetMapping("/transaction_history/{pet_id}")
    public ResponseEntity<List<Transactions>> getTransactionHistoryByPetId(@PathVariable Long pet_id) {
        List<Transactions> transactions = petsService.getTransactionHistoryByPetId(pet_id);
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else {
            throw new ResourceNotFoundException("No transaction history found for pet id");
        }
    }
}

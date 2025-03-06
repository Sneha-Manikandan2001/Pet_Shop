package com.controller;

import com.exception.ResourceNotFoundException;
import com.model.PetFood;
import com.service.PetFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pet_foods")
public class PetFoodController {

    @Autowired
    private PetFoodService petFoodService;

    @GetMapping
    public List<PetFood> getAllPetFoods() {
        List<PetFood> petFoods = petFoodService.getAllPetFoods();
        if (petFoods == null || petFoods.isEmpty()) {
            throw new ResourceNotFoundException("No pet foods found");
        }
        return petFoods;
    }

   @GetMapping("/{foodId}")
    public PetFood getPetFoodById(@PathVariable Long foodId) {
        PetFood petFood = petFoodService.getPetFoodById(foodId);
        if (petFood == null) {
            throw new ResourceNotFoundException("Pet id not found");
        }
        return petFood;
    }

    @GetMapping("/search")
    public List<PetFood> searchPetFoodsByName(@RequestParam String name) {
        List<PetFood> petFoods = petFoodService.searchPetFoodsByName(name);
        if (petFoods == null || petFoods.isEmpty()) {
            throw new ResourceNotFoundException("No pet foods found with the given name");
        }
        return petFoods;
    }

    @GetMapping("/food_type/{type}")
    public List<PetFood> getPetFoodsByType(@PathVariable String type) {
        List<PetFood> petFoods = petFoodService.getPetFoodsByType(type);
        if (petFoods == null || petFoods.isEmpty()) {
            throw new ResourceNotFoundException("No pet foods found with the given type");
        }
        return petFoods;
    }

    @GetMapping("/brand/{brand_Name}")
    public List<PetFood> getPetFoodsByBrand(@PathVariable String brand_Name) {
        List<PetFood> petFoods = petFoodService.getPetFoodsByBrand(brand_Name);
        if (petFoods == null || petFoods.isEmpty()) {
            throw new ResourceNotFoundException("No pet foods found with the given brand");
        }
        return petFoods;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addPetFood(@RequestBody PetFood petFood) {
        if (petFood == null || petFood.getName() == null || petFood.getName().isEmpty()||
                petFood.getBrand() == null || petFood.getBrand().isEmpty() ||
                petFood.getType() == null || petFood.getType().isEmpty() ||
                petFood.getQuantity() <= 0 || petFood.getPrice() <= 0) {
            throw new RuntimeException("Invalid");
            
        }
        petFoodService.addPetFood(petFood);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Pet food added successfully");
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{foodId}")
    public ResponseEntity<Map<String, Object>> updatePetFood(@PathVariable Long foodId, @RequestBody PetFood petFood) {
    	if (petFood == null || petFood.getName() == null || petFood.getName().isEmpty()||
                petFood.getBrand() == null || petFood.getBrand().isEmpty() ||
                petFood.getType() == null || petFood.getType().isEmpty() ||
                petFood.getQuantity() <= 0 || petFood.getPrice() <= 0) {   
    	throw new RuntimeException("Invalid");
        }
        petFoodService.updatePetFood(foodId, petFood);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Pet food updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/quantity/{foodId}")
    public ResponseEntity<Map<String, Object>> updatePetFoodQuantity(@PathVariable Long foodId, @RequestParam int quantity) {
        if (quantity <= 0) {
            throw new RuntimeException("Invalid quantity");
        }
        petFoodService.updatePetFoodQuantity(foodId, quantity);
        Map<String, Object> response = new HashMap<>();
        response.put("timeStamp", LocalDate.now().toString());
        response.put("message", "Pet food quantity updated successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
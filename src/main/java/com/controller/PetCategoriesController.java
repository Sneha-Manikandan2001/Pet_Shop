package com.controller;

import com.exception.ResourceNotFoundException;
import com.exception.ValidationException;
import com.model.PetCategories;
import com.service.PetCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/categories")
public class PetCategoriesController {

    @Autowired
    private PetCategoriesService petCategoriesservice;

    @GetMapping
    public ResponseEntity<List<PetCategories>> getAllcategories() {
        List<PetCategories> petcategories = petCategoriesservice.getAllCategories();
        if (petcategories == null || petcategories.isEmpty()) {
            throw new RuntimeException("Validation Failed");
        }
        return ResponseEntity.ok(petcategories);
    }

    @GetMapping("/{category_id}")
    public ResponseEntity<PetCategories> getCategoryById(@PathVariable Long category_id) {
    	Optional<PetCategories> petcategories = petCategoriesservice.getCategoryById(category_id);
    	if (petcategories == null || petcategories.isEmpty()) {
            throw new ResourceNotFoundException("Validation Failed");
        }
        return petcategories.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{category_name}")
    public ResponseEntity<List<PetCategories>> getCategoryByName(@PathVariable("category_name") String category_name) {
        List<PetCategories> category = petCategoriesservice.getCategoryByName(category_name);
        if (category != null && !category.isEmpty()) {
            return ResponseEntity.ok(category);
        } else {
            throw new ResourceNotFoundException("No pets found in category");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPetCategories(@RequestBody PetCategories petcategories) {
    	if (petcategories == null) {
            throw new ValidationException("Validation Failed");
        }
        petCategoriesservice.addcate(petcategories);
        
        Map<String, String> response = new HashMap<>();
        response.put("timeStamp", "2023-06-08");
        response.put("message", "message");
        
       
        return ResponseEntity.ok("Categories added successfully.");
    }
    @PutMapping("/update/{category_id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long category_id, @RequestBody PetCategories categoryDetails) {
    	if (categoryDetails == null) {
            throw new ValidationException("Validation Failed");
        }
    	Optional<PetCategories> categoriesOptional = petCategoriesservice.getCategoryById(category_id);
        if (categoriesOptional.isPresent()) {
        	PetCategories address = categoriesOptional.get();
            address.setCategoryId(categoryDetails.getCategoryId());
            
            address.setName(categoryDetails.getName());
          
            petCategoriesservice.updateCategory(category_id, categoryDetails);
            return ResponseEntity.ok("messages updated");
        }
        else {
        	//return ResponseEntity.notFound().build();
        	throw new ResourceNotFoundException("Category not found with this id ");
        }
    }
}

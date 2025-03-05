package com.service;

import com.model.PetFood;
import com.dao.PetFoodDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetFoodService {

    @Autowired
    private PetFoodDAO petFoodDAO;

    public List<PetFood> getAllPetFoods() {
        return petFoodDAO.findAll();
    }

    public PetFood getPetFoodById(long foodId) {
        Optional<PetFood> petFood = petFoodDAO.findById(foodId);
        return petFood.orElse(null);
    }

    public List<PetFood> searchPetFoodsByName(String name) {
        return petFoodDAO.findByName(name);
    }

    public List<PetFood> getPetFoodsByType(String type) {
        return petFoodDAO.findByType(type);
    }

    public List<PetFood> getPetFoodsByBrand(String brandName) {
        return petFoodDAO.findByBrand(brandName);
    }

    public PetFood addPetFood(PetFood petFood) {
        return petFoodDAO.save(petFood);
    }

    public PetFood updatePetFood(long foodId, PetFood petFood) {
        if (petFoodDAO.existsById(foodId)) {
            petFood.setFoodId(foodId);
            return petFoodDAO.save(petFood);
        }
        return null;
    }
    

    public PetFood updatePetFoodQuantity(Long food_id, int quantity) {
        PetFood existingPetFood = petFoodDAO.findById(food_id).orElse(null);
        if (existingPetFood != null) {
            existingPetFood.setQuantity(quantity);
            return petFoodDAO.save(existingPetFood);
        }
        return null;
    }
}
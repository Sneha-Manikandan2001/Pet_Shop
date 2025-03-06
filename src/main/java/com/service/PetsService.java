package com.service;

import com.dao.PetsDAO;
import com.model.Pets;
import com.model.FoodRelationship;
import com.model.GroomingRelationship;
import com.model.SupplierRelationship;
import com.model.VaccinationRelationship;
import com.model.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PetsService {

    @Autowired
    private PetsDAO petsDAO;

    @Transactional(readOnly = true)
    public List<Pets> getAllPets() {
        return petsDAO.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Pets> getPetById(Long petId) {
        return petsDAO.findById(petId);
    }

    @Transactional(readOnly = true)
    public List<Pets> getPetsByCategory(String category) {
        return petsDAO.findByCategoryName(category);
    }

    @Transactional
    public boolean addPet(Pets pet) {
        petsDAO.save(pet);
        return true;
    }

    @Transactional
    public Optional<Pets> updatePet(Long petId, Pets petDetails) {
        try {
            return petsDAO.findById(petId)
                    .map(pet -> {
                        pet.setName(petDetails.getName());
                        pet.setBreed(petDetails.getBreed());
                        pet.setAge(petDetails.getAge());
                        pet.setPrice(petDetails.getPrice());
                        pet.setDescription(petDetails.getDescription());
                        pet.setImage(petDetails.getImage());
                        pet.setCategory(petDetails.getCategory());
                        return petsDAO.save(pet);
                    });
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle the optimistic locking failure
            throw new RuntimeException("Failed to update pet. The pet was modified by another transaction.");
        }
    }

    @Transactional(readOnly = true)
    public List<GroomingRelationship> getGroomingServicesByPetId(Long petId) {
        return petsDAO.findGroomingServicesByPetId(petId);
    }

    @Transactional(readOnly = true)
    public List<VaccinationRelationship> getVaccinationsByPetId(Long petId) {
        return petsDAO.findVaccinationsByPetId(petId);
    }

    @Transactional(readOnly = true)
    public List<FoodRelationship> getFoodInfoByPetId(Long petId) {
        return petsDAO.findFoodInfoByPetId(petId);
    }

    @Transactional(readOnly = true)
    public List<SupplierRelationship> getSuppliersByPetId(Long petId) {
        return petsDAO.findSuppliersByPetId(petId);
    }

    @Transactional(readOnly = true)
    public List<Transactions> getTransactionHistoryByPetId(Long petId) {
        return petsDAO.findTransactionHistoryByPetId(petId);
    }
}
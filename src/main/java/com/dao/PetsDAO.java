package com.dao;

import com.model.Pets;
import com.model.FoodRelationship;
import com.model.GroomingRelationship;
import com.model.SupplierRelationship;
import com.model.VaccinationRelationship;
import com.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetsDAO extends JpaRepository<Pets, Long> {
    List<Pets> findByCategoryName(String categoryName);

    @Query("SELECT g FROM GroomingRelationship g JOIN FETCH g.groomingService gs WHERE g.pet.pet_id = :pet_id")
    List<GroomingRelationship> findGroomingServicesByPetId(@Param("pet_id") Long pet_id);

    @Query("SELECT v FROM VaccinationRelationship v JOIN FETCH v.vaccination WHERE v.pet.pet_id = :pet_id")
    List<VaccinationRelationship> findVaccinationsByPetId(@Param("pet_id") Long pet_id);

    
    @Query("SELECT f FROM FoodRelationship f JOIN FETCH f.food WHERE f.pet.pet_id = :pet_id")
    List<FoodRelationship> findFoodInfoByPetId(@Param("pet_id") Long pet_id);

    @Query("SELECT s FROM SupplierRelationship s JOIN FETCH s.supplier WHERE s.pet.pet_id = :pet_id")
    List<SupplierRelationship> findSuppliersByPetId(@Param("pet_id") Long pet_id);

    @Query("SELECT t FROM Transactions t WHERE t.pet.pet_id = :pet_id")
    List<Transactions> findTransactionHistoryByPetId(@Param("pet_id") Long pet_id);
}
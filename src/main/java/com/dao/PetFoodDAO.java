package com.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.model.PetFood;

@Repository
public interface PetFoodDAO extends JpaRepository<PetFood, Long> {
	List<PetFood> findByName(String name);
	List<PetFood> findByType(String type);
	List<PetFood> findByBrand(String brand);
	
	
	@Query("UPDATE PetFood p SET p.quantity = :quantity WHERE p.foodId = :foodId")
	 int updatePetFoodQuantity(Long food_id, PetFood petFood);
}

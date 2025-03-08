package com.dao;

import com.model.PetCategories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetCategoriesDAO extends JpaRepository<PetCategories, Long> {

	PetCategories getCategoryByName(String category_name);

	List<PetCategories> findByName(String name);
}

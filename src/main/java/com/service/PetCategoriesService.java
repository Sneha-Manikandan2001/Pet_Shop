package com.service;

import com.model.PetCategories;
import com.dao.PetCategoriesDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetCategoriesService {

    @Autowired
    private PetCategoriesDAO petCategoriesDAO;

    public List<PetCategories> getAllCategories() {
        return petCategoriesDAO.findAll();
    }

    public Optional<PetCategories> getCategoryById(Long categoryId) {
        return petCategoriesDAO.findById(categoryId);
    }

    public List<PetCategories> getCategoryByName(String categoryName) {
        return petCategoriesDAO.findByName(categoryName);
    }

    public PetCategories addCategory(PetCategories category) {
        return petCategoriesDAO.save(category);
    }
    public boolean addcate(PetCategories cate) 
	{
		petCategoriesDAO.save(cate);
		return true;
	}

    public Optional<PetCategories> updateCategory(Long categoryId, PetCategories categoryDetails) {
        return petCategoriesDAO.findById(categoryId)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    return petCategoriesDAO.save(category);
                });
    }

	public List<PetCategories> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.meerity.yourgym.service;

import com.meerity.yourgym.model.entity.Category;
import com.meerity.yourgym.model.entity.Manufacturer;
import com.meerity.yourgym.repositories.CategoryRepository;
import com.meerity.yourgym.repositories.ManufacturerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public CategoryService(CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Manufacturer> getManufacturersByCategoryName(String categoryName) {
        Category category = categoryRepository.findByInternalName(categoryName);
        Set<Category> categorySet = Collections.singleton(category);
        return manufacturerRepository.findByCategories(categorySet);
    }
}

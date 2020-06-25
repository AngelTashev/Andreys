package com.shop.andreys.service.impl;

import com.shop.andreys.model.binding.CategoryAddBindingModel;
import com.shop.andreys.model.entity.Category;
import com.shop.andreys.model.entity.CategoryName;
import com.shop.andreys.model.service.CategoryServiceModel;
import com.shop.andreys.repository.CategoryRepository;
import com.shop.andreys.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(CategoryAddBindingModel categoryAddBindingModel) {
        this.categoryRepository.saveAndFlush(this.modelMapper.map(categoryAddBindingModel, Category.class));
    }

    @Override
    public boolean isRepositoryEmpty() {
        return this.categoryRepository.count() == 0;
    }

    @Override
    public CategoryServiceModel getByName(String name) {
        return this.modelMapper.map(this.categoryRepository.findByName(CategoryName.valueOf(name)).orElse(null), CategoryServiceModel.class);
    }
}

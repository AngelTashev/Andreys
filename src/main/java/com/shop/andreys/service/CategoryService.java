package com.shop.andreys.service;

import com.shop.andreys.model.binding.CategoryAddBindingModel;
import com.shop.andreys.model.service.CategoryServiceModel;

public interface CategoryService {

    void addCategory(CategoryAddBindingModel categoryAddBindingModel);

    boolean isRepositoryEmpty();

    CategoryServiceModel getByName(String name);
}

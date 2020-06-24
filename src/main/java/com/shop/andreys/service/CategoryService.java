package com.shop.andreys.service;

import com.shop.andreys.model.binding.CategoryAddBindingModel;

public interface CategoryService {

    void addCategory(CategoryAddBindingModel categoryAddBindingModel);

    boolean isRepositoryEmpty();
}

package com.shop.andreys.init;

import com.shop.andreys.model.binding.CategoryAddBindingModel;
import com.shop.andreys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInit implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public CategoryInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryService.isRepositoryEmpty()) {
            this.categoryService.addCategory(new CategoryAddBindingModel("SHIRT", "Shirt is a swaggy pulover."));
            this.categoryService.addCategory(new CategoryAddBindingModel("DENIM", "Bad boy jeans."));
            this.categoryService.addCategory(new CategoryAddBindingModel("SHORTS", "Bad taste mate."));
            this.categoryService.addCategory(new CategoryAddBindingModel("JACKET", "Motorbike vibes"));
        }
    }
}

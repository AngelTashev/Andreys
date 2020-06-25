package com.shop.andreys.service;

import com.shop.andreys.model.service.ItemServiceModel;

import java.util.List;

public interface ItemService {

    void addItem(ItemServiceModel itemServiceModel);

    List<ItemServiceModel> getAllItems();

    ItemServiceModel getItemById(String id);

    void deleteById(String id);
}

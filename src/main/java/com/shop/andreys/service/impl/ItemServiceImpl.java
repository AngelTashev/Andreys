package com.shop.andreys.service.impl;

import com.shop.andreys.model.binding.ItemAddBindingModel;
import com.shop.andreys.model.entity.Item;
import com.shop.andreys.model.service.ItemServiceModel;
import com.shop.andreys.repository.ItemRepository;
import com.shop.andreys.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ModelMapper modelMapper, ItemRepository itemRepository) {
        this.modelMapper = modelMapper;
        this.itemRepository = itemRepository;
    }

    @Override
    public void addItem(ItemServiceModel itemServiceModel) {
        this.itemRepository.saveAndFlush(this.modelMapper.map(itemServiceModel, Item.class));
    }

    @Override
    public List<ItemServiceModel> getAllItems() {
        return this.itemRepository.findAll().stream()
                .map(item -> this.modelMapper.map(item, ItemServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemServiceModel getItemById(String id) {
        return this.modelMapper.map(this.itemRepository.findById(id).orElse(null), ItemServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        this.itemRepository.deleteById(id);
    }
}

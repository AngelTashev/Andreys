package com.shop.andreys.service;

import com.shop.andreys.model.service.UserServiceModel;

public interface UserService {

    UserServiceModel findByUsername(String username);
}

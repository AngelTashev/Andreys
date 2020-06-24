package com.shop.andreys.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String email;
    private BigDecimal budget;

    public UserServiceModel() {
    }

    @Length(min = 2, message = "Username length must be more than two characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2, message = "Password length must be more than two characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Min(0)
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}

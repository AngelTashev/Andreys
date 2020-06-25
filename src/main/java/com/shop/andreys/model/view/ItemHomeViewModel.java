package com.shop.andreys.model.view;

import java.math.BigDecimal;

public class ItemHomeViewModel extends BaseViewModel {

    private String name;
    private BigDecimal price;
    private String imageSrc;

    public ItemHomeViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
}

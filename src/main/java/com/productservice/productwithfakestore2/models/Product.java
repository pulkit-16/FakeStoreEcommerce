package com.productservice.productwithfakestore2.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;

    private Category category;
    private String imageUrl;
    private boolean isPublic;
}
// P : C
// 1 -> 1
// m <- 1
// M <-> 1
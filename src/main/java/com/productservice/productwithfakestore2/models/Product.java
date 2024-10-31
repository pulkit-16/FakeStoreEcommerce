package com.productservice.productwithfakestore2.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private double price;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private String imageUrl;
    private boolean isPublic;
}
// P : C
// 1 -> 1
// m <- 1
// M <-> 1
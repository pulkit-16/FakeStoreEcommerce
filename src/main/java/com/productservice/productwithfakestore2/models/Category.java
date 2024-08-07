package com.productservice.productwithfakestore2.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Category extends BaseModel {
    private String name;
    private Set<Product> products;
}

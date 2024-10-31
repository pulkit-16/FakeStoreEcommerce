package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.GenericCategoryDto;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;

import java.util.List;

public interface CategoryService {
    List<GenericCategoryDto> getAllCategories();

    List<GenericProductDto> getProductsInCategory(String categoryName);
}

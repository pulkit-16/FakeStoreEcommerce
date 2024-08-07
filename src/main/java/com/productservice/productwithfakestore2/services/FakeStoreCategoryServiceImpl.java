package com.productservice.productwithfakestore2.services;

import org.springframework.stereotype.Service;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {
    @Override
    public String getAllCategories(){
        return "get all caegories";
    }

    @Override
    public String getProductsInCategories(){
        return "Get products in ctagory";
    }

}

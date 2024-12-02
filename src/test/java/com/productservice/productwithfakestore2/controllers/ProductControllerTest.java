package com.productservice.productwithfakestore2.controllers;

import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static  org.mockito.Mockito.*;

@SpringBootTest

class ProductControllerTest {

    @Autowired
    private  ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProductsShouldReturnEmptyListWhenNoProduct(){
        List<GenericProductDto> p = new ArrayList<>();
        GenericProductDto p1= new GenericProductDto();
        p1.setPrice(109.95);
        p.add(p1);

        when(productService.getAllProducts()).thenReturn(p);

        List<GenericProductDto> listOfProducts = productController.getAllProducts();

        assert listOfProducts.get(0).getPrice()==109.95;

    }
}
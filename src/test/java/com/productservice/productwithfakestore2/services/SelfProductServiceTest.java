package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import com.productservice.productwithfakestore2.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SelfProductServiceTest {
    @MockBean
    ProductRepository productRepository;

    @Autowired
   SelfProductService selfProductService;

    @Test
     void testSingleProductThrowsExceptionWhenNoSuchProduct()  {
        when(productRepository.findProductById(any())).thenReturn(null);

        assertThrows(NotFoundException.class,()->selfProductService.getSingleProduct(1L));

    }
}
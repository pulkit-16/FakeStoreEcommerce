package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.ProductDto;
import com.productservice.productwithfakestore2.models.Product;


import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getSingleProduct( Long productId);

    Product addNewProduct( ProductDto productdto);
    Product updateProduct(Long productId,
                        ProductDto product);
    Boolean deleteProduct(Long productId);
}

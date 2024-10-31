package com.productservice.productwithfakestore2.services;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;



import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<GenericProductDto> getAllProducts();
    GenericProductDto getSingleProduct(Long productId) throws NotFoundException;

    GenericProductDto addNewProduct( GenericProductDto productdto);
    GenericProductDto updateProduct(Long productId,
                        GenericProductDto product) throws NotFoundException;
    GenericProductDto deleteProduct(Long productId) throws NotFoundException;

}

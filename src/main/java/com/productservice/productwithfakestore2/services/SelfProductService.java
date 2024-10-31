package com.productservice.productwithfakestore2.services;


import com.productservice.productwithfakestore2.dtos.DtoConverter;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;


    public SelfProductService(ProductRepository productRepository){
        this.productRepository=productRepository;
    }




    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> dtoList = new ArrayList<>();

        for (Product product : products) {
            GenericProductDto dto = DtoConverter.convertProductToGenericProductDto(product);
            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public GenericProductDto getSingleProduct(Long productId) throws NotFoundException {
        return null;
    }

    @Override
    public GenericProductDto addNewProduct(GenericProductDto productDto) {
        Product product = DtoConverter.convertGenericProductDtoToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return DtoConverter.convertProductToGenericProductDto(savedProduct);
    }


    @Override
    public GenericProductDto updateProduct(Long productId, GenericProductDto productDto) throws NotFoundException {
        // Step 1: Retrieve the existing product by ID
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        // Step 2: Update the fields of the existing product with values from productDto
        existingProduct.setTitle(productDto.getTitle());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setImageUrl(productDto.getImage());

        // If you have a Category object, set it here (assuming the category name is in the DTO)
        Category category = new Category();
        category.setName(productDto.getCategory());
        existingProduct.setCategory(category);  // Adjust based on your Category structure

        // Step 3: Save the updated product
        Product updatedProduct = productRepository.save(existingProduct);

        // Step 4: Convert and return the updated Product entity as GenericProductDto
        return DtoConverter.convertProductToGenericProductDto(updatedProduct);
    }


    @Override
    public GenericProductDto deleteProduct(Long productId) throws NotFoundException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + productId));

        productRepository.delete(existingProduct);

        return DtoConverter.convertProductToGenericProductDto(existingProduct);

    }
}

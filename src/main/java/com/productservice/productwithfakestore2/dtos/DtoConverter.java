package com.productservice.productwithfakestore2.dtos;

import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreProductDto;

public class DtoConverter {
    public static GenericProductDto convertFakeStoreProductDtoToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto product1 = new GenericProductDto();
        product1.setId(fakeStoreProductDto.getId());
        product1.setTitle(fakeStoreProductDto.getTitle());
        product1.setPrice(fakeStoreProductDto.getPrice());
        product1.setCategory(fakeStoreProductDto.getCategory());
        product1.setImage(fakeStoreProductDto.getImage());
        product1.setDescription(fakeStoreProductDto.getDescription());
        return product1;
    }
    public static GenericProductDto convertProductToGenericProductDto(Product product) {
        GenericProductDto dto = new GenericProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        Category category = new Category();

        dto.setCategory(category.getName());
        dto.setImage(product.getImageUrl());
        dto.setDescription(product.getDescription());
        return dto;
    }
    public static Product convertGenericProductDtoToProduct(GenericProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImage());

        // If category is a separate entity, you need to fetch or create it here
        Category category = new Category();
        category.setName(dto.getCategory());
        product.setCategory(category);  // Assuming Product has a Category field

        return product;
    }
    public static GenericCategoryDto convertCategoryToGenericCategoryDto(Category category) {
        GenericCategoryDto dto = new GenericCategoryDto();

        // Map Category fields to GenericCategoryDto fields
        dto.setId(category.getId());
        dto.setName(category.getName());

        // Map any additional fields if needed
        // e.g., dto.setDescription(category.getDescription());

        return dto;
    }
}

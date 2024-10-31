package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.DtoConverter;
import com.productservice.productwithfakestore2.dtos.GenericCategoryDto;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.repository.CategoryRepository;
import com.productservice.productwithfakestore2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelfCategoryService implements   CategoryService{
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public SelfCategoryService(CategoryRepository categoryRepository,ProductRepository productRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;

    }


    @Override
    public List<GenericCategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<GenericCategoryDto> genericCategoryDtosList = new ArrayList<>();
        for(Category category :categories){
            GenericCategoryDto genericCategoryDto = DtoConverter.convertCategoryToGenericCategoryDto(category);
            genericCategoryDtosList.add(genericCategoryDto);
        }
        return genericCategoryDtosList;
    }

    @Override
    public List<GenericProductDto> getProductsInCategory(String categoryName ) {
        List<Product> products = productRepository.findProductByCategory_Name(categoryName);
        List<GenericProductDto> dtoList = new ArrayList<>();
        for(Product product:products){
            GenericProductDto genericProductDto = DtoConverter.convertProductToGenericProductDto(product);
            dtoList.add(genericProductDto);
        }

        return  dtoList;
    }
}

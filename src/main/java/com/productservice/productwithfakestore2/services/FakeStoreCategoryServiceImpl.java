package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.DtoConverter;
import com.productservice.productwithfakestore2.dtos.GenericCategoryDto;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreCategoryDto;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreClient;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceImpl implements CategoryService {

    private final FakeStoreClient fakeStoreClient;

    public FakeStoreCategoryServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient= fakeStoreClient;

    }
    private GenericCategoryDto convertFakeStoreCategoryDtoToGenericCategoryDto(
            FakeStoreCategoryDto fakeStoreCategoryDto) {

       GenericCategoryDto category1 = new GenericCategoryDto();
       category1.setName(fakeStoreCategoryDto.getName());

       return category1;
    }


    @Override
    public List<GenericCategoryDto> getAllCategories(){
        List<FakeStoreCategoryDto>temp = fakeStoreClient.getAllCategories();

        List<GenericCategoryDto>ans = new ArrayList<>();

        for(FakeStoreCategoryDto fakeStoreCategoryDto1: temp){
                        ans.add(convertFakeStoreCategoryDtoToGenericCategoryDto(fakeStoreCategoryDto1));
        }

        if (ans.isEmpty()) {
            throw new RuntimeException("No categories found from the FakeStore API.");
        }

        return ans;
    }

    @Override
    public List<GenericProductDto> getProductsInCategory(String categoryName) {
        List<FakeStoreProductDto> products = fakeStoreClient.getProductsByCategory(categoryName);
        List<GenericProductDto> genericProducts = new ArrayList<>();

        for (FakeStoreProductDto fakeStoreProductDto : products) {
            genericProducts.add(DtoConverter.convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto));
        }

        if (genericProducts.isEmpty()) {
            throw new RuntimeException("No products found for category: " + categoryName);
        }

        return genericProducts;
    }


}

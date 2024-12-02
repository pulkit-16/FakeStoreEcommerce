package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.DtoConverter;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreClient;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreProductDto;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "fakeStoreProductService")
@Primary
public class FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient=fakeStoreClient;
    }



    //for patch and delete (generic calls)



    @Override
    public List<GenericProductDto> getAllProducts() {

        List<FakeStoreProductDto>temp = fakeStoreClient.getAllProducts();

        List<GenericProductDto>ans = new ArrayList<>() ;
        for(FakeStoreProductDto fakeStoreProductDto : temp){

            GenericProductDto product = DtoConverter.convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
            ans.add(product);

        }
        return ans;
    }

    @Override
    public GenericProductDto getSingleProduct(Long productId) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);
        return DtoConverter.convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto addNewProduct(GenericProductDto productDto) {
      return  DtoConverter.convertFakeStoreProductDtoToGenericProductDto(fakeStoreClient.addNewProduct(productDto));
    }

    @Override
    public GenericProductDto updateProduct(Long productId, GenericProductDto productDto) {

        return DtoConverter.convertFakeStoreProductDtoToGenericProductDto(
                fakeStoreClient.updateProduct(productId, productDto));


    }

    @Override
    public GenericProductDto deleteProduct(Long productId) throws NotFoundException {

     return DtoConverter.convertFakeStoreProductDtoToGenericProductDto(fakeStoreClient.deleteProduct(productId));
    }
}


















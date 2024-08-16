package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreClient;
import com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi.FakeStoreProductDto;
import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private FakeStoreClient fakeStoreClient;
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient){
        this.fakeStoreClient=fakeStoreClient;
    }

    private GenericProductDto convertFakeStoreProductDtoToGenericProductDto(
            FakeStoreProductDto fakeStoreProductDto) {

        GenericProductDto product1 = new GenericProductDto();
        product1.setId(fakeStoreProductDto.getId());
        product1.setTitle(fakeStoreProductDto.getTitle());
        product1.setPrice(fakeStoreProductDto.getPrice());
        product1.setCategory(fakeStoreProductDto.getCategory());
        product1.setImage(fakeStoreProductDto.getImage());
        product1.setDescription(fakeStoreProductDto.getDescription());

        return  product1;
    }

    //for patch and delete (generic calls)



    @Override
    public List<GenericProductDto> getAllProducts() {

        List<FakeStoreProductDto>temp = fakeStoreClient.getAllProducts();

        List<GenericProductDto>ans = new ArrayList<>() ;
        for(FakeStoreProductDto fakeStoreProductDto : temp){

            GenericProductDto product = convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
            ans.add(product);

        }
        return ans;
    }

    @Override
    public GenericProductDto getSingleProduct(Long productId) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getSingleProduct(productId);
        return convertFakeStoreProductDtoToGenericProductDto(fakeStoreProductDto);
    }

    @Override
    public GenericProductDto addNewProduct(GenericProductDto productDto) {
      return  convertFakeStoreProductDtoToGenericProductDto(fakeStoreClient.addNewProduct(productDto));
    }

    @Override
    public GenericProductDto updateProduct(Long productId, GenericProductDto productDto) {

        return convertFakeStoreProductDtoToGenericProductDto(
                fakeStoreClient.updateProduct(productId, productDto));


    }

    @Override
    public GenericProductDto deleteProduct(Long productId) throws NotFoundException {

     return convertFakeStoreProductDtoToGenericProductDto(fakeStoreClient.deleteProduct(productId));
    }
}




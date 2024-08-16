package com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi;

import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }


    //for patch and delete (generic calls)
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();

        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }



    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products",

                FakeStoreProductDto[].class );


        return Arrays.asList(l.getBody());
    }


    public FakeStoreProductDto getSingleProduct(Long productId) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreProductDto.class,productId);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id : "+ productId +" doesn't exist");
        }

        return   fakeStoreProductDto;
    }

    public FakeStoreProductDto addNewProduct(GenericProductDto productDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                productDto,FakeStoreProductDto.class);


        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId, GenericProductDto productDto) {

        String url = "https://fakestoreapi.com/products/{id}";


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity  = requestForEntity(
                HttpMethod.PATCH,
                url,
                productDto,
                FakeStoreProductDto.class,
                productId
        );

        return fakeStoreProductDtoResponseEntity.getBody();

    }

    public FakeStoreProductDto deleteProduct(Long productId) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto>responseEntity =
                restTemplate.execute("https://fakestoreapi.com/products/{id}",
                        HttpMethod.DELETE, requestCallback, responseExtractor, productId);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        if(fakeStoreProductDto==null){
            throw new NotFoundException("Product with id : "+ productId +" doesn't exist");
        }
        return fakeStoreProductDto;
    }

}

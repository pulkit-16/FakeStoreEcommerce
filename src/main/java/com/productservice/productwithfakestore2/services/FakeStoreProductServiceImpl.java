package com.productservice.productwithfakestore2.services;

import com.productservice.productwithfakestore2.dtos.ProductDto;
import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductServiceImpl implements ProductService{

    private RestTemplateBuilder restTemplateBuilder;

    FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> l = restTemplate.getForEntity("https://fakestoreapi.com/products",

                ProductDto[].class );

        List<Product>ans = new ArrayList<>() ;
        for(Object obj : l.getBody()){
            ProductDto productDto = (ProductDto) obj;
            Product product = new Product();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setPrice(productDto.getPrice());

            Category category = new Category(); // just set the name of category
            category.setName(productDto.getCategory());
            product.setCategory(category);
            product.setImageUrl(productDto.getImage());
            product.setDescription(productDto.getDescription());
            ans.add(product);

        }
        return ans;
    }

    @Override
    public Product getSingleProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                ProductDto.class,productId);

        ProductDto productDto = response.getBody();

        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());

        Category category = new Category(); // just set the name of category
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());

        return product;
    }

    @Override
    public Product addNewProduct(ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                product,ProductDto.class);
        ProductDto productDto = response.getBody();

        Product product1 = new Product();
        product1.setId(productDto.getId());
        product1.setTitle(productDto.getTitle());
        product1.setPrice(productDto.getPrice());

        Category category = new Category(); // just set the name of category
        category.setName(productDto.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto.getImage());
        product1.setDescription(productDto.getDescription());

        return product1;
    }

    @Override
    public Product updateProduct(Long productId, ProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = "https://fakestoreapi.com/products/{id}";
        HttpEntity<ProductDto> requestUpdate = new HttpEntity<>(product);

        // Sending the PUT request and receiving the response
        ResponseEntity<ProductDto> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                requestUpdate,
                ProductDto.class,
                productId
        );

        // Returning the updated product

        ProductDto productDto = response.getBody();

        Product product1 = new Product();
        product1.setId(productDto.getId());
        product1.setTitle(productDto.getTitle());
        product1.setPrice(productDto.getPrice());

        Category category = new Category(); // just set the name of category
        category.setName(productDto.getCategory());
        product1.setCategory(category);
        product1.setImageUrl(productDto.getImage());
        product1.setDescription(productDto.getDescription());

        return product1;
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            restTemplate.delete("https://fakestoreapi.com/products/{id}", productId);
            return true;
        }



        catch (Exception e) {
            // Catch any other exceptions
            System.err.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }
}




package com.productservice.productwithfakestore2.controllers;

import com.productservice.productwithfakestore2.dtos.ProductDto;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("productId") Long productId){


        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("auth-token","notokenbro");
        //we can add header used for authentication but it is just testing
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(productId),
                headers,
                HttpStatus.OK
        );



        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productdto){
        ResponseEntity response = new ResponseEntity(
                productService.addNewProduct(productdto),HttpStatus.OK
        );
        return response;
    }

    @PutMapping("/{productId}")

    public ResponseEntity<Product> updateProduct(@PathVariable("productId")Long productId,
                                @RequestBody ProductDto productDto){
        ResponseEntity response = new ResponseEntity(
                productService.updateProduct(productId,productDto),
                HttpStatus.OK
        );
        return response;

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId")Long productId){
        Boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

}

package com.productservice.productwithfakestore2.controllers;

import com.productservice.productwithfakestore2.dtos.GenericProductDto;
import com.productservice.productwithfakestore2.exceptions.NotFoundException;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }
    @GetMapping("")
    public List<GenericProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GenericProductDto> getSingleProduct(@PathVariable("productId") Long productId) throws NotFoundException {


        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();

        headers.add("auth-token","notokenbro");
        //we can add header used for authentication but it is just testing

        GenericProductDto genericProductDto = productService.getSingleProduct((productId));


        ResponseEntity<GenericProductDto> response = new ResponseEntity<>(
                genericProductDto,
                headers,
                HttpStatus.OK
        );

        return response;
    }

    @PostMapping()
    public ResponseEntity<GenericProductDto> addNewProduct(@RequestBody GenericProductDto productdto){
        ResponseEntity response = new ResponseEntity(
                productService.addNewProduct(productdto),HttpStatus.OK
        );
        return response;
    }

    @PatchMapping ("/{productId}")

    public ResponseEntity<GenericProductDto> updateProduct(@PathVariable("productId")Long productId,
                                @RequestBody GenericProductDto productDto){
        ResponseEntity response = new ResponseEntity(
                productService.updateProduct(productId,productDto),
                HttpStatus.OK
        );
        return response;

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable("productId")Long productId) throws NotFoundException {
        GenericProductDto genericProductDto = productService.deleteProduct(productId);

      return new ResponseEntity<>(genericProductDto,HttpStatus.OK);
    }



}

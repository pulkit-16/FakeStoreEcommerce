package com.productservice.productwithfakestore2.dtos;

import com.productservice.productwithfakestore2.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSingleProductResponseDto {
    private Product product;
}

package com.productservice.productwithfakestore2.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;

    private String image;
    private  String category;
    private RatingDto rating;

}


//when we send data or recieves data from outside we use dtos
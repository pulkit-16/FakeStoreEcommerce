package com.productservice.productwithfakestore2.thirdPartyClients.fakestoreApi;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;

    private String image;
    private  String category;
}

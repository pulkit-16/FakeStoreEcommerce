package com.productservice.productwithfakestore2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProductwithFakestore2Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductwithFakestore2Application.class, args);
    }

}

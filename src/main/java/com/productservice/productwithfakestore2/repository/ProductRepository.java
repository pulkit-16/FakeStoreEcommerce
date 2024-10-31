package com.productservice.productwithfakestore2.repository;

import com.productservice.productwithfakestore2.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);

    Product findProductById(Long id);


    List<Product> findProductByCategory_Name(String CategoryName);
    
}


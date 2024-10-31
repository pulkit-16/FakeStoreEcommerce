package com.productservice.productwithfakestore2.repository;

import com.productservice.productwithfakestore2.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    Category save (Category category);

    List<Category> findAllByIdIn(List<Long> Ids);



}

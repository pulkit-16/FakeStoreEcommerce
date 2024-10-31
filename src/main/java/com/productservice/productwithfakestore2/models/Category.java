package com.productservice.productwithfakestore2.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;

    @OneToMany( mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Product> products;
}

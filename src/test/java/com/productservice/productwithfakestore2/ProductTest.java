package com.productservice.productwithfakestore2;

import com.productservice.productwithfakestore2.models.Category;
import com.productservice.productwithfakestore2.models.Product;
import com.productservice.productwithfakestore2.repository.CategoryRepository;
import com.productservice.productwithfakestore2.repository.ProductRepository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void savingProductAndCategory(){


        Product product = new Product();
        Category category = new Category();
        category.setName("Phones");
        Category savedCategory = categoryRepository.save(category);



        product.setTitle("Iphone16");
        product.setPrice(120000.0);
        product.setDescription("samet phone");
        product.setImageUrl("hello");
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);

        Product product1 = new Product();
        Category category1 = new Category();
        category1.setName("Electronics");
        categoryRepository.save(category1);



        product1.setTitle("Iphone15");
        product1.setPrice(100000.0);
        product1.setDescription("smartt phone");
        product1.setImageUrl("hello hi");
        product1.setCategory(category1);
        productRepository.save(product1);



        Product product2 = new Product();


        product2.setTitle("oven");
        product2.setPrice(10000.0);
        product2.setDescription(" samert oven");
        product2.setImageUrl("hello hi");
        product2.setCategory(category1);
         productRepository.save(product2);


        Product product3 = new Product();


        product3.setTitle("samsung S10");
        product3.setPrice(60000.0);
        product3.setDescription(" smart phone with 8gb ram ");
        product3.setImageUrl("hello hi yo");
        product3.setCategory(category);
        productRepository.save(product3);


    }

    @Test
    @Transactional
    void deleteProduct() {
        try {
            if (productRepository.existsById(2L)) {
                productRepository.deleteById(2L);
                productRepository.flush();
                System.out.println("Deleted product with ID 2");
            }

            if (productRepository.existsById(3L)) {
                productRepository.deleteById(3L);
                productRepository.flush();
                System.out.println("Deleted product with ID 3");
            }



        } catch (Exception e) {
            System.err.println("An error occurred during deletion: " + e.getMessage());
        }
    }





    @Test

    void deleteCategory(){
        Optional<Category> category = categoryRepository.findById(2L);
        if (category.isPresent() && category.get().getProducts().isEmpty()) {
            categoryRepository.delete(category.get());

            System.out.println("Deleted empty category with ID 2");
        }
    }

@Test
@Transactional
void getProductsForCategory(){


        List<Category> categories = categoryRepository.findAllByIdIn(List.of(1L,2L));


        for(Category category : categories){
            for(Product product: category.getProducts()){
                System.out.println(product.getTitle());
            }
        }

}


//    @Test
//   @Transactional
//    void  fetchTypesTest(){
//        Product product1 = productRepository.findProductById(1L);
//        System.out.println("Fetched Product");
//
//
//    }
}



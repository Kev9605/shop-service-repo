package com.example.product_service;

import com.example.product_service.product.Product;
import com.example.product_service.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveAndFind() {
        Product product = new Product();
        product.setName("Keyboard");
        product.setPrice(49.99);
        product.setQuantity(10);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct.getId());
        assertTrue(productRepository.findById(savedProduct.getId()).isPresent());
    }
}

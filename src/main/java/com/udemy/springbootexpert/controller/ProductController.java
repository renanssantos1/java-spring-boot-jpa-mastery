package com.udemy.springbootexpert.controller;

import com.udemy.springbootexpert.entity.ProductEntity;
import com.udemy.springbootexpert.model.Product;
import com.udemy.springbootexpert.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    public Product   saveProduct(@RequestBody Product product){

        ProductEntity productEntity = new ProductEntity(product);

        productRepository.save(productEntity);

//        logger.info("Product saved successfully: " + productEntity.getId());
        return product;
    }
}

package com.udemy.springbootexpert.controller;

import com.udemy.springbootexpert.entity.ProductEntity;
import com.udemy.springbootexpert.model.Product;
import com.udemy.springbootexpert.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    @Transactional
    public ResponseEntity saveProduct(@RequestBody @Valid Product product, UriComponentsBuilder uriBuilder){

        ProductEntity productEntity = new ProductEntity(product);

        productRepository.save(productEntity);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productEntity.getId()).toUri();

        logger.info("Product saved successfully: " );
        return ResponseEntity.created(uri).body(productEntity);

    }
}

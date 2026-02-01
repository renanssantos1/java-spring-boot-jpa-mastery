package com.udemy.springbootexpert.controller;

import com.udemy.springbootexpert.entity.ProductEntity;
import com.udemy.springbootexpert.model.Product;
import com.udemy.springbootexpert.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
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
    public ResponseEntity saveProduct(@RequestBody @Valid Product product,
                                      UriComponentsBuilder uriBuilder) {
        ProductEntity productEntity = new ProductEntity(product);

        productRepository.save(productEntity);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(productEntity.getId()).toUri();

        logger.info("Product saved successfully: " + productEntity.getId());
        return ResponseEntity.created(uri).body(productEntity);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        productEntity.orElseThrow(() -> {
                    logger.warning("Product not found: " + id);
                    return new RuntimeException("Product not found");
                }
        );

        return ResponseEntity.ok(productEntity.get());
    }

    @PutMapping("/products/{id}")
    @Transactional
    public ResponseEntity<ProductEntity> updateProductById(@PathVariable String id,
                                                           @RequestBody @Valid Product product) {
        Optional<ProductEntity> existingProductEntity = productRepository.findById(id);
        existingProductEntity.orElseThrow(() -> {
                    logger.warning("Product not found for update: " + id);
                    return new RuntimeException("Product not found");
                }
        );
        ProductEntity productEntity = existingProductEntity.get();
        productEntity.setName(product.name());
        productEntity.setDescription(product.description());
        productEntity.setPrice(product.price());

        productRepository.save(productEntity);

        logger.info("Product updated successfully: " + id);
        return ResponseEntity.ok(productEntity);
    }

    @DeleteMapping("/products/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        productEntity.orElseThrow(() -> {
                    logger.warning("Product not found for deletion: " + id);
                    return new RuntimeException("Product not found");
                }
        );

        productRepository.deleteById(id);
        logger.info("Product deleted successfully: " + id);
        return ResponseEntity.noContent().build();
    }
}

package com.udemy.springbootexpert.controller;

import com.udemy.springbootexpert.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());

    @PostMapping("/products")
    public void saveProduct(@RequestBody Product product){
        logger.info("Product saved: " + product);
    }
}

package com.udemy.springbootexpert.repository;

import com.udemy.springbootexpert.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> { }

package com.udemy.springbootexpert.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Product(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotNull
        Double price) { }



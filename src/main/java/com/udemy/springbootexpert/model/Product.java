package com.udemy.springbootexpert.model;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public record Product(
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        @Pattern(regexp = "\\d+(\\.\\d{1,2})?")
        Double price) { }



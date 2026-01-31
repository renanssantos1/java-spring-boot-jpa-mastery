package com.udemy.springbootexpert.model;

import lombok.Data;

public record Product(String name, String description, Double price) { }
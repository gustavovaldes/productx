package com.gustavo.spring.lab.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String sku;
    private Category category;
}

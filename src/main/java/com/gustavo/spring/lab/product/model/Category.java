package com.gustavo.spring.lab.product.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Category {

    @Id
    private String code;
    private String name;
    private String description;
}

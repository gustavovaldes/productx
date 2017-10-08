package com.gustavo.spring.lab.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private String sku;
    @OneToOne
    private Category category;
}

package com.gustavo.spring.lab.product.model;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String currency = "EUR";
    @Ignore
    @Transient
    private double priceEUR;
    @ManyToOne
    private Category category;
}

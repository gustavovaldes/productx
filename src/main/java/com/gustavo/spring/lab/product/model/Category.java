package com.gustavo.spring.lab.product.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {

    @Id
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
}

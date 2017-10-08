package com.gustavo.spring.lab.product.model;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "inlineCategory", types= {Product.class})
public interface InlineCategory {
    String getCode();
    String getName();
    String getDescription();
    Category getCategory();
}

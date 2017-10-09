package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.InlineCategory;
import com.gustavo.spring.lab.product.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(excerptProjection = InlineCategory.class, collectionResourceRel = "product", path = "product")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}

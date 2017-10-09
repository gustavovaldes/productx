package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.Product;
import com.gustavo.spring.lab.product.service.FxRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Service;

@Service
public class ProductProcessorImpl implements ResourceProcessor<Resource<Product>> {

    @Autowired
    FxRateService fxRateService;
    private static final String BASE_CURRENCY = "EUR" ;


    @Override
    public Resource<Product> process(Resource<Product> resource) {
        double rate = 1.0d;
        if (!BASE_CURRENCY.equals(resource.getContent().getCurrency())) {
            rate = fxRateService.getRate(resource.getContent().getCurrency());
        }
        resource.getContent().setPriceEUR(resource.getContent().getPrice() * rate);
        return resource;
    }
}


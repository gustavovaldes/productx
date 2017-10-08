package com.gustavo.spring.lab.product.service;

import com.gustavo.spring.lab.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Service;

@Service
public class ProductProcessor implements ResourceProcessor<Resource<Product>> {

    @Autowired
    FxRateServiceImpl fxRateService;

    @Override
    public Resource<Product> process(Resource<Product> resource) {


        double rate = fxRateService.getConvertion(resource.getContent().getCurrency());
        resource.getContent().setDescription(resource.getContent().getDescription() + rate);
        resource.getContent().setPriceEUR(resource.getContent().getPrice()*rate);
        return resource;
    }
}


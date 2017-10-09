package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.Product;
import com.gustavo.spring.lab.product.service.FxRateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Service;

@Service
public class ProductProcessorImpl implements ResourceProcessor<Resource<Product>> {

    @Autowired
    FxRateServiceImpl fxRateService;

    @Override
    public Resource<Product> process(Resource<Product> resource) {
        double rate = fxRateService.getRate(resource.getContent().getCurrency());
        resource.getContent().setDescription(resource.getContent().getDescription() + rate);
        resource.getContent().setPriceEUR(resource.getContent().getPrice()*rate);
        return resource;
    }
}


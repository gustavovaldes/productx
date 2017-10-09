package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.Product;
import com.gustavo.spring.lab.product.service.FxRateService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductProcessorImplTest {

    @Mock
    FxRateService fxRateService;

    @InjectMocks
    ProductProcessorImpl productProcessor = new ProductProcessorImpl();

    @Test
    public void  productFxEnrichmentTest(){
        when(fxRateService.getRate("CAD")).thenReturn(1.5D);
        Resource<Product> resource = new Resource<Product>(new Product(2L, "p3", "d1", "c1", 1.2d, "CAD", 0, null));
        productProcessor.process(resource);
        Assert.assertEquals(resource.getContent().getPriceEUR(), 1.8D , 0.0000001d);
    }

    @Test
    public void  productFxEnrichmentWhenCurrencyIsEURTest(){
        Resource<Product> resource = new Resource<Product>(new Product(2L, "p3", "d1", "c1", 1.2d, "EUR", 0, null));
        productProcessor.process(resource);
        Assert.assertEquals(resource.getContent().getPriceEUR(), 1.2D , 0);
        verify(fxRateService, times(0)).getRate(anyObject());
    }
}
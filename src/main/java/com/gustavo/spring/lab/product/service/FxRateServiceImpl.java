package com.gustavo.spring.lab.product.service;

import com.gustavo.spring.lab.product.model.FxRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FxRateServiceImpl implements FxRateService {

    @Value("${com.gustavo.spring.lab.fxRateProvider.url}")
    private String FX_RATE_PROVIDER_URI;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable("fxRates")
    @Override
    public double getConversion(String currency) {
        FxRates fxRates = restTemplate.getForObject(FX_RATE_PROVIDER_URI, FxRates.class);
        if(!fxRates.getRates().containsKey(currency)){
            //TODO decide
            return -1;
        }
        return fxRates.getRates().get(currency);
    }
}

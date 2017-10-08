package com.gustavo.spring.lab.product.service;

import com.gustavo.spring.lab.product.model.FxRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FxRateServiceImpl {

    @Autowired
    RestTemplate restTemplate;

    String baseCurrency = "EUR";

    public double getConvertion(String currency) {
        FxRates fxRates = restTemplate.getForObject("http://api.fixer.io/latest", FxRates.class);
        return fxRates.getRates().get(currency);
    }
}

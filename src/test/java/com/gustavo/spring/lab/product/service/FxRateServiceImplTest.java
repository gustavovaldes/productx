package com.gustavo.spring.lab.product.service;

import com.gustavo.spring.lab.product.model.FxRates;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FxRateServiceImplTest {

    private final String FX_PROVIDER_URI = "http://api.fixer.io/latest";

    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private FxRateService fxRateService = new FxRateServiceImpl();

    @Before
    public void init() throws Exception {
        Field uri = FxRateServiceImpl.class.getDeclaredField("FX_RATE_PROVIDER_URI");
        uri.setAccessible(true);
        ReflectionUtils.setField(uri, fxRateService, FX_PROVIDER_URI);
    }

    @Test
    public void getConversion() throws Exception {
        FxRates fxRates = new FxRates();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 1d);
        rates.put("CAD", 1.4d);
        fxRates.setBase("EUR");
        fxRates.setDate("2017-01-02");
        fxRates.setRates(rates);

        when(restTemplate.getForObject(FX_PROVIDER_URI, FxRates.class)).thenReturn(fxRates);

        Assert.assertEquals(1.4d, fxRateService.getRate("CAD"), 0.d);
        Assert.assertEquals(-1, fxRateService.getRate("CLP"), 0.d);
    }

}
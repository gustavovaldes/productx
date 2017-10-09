package com.gustavo.spring.lab.product.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class FxRatesTest {
    @Autowired
    private JacksonTester<FxRates> json;

    @MockBean
    private RestTemplate restTemplate;
    @Test
    public void fxRatesMarshallingTest() throws Exception{
        FxRates fxRates = new FxRates();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 1d);
        rates.put("CAD", 1.4d);
        fxRates.setBase("EUR");
        fxRates.setDate("2017-01-02");
        fxRates.setRates(rates);

        String content = "{\"base\":\"EUR\",\"date\":\"2017-01-02\", \"rates\": { \"EUR\": 1, \"CAD\":1.4}}";
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(fxRates)).isEqualToJson(content);
        // Or use JSON path based assertions
        assertThat(this.json.write(fxRates)).extractingJsonPathStringValue("@.date")
                .isEqualTo("2017-01-02");
    }

    @Test
    public void fxRatesUnmarshallingTest() throws Exception{
        String content = "{\"base\":\"EUR\",\"date\":\"2017-01-02\", \"rates\": { \"EUR\": 1, \"CAD\":1.4}}";
        FxRates fxRates = new FxRates();
        Map<String, Double> rates = new HashMap<>();
        rates.put("EUR", 1d);
        rates.put("CAD", 1.4d);
        fxRates.setBase("EUR");
        fxRates.setDate("2017-01-02");
        fxRates.setRates(rates);
        assertThat(this.json.parse(content))
                .isEqualTo(fxRates);
        assertThat(this.json.parseObject(content).getRates().get("CAD")).isEqualTo(1.4d);
    }
}
package com.gustavo.spring.lab.product.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ProductTest {

    @Autowired
    private JacksonTester<Product> json;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testSerialize() throws Exception {
        Product details = new Product(1L, "p1", "d1", "c1", 1.3d, "EUR", 0, null);
        String content = "{\"id\":1, \"code\":\"c1\",\"name\":\"p1\", \"description\":\"d1\", \"price\":1.3}";
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson(content);
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.description")
                .isEqualTo("d1");
    }

    @Test
    public void testDeserialize() throws Exception {
        String content = "{\"id\":1, \"code\":\"c1\",\"name\":\"p1\", \"description\":\"d1\", \"price\":1.3, \"currency\":\"EUR\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new Product(1L, "p1", "d1", "c1", 1.3d, "EUR", 0, null));
        assertThat(this.json.parseObject(content).getCode()).isEqualTo("c1");
    }
}
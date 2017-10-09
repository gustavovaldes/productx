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
public class CategoryTest {

    @Autowired
    private JacksonTester<Category> json;

    @MockBean
    private RestTemplate restTemplate;
    @Test
    public void testSerialize() throws Exception{
        Category details = new Category("cat1","category1","category 1");
        String content = "{\"code\":\"cat1\",\"name\":\"category1\", \"description\":\"category 1\"}";
        // Assert against a `.json` file in the same package as the test
        assertThat(this.json.write(details)).isEqualToJson(content);
        // Or use JSON path based assertions
        assertThat(this.json.write(details)).extractingJsonPathStringValue("@.name")
                .isEqualTo("category1");
    }

    @Test
    public void testDeserialize() throws Exception{
        String content = "{\"code\":\"cat1\",\"name\":\"category1\", \"description\":\"category 1\"}";
        assertThat(this.json.parse(content))
                .isEqualTo(new Category("cat1", "category1","category 1"));
        assertThat(this.json.parseObject(content).getCode()).isEqualTo("cat1");
    }
}
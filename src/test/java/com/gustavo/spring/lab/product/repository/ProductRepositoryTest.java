package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {


    @Autowired
    ProductRepository productRepository;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void createProductTest(){
        Product product = new Product(null, "p1", "d1", "c1", 1.3d, "EUR", 0, null);
        Product result = productRepository.save(product);
        assertEquals(product.getName(), result.getName());
        productRepository.deleteAll();
    }

    @Test
    public void findAllProductTest(){
        Product entity = new Product(null, "p2", "d1", "c1", 1.3d, "EUR", 0, null);
        productRepository.save(entity);
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("d1", result.get(0).getDescription());
        productRepository.deleteAll();

    }

    @Test
    public void deleteProductTest(){
        Product entity = new Product(null, "p3", "d1", "c1", 1.3d, "EUR", 0, null);
        productRepository.save(entity);
        List<Product> result = new ArrayList<>();
        productRepository.delete(entity.getId());
        productRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void updateProductTest(){
        Product entity = new Product(null, "p4", "d1", "c1", 1.3d, "EUR", 0, null);
        productRepository.save(entity);
        List<Product> result = new ArrayList<>();
        Product entityUpdated = new Product(entity.getId(), "p1", "d1Updated", "c1", 1.3d, "EUR", 0, null);
        productRepository.save (entityUpdated);
        productRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("d1Updated", result.get(0).getDescription());
    }
}
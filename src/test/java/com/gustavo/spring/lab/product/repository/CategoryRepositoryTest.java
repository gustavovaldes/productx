package com.gustavo.spring.lab.product.repository;

import com.gustavo.spring.lab.product.model.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CategoryRepository categoryRepository;

    @MockBean
    RestTemplate restTemplate;

    @Test
    public void createCategoryTest(){
        Category category = new Category("c1","n1","d1");
        Category result = categoryRepository.save(category);
        assertEquals(category, result);
    }

    @Test
    public void findAllCategoryTest(){
        Category entity = new Category("c2","n2","d2");
        testEntityManager.persist(entity);
        List<Category> result = new ArrayList<>();
        categoryRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("d2", result.get(0).getDescription());

    }

    @Test
    public void deleteCategoryTest(){
        Category entity = new Category("c2","n2","d2");
        testEntityManager.persist(entity);
        List<Category> result = new ArrayList<>();
        categoryRepository.delete("c2");
        categoryRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void updateCategoryTest(){
        Category entity = new Category("c2","n2","d2");
        testEntityManager.persist(entity);
        List<Category> result = new ArrayList<>();
        Category entityUpdated = new Category("c2","n2","d2Updated");
        categoryRepository.save (entityUpdated);
        categoryRepository.findAll().forEach(x->{result.add(x);});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("d2Updated", result.get(0).getDescription());
    }

}
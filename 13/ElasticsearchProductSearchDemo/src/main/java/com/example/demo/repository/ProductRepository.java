package com.example.demo.repository;


import com.example.demo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 Elasticsearch案例
 */
@Component
public interface ProductRepository extends ElasticsearchRepository<Product,Long> {
    Product findById(long id);
    Product findByName(String name);
    List<Product> findByPriceBetween(double price1, double price2);
}
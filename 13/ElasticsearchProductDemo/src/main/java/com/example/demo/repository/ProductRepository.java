package com.example.demo.repository;


import com.example.demo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 Elasticsearch案例
 */
@Component
public interface ProductRepository extends ElasticsearchRepository<Product,Long> {
    Product findById(long id);
    Product findByName(String name);
}
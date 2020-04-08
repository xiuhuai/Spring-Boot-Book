package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: QueryTest
 * Author:   longzhonghua
 * Date:     2019/5/5 13:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    /**
     * Description: 根据方法名查询。
     */
    public void queryByPriceBetween() {
        Iterable<Product> list = productRepository.findByPriceBetween(7.00, 8.00);

        for (Product product : list) {
            System.out.println(product);
        }

    }



    @Test
    /**
     * Description: 单个词查询
     */
    public void termQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        // 查询词,只能查询一个汉字,或者一个英文单词
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.termQuery("name", "富"));
        // 搜索，获取结果
        Page<Product> products = productRepository.search(nativeSearchQueryBuilderQueryBuilder.build());
        // 总条数
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    /**
     * Description: 多参数termsQuery
     */
    public void termsQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        // 查询词,只能查询一个汉字,或者一个英文单词
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.termsQuery("name", "富","帅"));
        // 搜索，获取结果
        Page<Product> products = productRepository.search(nativeSearchQueryBuilderQueryBuilder.build());
        // 总条数
        for (Product product : products) {
            System.out.println(product);
        }
    }


    @Test
    /**
     * Description: matchQuery分词查询，采用默认的分词器。
     */
    public void matchQuery() {
        // 查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilderQueryBuilder = new NativeSearchQueryBuilder();
        // 查询词
        nativeSearchQueryBuilderQueryBuilder.withQuery(QueryBuilders.matchQuery("name", "红士"));
        // 搜索，获取结果
        Page<Product>  products= productRepository.search(nativeSearchQueryBuilderQueryBuilder.build());
        for (Product product : products) {
            System.out.println(product);
        }
    }

    @Test
    /**
     * Description: 多字段查询
     */
    public void multiMatchQuery() {
        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder  = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("红富士金帅","name","body"));
        // 搜索，获取结果
        Page<Product> products = productRepository.search(nativeSearchQueryBuilder.build());
        // 总条数
        for (Product product : products) {
            System.out.println(product);
        }
    }


}
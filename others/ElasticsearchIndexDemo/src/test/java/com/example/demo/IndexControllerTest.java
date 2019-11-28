package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: IndexControllerTest
 * Author:   longzhonghua
 * Date:     2019/5/5 10:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IndexControllerTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void creatIndex() {
        elasticsearchTemplate.createIndex("demoindex");
    }
    @Test
    public void queryIndex(){
        elasticsearchTemplate.indexExists("demoindex");
        System.out.println( elasticsearchTemplate.indexExists("demoindex"));

    }




    @Test
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex("demoindex2");
    }


}
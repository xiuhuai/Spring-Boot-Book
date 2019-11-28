package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: IndexController
 * Author:   longzhonghua
 * Date:     2019/5/5 10:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class IndexController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    public void creatIndex(){
        elasticsearchTemplate.createIndex("DemoIndex");
    }
    public void queryIndex(){
        elasticsearchTemplate.indexExists("DemoIndex");
    }
}

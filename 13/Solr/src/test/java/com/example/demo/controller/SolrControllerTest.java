package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.SolrService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: SolrControllerTest
 * Author:   longzhonghua
 * Date:     2019/5/9 11:10
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrControllerTest {
    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient solrClient;
    @Test
    public void addUser() throws IOException, SolrServerException {
        // 构造一篇文档
        //往doc中添加字段，在客户端这边添加的字段必须在服务端的配置文件中有定义
        User user = new User();
        user.setId("8888888");
        user.setName("龙知然");
        solrClient.addBean(user);
        solrClient.commit();
    }

    //根据di查询
    @Test
    public void getByIdFromSolr() throws IOException, SolrServerException {
        //根据id查询内容
        String id="8888888";
        SolrDocument solrDocument = solrClient.getById(id);
        //获取filedName
        Collection<String> fieldNames = solrDocument.getFieldNames();
        //获取file名和内容
        Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();
        List<SolrDocument> childDocuments = solrDocument.getChildDocuments();
        String results = solrDocument.toString();
        System.out.println(results);

    }


    @Test
    public void updateUser()  throws IOException, SolrServerException {
        User user = new User();
        user.setId("8888888");
        user.setName("知然");
        solrClient.addBean(user);
        solrClient.commit();
    }

    @Test
    public void delById()  throws IOException, SolrServerException {
        //根据id删除信息
        UpdateResponse updateResponse = solrClient.deleteById("8888888");
        //执行的时间
        long elapsedTime = updateResponse.getElapsedTime();
        int qTime = updateResponse.getQTime();
        //请求地址
        String requestUrl = updateResponse.getRequestUrl();
        //请求的结果{responseHeader={status=0,QTime=2}}
        NamedList<Object> response = updateResponse.getResponse();
        //请求结果的头{status=0,QTime=2}
        NamedList responseHeader = updateResponse.getResponseHeader();
        //请求的状态 0
        solrClient.commit();
        int status = updateResponse.getStatus();
        //成功则返回0.要是没有文档被删除也会返回0,代表根本没有

    }


    @Test
    public void queryAllOne() {
    }

    @Test
    public void queryAll() throws IOException, SolrServerException {


        //第二种方式
        SolrQuery solrQuery = new SolrQuery();
        // 设置默认搜索域
        solrQuery.setQuery("*:*");
//        solrQuery.addField("*");
        solrQuery.set("q", "知然");
        solrQuery.add("q", "name:然");
        // 设置返回结果的排序规则
        solrQuery.setSort("id", SolrQuery.ORDER.asc);
        //设置查询的条数
        solrQuery.setRows(50);
        //设置查询的开始
        solrQuery.setStart(0);
        // 设置分页参数
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        //设置高亮
        solrQuery.setHighlight(true);
        //设置高亮的字段
        solrQuery.addHighlightField("name");
        //设置高亮的样式
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");
        System.out.println(solrQuery);
        QueryResponse response = solrClient.query(solrQuery);
        //返回高亮显示结果
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //response.getResults();查询返回的结果

        SolrDocumentList documentList = response.getResults();
        long numFound = documentList.getNumFound();
        System.out.println("总共查询到的文档数量： " + numFound);
        for (SolrDocument solrDocument : documentList) {
            System.out.println(solrDocument);
            System.out.println(solrDocument.get("name"));
        }
        System.out.println(highlighting);
    }
}
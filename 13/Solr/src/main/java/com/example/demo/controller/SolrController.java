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
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longzhonghua
 * @data 2/24/2019 11:36 PM
 */
@RestController
public class SolrController {
    @Autowired
    private SolrService solrService;

    @Autowired
    private SolrClient solrClient;

    //批量增加
    @RequestMapping("/addUsers")
    public void addUsers() throws IOException, SolrServerException {
        List<User> users = solrService.addUser();
        solrClient.addBeans(users);
        solrClient.commit();
    }

    //增加
    @RequestMapping("/addUser")
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
    @RequestMapping("/getById/{id}")
    public String getByIdFromSolr(@PathVariable("id") String id) throws IOException, SolrServerException {

        //根据id查询内容
        SolrDocument solrDocument = solrClient.getById(id);
        //获取filedName
        Collection<String> fieldNames = solrDocument.getFieldNames();
        //获取file名和内容
        Map<String, Object> fieldValueMap = solrDocument.getFieldValueMap();

//            int childDocumentCount = solrDocument.getChildDocumentCount();

        List<SolrDocument> childDocuments = solrDocument.getChildDocuments();
        String results = solrDocument.toString();
        //fieldNames
        // fieldValueMap
        // childDocuments;
        return results;

    }

    //改
    @RequestMapping("/updateUser")
    public void updateUser() throws IOException, SolrServerException {
        User user = new User();
        user.setId("8888888");
        user.setName("知然");

        solrClient.addBean(user);
        solrClient.commit();
    }

    //根据di删除
    @RequestMapping("/delById/{id}")
    public String delById(@PathVariable("id") String id) throws IOException, SolrServerException {
        //根据id删除信息
        UpdateResponse updateResponse = solrClient.deleteById(id);
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
        return status == 0 ? "success" : "failed";
    }

    @RequestMapping("/delAll")
    public void delAll() throws IOException, SolrServerException {
        solrClient.deleteByQuery("*:*");
        UpdateResponse response = solrClient.commit();

    }

    @RequestMapping("/queryAllOne")
    public String queryAllOne() throws IOException, SolrServerException {
 /*  //第一种方式
        Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "*:*");
        //queryParamMap.put("f1", "id,name");
        queryParamMap.put("f1", "id:88");
        queryParamMap.put("sort", "id asc");
        MapSolrParams mapSolrParams = new MapSolrParams(queryParamMap);
        solrClient.query(mapSolrParams);

        for (Map.Entry<String, String[]> mapSolrParam : mapSolrParams) {

            System.out.println("solrDocument==============" + mapSolrParam);
        }

        return mapSolrParams;*/

        SolrQuery query = new SolrQuery();

        // 给query设置一个主查询条件：关键词
        query.setQuery("*:*");
        query.add("q", "name:然");

        QueryResponse response = solrClient.query(query);

        SolrDocumentList docs = response.getResults();

        long numFound = docs.getNumFound();

        System.out.println("总共查询到的文档数量： " + numFound);

        return docs.toString();

    }


    @RequestMapping("/queryAll")
    public Object queryAll() throws IOException, SolrServerException {


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
            System.out.println("solrDocument==============" + solrDocument);
            System.out.println("solrDocument==============" + solrDocument.get("name"));
        }
        return highlighting;
    }
}

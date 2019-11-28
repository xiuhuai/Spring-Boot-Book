package com.example.demo.controller;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
/**
 * Author:   longzhonghua
 * Date:     4/8/2019 10:20 AM
 */
@RestController
public class PostController {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/postForEntity")
    public User postForEntity() {
        //RestTemplate client= restTemplateBuilder.build();
// 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "20180416");
        paramMap.add("id", 4);
        User user = new User();
        user.setName("hongwei");
        user.setId(4);
        //方法的第一参数表示要调用的服务的地址
        //方法的第二个参数表示上传的参数
        //方法的第三个参数表示返回的消息体的数据类型
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:8080/postuser", paramMap, User.class);
        return responseEntity.getBody();
    }

    @RequestMapping("/postForObject")
    public String postForObject() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "20180416");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        User user = new User();
        user.setName("hongwei");
        user.setId(5);
        String response = client.postForObject("http://localhost:8080/postuser", paramMap, String.class);
        return response;
    }

    @RequestMapping("/postForLocation")
    public URI postForLocation() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "20180416");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        User user = new User();
        user.setName("hongwei");
        user.setId(6);
        URI response = client.postForLocation("http://localhost:8080/postuser", paramMap);
        return response;
    }
    @RequestMapping("/postForexchange")
    public String postForexchange() {
        // 封装参数，千万不要替换为Map与HashMap，否则参数无法传递
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "20180416");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        //headers.set("id", "long");
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap,headers);
        ResponseEntity<String> response = client.exchange("http://localhost:8080/postuser",HttpMethod.POST,httpEntity,String.class,paramMap);
        return response.getBody();
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author longzhonghua
 * @data 2019/02/05 20:44
 */
@RestController
public class GetPersonInfoController {
    //获取配置文件中的age
    @Value("${age}")
    private int age;

    //获取配置文件中的name
    @Value("${name}")
    private String name;
@GetMapping("/getage")
    public int getAge() {
        return age;
    }
    @GetMapping("/getname")
    public String getName() {
        return name;
    }

    @Autowired
    private GetPersonInfoProperties getPersonInfoProperties;
    @GetMapping("/getpersonproperties")
    public String getpersonproperties() {
        return getPersonInfoProperties.getName()+getPersonInfoProperties.getAge();
    }
}

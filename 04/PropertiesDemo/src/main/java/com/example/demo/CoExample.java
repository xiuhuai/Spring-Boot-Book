package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CoExample
 * Author:   longzhonghua
 * Date:     2019/4/28 14:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
@Component
@ConfigurationProperties(prefix ="com.example")
public class CoExample{
    private String name;
    private int age;
    private List<String> address;
}

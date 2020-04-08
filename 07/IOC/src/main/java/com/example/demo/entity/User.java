package com.example.demo.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: Student
 * Author:   longzhonghua
 * Date:     2019/4/16 17:12
 *
 * @Description: $description$
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@Data
public class User implements Serializable {
    private int id;
    private String name;
    }

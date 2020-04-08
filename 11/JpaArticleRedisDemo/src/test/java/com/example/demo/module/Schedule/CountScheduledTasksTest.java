package com.example.demo.module.Schedule;

import com.example.demo.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: CountScheduledTasksTest
 * Author:   longzhonghua
 * Date:     2019/5/8 16:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountScheduledTasksTest {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void syncViews() {
        Long startTime = System.nanoTime();
        List dtoList = new ArrayList<>();
        Set<String> keySet = stringRedisTemplate.keys("name::*");
        for (String key : keySet) {
            String views = stringRedisTemplate.opsForValue().get(key);
            String sid = key.replaceAll("name::", "");
            long lid = Long.parseLong(sid);
            long lviews = Long.parseLong(views);
            //批量更新可以用Collection<?>大家可以研究下
            articleRepository.updateArticleViewById(lviews, lid);
            //删除key
            stringRedisTemplate.delete(key);
        }

    }
    }

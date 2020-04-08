package com.example.demo.module.Schedule;

import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 3/3/2019 4:21 PM
 * 更新redis的点击量到mysql数据库
 */

@Component
public class CountScheduledTasks {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(cron = "0 00 2 ? * * ") //每天凌晨2:00执行

    public void syncPostViews() {
        Long startTime = System.nanoTime();
        List dtoList = new ArrayList<>();
        Set<String> keySet = stringRedisTemplate.keys("name::*");
        for (String key : keySet) {
            String views = stringRedisTemplate.opsForValue().get(key);
            String sid = key.replaceAll("name::", "");
            long lid = Long.parseLong(sid);
            long lviews = Long.parseLong(views);
            //批量更新可以用Collection<?>
            articleRepository.updateArticleViewById(lviews, lid);
            //删除key
            stringRedisTemplate.delete(key);
        }

    }
}


package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author longzhonghua
 * @data 3/3/2019 10:51 AM
 */
public class RedisUtils {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public long incr(String key, long view) {
        return redisTemplate.opsForValue().increment(key, view);
    }

}

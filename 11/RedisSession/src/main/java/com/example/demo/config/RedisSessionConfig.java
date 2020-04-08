package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author longzhonghua
 * @data 3/4/2019 4:48 PM
 */
@Configuration
@EnableRedisHttpSession//spring-session-data-redis提供的,加了spring-boot-starter-data-redis之后要加spring-session-data-redis依赖
public class RedisSessionConfig {
}

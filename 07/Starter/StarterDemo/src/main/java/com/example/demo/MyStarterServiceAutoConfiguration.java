package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
/**
 * Description: 当类路径classpath下有指定的类的情况下进行自动配置
 */
@ConditionalOnClass(MyStarter.class)
/**
 * Description: 配置文件中matchIfMissing =true时进行自动配置
 */
@ConditionalOnProperty(prefix = "spring.mystarter", value = "enabled", matchIfMissing = true)
public class MyStarterServiceAutoConfiguration {
    @Autowired
    private MyStarterProperties myproperties;
    @Bean
    /**
     * Description: 当容器中没有指定Bean的情况下，自动配置MyStarterService类
     */
    @ConditionalOnMissingBean(MyStarter.class)
    public MyStarter MyStarterService(){
        MyStarter myStarterService = new MyStarter(myproperties);
        return myStarterService;
    }
}

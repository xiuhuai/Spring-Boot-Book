package com.example.demo.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 /**
 * @author longzhonghua
 * @data 2/25/2019 7:31 PM
 */
@Configuration
public class RabbitmqConfig {
     @Bean
     public Queue objectQueue() {
         return new Queue("object");
     }
}

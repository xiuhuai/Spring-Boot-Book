package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 * 队列配置
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Queue regQueue() {
        return new Queue("reg_email");
    }

    /**
     * 订单取消
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayed_exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue queue() {
        Queue queue = new Queue("delay_queue_1", true);
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(delayExchange()).with("delay_queue_1").noargs();
    }

}
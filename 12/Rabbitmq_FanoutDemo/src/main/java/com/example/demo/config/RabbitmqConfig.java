package com.example.demo.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
     public Queue queueA() {
         return new Queue("fanout.A");
     }

     @Bean
     public Queue queueB() {
         return new Queue("fanout.B");
     }

     @Bean
     FanoutExchange fanoutExchange() {
         return new FanoutExchange("fanoutExchanger");
     }

     @Bean
     Binding bindingExchangeA(Queue queueA, FanoutExchange fanoutExchanger) {
         return BindingBuilder.bind(queueA).to(fanoutExchanger);
     }

     @Bean
     Binding bindingExchangeB(Queue queueB, FanoutExchange fanoutExchanger) {
         return BindingBuilder.bind(queueB).to(fanoutExchanger);
     }
}

package com.example.busniess.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {


//    @Bean
//    public MessageConverter converterMessage() {
//        return new Jackson2JsonMessageConverter();
//    }

    /**
     * 交换器
     *
     * @return
     */
    @Bean("inform")
    public DirectExchange creatExchange() {

        return new DirectExchange("inform");
    }

    /**
     * 用户队列
     *
     * @return
     */
    @Bean(name = "user")
    public Queue creatUserQueue() {
        return new Queue("user");
    }

    /**
     * 管理员队列
     *
     * @return
     */
    @Bean("Administrator")
    public Queue creatAdministratorQueue() {
        return new Queue("Administrator");
    }

    @Bean
    public Binding bingUser(@Qualifier("user") Queue queue, @Qualifier("inform") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("user");
    }

    @Bean
    public Binding bingAdmin(@Qualifier("Administrator") Queue queue, @Qualifier("inform") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("admin");
    }
}

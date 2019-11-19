package com.example.busniess.utiles;

import com.example.busniess.entity.InformEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitUtil {

    public static final String EXCHANGE = "exchange";//交换器
    public static final String USERQUEUE = "user";//用户交队列
    public static final String ADMINQUEUE = "Administrator";//管理员队列
    public static final String USERKEY = "user";//用户路由键
    public static final String ADMINkEY = "admin";//管理员路由键
    @Autowired
    private static RabbitTemplate rabbitTemplate;

    /**
     * 发送到指定的路由上
     */
    public static void sendRabbic(String exchange, String key, InformEntity informEntity) {
        rabbitTemplate.convertAndSend(exchange, key, informEntity);
    }


}

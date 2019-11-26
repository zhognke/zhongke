package com.example.busniess.utiles;

import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.InformEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RabbitUtil {

    public static final String EXCHANGE = "inform";//交换器
    public static final String USERQUEUE = "user";//用户交队列
    public static final String ADMINQUEUE = "Administrator";//管理员队列
    public static final String USERKEY = "user";//用户路由键
    public static final String ADMINkEY = "admin";//管理员路由键

    /**
     * 要发送的信息
     * @param userName//用户名
     * @param count//内容
     * @param date//时间
     * @return
     */
    public static InformEntity sendRabbic(String userName, String count, Date date) {
        InformEntity informEntity = new InformEntity();
        informEntity.setUserName(userName);
        informEntity.setCount(count);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        informEntity.setTime(df.format(date));
        return informEntity;

    }


}

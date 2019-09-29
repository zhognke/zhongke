package com.example.busniess;

import com.example.busniess.dao.*;
import com.example.busniess.entity.BusinessCenter;
import com.example.busniess.entity.ImageAddress;
import com.example.busniess.entity.Occupancy;
import com.example.busniess.entity.User;
import com.example.busniess.service.OccupancyServiceimplements;
import com.example.busniess.service.UserService;
import com.example.busniess.service.UserServiceImplements;
import com.example.busniess.utiles.EmailUtiles;
import com.example.busniess.utiles.Md5Utiles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusniessApplicationTests {
    //	@Autowired
//	BusinessCenterDao businessCenterDao;
//	@Autowired
//	UserDao userDao;
    @Autowired
    UserServiceImplements userServiceImplements;
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    OccupancyServiceimplements occupancyServiceimplements;
    @Autowired
    ImageAddressDao imageAddressDao;
    @Autowired
    RejectDao rejectDao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    NewsInformationDao newsInformationDao;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 测试例子
     */
    @Test
    public void contextLoads() throws MessagingException {


        System.out.println(newsInformationDao.selectNewsInformationByCategory("33"));


    }

    @Test
    public void send() throws MessagingException {

//        MimeMessage mimeMessage =mailSender.createMimeMessage();
//
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
//
//        mimeMessageHelper.setFrom(from);
//      mimeMessageHelper.setSubject("开会");
//      mimeMessageHelper.setText("<a href='www.baidu.com'>开会</a>",true);
//      mimeMessageHelper.setTo("396802762@qq.com");
//      mailSender.send(mimeMessage);
    }


}





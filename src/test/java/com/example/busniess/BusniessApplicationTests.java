package com.example.busniess;

import com.example.busniess.dao.*;
import com.example.busniess.service.imp.OccupancyServiceimpl;
import com.example.busniess.service.imp.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusniessApplicationTests {
    //	@Autowired
//	BusinessCenterDao businessCenterDao;
//	@Autowired
//	UserDao userDao;
    @Autowired
    UserServiceImpl UserServiceImpl;
    @Autowired
    OccupancyDao occupancyDao;
    @Autowired
    OccupancyServiceimpl occupancyServiceimpl;
    @Autowired
    ImageAddressDao imageAddressDao;
    @Autowired
    RejectDao rejectDao;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    NewsInformationDao newsInformationDao;
    @Autowired
   BusinessCenterDao businessCenterDao;

    @Value("${spring.mail.username}")
    private String from;


    /**
     * 测试例子
     */
    @Test
    public void contextLoads() throws MessagingException {
        System.out.println(occupancyDao.selectOneOccupancy(6));


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





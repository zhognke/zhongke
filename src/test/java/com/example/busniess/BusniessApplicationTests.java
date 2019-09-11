package com.example.busniess;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
import com.example.busniess.service.UserService;
import com.example.busniess.service.UserServiceImplements;
import com.example.busniess.utiles.Md5Utiles;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusniessApplicationTests {
	@Autowired
	UserDao userDao;
	@Autowired
	UserServiceImplements userServiceImplements;
	/**
	 * 测试例子
	 */
	@Test
	public void contextLoads() {
User user=new User();
user.setId(1);
user.setUserName("中国");
user.setPassword("000000");

		userServiceImplements.modifiUser(user);
	}



}

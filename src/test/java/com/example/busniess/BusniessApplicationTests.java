package com.example.busniess;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
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

	/**
	 * 测试例子
	 */
	@Test
	public void contextLoads() {
		User user=new User();

user.setPassword("123444");
user.setPhoneNumber("1356222193");
		userDao.insertUser(user);
	}

}

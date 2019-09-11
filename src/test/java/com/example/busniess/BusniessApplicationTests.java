package com.example.busniess;

import com.example.busniess.dao.UserDao;
import com.example.busniess.entity.User;
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

	/**
	 * 测试例子
	 */
	@Test
	public void contextLoads() {
		String hashAlgorithnName="md5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin");
		int hashIterations = 1024;

		Object result = new SimpleHash(hashAlgorithnName,credentials,salt,hashIterations);
		System.out.println(result);
	}



}

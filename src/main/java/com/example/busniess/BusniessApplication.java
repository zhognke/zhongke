package com.example.busniess;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Timer;
import java.util.TimerTask;

@MapperScan("com.example.busniess.dao")
@SpringBootApplication
public class BusniessApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusniessApplication.class, args);
	}


}



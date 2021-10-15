package com.mongta.myspringboot.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mongta.myspringboot.property.MongtaProperty;

@Component

public class MyRunner implements ApplicationRunner{
//	@Value("${mongta.name}")
//	private String name;
	
	@Value("${mongta.age}")
	private int age;
	
	@Autowired
	private MongtaProperty property;
	
	@Autowired
	private String hello;
	
	//Logger 생성
	Logger logger = LoggerFactory.getLogger(MyRunner.class);
	
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		System.out.println("Logger 구현 클래스 이름 : " + logger.getClass().getName());
		logger.debug("MyRunner Start!!");
		logger.debug("VM argument foo : "+args.containsOption("foo"));
		logger.debug("Program argument bar : "+args.containsOption("bar"));
		logger.debug("mongta age : " + age);
		
		System.out.println("===================");
		
		logger.info("Property Name : "+ property.getName());
		logger.info("Property Age : "+ property.getAge());
		logger.info("Property Full Name : "+ property.getFullName());
		logger.info("Hello Bean : " + hello);
	}
}

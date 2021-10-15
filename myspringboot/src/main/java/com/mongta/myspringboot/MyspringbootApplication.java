package com.mongta.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyspringbootApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MyspringbootApplication.class, args);
		SpringApplication application = new SpringApplication(MyspringbootApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET); // default configure
		application.run(args);
	}

}

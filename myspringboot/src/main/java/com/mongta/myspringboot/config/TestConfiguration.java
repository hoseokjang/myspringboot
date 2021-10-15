package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class TestConfiguration {
	@Profile("test")
	@Bean
	public String hello()
	{
		return "개발 Mode";
	}
}

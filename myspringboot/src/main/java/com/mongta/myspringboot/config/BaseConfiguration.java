package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class BaseConfiguration {
	@Profile("base")
	@Bean
	public String hello()
	{
		return "운영 Mode";
	}
}

package com.mongta.myspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/mypage/**").authenticated()
			.antMatchers("/**").permitAll()
			.and()
			.formLogin()
			.and()
			.httpBasic()
			.and()
			.logout()
			.logoutUrl("/app-logout")
			.deleteCookies("JSESISONID")
			.logoutSuccessUrl("/");
	}
}

package com.mongta.myspringboot.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongta.myspringboot.entity.Account;

@SpringBootTest
public class AccountRepositoryTest {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Test
	public void account() throws Exception
	{
		Account account = new Account();
		account.setUsername("test");
		account.setPassword("1234");
		
		// DB에 object를 insert
		Account saveAcct = accountRepository.save(account);
		System.out.println("ID : " + saveAcct.getId() + ", Name : " + saveAcct.getUsername() + ", Password : " + saveAcct.getPassword());
	
		assertThat(saveAcct).isNotNull();
		
		Optional<Account> optional =  accountRepository.findByUsername("mongta");
		if(optional.isPresent())
		{
			Account mongta = optional.get();
			System.out.println(mongta.getUsername());
		}
		// System.out.println(optional.isPresent()); // 검색한 option이 있는지 T/F로 return해줌
		
		Optional<Account> optional2 = accountRepository.findByUsername("test");
		// orElseThrow argument Supplier의 T get() 메서드를 재정의
		// Account emptyAcct = optional2.orElseThrow(() -> new RuntimeException("Account Not Found"));
		// find해서 null 인 경우에는 Account Not Found를 반환
		// Throwable은 Exception보다 상위에 있음
		optional2.orElseGet(() -> new Account());
		
	}
}

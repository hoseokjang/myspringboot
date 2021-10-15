package com.mongta.myspringboot.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongta.myspringboot.entity.Account;
import com.mongta.myspringboot.property.MongtaProperty;
import com.mongta.myspringboot.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService{

	@Autowired
	private AccountRepository repository;

	private final MongtaProperty property;
	
	@Autowired
	private PasswordEncoder encoder;

	public AccountService(AccountRepository repository, MongtaProperty property) {
		super();
		this.repository = repository;
		this.property = property;
		this.encoder = encoder;
	}
	
	@Override // ctrl+space bar 하면 loaduser...가 바로 보임
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> optional = repository.findByUsername(username);
		Account account = optional.orElseThrow(()->new UsernameNotFoundException(username));
		return new User(account.getUsername(), account.getPassword(), authorities());
	}
	
	private Collection<? extends GrantedAuthority> authorities()
	{	// 위에 userdetail에서 return에서 error에서 create를 클릭하면 자동 생성
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	@PostConstruct // 생성자 만든 직후에 호출
	public void insert()
	{
		Optional<Account> optional = repository.findByUsername(property.getUsername());
		if(optional.isEmpty())
		{	// empty면 새로 생성
			Account account = createAccount(property.getUsername(), property.getPassword());
			System.out.println("Account Password : " + account.getPassword());
		}
	}
	
	// Account 레코드 추가
	public Account createAccount(String username, String password)
	{
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(encoder.encode(password));
		// account.setPassword(password);
		return repository.save(account);
	}
	
	
	
	
}

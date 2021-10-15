package com.mongta.myspringboot;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.mongta.myspringboot.repository.AccountRepository;

public class LambdaTest {
	@Test @Disabled // @Disabled -> 잠시 실행 중지
	public void lambda() throws Exception
	{
		// 1. Anonymous InnerClass 형태로 Runnable의 run()을 재정의 하기
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});
		t1.setName("mongta");
		t1.start();
		
		Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
		t2.setName("몽타");
		t2.start();
		
	}
	
	@Test
	public void iterable() throws Exception
	{
		// Inner Class
		List<String> list = List.of("aa","bb","cc");
		list.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		
		// Lambda
		list.forEach(value -> System.out.println(value));
	
		// Method Reference
		list.forEach(System.out::println);
	}
	
	
}

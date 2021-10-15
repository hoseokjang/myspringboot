package com.mongta.myspringboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.repository.UserRepository;

@Controller
public class UserController {
	
	// Autowired를 사용하지 않고 주입받는 방법 -> Constructor Injection
	private final UserRepository userRepository;
	public UserController(UserRepository repository)
	{
		this.userRepository = repository;
	}
		
	@GetMapping("/thymeleaf")
	public String thymeleaf(Model model)
	{
		model.addAttribute("name","hoseok");
		return "leaf";
	}
	
	@GetMapping("/index")
	public ModelAndView index()
	{
		List<User> userlist = userRepository.findAll();
		return new ModelAndView("index","users",userlist);
		// ("url","model이름",model변수)
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(User user)
	{
		return "add-user";
	}
	
	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result)
	{ // PPT와 다르게 Model을 사용하지 않고 redirect 사용
		if(result.hasErrors()) // 입력 검증 오류가 있으면
		{
			return "add-user";
		}
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model)
	{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result)
	{
		if (result.hasErrors())
		{
			user.setId(id);
			return "update-user";
		}
		userRepository.save(user);
		return "redirect:/index";
	}	
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id)
	{
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:/index";
	}
	
	@GetMapping("/mypage")
	public String mypage()
	{
		return "mypage";
	}

}

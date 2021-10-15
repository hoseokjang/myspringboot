package com.mongta.myspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.entity.Users;
import com.mongta.myspringboot.exception.ResourceNotFoundException;
import com.mongta.myspringboot.repository.UserRepository;

@RestController
public class UserRestController {

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	public User create(@RequestBody User user)
	{
		return userRepository.save(user);
	}
	
	@RequestMapping("/users/{id}")
	public User getUser(@PathVariable Long id)
	{
		Optional<User> optional = userRepository.findById(id);
		User user = optional.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return user;
	}
	
	@RequestMapping(value = "/users",produces= {"application/json"})
	public List<User> getUsers()
	{
		return userRepository.findAll();
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetail)
	{
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id)
	{
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty()) // id와 매핑되는 User 객체가 없으면
		{
			return new ResponseEntity<>("id "+ id + " User Not Found", HttpStatus.NOT_FOUND); 
		}
		userRepository.deleteById(id);
		return ResponseEntity.ok("id " + id + " User 삭제 되었음.");
	}
	
	@RequestMapping(value="/userxml", produces = {"application/xml"})
	public Users getUserXml()
	{
		Users users = new Users();
		users.setUsers(userRepository.findAll());
		return users;
	}
}

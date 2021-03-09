package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {
	
	@Autowired
	private UserRepository UserRepository;
	
	@GetMapping("users")
	public List<User> getUsers(){
		return this.UserRepository.findAll();
	}
	
	@DeleteMapping("users/delete/{id}")
	public String deleteUserById(@PathVariable long id) {
		UserRepository.deleteById(id);
	    return "successfully deleted";
	}
	
	@GetMapping("users/get/{id}")
	public User getUser(@PathVariable long id) {
		List<User> users = UserRepository.findAll();
		if(!users.isEmpty()) {
		  for (User user: users) {
		    if (user.getUserId() == id) {
		      return user;
		    }
		  }
		}
		return null;
	}
	
	@PostMapping("users/create")
	public String createUser(@RequestBody User user){
		UserRepository.save(user);
		return "successfully user added";
	}
	
	@PutMapping("users/update/{id}")
	public String updateUser(@PathVariable long id,@RequestBody User user) {
		List<User> userList = UserRepository.findAll();
	
		for(User usr : userList) {
			if(usr.getUserId() == id) 
			{
				usr.setFirstName(user.getFirstName());
				usr.setLastName(user.getLastName());
				usr.setEmail(user.getEmail());
				UserRepository.save(usr);
			}
		}
		
		return "success";
	}
	
}

package com.tjs.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjs.userservice.entity.User;
import com.tjs.userservice.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/user-service")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/create-user")
	public ResponseEntity<User> createUser(@RequestBody User user) {

		return userService.saveUser(user);

	}

	@GetMapping("/get-users")
	public List<User> getUsers() {
		return userService.findAllUsers();
	}
	
	@GetMapping("get-user-by-id/{id}")
	public User getUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@PutMapping("/update-user/{id}")
	public ResponseEntity<User> updateTutorial(@PathVariable long id, @RequestBody User user) {

		return userService.updateUser(id, user);

	}

	@DeleteMapping("/delete/{userId}")
	public String deleteUser(@PathVariable long userId) {
		return userService.deleteUser(userId);

	}

}

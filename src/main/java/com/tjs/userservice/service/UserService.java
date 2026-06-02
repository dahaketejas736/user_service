package com.tjs.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tjs.userservice.entity.User;
import com.tjs.userservice.exception.ResourceNotFoundException;
import com.tjs.userservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<User> saveUser(User user) {

		try {
			userRepository.save(user);

			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);

		}
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findUserById(Long id) {
		return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No user found with given id"+id));
	}

	public String deleteUser(Long id) {
		try {
			userRepository.deleteById(id);
			return "User Deleted Successfully..!!";
		} catch (Exception e) {
			return "Failed to delete user : "+e.getMessage();
		}
		
	}

	public ResponseEntity<User> updateUser(long userId, User user) {
		Optional<User> userData = userRepository.findById(userId);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setEmailId(user.getEmailId());
			_user.setPhoneNumber(user.getPhoneNumber());
			_user.setUsername(user.getUsername());
			_user.setPassword(user.getPassword());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}

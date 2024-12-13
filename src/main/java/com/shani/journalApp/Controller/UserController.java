package com.shani.journalApp.Controller;

import java.util.List;

import com.shani.journalApp.Entity.Users;
import com.shani.journalApp.Repository.UserRepository;
import com.shani.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

	 
	 @Autowired
	 private UserService userService;

	 @Autowired
	 private UserRepository userRepository;
	 
	 @GetMapping
	 public List<Users> getAllUser(){

		 return userService.getAll();
	 }
	 
	 

	 
	 @PutMapping
	 public ResponseEntity<?> updateUser(@RequestBody Users user){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 String userName = authentication.getName();
		 Users userInDbUser = userService.findByUserName(userName);
		 if (userInDbUser != null) {
			 
			 userInDbUser.setUserName(user.getUserName());
			 userInDbUser.setPassword(user.getPassword());
			 userService.saveNewUser(userInDbUser);
			 
			 }
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 
	 }

	 @DeleteMapping
	public ResponseEntity<?> deleteUserById(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 userRepository.deleteByUserName(authentication.getName());
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	 }
}

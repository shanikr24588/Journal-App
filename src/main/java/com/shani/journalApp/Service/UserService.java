package com.shani.journalApp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.shani.journalApp.Entity.Users;
import com.shani.journalApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class UserService {
	
//	For Business Logic
	
	@Autowired
	private UserRepository userRepository;

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public boolean saveNewUser(Users user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			 log.error("Error occured for {} :", user.getUserName(), e);
			return false;
		}

		
	}

	public void saveAdmin(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER", "ADMIN"));
		userRepository.save(user);

	}

	public void saveUser(Users user){
		userRepository.save(user);
	}
	
	public List<Users> getAll(){

		return userRepository.findAll();
	}
	
	public Optional<Users> findById(ObjectId id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId Id) {
		userRepository.deleteById(Id);
	}
	
	public Users findByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
		
	}

}

package com.shani.journalApp.Repository;

import com.shani.journalApp.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface UserRepository extends MongoRepository<Users, ObjectId> {
	
	Users findByUserName(String username);

	void deleteByUserName(String userName);

}

package com.shani.journalApp.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
 

@Document(collection = "users")
@Data
public class Users {

	
//	 @Setter
//     @Getter
     private ObjectId id;
//	 @Setter
//     @Getter
     @Indexed(unique = true)
	 @NonNull
	 private String userName;

//    @Getter
//    @Setter
    @NonNull
	 private String password;
	 
//	 @Setter
//     @Getter
     @DBRef
	 private List<JournalEntry> journalEntries = new ArrayList<>();

	private List<String> roles;
	 
	 

}

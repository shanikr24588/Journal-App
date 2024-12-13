package com.shani.journalApp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.shani.journalApp.Entity.JournalEntry;
import com.shani.journalApp.Entity.Users;
import com.shani.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class JournalEntryService {
	
//	For Business Logic
	
	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);

	@Transactional
	public void saveEntry(JournalEntry journalEntry, String userName) {
		try {
			Users user = userService.findByUserName(userName);
			journalEntry.setDate(LocalDateTime.now());
			JournalEntry saved = journalEntryRepository.save(journalEntry);
			user.getJournalEntries().add(saved);
			userService.saveUser(user);
		} catch (Exception e){
			System.out.println(e);
			throw new RuntimeException("An error occur while saving the entry,", e);
		}

		
	}

	public void saveEntry(JournalEntry journalEntry) {


		journalEntryRepository.save(journalEntry);

	}
	
	public List<JournalEntry> getAll(){

		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {

		return journalEntryRepository.findById(id);
	}

	@Transactional
	public boolean deleteById(ObjectId Id, String userName) {
		boolean removed = false;
		try {
			Users user = userService.findByUserName(userName);
			removed = user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
			if(removed){
				userService.saveUser(user);
				journalEntryRepository.deleteById(Id);
			}

		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("An error occurred while deleteting the entry.", e);
		}
		return removed;


	}


//	public List<JournalEntry> findByUserName(String userName){
//
//	}

}

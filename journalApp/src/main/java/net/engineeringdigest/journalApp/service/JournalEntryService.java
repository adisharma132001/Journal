  package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;

@Service
public class JournalEntryService {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private JournalEntryRepo journalEntryRepo;
	
	
	@Transactional
	public void saveEntry(JournalEntry journalEntry, String Username) {
		
		try {
			User user=userService.findByuserName(Username);
			JournalEntry Saved = journalEntryRepo.save(journalEntry); 
			user.getGetjournalEntries().add(Saved);
			userService.saveEntry(user);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			throw new RuntimeException("occured while saving the entry.",e);
		}
		
		
	}
	
public void saveEntry(JournalEntry journalEntry) {
		
		journalEntryRepo.save(journalEntry); 
		
	}
	
	public List<JournalEntry> getAll(){
		return journalEntryRepo.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id){
		return journalEntryRepo.findById(id);
	}
	
	public void deleteById(ObjectId id, String username) {
		User user=userService.findByuserName(username);
		user.getGetjournalEntries().removeIf(x -> x.getId().equals(id));
		userService.saveEntry(user);
 		journalEntryRepo.deleteById(id);
 		
	}
	
}

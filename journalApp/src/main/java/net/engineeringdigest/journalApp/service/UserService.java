package net.engineeringdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserRepo;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepo UserRepo;
	
	public void saveEntry(User user) {
		UserRepo.save(user);
	}
	
	public List<User> getAll(){
		return UserRepo.findAll();
	}
	
	public Optional<User> findById(ObjectId id){
		return UserRepo.findById(id);
	}
	
	public void deleteById(ObjectId id) {
		UserRepo.deleteById(id);
	}
	
	public User findByuserName(String username){
		return UserRepo.findByUserName(username );
	}
	
}

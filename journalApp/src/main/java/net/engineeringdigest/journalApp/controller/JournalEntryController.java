package net.engineeringdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	@Autowired
	private JournalEntryService journalEntryService;
	
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public  ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username){
		
		 User user = userService.findByuserName(username); 
		 List<JournalEntry> all = user.getGetjournalEntries();  
		 if (all !=null && !all.isEmpty()) {
			 return new ResponseEntity<>(all, HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/{username}")
	public ResponseEntity<JournalEntry>  createEntry(@RequestBody JournalEntry myEntry, @PathVariable String username) {
		
  		myEntry.setDate(LocalDateTime.now());
		journalEntryService.saveEntry(myEntry, username);
		
		return new ResponseEntity<JournalEntry>(HttpStatus.OK);
	}
	
	@GetMapping("id/{myId}")
	public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
		Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
		if(journalEntry.isPresent()) {
			return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
		}
		return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("id/{username}/{myId}")
	public boolean deleteEntryById(@PathVariable ObjectId myId, @PathVariable String username) {
		journalEntryService.deleteById(myId, username);
		return true;
	}
	
	@PutMapping("/id/{username}/{id}")
	public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String username) {
		//TODO: process PUT request
		JournalEntry old = journalEntryService.findById(id).orElse(null);
		if(old != null) {
		old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
		old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
		journalEntryService.saveEntry(old);
		return new ResponseEntity<>(old, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}

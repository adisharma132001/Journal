package net.engineeringdigest.journalApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAll(){
		return userService.getAll();
	}
	
	@PostMapping
	public void saveAll(@RequestBody User user){
		userService.saveEntry(user);
	}
	
	@PutMapping("/{userName}")
	public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
		User UserInDb = userService.findByuserName(userName);
		if(UserInDb != null) {
			user.setUserName(user.getUserName());
			user.setPassword(user.getPassword());
			userService.saveEntry(UserInDb);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}

package net.engineeringdigest.journalApp.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NonNull;


@Document(collection = "users")
@Data
public class User {

	@Id
	private ObjectId id;
	
	@NonNull
	@Indexed(unique = true)
	private String userName;
	
	@NonNull
	private String password;
	
	@DBRef
	private List<JournalEntry> getjournalEntries = new ArrayList<>();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<JournalEntry> getGetjournalEntries() {
		return getjournalEntries;
	}

	public void setGetjournalEntries(List<JournalEntry> getjournalEntries) {
		this.getjournalEntries = getjournalEntries;
	} 
}

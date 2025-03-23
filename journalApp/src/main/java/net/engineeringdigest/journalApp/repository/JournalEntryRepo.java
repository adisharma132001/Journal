package net.engineeringdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import net.engineeringdigest.journalApp.entity.JournalEntry;

@Repository
@Component
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {
	
}

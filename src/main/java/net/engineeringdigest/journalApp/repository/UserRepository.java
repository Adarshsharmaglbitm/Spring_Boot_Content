package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.Enitity.JournalEntry;
import net.engineeringdigest.journalApp.Enitity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, ObjectId>{
    User findByUsername(String username);

}

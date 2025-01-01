package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Enitity.JournalEntry;
import net.engineeringdigest.journalApp.Enitity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
        @Autowired
        private UserRepository userRepository;

        public void saveEntry(User user)
        {
            userRepository.save(user);
        }

        public List<User> getAll()
        {
            return userRepository.findAll();
        }

        public Optional<User> findById(ObjectId id)
        {
            return userRepository.findById(id);
        }
        public void deleteById(ObjectId id)
        {
            userRepository.deleteById(id);
        }

        public  User findByUserName(String userName)
        {
            return  userRepository.findByUsername(userName);
        }
    }
//controller ---> service ------->   repository


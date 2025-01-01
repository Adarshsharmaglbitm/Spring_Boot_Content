package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Enitity.JournalEntry;
import net.engineeringdigest.journalApp.Enitity.User;
import net.engineeringdigest.journalApp.Service.JournalEntryService;
import net.engineeringdigest.journalApp.Service.UserService;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
      @Autowired
      private JournalEntryService journalEntryService;

      @Autowired
      private UserService userService;
    // Get all journal entries
    @GetMapping("{userName}")
    public ResponseEntity<?>getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user=userService.findByUserName(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if (all != null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    // Create a new journal entry
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            User user=userService.findByUserName(userName);
//            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        journalEntries.put(myEntry.getId(), myEntry);

    }

    // Get a specific journal entry by ID
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
        if(journalEntry.isPresent())
        {
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a journal entry by ID
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {

//        return journalEntries.remove(myId);
       journalEntryService.deleteById(myId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Update an existing journal entry by ID
    @PutMapping("/id/{id}")
    public ResponseEntity<?>  updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
//        journalEntries.put(id, myEntry);
        JournalEntry old=journalEntryService.findById(id).orElse(null);
        if (old !=null)
        {
            old.setTittle(newEntry.getTittle() !=null && newEntry.getTittle().equals("") ? newEntry.getTittle() : old.getTittle());
            old.setContent(newEntry.getContent() !=null & newEntry.equals("") ? newEntry.getContent() :old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK) ;

        }
//        old.setDate(LocalDateTime.now());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

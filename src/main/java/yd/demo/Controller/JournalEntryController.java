package yd.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yd.demo.Entry.JournalEntry;
import yd.demo.Entry.UserEntry;
import yd.demo.Services.JournalEntryServices;
import yd.demo.Services.UserEntryServices;

@RestController
@RequestMapping("journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryService;

    @Autowired UserEntryServices userService;

    // Get all Journal Entries
    @GetMapping("all")
    public List<JournalEntry> getAll() {
        return journalEntryService.GetAllJournalEntry();
    }

    // Add a new Journal Entry
    @PostMapping("add")
    public ResponseEntity<HttpStatus> addEntry(@RequestBody JournalEntry entry) {
        journalEntryService.saveJournalEntry(entry);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Get a Journal Entry by ID
    @GetMapping("get/{id}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId id) {
        Optional<JournalEntry> entry = journalEntryService.GetJournalEntry(id);

        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a Journal Entry
    @PutMapping("update/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry entry) {
        JournalEntry Journal = journalEntryService.updateJournalEntry(entry, id);
        if(Journal != null){
            return new ResponseEntity<>(Journal, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a Journal Entry
    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteEntry(@PathVariable ObjectId id) {
        journalEntryService.deleteEntry(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    // Get all Journal Entry of User
    @RequestMapping("/all/{username}")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String username ){
        UserEntry userEntry = userService.getUserByUsername(username);
        List <JournalEntry> JEntry = userEntry.getJournalEntry();
        if(JEntry != null && !JEntry.isEmpty()){
            return new ResponseEntity<>(JEntry, HttpStatus.OK);
        }else{
            Object msg = "Journal entry is not found";
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
    }
}
